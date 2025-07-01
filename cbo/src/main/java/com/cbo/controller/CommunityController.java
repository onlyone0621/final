package com.cbo.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cbo.community.model.BoardDTO;
import com.cbo.community.model.CommunityDTO;
import com.cbo.community.model.ImageDTO;
import com.cbo.community.model.PostDTO;
import com.cbo.community.model.ReplyDTO;
import com.cbo.community.service.CommunityService;
import com.cbo.config.WebSocketConfig;
import com.cbo.member.model.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CommunityController {

    private final AddrController addrController;

    private final WebSocketConfig webSocketConfig;
    private final IndexController indexController;
	
	@Autowired
	private CommunityService service;
	
	
	public CommunityController(IndexController indexController, WebSocketConfig webSocketConfig, AddrController addrController) {
        this.indexController = indexController;
        this.webSocketConfig = webSocketConfig;
        this.addrController = addrController;

    }
	///MAIN ///////////////////
	/// mainNewest URL 이동
	@GetMapping("communityMainNewest")
	public ModelAndView CommunityMainNewestGo() {
		
		List<CommunityDTO> lists=null;
		try {
			lists=service.communityList();
		}catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav=new ModelAndView();
		mav.addObject("lists", lists);
		mav.setViewName("community/communityMainNewest");
		return mav;
	}
	
	
	
	///mainNewest 최신글 불러오기 5개 
	@PostMapping("communityMainNewest")
	public ModelAndView CommunityMainNewest() {
		
		List<CommunityDTO> lists=null;
		try {
			lists=service.communityList();
		}catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("lists", lists);
		mav.setViewName("community/communityMainNewest");
		
		return mav;
	}
	
	// 커뮤니티 가입 목록 URL이동
	@GetMapping("communityMainJoin")
	public String mainJoinList() {
		return "community/communityMainJoin";
	}

	
	
	// 커뮤니티 전체 목록 조회 목록 URL 이동
	@GetMapping("communityMainAll")
	public ModelAndView CommunityList() {
		
		List<CommunityDTO> lists=null;
		try {
			lists=service.communityList();
		}catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav=new ModelAndView();
		mav.addObject("lists", lists);
		mav.setViewName("community/communityMainAll");
		return mav;
	}
	
	////////////////////////////////////////
	//커뮤니티 manage 폴더 관리 
	
	//커뮤니티 생성 get방식 이동
	@GetMapping("communityCreate")
	public ModelAndView showCreateForm() {
		List<CommunityDTO> lists=null;
		try {
			lists=service.communityList();
		}catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav=new ModelAndView();
		mav.addObject("lists", lists);
		mav.setViewName("community/manage/communityCreate");
		return mav;
	}
	
	//커뮤니티 생성 post방식
	@PostMapping("communityCreate")
	public ModelAndView insertCommunity(CommunityDTO dto, HttpServletRequest request) {
		
	    HttpSession session = request.getSession();
	    MemberDTO udto = (MemberDTO) session.getAttribute(com.cbo.constant.MemberConst.USER_KEY);

	    if (udto == null) {
	        ModelAndView login = new ModelAndView();
	        login.addObject("msg", "로그인 해주세요.");
	        login.addObject("gourl", "memberLogin");
	        login.setViewName("community/communityMsg");
	        return login;
	    }

	    dto.setMember_id(udto.getId());
	    int result = 0;
	    String msg = null;
	    String goUrl=null;
	    try {
	        result = service.insertCommunity(dto);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    msg = result > 0 ? "커뮤니티 생성 성공!" : "커뮤니티 생성 실패!";
	    goUrl = result > 0 ? "/community/" + dto.getId() : "communityCreate";

	    ModelAndView mav = new ModelAndView();
	    mav.addObject("msg", msg);
	    mav.addObject("goUrl", goUrl );
	    mav.setViewName("community/communityMsg");
	    return mav;
	}
	
	// 커뮤니티 home 각 커뮤니티의 첫 화면
	@GetMapping("community/{cId}")
	public ModelAndView communityHome(@PathVariable("cId") String cId) {
	    List<BoardDTO> boardLists = null;

	    try {
	        Map<String, Object> map = new HashMap<>();
	        map.put("cId", cId); 
	        

	        boardLists = service.boardListByCommunityId(map);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    ModelAndView mav = new ModelAndView();
	    mav.addObject("boardLists", boardLists);
	    mav.addObject("cId", cId); 
	    mav.setViewName("community/manage/communityHome");
	    return mav;
	}

//마스터 관리 페이지
	/////////////////////////////////////////////////////////////////
	// 커뮤니티 정보 수정
	@GetMapping("community/{cId}Update")
	public ModelAndView communityUpdate(@PathVariable("cId")String cId) {
		//model.addAttribute("id", id);
		ModelAndView mav=new ModelAndView();
		mav.addObject("cId", cId);
		mav.setViewName("community/manage/communityUpdate");
		return mav;
	}


	
	//get 커뮤니티 게시판 관리 - 게시판 목록 ( 게시판 이름, 운영자, 설정버튼) 불러오기
	@GetMapping("/community/{cId}/boardDelete")
	public ModelAndView deleteBoardLists(@PathVariable("cId") int cId) {
	    ModelAndView mav = new ModelAndView();

	    try {
	        // 사이드바용 게시판 목록
	        Map<String, Object> map = new HashMap<>();
	        map.put("cId", cId);
	        List<BoardDTO> sidebarBoardList = service.boardListByCommunityId(map);

	        // 게시판 + master 이름 + 설정용 목록
	        List<Map<String, Object>> boardLists = service.boardListWithMaster(cId);

	        // ✅ 콘솔에 boardLists 내용 출력
	        System.out.println("boardLists = " + boardLists);

	        mav.addObject("sidebarBoardList", sidebarBoardList);
	        mav.addObject("boardLists", boardLists);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    mav.addObject("cId", cId);
	    mav.setViewName("community/manage/communityBoardDelete");
	    return mav;
	}
	
	// post 커뮤니티 게시판 관리 - 게시판 삭제  
	@PostMapping("/community/{cId}/boardDelete")
	public ModelAndView deleteBoards(@PathVariable("cId") int cId,
	                                 @RequestParam(value = "boardIds", required = false) List<Integer> boardIds) {
	    ModelAndView mav = new ModelAndView();
	    String msg;

	    if (boardIds == null || boardIds.isEmpty()) {
	        msg = "선택된 게시판이 없습니다.";
	    } else {
	        try {
	            int result = service.deleteBoards(boardIds);
	            msg = result > 0 ? "게시판 삭제되었습니다!" : "삭제할 게시판이 없습니다.";
	        } catch (Exception e) {
	            e.printStackTrace();
	            msg = "삭제 중 오류가 발생했습니다.";
	        }
	    }

	    mav.addObject("msg", msg);
	    mav.addObject("goUrl", "/community/" + cId + "/boardDelete");
	    mav.setViewName("community/manage/communityMsg");
	    mav.addObject("msg", "삭제가 완료되었습니다.");
	    mav.addObject("goUrl", "/community/" + cId + "/boardDelete");
	    mav.setViewName("community/communityMsg");
	    return mav;
	}

	
	
	//커뮤니티 멤버 관리
	@GetMapping("communityMember")
	public String communityMember() {
		return "community/manage/communityMember";
	}
	
	
	//커뮤니티 페쇄 URL 이동
	@GetMapping("/community/{id}/close")
	public ModelAndView closePage(@PathVariable("id") String id) {
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("id", id); 
	    //<input type="hidden" name="id" th:value="${id}">
	    //통해 값을 담았음 th:vallue
	    mav.setViewName("community/manage/communityClose");
	    return mav;
	}
	
	
	//커뮤니티 폐쇄 기능
	@PostMapping("/community/{cId}/close")
	public ModelAndView deleteCommunity(@PathVariable("cId") int cId) {	
		 
		int result = 0;
	    String msg = null;
	    try {
	    	result = service.deleteCommunity(cId);
			msg = result > 0 ? "커뮤니티삭제 성공" : "커뮤니티삭제 실패";
			
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg); 
		mav.setViewName("community/communityMsg");
		return mav;
	}

	//////////////////////////////////////////////////////////////////

	//게시판(board) create 생성 url 이동
	@GetMapping("/community/{cId}/board/create")
	public ModelAndView boardCreateForm(@PathVariable("cId") int cId) {
	    List<CommunityDTO> communities = null; //커뮤니티 목록들 
	    List<BoardDTO> boardLists = null; //게시판 목록들

	    try {
	        communities = service.communityList(); //커뮤니티 목록들

	        Map<String, Object> map = new HashMap<>();
	        map.put("cId", cId);     //map= {"cid" : 3} 의미임 !!! cid가 3일때
	        boardLists = service.boardListByCommunityId(map); // map
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    ModelAndView mav = new ModelAndView();
	    mav.addObject("communities", communities); //커뮤니티 목록들
	    mav.addObject("boardLists", boardLists);  //선택된 커뮤니티의 게시판 목록들
	    mav.addObject("cId", cId);
	    mav.setViewName("community/board/boardCreate");
	    return mav;
	}
	    

//게시판(board) create 생성 기능
	@PostMapping("/community/{cId}/board/create")
	public ModelAndView boardCreate(@PathVariable("cId") int cId, BoardDTO bdto) {
		bdto.setCommunity_id(cId);
		int result = 0;
	    String msg = null;

	    try {
	    		result = service.insertBoard(bdto);
	    } catch (Exception e) {
	        	e.printStackTrace();
	    }

	    msg = result > 0 ? "게시판 생성 성공!" : "게시판 생성 실패!";

	    String goUrl = result > 0 ? 
	    			"/community/" + bdto.getCommunity_id() + "/board/" + bdto.getId() :
	    	    	"/community/" + cId + "/board/create";
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("msg", msg);
	    mav.addObject("goUrl", goUrl);
	    mav.setViewName("community/board/boardMsg");
	    return mav;
	
	}
	//////////////////////////////////////////////////////////

	
	//게시판 수정
	
	
	
	
	// (게시판별 home) 게시글 list
	@GetMapping("/community/{cId}/board/{boardId}")
	public ModelAndView postList(@PathVariable int cId,
	                             @PathVariable int boardId) {
	    ModelAndView mav = new ModelAndView();
	    try {
	        // 사이드바용 : 커뮤니티의 게시판 목록
	        Map<String, Object> map = new HashMap<>();
	        map.put("cId", cId);
	        List<BoardDTO> boardLists = service.boardListByCommunityId(map);

	        // 현재 게시판의 게시글 목록
	        List<PostDTO> postLists = service.postListByBoardId(boardId);

	        // 게시판명 + 커뮤니티명 가져오기
	        Map<String, String> names = service.selectBoardAndCommunity(boardId);

	        // 필요한 데이터 뷰에 전달
	        mav.addObject("boardLists", boardLists);
	        mav.addObject("postLists", postLists);
	        mav.addObject("cId", cId);
	        mav.addObject("boardId", boardId);
	        mav.addObject("communityName", names.get("community_name"));
	        mav.addObject("boardName", names.get("board_name"));

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    mav.setViewName("community/board/postList");
	    return mav;
	}
	
	
/////////////////////////////////////////////////////////////////////
	
	// 게시글(post) 작성 url 이동
	@GetMapping("/community/{cId}/board/{boardId}/write")
	public ModelAndView postWrite(@PathVariable("cId") int cId,
	                              @PathVariable("boardId") int boardId) {
	    ModelAndView mav = new ModelAndView();

	    try {
	    	 // 해당 커뮤니티의 게시판 목록 (사이드바용 wj정보임 )
	        Map<String, Object> map = new HashMap<>();
	        map.put("cId", cId);  // map에 cid : 3 정보 넣어줌 key,value
	        List<BoardDTO> boardList = service.boardListByCommunityId(map); //3(value)에 대한 게시판 목록들 
	        mav.addObject("boardList", boardList); // 사이드바에 사용될 게시판 목록 전달
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    mav.addObject("cId", cId); // 커뮤니티 ID
	    mav.addObject("boardId", boardId); // 게시판 ID
	    mav.setViewName("community/board/postWrite");  // 게시글 작성 페이지로 이동
	    return mav;
	}

	
	
	// 게시글 작성 + 이미지 업로드
	@PostMapping("/community/{cId}/board/{boardId}/write")
	public ModelAndView postWriteSubmit(@PathVariable int cId,
	                                    @PathVariable int boardId,
	                                    PostDTO pdto,
	                                    @RequestParam(value = "images", required = false) MultipartFile[] images,
	                                    HttpServletRequest request) {

	    HttpSession session = request.getSession();
	    MemberDTO udto = (MemberDTO) session.getAttribute(com.cbo.constant.MemberConst.USER_KEY);

	    if (udto == null) {
	        ModelAndView mav = new ModelAndView("community/board/postMsg");
	        mav.addObject("msg", "로그인 해주세요.");
	        mav.addObject("goUrl", "/memberLogin");
	        return mav;
	    }

	    try {
	        pdto.setBoard_id(boardId);
	        pdto.setMember_id(udto.getId());

	        int result = service.insertPost(pdto);
	        int postId = pdto.getId();

	        String msg;
	        String goUrl;

	        if (result > 0) {
	            if (images != null && images.length > 0) {
	                String uploadPath = "C:/upload/postImages/";
	                java.io.File uploadDir = new java.io.File(uploadPath);
	                if (!uploadDir.exists()) {
	                    uploadDir.mkdirs(); // 경로 없으면 생성
	                }

	                for (MultipartFile image : images) {
	                    if (!image.isEmpty()) {
	                        String originalName = image.getOriginalFilename();
	                        String extension = "";
	                        if (originalName != null && originalName.contains(".")) {
	                            extension = originalName.substring(originalName.lastIndexOf("."));
	                        }
	                        String savedName = java.util.UUID.randomUUID().toString() + extension;

	                        java.io.File dest = new java.io.File(uploadPath + savedName);
	                        image.transferTo(dest);

	                        // DB 등록
	                        ImageDTO idto = new ImageDTO();
	                        idto.setPost_id(postId);
	                        idto.setMember_id(udto.getId());
	                        idto.setSaved_name(savedName);
	                        idto.setOriginal_name(originalName);

	                        service.insertImage(idto);
	                    }
	                }
	            }

	            msg = "게시글 작성 성공!";
	            goUrl = "/community/" + cId + "/board/" + boardId + "/post/" + postId;
	        } else {
	            msg = "게시글 작성 실패!";
	            goUrl = "/community/" + cId + "/board/" + boardId + "/write";
	        }

	        ModelAndView mav = new ModelAndView("community/board/postMsg");
	        mav.addObject("msg", msg);
	        mav.addObject("goUrl", goUrl);
	        return mav;

	    } catch (Exception e) {
	        e.printStackTrace();
	        ModelAndView mav = new ModelAndView("community/board/postMsg");
	        mav.addObject("msg", "에러 발생!");
	        mav.addObject("goUrl", "/community/" + cId + "/board/" + boardId + "/write");
	        return mav;
	    }
	}

	// 본문 보기
	@GetMapping("/community/{cId}/board/{boardId}/post/{postId}")
	public ModelAndView postContent(@PathVariable int cId,
	                                @PathVariable int boardId,
	                                @PathVariable int postId) {
	    ModelAndView mav = new ModelAndView();

	    try {
	    
	    //게시판 목록 정보
	        Map<String, Object> map = new HashMap<>();
	        map.put("cId", cId);
	        List<BoardDTO> boardList = service.boardListByCommunityId(map);
	        mav.addObject("boardList", boardList); 
	     
	    //게시글 정보
	        PostDTO post = service.selectPostById(postId); 
	        mav.addObject("post", post);
	    
	    // 이미지 정보
	        List<ImageDTO> imageList = service.selectImagesByPostId(postId);
	        mav.addObject("imageList", imageList); 

	      // 조회수 늘리기
	        service.ViewNumPlus(postId);
	       
	      
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    mav.addObject("cId", cId);
	    mav.addObject("boardId", boardId);
	    mav.setViewName("community/board/postContent");
	    return mav;
	}
	


	@PostMapping("/community/{cId}/board/{boardId}/post/{postId}/like")
	@ResponseBody
	public Map<String, Object> upvote(@PathVariable int postId) {
	    Map<String, Object> result = new HashMap<>();
	    try {
	        service.upvotePlus(postId); // 좋아요 +1
	        int upvote = service.selectPostById(postId).getUpvote(); // 최신 좋아요 수 가져오기
	        result.put("status", "success");
	        result.put("upvote", upvote);  // upvote 값을 JSON에 담아줌
	    } catch (Exception e) {
	        e.printStackTrace();
	        result.put("status", "error");
	        result.put("msg", "좋아요 처리 실패");
	    }
	    return result;
	}
	
	
	
	//게시글 수정 url 이동
	@GetMapping("/community/{cId}/board/{boardId}/post/{postId}/edit")
	public ModelAndView postEditUrl(@PathVariable int cId,
	                                @PathVariable int boardId,
	                                @PathVariable int postId) {
	    ModelAndView mav = new ModelAndView();

	    try {
	        Map<String, Object> map = new HashMap<>();
	        map.put("cId", cId);
	        List<BoardDTO> boardList = service.boardListByCommunityId(map);
	        mav.addObject("boardList", boardList);

	        PostDTO post = service.selectPostById(postId);
	        mav.addObject("post", post);

	        List<ImageDTO> imageList = service.selectImagesByPostId(postId);
	        mav.addObject("imageList", imageList);  // d이미지 불러오기 임 이거

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    mav.addObject("cId", cId);
	    mav.addObject("boardId", boardId);
	    mav.setViewName("community/board/postEdit");
	    return mav;
	}
	
	//게시글 수정 기능
	@PostMapping("/community/{cId}/board/{boardId}/post/{postId}/edit")
	public ModelAndView editPostSubmit(@PathVariable int cId,
	                                   @PathVariable int boardId,
	                                   @PathVariable int postId,
	                                   PostDTO pdto,
	                                   @RequestParam(value = "deleteImageIds", required = false) int[] deleteImageIds,
	                                   @RequestParam(value = "images", required = false) MultipartFile[] images,
	                                   HttpServletRequest request) {
	    pdto.setId(postId);
	    HttpSession session = request.getSession();
	    MemberDTO udto = (MemberDTO) session.getAttribute(com.cbo.constant.MemberConst.USER_KEY);
	    String path = "C:/upload/postImages/";
	    ModelAndView mav = new ModelAndView("community/board/postMsg");
	    String msg;
	    String goUrl;

	    try {
	        int result = service.updatePost(pdto);

	        if (result > 0) {
	            // 이미지 삭제
	            if (deleteImageIds != null) {
	                for (int imageId : deleteImageIds) {
	                    ImageDTO img = service.selectImageById(imageId);
	                    if (img != null) {
	                        service.deleteImage(imageId);
	                        File file = new File(path + img.getSaved_name());
	                        if (file.exists()) file.delete();
	                    }
	                }
	            }

	            // 이미지 추가
	            if (images != null) {
	                new File(path).mkdirs();
	                for (MultipartFile file : images) {
	                    if (!file.isEmpty()) {
	                        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
	                        String saveName = java.util.UUID.randomUUID() + ext;
	                        file.transferTo(new File(path + saveName));

	                        ImageDTO idto = new ImageDTO();
	                        idto.setPost_id(postId);
	                        idto.setMember_id(udto.getId());
	                        idto.setSaved_name(saveName);
	                        idto.setOriginal_name(file.getOriginalFilename());
	                        service.insertImage(idto);
	                    }
	                }
	            }

	            msg = "게시글 수정 성공!";
	            goUrl = "/community/" + cId + "/board/" + boardId + "/post/" + postId;

	        } else {
	            msg = "게시글 수정 실패!";
	            goUrl = "/community/" + cId + "/board/" + boardId + "/post/" + postId + "/edit";
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        msg = "에러 발생!";
	        goUrl = "/community/" + cId + "/board/" + boardId + "/post/" + postId + "/edit";
	    }

	    mav.addObject("msg", msg);
	    mav.addObject("goUrl", goUrl);
	    return mav;
	}

	

	
	//게시글 삭제
	@PostMapping("/community/{cId}/board/{boardId}/post/{postId}/delete")
	public ModelAndView deletePost(@PathVariable int cId,
	                               @PathVariable int boardId,
	                               @PathVariable int postId) {
	    ModelAndView mav = new ModelAndView();
	    String msg;
	    String goUrl;

	    int result = 0;

	    try {
	        result = service.deletePost(postId);
	    } catch (Exception e) {
	        e.printStackTrace();
	        result = -1; // 
	    }

	    if (result > 0) {
	        msg = "게시글 삭제 성공!";
	        goUrl = "/community/" + cId + "/board/" + boardId;
	    } else {
	        msg = "게시글 삭제 실패!";
	        goUrl = "/community/" + cId + "/board/" + boardId + "/post/" + postId;
	    }

	    mav.addObject("msg", msg);
	    mav.addObject("goUrl", goUrl);
	    mav.setViewName("community/board/postMsg");
	    return mav;
	}


	// 댓글 등록
//	@PostMapping("/community/{cId}/board/{boardId}/post/{postId}/reply")
//	@ResponseBody
//	public Map<String, Object> insertReply(@PathVariable int postId,
//	                                       @RequestBody ReplyDTO rdto,
//	                                       HttpSession session) {
//	    Map<String, Object> result = new HashMap<>();
//	    try {
//	        MemberDTO user = (MemberDTO) session.getAttribute("user");
//	        if (user == null) {
//	            result.put("status", "fail");
//	            result.put("msg", "로그인이 필요합니다.");
//	            return result;
//	        }
//	        rdto.setBoard_post_id(postId);
//	        rdto.setMember_id(user.getId());
//	        service.insertReply(rdto);
//	        result.put("status", "success");
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        result.put("status", "error");
//	    }
//	    return result;
//	}


	// 댓글 등록
	@PostMapping("/community/{cId}/board/{boardId}/post/{postId}/reply")
	@ResponseBody
	public Map<String, Object> insertReply(@PathVariable int cId,
	                                       @PathVariable int boardId,
	                                       @PathVariable int postId,
	                                       @RequestBody ReplyDTO rdto,
	                                       HttpSession session) {
	    Map<String, Object> result = new HashMap<>();
	    try {
	        MemberDTO user = (MemberDTO) session.getAttribute(com.cbo.constant.MemberConst.USER_KEY);
	        if (user == null) {
	            result.put("status", "fail");
	            return result;
	        }
	        rdto.setBoard_post_id(postId);
	        rdto.setMember_id(user.getId());
	        service.insertReply(rdto);
	        result.put("status", "success");
	    } catch (Exception e) {
	        e.printStackTrace();
	        result.put("status", "error");
	    }
	    return result;
	}

	// 댓글 수정
	@PostMapping("/community/{cId}/board/{boardId}/post/{postId}/reply/{replyId}/edit")
	@ResponseBody
	public Map<String, Object> updateReply(@PathVariable int cId,
	                                       @PathVariable int boardId,
	                                       @PathVariable int postId,
	                                       @PathVariable int replyId,
	                                       @RequestBody ReplyDTO rdto,
	                                       HttpSession session) {
	    Map<String, Object> result = new HashMap<>();
	    try {
	        MemberDTO user = (MemberDTO) session.getAttribute(com.cbo.constant.MemberConst.USER_KEY);
	        if (user == null) {
	            result.put("status", "fail");
	            return result;
	        }
	        rdto.setId(replyId);
	        rdto.setMember_id(user.getId());
	        int updated = service.updateReply(rdto);
	        result.put("status", updated > 0 ? "success" : "fail");
	    } catch (Exception e) {
	        e.printStackTrace();
	        result.put("status", "error");
	    }
	    return result;
	}

	// 댓글 삭제
	@PostMapping("/community/{cId}/board/{boardId}/post/{postId}/reply/{replyId}/delete")
	@ResponseBody
	public Map<String, Object> deleteReply(@PathVariable int cId,
	                                       @PathVariable int boardId,
	                                       @PathVariable int postId,
	                                       @PathVariable int replyId,
	                                       HttpSession session) {
	    Map<String, Object> result = new HashMap<>();
	    try {
	        MemberDTO user = (MemberDTO) session.getAttribute(com.cbo.constant.MemberConst.USER_KEY);
	        if (user == null) {
	            result.put("status", "fail");
	            return result;
	        }
	        int deleted = service.deleteReply(replyId);
	        result.put("status", deleted > 0 ? "success" : "fail");
	    } catch (Exception e) {
	        e.printStackTrace();
	        result.put("status", "error");
	    }
	    return result;
	}

	// 댓글 목록 조회
	@GetMapping("/community/{cId}/board/{boardId}/post/{postId}/replies")
	@ResponseBody
	public List<ReplyDTO> getReplies(@PathVariable int cId,
	                                 @PathVariable int boardId,
	                                 @PathVariable int postId) {
	    try {https://chatgpt.com/c/685e45dd-d3c4-8012-b7a5-1c65de1d31b2
	        return service.selectReplyByPostId(postId);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ArrayList<>();
	    }
	}

}
