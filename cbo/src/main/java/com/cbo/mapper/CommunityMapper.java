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

	//int로 매개값 받게 하기
	public int insertCommunity(CommunityDTO dto) throws Exception;
	public int deleteCommunity(int id) throws Exception;
	//커뮤니티 모든 목록 가져오기
	public List<CommunityDTO> communityList() throws Exception;
	//id에 해당하는 각 커뮤니티 dto 정보 가져오기
	public CommunityDTO communityInfoById(int cId) throws Exception;
	//커뮤니티 수정(이름, 설명 변경)
	public int updateCommunityInfo(CommunityDTO cdto) throws Exception;
	//게시판 생성 
	public int insertBoard(BoardDTO dto) throws Exception;
	//community id 받은 게시판 목록
	public List<BoardDTO> boardListByCommunityId(Map<String, Object> map) throws Exception;
	//모든 게시판 목록
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
	
	//최신글 5개 가져오기
	 public List<Map<String, Object>> newestPosts() throws Exception;

	//커뮤니티 홈 각 커뮤니티의 모든 글 리스트
	public List<PostListDTO> selectPostListByCommunityId(int commId);
	
	//게시판 수정 폼에 현재값 표시
	public BoardDTO selectBoardById(int boardId) throws Exception;

	//게시판 이름, 설명 수정
	public int updateBoardInfo(BoardDTO bdto) throws Exception;
	
	//특정 커뮤니티에서 지정된 roles에 해당하는 멤버 리스트 가져오기 roles = role 목록 (예: ["user", "submaster"]) 
	//파라미터 2개 이상이니까 XML에서 #{cId}, #{list} 이름으로 쓰려면 MyBatis에 이름을 지정해야 함
	// list 이름을 @Param("list")로 지정해서 XML에서 list로 참조 가능하게 한 거
	public List<Map<String, Object>> selectMembersByRoles(@Param("cId") int cId, @Param("list") List<String> roles);
    
	//멤버 권한 변경  -  가입 승인 / 거절 / 권한변경 / 부마스터 지정
	//(cId = 커뮤니티 ID ) + (memberId = 멤버 ID)  + (role = 새 role 값)= (파라미터 2개 이상 → XML에서 #{cId}, #{memberId}, #{role} 쓸 이름 지정 필요)
    public int updateMemberRole(@Param("cId") int cId, 
    							@Param("memberId") int memberId, 
    							@Param("role") String role);
    
    //특정 멤버를 커뮤니티 멤버 목록에서 삭제 - cId = 커뮤니티 ID -/memberId = 멤버 ID
    public int deleteCommunityMember(@Param("cId") int cId, 
    								 @Param("memberId") int memberId);


}	 




