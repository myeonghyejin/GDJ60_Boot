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
	
	//@Test
	void setInsertTest() throws Exception {
		
		for(int i=0; i < 120; i++) {
			BoardVO boardVO = new NoticeVO();
			
			boardVO.setTitle("아기 토끼"+i);
			boardVO.setContents(i+"만큼 귀여워요");
			boardVO.setWriter("엄마 토끼");
			
			int result = noticeDAO.setInsert(boardVO);
			
			if(i % 10 == 0) {
				Thread.sleep(500);
			}
		}
		System.out.println("종료");
	}
	
//	void setUpdateTest() throws Exception {
//		BoardVO boardVO = new NoticeVO();
//		
//		boardVO.setNum(1);
//		boardVO.setContents("cute");
//	}

}
