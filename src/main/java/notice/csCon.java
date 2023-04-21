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
import model.QNADAO;
import model.QNAVO;


@WebServlet("/csCon.do")
public class csCon extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeDAO ndao = new NoticeDAO();
	      Vector<NoticeBean> v = ndao.getAllBoard(1, 5);
	      
	      
	      
	      int size = v.size();
	      
	      String[] sub = new String[size];
	      for(int i=0; i<size; i++) {
	         sub[i] = v.get(i).getSubject();
	         if(sub[i].length() >= 20) {
	            sub[i] = sub[i].substring(0,20);
	            v.get(i).setSubject(sub[i] + "..");
	         }
	      }
	      
	      QNADAO qdao = new QNADAO();
	      Vector<QNAVO> qv = qdao.getAllQNABoard(1,5);
	      
	      int qsize = qv.size();
	      String[] title = new String[qsize];
	      for(int i=0; i<qsize; i++) {
	         title[i] = qv.get(i).getQtitle();
	         if(title[i].length() >= 20) {
	            title[i] = title[i].substring(0,20);
	            qv.get(i).setQtitle(title[i] + "..");
	         }
	      }
	      request.setAttribute("qv", qv);
	      
	      request.setAttribute("v", v);
	      
	      
	      RequestDispatcher dis = request.getRequestDispatcher("cs.jsp");
	      dis.forward(request, response);
	}

}
