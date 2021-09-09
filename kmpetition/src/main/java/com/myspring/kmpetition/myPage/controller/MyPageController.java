package com.myspring.kmpetition.myPage.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.kmpetition.member.vo.MemberVO;

public interface MyPageController {
//	public ModelAndView modMemberForm(HttpServletRequest request, HttpServletResponse response) throws Exception ;
	public ResponseEntity modMember(@ModelAttribute("memberInfo") MemberVO _memberVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception;
	
	public ModelAndView deleteMember(HttpServletRequest request, HttpServletResponse response) throws Exception ;
	
	public ModelAndView myQna(HttpServletRequest request, HttpServletResponse response) throws Exception ;
	
	
}
