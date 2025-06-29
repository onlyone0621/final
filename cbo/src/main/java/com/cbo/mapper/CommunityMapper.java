package com.cbo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cbo.community.model.BoardDTO;
import com.cbo.community.model.CommunityDTO;
import com.cbo.community.model.PostDTO;

public interface CommunityMapper {

	//int로 매개값 받게 하기
	public int insertCommunity(CommunityDTO dto) throws Exception;
	public int deleteCommunity(int id) throws Exception;
	public List<CommunityDTO> communityList() throws Exception;
	public int insertBoard(BoardDTO dto) throws Exception;
	//public List<BoardDTO> boardList(@Param("cId")int cId) throws Exception;
	public List<BoardDTO> boardListByCommunityId(Map<String, Object> map) throws Exception;
	public List<BoardDTO> boardList() throws Exception;
	
	//게시글 작성, 본문보기;
	public int insertPost(PostDTO pdto) throws Exception;
	public PostDTO selectPostById(int postId) throws Exception;
}
