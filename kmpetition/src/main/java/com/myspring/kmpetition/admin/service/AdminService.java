package com.myspring.kmpetition.admin.service;

import java.util.List;

import com.myspring.kmpetition.board.vo.NoticeVO;
import com.myspring.kmpetition.member.vo.MemberVO;

public interface AdminService {
	public List<MemberVO> memberList() throws Exception;
	public int addNotice(NoticeVO noticeVO) throws Exception;
	public void removeNotice(int articleNO) throws Exception;
	
}
