package com.myspring.kmpetition.admin.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.kmpetition.board.vo.NoticeVO;
import com.myspring.kmpetition.member.vo.MemberVO;

public interface AdminDAO {
	public List<MemberVO> selectAllMember() throws DataAccessException;
	
	public void insertNotice(NoticeVO noticeVO) throws DataAccessException;
	public int selectLatestOne() throws DataAccessException;
	
	public void deleteNotice(int articleNO) throws DataAccessException;
}
