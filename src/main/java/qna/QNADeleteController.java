package qna;

import java.io.IOException;
import model.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QNADeleteController implements Controller{
   @Override
   public String requestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      String ctx=request.getContextPath(); // /MVC04
      
      int qnum=Integer.parseInt(request.getParameter("qnum"));
      
      QNADAO dao=new QNADAO();
      int cnt=dao.deleteQNABoard(qnum);
      String nextPage=null;
      if(cnt>0) {
         nextPage="redirect:"+ctx+"/QNAListController.do";
      }else {
         throw new ServletException("not delete");   
      }      
      return nextPage;
   }

}