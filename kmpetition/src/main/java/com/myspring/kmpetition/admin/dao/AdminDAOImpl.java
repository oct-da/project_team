package com.myspring.kmpetition.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.kmpetition.board.vo.NoticeVO;
import com.myspring.kmpetition.member.vo.MemberVO;

@Repository("adminDAO")
public class AdminDAOImpl implements AdminDAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<MemberVO> selectAllMember() throws DataAccessException {
		 return sqlSession.selectList("mapper.admin.selectAllMember");
		
	}

	@Override
	public void insertNotice(NoticeVO noticeVO) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int selectLatestOne() throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteNotice(int articleNO) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	
}
