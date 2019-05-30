package com.cafe24.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.vo.BoardVo;

@Service
public class BoardService {

	private static final int COUNT_PER_PAGE = 8;
	private static final int PAGE_COUNT = 5;

	private static final String SAVE_PATH = "/mysite-uploads";

	@Autowired
	private BoardDao boarddao;

	public String createPagebar(int page,String kwd) {

		int firstPage = ((page - 1) / PAGE_COUNT) * PAGE_COUNT + 1;
		int previousPage = page > 1 ? page - 1 : 0;
		int nextPage = page + 1;
		int totalcount = boarddao.getTotalcount(kwd);
		int maxpage = totalcount / COUNT_PER_PAGE + 1;

		String result = "<div class=\"pager\">\r\n" + "	<ul>\r\n";

		if (previousPage == 0) {
			result += "<li>◀</li>\r\n";
		} else {
			result += "<li><a href='/mysite2/board/list?page=" + previousPage + "&kwd="+kwd+"'>◀</a></li>\r\n";
		}

		for (int i = 0; i < PAGE_COUNT; i++) {
			if (page == (firstPage + i)) {
				result += "<li class='selected'>" + (firstPage + i) + "</li>\r\n";
			} else {
				if ((firstPage + i) > maxpage) {
					result += "<li>" + (firstPage + i) + "</li>\r\n";
				} else {
					result += "<li><a href='/mysite2/board/list?page=" + (firstPage + i) + "&kwd="+kwd+"'>" + (firstPage + i)
							+ "</a></li>\r\n";
				}
			}
		}

		if (nextPage <= maxpage) {
			result += "<li><a href='/mysite2/board/list?page=" + nextPage + "&kwd="+kwd+"'>▶</a></li>\r\n";
		} else {
			result += "<li>▶</li>\r\n";
		}

		result += "</ul>\r\n" + "</div>";

		return result;
	}

	public List<BoardVo> getList(int page,String kwd) {
		Map<String, Object> map = new HashMap<String, Object>();
		int start = (page-1) * COUNT_PER_PAGE;
		map.put("start", start);
		map.put("countperpage", COUNT_PER_PAGE);
		map.put("kwd", kwd);
		List<BoardVo> result =  boarddao.getList(map);
		
		for(BoardVo vo:result) {
			vo.setTitle(vo.getTitle().replaceAll(kwd, "<font color=\"tomato\";>"+kwd+"</font>"));
		}
		
		
		return result;
	}

	public int writeContents(int replyno, BoardVo vo) {
		try {
			String originfilename = vo.getAttach().getOriginalFilename();
			String ext = originfilename.substring(originfilename.lastIndexOf(".") + 1);
			String savefilename = generateSaveFileName(ext);

			byte[] fileData = vo.getAttach().getBytes();

			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + savefilename);
			os.write(fileData);
			os.close();

			vo.setOriginfilename(originfilename);
			vo.setSavefilename(savefilename);

			if (replyno > 0) { // 답글 작성인 경우
				int groupno = boarddao.getGroupNo(replyno); // 답하려는 글의 group_no
				int orderno = boarddao.getOrderNo(replyno); // 답하려는 글의 order_no
				int depth = boarddao.getDepth(replyno); // 답하려는 글의 깊이

				Map<String, Integer> map = new HashMap<String, Integer>();
				map.put("groupno", groupno);
				map.put("orderno", orderno);

				// 해당 group_no 의 해당 orderno 와 같거나 큰 것들은 +1 업데이트
				boarddao.updateOrderNo(map);

				vo.setGroup_no(groupno);
				vo.setOrder_no(orderno);
				vo.setDepth(depth);

				return boarddao.replyinsert(vo);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return boarddao.insert(vo);
	}

	public BoardVo viewContent(String no, Boolean visited) {
		if (!visited)
			boarddao.updateHit(no);
		return boarddao.get(no);
	}

	private String generateSaveFileName(String extName) {
		String filename = "";
		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);

		return filename;
	}

	public int deleteContents(String no) {
		return boarddao.updateisexist(no);
	}

}
