package qna;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

public class QNAReWritepProController implements Controller{
   @Override
   public String requestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      String ctx=request.getContextPath();
      QNAVO vo = new QNAVO();
      vo.setId(request.getParameter("sId"));
      vo.setQtitle(request.getParameter("qtitle"));
      vo.setQcontent(request.getParameter("qcontent"));
      vo.setQ_ref(Integer.parseInt(request.getParameter("q_ref")));
      vo.setQ_re_step(Integer.parseInt(request.getParameter("q_re_step")));
      vo.setQ_re_level(Integer.parseInt(request.getParameter("q_re_level")));
      
      //데이터 베이스 객체 생성
      QNADAO dao = new QNADAO();
      int cnt = dao.reWriteQNABoard(vo);
      System.out.println("QNA답글 프로콘트롤러에 cnt값:"+cnt);
      String nextPage=null;
      if(cnt>0) {
         nextPage="redirect:"+ctx+"/QNAListController.do";
      }else {
         throw new ServletException("not reWriteQNA");   
      }
      return nextPage;
   }

}