package com.myspring.kmpetition.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface AdminController {
	public ModelAndView memberList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView addNotice(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity removeNotice(@RequestParam("articleId") String articleId,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView dataPreview(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
