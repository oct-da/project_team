package com.myspring.kmpetition.member.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.kmpetition.member.dao.MemberDAO;
import com.myspring.kmpetition.member.vo.MemberVO;

@Service("memberService")
@Transactional(propagation = Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberVO memberVO;
	@Autowired
	private MemberDAO memberDAO;

//	로그인
	@Override
	public MemberVO login(Map loginMap) throws Exception {
		memberVO = memberDAO.login(loginMap);
//		if (memberVO != null && memberVO.getId() != null) {
//			memberDAO.updateLastLogin(memberVO.getId());
//		}
		return memberVO;

	}

//	최종접속일 업데이트
	@Override
	public void updateDate(String id) throws Exception {
		memberDAO.updateLastLogin(id);
	}


	@Override
	public String awakeMember(MemberVO memberVO) throws Exception {
		String result="false";
		System.out.println("서비스의 awakeMember 진입");
		
//		휴면계정 활성화 폼에서 입력한 값으로 회원을 조회
		memberVO =memberDAO.selectForAwake(memberVO);
		
		System.out.println("서비스의 awakeMember 통과, 접속일 업데이트 전");
//		System.out.println("아이디 : "+memberVO.getId()+", 최종접속일 : "+memberVO.getLastlogin());
		
//		해당 데이터와 일치하는 회원이 존재하면 해당 회원의 최종접속일 업데이트
		if (memberVO!=null) {
			
			memberDAO.updateLastLogin(memberVO.getId());
			System.out.println("서비스의 awakeMember 통과, 접속일 업데이트 후");
			result="true";
		}
		return result;
		
	}
	
//	회원가입
//	@Override
//	public void addMember(MemberVO memberVO) throws Exception {
//		 memberDAO.insertMember(memberVO); 
//		
//	}


	// 이메일드롭박스를 이용한 회원가입시
	@Override
	public void addMember(Map<String, String> memberMap) throws Exception {
		memberDAO.insertMember(memberMap);

	}

//	ID중복검사(회원가입)
	@Override
	public String overlapped(String id) throws Exception {
		// TODO Auto-generated method stub
		return memberDAO.overlapped(id);
	}

//	이메일 중복검사(회원가입)

	@Override
	public String checkEmail(String email) throws Exception {
		return memberDAO.checkEmail(email);
	}

	@Override
	public MemberVO findId(Map<String, String> findMap) throws Exception {
		System.out.println("service의 findid");
		return memberDAO.selectForFindId(findMap);
	}

//	비밀번호 찾기
	@Override
	public MemberVO findPwd(Map<String, String> findMap) throws Exception {
		return memberDAO.selectForFindPwd(findMap);
	}

//	비밀번호 찾기 결과, 비밀번호 변경
	public void modPwd(Map<String, String> modMap) throws Exception {
		memberDAO.updatePwd(modMap);
	}

}
