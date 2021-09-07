package com.myspring.kmpetition.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.kmpetition.admin.dao.AdminDAO;
import com.myspring.kmpetition.board.vo.NoticeVO;
import com.myspring.kmpetition.member.vo.MemberVO;

@Service("adminService")
@Transactional(propagation = Propagation.REQUIRED)
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDAO dao;
	
//	회원목록조회
	@Override
	public List<MemberVO> memberList() throws Exception {
		
		return dao.selectAllMember();
	}

//	Notice 새 글 추가 (1.새글 추가/ 2.추가한 새글 상세창으로 이동하기 위해 새글의 articleNO 반환)
	@Override
	public int addNotice(NoticeVO noticeVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

//	Notice 게시물 삭제
	@Override
	public void removeNotice(int articleNO) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	
}
