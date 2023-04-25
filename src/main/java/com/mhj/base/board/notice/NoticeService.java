package com.mhj.base.board.notice;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mhj.base.board.BoardFileVO;
import com.mhj.base.board.BoardService;
import com.mhj.base.board.BoardVO;
import com.mhj.base.util.FileManager;
import com.mhj.base.util.Pagination;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class NoticeService implements BoardService {
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload.notice}")
	private String path;

	@Override
	public List<BoardVO> getList(Pagination pagination) throws Exception {
		pagination.makeStartRow();
		pagination.pagination(noticeDAO.getTotalCount(pagination));
		return noticeDAO.getList(pagination);
	}

	@Override
	public BoardVO getDetail(BoardVO boardVO) throws Exception {
		return noticeDAO.getDetail(boardVO);
	}
	
	@Override
	public BoardFileVO getFileDetail(BoardFileVO boardFileVO) throws Exception {
		return noticeDAO.getFileDetail(boardFileVO);
	}

	@Override
	public int setInsert(BoardVO boardVO, MultipartFile [] multipartFiles) throws Exception {		
		int result = noticeDAO.setInsert(boardVO);
		log.error("Num ============> {}", boardVO.getNum());
		
//		Random random = new Random();
//		int num = random.nextInt(1);
//		
//		if(num == 0) {
//			throw new Exception();
//		}
		
		if(multipartFiles != null) {
			for(MultipartFile multipartFile : multipartFiles) {
				if(!multipartFile.isEmpty()) {
					String fileName = fileManager.saveFile(path, multipartFile);
					BoardFileVO boardFileVO = new BoardFileVO();
					boardFileVO.setFileName(fileName);
					boardFileVO.setOriginalName(multipartFile.getOriginalFilename());
					boardFileVO.setNum(boardVO.getNum());
					result = noticeDAO.setFileInsert(boardFileVO);
				}
			}
		}
		return result;
	}

	@Override
	public int setUpdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setDelete(BoardVO boardVO, HttpSession session) throws Exception {
		List<BoardFileVO> ar = noticeDAO.getFileList(boardVO);
		
		int result = noticeDAO.setDelete(boardVO);
		
		if(result > 0) {
			String realPath = session.getServletContext().getRealPath("production/uplodate/notice/");
			
			for(BoardFileVO boardFileVO : ar) {
				boolean check = fileManager.fileDelete(realPath, boardFileVO.getFileName());
			}
		}
		
		return result;
	}

}
