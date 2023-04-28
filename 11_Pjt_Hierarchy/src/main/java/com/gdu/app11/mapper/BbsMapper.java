package com.gdu.app11.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app11.domain.BbsDTO;

@Mapper
public interface BbsMapper {
	public int getBbsCount();
	public List<BbsDTO> getBbsList(Map<String, Object> map);
}
