package com.cbo.community.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbo.community.model.BoardDTO;
import com.cbo.community.model.CommunityDTO;
import com.cbo.community.model.PostDTO;
import com.cbo.mapper.CommunityMapper;
@Service
public class CommunityServiceImple implements CommunityService {
  @Autowired
  private CommunityMapper mapper;

//커뮤니티 생성
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
// 게시판 생성
@Override
	public int insertBoard(BoardDTO dto) throws Exception {
		int result = mapper.insertBoard(dto);
		return result;
	}
//게시판 목록 (커뮤니티에 해당하는 게시판 목록 불러오기)
@Override
	public List<BoardDTO> boardListByCommunityId(Map<String, Object> map) throws Exception {
		List<BoardDTO> boardListByCommunityId=mapper.boardListByCommunityId(map);
		return boardListByCommunityId;
	
	}
// 게시판 목록 전체
@Override
	public List<BoardDTO> boardList() throws Exception {
		List<BoardDTO> boardLists=mapper.boardList();
		return boardLists;
	}
// 게시글 작성
@Override
	public int insertPost(PostDTO pdto) throws Exception {
		int result = mapper.insertPost();
		return result;
	}
}