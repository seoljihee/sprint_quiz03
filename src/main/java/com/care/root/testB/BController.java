package com.care.root.testB;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.care.root.common.session.MemberSessionName;

@Controller
public class BController implements MemberSessionName{
	@GetMapping("board/list")
	public String testb(HttpSession session) {
		//if(session.getAttribute(LOGIN) != null) {
			return "board/list";
		//}
		//return "redirect:/member/login";
	}
	
	@GetMapping("board/write")
	public String write() {
		return "board/write";
	}
}
