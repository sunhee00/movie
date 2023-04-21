package notice;

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
		request.setCharacterEncoding("utf-8");
		NoticeDAO ndao = new NoticeDAO();
		RequestDispatcher dis;
		System.out.println("noticeCreateCon.java 실행");
		
		//세션값 세팅
		String sId = ndao.getSession(request);
		System.out.println("noticeCreateCon do에서 받는 ID값="+sId);
		if(sId.equals("admin")) {
			request.setAttribute("sId", sId);
			//포워딩
			dis = request.getRequestDispatcher("NoticeCreate.jsp");
			dis.forward(request, response);
		}
		
	}

}
