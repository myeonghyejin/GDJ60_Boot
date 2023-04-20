package com.mhj.base.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mhj.base.board.BoardFileVO;
import com.mhj.base.board.BoardService;
import com.mhj.base.board.BoardVO;
import com.mhj.base.util.FileManager;
import com.mhj.base.util.Pagination;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QnaService implements BoardService {
	
	@Autowired
	private QnaDAO qnaDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload.qna}")
	private String path;

	@Override
	public List<BoardVO> getList(Pagination pagination) throws Exception {
		pagination.makeStartRow();
		pagination.pagination(qnaDAO.getTotalCount(pagination));
		return qnaDAO.getList(pagination);
	}

	@Override
	public BoardVO getDetail(BoardVO boardVO) throws Exception {
		return qnaDAO.getDetail(boardVO);
	}

	@Override
	public BoardFileVO getFileDetail(BoardFileVO boardFileVO) throws Exception {
		return qnaDAO.getFileDetail(boardFileVO);
	}

	@Override
	public int setInsert(BoardVO boardVO, MultipartFile [] multipartFiles) throws Exception {
		int result = qnaDAO.setInsert(boardVO);
		
		if(multipartFiles != null) {
			for(MultipartFile multipartFile : multipartFiles) {
				if(!multipartFile.isEmpty()) {
					String fileName = fileManager.saveFile(path, multipartFile);
					BoardFileVO boardFileVO = new BoardFileVO();
					boardFileVO.setFileName(fileName);
					boardFileVO.setOriginalName(multipartFile.getOriginalFilename());
					boardFileVO.setNum(boardVO.getNum());
					result = qnaDAO.setFileInsert(boardFileVO);
					log.error("Num ============> {}", boardFileVO.getNum());
				}
			}
		}
		result = qnaDAO.setRefUpdate(boardVO);
		
		return result;
	}

	@Override
	public int setUpdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setDelete(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
