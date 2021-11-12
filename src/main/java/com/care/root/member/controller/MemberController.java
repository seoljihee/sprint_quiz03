package com.care.root.member.controller;
import java.util.Calendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.root.common.session.MemberSessionName;
import com.care.root.member.dto.MemberDTO;
import com.care.root.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController implements MemberSessionName{ //부모가 가지고 있는 기능은 자식이 쓸수 있음.
	@Autowired MemberService ms;
	@GetMapping("login")
	public String login() {
		return "login/Login";
	}
	
	@PostMapping("loginChk")
	public String loginChk(@RequestParam String id,
							@RequestParam String pwd,
							@RequestParam(required = false) String autoLogin,
							RedirectAttributes rs) {
		int result = ms.userCheck(id,pwd);  //로그인 성공 : 0 , 실패 : 1
		System.out.println("autoLogin : " + autoLogin);
		if(result == 0) {
			rs.addAttribute("id",id);
			rs.addAttribute("autoLogin",autoLogin);
			return "redirect:successLogin";  //0이라면 성공! 이곳으로 이동
			//rs를 만든 이유는 successLogin으로 session을 만들기 위함이다.
			//jsp페이지로 갈것이 아니기 때문에 model로 받아줘도 된다.
		}
		return "redirect:login";  //성공하지 못했다면 
	}
	
	@GetMapping("successLogin")  //로그인 성공한 사람들만 오는 곳
	public String successLogin(@RequestParam String id,
								@RequestParam(required = false) String autoLogin,	
								HttpSession session,
								HttpServletResponse response) {
		if(autoLogin != null) {
			int limitTime = 60*60*24*90; //90일 동안 살려둔다.
			Cookie loginCookie = new Cookie("loginCookie",session.getId());  //유일한 값으로 넣어준다.
			loginCookie.setMaxAge(limitTime);
			loginCookie.setPath("/"); //최상위로 두겠다. 어느위치에서든지 쿠키를 사용하겠다는 의미이다.
			response.addCookie(loginCookie);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(new java.util.Date());  //현재의 날짜를 얻어와서
			cal.add(Calendar.MONTH, 3);  //month는 3개월 후로 설정해 주세요.
			
			//sql에 넣어주기 위해 한번 더 변환하기
			java.sql.Date limitDate = new java.sql.Date(cal.getTimeInMillis());
			//service쪽으로 연결해주기
			ms.keepLogin(session.getId(), limitDate, id);
		}
		session.setAttribute(LOGIN, id);
		return "member/successLogin";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session,HttpServletResponse response,
						@CookieValue(value="loginCookie",required = false) Cookie loginCookie) {
		if(session.getAttribute(LOGIN) != null) {
			if(loginCookie != null) {    //사용자가 쿠키값이 있다면 사용자의 쿠키값을 만료시키겠다.
				loginCookie.setMaxAge(0);  //쿠키값을 0으로 지정하여 만료시킨다.
				loginCookie.setPath("/");   //상위경로에 있는 쿠키를 없애줘야 로그아웃이 가능하다.
				response.addCookie(loginCookie);
				ms.keepLogin("nan",
						new java.sql.Date(System.currentTimeMillis()),
						(String)session.getAttribute(LOGIN));
			}
			session.invalidate();
		}
		return "redirect:main";
	}
	
	@GetMapping("main")
	public String main() {
		return "default/main";
	}
	
	@GetMapping("memberInfo")
	public String memberInfo(Model model,HttpSession session) {
		//if(session.getAttribute(LOGIN) != null) {
			ms.memberInfo(model);
			return "member/memberInfo";
			
		//}
		//return "redirect:login";
	}
	
	@GetMapping("info")
	public String info(@RequestParam String id, Model model) {
		ms.getMember(id,model);
		return "member/info";
	}
	@GetMapping("memberShipview")
	public String memberShipview() {
		return "member/memberShipview";
	}
	
	@PostMapping("member_save")
	public String member_save(MemberDTO dto) {
		int result = ms.save(dto);
		if(result == 1) {
			return "redirect:login";
		}
		return "redirect:member_save";
	}
}
