package com.mhj.base.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	//Password가 일치하는지 검증하는 메서드
	public boolean memberCheck(MemberVO memberVO, BindingResult bindingResult) throws Exception {
		boolean check = false;
		//false : error 없음, 검증 성공
		//true : error 있음, 검증 실패
		
		//1. Annotation의 검증 결과
		check = bindingResult.hasErrors();
		
		//2. Password 일치 검증
		if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())) {
			check = true;
			bindingResult.rejectValue("passwordCheck", "member.password.notEqual");
		}
		
		//3. ID 중복 검사
		MemberVO idCheck = memberDAO.idDuplicateCheck(memberVO);
		if(idCheck != null) {
			check = true;
			bindingResult.rejectValue("userName", "member.id.duplicate");
		}
		
		return check;
	}
	
	public int setJoin(MemberVO memberVO)throws Exception{
		memberVO.setEnabled(true);
		int result = memberDAO.setJoin(memberVO);
		Map<String, Object> map = new HashMap<>();
		map.put("userName", memberVO.getUserName());
		map.put("num", 3);
		result = memberDAO.setMemberRole(map);
		
		return result;
	}
	
	public MemberVO idDuplicateCheck(MemberVO memberVO) throws Exception {
		return memberDAO.idDuplicateCheck(memberVO);
	}
	
	public MemberVO getLogin(MemberVO memberVO) throws Exception {
		return memberDAO.getLogin(memberVO);
	}
	
	public int setLastTime(MemberVO memberVO) throws Exception {
		return memberDAO.setLastTime(memberVO);
	}
	
	public List<MemberVO> getBirth() throws Exception {
		return memberDAO.getBirth();
	}

}
