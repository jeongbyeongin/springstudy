package com.gdu.app01.java_into_xml;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {

	@Bean
	public Publisher publisher() {
		Publisher publisher = new Publisher();  // 디폴트로 생성했기 때문에 만들어야한다.
		publisher.setName("한국출판사");
		publisher.setTel("010-1241-1232");
		return publisher;
	}
	
	@Bean
	public Book book() {
		Book book = new Book();
		book.setTitle("소나기");
		book.setPublisher(publisher());
		return book;
	}
	
}
