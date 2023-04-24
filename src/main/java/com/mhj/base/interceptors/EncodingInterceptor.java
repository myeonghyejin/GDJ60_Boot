package com.mhj.base.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mhj.base.board.notice.NoticeDAO;
import com.mhj.base.board.qna.QnaDAO;

@Component
public class EncodingInterceptor implements HandlerInterceptor{
	
	@Autowired
	private QnaDAO qnaDAO;
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		return true;
	}

}