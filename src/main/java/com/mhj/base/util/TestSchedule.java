package com.mhj.base.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mhj.base.board.notice.NoticeDAO;
import com.mhj.base.board.notice.NoticeVO;
import com.mhj.base.member.MemberDAO;
import com.mhj.base.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TestSchedule {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Autowired
	private MailManager mailManager;
	
	@Scheduled(cron = "0 0 0 * * *")
	public void test() throws Exception {
		
		List<MemberVO> ar = memberDAO.getBirth();
		
		for(MemberVO memberVO : ar) {
			mailManager.send(memberVO.getEmail(), "생일 축하합니다.", "<h1>당신의 생일을 축하합니다.</h1>");
		}
		
		//생일자 공지
//		List<MemberVO> ar = memberDAO.getBirth();
//		
//		StringBuffer sb = new StringBuffer();
//		sb.append("오늘은 ");
//		
//		for(MemberVO memberVO : ar) {
//			sb.append(memberVO.getName());
//			sb.append(", ");
//		}
//		
//		sb.append("생일입니다.");
//		
//		NoticeVO noticeVO = new NoticeVO();
//		
//		noticeVO.setTitle("생일 축하합니다.");
//		noticeVO.setWriter("관리자");
//		noticeVO.setContents(sb.toString());
//		
//		noticeDAO.setInsert(noticeVO);
		
		//3일 지나면 Enabled 비활성화 (선생님이 풀이해 준 방법)
//		MemberVO memberVO = new MemberVO();
//		memberDAO.setEnabled(memberVO);
		
		//3일 지나면 Enabled 비활성화
//		List<MemberVO> ar = memberDAO.getMembers();
//		for (MemberVO memberVO : ar) {
//			log.error("================ ID : {} =================", memberVO.getUserName());
//			int result = memberDAO.setLastTimeCheck(memberVO);
//			if(result > 3) {
//				memberDAO.setEnabled(memberVO);
//			}
//		}
	}

}
