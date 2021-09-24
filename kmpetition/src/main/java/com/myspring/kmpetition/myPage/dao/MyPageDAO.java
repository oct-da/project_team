package com.myspring.kmpetition.myPage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.kmpetition.board.vo.BoardVO;
import com.myspring.kmpetition.member.vo.MemberVO;

public interface MyPageDAO {
	public void updateMember(MemberVO memberVO) throws DataAccessException;
	public List<BoardVO> selectBoardList(Map pagingMap) throws DataAccessException;
	public int selectTotList(String member_id) throws DataAccessException;
}
