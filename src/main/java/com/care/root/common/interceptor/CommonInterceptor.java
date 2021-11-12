package com.care.root.common.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.care.root.common.session.MemberSessionName;

public class CommonInterceptor extends HandlerInterceptorAdapter
								implements MemberSessionName{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("컨트롤러 실행 전전전");
		
		HttpSession session = request.getSession();
		if(session.getAttribute(LOGIN)==null) {
			response.sendRedirect("/member/login");
		//	response.setContentType("text/html; charset=utf-8");
		//	PrintWriter out = response.getWriter();
		//	out.print("<script>alert('로그인 해라');"
		//			+ "location.href='/member/login';</script>");
			//+ "location.href='"+ request.getContextPath() +"/member/login';</script>");
			//request.getContextPath() 절대경로 root값을 가지고 오는 메소드이다.
			return false;
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("컨트롤러 실행 후후후");
	}

}
