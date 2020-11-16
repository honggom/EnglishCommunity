package kr.co.engcom.dto;

import java.sql.Date;

public class BoardDto {
	private int contentNumber;	//글번호
	private String id;			//아이디
	private String contentTitle;//글제목
	private String content;		//글내용
	private String filename;	//첨부파일명
	private int filesize;		//첨부파일사이즈
	private int refer;			//원글번호
	private int depth;			//들여쓰기
	private int step;			//답글정렬
	private int replyNumber;	//댓글 번호
	private String replyContent;//글 내용
	private int boardCode;		//게시판 코드
	private String boardName;	//게시판 이름
	private String reportingDate;	//작성일
	
	
	public int getContentNumber() {
		return contentNumber;
	}
	public void setContentNumber(int contentNumber) {
		this.contentNumber = contentNumber;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContentTitle() {
		return contentTitle;
	}
	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getFilesize() {
		return filesize;
	}
	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}
	public int getRefer() {
		return refer;
	}
	public void setRefer(int refer) {
		this.refer = refer;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}

	public int getReplyNumber() {
		return replyNumber;
	}
	public void setReplyNumber(int replyNumber) {
		this.replyNumber = replyNumber;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public int getBoardCode() {
		return boardCode;
	}
	public void setBoardCode(int boardCode) {
		this.boardCode = boardCode;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getReportingDate() {
		return reportingDate;
	}
	public void setReportingDate(String reportingDate) {
		this.reportingDate = reportingDate;
	}
	

	
}
