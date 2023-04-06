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

@WebServlet("/NoticeUpdateCon.do")
public class NoticeUpdateCon extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doHandle(request, response);
	}
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		NoticeDAO ndao = new NoticeDAO();
		NoticeBean bean;
		RequestDispatcher dis;
		
		
		//세션값 세팅
		String sId = ndao.getSession(request);
		
		
		
		
		
		if(sId.equals("admin")) {
			//수정 할 게시글 정보 받아오기
			bean = ndao.getUpdateBoard(num);
			
			//값 세팅
			request.setAttribute("bean", bean);
			request.setAttribute("sId", sId);
			
			
			//포워드
			dis = request.getRequestDispatcher("NoticeUpdate.jsp");
			dis.forward(request, response);
		}else {
			dis = request.getRequestDispatcher("error.jsp");
			dis.forward(request, response);
		}
		
	}

}
