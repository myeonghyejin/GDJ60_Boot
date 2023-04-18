package com.mhj.base.board;

import java.util.List;

public interface BoardDAO {
	
//	//글 개수
//	public Long getTotalCount() throws Exception;
//		
//	//글 리스트 조회
//	public List<BoardVO> getList() throws Exception;
//		
//	//글 하나 조회
//	public BoardVO getDetail(BoardVO boardVO) throws Exception;
		
	//글 작성
	public int setInsert(BoardVO boardVO) throws Exception;
		
//	//글 수정
//	public int setUpdate(BoardVO boardVO) throws Exception;
//		
//	//글 삭제
//	public int setDelete(BoardVO boardVO) throws Exception;

}
