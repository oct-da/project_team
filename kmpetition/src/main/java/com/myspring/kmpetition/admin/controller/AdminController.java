package com.myspring.kmpetition.admin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.kmpetition.board.vo.NoticeVO;
import com.myspring.kmpetition.board.vo.ReplyVO;

public interface AdminController {
	public ModelAndView memberList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView addNotice(@RequestParam Map articleMap, MultipartHttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView modNoticeForm(@RequestParam(value = "articleNO") int articleNO, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	public ModelAndView modNotice(@RequestParam Map modMap, MultipartHttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	public String removeNotice(@RequestParam("articleNO") int articleNO,HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView dataPreview(HttpServletRequest request, HttpServletResponse response) throws Exception;


	public ModelAndView replyForm(@ModelAttribute("articleNO") int articleNO, HttpServletRequest request, HttpServletResponse response) throws Exception ;
	public ModelAndView modReplyForm(@RequestParam Map modMap, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ResponseEntity removeReply(@RequestParam("articleNO")int articleNO, HttpServletRequest request, HttpServletResponse response)  throws Exception; 
	public ModelAndView modReply(@RequestParam Map modMap, MultipartHttpServletRequest request,
			HttpServletResponse response) throws Exception;
	public ModelAndView addReply(@ModelAttribute("reply")ReplyVO reply, MultipartHttpServletRequest request, HttpServletResponse response)  throws Exception;
}
