package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.MemberJoinAction;
import action.MemberListAction;
import action.MemberLoginAction;
import action.MemberViewAction;
import action.memberDeleteAction;
import vo.ActionForward;

/**
 * Servlet implementation class MemberFrontController
 */
@WebServlet("*.me")
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	protected void doProcess(HttpServletRequest request,HttpServletResponse response) 
	throws ServletException, IOException {
		
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action= null;
		
		
		// 로그인창 이동
		if (command.equals("/memberLogin.me")) {
			forward = new ActionForward();
			forward.setRedirect(true);   //리다이렉트로 이동시키기
			forward.setPath("./loginForm.jsp");  //jsp로 이동시키기 (path)
		
		// 회원가입 창 이동
		} else if(command.equals("/memberjoin.me")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./jorinForm.jsp");
			
		
		// 로그인 액션 창 실행	
		} else if (command.equals("/memberLoginAction.me")) {
			action = new MemberLoginAction();  // 로그인 액션 객체 생성
			try {
				forward = action.execute(request,response);
			} catch (Exception e) {
				// TODO: handle exception\
				e.printStackTrace();
			}
			
		// 가입 액션창 실행
		} else if(command.equals("/memberJoinAction.me")) {
			action = new MemberJoinAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
		// 회원 목록 액션창 실행
		} else if(command.equals("/memberListAction.me")) {
			action = new MemberListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
		// 회원 정보 상세보기 액션 실행
		} else if (command.equals("/memberViewAction.me")) {
			action = new MemberViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
			
		// 멤버정보 삭제액션 실행	
		} else if (command.equals("/memberDeleteAction.me")) {
			action = new memberDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = 
						request.getRequestDispatcher(forward.getPath());
			}
			
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		doProcess(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		doProcess(request,response);
	}

}
