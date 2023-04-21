package com.gdu.app07.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gdu.app07.domain.BoardDTO;

@Repository
public class BoardDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	private final String NS = "mybatis.mapper.board.";
	
	public List<BoardDTO> selectBoardList(){
		return sqlSessionTemplate.selectList(NS + "selectBoardList");
	}
	
	public BoardDTO selectBoardByNo(int BoardNo) { 	// 서비스로부터 받아오는 자리  (int BoardNo) -> ( 이 자리 )
		return sqlSessionTemplate.selectOne(NS + "selectBoardByNo", BoardNo); 	// 받아온 것을 BoardNo) 여기서 주는 것이다.
	}
	
	public int insertBoard(BoardDTO board) {
		return sqlSessionTemplate.insert(NS + "insertBoard", board);
	}
	
	public int updateBoard(BoardDTO board) {
		return sqlSessionTemplate.update(NS + "updateBoard", board);
	} 
	
	public int deleteBoard(int BoardNo) {
		return sqlSessionTemplate.update(NS + "deleteBoard", BoardNo);
	}
	
}
