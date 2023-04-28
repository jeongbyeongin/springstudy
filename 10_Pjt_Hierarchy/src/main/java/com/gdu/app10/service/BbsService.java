package com.gdu.app10.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface BbsService {
	public void loadBbsList(HttpServletRequest request, Model model); 		// 컨트롤러에서 HttpServletRequest request, Model model 이렇게 보내줬으니 똑같이 받아야한드.
	public int addBbs(HttpServletRequest request);  
	public int removeBbs(int bbsNo);
	public int addReply(HttpServletRequest request);		// 파라미터와 아이디를 써야하기때문에 request를 쓴다.
}
