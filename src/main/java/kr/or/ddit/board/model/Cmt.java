package kr.or.ddit.board.model;

public class Cmt {

	private int cmtId;
	private String cmtContent;
	private String cmtReg_dt;
	private int postId;
	private String userId;
	public int getCmtId() {
		return cmtId;
	}
	public void setCmtId(int cmtId) {
		this.cmtId = cmtId;
	}
	public String getCmtContent() {
		return cmtContent;
	}
	public void setCmtContent(String cmtContent) {
		this.cmtContent = cmtContent;
	}
	public String getCmtReg_dt() {
		return cmtReg_dt;
	}
	public void setCmtReg_dt(String cmtReg_dt) {
		this.cmtReg_dt = cmtReg_dt;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
