package com.mhj.base.board;

import java.util.List;

import com.mhj.base.util.Pagination;

public interface BoardDAO {
	
	//글 개수
	public Long getTotalCount(Pagination pagination) throws Exception;
		
	//글 리스트 조회
	public List<BoardVO> getList(Pagination pagination) throws Exception;
	
	//파일 리스트 조회
	public List<BoardFileVO> getFileList(BoardVO boardVO) throws Exception;
		
	//글 하나 조회
	public BoardVO getDetail(BoardVO boardVO) throws Exception;
		
	//파일 조회
	public BoardFileVO getFileDetail(BoardFileVO boardFileVO) throws Exception;
	
	//글 작성
	public int setInsert(BoardVO boardVO) throws Exception;
	
	//파일 업로드
	public int setFileInsert(BoardFileVO boardFileVO) throws Exception;
		
	//글 수정
	public int setUpdate(BoardVO boardVO) throws Exception;
		
	//글 삭제
	public int setDelete(BoardVO boardVO) throws Exception;

}
