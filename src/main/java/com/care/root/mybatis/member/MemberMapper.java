package com.care.root.mybatis.member;

import java.util.ArrayList;
import java.util.Map;

import com.care.root.member.dto.MemberDTO;

public interface MemberMapper {
	public MemberDTO getMember(String id);
	public ArrayList<MemberDTO> memberInfo();
	public int save(MemberDTO dto);
	public void keepLogin(Map<String , Object> map);
	public MemberDTO getUserSessionId(String sessionId);
}
