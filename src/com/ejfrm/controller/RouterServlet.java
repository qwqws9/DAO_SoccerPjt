package com.ejfrm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejfrm.dao.SoccerDAO;
import com.ejfrm.vo.SoccerVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class soccerList
 */
@WebServlet("/Router.do")
public class RouterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		SoccerDAO sDao = SoccerDAO.getInstance();
		
		String url = "index.jsp";
		
		if(action.equals("selectAll")) {
			List<SoccerVO> list = sDao.selectAll(); 
			url = "soccer/List.jsp";
			
			request.setAttribute("list", list);
			
		}else if(action.equals("create")) {
			request.setAttribute("message", "구단 생성");
			request.setAttribute("action", "create.do");
			url = "soccer/CreateTeam.jsp";
		}else if(action.equals("update")) {
			int code = Integer.parseInt(request.getParameter("code"));
			SoccerVO sVo = sDao.selectCode(code);
			request.setAttribute("sVo", sVo);
			request.setAttribute("message", "구단 수정");
			request.setAttribute("action", "update.do");
			request.setAttribute("code", code);
			url = "soccer/UpdateTeam.jsp";
		}else if(action.equals("delete")) {
			int code = Integer.parseInt(request.getParameter("code"));
			SoccerVO sVo = sDao.selectCode(code);
			request.setAttribute("sVo", sVo);
			request.setAttribute("action", "delete.do");
			request.setAttribute("code", code);
			request.setAttribute("message", "구단 삭제");
			request.setAttribute("choice", "삭제");
			url = "soccer/DeleteTeam.jsp";
		}else if(action.equals("select")) {
			int code = Integer.parseInt(request.getParameter("code"));
			SoccerVO sVo = sDao.selectCode(code);
			request.setAttribute("sVo", sVo);
			request.setAttribute("action", "delete.do");
			request.setAttribute("code", code);
			request.setAttribute("message", "구단 정보");
			
			url = "soccer/DeleteTeam.jsp";
		}
		RequestDispatcher disp = request.getRequestDispatcher(url);
		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		ServletContext context = getServletContext();
		String path = context.getRealPath("upload");
		String encType = "utf-8";
		int sizeLimit = 5 * 1024 * 1024;
		MultipartRequest multi = new MultipartRequest(request, path, sizeLimit, encType, new DefaultFileRenamePolicy());
		request.setCharacterEncoding("utf-8");
		String action = multi.getParameter("action");
		System.out.println(action);
		
		if(action.equals("create.do")) {
			
			SoccerVO sVo = new SoccerVO();
			sVo.setTeamname(multi.getParameter("teamname"));
			sVo.setCountry(multi.getParameter("country"));
			sVo.setHomeground(multi.getParameter("homeground"));
			sVo.setCoach(multi.getParameter("coach"));
			sVo.setPlayers(multi.getParameter("players"));
			sVo.setPicture(multi.getFilesystemName("picture"));
			
			SoccerDAO sDao = SoccerDAO.getInstance();
			sDao.insertTeam(sVo);
			
			
			
			
		}else if(action.equals("update.do")) {
			SoccerVO sVo = new SoccerVO();
			sVo.setTeamname(multi.getParameter("teamname"));
			sVo.setCountry(multi.getParameter("country"));
			sVo.setHomeground(multi.getParameter("homeground"));
			sVo.setCoach(multi.getParameter("coach"));
			sVo.setPlayers(multi.getParameter("players"));
			sVo.setPicture(multi.getFilesystemName("picture"));
			sVo.setCode(Integer.parseInt(multi.getParameter("code")));
			
			SoccerDAO sDao = SoccerDAO.getInstance();
			sDao.updateTeam(sVo);
			
		}else if(action.equals("delete.do")) {
			int code = Integer.parseInt(multi.getParameter("code"));
			SoccerDAO sDao = SoccerDAO.getInstance();
			sDao.DeleteTeam(code);
		}
		response.sendRedirect("Router.do?action=selectAll");
	}

}
