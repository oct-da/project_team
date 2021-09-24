package com.myspring.kmpetition.myPage.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.kmpetition.board.vo.BoardVO;
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
	
	@Override
	public Map viewList(Map pagingMap) throws Exception {
		Map ListMap=new HashedMap();
		String member_id = (String) pagingMap.get("member_id");
		
		List<BoardVO> boardVO=dao.selectBoardList(pagingMap);
		
		for(BoardVO vo : boardVO) {
			System.out.println(vo.getTitle());
		}
		
		int totList=dao.selectTotList(member_id);
		
		ListMap.put("totList" , totList);
		ListMap.put("boardVO" , boardVO);
		return ListMap;
	}

}
