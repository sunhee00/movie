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


@WebServlet("/NoticeUpdateProcCon.do")
public class NoticeUpdateProcCon extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//수정한 게시글 저장해서 다시 띄워주기
		
		//값 전달받기
		int num = Integer.parseInt(request.getParameter("num"));
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		//저장 메소드 호출
		NoticeDAO ndao = new NoticeDAO();
		NoticeBean bean = ndao.updateBoard(num, subject, content);
		
		//값 세팅
		request.setAttribute("bean", bean);
		
		//포워딩
		RequestDispatcher dis = request.getRequestDispatcher("NoticeInfo.jsp");
		dis.forward(request, response);
	}

}
