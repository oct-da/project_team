package com.myspring.kmpetition.myPage.service;

import java.util.Map;

import com.myspring.kmpetition.member.vo.MemberVO;

public interface MyPageService {
	public void modMember(MemberVO _memberVO) throws Exception;
	public Map viewList(Map pagingMap) throws Exception;
}
