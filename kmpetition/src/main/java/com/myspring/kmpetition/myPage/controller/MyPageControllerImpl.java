package com.myspring.kmpetition.myPage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.kmpetition.main.MainController;
import com.myspring.kmpetition.member.vo.MemberVO;
import com.myspring.kmpetition.myPage.service.MyPageService;

@Controller("MyPageController")
@RequestMapping("/mypage")
public class MyPageControllerImpl extends MainController implements MyPageController{
	
	@Autowired
	private MyPageService myPageService;
//	@Autowired
//	private MyPageVO myPageVO;
	
	
//	회원정보수정 페이지로 이동
//	@Override
//	@RequestMapping(value="/modMemberForm", method = {RequestMethod.POST, RequestMethod.GET})
//	public ModelAndView modMemberForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String viewName=(String)request.getAttribute("viewName");
//		ModelAndView mav = new ModelAndView(viewName);
//		return mav;
//		
//	}

	@Override
	@RequestMapping(value="/modMember", method = RequestMethod.POST)
	public ResponseEntity modMember(@ModelAttribute("memberInfo") MemberVO _memberVO,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		System.out.println("mypage의 modmember 도착");
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			myPageService.modMember(_memberVO);
			
			HttpSession session=request.getSession();
			session.removeAttribute("memberInfo");
			session.setAttribute("memberInfo", _memberVO);
			System.out.println("mypage의 modMember에서 session 업데이트 완료");
			
			
			message = "<script>";
			message += " alert('회원 정보 수정을 완료했습니다.');";
			message += " location.href='" + request.getContextPath() + "/mypage/modMemberForm.do';";
			message += " </script>";

		} catch (Exception e) {
			message = "<script>";
			message += " alert('작업 중 오류가 발생했습니다. 다시 시도해 주세요');";
			message += " location.href='" + request.getContextPath() + "/mypage/modMemberForm.do';";
			message += " </script>";
			e.printStackTrace();
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}

	@Override
	public ModelAndView deleteMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	@RequestMapping(value="/myList.do", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView myList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			HttpSession session=request.getSession();

			MemberVO memberInfo = (MemberVO) session.getAttribute("memberInfo"); 

			String member_id = memberInfo.getId();
					
			String _section = request.getParameter("section");
			String _pageNum = request.getParameter("pageNum");
			int section = Integer.parseInt(((_section == null) ? "1" : _section));
			int pageNum = Integer.parseInt(((_pageNum == null) ? "1" : _pageNum));
			Map pagingMap = new HashMap();
			pagingMap.put("section", section);
			pagingMap.put("pageNum", pageNum);
		
			System.out.println(member_id);
			
			pagingMap.put("member_id", member_id);
			
			String viewName = (String) request.getAttribute("viewName");
			ModelAndView mav = new ModelAndView(viewName);
			
			Map articleMap = myPageService.viewList(pagingMap);
			
			int startNum = (pageNum - 1) * 10 + (section - 1) * 100 + 1;
			articleMap.put("startNum", startNum);
			articleMap.put("section", section);
			articleMap.put("pageNum", pageNum);
			mav.addObject("articleMap", articleMap);
			return mav;
	}

	//글번호에 해당하는 게시글 상세 보기
		@RequestMapping(value = "/myVisitSite.do", method = {RequestMethod.POST, RequestMethod.GET})
		public ModelAndView myVisitSite(HttpServletRequest request, HttpServletResponse response) throws Exception { 

			String viewName = (String) request.getAttribute("viewName");
			HttpSession session = request.getSession();
			MemberVO mem = (MemberVO) session.getAttribute("memberInfo");
			String id = mem.getId();
			
			String _section = request.getParameter("section");
			String _pageNum = request.getParameter("pageNum");
			int section = Integer.parseInt(((_section == null) ? "1" : _section));
			int pageNum = Integer.parseInt(((_pageNum == null) ? "1" : _pageNum));
			Map pagingMap = new HashMap();
			pagingMap.put("section", section);
			pagingMap.put("pageNum", pageNum);
			pagingMap.put("id", id);
			
			ModelAndView mav = new ModelAndView(viewName);
			Map readContent = myPageService.readContent(pagingMap);
			readContent.put("section", section);
			readContent.put("pageNum", pageNum);
			int startNum=(pageNum-1)*10+(section-1)*100;
			readContent.put("startNum", startNum);
			mav.addObject("readContent", readContent);
			
			
			return mav;
		}
		
	
	
}
