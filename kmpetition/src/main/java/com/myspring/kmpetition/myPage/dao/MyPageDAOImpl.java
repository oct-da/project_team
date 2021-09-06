package com.myspring.kmpetition.myPage.dao;

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

}
