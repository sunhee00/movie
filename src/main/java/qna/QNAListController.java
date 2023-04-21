package qna;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.QNADAO;
import model.QNAVO;

public class QNAListController implements Controller{
   @Override
   public String requestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
	   request.setCharacterEncoding("utf-8");
      //화면에 보여질 게시글의 개수를 지정 
      int pageSize=5;
      
      //현재 보여지고 있는 페이지의 넘버 값을 읽어드림 
      String pageNum = request.getParameter("pageNum");
      
      //null 처리
      if(pageNum==null){
         pageNum="1";
      }
      
      //전체 게시글의 갯수 
      int count;
      
      //jsp페이지 내에서 보여질 넘버링 숫자값을 저장하는 변수
      int number;
      
      //현재 보여지고 있는 페이지 문자를 숫자로 변환 
      int currentPage =Integer.parseInt(pageNum);
      
      //전체 게시글의 갯수를 가져와야 하기에 데이터 베이스 객체 생성
      // POJO가 해야할 일의 범위
         // 1. Model 연동
      QNADAO dao = new QNADAO();
      count =dao.getAllQNACount();
      
      //현재 보여질 페이지 시작 번호를 설정 
      int startRow = (currentPage-1)*pageSize+1;
      int endRow =currentPage * pageSize;
      
      //최신글 10개를 기준으로 게시글 리턴 받아주는 메소드 호출 
      Vector<QNAVO> vo = dao.getAllQNABoard(startRow, endRow);
      
      //게시글리스트번호
      number = count - (currentPage -1)*pageSize;
         
      //페이징
            //페이지개수
            frontcontroller.Paging paging = new frontcontroller.Paging();
            paging.getAllPage(count, pageSize);
            int pageCount = paging.getPageCount(); 
            //표기될 페이지 개수
            int page = paging.getPage();
            
            //페이지 첫and마지막 게시글
            paging.getPageBlock(currentPage);
            int blockStart = paging.getBlockStart();
            int blockEnd = paging.getBlockEnd();
                 
            //BoardList.jsp쪽으로 request객체에 담아서 넘겨줌   // 2. 객체바인딩
      //request.setAttribute("sId", sId);
      request.setAttribute("vo",vo );
      request.setAttribute("number",number);
      request.setAttribute("pageSize", pageSize);
      request.setAttribute("count", count);
      request.setAttribute("currentPage", currentPage);
      request.setAttribute("page", page);
      request.setAttribute("pageCount", pageCount);
      request.setAttribute("blockStart", blockStart);
      request.setAttribute("blockEnd", blockEnd);
      // 3.다음페이지정보(View)
      return "QNABoardList";
   }
      
}