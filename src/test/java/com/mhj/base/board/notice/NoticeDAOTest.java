package com.mhj.base.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mhj.base.board.BoardVO;

@SpringBootTest
class NoticeDAOTest {

	@Autowired
	private NoticeDAO noticeDAO;
	
	@Test
	void setInsertTest() throws Exception {
		BoardVO boardVO = new NoticeVO();
		
		boardVO.setTitle("bunny");
		boardVO.setContents("cute");
		boardVO.setWriter("mom");
		
		int result = noticeDAO.setInsert(boardVO);
		
		assertEquals(1, result);
		
	}

}
