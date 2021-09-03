package com.myspring.kmpetition.member.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.kmpetition.member.vo.MemberVO;

public interface MemberDAO {

	public MemberVO login(Map loginMap) throws DataAccessException;
}
