package com.myspring.kmpetition.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.kmpetition.board.vo.NoticeVO;

@Repository("adminDAO")
public class AdminDAOImpl implements AdminDAO{
	@Autowired
	private SqlSession sqlSession;
	
//	@Override
//	public List<MemberVO> selectAllMember() throws DataAccessException {
//		 return sqlSession.selectList("mapper.admin.selectAllMember");
//		
//	}
	
	public List selectAllMember(Map pagingMap) throws DataAccessException{
		System.out.println("dao의 멤버조회 진입");

		int section=(Integer)pagingMap.get("section");
		int pageNum=(Integer)pagingMap.get("pageNum");
		int startNum=(pageNum-1)*10+(section-1)*100+1;
		return sqlSession.selectList("mapper.admin.selectAllMember", startNum);
	}
	
	public int selectTotMember() throws DataAccessException{
		System.out.println("dao의 총 멤버수 진입");

		return sqlSession.selectOne("mapper.admin.selectTotMember");
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
