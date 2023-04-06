package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.NoticeDAO;

@WebServlet("/NoticeDeleteCon.do")
public class NoticeDeleteCon extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		NoticeDAO ndao = new NoticeDAO();
		RequestDispatcher dis;
		
		
		//세션값 세팅
		String sId = ndao.getSession(request);
		request.setAttribute("sId", sId);

		
		
		if(sId.equals("admin")) {
			//세션 값이 admin일 경우만 delete 메소드 실행
			ndao.noticeDelete(num);
			
			//포워드
			dis = request.getRequestDispatcher("NoticeListCon.do");
			dis.forward(request, response);
		}else {
			dis = request.getRequestDispatcher("error.jsp");
			dis.forward(request, response);
		}
	}

}
