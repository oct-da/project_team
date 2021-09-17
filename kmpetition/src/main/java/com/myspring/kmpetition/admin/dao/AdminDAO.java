package com.myspring.kmpetition.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.kmpetition.board.vo.NoticeVO;
import com.myspring.kmpetition.board.vo.ReplyVO;
import com.myspring.kmpetition.board.vo.UploadVO;

public interface AdminDAO {
//	public List<MemberVO> selectAllMember() throws DataAccessException;

	public List selectAllMember(Map pagingMap) throws DataAccessException;

	public int selectTotMember() throws DataAccessException;

	public void insertNotice(NoticeVO noticeVO) throws DataAccessException;

	public int selectLatestOne() throws DataAccessException;

	public void deleteNotice(int articleNO) throws DataAccessException;

	/* 답글 관련 기능 */
	public void updateDisable(int articleNO) throws DataAccessException;

	public void insertReply(ReplyVO reply) throws DataAccessException;

	public void insertReplyUpload(List<UploadVO> replyUpload) throws DataAccessException;

	public List<String> selectReplyUploadList(int articleNO) throws DataAccessException;

	public void updateReply(ReplyVO replyVO) throws DataAccessException;

	public void deleteReplyUpload(List<UploadVO> deleteList) throws DataAccessException;

	public void deleteReply(int articleNO) throws DataAccessException;

}
