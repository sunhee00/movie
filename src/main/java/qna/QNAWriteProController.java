package qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.QNADAO;
import model.QNAVO;

public class QNAWriteProController implements Controller{
   @Override
   public String requestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      String ctx=request.getContextPath();
      QNAVO vo = new QNAVO();
      vo.setId(request.getParameter("sId"));
      vo.setQtitle(request.getParameter("qtitle"));
      vo.setQcontent(request.getParameter("qcontent"));

      QNADAO dao = new QNADAO();
      int cnt=dao.insertQNABoard(vo);
      String nextPage=null;
      if(cnt>0) {
             // 작성성공              
         nextPage="redirect:"+ctx+"/QNAListController.do";
       }else {
             // 작성실패-> 예외객체를 만들어서  WAS에게 던지자.
             throw new ServletException("not write");          
       }      
      return nextPage;
   }

}
