package com.cbo.community.service;

import java.util.List;
import java.util.Map;

import com.cbo.community.model.BoardDTO;
import com.cbo.community.model.CommunityDTO;
import com.cbo.community.model.PostDTO;

public interface CommunityService {

	public int insertCommunity(CommunityDTO dto) throws Exception;
	public int deleteCommunity(int id) throws Exception;
	public List<CommunityDTO> communityList() throws Exception;
	public int insertBoard(BoardDTO dto) throws Exception;
	public List<BoardDTO> boardListByCommunityId(Map<String, Object> map) throws Exception;
	public List<BoardDTO> boardList() throws Exception;
	
	//게시글(post)
	public int insertPost(PostDTO pdto) throws Exception;
	public PostDTO selectPostById(int postId) throws Exception;
	
}
