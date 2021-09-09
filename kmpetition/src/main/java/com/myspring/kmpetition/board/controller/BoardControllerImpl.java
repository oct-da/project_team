package com.myspring.kmpetition.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.kmpetition.board.service.BoardService;
import com.myspring.kmpetition.board.vo.NoticeVO;
import com.myspring.kmpetition.main.MainController;

@Controller("boardController")
@RequestMapping(value = "/board")
public class BoardControllerImpl extends MainController implements BoardController{
	@Autowired
	private BoardService boardService;

	@Override
	@RequestMapping(value = "/noticeList", method = RequestMethod.GET)
	public ModelAndView noticeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String) request.getAttribute("viewName");
		System.out.println(viewName);
		ModelAndView mav = new ModelAndView(viewName);

		String _section = request.getParameter("section");
		String _pageNum = request.getParameter("pageNum");
		int section = Integer.parseInt(((_section == null) ? "1" : _section));
		int pageNum = Integer.parseInt(((_pageNum == null) ? "1" : _pageNum));
		Map<String, Integer> pagingMap = new HashMap<String, Integer>();
		pagingMap.put("section", section);
		pagingMap.put("pageNum", pageNum);
		
		Map noticeMap = boardService.noticeList(pagingMap);
		
		System.out.println("noticeList가 null인가?");
		System.out.println(noticeMap.get("noticeList")==null);
		
		int startNum=(pageNum-1)*10+(section-1)*100+1;
		noticeMap.put("startNum", startNum);
		noticeMap.put("section", section);
		noticeMap.put("pageNum", pageNum);
		mav.addObject("noticeMap", noticeMap);
//		최종적으로 mav에 바인딩된 정보
//		startNum : 게시물 시작번호(뷰에서 시작번호~번호+10 까지 넘버링)
//		sescion : 섹션번호(1~)
//		pageNum : 페이지번호(1~10)
//		noticeMap : 해당 섹션, 페이지에 해당하는 noticeVO (최대 10개)
//		totNotice : 총 게시물 개수
		return mav;
	}

	@Override
	public ModelAndView noticeDetail(int articleNO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView boardList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView boardDetail(int articleNO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView addBoard(Map articleMap, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView modBoard(NoticeVO noticeVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity removeBoard(int articleNO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
