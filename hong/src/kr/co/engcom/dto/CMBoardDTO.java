package kr.co.engcom.dto;

import java.util.Date;

public class CMBoardDTO {
	private int CMBoardNumber;
	private String CMBoardId;		
	private String CMBoardSubject;	
	private String CMBoardFile;	
	private String CMBoardAnswer; 
	private Date CMBoardDate;

	public int getCMBoardNumber() {
		return CMBoardNumber;
	}
	public void setCMBoardNumber(int cMBoardNumber) {
		CMBoardNumber = cMBoardNumber;
	}
	public String getCMBoardId() {
		return CMBoardId;
	}
	public void setCMBoardId(String cMBoardId) {
		CMBoardId = cMBoardId;
	}
	public String getCMBoardSubject() {
		return CMBoardSubject;
	}
	public void setCMBoardSubject(String cMBoardSubject) {
		CMBoardSubject = cMBoardSubject;
	}
	public String getCMBoardFile() {
		return CMBoardFile;
	}
	public void setCMBoardFile(String cMBoardFileURL) {
		CMBoardFile = cMBoardFileURL;
	}
	public String getCMBoardAnswer() {
		return CMBoardAnswer;
	}
	public void setCMBoardAnswer(String cMBoardAnswer) {
		CMBoardAnswer = cMBoardAnswer;
	}
	public Date getCMBoardDate() {
		return CMBoardDate;
	}
	public void setCMBoardDate(Date cMBoardDate) {
		CMBoardDate = cMBoardDate;
	}
	@Override
	public String toString() {
		return "CMBoardDTO [CMBoardNumber=" + CMBoardNumber + ", CMBoardId=" + CMBoardId + ", CMBoardSubject="
				+ CMBoardSubject + ", CMBoardFileURL=" + CMBoardFile + ", CMBoardAnswer=" + CMBoardAnswer
				+ ", CMBoardDate=" + CMBoardDate + "]";
	}	
	}
