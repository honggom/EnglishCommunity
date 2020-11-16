package kr.co.engcom.dto;

import java.util.Date;

public class CMReplyDTO {

	private int CMReplyNumber;		//댓글의 고유 번호 모든 게시물에서 공유함 따라서 모든 댓글 번호는 각자 다름 
	private int CMBoardNumber;		//댓글이 작성된 게시판의 번호 
	private String CMReplyContent;	//댓글 작성 내용
	private String CMReplyId;		//댓글 작성자 ID
	private Date CMReplyDate; 		//댓글 작성일시
	private String CMReplyStringDate; //댓글 작성일시 
	
	public String getCMReplyStringDate() {
		return CMReplyStringDate;
	}
	public void setCMReplyStringDate(String cMReplyStringDate) {
		CMReplyStringDate = cMReplyStringDate;
	}
	
	
	public int getCMReplyNumber() {
		return CMReplyNumber;
	}
	public void setCMReplyNumber(int cMReplyNumber) {
		CMReplyNumber = cMReplyNumber;
	}
	public int getCMBoardNumber() {
		return CMBoardNumber;
	}
	public void setCMBoardNumber(int cMBoardNumber) {
		CMBoardNumber = cMBoardNumber;
	}
	public String getCMReplyContent() {
		return CMReplyContent;
	}
	public void setCMReplyContent(String cMReplyContent) {
		CMReplyContent = cMReplyContent;
	}
	
	public String getCMReplyId() {
		return CMReplyId;
	}
	public void setCMReplyId(String cMReplyId) {
		CMReplyId = cMReplyId;
	}
	public Date getCMReplyDate() {
		return CMReplyDate;
	}
	public void setCMReplyDate(Date cMReplyDate) {
		CMReplyDate = cMReplyDate;
	}
	
	@Override
	public String toString() {
		return "CMReplyDTO [CMReplyNumber=" + CMReplyNumber + ", CMBoardNumber=" + CMBoardNumber + ", CMReplyContent="
				+ CMReplyContent + ", CMReplyId=" + CMReplyId + ", CMReplyDate=" + CMReplyDate + "]";
	}
	
}
