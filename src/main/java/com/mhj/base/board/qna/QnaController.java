package com.mhj.base.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mhj.base.board.BoardFileVO;
import com.mhj.base.board.BoardVO;
import com.mhj.base.util.Pagination;

@Controller
@RequestMapping("/qna/*")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "qna";
	}
	
	/** SELECT **/
	@GetMapping("list")
	public ModelAndView getList(Pagination pagination) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		List<BoardVO> ar = qnaService.getList(pagination);
		
		mv.addObject("list", ar);
		mv.setViewName("board/list");
		
		return mv;
	}
	
	@GetMapping("detail")
	public ModelAndView getDetail(QnaVO qnaVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		BoardVO boardVO = qnaService.getDetail(qnaVO);
		
		mv.addObject("boardVO", boardVO);
		mv.setViewName("board/detail");
		
		return mv;
	}
	
	@GetMapping("fileDownload")
	public ModelAndView getFileDownload(BoardFileVO boardFileVO) throws Exception {
		boardFileVO = qnaService.getFileDetail(boardFileVO);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardFileVO", boardFileVO);
		mv.setViewName("fileManager");
		
		return mv;
	}
	
	/** INSERT **/
	@GetMapping("add")
	public ModelAndView getInsert() throws Exception {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("board/add");
		
		return mv;
	}
	
	@PostMapping("add")
	public ModelAndView getInsert(QnaVO qnaVO, MultipartFile [] boardFiles) throws Exception {
		int result = qnaService.setInsert(qnaVO, boardFiles);
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("redirect:./list");
				
		return mv;
	}

}
