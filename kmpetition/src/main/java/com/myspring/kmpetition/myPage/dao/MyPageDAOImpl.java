package com.myspring.kmpetition.myPage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.kmpetition.member.vo.MemberVO;

@Repository("myPageDAO")
public class MyPageDAOImpl implements MyPageDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void updateMember(MemberVO memberVO) throws DataAccessException {
		System.out.println("dao의 updateMember");
		sqlSession.update("mapper.mypage.updateMember", memberVO);
		
	}

	@Override
	public List selectBoardList(Map pagingMap) throws DataAccessException {
		int section=(Integer)pagingMap.get("section");
		int pageNum=(Integer)pagingMap.get("pageNum");
		int startNum=(pageNum-1)*10+(section-1)*100;
		pagingMap.put("startNum", startNum);
		System.out.println("startNum:"+startNum);
		return sqlSession.selectList("mapper.mypage.myList", pagingMap);
	}
	
	@Override
	public int selectTotList(String member_id) throws DataAccessException {
		return sqlSession.selectOne("mapper.mypage.selectTotList", member_id);
	}	
}
