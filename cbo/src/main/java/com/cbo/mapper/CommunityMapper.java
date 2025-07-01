package com.cbo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cbo.community.model.BoardDTO;
import com.cbo.community.model.CommunityDTO;
import com.cbo.community.model.ImageDTO;
import com.cbo.community.model.PostDTO;
import com.cbo.community.model.ReplyDTO;

public interface CommunityMapper {

	//int로 매개값 받게 하기
	public int insertCommunity(CommunityDTO dto) throws Exception;
	public int deleteCommunity(int id) throws Exception;
	public List<CommunityDTO> communityList() throws Exception;
	//게시판 생성 
	public int insertBoard(BoardDTO dto) throws Exception;
	//public List<BoardDTO> boardList(@Param("cId")int cId) throws Exception;
	public List<BoardDTO> boardListByCommunityId(Map<String, Object> map) throws Exception;
	public List<BoardDTO> boardList() throws Exception;
	
	//게시글 작성, ;
	public int insertPost(PostDTO pdto) throws Exception;
	
	//게시글 본문보기
	public PostDTO selectPostById(int postId) throws Exception;
	// 조회수 +1 
	public void ViewNumPlus(int postId) throws Exception;
	//좋아요 +1
	public int upvotePlus(int postId) throws Exception;
	
	//게시글 list가져오기 (boardId 갖고옴)
	public List<PostDTO> postListByBoardId(int boardId) throws Exception;
	//게시글 정보 커뮤니티명, 게시판 명 가져오기 
	public Map<String, String> selectBoardAndCommunity(int boardId) throws Exception;
	
	//게시글 이미지 넣기 이미지 불러오기
	public int insertImage(ImageDTO idto) throws Exception;
	List<ImageDTO> selectImagesByPostId(int postId) throws Exception;
	
	//게시글 수정, 삭제, 이미지 삭제
	public int updatePost(PostDTO pdto) throws Exception;
	public int deletePost(int postId) throws Exception;
	public int deleteImage(int imageId) throws Exception;
	
	//이미지 본문보기
	public ImageDTO selectImageById(int imageId) throws Exception;
	
	//댓글달기
	public int insertReply(ReplyDTO rdto) throws Exception;
	
	
	//댓글 수정
	public int updateReply(ReplyDTO rdto) throws Exception;
	
	//댓글 삭제
	public int deleteReply(int replyId) throws Exception;
	
	//댓글 읽기 (postid가져옴)
	public List<ReplyDTO> selectReplyByPostId(int postId) throws Exception;

	//순번 밁기
	public int updateReplySunbun(Map<String, Object> map) throws Exception;
	
	//게시판 정보 수정 - 해당 커뮤니티 게시판목록, 운영자 이름 불러오기
	public List<Map<String, Object>> boardListWithMaster(int cId) throws Exception;
	
	//각 커뮤니티의 모든 목록 선택 삭제, 모두 삭제
	public int deleteBoards(List<Integer> boardIds)throws Exception;
}
