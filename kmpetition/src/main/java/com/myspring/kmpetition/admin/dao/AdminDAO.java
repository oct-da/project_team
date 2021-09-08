package com.myspring.kmpetition.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.kmpetition.board.vo.NoticeVO;

public interface AdminDAO {
//	public List<MemberVO> selectAllMember() throws DataAccessException;
	
	public List selectAllMember(Map pagingMap) throws DataAccessException;
	public int selectTotMember() throws DataAccessException;
	
	public void insertNotice(NoticeVO noticeVO) throws DataAccessException;
	public int selectLatestOne() throws DataAccessException;
	
	public void deleteNotice(int articleNO) throws DataAccessException;
}
