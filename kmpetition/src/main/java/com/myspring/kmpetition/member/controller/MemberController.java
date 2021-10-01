package com.myspring.kmpetition.member.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.kmpetition.member.vo.HistoryVO;
import com.myspring.kmpetition.member.vo.MemberVO;

public interface MemberController {
//	로그인
	public ModelAndView login(@RequestParam Map<String, String> loginMap,HttpServletRequest request, HttpServletResponse response) throws Exception;
//	휴면계정 활성화
	public ResponseEntity awakeMember(@ModelAttribute("memberVO") MemberVO memberVO,
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
	
	
	public int checkLoginDate(Date loginDate);
	public void checkSaveId(String saveId, Map loginMap, HttpServletResponse response) throws Exception;
	
	public ResponseEntity removeMember(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
//	내가 열람한 페이지를 보여주기 위해 DB에 저장 목적
	public @ResponseBody String saveVisit(@RequestParam Map visitMap, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
}
