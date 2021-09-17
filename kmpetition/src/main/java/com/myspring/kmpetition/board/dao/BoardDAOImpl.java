package com.myspring.kmpetition.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.kmpetition.board.vo.BoardVO;
import com.myspring.kmpetition.board.vo.NoticeVO;
import com.myspring.kmpetition.board.vo.PetitionVO;
import com.myspring.kmpetition.board.vo.ReplyVO;
import com.myspring.kmpetition.board.vo.UploadVO;

@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO{
	

	@Autowired
	private SqlSession sqlSession;

	
//	--------------------공지사항 게시판-----------------
	
	
	@Override
	public List selectAllNotice(Map<String, Integer> pagingMap) throws DataAccessException {
		int section=(Integer)pagingMap.get("section");
		int pageNum=(Integer)pagingMap.get("pageNum");
		int startNum=(pageNum-1)*10+(section-1)*100;
		System.out.println(startNum);
		return sqlSession.selectList("mapper.board.selectAllNotice", startNum);
	}

	@Override
	public int selectTotNotice() throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectTotNotice");
	}

	@Override
	public NoticeVO noticeDetail(int articleNO) throws DataAccessException {
		NoticeVO noticeVO=sqlSession.selectOne("mapper.board.selectNotice", articleNO);
		return noticeVO;
	}

	@Override
	public void insertNotice(Map noticeMap) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public NoticeVO updateNotice(Map noticeMap) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	
//	------------------1:1문의게시판-----------------
	
	
	@Override
	public List<BoardVO> selectAllBoard(Map<String, Integer> pagingMap) throws DataAccessException {
		int pageNum=pagingMap.get("pageNum");
		int section=pagingMap.get("section");
		int startNum=(pageNum-1)*10+(section-1)*100;
		return sqlSession.selectList("mapper.board.selectAllBoard", startNum);
	}


	@Override
	public int selectTotBoard() throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectTotboard");
	}

	@Override
	public BoardVO articleDetail(int articleNO) throws DataAccessException {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.board.selectBoard", articleNO);
	}
	@Override
	public List<UploadVO> articleFile(int articleNO) throws DataAccessException {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mapper.board.selectArticleFile", articleNO);
	}
	@Override
	public ReplyVO selectReply(int articleNO) throws DataAccessException {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.board.selectReply", articleNO);
	}
	@Override
	public List<UploadVO> selectReplyFile(int articleNO) throws DataAccessException {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mapper.board.selectReplyFile", articleNO);
	}
	
	

	
	
	@Override
	public void insertBoard(BoardVO boardVO) throws DataAccessException {
		System.out.println(boardVO.getId());
		System.out.println(boardVO.getTitle());
		System.out.println(boardVO.getContent());
		
		sqlSession.insert("mapper.board.insertArticle", boardVO);
		
	}
	
	@Override
	public int selectArticleNO() throws DataAccessException {
		
		return sqlSession.selectOne("mapper.board.maxArticleNO");
	}

	// BOARDUPLOAD 테이블에서 이미지 파일 리스트 가져오기
		public List<String> selectArticleUploadList(int articleNO) throws DataAccessException {

			return sqlSession.selectList("mapper.upload.selectArticleUploadList", articleNO);
		}
	@Override
	public void insertArticleUpload(List<UploadVO> uploadList) throws DataAccessException {
		sqlSession.insert("mapper.upload.insertArticleUpload", uploadList);
	}
	
	

	public void updateBoard(BoardVO articleVO) throws DataAccessException {
		 sqlSession.update("mapper.board.updateArticle", articleVO);
		
	}

	@Override
	public void deleteBoard(int articleNO) throws DataAccessException {
		sqlSession.delete("mapper.board.deleteArticle", articleNO);
		
	}
	
	// 문의글 첨부파일 수정(삭제)
		public void deleteArticleUpload(List<UploadVO> deleteList) throws DataAccessException{
			
			 sqlSession.delete("mapper.upload.deleteArticleUpload", deleteList);
		}
	
	
	@Override
	public List selectSearch(Map searchMap) throws DataAccessException {
		int section=(Integer)searchMap.get("section");
		int pageNum=(Integer)searchMap.get("pageNum");
		int startNum=(pageNum-1)*10+(section-1)*100;
		searchMap.put("startNum", startNum);
		List<PetitionVO> searchList = sqlSession.selectList("mapper.board.selectSearchList", searchMap);
		
		return searchList;
	}
	@Override
	public int selectSearchNum(Map searchMap) throws DataAccessException {
		int totSearch= sqlSession.selectOne("mapper.board.searchNum", searchMap);
		System.out.println("totSearch : "+totSearch);
		return totSearch;
	}
	
	
}
