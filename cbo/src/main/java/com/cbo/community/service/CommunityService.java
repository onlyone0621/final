package com.cbo.community.service;

import java.util.List;

import com.cbo.community.model.BoardDTO;
import com.cbo.community.model.CommunityDTO;

public interface CommunityService {

	public int insertCommunity(CommunityDTO dto) throws Exception;
	public int deleteCommunity(int id) throws Exception;
	public List<CommunityDTO> communityList() throws Exception;
	public int insertBoard(BoardDTO dto) throws Exception;
}
