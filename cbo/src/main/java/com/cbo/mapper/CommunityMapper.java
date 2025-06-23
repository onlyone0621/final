package com.cbo.mapper;

import com.cbo.community.model.CommunityDTO;

public interface CommunityMapper {

	//int로 매개값 받게 하기
	public int insertCommunity(CommunityDTO dto) throws Exception;
	
}
