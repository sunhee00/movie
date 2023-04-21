package qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.QNADAO;
import model.QNAVO;

public class QNAUpdateProController  implements Controller {
   @Override
   public String requestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      int qnum = Integer.parseInt(request.getParameter("qnum"));
      
      //데이버테이스에서 하나의 게시글에 대한 정보를 리턴하는 메소드 호출
      QNADAO dao = new QNADAO();
      QNAVO vo = dao.getOneQNABoard(qnum);
      
      request.setAttribute("vo", vo);
      
      return "QNABoardUpdateForm";
   }
}