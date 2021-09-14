package com.myspring.kmpetition.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.kmpetition.board.vo.BoardVO;
import com.myspring.kmpetition.board.vo.NoticeVO;
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

	@Override
	public void insertArticleUpload(List<UploadVO> uploadList) throws DataAccessException {
		sqlSession.insert("mapper.upload.insertArticleUpload", uploadList);
	}
	
	

	@Override
	public BoardVO updateBoard(Map articleMap) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBoard(int articleNO) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
