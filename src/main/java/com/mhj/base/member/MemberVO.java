package com.mhj.base.member;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {
	
	private String userName;
	private String password;
	private String name;
	private String email;
	private Date birth;
	private boolean enabled;
	
	private List<RoleVO> roleVO;

}
