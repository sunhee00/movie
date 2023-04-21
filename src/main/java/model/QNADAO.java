package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class QNADAO {

   Connection con;
   PreparedStatement pstmt;
   ResultSet rs;
   
   //데이터 베이스에 연결 메소드
   public void getCon() {
      
      try {
         Context initctx= new InitialContext();
         Context envctx = (Context)initctx.lookup("java:comp/env");
         DataSource ds = (DataSource)envctx.lookup("jdbc/oracle");
         con = ds.getConnection();//커넥션 연결 해주는 메소드
      }catch(Exception e) {
         e.printStackTrace();
      }
   }
   
   //전체게시글 갯수 리턴 메소드
   public int getAllQNACount() {
      int count=0;
      getCon();
      
      try {
         String sql = "select count(*) from qnaboard";
         pstmt = con.prepareStatement(sql);
         //쿼리실행 후 결과 리턴
         rs = pstmt.executeQuery();
         if(rs.next()) {
            count = rs.getInt(1);//첫번째 결과값을 가져오라는거 첫번째 결과값=>qnum
         }
         con.close();
      }catch(Exception e) {
         e.printStackTrace();
      }
      return count;
   }
   
   //모든 화면에 보여질 데이터 5개씩 추출해서 리턴하는 메소드
      public Vector<QNAVO> getAllQNABoard(int startRow, int endRow) {
         Vector<QNAVO> v=new Vector<>();
         getCon();
         try {
            String getAllSql = "select * from (select A.* ,Rownum Rnum from (select * from qnaboard order by q_ref desc, q_re_step asc)A)" +
                           "where Rnum >=? and Rnum <= ?";
            pstmt = con.prepareStatement(getAllSql);
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);
            
            rs = pstmt.executeQuery();
            
            //데이터 개수가 몇개인지 모르기때문에 반복문을 이용해서 데이터를 추출
            while(rs.next()) {
               //데이터를 패키징해줌
               QNAVO vo = new QNAVO();
               vo.setQnum(rs.getInt(1));
               vo.setId(rs.getString(2));
               vo.setQtitle(rs.getString(3));
               vo.setQcontent(rs.getString(4));
               vo.setQdate(rs.getDate(5));
               vo.setQ_ref(rs.getInt(6));
               vo.setQ_re_step(rs.getInt(7));
               vo.setQ_re_level(rs.getInt(8));
               vo.setReadcount(rs.getInt(9));
               //패키징한 데이터 arrayList에 저장
               v.add(vo);
            }
            con.close();
         }catch(Exception e) {
            e.printStackTrace();
         }
         return v;
      }
   
   //하나의 게시글 저장 메소드
      public int insertQNABoard(QNAVO vo) {
         getCon();
         int cnt=-1;
         int q_ref=0;
         int q_re_step=1;
         int q_re_level=1;
         
         try {
            String q_refSQL = "select max(q_ref) from qnaboard";
            pstmt = con.prepareStatement(q_refSQL);
            rs = pstmt.executeQuery();
            
            if(rs.next()) {
               q_ref = rs.getInt(1) + 1; //가장 큰 값에 1을 더해주는 것(이유: 이렇게 해야 다음번 q_ref값이 겹치지 않게 들어가기 때문)
            }
            
            //데이터를 삽입하는 쿼리
            String insertSQL = "insert into qnaboard values (qna_seq.nextval,?,?,?,sysdate,?,?,?,0)";
            pstmt = con.prepareStatement(insertSQL);
            pstmt.setString(1, vo.getId());
            pstmt.setString(2, vo.getQtitle());
            pstmt.setString(3, vo.getQcontent());
            pstmt.setInt(4, q_ref);
            pstmt.setInt(5, q_re_step);
            pstmt.setInt(6, q_re_level);
            
            //쿼리 실행
            cnt=pstmt.executeUpdate();
            con.close();
         }catch(Exception e) {
            e.printStackTrace();
         }
         return cnt; // 1 or 0
      }
   
      
      //QNAInfo 하나의 게시글 리턴하는 메소드 (QNAVO 타입으로 리턴)
      public QNAVO getOneQNABoard(int qnum) {
         //리턴타입 선언
         QNAVO vo = new QNAVO();
         getCon();
         
         try {
            //조회수 증가 쿼리 - 하나의 게시글을 읽었다는 조회 수 증가
            String readSQL = "update qnaboard set readcount = readcount + 1 where qnum=?";
            pstmt = con.prepareStatement(readSQL);
            pstmt.setInt(1, qnum);
            pstmt.executeUpdate();
            
            //한 게시글에 대한 정보를 리턴해주는 쿼리 준비
            String SQL = "select * from qnaboard where qnum=?";
            pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, qnum);
            //쿼리 실행 후 결과 리턴
            rs = pstmt.executeQuery();
            
            if(rs.next()) {
               vo.setQnum(rs.getInt(1));
               vo.setId(rs.getString(2));
               vo.setQtitle(rs.getString(3));
               vo.setQcontent(rs.getString(4));
               vo.setQdate(rs.getDate(5));
               vo.setQ_ref(rs.getInt(6));
               vo.setQ_re_step(rs.getInt(7));
               vo.setQ_re_level(rs.getInt(8));
               vo.setReadcount(rs.getInt(9));
            }
            
            con.close();
         }catch(Exception e) {
            e.printStackTrace();
         }
         return vo;
      }
   
      //답변글이 저장되는 메소드
      public int reWriteQNABoard(QNAVO vo) {
         //부모글 그룹과 글레벨, 글스텝을 읽어옴
         int q_ref = vo.getQ_ref();
         int q_re_step = vo.getQ_re_step();
         int q_re_level = vo.getQ_re_level();
         int cnt=-1;
         getCon();
         
         try {
            //부모글보다 큰  re_level의 값을 전부 1씩 증가시켜준다.
            String levelSQL = "update qnaboard set q_re_level=q_re_level+1 where q_ref=? and q_re_level > ?";
            pstmt = con.prepareStatement(levelSQL);
            pstmt.setInt(1, q_ref);
            pstmt.setInt(2, q_re_level);
            
            pstmt.executeUpdate();
            
            //답변글 데이터를 저장
            String reWriteInsertSQL = "insert into qnaboard values(qna_seq.nextval,?,?,?,sysdate,?,?,?,0)";
            pstmt=con.prepareStatement(reWriteInsertSQL);
            pstmt.setString(1, vo.getId());
            pstmt.setString(2, vo.getQtitle());
            pstmt.setString(3, vo.getQcontent());
            pstmt.setInt(4, q_ref);
            pstmt.setInt(5, q_re_step + 1); //답글이기 대문에 부모글 q_re_step에 1을 더해준다.
            pstmt.setInt(6, q_re_level + 1);
            // 쿼리실행해서 cnt에 값 넣어주기.
            cnt = pstmt.executeUpdate();
            con.close();
         }catch(Exception e) {
            e.printStackTrace();
            System.out.println("답변글 저장 중 오류 : "+e);
         }
          return cnt;
      }
   
   // 하나의 게시글 삭제 메소드
      public int deleteQNABoard(int qnum) {
         String deleteSQL = "delete from qnaboard where qnum=?";
         getCon();
         int cnt = -1;
         try {
            pstmt=con.prepareStatement(deleteSQL);
            pstmt.setInt(1, qnum);
            cnt = pstmt.executeUpdate();
            con.close();
         }catch(Exception e) {
            e.printStackTrace();
         }finally {
            dbClose();
         }
         return cnt;
      }
   
      
      //하나의 게시글 수정 메소드
      public int updateQNABoard(int qnum, String qtitle, String qcontent) {
         getCon();
         int cnt=-1;
         try {
            String updateSQL = "update qnaboard set qtitle=?, qcontent=? where qnum=?";
            
            pstmt = con.prepareStatement(updateSQL);
            pstmt.setString(1, qtitle);
            pstmt.setString(2, qcontent);
            pstmt.setInt(3, qnum);
            
            cnt = pstmt.executeUpdate();
            con.close();
         }catch(Exception e) {
            e.printStackTrace();
         }
         return cnt;
      }
      
      //세션값 세팅하는 메소드
      public String getSession(HttpServletRequest request) {
         HttpSession session = request.getSession();
         String sId = (String) session.getAttribute("id"); 
 		System.out.println("getSession에서 가져오는 id=>"+sId);
 		return sId;
      }
      
       // 데이터베이스 연결 끊기
         public void dbClose() {
           try { 
            if(rs!=null) rs.close();
            if(pstmt!=null) pstmt.close();
            if(con!=null) con.close();
           }catch(Exception e) {
              e.printStackTrace();
           }
   
         }
}