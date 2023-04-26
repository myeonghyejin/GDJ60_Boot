package com.mhj.base.board;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.mhj.base.member.MemberVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {
	
	private Long num;
	@NotBlank
	@Size(min = 3, max = 20)
	private String title;
	private String contents;
	@NotBlank
	private String writer;
	private Date regDate;
	private Long hit;
	
	private List<BoardFileVO> boardFileVO;
	
	private List<MemberVO> memberVO;

}