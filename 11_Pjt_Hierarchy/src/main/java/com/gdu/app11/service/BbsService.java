package com.gdu.app11.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface BbsService {
	public void loadBbsList(HttpServletRequest request, Model model);
}
