package com.cbo.community.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
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
        if (result > 0) {
            int masterResult = insertCommunityMaster(dto.getId(), dto.getMember_id());
            if (masterResult <= 0) {
                throw new Exception("커뮤니티 마스터 등록 실패");
            }
        }
        return result;
    }
// 커뮤니티 생성 시 마스터 지정 
    @Override
    public int insertCommunityMaster(int communityId, int memberId) throws Exception {
        return mapper.insertCommunityMaster(communityId, memberId);
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
		List<CommunityDTO> lists = mapper.communityList();
		return lists;
	}

//커뮤니티 정보 가져오기 id에 해당하는 각 커뮤니티 dto 정보 
	@Override
	public CommunityDTO communityInfoById(int cId) throws Exception {
		CommunityDTO cdto = mapper.communityInfoById(cId);
		return cdto;
	}

//커뮤니티 수정(이름, 설명 변경)
	@Override
	public int updateCommunityInfo(CommunityDTO cdto) throws Exception {
		int result = mapper.updateCommunityInfo(cdto);
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
		List<BoardDTO> boardListByCommunityId = mapper.boardListByCommunityId(map);
		return boardListByCommunityId;

	}

// 게시판 목록 전체
	@Override
	public List<BoardDTO> boardList() throws Exception {
		List<BoardDTO> boardLists = mapper.boardList();
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
		PostDTO pdto = mapper.selectPostById(postId);
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
		int result = mapper.upvotePlus(postId);
		return result;
	}

//게시글(post) list가져오기 (boardId 갖고옴)
	@Override
	public List<PostDTO> postListByBoardId(int boardId) throws Exception {
		List<PostDTO> postLists = mapper.postListByBoardId(boardId);
		return postLists;
	}

//게시글 정보 커뮤니티명, 게시판 명 가져오기 
	@Override
	public Map<String, String> selectBoardAndCommunity(int boardId) throws Exception {
		Map<String, String> result = mapper.selectBoardAndCommunity(boardId);
		return result;
	}

//게시글 이미지 넣기
	@Override
	public int insertImage(ImageDTO idto) throws Exception {
		int result = mapper.insertImage(idto);
		return result;
	}

//이미지 여러장  본문보기
	@Override
	public List<ImageDTO> selectImagesByPostId(int postId) throws Exception {
		List<ImageDTO> imageList = mapper.selectImagesByPostId(postId);
		return imageList;
	}

//게시글 수정
	@Override
	public int updatePost(PostDTO pdto) throws Exception {
		int result = mapper.updatePost(pdto);
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
		int result = mapper.deleteImage(imageId);
		return result;
	}

// 이미지 선택 삭제, 이미지 실제 파일 삭제
	@Override
	public ImageDTO selectImageById(int imageId) throws Exception {
		ImageDTO idto = mapper.selectImageById(imageId);
		return idto;
	}
//
////댓글 달기
//	@Override
//	public int insertReply(ReplyDTO rdto) throws Exception {
//		int result = mapper.insertReply(rdto);
//		return result;
//	}
//
////댓글 수정
//	@Override
//	public int updateReply(ReplyDTO rdto) throws Exception {
//		int result = mapper.updateReply(rdto);
//		return result;
//	}
//
////댓글 삭제
//	@Override
//	public int deleteReply(int replyId) throws Exception {
//		int result = mapper.deleteReply(replyId);
//		return result;
//	}
//
////댓글 목록
//	@Override
//	public List<ReplyDTO> selectReplyByPostId(int postId) throws Exception {
//		List<ReplyDTO> replyLists = mapper.selectReplyByPostId(postId);
//		return replyLists;
//	}
//
////순번 밀기
//	@Override
//	public int updateReplySunbun(Map<String, Object> map) throws Exception {
//		int result = mapper.updateReplySunbun(map);
//		return result;
//
//	}
	
	
	  // 댓글 작성
    @Override
    public int insertReply(ReplyDTO dto) throws Exception {
        // 댓글 insert (id + 기본 ref = 0)
        int result = mapper.insertReply(dto);
        // insert 후 ref = id 로 업데이트
        mapper.updateReplyRef(dto.getId());
        return result;
    }

    // 답글 작성
    @Override
    public int insertChildReply(ReplyDTO dto, int parentId) throws Exception {
        // 부모 댓글 정보 조회
        ReplyDTO parent = mapper.selectReplyById(parentId);
        // sunbun 밀기 (답글 위치 확보)
        Map<String, Object> map = new HashMap<>();
        map.put("ref", parent.getRef());
        map.put("sunbun", parent.getSunbun() + 1);
        mapper.updateReplySunbun(map);

        // 답글 ref, lev, sunbun 세팅
        dto.setRef(parent.getRef());
        dto.setLev(parent.getLev() + 1);
        dto.setSunbun(parent.getSunbun() + 1);

        return mapper.insertReply(dto);
    }

    // 댓글/답글 수정
    @Override
    public int updateReply(ReplyDTO dto) throws Exception {
        return mapper.updateReply(dto);
    }

    // 댓글/답글 삭제
    @Override
    public int deleteReply(int id) throws Exception {
        return mapper.deleteReply(id);
    }

    // 댓글/답글 목록 조회
    @Override
    public List<Map<String, Object>> selectReplyByPostId(int postId) throws Exception {
        return mapper.selectReplyByPostId(postId);
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
		int result = mapper.deleteBoards(boardIds);
		return result;
	}

//최신글 5개 가져오기
	@Override
	public List<Map<String, Object>> newestPosts() throws Exception {
		List<Map<String, Object>> newestPosts = mapper.newestPosts();
		return newestPosts;
	}

//커뮤니티 홈 각 커뮤니티의 모든 글 리스트
	@Override
	public List<PostListDTO> selectPostListByCommunityId(int commId) {
		List<PostListDTO> postLists = mapper.selectPostListByCommunityId(commId);

		return postLists;
	}

//게시판 수정 폼에 현재값 표시
	@Override
	public BoardDTO selectBoardById(int boardId) throws Exception {
		BoardDTO bdto = mapper.selectBoardById(boardId);
		return bdto;
	}

//게시판 이름, 설명 수정
	@Override
	public int updateBoardInfo(BoardDTO bdto) throws Exception {
		int result = mapper.updateBoardInfo(bdto);
		return result;
	}

//////// 권한 부여

	
	// 가입되어있는 master,submaster,user 목록 구분용 가져오기
	@Override
	public List<Map<String, Object>> joinMemberList(int cId) throws Exception {
	    // mapper의 joinMemberList 호출 → master, submaster, user 가져오는 쿼리
	    return mapper.joinMemberList(cId);
	}

	//가입 대기에 해당하는 회원 정보들 가져오기
	@Override
	public List<Map<String, Object>> pendingMemberList(int cId) throws Exception {
	    // mapper의 pendingMemberList 호출 → 가입대기만 가져옴
	    return mapper.pendingMemberList(cId, List.of(CommunityConst.PENDING));
	}

//가입 승인 (가입대기 → user)
	@Override
	public void approveMember(int cId, int memberId) throws Exception {
		mapper.updateMemberRole(cId, memberId, CommunityConst.USER);
	}

//가입 거절 (가입대기 → 거절)
	@Override
	public void rejectMember(int cId, int memberId) throws Exception {
		mapper.updateMemberRole(cId, memberId, "거절");
	}

//멤버 탈퇴 (삭제 X, role 변경)
	@Override
	public void removeMember(int cId, int memberId) throws Exception {
	    String role = mapper.selectMemberRole(cId, memberId);
	    if (CommunityConst.MASTER.equals(role)) {
	        throw new IllegalStateException("마스터는 탈퇴할 수 없습니다."); // 마스터는 삭제불가
	    }
	    mapper.deleteCommunityMember(cId, memberId);  //다른 멤버는 row 삭젲 !!
	}

//권한 변경 (예: submaster ↔ user)
	@Override
	public void changeRole(int cId, int memberId, String role) throws Exception {
		mapper.updateMemberRole(cId, memberId, role);
	}

//마스터 여부 조회
	@Override
	public String selectMemberRole(int cId, int memberId) throws Exception {
		return mapper.selectMemberRole(cId, memberId);
		
	}
	
	///////////////////////////( 멤버 초대 )/////////////////////////////////
	
//사이드바 멤버 목록(이름, 직책, 역할(마스터))
	@Override
	public List<Map<String, Object>> sidebarMemberList(int cId) throws Exception {
	    return mapper.sidebarMemberList(cId);
	}

//모든 가입 멤버 상세 조회	
	@Override
	public List<Map<String, Object>> fullMemberList(int cId) throws Exception {
	    return mapper.fullMemberList(cId);
	}
	
	// 초대할 수 있는 멤버 목록 (role이 null 이면 미가입 상태)
	@Override
	public List<Map<String, Object>> selectMemberInviteList(int cId) throws Exception {
		 return mapper.selectMemberInviteList(cId);
	}
	
	//현재 커뮤니티에서 현재 사용자의 역할 알기(마스터, 부마스터만 멤버초대 할 수 있도록)
	//+ 멤버추가버튼도 둘만 볼 숭 있음
	@Override
	public String getCommunityRole(int cId, int memberId) throws Exception {
		 return mapper.getCommunityRole(cId, memberId);
	}
		
	
	// 초대 할때 이미 멤버인지 확인 ((초대 버튼 누를 때 중복 초대 방지) )
	@Override
	public int checkInviteAlreadyMember(int cId, int memberId) throws Exception {
	    return mapper.checkInviteAlreadyMember(cId, memberId);
	}
	
	// 멤버 초대하기 (insert 커뮤니티 )
	@Override
	public void insertInviteMember(int cId, int memberId, String role) throws Exception {
	    if (mapper.checkInviteAlreadyMember(cId, memberId) > 0) {
	        throw new IllegalStateException("이미 초대되었거나 가입된 멤버입니다.");
	    }
	    mapper.insertInviteMember(cId, memberId, role);
	}
	
	
	////////////////////////마스터/ 부마스터 지정(변경)/////////////////////////
	
	
	//가입된 멤버 전체 목록(커뮤니티 정보 수정) -	community_member + member + 부서 + 직급 JOIN, role별 정렬
	@Override
	public List<Map<String, Object>> selectJoinAllMembers(int cId) throws Exception {
	    return mapper.selectJoinAllMembers(cId);
	}
	
	//현재 마스터 조회
	@Override
	public Map<String, Object> selectCurrentMaster(int cId) throws Exception {
	    return mapper.selectCurrentMaster(cId);
	}

	//현재 부마스터 목록 조회
	@Override
	public List<Map<String, Object>> selectCurrentSubmasters(int cId) throws Exception {
	    return mapper.selectCurrentSubmasters(cId);
	}

	//마스터 변경 - 지정 id는 master, 나머지는 user 처리
	@Override
	public void updateMasterRole(int cId, int newMasterId) throws Exception {
	    mapper.updateMasterRole(cId, newMasterId);
	}

	//부마스터 지정 - 부마스터 role로 업데이트
	@Override
	public void assignSubmaster(int cId, int memberId) throws Exception {
	    mapper.assignSubmaster(cId, memberId);
	}

	//부마스터 해제 - user role로 업데이트
	@Override
	public void removeSubmaster(int cId, int memberId) throws Exception {
	    mapper.removeSubmaster(cId, memberId);
	}
	
	
	/////////////////////// 가입신청 / 가입 완료 / 탈퇴///////////////////////////////
	 
	//가입 상태 확인
	@Override
	    public String getJoinStatus(int cId, int memberId) throws Exception {
	        return mapper.getJoinStatus(cId, memberId);
	    }
	
	// 모든 커뮤니티 조회(가입상태도 확인)
	@Override
	public List<Map<String, Object>> selectCommunityMainAll(int memberId) throws Exception {
	    return mapper.selectCommunityMainAll(memberId);
	}
	
	
	// 커뮤니티 가입 신청
	@Override
	public void requestJoin(int cId, int memberId) throws Exception {
	    int exists = mapper.checkExistCommunityMember(cId, memberId);  //가입 신청 전 기존 멤버 존재 여부 확인

	    if (exists == 0) {
	        int result = mapper.requestJoin(cId, memberId);
	        if (result == 0) throw new Exception("가입 신청 insert 실패"); // 가입 신청
	       
	    } else {
	        int result = mapper.updateToPending(cId, memberId);
	       
	        if (result == 0) throw new Exception("가입 신청 update 실패"); //가입 신청 전 기존 멤버 상태 pending으로 변경
	    }
	}
	


	//커뮤니티 멤버 탈퇴 본인이 (탈퇴)
	@Override
	public void leaveCommunity(int cId, int memberId) throws Exception {
	    Map<String, Object> map = Map.of("cId", cId, "memberId", memberId);
	    mapper.leaveCommunity(map);
	}
	
	/////////////////////////////////////////////////////////
	
	//커뮤니티 가입 목록 
	@Override
	public List<Map<String, Object>> communityMainJoin(int memberId) throws Exception {
	    return mapper.communityMainJoin(memberId);
	}
	
	// 사이드바 커뮤니티 가입한 목록들 이름
	@Override
	public List<Map<String, Object>> joinList(int userId) throws Exception {
		return mapper.joinList(userId);
	}
	
	
}
