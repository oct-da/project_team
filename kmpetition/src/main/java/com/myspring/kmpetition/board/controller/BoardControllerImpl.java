package com.myspring.kmpetition.board.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class BoardControllerImpl extends MainController implements BoardController {
	@Autowired
	private BoardService boardService;

	@Override
	@RequestMapping(value = "/noticeList", method = RequestMethod.GET)
	public ModelAndView noticeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		System.out.println(viewName);
		ModelAndView mav = new ModelAndView(viewName);

		String _section = request.getParameter("section");
		String _pageNum = request.getParameter("pageNum");
		int section = Integer.parseInt(((_section == null) ? "1" : _section));
		int pageNum = Integer.parseInt(((_pageNum == null) ? "1" : _pageNum));
		System.out.println(section + ", " + pageNum);
		Map<String, Integer> pagingMap = new HashMap<String, Integer>();
		pagingMap.put("section", section);
		pagingMap.put("pageNum", pageNum);

		Map noticeMap = boardService.noticeList(pagingMap);

		int startNum = (pageNum - 1) * 10 + (section - 1) * 100 + 1;
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

		HttpSession session = request.getSession();
		System.out.println("admin인가?");
		System.out.println(session.getAttribute("isAdmin"));
		return mav;
	}

	@Override
	@RequestMapping(value = "/noticeDetail", method = RequestMethod.GET)
	public ModelAndView noticeDetail(@RequestParam("articleNO") int articleNO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		NoticeVO noticeVO = boardService.noticeDetail(articleNO);
		mav.addObject("noticeVO", noticeVO);
		return mav;
	}

	@Override
	@RequestMapping(value = "/boardList.do", method = RequestMethod.GET)
	public ModelAndView boardList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
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
			Map articleMap = boardService.articleList(pagingMap);
			int startNum = (pageNum - 1) * 10 + (section - 1) * 100 + 1;
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
	@RequestMapping(value = "/boardDetail.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView boardDetail(@RequestParam("articleNO") int articleNO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		Map articleMap = boardService.articleDetail(articleNO);
		BoardVO boardVO = (BoardVO) articleMap.get("boardVO");
		System.out.println("글 공개여부 : " + boardVO.isVisible());
		if (boardVO.isVisible() != true) {
			String errMsg = "비공개 게시글";
			mav.addObject(errMsg);
			mav.setViewName("redirect:/board/boardList.do");
			System.out.println("비공개 게시글입니다.");
		} else {
			mav.addObject("articleMap", articleMap);
			mav.setViewName(viewName);
			System.out.println("선택한 글제목 : " + boardVO.getTitle());
		}
		return mav;
	}

	@Override
	@RequestMapping(value = "/addBoard.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView addBoard(@RequestParam Map articleMap, MultipartHttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();

		BoardVO article = new BoardVO();
		int articleNO = boardService.createArticleNO() + 1;
		article.setArticleNO(articleNO);
		article.setId((String) articleMap.get("id"));
		article.setTitle((String) articleMap.get("title"));
		article.setContent((String) articleMap.get("content"));

		if (articleMap.get("visible").equals("on")) {
			article.setVisible(true);
		}

		System.out.println("공개글인가? : " + article.isVisible());
		System.out.println("작성아이디 : " + article.getId());

//		createdate는 매퍼에서 crudate()로 들어가니까 생략.

		// 파일 업로드 이후 DB에 등록할 목록 생성. MainController에 있는 메서드 활용.
//		add로 첫생성하는 레코드이므로 nameList는 null로 보내기. 
		List<UploadVO> uploadList = uploadFile(articleNO, null, request);

		// 데이터 베이스에 문의글/첨부파일 정보 입력하기
		Map addArticleMap = new HashMap();
		addArticleMap.put("article", article);
		addArticleMap.put("uploadList", uploadList);

		try {
			boardService.addArticle(addArticleMap);

			String msg = "게시글 등록 완료";
			System.out.println(msg);
			mav.addObject("msg", msg);
			mav.setViewName("redirect:/board/boardDetail.do?articleNO=" + articleNO);
		} catch (Exception e) {
			e.printStackTrace();
			String errMsg = "게시글 등록 중 오류 발생";
			System.out.println(errMsg);
			mav.addObject("errMsg", errMsg);
			mav.setViewName("redirect:/board/boardForm.do");
		}
		return mav;
	}

	@Override
	public ModelAndView modBoard(@ModelAttribute("noticeVO") NoticeVO noticeVO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity removeBoard(@RequestParam("articleNO") int articleNO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(value = "/search.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView search(@RequestParam Map searchMap, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView(viewName);

//		페이징처리용 코드
		String _section = request.getParameter("section");
		String _pageNum = request.getParameter("pageNum");
		int section = Integer.parseInt(((_section == null) ? "1" : _section));
		int pageNum = Integer.parseInt(((_pageNum == null) ? "1" : _pageNum));

		System.out.println(section);
		System.out.println(pageNum);
		
//		input으로 들어온 값 처리
		String[] search = request.getParameterValues("searchWord");
		String[] except = request.getParameterValues("exceptWord");
		List<String> searchWord = new ArrayList<String>();
		
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		String startDate=null;
		String endDate=null;
		
		if (searchMap.get("startDate") == "") {
			searchMap.put("startDate", null);
		}else {
			startDate=(String) searchMap.get("startDate");
			System.out.println("startDate:"+startDate);
		}
		
		if (searchMap.get("endDate") == "") {
			searchMap.put("endDate", null);
		}else {
			endDate=(String) searchMap.get("endDate");
			System.out.println("endDate:"+endDate);
		}
		

		if (search != null) {
			if (search[0] == "") {
				searchWord = null;
			} else {
				for (int i = 0; i < search.length; i++) {
					searchWord.add(search[i]);
				}
			}
		}
		
		else {
			searchWord = null;
		}


		List<String> exceptWord = new ArrayList<String>();
		if (except != null) {
			if (except[0] == "") {
				exceptWord = null;
			} else {
				for (int i = 0; i < except.length; i++) {
					exceptWord.add(except[i]);
				}
			}
		}
		
		else {
			
			exceptWord = null;
		}

		
//		리스트에서 다른 페이지로 넘어왔을 때 검색조건 불러오기
//		검색조건을 입력한 경우(조건 3개 중 하나라도 null이 아니면) 입력한 값을 searchMap에 넣어주고 세션에 바인딩
		if ((searchWord != null) || (exceptWord != null) || (startDate != null)) {
			session.removeAttribute("searchMap");
			
			searchMap.put("searchWord", searchWord);
			searchMap.put("exceptWord", exceptWord);
			session.setAttribute("searchMap", searchMap);
			System.out.println("작동");
		} else {
			searchMap = (Map) session.getAttribute("searchMap");
			System.out.println("작동2");
		}
		System.out.println("startDate 검사------");
		System.out.println("startDate==null ? : "+(startDate==null));
		
		searchMap.put("section", section);
		searchMap.put("pageNum", pageNum);
		Map petitionMap = boardService.searchList(searchMap);

		mav.addObject("petitionMap", petitionMap);
		mav.addObject("section", section);
		mav.addObject("pageNum", pageNum);

		return mav;
	}

}
