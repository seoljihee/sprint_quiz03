package com.care.root.member.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.root.member.dto.MemberDTO;
import com.care.root.mybatis.member.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired MemberMapper mapper;
	BCryptPasswordEncoder encoder;
		
	public MemberServiceImpl() {
		encoder =  new BCryptPasswordEncoder();
	}
	@Override
	public int userCheck(String id, String pwd) {
		
		MemberDTO dto = mapper.getMember(id);
		System.out.println(id + pwd);
		System.out.println(dto.getId() + dto.getPwd());
		if(dto != null) {  //해당하는 아이디가 있으니까 비밀번호를 비교하겠다.
			if(encoder.matches(pwd, dto.getPwd()) || pwd.equals(dto.getPwd())) {
				return 0; //로그인 성공
			}
		}
		return 1;  //로그인 실패
	}
	@Override
	public void memberInfo(Model model) {
		model.addAttribute("memberList",mapper.memberInfo());
	}
	@Override
	public void getMember(String id, Model model) {
		model.addAttribute("info",mapper.getMember(id));
	}
	@Override
	public int save(MemberDTO dto) {
		System.out.println("변경 전 : " + dto.getPwd());
		String securePwd = encoder.encode(dto.getPwd());
		System.out.println("변경 후 : " + securePwd);
		dto.setPwd(securePwd);
		int result = 0;
		try {
			
			result = mapper.save(dto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public void keepLogin(String sessionId, Date limitDate, String id) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("sessionId", sessionId);
		map.put("limitDate", limitDate);
		map.put("id", id);
		mapper.keepLogin(map);
	}
	@Override
	public MemberDTO getUserSessionId(String sessionId) {
		return mapper.getUserSessionId(sessionId);
	}

}
