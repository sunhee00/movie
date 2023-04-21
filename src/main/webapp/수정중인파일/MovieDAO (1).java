package teamProject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import member.MemberVO;

public class MovieDAO {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;
		
	//db연결
	public MovieDAO() {
		try {
			Context ctx = new InitialContext(); 
			Context envContext = (Context)ctx.lookup("java:/comp/env"); //컨텍스트xml 리소스
			dataFactory =(DataSource) envContext.lookup("jdbc/oracle"); //오라클연결
		} catch (Exception e) {
			System.out.println("movieDAO에서 db연결중 에러");
		}
	}
		
	//영화정보 조회 메소드
	public List listMovies() {
		List movieList = new ArrayList();
		try {
			conn = dataFactory.getConnection(); //db연결
			String query = "select mvTitle, img, mvContent, runTime, openDate from movie order by no";
			System.out.println("movieDAO에서 쿼리확인"+query); // db연결확인
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(); 
			while(rs.next()) { 
				String mvTitle = rs.getString("mvTitle");//실제 db값
				String img = rs.getString("img");
				String mvContent = rs.getString("mvContent");
				String runTime = rs.getString("runTime");
				Date openDate = rs.getDate("openDate");
				MovieVO movieVO= new MovieVO();
				movieVO.setMvTitle(mvTitle);
				movieVO.setImg(img);
				movieVO.setMvContent(mvContent);
				movieVO.setRunTime(runTime);
				movieVO.setOpenDate(openDate);
				movieList.add(movieVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("movieDAO에서 listMovies메소드 중 db조회중에러");
		}
		return movieList;
	}
		
		

	//영화 예매가능 일정 조회 메소드
	public List ListMvInfo() {
		List mvInfoList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			String query = "select mvTitle, theater, showingDate, showingTime from movieInfo";
			System.out.println("movieDAO에서 listMvInfo메소드에서"+query); 
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String mvTitle = rs.getString("mvTitle");
				String theater = rs.getString("theater");
				String showingDate = rs.getString("showingDate");
				String showingTime = rs.getString("showingTime");
				MovieInfoVO movieInfoVO = new MovieInfoVO();
				movieInfoVO.setMvTitle(mvTitle);
				movieInfoVO.setTheater(theater);;
				movieInfoVO.setShowingDate(showingDate);
				movieInfoVO.setShowingTime(showingTime);
				mvInfoList.add(movieInfoVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("movieDAO에서 listMvInfo메소드에서 db조회중 에러");
		}
		return mvInfoList;
	}
		
	//예매하기 메소드
	public void addTicket(MovieInfoVO movieInfoVO) {
		try {
			conn = dataFactory.getConnection(); //db연결
			String id = movieInfoVO.getId();
			String mvTitle = movieInfoVO.getMvTitle();
			String theater = movieInfoVO.getTheater();
			String showingDate = movieInfoVO.getShowingDate();
			String showingTime = movieInfoVO.getShowingTime();
			String seatNum = movieInfoVO.getSeatNum();
			String bookNum = movieInfoVO.getBookNum();
			String query = "insert into ticketinfo (id, mvTitle, theater, showingDate, showingTime, seatNum, bookNum) values(?,?,?,?,?,?,?)";
			System.out.println("movieDAO에서 addTicket메소드에서"+query); 
			pstmt = conn.prepareStatement(query);
			System.out.println(id);
			System.out.println(mvTitle);
			System.out.println(theater);
			System.out.println(showingDate);
			System.out.println(showingTime);
			System.out.println(seatNum);
			System.out.println(bookNum);
			pstmt.setString(1, id);
			pstmt.setString(2, mvTitle);
			pstmt.setString(3, theater);
			pstmt.setString(4, showingDate);
			pstmt.setString(5, showingTime);
			pstmt.setString(6, seatNum );
			pstmt.setString(7, bookNum);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("movieDAO에서 addTicket메소드에서 예매저장중에러");
		}
	}
		

	//중복좌석 조회 메소드
	public List<MovieInfoVO> seatCheck(String _mvTitle, String _theater, String _showingDate, String _showingTime) {
		List<MovieInfoVO> seatList=new ArrayList();
         try {
            conn = dataFactory.getConnection(); //db연결
            String query = "select rtrim(seatNum,',') as seatNum from ticketInfo where mvTitle=? and theater=? and showingDate=? and showingTime=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, _mvTitle);
            pstmt.setString(2, _theater);
            pstmt.setString(3, _showingDate);
            pstmt.setString(4, _showingTime);
            ResultSet rs = pstmt.executeQuery();
       
            while(rs.next()) {
            	MovieInfoVO vo = new MovieInfoVO();
            	vo.setSeatNum(rs.getString("seatNum"));
            	seatList.add(vo);
            }
            pstmt.close();
               conn.close();
               rs.close();
         } catch (Exception e) {
            System.out.println("movieDAO에 seatCheck메소드에서 좌석 조회중 에러");
         }
         return seatList;
      }
	
	//예매내역 조회 메소드
	public MovieInfoVO ticketChk(String _id) {
		MovieInfoVO movieInfoVO=null;
		try {
			conn=dataFactory.getConnection();
			String query="select * from ticketInfo where id=?";
			pstmt=conn.prepareStatement(query);
			System.out.println(query);
			pstmt.setString(1, _id);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
				String id=rs.getString("id");
				String mvTitle=rs.getString("mvTitle");
				String theater=rs.getString("theater");
				String showingDate=rs.getString("showingDate");
				String showingTime=rs.getString("showingTime");
				String seatNum=rs.getString("seatNum");
				String bookNum=rs.getString("bookNum");
				movieInfoVO=new MovieInfoVO(id, mvTitle, theater, showingDate, showingTime, seatNum, bookNum);

			rs.close();
			pstmt.close();
			conn.close();	
		} catch (Exception e) {
			System.out.println("movieDAO의 addTicket메소드 예매조회중 에러");
		}
		return movieInfoVO;
	}
	
	//예매내역 목록
	public List<MovieInfoVO> listTicket(String _id) {
		List<MovieInfoVO> ticketList = new ArrayList();
		try {
			conn = dataFactory.getConnection(); 
			String query= "select * from ticketInfo where id=?";
			System.out.println("movieDAO에서 쿼리확인"+query); 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, _id);
			ResultSet rs = pstmt.executeQuery(); 
			while(rs.next()) { 
				String id=rs.getString("id");
				String mvTitle=rs.getString("mvTitle");
				String theater=rs.getString("theater");
				String showingDate=rs.getString("showingDate");
				String showingTime=rs.getString("showingTime");
				String seatNum=rs.getString("seatNum");
				String bookNum=rs.getString("bookNum");
				MovieInfoVO movieInfoVO = new MovieInfoVO(); 
				movieInfoVO.setId(id);
				movieInfoVO.setMvTitle(mvTitle);
				movieInfoVO.setTheater(theater);
				movieInfoVO.setShowingDate(showingDate);
				movieInfoVO.setShowingTime(showingTime);
				movieInfoVO.setSeatNum(seatNum);
				movieInfoVO.setBookNum(bookNum);
				ticketList.add(movieInfoVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("예매내역 조회중 에러");
		}
		return ticketList;
	}
	
	//예매취소
	public void delTicket(String bookNum) {
		try {
			conn=dataFactory.getConnection();
			String query="delete from ticketInfo where bookNum=?";
			//System.out.println(query + id);
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, bookNum);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("예매취소 중 에러");
		}
	}
}		

