package com.cafe24.mysite.vo;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class BoardVo {
	private MultipartFile attach;

	public MultipartFile getAttach() {
		return attach;
	}

	public void setAttach(MultipartFile attach) {
		this.attach = attach;
	}

	private Long no;

	@NotEmpty
	private String title;

	private int hit;
	private String reg_date;
	private String contents;
	private int group_no;
	private int order_no;
	private int depth;
	private String isexist;

	private Long memberNo;
	private String originfilename;
	private String savefilename;

	public String getIsexist() {
		return isexist;
	}

	public void setIsexist(String isexist) {
		this.isexist = isexist;
	}

	public String getOriginfilename() {
		return originfilename;
	}

	public void setOriginfilename(String originfilename) {
		this.originfilename = originfilename;
	}

	public String getSavefilename() {
		return savefilename;
	}

	public void setSavefilename(String savefilename) {
		this.savefilename = savefilename;
	}

	private String memberName;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getGroup_no() {
		return group_no;
	}

	public void setGroup_no(int group_no) {
		this.group_no = group_no;
	}

	public int getOrder_no() {
		return order_no;
	}

	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public Long getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Override
	public String toString() {
		return "BoardVo [attach=" + attach + ", no=" + no + ", title=" + title + ", hit=" + hit + ", reg_date="
				+ reg_date + ", contents=" + contents + ", group_no=" + group_no + ", order_no=" + order_no + ", depth="
				+ depth + ", isexist=" + isexist + ", memberNo=" + memberNo + ", originfilename=" + originfilename
				+ ", savefilename=" + savefilename + ", memberName=" + memberName + "]";
	}

}
