package com.myspring.teamprj.member.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.myspring.teamprj.member.vo.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService{

	@Override
	public MemberVO login(Map loginMap) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addMember(MemberVO memberVO) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String overlapped(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteMember(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modMember(MemberVO memberVO) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
}
