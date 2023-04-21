package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class NoticeDAO {
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public void getCon () {
		try {
			Context initctx = new InitialContext();
			
			Context envctx = (Context) initctx.lookup("java:comp/env"); //Object클래스라서 Context로 하위 캐스팅
			DataSource ds = (DataSource) envctx.lookup("jdbc/oracle");
			con = ds.getConnection(); //커넥션 연결 해주는 메소드
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//전체 게시글 수를 구해주는 메소드
	public int getAllCount() {
		getCon();
		int count = 0;
		try {
			String sql = "select count(*) from notice";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return count;
		
	
		
	}
	
	//한 페이지당 게시글(5개가 최대)의 정보를 리턴해줌
	public Vector<NoticeBean> getAllBoard(int startNum, int endNum) {
		Vector<NoticeBean> v = new Vector<>();
		getCon();
		try {
			
			String sql = "select * from (select A.* ,Rownum Rnum from (select *from notice order by num desc) A)"
					+ "where Rnum >= ? and Rnum <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				NoticeBean bean = new NoticeBean();
				bean.setNum(rs.getInt(1));
				bean.setId(rs.getString(2));
				bean.setSubject(rs.getString(3));
				bean.setW_date(rs.getDate(4).toString());
				bean.setReadcount(rs.getInt(5));
				bean.setContent(rs.getString(6));
				v.add(bean);
			}
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return v;
		
	}
	
	//하나의 게시글을 리턴해주는 메소드
	public NoticeBean getOneBoard(int num, String sId) {
		getCon();
		NoticeBean bean = new NoticeBean();
		try {
			//글 작성자(admin)이 게시글을 클릭할 시 조회수증가 불가하도록 조건 걸기
			if(sId != "admin") {
				//조회 수 증가
				String readsql = "update notice set readcount = readcount+1 where num=?";
				pstmt = con.prepareStatement(readsql);
				pstmt.setInt(1, num);
				pstmt.executeQuery();
			}
			
			
			//하나의 게시글 리턴
			String sql = "select * from notice where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean.setNum(rs.getInt(1));
				bean.setId(rs.getString(2));
				bean.setSubject(rs.getString(3));
				bean.setW_date((rs.getDate(4).toString()));
				bean.setReadcount(rs.getInt(5));
				bean.setContent(rs.getString(6));
			}
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	//업데이트 할 게시글을 리턴(조회수증가x)
	public NoticeBean getUpdateBoard(int num) {
		getCon();
		NoticeBean bean = new NoticeBean();
		try {
			//하나의 게시글 리턴
			String sql = "select * from notice where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean.setNum(rs.getInt(1));
				bean.setId(rs.getString(2));
				bean.setSubject(rs.getString(3));
				bean.setW_date((rs.getDate(4).toString()));
				bean.setReadcount(rs.getInt(5));
				bean.setContent(rs.getString(6));
			}
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	
	//게시글 수정 메소드
	public NoticeBean updateBoard(int num, String subject, String content) {
		getCon();
		NoticeBean bean = new NoticeBean();
		try {
			//게시글 업데이트
			String sql = "update notice set subject = ?, content = ? where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setInt(3, num);
			pstmt.executeQuery();
			
			
			//업데이트된 게시글 불러오기
			String sql2 = "select * from notice where num = ?";
			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean.setNum(rs.getInt(1));
				bean.setId(rs.getString(2));
				bean.setSubject(rs.getString(3));
				bean.setW_date((rs.getDate(4).toString()));
				bean.setReadcount(rs.getInt(5));
				bean.setContent(rs.getString(6));
			}
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	//게시글 삭제
	public void noticeDelete(int num) {
		getCon();
		try {
			//게시글 삭제
			String dsql = "delete from notice where num = ?";
			pstmt = con.prepareStatement(dsql);
			pstmt.setInt(1, num);
			pstmt.executeQuery();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//게시글 등록
	public NoticeBean noticeCreate(String id, String subject, String content) {
		getCon();
		NoticeBean bean = new NoticeBean();
		int count=0;
		try {
			
			
			//insert
			String isql = "insert into notice (num, id, subject, w_date, readcount, content) values (notice_seq.nextval,?,?,sysdate,0,?)";
			pstmt = con.prepareStatement(isql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, subject);
			pstmt.setString(3, content);
			pstmt.executeQuery();
			
			//방금 인서트한 게시글 불러오기
			String ssql = "select * from notice where num = (select max(num) from notice)";
			pstmt = con.prepareStatement(ssql);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean.setNum(rs.getInt(1));
				bean.setId(rs.getString(2));
				bean.setSubject(rs.getString(3));
				bean.setW_date((rs.getDate(4).toString()));
				bean.setReadcount(rs.getInt(5));
				bean.setContent(rs.getString(6));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	//세션값 세팅하는 메소드
	public String getSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String sId = (String) session.getAttribute("id"); 
		System.out.println("noticeDAO에 있는 getSession에서 가져오는 id=>"+sId);
		return sId;
	}
	
	

}
