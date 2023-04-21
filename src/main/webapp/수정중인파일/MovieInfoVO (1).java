package teamProject;

import java.sql.Array;
import java.sql.Date;
import java.util.List;

public class MovieInfoVO {
	private String id;
	private String mvTitle;
	private String theater;
	private String showingDate;
	private String showingTime;
	private String seatNum;
	private String bookNum;
	
	public MovieInfoVO() {
	
	}


	
	public MovieInfoVO(String mvTitle, String theater, String showingDate, String showingTime, String seatNum) {
		super();
		this.mvTitle = mvTitle;
		this.theater = theater;
		this.showingDate = showingDate;
		this.showingTime = showingTime;
		this.seatNum = seatNum;

	}
	
	public MovieInfoVO(String mvTitle, String theater, String showingDate, String showingTime, String seatNum, String bookNum) {
		super();
		this.mvTitle = mvTitle;
		this.theater = theater;
		this.showingDate = showingDate;
		this.showingTime = showingTime;
		this.seatNum = seatNum;
		this.bookNum = bookNum;
	}

	
	
	public MovieInfoVO(String id, String mvTitle, String theater, String showingDate, String showingTime,
			String seatNum, String bookNum) {
		super();
		this.id = id;
		this.mvTitle = mvTitle;
		this.theater = theater;
		this.showingDate = showingDate;
		this.showingTime = showingTime;
		this.seatNum = seatNum;
		this.bookNum = bookNum;
	}



	public String getMvTitle() {
		return mvTitle;
	}

	public void setMvTitle(String mvTitle) {
		this.mvTitle = mvTitle;
	}

	public String getTheater() {
		return theater;
	}

	public void setTheater(String theater) {
		this.theater = theater;
	}

	public String getShowingDate() {
		return showingDate;
	}

	public void setShowingDate(String showingDate) {
		this.showingDate = showingDate;
	}

	public String getShowingTime() {
		return showingTime;
	}

	public void setShowingTime(String showingTime) {
		this.showingTime = showingTime;
	}

	public String getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}

	public String getBookNum() {
		return bookNum;
	}

	public void setBookNum(String bookNum) {
		this.bookNum = bookNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
