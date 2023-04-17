package com.gdu.app04.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.gdu.app04.domain.BoardDTO;
import com.gdu.app04.repository.BoardDAO;

/*
	@Component
 	1. BoardServiceImpl 클래스 타입의 객체를 만들어서 Spring Container에 저장하시오. 
	2. <bean> 태그나 @Configuration + @Bean 애너테이션을 대체하는 방식이다.
	3. Layer별 전용 @Component가 만들어져 있다.
		1) 컨트롤러   	   : @Controller
		2) 서비스	       : @Service
		3) 레파지토리(dao) : @Repository
*/
/* 
    단, 이 @Component가 동작하려면 @Autowired를 통해서 인식되려면 Component-Scan이 (필요하다.) 등록되어있어야한다.
	Component-Scan 등록 방법
	방법1. <context:component-scan> 태그 사용 - servlet-context.xml에 이미 등록되어 있다. ( 경로가 )
	방법2. @ComponentScan 에너테이션 사용 
*/
@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDAO;

	@Override
	public List<BoardDTO> getBoardList() {
		// TODO Auto-generated method stub
		return boardDAO.selectBoardList();
	}

	@Override
	public BoardDTO getBoardByNo(int board_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addBoard(BoardDTO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifyBoard(BoardDTO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeBoard(int board_no) {
		// TODO Auto-generated method stub
		return 0;
	}

}