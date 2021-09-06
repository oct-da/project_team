package com.myspring.kmpetition.member.controller;

import java.util.Map;

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
	@Override
	@RequestMapping(value = "/login.do")
	public ModelAndView login(@RequestParam Map<String, String> loginMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("Member컨의 login");
		ModelAndView mav = new ModelAndView();
		memberVO = memberService.login(loginMap);

//		서비스 대신 임시코드
//		String id=loginMap.get("id");
//		String pwd=loginMap.get("pwd");
//		memberVO.setId(id);
//		memberVO.setPwd(pwd);
//		임시코드 끝

		// 로그인한 정보가 있고 id가 null이 아니면
		if (memberVO != null && memberVO.getId() != null) {
			HttpSession session = request.getSession();
			session.setAttribute("isLogOn", true);
			session.setAttribute("memberInfo", memberVO);
			mav.setViewName("redirect:/main/main.do");

//			id, pwd 확인용(게터값이 null이면 에러남)
			String id = memberVO.getId();
			String pwd = memberVO.getPwd();
			System.out.println(id + ", " + pwd);

			// 로그인 정보가 없거나 id가 null이면(로그인 정보로 DB에서 조회가 안 되면)
		} else {
			String message = "아이디나 비밀번호가 틀립니다. 다시 로그인해주세요.";
			mav.addObject("message", message);
			mav.setViewName("/member/loginForm");
		}
		return mav;
	}

//	로그아웃
	@Override
	@RequestMapping(value = "/logout.do")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		session.removeAttribute("isLogOn");
		session.setAttribute("isLogOn", false);
		session.removeAttribute("memberInfo");
		mav.setViewName("redirect:/main/main.do");
		return mav;
	}

	/*
	 * // 회원가입
	 * 
	 * @Override
	 * 
	 * @RequestMapping(value = "/addMember.do", method = RequestMethod.POST) public
	 * ResponseEntity addMember(@ModelAttribute("memberVO") MemberVO _memberVO,
	 * HttpServletRequest request, HttpServletResponse response) throws Exception {
	 * // 애너테이션으로 Form에서 온 정보는 _memberVO에 할당, 이 _memberVO를 Model 객체에 "memberVO"로
	 * set한 상태 response.setContentType("text/html; charset=UTF-8");
	 * request.setCharacterEncoding("utf-8"); String message = null; ResponseEntity
	 * resEntity = null; HttpHeaders responseHeaders = new HttpHeaders();
	 * responseHeaders.add("Content-Type", "text/html; charset=utf-8"); try {
	 * memberService.addMember(_memberVO); message = "<script>"; message +=
	 * " alert('회원 가입을 마쳤습니다.로그인창으로 이동합니다.');"; message += " location.href='" +
	 * request.getContextPath() + "/member/loginForm.do';"; message += " </script>";
	 * 
	 * } catch (Exception e) { message = "<script>"; message +=
	 * " alert('작업 중 오류가 발생했습니다. 다시 시도해 주세요');"; message += " location.href='" +
	 * request.getContextPath() + "/member/memberForm.do';"; message +=
	 * " </script>"; e.printStackTrace(); } resEntity = new ResponseEntity(message,
	 * responseHeaders, HttpStatus.OK); return resEntity; }
	 */

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

		/*
		 * 확인용 출력 System.out.println(memberMap.get("email"));
		 * System.out.println(memberMap.get("id"));
		 * System.out.println(memberMap.get("pwd"));
		 * System.out.println(memberMap.get("name"));
		 * System.out.println(memberMap.get("phone"));
		 */

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
//		public ModelAndView findId(@RequestParam MemberVO member, HttpServletRequest request, HttpServletResponse response)
//				throws Exception {
		System.out.println("member컨의 findid");
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		MemberVO memberVO = memberService.findId(findMap);
//		MemberVO memberVO = memberService.findId(member);
		System.out.println("member컨의 findid의 service 이후");
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
		MemberVO memberVO = memberService.findPwd(findMap);
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

	/*-------------------- 파일 업로드/다운로드 실험용--------------------
	private static final String CURR_IMAGE_REPO_PATH = "C:\\shopping\\file_repo";

	protected List<ImageFileVO> upload(MultipartHttpServletRequest multipartRequest) throws Exception {
		List<ImageFileVO> fileList = new ArrayList<ImageFileVO>();
		Iterator<String> fileNames = multipartRequest.getFileNames();
		while (fileNames.hasNext()) {
			ImageFileVO imageFileVO = new ImageFileVO();
			String fileName = fileNames.next();
			imageFileVO.setFileType(fileName);
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String originalFileName = mFile.getOriginalFilename();
			imageFileVO.setFileName(originalFileName);
			fileList.add(imageFileVO);

			File file = new File(CURR_IMAGE_REPO_PATH + "\\" + fileName);
			if (mFile.getSize() != 0) { // File Null Check
				if (!file.exists()) { // 경로상에 파일이 존재하지 않을 경우
					if (file.getParentFile().mkdirs()) { // 경로에 해당하는 디렉토리들을 생성
						file.createNewFile(); // 이후 파일 생성
					}
				}
				mFile.transferTo(new File(CURR_IMAGE_REPO_PATH + "\\" + "temp" + "\\" + originalFileName)); // 임시로 저장된
																											// multipartFile을
																											// 실제 파일로 전송
			}
		}
		return fileList;
	}

	private void deleteFile(String fileName) {
		File file = new File(CURR_IMAGE_REPO_PATH + "\\" + fileName);
		try {
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/

}
