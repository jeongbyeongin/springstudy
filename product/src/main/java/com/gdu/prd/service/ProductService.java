package com.gdu.prd.service;

import org.springframework.ui.Model;

import com.gdu.prd.domain.ProductDTO;

public interface ProductService {
	public void loadProductList(Model model);	 // 반환할게 너무 많아서 void로 반환을 하지 않겠다.
	public int addProduct(ProductDTO productDTO);
	public void loadProduct(int prodNo, Model model);
}
