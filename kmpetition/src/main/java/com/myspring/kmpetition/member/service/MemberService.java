package com.myspring.kmpetition.member.service;

import java.util.Map;

import com.myspring.kmpetition.member.vo.MemberVO;

public interface MemberService {
//	로그인
	public MemberVO login(Map  loginMap) throws Exception;
	
//	회원가입
	public String addMember(MemberVO memberVO) throws Exception;
	
//	ID중복검사(회원가입)
	public String overlapped(String id) throws Exception;
	
//	회원탈퇴
	public String deleteMember(String id) throws Exception;
	
//	회원수정
	public void modMember(MemberVO memberVO) throws Exception;
	
	
}
