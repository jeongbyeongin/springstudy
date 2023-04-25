package com.gdu.prd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.prd.domain.ProductDTO;
import com.gdu.prd.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/list.do")
	public String list(Model model) {  		// ()에 파라미터 처리가 들어와야한다. 페이지에서
		productService.loadProductList(model);
		return "product/list";
	}
	
	@PostMapping("/add.do")
	public String add(ProductDTO productDTO, RedirectAttributes redirectAttributes) { // 2가지를 전달하는데 request를 하지 않겠다.2가지 @RequestParam, 두가지를 다 가지고 있는 ProductDTO클래스를 이용하자
																					  // Redirect를 하기위해 선언하는 위치
		int addResult = productService.addProduct(productDTO);
		redirectAttributes.addFlashAttribute("addResult", addResult);					// addResult를 리다이렉트하는 것은 list.jsp에서 addResult를 속성의 형태로 보낸다.
		return "redirect:/product/list.do";
	}
	
	@GetMapping("/detail.do")
	public String detail(@RequestParam(value="prodNo", required= false, defaultValue= "0") int prodNo, Model model) {
		productService.loadProduct(prodNo, model);
		return "/product/detail";
	}
	
	@PostMapping("/edit.do")
	public String edit(ProductDTO product) {
		return "/product/edit";
	}

}
