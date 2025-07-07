package com.cbo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cbo.community.model.BoardDTO;
import com.cbo.community.model.CommunityDTO;
import com.cbo.community.model.ImageDTO;
import com.cbo.community.model.PostDTO;
import com.cbo.community.model.PostListDTO;
import com.cbo.community.model.ReplyDTO;
import com.cbo.member.model.MemberDTO;

public interface CommunityMapper {

	// 커뮤니티 생성
	public int insertCommunity(CommunityDTO dto) throws Exception;

	// 커뮤니티 생성자 마스터 등록 ( 커뮤니티 생성 직후 바로 마스터 등록을 자동으로 처리)
	public int insertCommunityMaster(int community_id, int member_id) throws Exception;

	public int deleteCommunity(int id) throws Exception;

	// 커뮤니티 모든 목록 가져오기
	public List<CommunityDTO> communityList() throws Exception;

	// id에 해당하는 각 커뮤니티 dto 정보 가져오기
	public CommunityDTO communityInfoById(int cId) throws Exception;

	// 커뮤니티 수정(이름, 설명 변경)
	public int updateCommunityInfo(CommunityDTO cdto) throws Exception;

	// 게시판 생성
	public int insertBoard(BoardDTO dto) throws Exception;

	// community id 받은 게시판 목록
	public List<BoardDTO> boardListByCommunityId(Map<String, Object> map) throws Exception;

	// 모든 게시판 목록
	public List<BoardDTO> boardList() throws Exception;

	// 게시글 작성, ;
	public int insertPost(PostDTO pdto) throws Exception;

	// 게시글 본문보기
	public PostDTO selectPostById(int postId) throws Exception;

	// 조회수 +1
	public void ViewNumPlus(int postId) throws Exception;

	// 좋아요 +1
	public int upvotePlus(int postId) throws Exception;

	// 게시글 list가져오기 (boardId 갖고옴)
	public List<PostDTO> postListByBoardId(int boardId) throws Exception;

	// 게시글 정보 커뮤니티명, 게시판 명 가져오기
	public Map<String, String> selectBoardAndCommunity(int boardId) throws Exception;

	// 게시글 이미지 넣기 이미지 불러오기
	public int insertImage(ImageDTO idto) throws Exception;

	List<ImageDTO> selectImagesByPostId(int postId) throws Exception;

	// 게시글 수정, 삭제, 이미지 삭제
	public int updatePost(PostDTO pdto) throws Exception;

	public int deletePost(int postId) throws Exception;

	public int deleteImage(int imageId) throws Exception;

	// 이미지 본문보기
	public ImageDTO selectImageById(int imageId) throws Exception;

	// 댓글달기
	public int insertReply(ReplyDTO rdto) throws Exception;

	// 댓글 수정
	public int updateReply(ReplyDTO rdto) throws Exception;

	// 댓글 삭제
	public int deleteReply(int replyId) throws Exception;

	// 댓글 읽기 (postid가져옴)
	public List<ReplyDTO> selectReplyByPostId(int postId) throws Exception;

	// 순번 밁기
	public int updateReplySunbun(Map<String, Object> map) throws Exception;

	// 게시판 정보 수정 - 해당 커뮤니티 게시판목록, 운영자 이름 불러오기
	public List<Map<String, Object>> boardListWithMaster(int cId) throws Exception;

	// 각 커뮤니티의 모든 목록 선택 삭제, 모두 삭제
	public int deleteBoards(List<Integer> boardIds) throws Exception;

	// 최신글 5개 가져오기
	public List<Map<String, Object>> newestPosts() throws Exception;

	// 커뮤니티 홈 각 커뮤니티의 모든 글 리스트
	public List<PostListDTO> selectPostListByCommunityId(int commId);

	// 게시판 수정 폼에 현재값 표시
	public BoardDTO selectBoardById(int boardId) throws Exception;

	// 게시판 이름, 설명 수정
	public int updateBoardInfo(BoardDTO bdto) throws Exception;

//////////////////////////////////////멤버 초대)/////////////////////////////////////

	// 사이드바 멤버 목록(이름, 직책, 역할(마스터))
	public List<Map<String, Object>> sidebarMemberList(@Param("cId") int cId) throws Exception;

	// 전체 멤버 상세 목록 (full view)
	public List<Map<String, Object>> fullMemberList(int cId) throws Exception;

	// 초대용 - 커뮤니티 모든 회원 + 현재 role (가입자, 미가입자 구분)
	public List<Map<String, Object>> selectMemberInviteList(@Param("cId") int cId) throws Exception;

	// 현재 커뮤니티에서 현재 사용자의 역할 알기(마스터, 부마스터만 멤버초대 할 수 있도록) +멤버 추가 버튼 볼 수 있음
	public String getCommunityRole(@Param("cId") int cId, @Param("memberId") int memberId) throws Exception;

	// 초대 할때 이미 멤버인지 확인 ((초대 버튼 누를 때 중복 초대 방지) )
	public int checkInviteAlreadyMember(@Param("cId") int cId, @Param("memberId") int memberId) throws Exception;

	// 멤버 초대하기 (insert 커뮤니티 )
	public void insertInviteMember(@Param("cId") int cId, @Param("memberId") int memberId, @Param("role") String role)
			throws Exception;

	//////////////////////// 마스터, 부마스터 지정////////////
	///
	// 가입된 멤버 전체 목록(커뮤니티 정보 수정) - community_member + member + 부서 + 직급 JOIN, role별 정렬
	public List<Map<String, Object>> selectJoinAllMembers(int cId) throws Exception;

	// 현재 마스터 조회
	public Map<String, Object> selectCurrentMaster(int cId) throws Exception;

	// 현재 부마스터 목록 조회
	public List<Map<String, Object>> selectCurrentSubmasters(int cId) throws Exception;

	// 마스터 변경 - 지정 id는 master, 나머지는 user 처리
	public void updateMasterRole(int cId, int newMasterId) throws Exception;

	// 부마스터 지정 - 부마스터 role로 업데이트
	public void assignSubmaster(int cId, int memberId) throws Exception;

	// 부마스터 해제 - user role로 업데이트
	public void removeSubmaster(int cId, int memberId) throws Exception;

	///////////////////////////// 권한 부여  ////////



	//마스터  : 6 가입 대기자 목록 조회 ) 
// roles = role 목록 (예: ["user", "submaster"])
	public List<Map<String, Object>> selectMembersByRoles(@Param("cId") int cId, @Param("list") List<String> roles)
			throws Exception;

//  마스터  : 1,2 가입  승인(pending → user), 거절(pending → 거절) 
	public void updateMemberRole(@Param("cId") int cId, @Param("memberId") int memberId, @Param("role") String role)
			throws Exception;

//  마스터  : 3 멤버 삭제(강제 탈퇴) --> 
	public void deleteCommunityMember(@Param("cId") int cId, @Param("memberId") int memberId) throws Exception;

//  마스터  : 5 특정 멤버 role 가져옴 (탈퇴/권한 관리용) role 조회 selectMemberRole : removeMember에서 마스터인지 확인 -->
	public String selectMemberRole(@Param("cId") int cId, @Param("memberId") int memberId) throws Exception;

	// 사용자 1: 가입 상태 확인
	public String getJoinStatus(@Param("cId") int cId, @Param("memberId") int memberId);

	// 사용자 2: 전체 커뮤니티 + 회원 가입 상태 조회
	public List<Map<String, Object>> selectCommunityMainAll(int memberId) throws Exception;

	// 사용자 3: 가입 신청
	public int requestJoin(Map<String, Object> map) throws Exception;

	// 사용자 4 : 탈퇴
	public int leaveCommunity(Map<String, Object> map) throws Exception;

}
