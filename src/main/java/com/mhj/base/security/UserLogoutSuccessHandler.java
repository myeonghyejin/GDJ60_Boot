package com.mhj.base.security;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mhj.base.member.MemberDAO;
import com.mhj.base.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {

	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String restKey;
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		log.error("========== logout success handler ==========");
		log.error("========== {} ==========", memberDAO);
		
		response.sendRedirect("https://kauth.kakao.com/oauth/logout?client_id="+restKey+"&logout_redirect_uri=http://ec2-54-180-114-183.ap-northeast-2.compute.amazonaws.com/");
//		response.sendRedirect("/");
	}

}
