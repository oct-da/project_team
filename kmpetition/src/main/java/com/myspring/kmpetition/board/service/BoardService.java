package com.myspring.kmpetition.board.service;

import java.util.List;
import java.util.Map;

import com.myspring.kmpetition.board.vo.BoardVO;
import com.myspring.kmpetition.board.vo.NoticeVO;

public interface BoardService {

//	notice 게시판 관련
	public Map noticeList(Map<String, Integer> pagingMap) throws Exception;
	public NoticeVO noticeDetail(int articleNO) throws Exception;
	
//	1:1 문의 게시판 관련
	public Map articleList(Map<String, Integer> pagingMap) throws Exception;
	public BoardVO articleDetail(int articleNO) throws Exception;
	public void addArticle(Map articleMap) throws Exception;
	public void modArticle(BoardVO articleVO) throws Exception;
	public void removeArticle(int articleNO) throws Exception;
	public int createArticleNO() throws Exception;;
}
