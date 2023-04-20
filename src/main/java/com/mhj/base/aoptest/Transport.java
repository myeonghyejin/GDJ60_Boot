package com.mhj.base.aoptest;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.mhj.base.board.BoardFileVO;
import com.mhj.base.util.Pagination;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Transport {
	
	public int useSubway(BoardFileVO boardFileVO) throws Exception {
//		Random random = new Random();
//		int num = random.nextInt(2);
//		if(num == 0) {
//			this new Exception();
//		}
		log.error("지하철 이용");
		return 7;
	}
	
	public String useBus(Pagination pagination) {
		log.error("버스 이용");
		return "BUS";
	}
	
	public void takeWalk() {
		log.error("걸어 가요~");
	}

}