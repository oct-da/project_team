package com.myspring.kmpetition.board.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.kmpetition.board.dao.BoardDAO;
import com.myspring.kmpetition.board.vo.BoardVO;
import com.myspring.kmpetition.board.vo.NoticeVO;
import com.myspring.kmpetition.board.vo.UploadVO;

@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardDAO dao;
	
	@Override
	public Map noticeList(Map<String, Integer> pagingMap) throws Exception {
		Map noticeMap=new HashedMap();
		List<NoticeVO> noticeList=dao.selectAllNotice(pagingMap);
		int totNotice=dao.selectTotNotice();
		
		noticeMap.put("noticeList", noticeList);
		noticeMap.put("totNotice", totNotice);
		return noticeMap;
	}

	@Override
	public NoticeVO noticeDetail(int articleNO) throws Exception {
		return dao.noticeDetail(articleNO);
	}
	
	
	

	@Override
	public Map articleList(Map<String, Integer> pagingMap) throws Exception {
		Map articleMap=new HashedMap();
		List<BoardVO> articleList=dao.selectAllBoard(pagingMap);
		int totBoard=dao.selectTotBoard();
		articleMap.put("articleList", articleList);
		articleMap.put("totBoard", totBoard);
		return articleMap;
	}

	@Override
	public Map articleDetail(int articleNO) throws Exception {
		// TODO Auto-generated method stub
//		return dao.articleDetail(articleNO);
		BoardVO bvo = dao.articleDetail(articleNO);
		List<UploadVO> uploadList=dao.articleFile(articleNO);
		Map articleMap=new HashMap();
		articleMap.put("boardVO", bvo);
		articleMap.put("uploadList",uploadList);
		return articleMap;
		
	}

	@Override
	public void addArticle(Map addArticleMap) throws Exception {
		BoardVO articleVO = (BoardVO) addArticleMap.get("article");
		List<UploadVO> uploadList = (ArrayList<UploadVO>) addArticleMap.get("uploadList");
		
		dao.insertBoard(articleVO);
		if(uploadList.size()!=0) {
			System.out.println("파일리스트 개수 : "+uploadList.size());
					
			dao.insertArticleUpload(uploadList);
		}
	}

	@Override
	public int createArticleNO() throws Exception {
		return dao.selectArticleNO();
		
	}

	@Override
	public void modArticle(BoardVO articleVO) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeArticle(int articleNO) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Map searchList(Map searchMap) throws Exception {
		List<String> searchWord = (List<String>) searchMap.get("searchWord");
		List<String> exceptWord = (List<String>) searchMap.get("exceptWord");
		String startDate=(String) searchMap.get("startDate");
		String endDate=(String) searchMap.get("endDate");
		
		List petitionList = dao.selectSearch(searchMap);
		int totSearchList=dao.selectSearchNum(searchMap);
		
		Map petitionMap=new HashedMap();
		petitionMap.put("petitionList", petitionList);
		petitionMap.put("totSearchList", totSearchList);
		return petitionMap;
	}

	
}
