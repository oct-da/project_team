package com.myspring.kmpetition.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.kmpetition.admin.service.AdminService;
import com.myspring.kmpetition.board.vo.NoticeVO;
import com.myspring.kmpetition.main.MainController;

@Controller("adminController")
@RequestMapping(value="/admin")
public class AdminControllerImpl extends MainController implements AdminController {
	@Autowired
	private AdminService adminService;

//	관리자페이지 중 회원정보조회 페이지
	@Override
	@RequestMapping(value = "/memberList.do")
	public ModelAndView memberList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");

		if (isAdmin == true) {
//			--------------------------페이징 전 코드-------------------
//			List<MemberVO> memberList = adminService.memberList();
//			mav.addObject("memberList", memberList);
//			mav.setViewName("/admin/memberList");
//			-----------------------------------------------코드 끝
			
			String viewName= (String) request.getAttribute("viewName");
			mav.setViewName(viewName);

			try {

				String _section = request.getParameter("section");
				String _pageNum = request.getParameter("pageNum");
				int section = Integer.parseInt(((_section == null) ? "1" : _section));
				int pageNum = Integer.parseInt(((_pageNum == null) ? "1" : _pageNum));
				Map<String, Integer> pagingMap = new HashMap<String, Integer>();
				pagingMap.put("section", section);
				pagingMap.put("pageNum", pageNum);

//				memberMap에는 memberList와 totMember가 들어 있음.
				Map memberMap = adminService.memberList(pagingMap);
				int startNum=(pageNum-1)*10+(section-1)*100+1;
				memberMap.put("startNum", startNum);
				memberMap.put("section", section);
				memberMap.put("pageNum", pageNum);
				mav.addObject("memberMap", memberMap);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String message = "잘못된 요청입니다.";
			mav.addObject("message", message);
			mav.setViewName("/main/main");
		}
		return mav;
	}

	@Override
	@RequestMapping(value = "/addNotice.do", method = RequestMethod.POST)
	public ModelAndView addNotice(@ModelAttribute("noticeVO") NoticeVO noticeVO, MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		
		if((boolean)session.getAttribute("isAdmin")==true) {
			try{
				adminService.addNotice(noticeVO);
			}catch (Exception e) {
				e.printStackTrace();
				String message="잘못된 요청입니다.";
				mav.addObject("errMsg",message);
				mav.setViewName("/board/noticeForm");
			}
			
		}else {
			String message="잘못된 요청입니다.";
			mav.addObject("errMsg",message);
			mav.setViewName("/board/noticeList");
		}
		
		
		
		return mav;
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
