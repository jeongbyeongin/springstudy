package com.gdu.app09.servie;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface EmployeeListService {
	public void getEmployeeListUsingPagination(HttpServletRequest request, Model model);
		
}
