package com.cbo.community.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbo.community.model.BoardDTO;
import com.cbo.community.model.CommunityDTO;
import com.cbo.community.model.ImageDTO;
import com.cbo.community.model.PostDTO;
import com.cbo.community.model.PostListDTO;
import com.cbo.community.model.ReplyDTO;
import com.cbo.constant.CommunityConst;
import com.cbo.mapper.CommunityMapper;
import com.cbo.member.model.MemberDTO;
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
//커뮤니티 정보 가져오기 id에 해당하는 각 커뮤니티 dto 정보 
@Override
	public CommunityDTO communityInfoById(int cId) throws Exception {
			CommunityDTO cdto=mapper.communityInfoById(cId);
			return cdto;
		}
//커뮤니티 수정(이름, 설명 변경)
@Override
	public int updateCommunityInfo(CommunityDTO cdto) throws Exception {
		int result=mapper.updateCommunityInfo(cdto);
		return result;
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
//최신글 5개 가져오기
@Override
	public List<Map<String, Object>> newestPosts() throws Exception {
	List<Map<String, Object>> newestPosts=mapper.newestPosts();
		return newestPosts;
	}

//커뮤니티 홈 각 커뮤니티의 모든 글 리스트
@Override
	public List<PostListDTO> selectPostListByCommunityId(int commId) {
		List<PostListDTO> postLists=mapper.selectPostListByCommunityId(commId);

		return postLists;
	}
//게시판 수정 폼에 현재값 표시
@Override
	public BoardDTO selectBoardById(int boardId) throws Exception {
		BoardDTO bdto=mapper.selectBoardById(boardId);
		return bdto;
	}

//게시판 이름, 설명 수정
@Override
	public int updateBoardInfo(BoardDTO bdto) throws Exception {
		int result=mapper.updateBoardInfo(bdto);
		return result;
	}


//////// 권한 부여
//public List<MemberDTO> joinMembersList(int cId) throws Exception {
//List<MemberDTO> mdto =   mapper.selectMembersByRoles(cId, List.of("master", "submaster", "user"));
		//return mdto;

// 가입된 멤버 리스트 가져오기 
@Override
public List<Map<String, Object>> joinMemberList(int cId) throws Exception {
    List<Map<String, Object>> mdto = mapper.selectMembersByRoles(cId, List.of(
        CommunityConst.MASTER,
        CommunityConst.SUBMASTER,
        CommunityConst.USER
    ));
    return mdto;
}

// 가입 대기 멤버 리스트 가져오기
@Override
public List<Map<String, Object>> pendingMemberList(int cId) throws Exception {
    List<Map<String, Object>> mdto = mapper.selectMembersByRoles(cId, List.of("가입대기"));
    return mdto;
}

// 가입 승인 (가입대기 → user)
@Override
	public void approveMember(int cId, int memberId) throws Exception {
		mapper.updateMemberRole(cId, memberId, "user");
		
	}

// 가입 거절 (가입대기 → 거절)
@Override
	public void rejectMember(int cId, int memberId) throws Exception {
		mapper.updateMemberRole(cId, memberId, "거절");
		
	}

// 멤버 탈퇴
@Override
	public void removeMember(int cId, int memberId) throws Exception {
		mapper.deleteCommunityMember(cId, memberId);
		
	}

// 권한 변경 (예: submaster ↔ user)
@Override
	public void changeRole(int cId, int memberId, String role) {
		mapper.updateMemberRole(cId, memberId, role);
		
	}


}






