package com.mhj.base.board;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {
	
	private Long num;
	private String title;
	private String contents;
	private String writer;
	private Date regDate;
	private Long hit;

}