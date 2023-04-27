package com.gdu.app09.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app09.servie.EmployeeListService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeListService employeeListService;
	
	@GetMapping("/employees/pagination.do")
	public String pagination(HttpServletRequest request, Model model) {
		employeeListService.getEmployeeListUsingPagination(request, model);
		return "employees/pagination";
	}
	
	@GetMapping("/employees/change/record.do")
	public String changeRecord(HttpSession session
							 , HttpServletRequest request
							 , @RequestParam(value="recordPerPage", required=false, defaultValue="10") int recordPerPage) {
		session.setAttribute("recordPerPage", recordPerPage);
		return "redirect:" + request.getHeader("referer");  // 현재 주소(/employees/change/record.do)의 이전 주소(Referer)로 이동하시오.
															// referer = 이 전에 클릭했던 페이지 주소를 말함
	}

	/* 위에랑 같은 방식(옛날 방식)
	 * @GetMapping("/employees/change/record.do") public String
	 * changeRecord(HttpServletRequest request) { Optional<String> opt =
	 * Optional.ofNullable(request.getParameter("recordPerPage")); int recordPerPage
	 * = Integer.parseInt(opt.orElse("10"));
	 * 
	 * HttpSession session = request.getSession();
	 * session.setAttribute("recordPerPage", recordPerPage); }
	 */
	
	@GetMapping("/employees/scroll.page")
	public String scrollPage() {
		return "employees/scroll";
	}
	
	@ResponseBody	
	@GetMapping(value="/employee/scroll.do", produces="application/json")
	public Map<String, Object> scroll(HttpServletRequest request){
		return employeeListService.getEmployeeListUsingScroll(request);
	}
	
	@GetMapping("/employees/search.do")
	public String search(HttpServletRequest request, Model model) {
		employeeListService.getEmployeeListUsingSearch(request, model);
		return "employees/search";
	}
	
	@ResponseBody	
	@GetMapping(value="/employees/autoComplete.do", produces="application/json")
	public Map<String, Object> autoComplete(HttpServletRequest request){
		return employeeListService.getAutoComplete(request);
	}	
	
}
