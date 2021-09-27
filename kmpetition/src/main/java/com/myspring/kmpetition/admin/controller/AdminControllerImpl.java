package com.myspring.kmpetition.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.kmpetition.admin.service.AdminService;
import com.myspring.kmpetition.board.vo.BoardVO;
import com.myspring.kmpetition.board.vo.NoticeVO;
import com.myspring.kmpetition.board.vo.ReplyVO;
import com.myspring.kmpetition.board.vo.UploadVO;
import com.myspring.kmpetition.main.MainController;

@Controller("adminController")
@RequestMapping(value = "/admin")
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
			String viewName = (String) request.getAttribute("viewName");
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
				int startNum = (pageNum - 1) * 10 + (section - 1) * 100 + 1;
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
	public ModelAndView addNotice(@RequestParam Map articleMap, MultipartHttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();

		NoticeVO noticeVO = new NoticeVO();
		int articleNO = adminService.maxNoticeNO() + 1;

		noticeVO.setArticleNO(articleNO);
		noticeVO.setTitle((String) articleMap.get("title"));
		noticeVO.setContent((String) articleMap.get("content"));

//		createdate는 매퍼에서 crudate()로 들어가니까 생략.

		// 파일 업로드 이후 DB에 등록할 목록 생성. MainController에 있는 메서드 활용.
//		add로 첫생성하는 레코드이므로 nameList는 null로 보내기. 
		List<UploadVO> uploadList = uploadFile(articleNO, null, request);

		// 데이터 베이스에 문의글/첨부파일 정보 입력하기
		Map addArticleMap = new HashMap();
		addArticleMap.put("noticeVO", noticeVO);
		addArticleMap.put("uploadList", uploadList);

		try {
			adminService.addNotice(addArticleMap);

			String msg = "공지사항 등록 완료";
			System.out.println(msg);
			mav.addObject("msg", msg);
			mav.setViewName("redirect:/board/noticeDetail.do?articleNO=" + articleNO);
		} catch (Exception e) {
			e.printStackTrace();
			String errMsg = "게시글 등록 중 오류 발생";
			System.out.println(errMsg);
			mav.addObject("errMsg", errMsg);
			mav.setViewName("redirect:/admin/noticeForm.do");
		}
		return mav;
	}

	// 문의글 수정 페이지
	@Override
	@RequestMapping(value = "/modNoticeForm.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView modNoticeForm(@RequestParam(value = "articleNO") int articleNO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("modArticleForm.do 진입");
		System.out.println(articleNO);
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

//			글작성자와 로그인한 회원 ID가 동일한지 체크는 뷰에서.

		Map articleMap = adminService.noticeDetail(articleNO);

		NoticeVO article = (NoticeVO) articleMap.get("noticeVO");
		article.setArticleNO(articleNO);
		articleMap.put("article", article);

		mav.addObject("articleMap", articleMap);
		return mav;
	}

	@Override
	@RequestMapping(value = "/modNotice.do", method = { RequestMethod.PUT, RequestMethod.POST })
	public ModelAndView modNotice(@RequestParam Map modMap, MultipartHttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String articleNOStr = (String) modMap.get("articleNO");
		int articleNO = Integer.parseInt(articleNOStr);

//		수정/삭제 파일의 제거 작업
		String[] removeStr = request.getParameterValues("removeList");
		List<String> removeList = new ArrayList<String>();
		List<UploadVO> deleteList = null;
		if (removeStr != null) {
			for (String str : removeStr) {
				removeList.add(str);
			}
			deleteList = deleteFile(removeList);

		}

//		수정/추가 파일 업로드 작업
		String[] attach = request.getParameterValues("attachName");
		List<String> attachName = new ArrayList<String>();
		List<UploadVO> uploadList = null;
		if (attach != null) {
			for (String str : attach) {
				attachName.add(str);
			}
		} else {
			attachName = null;
		}
		uploadList = uploadFile(articleNO, attachName, request);

//		들어온 데이터로 boardVO 세팅
		NoticeVO article = new NoticeVO();
		String title = (String) modMap.get("title");
		System.out.println("title:"+title);
		String content = (String) modMap.get("content");
		System.out.println("content:"+content);
		article.setTitle(title);
		article.setContent(content);
		article.setArticleNO(articleNO);

		// DB 반영
		Map articleMap = new HashMap();
		articleMap.put("article", article);
		articleMap.put("delete", deleteList);
		articleMap.put("upload", uploadList);
		adminService.modNotice(articleMap);

		mav.setViewName("redirect:/board/noticeDetail.do?articleNO=" + articleNO);
		return mav;

	}

//	수정 고려해서 재작성
	@Override
	@RequestMapping(value = "/removeNotice.do", method = RequestMethod.GET)
	public String removeNotice(@RequestParam("articleNO") int articleNO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 로컬에 저장된 첨부파일 삭제
		List<String> articleUploadList = adminService.noticeUploadList(articleNO);
		deleteFile(articleUploadList);

		// DB에 저장된 게시글과 답글, 첨부파일 삭제
		adminService.removeNotice(articleNO);

		return "redirect:/board/noticeList.do";
	}

	@Override
	public ModelAndView dataPreview(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RequestMapping(value = "/replyForm.do", method = RequestMethod.POST)
	public ModelAndView replyForm(@ModelAttribute("articleNO") int articleNO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;

	}

	/* 이하는 상윤씨 작업 분량. 답글 관련 기능 */
	@RequestMapping(value = "/addReply.do", method = RequestMethod.POST)
	public ModelAndView addReply(@ModelAttribute("reply") ReplyVO reply, MultipartHttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();

		int articleNO = reply.getArticleNO();

		List<UploadVO> uploadList = uploadFile(articleNO, null, request);

		Map replyMap = new HashMap();
		replyMap.put("reply", reply);
		replyMap.put("replyUpload", uploadList);

		try {
			adminService.addReply(replyMap);

			mav.setViewName("redirect:/board/boardDetail.do?articleNO=" + articleNO);

		} catch (Exception e) {
			String errMsg = "에러 발생";
			mav.addObject("articleNO", articleNO);
			mav.setViewName("/admin/replyForm.do");
		}
		return mav;

	}

	@Override
	@RequestMapping(value = "/modReplyForm.do", method = RequestMethod.POST)
	public ModelAndView modReplyForm(@RequestParam Map modMap, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		int articleNO = Integer.parseInt((String) modMap.get("articleNO"));
		System.out.println((String) modMap.get("title"));
		System.out.println((String) modMap.get("content"));
		System.out.println(articleNO);

		ReplyVO rvo = new ReplyVO();
		rvo.setTitle((String) modMap.get("title"));
		rvo.setContent((String) modMap.get("content"));
		rvo.setArticleNO(articleNO);

		List<String> uploadList = adminService.getReplyUploadList(articleNO);

		Map replyMap = new HashMap();
		replyMap.put("replyVO", rvo);
		replyMap.put("uploadList", uploadList);

		mav.addObject("replyMap", replyMap);
		System.out.println("modReplyForm.do 완료");

		return mav;

	}

	@RequestMapping(value = "/modReply.do", method = { RequestMethod.PUT, RequestMethod.POST })
	public ModelAndView modReply(@RequestParam Map modMap, MultipartHttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("modReply 진입");
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();

		int articleNO = Integer.parseInt((String) modMap.get("articleNO"));

//		수정/삭제 파일의 제거 작업
//		삭제한 파일의 이름들을 removeList에 넣어서 실제 데이터 삭제(deleteFile)
//		삭제한 파일의 이름들을 deleteList로 반환받음
		String[] removeStr = request.getParameterValues("removeList");
		List<String> removeList = new ArrayList<String>();
		System.out.println("제거리스트 생성시작");
		List<UploadVO> deleteList = null;
		if (removeStr != null) {
			for (String str : removeStr) {
				removeList.add(str);
				System.out.println(str);
			}
			deleteList = deleteFile(removeList);
			

		}

//		수정/추가 파일 업로드 작업
//		기존에 올렸던, 수정하지 않은 파일 이름을 attachName 리스트로 생성
//		request와 attachName(기존이름 리스트)를 가지고 실제 업로드(uploadFile)
//		업로드해서 실제로 저장된 이름을 uploadList
		System.out.println("기존파일리스트 생성시작");
		String[] attach = request.getParameterValues("attachName");
		List<String> attachName = new ArrayList<String>();
		List<UploadVO> uploadList = null;
		if (attach != null) {
			for (String str : attach) {
				attachName.add(str);
				System.out.println(str);
			}
		} else {
			attachName = null;
		}
		uploadList = uploadFile(articleNO, attachName, request);

		ReplyVO rvo = new ReplyVO();
		rvo.setTitle((String) modMap.get("title"));
		rvo.setContent((String) modMap.get("content"));
		rvo.setArticleNO(articleNO);

//		List<String> nameList = adminService.getReplyUploadList(articleNO);
//		List<UploadVO> replyUpload = uploadFile(articleNO, nameList, request);

		Map replyMap = new HashMap();
		replyMap.put("reply", rvo);
		replyMap.put("delete", deleteList);
		replyMap.put("insert", uploadList);

		adminService.modReply(replyMap);

		mav.setViewName("redirect:/board/boardDetail.do?articleNO=" + articleNO);
		return mav;
	}

	@Override
	@RequestMapping(value = "/removeReply.do", method = { RequestMethod.DELETE, RequestMethod.GET })
	public ResponseEntity removeReply(@RequestParam("articleNO") int articleNO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("removeReply 메서드 진입");
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			List<String> deleteList = (ArrayList<String>) adminService.getReplyUploadList(articleNO);
			deleteFile(deleteList);
			adminService.removeReply(articleNO);
			message = "success";
		} catch (Exception e) {

			message = "error";
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;

	}

}
