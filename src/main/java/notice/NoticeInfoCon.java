package notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.NoticeBean;
import model.NoticeDAO;

@WebServlet("/NoticeInfoCon.do")
public class NoticeInfoCon extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시물num 값을 받기
		int num = Integer.parseInt(request.getParameter("num"));
		
		//db접근
		NoticeDAO ndao = new NoticeDAO();
		
		//세션값 세팅
		String sId = ndao.getSession(request);
		
		
		//선택한 게시글 불러오기(num과 일치하는)
		NoticeBean bean = ndao.getOneBoard(num,sId);//sId가 admin이 아닐경우 조회수 증가
		
		//게시글 정보 보내기
		request.setAttribute("bean", bean);
		request.setAttribute("sId", sId);
		
		
		//포워딩
		RequestDispatcher dis = request.getRequestDispatcher("NoticeInfo.jsp");
		dis.forward(request, response);
	}


}
