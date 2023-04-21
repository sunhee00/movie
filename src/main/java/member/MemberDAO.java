package member;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class MemberDAO {

	//DB 연결
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public MemberDAO() {
		try {
			Context ctx=new InitialContext();
			Context envContext=(Context)ctx.lookup("java:/comp/env");
			dataFactory=(DataSource)envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("DB 연결 에러 발생");
		}
	}
	
	//회원목록창(DB연결확인용/나중에 삭제)
	public List<MemberVO> listMembers() {
		List<MemberVO> memberList=new ArrayList<MemberVO>();
		try {
			conn=dataFactory.getConnection();
			String query="select * from memberlist order by mno desc";
			//System.out.println(query);
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			while (rs.next()) {
				int mno=rs.getInt("mno");
				String id=rs.getString("id");
				String pw=rs.getString("pw");
				String name=rs.getString("name");
				String email=rs.getString("email");
				String hp=rs.getString("hp");
				Date birth=rs.getDate("birth");
				MemberVO memberVO=new MemberVO(id, name, pw, email, hp, birth, mno);
				memberList.add(memberVO); //memberList에 get한 것들 세팅(Controller에)
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("DB 처리 중 에러");
		}
		return memberList;
	}
		
	//ID 중복 체크
	public boolean chkId(String id) { //사용자가 입력한 id를 받아 t/f로 체크
		boolean result=false;
		try {
			conn=dataFactory.getConnection();
			String query="select decode(count(*), 1, 'true', 'false') as result from memberlist where id=?";
					//decode(컬럼명, 조건, 참, 거짓) => id가 테이블에 존재하면(count해서 1이 나옴) true, 없으면(count=0) false
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			result=Boolean.parseBoolean(rs.getString("result"));
			//System.out.println("DAO ID : " + id + ", result : " + result);
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("아이디 중복 확인 중 에러");
		}
		return result;
	}
	
	//회원번호 생성
	private int getNewMemNumber() {
		try {
			conn=dataFactory.getConnection();
			String query="select max(mno) from memberlist";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				return (rs.getInt(1)+1);
			}
			rs.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("회원번호 생성 에러");
		}
		return 1000; //1001번부터 시작 => 안되는데..??
	}
	
	// 회원가입
	public void joinMember(MemberVO memberVO) {
		int mno=getNewMemNumber();
		try {
			conn=dataFactory.getConnection();
			String id=memberVO.getId();
			String name=memberVO.getName();
			String pw=memberVO.getPw();
			String email=memberVO.getEmail();
			String hp=memberVO.getHp();
			Date birth=memberVO.getBirth();
			String query="insert into memberlist(id, name, pw, email, hp, birth, mno) values(?, ?, ?, ?, ?, ?, ?)";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, pw);
			pstmt.setString(4, email);
			pstmt.setString(5, hp);
			pstmt.setDate(6, birth);
			pstmt.setInt(7, mno);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("회원가입/DB 저장 중 에러");
		}
	}
	
	//로그인
	public boolean login(MemberVO memberVO) {
		boolean result=false;
		try {
			conn=dataFactory.getConnection();
			String id=memberVO.getId();
			String pw=memberVO.getPw();
			String query="select decode(count(*), 1, 'true', 'false') as result from memberlist where id=? and pw=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			result=Boolean.parseBoolean(rs.getString("result"));
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("로그인/DB 처리 중 에러");
		}
		return result;
	}

	//회원 정보 수정
	  //비밀번호 일치 여부 체크
	public boolean cfmPwd(MemberVO memberVO) {
		boolean result=false;
		try {
			conn=dataFactory.getConnection();
			String id=memberVO.getId();
			String pw=memberVO.getPw();
			String query="select decode(count(*), 1, 'true', 'false') as result from memberlist where id=? and pw=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			result=Boolean.parseBoolean(rs.getString("result"));
			//System.out.println("DAO id: " + id + ", pw: " + pw + ", rs.getString: " + rs.getString("result") + ", result: " + result);
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("회원정보수정/탈퇴-비밀번호 확인 중 에러");
		}
		return result;
	}
	
	  //수정할 회원 정보 찾기
	public MemberVO findMember(String session_id) {
		MemberVO memberInfo=null;
		try {
			conn=dataFactory.getConnection();
			String query="select * from memberlist where id=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, session_id);
			//System.out.println(query);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			String name=rs.getString("name");
			String id=rs.getString("id");
			String pw=rs.getString("pw");
			String hp=rs.getString("hp");
			String email=rs.getString("email");
			Date birth=rs.getDate("birth");
			memberInfo=new MemberVO(id, name, pw, email, hp, birth);
			rs.close();
			pstmt.close();
			conn.close();	
		} catch (Exception e) {
			System.out.println("수정할 회원 정보 찾는 중 에러");
		}
		return memberInfo;
	}
	
	  //회원 정보 수정
	public void modMember(MemberVO memberVO) {
		String id=memberVO.getId();
		String pw=memberVO.getPw();
		String hp=memberVO.getHp();
		String email=memberVO.getEmail();
		try {
			conn=dataFactory.getConnection();
			String query="update memberlist set pw=?, hp=?, email=? where id=?";
			System.out.println(query + "pw: " + pw + ", hp: " + hp + ", emial: " + email + ", id: " + id);
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, pw);
			pstmt.setString(2, hp);
			pstmt.setString(3, email);
			pstmt.setString(4, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("회원 정보 수정 중 에러");
		}
	}
	
	//회원탈퇴
	  //회원 정보 삭제
	public void delMember(String id) {
		try {
			conn=dataFactory.getConnection();
			String query="delete from memberlist where id=?";
			//System.out.println(query + id);
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("회원탈퇴 중 에러");
		}
	}
	
	//아이디 찾기
		public String findId(String name, String hp) {
			String result=null;
			try {
				conn=dataFactory.getConnection();
				String query="select id from memberlist where name=? and hp=?";
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, name);
				pstmt.setString(2, hp);
				ResultSet rs=pstmt.executeQuery();
				if(rs.next()) {
					result=rs.getString("id");
				}
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				System.out.println("아이디 찾기 중 에러");
			}
			return result;
		}
		
		//비밀번호 찾기
		public String findPw(String id, String name, String hp) {
			String result=null;
			try {
				conn=dataFactory.getConnection();
				String query="select pw from memberlist where id=? and name=? and hp=?";
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, id);
				pstmt.setString(2, name);
				pstmt.setString(3, hp);
				ResultSet rs=pstmt.executeQuery();
				if(rs.next()) {
					result=rs.getString("pw");
				}
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				System.out.println("아이디 찾기 중 에러");
			}
			return result;
		}
	
}
