package com.mhj.base.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	/** SELECT **/
	public List<MemberVO> getList() throws Exception {
		return memberDAO.getList();
	}
	
	public MemberVO getLogin(MemberVO memberVO) throws Exception {	
//		MemberVO result = memberDAO.getLogin(memberVO);
//		
//		if(result != null && memberVO.getPassword().equals(result.getPassword())) {
//			memberVO.setPassword(null);
//			memberVO.setRoleVO(result.getRoleVO());
//			return memberVO;
//		} else {
//			return null;
//		}
		
		memberVO = memberDAO.getLogin(memberVO);
		
		log.error("MemberVO => {}", memberVO);
		
		return memberVO;
		
	}
	
	/** INSERT **/
	public int setJoin(MemberVO memberVO) throws Exception {
		int result = memberDAO.setJoin(memberVO);
		result = memberDAO.setMemberRole(memberVO);
		return result;
	}

}
