package com.gdu.app10.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.app10.domain.BbsDTO;
import com.gdu.app10.mapper.BbsMapper;
import com.gdu.app10.util.PageUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor   // 생성자를 넣어서 Autowired를 생략한다.
@Service
public class BbsServiceImpl implements BbsService {

	// field
	private BbsMapper bbsMapper;
	private PageUtil pageUtil;
	
	@Override
	public void loadBbsList(HttpServletRequest request, Model model) {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));   // 페이지 전달이 안된경우 1을쓴다.
		
		int totalRecord = bbsMapper.getBbsCount();
				
		int recordPerPage = 20;
		
		pageUtil.setPageUtil(page, totalRecord, recordPerPage);  // begin과 end를 구하기위해서.두개를 구해야 bbs.xml까지 보내야하기 때문

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", pageUtil.getBegin());
		map.put("end", pageUtil.getEnd());
		
		List<BbsDTO> bbsList = bbsMapper.getBbsList(map);
		
		model.addAttribute("bbsList", bbsList);
		// beginNo는 1페이지에 경우에 200 , 2페이지인 경우엔 180, 3페이지 160 
		//								200-(1-1) * 20 / 200-(2-1)*20
		model.addAttribute("beginNo", totalRecord - (page - 1) * recordPerPage);
		model.addAttribute("pagination", pageUtil.getPagination(request.getContextPath() + "/bbs/list.do"));
		
	}
	
	@Override
	public int addBbs(HttpServletRequest request) {  		
		
		// 파라미터 writer, title  // write.jsp의 파라미터 2개 
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		
		// IP주소
		String ip = request.getRemoteAddr();
		
		// DB로 보낼 BbsDTO 객체
		BbsDTO bbsDTO = new BbsDTO();
		bbsDTO.setWriter(writer);
		bbsDTO.setTitle(title);
		bbsDTO.setIp(ip);
		
		// 원글 달기
		int addResult = bbsMapper.addBbs(bbsDTO);
		
		// 결과 반환
		return addResult;
		
	}
	
	@Override
	public int removeBbs(int bbsNo) {
		int removeResult = bbsMapper.removeBbs(bbsNo);
		return removeResult;
	}
	
	

}
