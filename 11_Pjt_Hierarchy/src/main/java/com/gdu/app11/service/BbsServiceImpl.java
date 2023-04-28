package com.gdu.app11.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.app11.mapper.BbsMapper;

@Service
public class BbsServiceImpl implements BbsService {

	// field
	private BbsMapper bbsMapper;
	
	@Override
	public void loadBbsList(HttpServletRequest request, Model model) {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		int totalRecord

	}

}
