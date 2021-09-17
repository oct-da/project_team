package com.myspring.kmpetition.member.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.kmpetition.member.vo.HistoryVO;
import com.myspring.kmpetition.member.vo.MemberVO;

public interface MemberDAO {

	public MemberVO login(Map loginMap) throws DataAccessException;
	public void updateLastLogin(String id) throws DataAccessException;
	
	public MemberVO selectForAwake(MemberVO memberVO) throws DataAccessException;
	
//	public void insertMember(MemberVO member) throws DataAccessException;
	
//	이메일드롭박스 회원가입
	public void insertMember(Map<String, String> memberMap) throws DataAccessException;
	
	public String overlapped(String id) throws DataAccessException;
	public String checkEmail(String email) throws DataAccessException;
	
	public MemberVO selectForFindId(Map<String, String> findMap) throws DataAccessException;
	public MemberVO selectForFindPwd(Map<String, String> findMap) throws DataAccessException;
	public void updatePwd(Map<String, String> modMap) throws DataAccessException;
	
	public void insertHistory(HistoryVO historyVO) throws DataAccessException;
}
