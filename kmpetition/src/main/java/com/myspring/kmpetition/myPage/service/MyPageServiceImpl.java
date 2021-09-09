package com.myspring.kmpetition.myPage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.kmpetition.member.vo.MemberVO;
import com.myspring.kmpetition.myPage.dao.MyPageDAO;

@Service("myPageService")
@Transactional(propagation = Propagation.REQUIRED)
public class MyPageServiceImpl implements MyPageService {
	@Autowired
	private MyPageDAO dao;
	
	@Override
	public void modMember(MemberVO _memberVO) throws Exception {
		System.out.println("mypage의 modMember");
		dao.updateMember(_memberVO);
		
	}

}
