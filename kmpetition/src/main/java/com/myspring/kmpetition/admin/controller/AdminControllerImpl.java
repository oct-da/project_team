package com.myspring.kmpetition.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.kmpetition.admin.service.AdminService;
import com.myspring.kmpetition.member.vo.MemberVO;

@Controller("adminController")
@RequestMapping("/admin")
public class AdminControllerImpl implements AdminController{
	@Autowired
	private AdminService adminService;
	
//	관리자페이지 중 회원정보조회 페이지
	@Override
	@RequestMapping(value="/memberList")
	public ModelAndView memberList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		Boolean isAdmin=(Boolean) session.getAttribute("isAdmin");
		
		if(isAdmin==true) {
			List<MemberVO> memberList = adminService.memberList();
			mav.addObject("memberList", memberList);
			mav.setViewName("/admin/memberList");
			
//			--------확인용 출력------------
//			for(MemberVO mem : memberList) 
//				System.out.println(mem.getId());
//			----------------------테스트 끝
			
		}else {
			String message="잘못된 요청입니다.";
			mav.addObject("message", message);
			mav.setViewName("/main/main");
		}
		
		return mav;
	}

//	
	@Override
	public ModelAndView addNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity removeNotice(String articleId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView dataPreview(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
