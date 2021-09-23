package com.myspring.kmpetition.board.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.kmpetition.board.vo.BoardVO;
import com.myspring.kmpetition.board.vo.NoticeVO;

public interface BoardController {
//	공지사항 관련 메서드
	public ModelAndView noticeList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView noticeDetail(@RequestParam("articleNO") int articleNO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
//	1:1 문의 관련 메서드
	public ModelAndView boardList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView boardDetail(@RequestParam("articleNO") int articleNO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView addBoard(@RequestParam Map articleMap, MultipartHttpServletRequest request, HttpServletResponse response)
			throws Exception ;
//	게시글에 답변이 달려있으면 수정/삭제가 불가능하므로 Form.jsp로 보내기 전 답변이 있는 글인지 확인하는 작업이 필요
	public ModelAndView modArticleForm(@RequestParam(value = "articleNO") int articleNO, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
//	이건 게시글에 hidden 태그로 boolean 타입의 reply 데이터를 넣어두고 자바스크립트로 미리 판단해서 보내주기
	public ModelAndView modBoard(@ModelAttribute("article") BoardVO article, MultipartHttpServletRequest request,
			HttpServletResponse response) throws Exception;
	public String  removeBoard(@RequestParam("articleNO") int articleNO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView search(@RequestParam Map searchMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
}
