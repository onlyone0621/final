package com.cbo.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbo.community.model.CommunityDTO;
import com.cbo.mapper.CommunityMapper;
@Service
public class CommunityServiceImple implements CommunityService {
  @Autowired
  private CommunityMapper mapper;
	
@Override
public int insertCommunity(CommunityDTO dto) throws Exception {
	
	int result = mapper.insertCommunity(dto);
	return result;
}

// 커뮤니티 삭제
@Override
	public int deleteCommunity(int id) throws Exception {
		int result = mapper.deleteCommunity(id);
		return result;
	}

//커뮤니티 목록

@Override
	public List<CommunityDTO> communityList() throws Exception {
		List<CommunityDTO> lists=mapper.communityList();
		
		return lists;
	}

}