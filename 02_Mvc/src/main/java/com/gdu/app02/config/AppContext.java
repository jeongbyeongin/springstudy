package com.gdu.app02.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gdu.app02.domain.Bbs;

@Configuration
public class AppContext {
	
	// 1번째 Bean은 root-context.xml에 있습니다.
	// Spring Container에 Bbs bbs1; 과 Bbs bbs2;가 저장되어있다.
	@Bean
	public Bbs bbs2() {
		Bbs bbs = new Bbs();
		bbs.setBbsNo(2);
		bbs.setTitle("한잔하게");
		bbs.setCreatedAt("2023-04-14");
		return bbs;
	}

}
