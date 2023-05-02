package com.mhj.base.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.mhj.base.member.MemberService;
import com.mhj.base.member.MemberSocialService;
import com.mhj.base.security.UserLoginFailureHandler;
import com.mhj.base.security.UserLogoutHandler;
import com.mhj.base.security.UserLogoutSuccessHandler;
import com.mhj.base.security.UserSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private MemberSocialService memberSocialService;
	
	@Autowired
	private UserLogoutHandler userLogoutHandler;
	
	@Autowired
	private UserLogoutSuccessHandler userLogoutSuccessHandler;
	
	@Autowired
	private UserSuccessHandler userSuccessHandler;
	
	@Autowired
	private UserLoginFailureHandler userLoginFailureHandler;
	
	@Bean
	//public 선언 시 default로 바꾸라는 메시지 출력됨
	WebSecurityCustomizer webSecurityConfig() {
		//Security에서 무시해야 하는 URL 패턴 등록
		return web -> web
				.ignoring()
				.antMatchers("/images/**")
				.antMatchers("/js/**")
				.antMatchers("/css/**")
				.antMatchers("/favicon/**")
				;
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.cors()
				.and()
				.csrf()
				.disable()
			.authorizeRequests()
				//URL과 권한 매칭
				.antMatchers("/").permitAll()
				.antMatchers("/member/join").permitAll()
				.antMatchers("/notice/add").hasRole("USER")
				.antMatchers("/notice/update").hasRole("ADMIN")
				.antMatchers("/notice/delete").hasRole("ADMIN")
				.antMatchers("/notice/*").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/qna/add").hasAnyRole("ADMIN", "MANAGER", "USER")
//				.anyRequest().authenticated()
				.anyRequest().permitAll()
				.and()
			.formLogin()
				.loginPage("/member/login")
//				.defaultSuccessUrl("/")
				.successHandler(userSuccessHandler)
//				.failureUrl("/member/login")
				.failureHandler(userLoginFailureHandler)
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/member/logout")
//				.logoutSuccessUrl("/")
//				.addLogoutHandler(userLogoutHandler)
				.logoutSuccessHandler(userLogoutSuccessHandler)
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.permitAll()
				.and()
			.oauth2Login()
				.userInfoEndpoint()
				.userService(memberSocialService)
//			.sessionManagement()
//				.maximumSessions(1)
//				//최대 허용 가능한 session의 수, -1의 경우 무제한
//				.maxSessionsPreventsLogin(false)
//				//false : 이전 사용자 세션 만료
//				//true : 새로운 사용자 인증 실패
				;
		
		return httpSecurity.build();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
