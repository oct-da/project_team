package com.myspring.kmpetition.member.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspring.kmpetition.member.dao.MemberDAO;
import com.myspring.kmpetition.member.vo.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberVO memberVO;
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public MemberVO login(Map loginMap) throws Exception {
		return memberDAO.login(loginMap);
		
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
