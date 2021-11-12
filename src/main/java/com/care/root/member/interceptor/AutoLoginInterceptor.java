package com.care.root.member.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.care.root.common.session.MemberSessionName;
import com.care.root.member.dto.MemberDTO;
import com.care.root.member.service.MemberService;

public class AutoLoginInterceptor extends HandlerInterceptorAdapter
								implements MemberSessionName{
	@Autowired MemberService ms;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			System.out.println("auto login execute");
			//오버라이딩을 하니까 메개변수를 쓸수 없다
			/*
			for(Cookie c : request.getCookies()) {
				if(c.getName().equals("loginCookie")) {
					
				}
			}
			*/
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			if(loginCookie != null) {
				MemberDTO dto = ms.getUserSessionId(loginCookie.getValue());
				if(dto != null) {
					/*
					HttpSession session = request.getSession();
					session.setAttribute(LOGIN, dto.getId());
					*/
					request.getSession().setAttribute(LOGIN, dto.getId());
				}
			}
		return true;
	}
	
	

}
