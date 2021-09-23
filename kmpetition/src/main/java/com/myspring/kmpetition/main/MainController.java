package com.myspring.kmpetition.main;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.kmpetition.board.vo.UploadVO;

// 컨텍스트만으로 접속 시 메인으로 보내주는 컨트롤러. 
// 모든 컨트롤러에 상속되어 viewForm의 기능을 더해주는 컨트롤러.

@Controller("mainController")
//@EnableAspectJAutoProxy  넣어야하나?
//@RequestMapping(value = "/main")
public class MainController {
	private final String Path = "C:\\test";

	@RequestMapping(value = "/main/main.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Main컨의 main");
		HttpSession session;
		ModelAndView mav = new ModelAndView();
		String viewName = "/main/main";
		mav.setViewName(viewName);
		return mav;
	}

//	contextPath만 입력하고 접속했을 때 자동으로 메인 화면으로 리다이렉트 하기 위한 메서드
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void home(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Main컨의 home");
		response.setContentType("text/html; charset=utf-8");
		response.sendRedirect("main/main.do");

	}

	@RequestMapping(value = { "/*.do" }, method = { RequestMethod.POST, RequestMethod.GET })
	protected ModelAndView viewForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Main컨의 viewForm - /*.do");
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}

	// 파일 업로드 메소드
	// 파일 이름 형식 'articleNO'_'fileName'.'확장자'
	// 중복파일 이름 형식 'articleNO'_'fileName'('num').'확장자'
	public List<UploadVO> uploadFile(int articleNO, List<String> nameList, MultipartHttpServletRequest request) {

		if (nameList == null) {
			nameList = new ArrayList<String>();
		}

		List<UploadVO> uploadList = new ArrayList<UploadVO>();

		// 첨부 파일 이름을 iterator 형식으로 가져오기
		Iterator<String> fileNames = request.getFileNames();

		// 문의 글 번호에 해당하는 경로 생성
		if (!new File(Path).exists()) {
			new File(Path).mkdirs();
		}

		// 모든 첨부파일에 대해 저장작업
		while (fileNames.hasNext()) {

			// request로부터 파일 이름과 첨부파일 가져오기
			String tagName = fileNames.next();

			System.out.println("파일이름? " + tagName);

			MultipartFile file = request.getFile(tagName);
			String fileName = file.getOriginalFilename();
			int dot = fileName.lastIndexOf("."); // 마지막 .의 인덱스
			int end = fileName.length(); // 파일이름의 총 길이
			String saveFileName = articleNO + "_" + fileName; // 파일의 저장될 이름(글번호_파일이름)
			String name = fileName.substring(0, dot); // 이름은 마지막. 전까지(확장자 제외)
			String extension = fileName.substring(dot, end); // 확장자이름 (예: .txt)

			// 중복된 파일 이름 변경하기
			int i = 1;

			while (nameList.contains(saveFileName)) {
				i += 1;
				saveFileName = articleNO + "_" + name + "(" + i + ")" + extension;
			}

			// 확정된 파일 이름 저장
			nameList.add(saveFileName);

			// 파일 생성위치 지정
			File target = new File(Path, saveFileName);

			// 문의 글 경로에 파일 생성
			try {
				FileCopyUtils.copy(file.getBytes(), target);
			} catch (Exception e) {
				e.printStackTrace();
			}

			UploadVO uploadVO = new UploadVO();
			uploadVO.setArticleNO(articleNO);
			uploadVO.setUploadfile(saveFileName);
			uploadList.add(uploadVO);
		}
		return uploadList;
	}

//	첨부파일 다운로드
	@RequestMapping(value = "/downloadFile.do")
	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent, String fileName) {
		System.out.println("Main의 downloadFile 진입");
//		파일 경로
		Resource resource = new FileSystemResource(Path + "\\" + fileName);
		if (!resource.exists())
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);

//		다운로드 이름
		String resourceName = resource.getFilename();

		HttpHeaders headers = new HttpHeaders();
		try { // 한글파일명 인코딩
			String downloadName = null;
			if (userAgent.contains("Trident")) { // internet explore 일 때
				downloadName = URLEncoder.encode(resourceName, "utf-8");
			} else {
				downloadName = new String(resourceName.getBytes("utf-8"), "iso-8859-1");
			}
			// 헤더 설정
			headers.add("content-disposition", "attachment; filename=" + downloadName);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("에러발생");
		}
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}

//	파일 삭제
	public List<UploadVO> deleteFile(List<String> fileList) {

		List<UploadVO> deleteList = new ArrayList<UploadVO>();

		for (String fileName : fileList) {

			File target = new File(Path, fileName);

			try {

				target.delete();
				UploadVO upload = new UploadVO();
				int articleNO = Integer.parseInt(fileName.substring(0, fileName.indexOf('_')));
				upload.setArticleNO(articleNO);
				upload.setUploadfile(fileName);
				deleteList.add(upload);
			}

			catch (Exception e) {

				e.printStackTrace();
			}
		}

		return deleteList;
	}

}