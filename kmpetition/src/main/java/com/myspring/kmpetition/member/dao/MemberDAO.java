package com.myspring.kmpetition.member.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.kmpetition.member.vo.MemberVO;

public interface MemberDAO {

	public MemberVO login(Map loginMap) throws DataAccessException;
	public void insertMember(MemberVO member) throws DataAccessException;
	public String overlapped(String id) throws DataAccessException;
	public MemberVO selectForFindId(Map<String, String> findMap) throws DataAccessException;
//	public MemberVO selectForFindId(MemberVO member) throws DataAccessException;
}
