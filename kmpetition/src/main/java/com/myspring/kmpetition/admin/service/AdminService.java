package com.myspring.kmpetition.admin.service;

import java.util.List;
import java.util.Map;

import com.myspring.kmpetition.board.vo.NoticeVO;
import com.myspring.kmpetition.member.vo.MemberVO;

public interface AdminService {
	public Map memberList(Map<String, Integer> pagingMap) throws Exception;
	public void addNotice(NoticeVO noticeVO) throws Exception;
	public void removeNotice(int articleNO) throws Exception;
	
}
