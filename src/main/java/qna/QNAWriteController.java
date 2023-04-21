package qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.QNADAO;
import model.QNAVO;

public class QNAWriteController implements Controller{
   @Override
   public String requestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      String ctx=request.getContextPath();
            
      //아이디
      QNADAO dao = new QNADAO();
      String sId = dao.getSession(request);
      request.setAttribute("sId", sId);
      return "QNABoardWriteForm";
   }

}
