package com.myspring.kmpetition.member.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.kmpetition.member.vo.MemberVO;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;

//	로그인 정보 조회
	@Override
	public MemberVO login(Map loginMap) throws DataAccessException {
		System.out.println("DAO의 login");
		MemberVO member =  sqlSession.selectOne("mapper.member.login", loginMap);
		return member;
	}

//	회원가입
//	@Override
//	public void insertMember(MemberVO member) throws DataAccessException {
//		sqlSession.selectOne("mapper.member.insertMember", member);
//
//	}
	

//	이멜 드롭박스 회원가입
	@Override
	public void insertMember(Map<String, String> memberMap) throws DataAccessException {
		sqlSession.selectOne("mapper.member.insertMember", memberMap);

	}

//	ID중복체크(회원가입)
	@Override
	public String overlapped(String id) throws DataAccessException {
		MemberVO memberVO = sqlSession.selectOne("mapper.member.checkId", id);
		if (memberVO != null && memberVO.getId() != null) {
			return "true"; // ID체크 불합격(이미 동일한 ID 존재)
		} else {
			return "false"; // ID체크 합격(동일한 ID 없음)
		}
	}
	
	
//	이메일 중복체크(회원가입)
	@Override
	public String checkEmail(String email) throws DataAccessException {
		MemberVO memberVO = sqlSession.selectOne("mapper.member.checkEmail", email);
		if (memberVO != null && memberVO.getId() != null) {
			return "true"; // 이멜체크 불합격(이미 동일한 이메일 존재)
		} else {
			return "false"; // 이멜체크 합격(동일한 이메일 없음)
		}
	}

	@Override
	public MemberVO selectForFindId(Map<String, String> findMap) throws DataAccessException {
//	public MemberVO selectForFindId(MemberVO member) throws DataAccessException {
		System.out.println("dao의 findid");
		MemberVO memberVO = (MemberVO) sqlSession.selectOne("mapper.member.findId", findMap);
//		MemberVO memberVO=(MemberVO)sqlSession.selectOne("mapper.member.findId", member);
		return memberVO;
	}

	@Override
	public MemberVO selectForFindPwd(Map<String, String> findMap) throws DataAccessException {
		MemberVO memberVO = (MemberVO) sqlSession.selectOne("mapper.member.findPwd", findMap);
		return memberVO;
	}

	@Override
	public void updatePwd(Map<String, String> modMap) throws DataAccessException {
		sqlSession.update("mapper.member.updatePwd",modMap);
		
	}
	
	

}
