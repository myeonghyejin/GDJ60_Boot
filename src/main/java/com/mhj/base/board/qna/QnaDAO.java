package com.mhj.base.board.qna;

import org.apache.ibatis.annotations.Mapper;

import com.mhj.base.board.BoardDAO;
import com.mhj.base.board.BoardVO;

@Mapper
public interface QnaDAO extends BoardDAO {
	
	public int setRefUpdate(BoardVO boardVO) throws Exception;

}
