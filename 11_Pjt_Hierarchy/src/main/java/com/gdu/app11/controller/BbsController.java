package com.gdu.app11.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/bbs")
@Controller
public class BbsController {

	@GetMapping("/list.do")
	public String list(HttpServletRequest request, Model model) {
		
		return "bbs/list";
	}
	
}
