package qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.QNADAO;

public class QNAReWriteController implements Controller{
   @Override
   public String requestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      int q_ref = Integer.parseInt(request.getParameter("q_ref"));
      int q_re_step = Integer.parseInt(request.getParameter("q_re_step"));
      int q_re_level = Integer.parseInt(request.getParameter("q_re_level"));
      
      QNADAO dao = new QNADAO();
      String sId = dao.getSession(request);
      request.setAttribute("sId", sId);
      
      request.setAttribute("q_ref", q_ref);
      request.setAttribute("q_re_step", q_re_step);
      request.setAttribute("q_re_level", q_re_level);
      
      return "QNABoardReWriteForm";
   } 
}