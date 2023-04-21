package movieInfo;

import java.sql.Date;

public class MovieVO {
	private String mvTitle;
	private String img;
	private String runTime;
	private String mvContent;
	private Date openDate;
	private String mvCode;
	private int no;
	
	public MovieVO() {
		System.out.println("movieVO 호출 확인");
	}
	
	public MovieVO(String mvTitle, String img, String runTime, String mvContent, Date openDate, String mvCode) {
		this.mvTitle = mvTitle;
		this.img = img;
		this.runTime = runTime;
		this.mvContent = mvCode;
		this.openDate = openDate;
		this.mvCode = mvCode;
	}
	
	public MovieVO(String mvTitle, String mvContent) {
		this.mvTitle = mvTitle;
		this.mvContent = mvCode;
	}

	public String getMvTitle() {
		return mvTitle;
	}

	public void setMvTitle(String mvTitle) {
		this.mvTitle = mvTitle;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getRunTime() {
		return runTime;
	}

	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}

	public String getMvContent() {
		return mvContent;
	}

	public void setMvContent(String mvContent) {
		this.mvContent = mvContent;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public String getMvCode() {
		return mvCode;
	}

	public void setMvCode(String mvCode) {
		this.mvCode = mvCode;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
	
}
