package com.gdu.prd.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	private int prodNo;
	private String prodName;
	private int prodPrice;
	private Date prodMadeAt;
}
