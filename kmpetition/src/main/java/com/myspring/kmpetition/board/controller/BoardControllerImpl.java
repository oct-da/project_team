package com.myspring.kmpetition.board.controller;

import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.kmpetition.board.service.BoardService;
import com.myspring.kmpetition.board.vo.BoardVO;
import com.myspring.kmpetition.board.vo.NoticeVO;
import com.myspring.kmpetition.board.vo.UploadVO;
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
		System.out.println(section+", "+pageNum);
		Map<String, Integer> pagingMap = new HashMap<String, Integer>();
		pagingMap.put("section", section);
		pagingMap.put("pageNum", pageNum);
		
		Map noticeMap = boardService.noticeList(pagingMap);
		
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
		
		HttpSession session=request.getSession();
		System.out.println("admin인가?");
		System.out.println(session.getAttribute("isAdmin"));
		return mav;
	}

	@Override
	@RequestMapping(value = "/noticeDetail", method = RequestMethod.GET)
	public ModelAndView noticeDetail(@RequestParam("articleNO") int articleNO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName=(String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		NoticeVO noticeVO = boardService.noticeDetail(articleNO);
		mav.addObject("noticeVO", noticeVO);
		return mav;
	}

	
	
	
	@Override
	@RequestMapping(value="/boardList.do", method = RequestMethod.GET)
	public ModelAndView boardList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName= (String) request.getAttribute("viewName");
		ModelAndView mav =new ModelAndView(viewName);
		HttpSession session = request.getSession();
		
		try {
			String _section = request.getParameter("section");
			String _pageNum = request.getParameter("pageNum");
			int section = Integer.parseInt(((_section == null) ? "1" : _section));
			int pageNum = Integer.parseInt(((_pageNum == null) ? "1" : _pageNum));
			Map<String, Integer> pagingMap = new HashMap<String, Integer>();
			pagingMap.put("section", section);
			pagingMap.put("pageNum", pageNum);

//			articleMap에는 articleList와 totArticle가 들어 있음.
			Map articleMap=boardService.articleList(pagingMap);
			int startNum=(pageNum-1)*10+(section-1)*100+1;
			articleMap.put("startNum", startNum);
			articleMap.put("section", section);
			articleMap.put("pageNum", pageNum);
			mav.addObject("articleMap", articleMap);
			System.out.println("boardList 수행 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@Override
	@RequestMapping(value="/boardDetail.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView boardDetail(@RequestParam("articleNO") int articleNO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav=new ModelAndView();
		BoardVO boardVO=boardService.articleDetail(articleNO);
		System.out.println("글 공개여부 : "+boardVO.isVisible());
		if (boardVO.isVisible()!=true) {
			String errMsg="비공개 게시글";
			mav.addObject(errMsg);
			mav.setViewName("redirect:/board/boardList.do");
			System.out.println("비공개 게시글입니다.");
		}else {
			mav.addObject("boardVO", boardVO);
			mav.setViewName(viewName);
			System.out.println("선택한 글제목 : "+boardVO.getTitle());
		}
		return mav;
	}

	@Override
	@RequestMapping(value="/addBoard.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView addBoard(@ModelAttribute("article") BoardVO article, MultipartHttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		int articleNO = boardService.createArticleNO()+1;
		HttpSession session = request.getSession();
		System.out.println("공개글인가? : " +article.isVisible());
		System.out.println("작성아이디 : "+article.getId());
		
//		articleNO는 insert할 때 자동증가니까 생략
//		createdate는 매퍼에서 crudate()로 들어가니까 생략.
		
		// 파일 업로드 이후 DB에 등록할 목록 생성. MainController에 있는 메서드 활용.
//		add로 첫생성하는 레코드이므로 nameList는 null로 보내기. 
		List<UploadVO> uploadList = uploadFile(articleNO, null, request);
		
		// 데이터 베이스에 문의글/첨부파일 정보 입력하기
		Map articleMap = new HashMap();
		articleMap.put("article", article);
		articleMap.put("upload", uploadList);
		
		try {
			boardService.addArticle(articleMap);
			
			String msg="게시글 등록 완료";
			System.out.println(msg);
			mav.addObject("msg", msg);
			mav.setViewName("redirect:/board/boardDetail.do?articleNO="+articleNO);
		}catch(Exception e) {
			e.printStackTrace();
			String errMsg="게시글 등록 중 오류 발생";
			System.out.println(errMsg);
			mav.addObject("errMsg", errMsg);
			mav.setViewName("redirect:/board/boadForm.do");
		}
		
		
		return mav;
	}

	@Override
	public ModelAndView modBoard(@ModelAttribute("noticeVO") NoticeVO noticeVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity removeBoard(@RequestParam("articleNO")int articleNO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
