package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.NoticeBean;
import model.NoticeDAO;



@WebServlet("/NoticeCreateProcCon.do")
public class NoticeCreateProcCon extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		NoticeDAO ndao = new NoticeDAO();
		NoticeBean bean;
		RequestDispatcher dis;
		
		
		//세션값 세팅
		String sId = ndao.getSession(request);
		
		
		
		
		
		if(sId.equals("admin")) {
			//게시글 생성 메소드 호출
			bean = ndao.noticeCreate(id, subject, content);
			int count = ndao.getAllCount();
			
			//request 값 세팅
			request.setAttribute("bean", bean);
			request.setAttribute("sId", sId);
			//포워딩
			dis = request.getRequestDispatcher("NoticeInfo.jsp");
			dis.forward(request, response);
		}else {
			dis = request.getRequestDispatcher("error.jsp");
			dis.forward(request, response);
		}
		
	}

}
