package com.mhj.base.security;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserLoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		log.error("============= {} =============", exception);
		log.error("============= {} =============", exception.getMessage());
		String errorMessage = "";
		
		if(exception instanceof BadCredentialsException) {
			errorMessage = "비밀 번호 틀림";
		} else if(exception instanceof InternalAuthenticationServiceException) {
			errorMessage = "ID 확인";
		} else if(exception instanceof DisabledException) {
			errorMessage = "유효하지 않은 사용자입니다.";
			//enabled가 false인 경우
		} else {
			errorMessage = "로그인 실패";
		}
		
		//redirect
		errorMessage = URLEncoder.encode(errorMessage, "UTF-8");
		response.sendRedirect("/member/login?errorMessage="+errorMessage);
	}

}
