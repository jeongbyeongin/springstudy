package com.gdu.app05.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.app05.domain.BoardDTO;
import com.gdu.app05.repository.BoardDAO;

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
		return boardDAO.selectBoardList();
	}

	@Override
	public BoardDTO getBoardByNo(int board_no) {
		return boardDAO.selectBoardByNo(board_no);
	}

	@Override
	public int addBoard(BoardDTO board) {
		return boardDAO.insertBoard(board);
	}

	@Override
	public int modifyBoard(BoardDTO board) {
		return boardDAO.updateBoard(board);
	}

	@Override
	public int removeBoard(int board_no) {
		return boardDAO.deleteBoard(board_no);
	}

}
