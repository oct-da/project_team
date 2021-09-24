package com.myspring.kmpetition.board.service;

import java.util.List;
import java.util.Map;

import com.myspring.kmpetition.board.vo.BoardVO;
import com.myspring.kmpetition.board.vo.NoticeVO;

public interface BoardService {

//	notice 게시판 관련
	public Map noticeList(Map<String, Integer> pagingMap) throws Exception;
	public Map noticeDetail(int articleNO) throws Exception;
	
//	1:1 문의 게시판 관련
	public Map articleList(Map<String, Integer> pagingMap) throws Exception;
//	public BoardVO articleDetail(int articleNO) throws Exception;
	public Map articleDetail(int articleNO) throws Exception;
	public void addArticle(Map addArticleMap) throws Exception;
	public void modArticle(Map addArticleMap) throws Exception;
	
	public List<String> articleUploadList(int articleNO) throws Exception;
	public List<String> replyUploadList(int articleNO) throws Exception;
	
	public int removeArticle(int articleNO) throws Exception;
	public int createArticleNO() throws Exception;
	
	public Map searchList(Map searchMap) throws Exception;
}
