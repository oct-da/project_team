package com.myspring.kmpetition.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.kmpetition.board.vo.NoticeVO;
import com.myspring.kmpetition.board.vo.ReplyVO;
import com.myspring.kmpetition.board.vo.UploadVO;

@Repository("adminDAO")
public class AdminDAOImpl implements AdminDAO{
	@Autowired
	private SqlSession sqlSession;
	
//	페이징처리한 selectAllMember
	public List selectAllMember(Map pagingMap) throws DataAccessException{
		System.out.println("dao의 멤버조회 진입");

		int section=(Integer)pagingMap.get("section");
		int pageNum=(Integer)pagingMap.get("pageNum");
		int startNum=(pageNum-1)*10+(section-1)*100+1;
		return sqlSession.selectList("mapper.admin.selectAllMember", startNum);
	}
	
	public int selectTotMember() throws DataAccessException{
		System.out.println("dao의 총 멤버수 진입");

		return sqlSession.selectOne("mapper.admin.selectTotMember");
	}

//	공지사항 추가 관련 메서드
	public int selectMaxNoticeNO() throws DataAccessException{
		return sqlSession.selectOne("mapper.admin.selectMaxNoticeNO");
	}
	@Override
	public void insertNotice(NoticeVO noticeVO) throws DataAccessException {
		sqlSession.insert("mapper.admin.insertNotice", noticeVO);
		
	}
	public void insertNoticeUpload(List<UploadVO> uploadList) throws DataAccessException{
		sqlSession.insert("mapper.upload.insertNoticeUpload", uploadList);
	}
	
	
	
	@Override
	public void updateNotice(NoticeVO noticeVO) throws DataAccessException {
		sqlSession.insert("mapper.admin.updateNotice", noticeVO);
		
	}

	@Override
	public void deleteNotice(int articleNO) throws DataAccessException {
		sqlSession.insert("mapper.admin.deleteNotice", articleNO);
		
	}

	
	
	
	
	/* 답글 관련 기능 */
public void updateDisable(int articleNO) throws DataAccessException{
		
		sqlSession.update("mapper.admin.updateDisable", articleNO);
	}
	
	
	public void insertReply(ReplyVO reply) throws DataAccessException {
		
		sqlSession.insert("mapper.admin.insertReply", reply);
	}
	
	public void insertReplyUpload(List<UploadVO> replyUpload) throws DataAccessException{
		
		sqlSession.insert("mapper.admin.insertReplyUpload", replyUpload);
	}
	
	public List<String> selectReplyUploadList(int articleNO) throws DataAccessException{
		
		return sqlSession.selectList("mapper.admin.selectReplyUploadList", articleNO);
	}
	
	public void updateReply(ReplyVO replyVO)  throws DataAccessException{
		
		sqlSession.update("mapper.admin.updateReply", replyVO);
	}
	
	public void deleteReplyUpload(List<UploadVO> deleteList) throws DataAccessException{
		
		sqlSession.delete("mapper.admin.deleteReplyUpload", deleteList);
	}
	
	public void deleteReply(int articleNO) throws DataAccessException{
		
		sqlSession.delete("mapper.admin.deleteReply",articleNO);
	}
}
