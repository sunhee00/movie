package qna;

import java.io.IOException;
import model.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QNAUpdateController implements Controller{
   @Override
   public String requestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      String ctx=request.getContextPath();
      
      int qnum=Integer.parseInt(request.getParameter("qnum"));
      String id = request.getParameter("id");
      String qtitle=request.getParameter("qtitle");
      String qcontent=request.getParameter("qcontent");
      
      QNAVO vo=new QNAVO();
      vo.setQnum(qnum);
      vo.setQcontent(qcontent);
      vo.setQtitle(qtitle);
      vo.setId(id);
      
      QNADAO dao=new QNADAO();
      int cnt = dao.updateQNABoard(qnum, qtitle, qcontent);
      
      String nextPage=null;
      if(cnt>0) {
             // 수정성공              
         nextPage="redirect:"+ctx+"/QNAListController.do";
       }else {
             // 수정작업패-> 예외객체를 만들어서  WAS에게 던지자.
             throw new ServletException("not update");          
       }      
      return nextPage;
   }

}