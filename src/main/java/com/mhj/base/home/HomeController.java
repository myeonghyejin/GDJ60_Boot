package com.mhj.base.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.mhj.base.aoptest.Card;
import com.mhj.base.aoptest.Transport;
import com.mhj.base.board.BoardFileVO;
import com.mhj.base.util.Pagination;

@Controller
public class HomeController {
	
	@Autowired
	private Transport transport;
	
	@Autowired
	private Card card;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/use")
	public void use() throws Exception {
		Pagination pagination = new Pagination();
		pagination.setCondition("Bus Title");
		transport.useBus(pagination);
		
		BoardFileVO boardFileVO = new BoardFileVO();
		boardFileVO.setFileName("Subway File");
		
		transport.useBus(pagination);

		transport.useSubway(boardFileVO);
		
		transport.takeWalk();
	}

}