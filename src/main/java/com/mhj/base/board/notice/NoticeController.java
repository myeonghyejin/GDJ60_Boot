package com.mhj.base.board.notice;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mhj.base.board.BoardFileVO;
import com.mhj.base.board.BoardVO;
import com.mhj.base.member.MemberService;
import com.mhj.base.member.MemberVO;
import com.mhj.base.util.Pagination;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice/*")
@Slf4j
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private MemberService memberService;
	
	//각 메서드가 실행되기 전에 이것부터 실행됨
	@ModelAttribute("board")
	public String getBoard() {
		return "notice";
	}
	
	/** SELECT **/
	@GetMapping("list")
//	@RequestMapping(value = "list", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getList(Pagination pagination, ModelAndView mv) throws Exception {
//		System.out.println(pagination.getCondition());
//		System.out.println(pagination.getSearch());
		
		log.info("condition : {}", pagination.getCondition());
		log.info("search : {}", pagination.getSearch());
		
		List<BoardVO> ar = noticeService.getList(pagination);
		List<MemberVO> ar2 = memberService.getBirth();
		
		mv.addObject("list", ar);
		mv.addObject("member", ar2);
		mv.setViewName("board/list");
		
		return mv;
	}
	
	@GetMapping("detail")
	public ModelAndView getDetail(NoticeVO noticeVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		BoardVO boardVO = noticeService.getDetail(noticeVO);
		
		mv.addObject("boardVO", boardVO);
		mv.setViewName("board/detail");
		
		return mv;
	}
	
	@GetMapping("fileDownload")
	public ModelAndView getFileDownload(BoardFileVO boardFileVO) throws Exception {
		boardFileVO = noticeService.getFileDetail(boardFileVO);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardFileVO", boardFileVO);
		//FileManager에서 꺼내 오는 이름과 같아야 함
		mv.setViewName("fileManager");
		//AbstractView를 상속 받은 상태에서만 가능
		
		return mv;
	}
	
	/** INSERT **/
	@GetMapping("add")
	public ModelAndView setInsert(@ModelAttribute BoardVO boardVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/add");
		//mv.addObject(new NoticeVO());//속성명은 클래스명의 첫 글자를 소문자로 바꾼 것
		return mv;
	}
	
	@PostMapping("add")
	public ModelAndView setInsert(@Valid BoardVO boardVO, BindingResult bindingResult,  MultipartFile [] boardFiles) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			log.warn("=========== 검증 실패 ===========");
			mv.setViewName("board/add");
			return mv;
		}
		
		for(MultipartFile multipartFile : boardFiles) {
			log.info("Original Name : {} Size : {}", multipartFile.getOriginalFilename(), multipartFile.getSize());
		}
		
		int result = noticeService.setInsert(boardVO, boardFiles);
		
		mv.setViewName("redirect:./list");
		return mv;
	}
	
	/** DELETE **/
	@PostMapping("delete")
	public ModelAndView setDelete(BoardVO boardVO, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("common/result");
		
		int result = noticeService.setDelete(boardVO, session);
		
		String message = "삭제되지 않았습니다.";
		
		if(result > 0) {
			message = "삭제되었습니다.";
		}
		
		mv.addObject("result", message);
		mv.addObject("url", "./list");
		
		return mv;
	}

}
