package com.myspring.kmpetition.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.kmpetition.member.service.MemberService;
import com.myspring.kmpetition.member.vo.MemberVO;

@Controller("memberController")
@RequestMapping(value="/member")
public class MemberControllerImpl implements MemberController{
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberVO memberVO;
	
	@Override
	@RequestMapping(value="/login.do")
	public ModelAndView login(Map<String, String> loginMap, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav=new ModelAndView();
		
//		아직 서비스 미구현
//		memberVO=memberService.login(loginMap);
		
//		서비스 대신 임시코드
		String id=loginMap.get("id");
		String pwd=loginMap.get("pwd");
		memberVO.setId(id);
		memberVO.setPwd(pwd);
		//로그인한 정보가 있고 id가 null이 아니면
		if(memberVO!=null&&memberVO.getId()!=null) {
			HttpSession session=request.getSession();
			session.setAttribute("isLogOn", true);
			session.setAttribute("memberInfo", memberVO);	
			mav.setViewName("redirect:/main/main.do");
		//로그인 정보가 없거나 id가 null이면(로그인 정보로 DB에서 조회가 안 되면)
		}else {
			String message="아이디나 비밀번호가 틀립니다. 다시 로그인해주세요.";
			mav.addObject("message", message);
			mav.setViewName("/member/loginForm");
		}
		return mav;
	}

	@Override
	@RequestMapping(value="/logout.do")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RequestMapping(value="/addMember.do")
	public ResponseEntity addMember(MemberVO member, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity overlapped(String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity removeMember(String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity modMember(MemberVO member, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
