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
	
	
//	이메일 중복검사(회원가입)

	@Override
	public String checkEmail(String email) throws Exception {
		return memberDAO.checkEmail(email);
	}

	
	@Override
	public MemberVO findId(Map<String, String> findMap) throws Exception{
		System.out.println("service의 findid");
		return memberDAO.selectForFindId(findMap);
	}
//	비밀번호 찾기
	@Override
	public MemberVO findPwd(Map<String, String> findMap) throws Exception{
		return memberDAO.selectForFindPwd(findMap);
	}
//	비밀번호 찾기 결과, 비밀번호 변경
	public void modPwd(Map<String, String> modMap) throws Exception{
		memberDAO.updatePwd(modMap);
	}
	
}
