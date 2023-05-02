package com.mhj.base.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.mhj.base.util.MailManager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class MemberService implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private MailManager mailManager;

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
//		memberVO.setEnabled(true);
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		int result = memberDAO.setJoin(memberVO);
		Map<String, Object> map = new HashMap<>();
		map.put("username", memberVO.getUsername());
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
	
	public int findPassword(MemberVO memberVO) throws Exception {
		memberVO = memberDAO.findPassword(memberVO);
		
		if(memberVO != null) {
			log.error("Username ::: {}", memberVO.getUsername());
			log.error("Email ::: {}", memberVO.getEmail());
			
			StringBuffer sb = new StringBuffer();
			Random random = new Random();
			
		}
		
		return 0;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.error("=========== Spring Security Login ===========");
		log.error("=========== {} ===========", username);
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		try {
			memberVO = memberDAO.getLogin(memberVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberVO;
	}

}
