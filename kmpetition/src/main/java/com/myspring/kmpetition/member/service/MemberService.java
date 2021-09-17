package com.myspring.kmpetition.member.service;

import java.util.Map;

import com.myspring.kmpetition.member.vo.HistoryVO;
import com.myspring.kmpetition.member.vo.MemberVO;

public interface MemberService {
//	로그인
	public MemberVO login(Map  loginMap) throws Exception;
//	정상 로그인 시 최종접속일 업데이트
	public void updateDate(String id) throws Exception;
//	휴면계정 활성화
	public String awakeMember(MemberVO memberVO) throws Exception;
	
//	회원가입
//	public void addMember(MemberVO memberVO) throws Exception;
	
//	회원가입-이메일 드롭박스 이용
	public void addMember(Map<String, String> memberMap) throws Exception;
	
	
//	ID중복검사(회원가입)
	public String overlapped(String id) throws Exception;
	
//	이메일 중복검사(회원가입)
	public String checkEmail(String email) throws Exception;
	
//	ID찾기
	public MemberVO findId(Map<String, String> findMap) throws Exception;
//	비밀번호 찾기
	public MemberVO findPwd(Map<String, String> findMap) throws Exception;
//	비밀번호 찾기 결과, 비밀번호 변경
	public void modPwd(Map<String, String> modMap) throws Exception;
	
//	내가 열람한 정보 저장
	public void saveHistory(HistoryVO historyVO) throws Exception;
}
