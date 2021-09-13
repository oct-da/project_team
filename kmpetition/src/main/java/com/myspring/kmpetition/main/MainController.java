package com.myspring.kmpetition.main;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


// 컨텍스트만으로 접속 시 메인으로 보내주는 컨트롤러. 
// 모든 컨트롤러에 상속되어 viewForm의 기능을 더해주는 컨트롤러.

@Controller("mainController")
//@EnableAspectJAutoProxy  넣어야하나?
//@RequestMapping(value = "/main")
public class MainController {
	private final String Path = "C:\\test";
	
	@RequestMapping(value = "/main/main.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Main컨의 main");
		HttpSession session;
		ModelAndView mav = new ModelAndView();
		String viewName = "/main/main";
		mav.setViewName(viewName);
		return mav;
	}
	

//	contextPath만 입력하고 접속했을 때 자동으로 메인 화면으로 리다이렉트 하기 위한 메서드
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void home(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Main컨의 home");
		response.setContentType("text/html; charset=utf-8");
		response.sendRedirect("main/main.do");
		
	}
	
	@RequestMapping(value={"/*.do"}, method = {RequestMethod.POST, RequestMethod.GET})
	protected ModelAndView viewForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Main컨의 viewForm - /*.do");
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav=new ModelAndView(viewName);
		return mav;
	}
	
	

	
	
}