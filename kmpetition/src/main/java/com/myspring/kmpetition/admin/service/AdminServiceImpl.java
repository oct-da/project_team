package com.myspring.kmpetition.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.kmpetition.admin.dao.AdminDAO;
import com.myspring.kmpetition.board.vo.BoardVO;
import com.myspring.kmpetition.board.vo.NoticeVO;
import com.myspring.kmpetition.board.vo.ReplyVO;
import com.myspring.kmpetition.board.vo.UploadVO;
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
		Map memberMap = new HashedMap();
		List<MemberVO> memberList = dao.selectAllMember(pagingMap);
//		System.out.println("서비스의 멤버조회 완료");

		int totMember = dao.selectTotMember();
//		System.out.println("서비스의 총 멤버수 완료");

		memberMap.put("memberList", memberList);
		memberMap.put("totMember", totMember);
		return memberMap;
	}

//	Notice 새글추가 메서드
	@Override
	public int maxNoticeNO() throws Exception {
		return dao.selectMaxNoticeNO();
	}

	@Override
	public void addNotice(Map noticeMap) throws Exception {
		NoticeVO noticeVO = (NoticeVO) noticeMap.get("noticeVO");
		List<UploadVO> uploadList = (ArrayList<UploadVO>) noticeMap.get("uploadList");

		dao.insertNotice(noticeVO);
		System.out.println("insertNotice 완료");

		if (uploadList.size() != 0) {
			System.out.println("파일리스트 개수 : " + uploadList.size());

			dao.insertNoticeUpload(uploadList);
		}
	}

	@Override
	public Map noticeDetail(int articleNO) throws Exception {
		NoticeVO bvo = dao.selectNotice(articleNO);
		List<String> uploadList = dao.selectNoticeUploadList(articleNO);

		Map articleMap = new HashMap();
		articleMap.put("noticeVO", bvo);
		articleMap.put("uploadList", uploadList);

		return articleMap;
	}

	@Override
	public void modNotice(Map modMap) throws Exception {

		NoticeVO articleVO = (NoticeVO) modMap.get("article");
		List<UploadVO> deleteList = (ArrayList<UploadVO>) modMap.get("delete");
		List<UploadVO> uploadList = (ArrayList<UploadVO>) modMap.get("upload");

		// DB 반영
		System.out.println(articleVO.getTitle());
		System.out.println(articleVO.getContent());
		System.out.println(articleVO.getArticleNO());
		dao.updateNotice(articleVO);
		
		if (deleteList != null) {
			if (deleteList.size() != 0) {
				System.out.println("deleteList.size():" + deleteList.size());
				dao.deleteArticleUpload(deleteList);
			}
		}
		if (uploadList != null) {
			if (uploadList.size() != 0) {
				System.out.println("uploadList.size():" + uploadList.size());
				dao.insertNoticeUpload(uploadList);
			}
		}
	}

//	Notice 게시물 삭제
//	CASCADE 설정을 해놔서 upload 테이블은 자동으로 삭제됨
	public List<String> noticeUploadList(int articleNO) throws Exception {
		return dao.selectNoticeUploadList(articleNO);
	}

	@Override
	public void removeNotice(int articleNO) throws Exception {
		dao.deleteNotice(articleNO);

	}

	/* 답글 관련 기능 */

	public void addReply(Map replyMap) throws Exception {

		ReplyVO reply = (ReplyVO) replyMap.get("reply");
		int articleNO = reply.getArticleNO();
		List<UploadVO> replyUpload = (ArrayList<UploadVO>) replyMap.get("replyUpload");

		dao.updateDisable(articleNO);
		dao.insertReply(reply);
		if (replyUpload.size() != 0) {
			dao.insertReplyUpload(replyUpload);
		}
	}

	public List<String> getReplyUploadList(int articleNO) throws Exception {

		return dao.selectReplyUploadList(articleNO);
	}

	public void modReply(Map replyMap) throws Exception {

		ReplyVO reply = (ReplyVO) replyMap.get("reply");
		List<UploadVO> deleteList = (ArrayList<UploadVO>) replyMap.get("delete");
		List<UploadVO> uploadList = (ArrayList<UploadVO>) replyMap.get("insert");

		dao.updateReply(reply);
		if (deleteList != null) {
			if (deleteList.size() != 0) {
				System.out.println("deleteList.size():" + deleteList.size());
				dao.deleteReplyUpload(deleteList);
			}
		}
		if (uploadList != null) {
			if (uploadList.size() != 0) {
				System.out.println("uploadList.size():" + uploadList.size());
				dao.insertReplyUpload(uploadList);
			}
		}
	}

	public void removeReply(int articleNO) throws Exception {

		dao.deleteReply(articleNO);
	}

}
