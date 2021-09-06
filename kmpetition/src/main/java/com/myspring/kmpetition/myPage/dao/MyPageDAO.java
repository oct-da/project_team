package com.myspring.kmpetition.myPage.dao;

import org.springframework.dao.DataAccessException;

import com.myspring.kmpetition.member.vo.MemberVO;

public interface MyPageDAO {
	public void updateMember(MemberVO memberVO) throws DataAccessException;
}
