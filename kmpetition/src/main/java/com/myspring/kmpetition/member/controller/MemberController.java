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
//	휴면계정 활성화
	public ResponseEntity awakeMember(@RequestParam Map<String, String> memberMap,
	          HttpServletRequest request, HttpServletResponse response) throws Exception;
	
//	로그아웃
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
////	회원가입
//	public ResponseEntity  addMember(@ModelAttribute("member") MemberVO member,
//            HttpServletRequest request, HttpServletResponse response) throws Exception;
	
//	회원가입에서 이메일드롭박스 사용하기
	public ResponseEntity  addMember(@RequestParam Map<String, String> memberMap,
          HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
//	ID 중복 검사(회원가입)
	public ResponseEntity overlapped(@RequestParam("id") String id,HttpServletRequest request, HttpServletResponse response) throws Exception;
//	이메일 중복검사(회원가입)
	public ResponseEntity checkEmail(@RequestParam String email, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
