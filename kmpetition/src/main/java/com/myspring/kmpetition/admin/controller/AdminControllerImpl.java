package com.myspring.kmpetition.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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

//	첨부기능 고려해서 수정하기
	public ModelAndView modNotice(@RequestParam NoticeVO noticeVO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		int articleNO = noticeVO.getArticleNO();

		adminService.modNotice(noticeVO);

		mav.setViewName("redirect:/board/noticeDetail?articleNO=" + articleNO);
		return mav;

	}

//	수정 고려해서 재작성
	@Override
	@RequestMapping(value = "/addNotice.do", method = RequestMethod.GET)
	public String removeNotice(@RequestParam("articleNO") int articleNO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 로컬에 저장된 첨부파일 삭제
		List<String> articleUploadList = adminService.noticeUploadList(articleNO);
		deleteFile(articleUploadList);

		// DB에 저장된 게시글과 답글, 첨부파일 삭제
		adminService.removeNotice(articleNO);

		return "redirect:/main.html";
	}

	@Override
	public ModelAndView dataPreview(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

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

	@RequestMapping(value = "/modReply.do", method = RequestMethod.PUT)
	public void modReply(@ModelAttribute("reply") ReplyVO reply, MultipartHttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int articleNO = reply.getArticleNO();
		HttpSession session = request.getSession();
		List<String> remove = (ArrayList<String>) session.getAttribute("remove");
		List<UploadVO> deleteList = deleteFile(remove);

		List<String> nameList = adminService.getReplyUploadList(articleNO);
		List<UploadVO> replyUpload = uploadFile(articleNO, nameList, request);

		Map replyMap = new HashMap();
		replyMap.put("reply", reply);
		replyMap.put("delete", deleteList);
		replyMap.put("insert", replyUpload);

		adminService.modReply(replyMap);
	}

	@RequestMapping(value = "/removeReply.do", method = RequestMethod.DELETE)
	public void removeReply(@RequestParam("articleNO") int articleNO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<String> deleteList = (ArrayList<String>) adminService.getReplyUploadList(articleNO);
		deleteFile(deleteList);
		adminService.removeReply(articleNO);
	}

}
