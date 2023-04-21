package notice;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.NoticeBean;
import model.NoticeDAO;
import model.Paging;

@WebServlet("/NoticeListCon.do")
public class NoticeListCon extends HttpServlet {
	
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한 페이지당 최대 게시글 수
				int pageSize = 5;
				//전체 게시글 수
				int count;
				//번호 표기
				int number;
				//현재 페이지 번호
				String pageNum = request.getParameter("pageNum");
				//현재 페이지 번호 null처리
				if( pageNum == null) {
					pageNum = "1";
				}
				//현재 페이지 번호 int로 변환
				int curPage = Integer.parseInt(pageNum);
				
				//전체 게시글 수
				NoticeDAO ndao = new NoticeDAO();
				count = ndao.getAllCount();
				
				//페이지 게시글 첫 번호
				int startNum = (curPage-1) * pageSize + 1;
				//페이지 게시글 마지막 번호
				int endNum = curPage * pageSize;
				//게시글 리스트 번호
				number = count - (curPage-1) * pageSize;
				
				//게시글 목록 불러오기
				Vector<NoticeBean> v = ndao.getAllBoard(startNum, endNum);
				
				
				//////////페이징
				//페이지개수
				Paging paging = new Paging();
				paging.getAllPage(count, pageSize);
				int pageCount = paging.getPageCount(); 
				//표기될 페이지 개수
				int page = paging.getPage();
				
				//페이지 첫and마지막 게시글
				paging.getPageBlock(curPage);
				int blockStart = paging.getBlockStart();
				int blockEnd = paging.getBlockEnd();
				
				
				/////////////////////jsp에 값 넘겨주기
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("count", count);
				request.setAttribute("curPage", curPage);
				request.setAttribute("number", number);
				request.setAttribute("v", v);
				
				request.setAttribute("page", page);
				request.setAttribute("pageCount", pageCount);
				request.setAttribute("blockStart", blockStart);
				request.setAttribute("blockEnd", blockEnd);
				

				//세션값 세팅
				String sId = ndao.getSession(request);
				
				System.out.println("noticelistCon에서 확인하는 ID값=>"+sId);
//				request.setAttribute("sId", sId);
	            if(sId != null) {
	               request.setAttribute("sId", sId);
	            }
				
				RequestDispatcher dis = request.getRequestDispatcher("/NoticeList.jsp");
				dis.forward(request, response);
	}

}
