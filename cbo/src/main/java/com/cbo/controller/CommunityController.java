package com.cbo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cbo.community.model.BoardDTO;
import com.cbo.community.model.CommunityDTO;
import com.cbo.community.model.PostDTO;
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
	        map.put("cId", cId); // ← MyBatis가 쓸 key

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

	
	// 커뮤니티 정보 수정
	@GetMapping("community/{cId}Update")
	public ModelAndView communityUpdate(@PathVariable("cId")String cId) {
		//model.addAttribute("id", id);
		ModelAndView mav=new ModelAndView();
		mav.addObject("cId", cId);
		mav.setViewName("community/manage/communityUpdate");
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
	///
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
	    
		int result = 0;
	    String msg = null;

	    try {
	    		result = service.insertBoard(bdto);
	    } catch (Exception e) {
	        	e.printStackTrace();
	    }

	    msg = result > 0 ? "게시판 생성 성공!" : "게시판 생성 실패!";
	    String goUrl = result > 0 ? "/community/" + bdto.getCommunity_id() : "/community/" + cId + "/board/create";

	    ModelAndView mav = new ModelAndView();
	    mav.addObject("msg", msg);
	    mav.addObject("goUrl", goUrl);
	    mav.setViewName("community/board/boardMsg");
	    return mav;
	
	}
	//////////////////////////////////////////////////////////
	
	//postList 목록 맨 첫화면 (각각 다름))
	@GetMapping("/community/{cId}/board/{boardId}")
	public ModelAndView postListUrl(@PathVariable("cId") int cId,
            						@PathVariable("boardId") int boardId) {
		 
		Map<String, Object> map = new HashMap<>();
		    map.put("cId", cId);
		    map.put("boardId", boardId);
		
		ModelAndView mav = new ModelAndView();
		    mav.addObject("boardId", boardId);
		    mav.addObject("cId", cId);
		    mav.setViewName("community/board/postList");
		    return mav;
	}
	
	//postList 로 url이동  	//게시판 목록 post목록
	@PostMapping("/community/{cId}/board/{boardId}")
	public ModelAndView postList(@PathVariable("cId") int cId,
	                             @PathVariable("boardId") int boardId) {

	    Map<String, Object> map = new HashMap<>();
	    map.put("cId", cId);
	    map.put("boardId", boardId);

	    //List<BoardDTO> boardLists = service.boardListByCommunityId(map);
	    //List<PostDTO> postLists = service.postListByBoardId(boardId); // ← 이 메서드 필요

	    ModelAndView mav = new ModelAndView();
	    //mav.addObject("boardLists", boardLists);
	    //mav.addObject("postLists", postLists);
	    mav.addObject("boardId", boardId);
	    mav.addObject("cId", cId);
	    mav.addObject("communityName", "실제값ㅅ"); // 
	    mav.addObject("boardName", "게시판이름"); // 실제 게시판 이름 가져오기
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

	
	// 게시글(post) 작성 기능 (사진은 나중에 넣을거임 )
	@PostMapping("/community/{cId}/board/{boardId}/write")
	public ModelAndView postWriteSubmit(@PathVariable int cId,
	                                    @PathVariable int boardId,
	                                    PostDTO pdto,
	                                    @RequestParam(value = "images", required = false) MultipartFile[] images,
	                                    HttpServletRequest request) {

	    HttpSession session = request.getSession();
	    MemberDTO udto = (MemberDTO) session.getAttribute(com.cbo.constant.MemberConst.USER_KEY);

	    if (udto == null) {
	        ModelAndView mav = new ModelAndView();
	        mav.addObject("msg", "로그인 해주세요.");
	        mav.addObject("goUrl", "/memberLogin");
	        mav.setViewName("community/board/postMsg");
	        return mav;
	    }

	    try {
	        pdto.setBoard_id(boardId);  // URL boardId 고정
	        pdto.setMember_id(udto.getId());

	        int result = service.insertPost(pdto);
	        int postId = pdto.getId();

	        String msg;
	        String goUrl;

	        if (result > 0) {
	            msg = "게시글 작성 성공!";
	            goUrl = "/community/" + cId + "/board/" + boardId + "/post/" + postId;

	            // TODO: 이미지 업로드 자리 (MultipartFile[] 처리)
	        } else {
	            msg = "게시글 작성 실패!";
	            goUrl = "/community/" + cId + "/board/" + boardId + "/write";
	        }

	        ModelAndView mav = new ModelAndView();
	        mav.addObject("msg", msg);
	        mav.addObject("goUrl", goUrl);
	        mav.setViewName("community/board/postMsg");
	        return mav;

	    } catch (Exception e) {
	        e.printStackTrace();
	        ModelAndView mav = new ModelAndView();
	        mav.addObject("msg", "에러 발생!");
	        mav.addObject("goUrl", "/community/" + cId + "/board/" + boardId + "/write");
	        mav.setViewName("community/board/postMsg");
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
	        Map<String, Object> map = new HashMap<>();
	        map.put("cId", cId);
	        List<BoardDTO> boardList = service.boardListByCommunityId(map);
	        mav.addObject("boardList", boardList);

	        PostDTO post = service.selectPostById(postId);
	        mav.addObject("post", post);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    mav.addObject("cId", cId);
	    mav.addObject("boardId", boardId);
	    mav.setViewName("community/board/postContent");
	    return mav;
	}










	
}
