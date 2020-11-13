package kr.co.engcom.dto;

public class TimeLine {
	private String content_kor;
	private String content_eng;
	private int timeLineNumber;

	public String getContent_kor() {
		return content_kor;
	}
	public void setContent_kor(String content_kor) {
		this.content_kor = content_kor;
	}
	public String getContent_eng() {
		return content_eng;
	}
	public void setContent_eng(String content_eng) {
		this.content_eng = content_eng;
	}
	public int getTimeLineNumber() {
		return timeLineNumber;
	}
	public void setTimeLineNumber(int timeLineNumber) {
		this.timeLineNumber = timeLineNumber;
	}
	
	public TimeLine() {}
	
	
	public TimeLine(String content_eng, String content_kor) {
		super();
		this.content_kor = content_kor;
		this.content_eng = content_eng;
	}
	public TimeLine(String content_eng, String content_kor, int timeLineNumber) {
		super();
		this.content_kor = content_kor;
		this.content_eng = content_eng;
		this.timeLineNumber = timeLineNumber;
	}
	@Override
	public String toString() {
		return "TimeLine [content_kor=" + content_kor + ", content_eng=" + content_eng + ", timeLineNumber="
				+ timeLineNumber + "]";
	}
	
	

}
