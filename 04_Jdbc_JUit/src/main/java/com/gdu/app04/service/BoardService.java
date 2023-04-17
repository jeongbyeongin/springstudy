package com.gdu.app04.service;

import java.util.List;

import com.gdu.app04.domain.BoardDTO;

public interface BoardService {
	// 서비스 계층의 메소드명은 가급적 "사용자 친화적"으로 작성하자.
	public List<BoardDTO> getBoardList();
	public BoardDTO getBoardByNo(int board_no);  // jdbc를 사용하고 있기 때문에 boardNo를 카멜케이스로 바꿔주는 옵션이 없다. 
	public int addBoard(BoardDTO board);
	public int modifyBoard(BoardDTO board);
	public int removeBoard(int board_no);	// 번호 한개만 있으면 삭제 가능.
}
