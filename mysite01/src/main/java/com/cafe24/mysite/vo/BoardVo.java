package com.cafe24.mysite.vo;

public class BoardVo {

	private Long no;
	private String title;
	private String cnt;
	private String regDate;
	private Long member_no;
	private String contents;
	
	private String memberName;
	private String memberEmail;
	
	
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
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
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public Long getMember_no() {
		return member_no;
	}
	public void setMember_no(Long member_no) {
		this.member_no = member_no;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", cnt=" + cnt + ", regDate=" + regDate + ", member_no="
				+ member_no + ", contents=" + contents + ", memberName=" + memberName + ", memberEmail=" + memberEmail
				+ "]";
	}
	
	
	
}
