package com.myspring.kmpetition.admin.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
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
//	@Override
//	public List<MemberVO> memberList() throws Exception {
//		return dao.selectAllMember();
//	}
	

//	회원목록조회------------페이징 추가해서 테스트 
	@Override
	public Map memberList(Map<String, Integer> pagingMap) throws Exception {
//		System.out.println("서비스의 멤버리스트 진입");
		Map memberMap=new HashedMap();
		List<MemberVO> memberList=dao.selectAllMember(pagingMap);
//		System.out.println("서비스의 멤버조회 완료");
		
		int totMember=dao.selectTotMember();
//		System.out.println("서비스의 총 멤버수 완료");
		
		memberMap.put("memberList", memberList);
		memberMap.put("totMember", totMember);
		return memberMap;
	}

//	Notice 새 글 추가
	@Override
	public void addNotice(NoticeVO noticeVO) throws Exception {
		dao.insertNotice(noticeVO);
	}

//	Notice 게시물 삭제
	@Override
	public void removeNotice(int articleNO) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	
}
