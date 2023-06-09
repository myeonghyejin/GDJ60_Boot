package com.mhj.base.member;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
	private String adminKey;
	
	@GetMapping("delete")
	public String delete() throws Exception {
		MemberVO memberVO = (MemberVO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//회원가입 방법 구분
		this.kakaoDelete(memberVO);
		
		return "redirect:./logout";
		
	}
	
	private void kakaoDelete(MemberVO memberVO) {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK "+adminKey);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("target_id_type", "user_id");
		params.add("target_id", memberVO.getAttributes().get("id").toString());
		
		HttpEntity<MultiValueMap<String, String>> req= new HttpEntity<>(params, headers);
		
		String id=restTemplate.postForObject("https://kapi.kakao.com/v1/user/unlink", req, String.class);
		log.error("Delete {} ::: ", id);
	}
	
	@GetMapping("findPassword")
	public ModelAndView findPassword() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/findPassword");
		return mv;
	}
	
	@PostMapping
	public ModelAndView findPassword(MemberVO memberVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		
//		memberVO = memberService.idEmailDuplicateCheck(memberVO);
		
		return mv;
	}
	
	@GetMapping("info")
	public void info(HttpSession session) {
		String pw = "1234";
		
		MemberVO memberVO = (MemberVO)memberService.loadUserByUsername("user1");
		
		log.error("{} ::::: ", memberVO.getPassword());
		log.error("{} ::::: ", passwordEncoder.encode(pw));
		log.error("{} ::::: ", memberVO.getPassword().equals(passwordEncoder.encode(pw)));
		
		boolean check = passwordEncoder.matches(pw, memberVO.getPassword());
		log.error("{} ::::: ", check);
		
		log.error("============= Login Info =============");
		//SPRING_SECURITY_CONTEXT
//		Enumeration<String> names = session.getAttributeNames();
//		while(names.hasMoreElements()) {
//			log.error("==== {} ====", names.nextElement());
//		}
//		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
//		log.error("========= {} =========", obj);
//		SecurityContextImpl contextImpl = (SecurityContextImpl)obj;
//		Authentication authentication = contextImpl.getAuthentication();
//
//		log.error("====== {} ======", obj);
//		log.error("====== Name : {} ======", authentication.getName());
//		log.error("====== Detail : {} ======", authentication.getDetails());
//		log.error("====== Principal : {} ======", authentication.getPrincipal());
	}
	
	@GetMapping("admin")
	public void getAdmin()throws Exception{}
	
	@GetMapping("mypage")
	public void getMyPage()throws Exception{}
	
	@GetMapping("idDuplicateCheck")
	@ResponseBody
	public boolean idDuplicateCheck(MemberVO memberVO)throws Exception{
		log.debug("================ ID 중복 체크 =================");
		boolean check=false;
		
		memberVO = memberService.idDuplicateCheck(memberVO);
		
		if(memberVO == null) {
			check=true;
		}
		
		return check;
	}
	
	@GetMapping("join")
	public ModelAndView setJoin(@ModelAttribute MemberVO memberVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/join");
		return mv;
	}
	
	@PostMapping("join")
	public ModelAndView setJoin(@Valid MemberVO memberVO, BindingResult bindingResult) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		boolean check = memberService.memberCheck(memberVO, bindingResult);
		
		if(check) {
			log.warn("=========== 검증 실패 ===========");
			mv.setViewName("member/join");
			return mv;
		}
		
		int result = memberService.setJoin(memberVO);
		
		mv.setViewName("redirect:../");
		
		return mv;
		
	}
	
//	@GetMapping("logout")
//	public ModelAndView getLogout(HttpSession session) throws Exception {
//		ModelAndView mv = new ModelAndView();
//		
//		MemberVO memberVO = (MemberVO)session.getAttribute("member");
//		
//		int result = memberService.setLastTime(memberVO);
//		
//		session.invalidate();
//		
//		mv.setViewName("redirect:../");
//		
//		return mv;
//	}
	
//	@GetMapping("socialLogout")
//	public ModelAndView getSocialLogout(HttpSession session) throws Exception {
//		ModelAndView mv = new ModelAndView();
//		
//		mv.setViewName("redirect:../");
//		
//		return mv;
//	}
	
	@GetMapping("login")
	public ModelAndView getLogin(HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		
		if(obj == null) {
			mv.setViewName("member/login");
		} else {
			mv.setViewName("redirect:/");
		}
		
		return mv;
	}
	
//	@PostMapping
//	public ModelAndView getLogin(MemberVO memberVO, HttpSession session)throws Exception{
//		ModelAndView mv = new ModelAndView();
//		memberVO = memberService.getLogin(memberVO);
//		
//		mv.setViewName("redirect:./login");
//		if(memberVO != null) {
//			session.setAttribute("member", memberVO);
//			mv.setViewName("redirect:../");
//		}
//		
//		return mv;
//	}

}
