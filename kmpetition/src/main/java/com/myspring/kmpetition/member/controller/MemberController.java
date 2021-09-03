package com.myspring.kmpetition.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.kmpetition.member.vo.MemberVO;

public interface MemberController {
//	로그인
	public ModelAndView login(@RequestParam Map<String, String> loginMap,HttpServletRequest request, HttpServletResponse response) throws Exception;
	
//	로그아웃
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
//	회원가입
	public ResponseEntity  addMember(@ModelAttribute("member") MemberVO member,
            HttpServletRequest request, HttpServletResponse response) throws Exception;
	
//	ID 중복 검사(회원가입)
	public ResponseEntity overlapped(@RequestParam("id") String id,HttpServletRequest request, HttpServletResponse response) throws Exception;
	
//	회원탈퇴
	public ResponseEntity removeMember(@RequestParam("id") String id,HttpServletRequest request, HttpServletResponse response) throws Exception;
	
//	회원수정
	public ResponseEntity modMember(@ModelAttribute("member") MemberVO member,
            HttpServletRequest request, HttpServletResponse response) throws Exception;
//	ㅠㅠ 
//	브랜치를 tester를 생성하여 그것으로 변경함 
//	pushshsh
	
}
