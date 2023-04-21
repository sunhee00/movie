package teamProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberDAO;
import member.MemberVO;


@WebServlet("/movie/*")
public class MovieController extends HttpServlet {
		MovieDAO movieDAO;
		MemberDAO memberDAO;
		public void init(ServletConfig config) throws ServletException { //초기화 시작시 한번만 실행
			movieDAO = new MovieDAO(); 
			memberDAO = new MemberDAO();
		}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String action = request.getPathInfo(); //  URL에 추가되어 있는 경로 정보를 리턴
		System.out.println("액션명: " + action);
		if(action==null || action.equals("/movie.do")) {
			nextPage = "/index.jsp";
			
		}else if (action.equals("/movieChart.do")) {
			nextPage = "/movieChart.jsp";
			
		}else if(action.equals("/remember.do")) {
			List movieList = movieDAO.listMovies();
			request.setAttribute("movieList", movieList);
			nextPage = "/remember.jsp";
			
		}else if(action.equals("/confession.do")) {
			List movieList = movieDAO.listMovies();
			request.setAttribute("movieList", movieList);
			nextPage = "/confession.jsp";
			
		}else if(action.equals("/ticket.do")) {
			List mvInfoList = movieDAO.ListMvInfo();
			request.setAttribute("mvInfoList", mvInfoList);
			nextPage = "/ticket.jsp";
			
		}else if(action.equals("/rememberTicket.do")) {
			List mvInfoList = movieDAO.ListMvInfo();
			request.setAttribute("mvInfoList", mvInfoList);
			nextPage = "/rememberTicket.jsp";
			
		}else if(action.equals("/confessionTicket.do")) {
			List mvInfoList = movieDAO.ListMvInfo();
			request.setAttribute("mvInfoList", mvInfoList);
			nextPage = "/confessionTicket.jsp";
			
		}else if (action.equals("/movieSeat.do")) {
			String mvTitle = request.getParameter("mvTitle");
			String theater = request.getParameter("theater");
			String showingDate = request.getParameter("showingDate");
			String showingTime = request.getParameter("showingTime");
			//MovieInfoVO movieInfoVO = movieDAO.seatCheck(mvTitle, theater, showingDate, showingTime);
			List<MovieInfoVO> movieInfoVO = movieDAO.seatCheck(mvTitle, theater, showingDate, showingTime);
			request.setAttribute("seatInfo", movieInfoVO);
			nextPage="/movieSeat.jsp";
			
		}else if (action.equals("/ticketComp.do")) {
			String mvTitle = request.getParameter("mvTitle");
			String theater = request.getParameter("theater");
			String showingDate = request.getParameter("showingDate");
			String showingTime = request.getParameter("showingTime");
			String seatNum = request.getParameter("seatNum");
			String bookNum = request.getParameter("bookNum");
			String id = request.getParameter("id");
			MovieInfoVO movieInfoVO = new MovieInfoVO();
			movieInfoVO.setMvTitle(mvTitle);
			movieInfoVO.setTheater(theater);
			movieInfoVO.setShowingDate(showingDate);
			movieInfoVO.setShowingTime(showingTime);
			movieInfoVO.setSeatNum(seatNum);
			movieInfoVO.setBookNum(bookNum);
			movieInfoVO.setId(id);
			movieDAO.addTicket(movieInfoVO);
			request.setAttribute("movieInfoVO", movieInfoVO);
			nextPage="/ticketComp.jsp";

		}else if (action.equals("/ticketCheck.do")) {
			String id = request.getParameter("_id");
			MovieInfoVO movieInfoVO = movieDAO.ticketChk(id);
			request.setAttribute("movieInfoVO", movieInfoVO);
			nextPage="/ticketCheck.jsp";
			
		}else if (action.equals("/ticketList.do")) {
			String id = request.getParameter("_id");
			String seatNum = request.getParameter("seatNum");
			List<MovieInfoVO> ticketList = movieDAO.listTicket(id);
			request.setAttribute("ticketList", ticketList);
			nextPage="/ticketList.jsp";

		
		}else if(action.equals("/delTicket.do")){
			String bookNum = request.getParameter("bookNum");
			movieDAO.delTicket(bookNum);
			out.print("<script>" + "alert('예매취소가 완료되었습니다.');" + "</script>" );
			nextPage="/movie/ticketList.do";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
		}
	}
