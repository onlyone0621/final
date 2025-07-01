package com.cbo.community.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbo.community.model.BoardDTO;
import com.cbo.community.model.CommunityDTO;
import com.cbo.community.model.ImageDTO;
import com.cbo.community.model.PostDTO;
import com.cbo.community.model.ReplyDTO;
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
		int result = mapper.insertPost(pdto);
		return result;
	}

//게시긃 본문보기
@Override
	public PostDTO selectPostById(int postId) throws Exception {
		PostDTO pdto=mapper.selectPostById(postId);
		pdto.setContent(pdto.getContent().replaceAll("\n", "<br>"));
		return pdto;
	}
// 조회수 늘리기 +1
@Override
	public void ViewNumPlus(int postId) throws Exception {
		mapper.ViewNumPlus(postId);
		
	}
// 좋아요 눌르기 +!
@Override
	public int upvotePlus(int postId) throws Exception {
		int result=mapper.upvotePlus(postId);
		return result;
	}



//게시글(post) list가져오기 (boardId 갖고옴)
@Override
	public List<PostDTO> postListByBoardId(int boardId) throws Exception {
		List<PostDTO> postLists=mapper.postListByBoardId(boardId);
		return postLists;
	}
//게시글 정보 커뮤니티명, 게시판 명 가져오기 
@Override
	public Map<String, String> selectBoardAndCommunity(int boardId) throws Exception {
	 Map<String, String> result =  mapper.selectBoardAndCommunity(boardId);
	 return result;
	}

//게시글 이미지 넣기
@Override
	public int insertImage(ImageDTO idto) throws Exception {
		int result= mapper.insertImage(idto);
		return result;
	}

//이미지 여러장  본문보기
@Override
	public List<ImageDTO> selectImagesByPostId(int postId) throws Exception {
		List<ImageDTO> imageList= mapper.selectImagesByPostId(postId);
		return imageList;
	}

//게시글 수정
@Override
	public int updatePost(PostDTO pdto) throws Exception {
		int result=mapper.updatePost(pdto);
		return result;
	}

//게시글 삭제
@Override
	public int deletePost(int postId) throws Exception {
		int result = mapper.deletePost(postId);
		return result;
	}
//이미지 삭제
@Override
	public int deleteImage(int imageId) throws Exception {
		int result=mapper.deleteImage(imageId);
		return result;
	}
// 이미지 선택 삭제, 이미지 실제 파일 삭제
@Override
	public ImageDTO selectImageById(int imageId) throws Exception {
		ImageDTO idto=mapper.selectImageById(imageId);
		return idto;
	}
//댓글 달기
@Override
	public int insertReply(ReplyDTO rdto) throws Exception {
		int result=mapper.insertReply(rdto);
		return result;
	}
//댓글 수정
@Override
	public int updateReply(ReplyDTO rdto) throws Exception {
		int result=mapper.updateReply(rdto);
		return result;
	}
//댓글 삭제
@Override
	public int deleteReply(int replyId) throws Exception {
		int result=mapper.deleteReply(replyId);
		return result;
	}

//댓글 목록
@Override
	public List<ReplyDTO> selectReplyByPostId(int postId) throws Exception {
	List<ReplyDTO> replyLists=mapper.selectReplyByPostId(postId);
	return replyLists;
	}

//순번 밀기
@Override
	public int updateReplySunbun(Map<String, Object> map) throws Exception {
		int result=mapper.updateReplySunbun(map);
		return result;
	
	}
//해당 커뮤니티 게시판 관리 - 게시판목록, 운영자 이름 불러오기
@Override
	public List<Map<String, Object>> boardListWithMaster(int cId) throws Exception {
	List<Map<String, Object>> boardlist = mapper.boardListWithMaster(cId);
		return boardlist;
	}

//해당 커뮤니티 게시판 관리 - 삭제
@Override
	public int deleteBoards(List<Integer> boardIds) throws Exception {
    	int result= mapper.deleteBoards(boardIds);
    		return result;
}

}






