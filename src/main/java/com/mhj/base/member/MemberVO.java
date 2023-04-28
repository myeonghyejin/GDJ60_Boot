package com.mhj.base.member;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO implements UserDetails {
	
	@NotBlank
	private String username;
	
	@NotBlank
	@Length(min = 4, max = 20)
	private String password;
	
	private String passwordCheck;
	
	@NotBlank
	private String name;
	
	@Email
	private String email;
	
	@Past
	private Date birth;
	
//	private boolean enabled;
	
	private Date lastTime;
	
	private List<RoleVO> roleVO;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for(RoleVO roleVOs : roleVO) {
			authorities.add(new SimpleGrantedAuthority(roleVOs.getRoleName()));
		}
		
		return authorities;
	}

//	@Override
//	public String getUsername() {
		// TODO Auto-generated method stub
		// Username(ID) 반환
//		return null;
//	}
	
//	@Override
//	public String getPassword() {
		// TODO Auto-generated method stub
		// Password 반환
//		return null;
//	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		// 계정의 만료 여부
		// true : 만료 X
		// false : 만료 O, 로그인 X
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		// 계정의 잠금 여부
		// true : 잠금 X
		// false : 잠금 O, 로그인 X
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		// 비밀 번호의 만료 여부
		// true : 만료 X
		// false : 만료 O, 로그인 X
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		// 계정의 활성화(사용) 여부
		// true : 계정 활성화
		// false : 계정 비활성화, 로그인 X
		return true;
	}

}
