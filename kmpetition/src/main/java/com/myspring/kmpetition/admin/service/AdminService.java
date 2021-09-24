package com.myspring.kmpetition.admin.service;

import java.util.List;
import java.util.Map;

import com.myspring.kmpetition.board.vo.NoticeVO;
import com.myspring.kmpetition.member.vo.MemberVO;

public interface AdminService {
	public Map memberList(Map<String, Integer> pagingMap) throws Exception;

	public int maxNoticeNO() throws Exception;
	public void addNotice(Map noticeMap) throws Exception;
	
	public void modNotice(NoticeVO noticeVO) throws Exception;

	public void removeNotice(int articleNO) throws Exception;

	/* 답글 관련 기능 */
	public void addReply(Map replyMap) throws Exception;

	public List<String> getReplyUploadList(int articleNO) throws Exception;

	public void modReply(Map replyMap) throws Exception;

	public void removeReply(int articleNO) throws Exception;

}
