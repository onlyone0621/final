package com.cbo.mapper;

import java.util.List;

import com.cbo.community.model.CommunityDTO;

public interface CommunityMapper {

	//int로 매개값 받게 하기
	public int insertCommunity(CommunityDTO dto) throws Exception;
	public int deleteCommunity(int id) throws Exception;
	public List<CommunityDTO> communityList() throws Exception;
	
	
}
