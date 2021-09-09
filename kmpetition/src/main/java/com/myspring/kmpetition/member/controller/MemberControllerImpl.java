package com.myspring.kmpetition.member.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SimpleTimeZone;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.kmpetition.main.MainController;
import com.myspring.kmpetition.member.service.MemberService;
import com.myspring.kmpetition.member.vo.MemberVO;

@Controller("memberController")
@RequestMapping(value = "/member")
public class MemberControllerImpl extends MainController implements MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberVO memberVO;

//	로그인
//	1. id,pwd로 DB조회하여 회원인지 여부 확인
//	2. 회원이 맞으면 마지막 접속일과 오늘 날짜를 대조해서 휴면회원인지 검사
//	2. 휴면회원이 아니라면 마지막 접속일 업데이트, 정상로그인
	@Override
	@RequestMapping(value = "/login.do")
	public ModelAndView login(@RequestParam Map<String, String> loginMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("Member컨의 login");
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();

		if (loginMap.get("id").equals("admin") && loginMap.get("pwd").equals("admin")) {

			session.setAttribute("isLogOn", true);
			session.setAttribute("isAdmin", true);

			String message = "admin 계정으로 로그인";
			mav.addObject("adminMessage", message);

			mav.setViewName("redirect:/main/main.do");

		} else { // 1번 if(admin 구별)
			memberVO = memberService.login(loginMap);

			// 로그인한 정보가 있고 id가 null이 아니면
			if (memberVO != null && memberVO.getId() != null) {

				Date loginDate = memberVO.getLastlogin();
//				날짜 비교 용도
				int compare = checkLoginDate(loginDate);

//				compare 값을 통해 휴면계정인지 판단
				if (compare >= 0) {
					System.out.println("한 달 동안 한 번 이상 접속했음.");
//				해당 회원의 최종접속일을 오늘로 갱신
					memberService.updateDate(memberVO.getId());

//				로그인 정보 저장 후 메인 화면으로 리다이렉트
					session.setAttribute("isLogOn", true);
					session.setAttribute("memberInfo", memberVO);
					mav.setViewName("redirect:/main/main.do");

//					ID저장 처리 
//					체크박스에 체크가 없이 값이 넘어오면 null값으로 넘어온다. 
					String saveId = request.getParameter("saveId");
					
//					메서드를 통해 ID저장에 체크가 되어있었으면 쿠키에 저장
					checkSaveId(saveId, loginMap, response);
					

//				id, pwd 확인용(게터값이 null이면 에러남)
					System.out.println("로그인 : " + memberVO.getId() + ", " + memberVO.getPwd());

				} else { // 3번 if == 휴면계정 판단
					System.out.println("한 달 동안 접속하지 않음. 휴면계정.");
//				session.setAttribute("isSleepMember", true);
//				String message = "휴면계정입니다. 계정을 활성화해주세요.";
//				mav.addObject("message", message);
					mav.setViewName("/member/awakeForm");
				}

				// 로그인 정보가 없거나 id가 null이면(로그인 정보로 DB에서 조회가 안 되면)
			} else { // 2번 if (조회된 회원 정보가 없는 경우)
				String message = "아이디나 비밀번호가 틀립니다. 다시 로그인해주세요.";
				mav.addObject("message", message);
				mav.setViewName("/member/loginForm");
			}
		}
		return mav;
	}

//	awakeForm에서 활성화할 계정 정보를 입력했을 때
	@Override
	@RequestMapping(value = "/awakeMember.do", method = RequestMethod.POST)
	public ResponseEntity awakeMember(@ModelAttribute("memberVO") MemberVO memberVO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		System.out.println("id : " + memberVO.getId());
		System.out.println("pwd : " + memberVO.getPwd());
		System.out.println("email : " + memberVO.getEmail());
		System.out.println("name : " + memberVO.getName());
		try {
			String result = memberService.awakeMember(memberVO);
			if (result.equals("true")) {
				message = "<script>";
				message += " alert('계정을 활성화했습니다. 로그인 화면으로 이동합니다.');";
				message += " location.href='" + request.getContextPath() + "/member/loginForm.do';";
				message += " </script>";
			} else {
				message = "<script>";
				message += " alert('조회된 회원이 없습니다. 다시 시도해 주세요.');";
				message += " location.href='" + request.getContextPath() + "/member/awakeForm.do';";
				message += " </script>";
			}
		} catch (Exception e) {

			message = "<script>";
			message += " alert('작업 중 오류가 발생했습니다. 다시 시도해 주세요');";
			message += " location.href='" + request.getContextPath() + "/member/awakeForm.do';";
			message += " </script>";
			e.printStackTrace();
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}

	// 로그아웃
	@Override
	@RequestMapping(value = "/logout.do")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		session.removeAttribute("isLogOn");
		session.removeAttribute("isAdmin");
		session.setAttribute("isLogOn", false);
		session.removeAttribute("memberInfo");
		mav.setViewName("redirect:/main/main.do");
		return mav;
	}

//	회원가입. 이메일 드롭박스 테스트
	@Override
	@RequestMapping(value = "/addMember.do", method = RequestMethod.POST)
	public ResponseEntity addMember(@RequestParam Map<String, String> memberMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		애너테이션으로 Form에서 온 정보는 _memberVO에 할당, 이 _memberVO를 Model 객체에 "memberVO"로 set한 상태
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

//		회원가입 시 쪼개져들어온 이메일값을 하나로 합쳐서 다시 지정
		String email_id = memberMap.get("email_id");
		String email_addr = memberMap.get("email_addr");
		String email = email_id + "@" + email_addr;
		memberMap.remove("email_id");
		memberMap.remove("email_addr");
		memberMap.put("email", email);

		try {
			memberService.addMember(memberMap);
			message = "<script>";
			message += " alert('회원 가입을 마쳤습니다.로그인창으로 이동합니다.');";
			message += " location.href='" + request.getContextPath() + "/member/loginForm.do';";
			message += " </script>";

		} catch (Exception e) {
			message = "<script>";
			message += " alert('작업 중 오류가 발생했습니다. 다시 시도해 주세요');";
			message += " location.href='" + request.getContextPath() + "/member/memberForm.do';";
			message += " </script>";
			e.printStackTrace();
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}

//	ID중복검사 (회원가입시)
	@Override
	@RequestMapping(value = "/overlapped.do", method = RequestMethod.POST)
	public ResponseEntity overlapped(@RequestParam String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("Member컨의 overlapped");
		ResponseEntity resEntity = null;
		String result = memberService.overlapped(id);
		resEntity = new ResponseEntity(result, HttpStatus.OK);
		return resEntity;

	}

//	이메일중복검사 (회원가입시)
	@RequestMapping(value = "/checkEmail.do", method = RequestMethod.POST)
	public ResponseEntity checkEmail(@RequestParam String email, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("Member컨의 checkEmail");
		ResponseEntity resEntity = null;
		String result = memberService.checkEmail(email);
		resEntity = new ResponseEntity(result, HttpStatus.OK);
		return resEntity;

	}

//	ID찾기
	@RequestMapping(value = "/findId.do", method = RequestMethod.POST)
	public ModelAndView findId(@RequestParam Map<String, String> findMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		System.out.println("member컨의 findid");
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		memberVO = memberService.findId(findMap);
//		System.out.println("member컨의 findid의 service 이후");
		String findId = null;
		if (memberVO != null) {
			findId = memberVO.getId();

		}
		System.out.println(findId);
		mav.addObject("findId", findId);
		return mav;
	}

//	비밀번호 찾기
	@RequestMapping(value = "/findPwd.do", method = RequestMethod.POST)
	public ModelAndView findPwd(@RequestParam Map<String, String> findMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		memberVO = memberService.findPwd(findMap);
		String id = findMap.get("id");
		mav.addObject("id", id);
		String result = null;
		if (memberVO != null) {
			result = "true";
		}
		mav.addObject("result", result);
		return mav;
	}

	@RequestMapping(value = "/modPwd.do", method = RequestMethod.POST)
	public ResponseEntity modPwd(@RequestParam Map<String, String> modMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			memberService.modPwd(modMap);
			message = "<script>";
			message += " alert('비밀번호 변경이 완료되었습니다. 로그인창으로 이동합니다.');";
			message += " location.href='" + request.getContextPath() + "/member/loginForm.do';";
			message += " </script>";

		} catch (Exception e) {
			message = "<script>";
			message += " alert('작업 중 오류가 발생했습니다. 다시 시도해 주세요');";
			message += " location.href='" + request.getContextPath() + "/member/findPwdForm.do';";
			message += " </script>";
			e.printStackTrace();
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}

	@Override
	public int checkLoginDate(Date loginDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

//		1달 전 날짜 구하기
		Calendar cal = Calendar.getInstance(new SimpleTimeZone(0x1ee6280, "KST"));
		cal.add(Calendar.MONTH, -1);
		Date monthAgo = cal.getTime();

//			System.out.println("한 달 전 날짜:" + sdf.format(monthAgo));
		System.out.println("회원 최종접속일 : " + loginDate);
//		날짜 비교 용도의 변수
		int compare = loginDate.compareTo(monthAgo);
		return compare;
	}

	@Override
	public void checkSaveId(String saveId, Map loginMap, HttpServletResponse response) throws Exception {

		if (saveId != null) {
			Cookie c = new Cookie("saveId", (String) loginMap.get("id"));
			c.setMaxAge(60 * 60 * 24 * 7); // 쿠키 유효기간 7일
			response.addCookie(c);
		} else {
			Cookie c = new Cookie("saveId", (String) loginMap.get("id"));
			c.setMaxAge(0); // 쿠키 유효기간 7일
			response.addCookie(c);
		}

	}

	/*-------------------- 파일 업로드 테스트용--------------------*/
	private static final String CURR_FILE_REPO_PATH = "C:\\team_file";

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView upload(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		Map map = new HashMap();
		Enumeration enu = multipartRequest.getParameterNames();

		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			map.put(name, value);
		}

		List fileList = fileProcess(multipartRequest);
		map.put("fileList", fileList);

		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map);
		mav.setViewName("/main/result");
		return mav;
	}

	private List<String> fileProcess(MultipartHttpServletRequest multipartRequest) throws Exception {
		List<String> fileList = new ArrayList<String>();
		Iterator<String> fileNames = multipartRequest.getFileNames();

		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String originalFileName = mFile.getOriginalFilename();
			fileList.add(originalFileName);
			File file = new File(CURR_FILE_REPO_PATH + "\\" + fileName);
			if (mFile.getSize() != 0) {
				if (!file.exists()) {
					if (file.getParentFile().mkdir()) {
						file.createNewFile();
					}
				}
				mFile.transferTo(new File(CURR_FILE_REPO_PATH + "\\" + originalFileName));
			}
		}
		return fileList;

	}
}
