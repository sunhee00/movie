package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.NoticeDAO;

@WebServlet("/NoticeCreateCon.do")
public class NoticeCreateCon extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		NoticeDAO ndao = new NoticeDAO();
		RequestDispatcher dis;
		
		
		//세션값 세팅
		String sId = ndao.getSession(request);
		
		if(sId.equals("admin")) {
			request.setAttribute("sId", sId);
			//포워딩
			dis = request.getRequestDispatcher("NoticeCreate.jsp");
			dis.forward(request, response);
		}
		
	}

}
