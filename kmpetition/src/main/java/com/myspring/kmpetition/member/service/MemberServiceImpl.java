package com.myspring.kmpetition.member.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.kmpetition.member.dao.MemberDAO;
import com.myspring.kmpetition.member.vo.MemberVO;

@Service("memberService")
@Transactional(propagation=Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberVO memberVO;
	@Autowired
	private MemberDAO memberDAO;
	
//	로그인
	@Override
	public MemberVO login(Map loginMap) throws Exception {
		return memberDAO.login(loginMap);
		
	}

//	회원가입
	@Override
	public void addMember(MemberVO memberVO) throws Exception {
		 memberDAO.insertMember(memberVO); 
		
	}

//	ID중복검사(회원가입)
	@Override
	public String overlapped(String id) throws Exception {
		// TODO Auto-generated method stub
		return memberDAO.overlapped(id);
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
