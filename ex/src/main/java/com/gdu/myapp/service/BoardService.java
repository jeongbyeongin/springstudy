package com.gdu.myapp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.gdu.myapp.domain.BoardDTO;

public interface BoardService {
	public List<BoardDTO> list();				// 목록 넘겨주는 것
	public BoardDTO detail1(int boardNo);		// 한개만 넘겨주는 것
	public BoardDTO detail2(HttpServletRequest request);
	public void detail3(Model model);
}
