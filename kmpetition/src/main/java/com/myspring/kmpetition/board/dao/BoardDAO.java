package com.myspring.kmpetition.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.kmpetition.board.vo.BoardVO;
import com.myspring.kmpetition.board.vo.NoticeVO;
import com.myspring.kmpetition.board.vo.ReplyVO;
import com.myspring.kmpetition.board.vo.UploadVO;


public interface BoardDAO {
//	notice게시판 관련
	public List selectAllNotice(Map<String, Integer> pagingMap) throws DataAccessException;
	public int selectTotNotice() throws DataAccessException;
	public NoticeVO noticeDetail(int articleNO) throws DataAccessException;
	
//	1:1문의 게시판 관련
	public List selectAllBoard(Map<String, Integer> pagingMap) throws DataAccessException;
	public int selectTotBoard() throws DataAccessException;
	
	public BoardVO articleDetail(int articleNO) throws DataAccessException;
	public List<UploadVO> articleFile(int articleNO) throws DataAccessException;
	public ReplyVO selectReply(int articleNO) throws DataAccessException;
	public List<UploadVO> selectReplyFile(int articleNO) throws DataAccessException ;
	
	public List<String> selectArticleUploadList(int articleNO) throws DataAccessException ;
	public List<String> selectReplyUploadList(int articleNO) throws DataAccessException ;
	
	public void insertBoard(BoardVO boardVO) throws DataAccessException;
	public int selectArticleNO() throws DataAccessException;
	public void insertArticleUpload(List<UploadVO> uploadList) throws DataAccessException;
	
//	public List<String> selectArticleUploadList(int articleNO) throws DataAccessException;
	
	
	public void updateBoard(BoardVO articleVO) throws DataAccessException ;
	public int deleteBoard(int articleNO) throws DataAccessException;
	public void deleteArticleUpload(List<UploadVO> deleteList) throws DataAccessException;
	
	public List selectSearch(Map SearchMap) throws DataAccessException;
	public int selectSearchNum(Map SearchMap) throws DataAccessException;
}
