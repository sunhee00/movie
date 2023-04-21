package qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.QNADAO;
import model.QNAVO;


public class QNAInfoController implements Controller{
   @Override
   public String requestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      int qnum= Integer.parseInt(request.getParameter("qnum"));
      //데이터베이스에 접근
      QNADAO dao = new QNADAO();
      //하나의 게시글에 대한 정보를 리턴
      QNAVO vo = dao.getOneQNABoard(qnum);//번호를 기준으로 데이터를 가져오시오
      String sId = dao.getSession(request);
      
      request.setAttribute("sId", sId);
      request.setAttribute("vo", vo);
            
      return "QNABoardInfo";
   }
}