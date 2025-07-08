package com.cbo.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import com.cbo.community.model.PostListDTO;
import com.cbo.community.model.ReplyDTO;
import com.cbo.community.service.CommunityService;
import com.cbo.config.WebSocketConfig;
import com.cbo.constant.CommunityConst;
import com.cbo.constant.MemberConst;
import com.cbo.member.model.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CommunityController {

	private final AddrController addrController;

	private final WebSocketConfig webSocketConfig;
	private final IndexController indexController;

	private boolean checkLogin(HttpSession session) {
		return session.getAttribute(com.cbo.constant.MemberConst.USER_KEY) != null;
	}

	@Autowired
	private CommunityService service;

	public CommunityController(IndexController indexController, WebSocketConfig webSocketConfig,
			AddrController addrController) {
		this.indexController = indexController;
		this.webSocketConfig = webSocketConfig;
		this.addrController = addrController;

	}
	/// MAIN ///////////////////

	/// mainNewest 최신글 불러오기 5개
	@GetMapping("/communityMainNewest")
	public ModelAndView communityMainNewest(HttpSession session) {
		List<Map<String, Object>> newestPosts = null; // 최신글 5개
		List<Map<String, Object>> sideJoin = null; // 가입한 커뮤니티 목록

		try {
			// 로그인한 사용자 정보 가져오기
			MemberDTO user = (MemberDTO) session.getAttribute(com.cbo.constant.MemberConst.USER_KEY);

			// 가입한 커뮤니티 목록 가져오기
			sideJoin = service.communityMainJoin(user.getId());

			// 최신글 5개 가져오기
			newestPosts = service.newestPosts();
		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("sideJoin", sideJoin); // 가입한 커뮤니티 목록
		mav.addObject("newestPosts", newestPosts); // 최신글 목록
		mav.setViewName("community/communityMainNewest"); // 뷰 설정

		return mav;
	}

	// 커뮤니티 가입 목록들
	@GetMapping("/communityMainJoin")
	public ModelAndView communityMainJoin(HttpSession session) {

		ModelAndView mav = new ModelAndView();

		try {
			MemberDTO user = (MemberDTO) session.getAttribute(com.cbo.constant.MemberConst.USER_KEY);

			if (user == null) {
				mav.setViewName("redirect:/memberLogin");
				return mav;
			}

			// user != null 이 보장됨
			List<Map<String, Object>> joinList = service.joinList(user.getId());
			mav.addObject("joinList", joinList);

			List<Map<String, Object>> lists = service.communityMainJoin(user.getId());
			mav.addObject("lists", lists);

			mav.setViewName("community/communityMainJoin");
			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("community/communityMsg");
			mav.addObject("msg", "가입 커뮤니티 목록 로드 중 오류 발생");
			mav.addObject("goUrl", "/communityMainNewest");
		}

		return mav;
	}

	// role = 'pending' → 가입 신청

	// role = 'user', 'submaster', 'master' → 가입

	// 값이 없으면 → 미가입

	// 커뮤니티 전체 목록 조회 (로그인 상태에 따라 가입 상태 포함)
	@GetMapping("/communityMainAll")
	public ModelAndView communityMainAll(HttpSession session) {
		ModelAndView mav = new ModelAndView("community/communityMainAll");

		// 로그인 체크
		MemberDTO user = (MemberDTO) session.getAttribute(MemberConst.USER_KEY);
		if (user == null) {
			mav.addObject("msg", "로그인이 필요합니다.");
			mav.addObject("goUrl", "/memberLogin");
			mav.setViewName("community/communityMsg");
			return mav;
		}

		try {
			int memberId = user.getId();
			// 가입 상태 포함 커뮤니티 전체 조회
			List<Map<String, Object>> lists = service.selectCommunityMainAll(memberId);
			mav.addObject("lists", lists);
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("msg", "데이터 조회 중 오류가 발생했습니다.");
			mav.addObject("goUrl", "/");
			mav.setViewName("community/communityMsg");
		}

		return mav;
	}

	// ✅ 커뮤니티 가입 신청 처리
	@PostMapping("/community/{cId}/join")
	public String joinCommunity(@PathVariable int cId, HttpSession session) {
		MemberDTO user = (MemberDTO) session.getAttribute(MemberConst.USER_KEY);
		if (user == null) {
			return "redirect:/memberLogin";
		}

		try {
			String status = service.getJoinStatus(cId, user.getId());
			if ("가입대기".equals(status)) {
				return "redirect:/communityMainAll";
			}
			service.requestJoin(cId, user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/communityMainAll";
	}

	// ✅ 커뮤니티 탈퇴 처리
	@PostMapping("/community/{cId}/leave")
	public String leaveCommunity(@PathVariable int cId, HttpSession session) {
		try {
			MemberDTO user = (MemberDTO) session.getAttribute(MemberConst.USER_KEY);
			service.leaveCommunity(cId, user.getId());

		} catch (Exception e) {
			e.printStackTrace();
			// return "redirect:/errorPage";
		}

		return "redirect:/communityMainAll";
	}

////////////////////////////////가입 상태 확인   //////////////////////////////////
//개별 커뮤니티 접근 시 컨트롤러/서비스에서 권한 체크용
	private boolean isApprovedMember(int cId, HttpSession session) throws Exception {
		MemberDTO user = (MemberDTO) session.getAttribute(MemberConst.USER_KEY);
		if (user == null)
			return false;
		String status = service.getJoinStatus(cId, user.getId());
		return "user".equals(status) || "submaster".equals(status) || "master".equals(status);
	}

	////////////////////////////////////////
	// 커뮤니티 manage 폴더 관리

	// 커뮤니티 생성 get방식 이동
	@GetMapping("/communityCreate")
	public ModelAndView showCreateForm() {
		List<CommunityDTO> lists = null;
		try {
			lists = service.communityList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("lists", lists);
		mav.setViewName("community/manage/communityCreate");
		return mav;
	}

	// 커뮤니티 생성 post방식
	@PostMapping("/communityCreate")
	public ModelAndView insertCommunity(CommunityDTO dto, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberDTO udto = (MemberDTO) session.getAttribute(com.cbo.constant.MemberConst.USER_KEY);

		if (udto == null) {
			ModelAndView login = new ModelAndView();
			login.addObject("msg", "로그인 해주세요.");
			login.addObject("goUrl", "memberLogin");
			login.setViewName("community/communityMsg");
			return login;
		}

		dto.setMember_id(udto.getId());

		String msg;
		String goUrl;
		try {
			int result = service.insertCommunity(dto);
			if (result > 0) {
				msg = "커뮤니티 생성되었습니다.";
				goUrl = "/community/" + dto.getId();
			} else {
				msg = "커뮤니티 생성 실패.";
				goUrl = "communityCreate";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "커뮤니티 생성 중 오류 발생.";
			goUrl = "communityCreate";
		}

		ModelAndView mav = new ModelAndView("community/communityMsg");
		mav.addObject("msg", msg);
		mav.addObject("goUrl", goUrl);
		return mav;
	}

	// 커뮤니티 home 각 커뮤니티의 첫 화면
	@GetMapping("/community/{cId}")
	public ModelAndView communityHome(@PathVariable("cId") int cId, HttpSession session) {
		ModelAndView mav = new ModelAndView();

		try {
			// 현재 로그인 사용자
			  MemberDTO udto = (MemberDTO) session.getAttribute(MemberConst.USER_KEY);

		        if (udto != null) {
		            int memberId = udto.getId();

		            // 🔑 현재 사용자가 해당 커뮤니티에서 어떤 역할인지 조회 (master / submaster / user / null)
		            String userRole = service.getCommunityRole(cId, memberId);
		            mav.addObject("userRole", userRole);
		        } else {
		            // 🔑 로그인하지 않았으면 userRole = null
		            mav.addObject("userRole", null);
		        }

			// 공통 데이터
			List<BoardDTO> sidebarBoardLists = service.boardListByCommunityId(Map.of("cId", cId));
			List<Map<String, Object>> sidebarMemberLists = service.sidebarMemberList(cId);
			CommunityDTO communityInfo = service.communityInfoById(cId);
			List<PostListDTO> postLists = service.selectPostListByCommunityId(cId);

			mav.addObject("sidebarBoardLists", sidebarBoardLists);
			mav.addObject("sidebarMemberLists", sidebarMemberLists);
			mav.addObject("communityInfo", communityInfo);
			mav.addObject("postLists", postLists);
			mav.addObject("cId", cId);

		} catch (Exception e) {
			e.printStackTrace();
		}

		mav.setViewName("community/manage/communityHome");
		return mav;
	}

	// 커뮤니티 수정
	@GetMapping("/community/{cId}/update")
	public ModelAndView communityUpdate(@PathVariable int cId, HttpSession session) throws Exception {
		MemberDTO user = (MemberDTO) session.getAttribute(MemberConst.USER_KEY);
		if (user == null) {
			return new ModelAndView("redirect:/memberLogin");
		}

		String role = service.getCommunityRole(cId, user.getId());
		if (!CommunityConst.MASTER.equals(role) && !CommunityConst.SUBMASTER.equals(role)) {
			return new ModelAndView("community/manage/accessDenied");
		}

		ModelAndView mav = new ModelAndView("community/manage/communityUpdate");
		mav.addObject("communityInfo", service.communityInfoById(cId));
		mav.addObject("currentMaster", service.selectCurrentMaster(cId));
		mav.addObject("currentSubMasters", service.selectCurrentSubmasters(cId));
		mav.addObject("sidebarBoardLists", service.boardListByCommunityId(Map.of("cId", cId)));
		mav.addObject("sidebarMemberLists", service.sidebarMemberList(cId));
		mav.addObject("cId", cId);
		mav.addObject("role", role); // 권한 전달 추가

		return mav;
	}

	// 커뮤니티 수정 기능
	@PostMapping("/community/{cId}/update")
	@ResponseBody
	public Map<String, Object> updateCommunity(@PathVariable int cId, @RequestBody Map<String, Object> body,
			HttpSession session) throws Exception {

		MemberDTO user = (MemberDTO) session.getAttribute(MemberConst.USER_KEY);
		String role = service.getCommunityRole(cId, user.getId());

		// 권한 확인 후 마스터가 아니면 마스터 및 부마스터 변경 차단
		if (!CommunityConst.MASTER.equals(role)) {
			body.remove("masterId");
			body.remove("subMasterIds");
		}

		String name = (String) body.get("name");
		String description = (String) body.get("description");

		Integer masterId = null;
		Object masterIdObj = body.get("masterId");
		if (masterIdObj instanceof Number) {
			masterId = ((Number) masterIdObj).intValue();
		} else if (masterIdObj instanceof String && !((String) masterIdObj).isEmpty()) {
			masterId = Integer.parseInt((String) masterIdObj);
		}

		List<Integer> subMasterIds = new ArrayList<>();
		Object subMasterObj = body.get("subMasterIds");
		if (subMasterObj instanceof List<?>) {
			for (Object obj : (List<?>) subMasterObj) {
				if (obj instanceof Number) {
					subMasterIds.add(((Number) obj).intValue());
				} else if (obj instanceof String) {
					subMasterIds.add(Integer.parseInt((String) obj));
				}
			}
		}

		CommunityDTO cdto = new CommunityDTO();
		cdto.setId(cId);
		cdto.setName(name);
		cdto.setDescription(description);

		service.updateCommunityInfo(cdto);

		if (CommunityConst.MASTER.equals(role)) {
			service.updateMasterRole(cId, masterId);

			for (Map<String, Object> m : service.selectJoinAllMembers(cId)) {
				int mId = (int) m.get("MEMBER_ID");
				String currentRole = (String) m.get("ROLE");
				if (subMasterIds.contains(mId)) {
					service.assignSubmaster(cId, mId);
				} else if ("submaster".equals(currentRole)) {
					service.removeSubmaster(cId, mId);
				}
			}
		}

		Map<String, Object> result = new HashMap<>();
		result.put("status", "success");
		result.put("msg", "커뮤니티 정보가 수정되었습니다.");
		return result;
	}

	@GetMapping("/community/{cId}/masterSelect")
	public ModelAndView masterSelect(@PathVariable int cId) throws Exception {
		ModelAndView mav = new ModelAndView("community/manage/masterSelect");
		mav.addObject("members", service.selectJoinAllMembers(cId));
		mav.addObject("cId", cId);
		return mav;
	}

	@GetMapping("/community/{cId}/subMasterSelect")
	public ModelAndView subMasterSelect(@PathVariable int cId) throws Exception {
		ModelAndView mav = new ModelAndView("community/manage/subMasterSelect");
		mav.addObject("members", service.selectJoinAllMembers(cId));
		mav.addObject("cId", cId);
		return mav;
	}

	/////////////////////////////////////////////////

	// GET - 커뮤니티 게시판 관리 (게시판 목록 + 운영자 정보)
	@GetMapping("/community/{cId}/boardDelete")
	public ModelAndView deleteBoardLists(@PathVariable("cId") int cId) {
		ModelAndView mav = new ModelAndView();
		CommunityDTO communityInfo = null;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("cId", cId);
			List<BoardDTO> sidebarBoardList = service.boardListByCommunityId(map);
			List<Map<String, Object>> boardLists = service.boardListWithMaster(cId);
			communityInfo = service.communityInfoById(cId);
			mav.addObject("sidebarBoardList", sidebarBoardList);
			mav.addObject("boardLists", boardLists);
		} catch (Exception e) {
			e.printStackTrace();
		}

		mav.addObject("cId", cId);
		mav.setViewName("community/manage/communityBoardDelete");
		mav.addObject("communityInfo", communityInfo);
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
				msg = (result > 0) ? "게시판이 삭제되었습니다!" : "삭제할 게시판이 없습니다.";
			} catch (Exception e) {
				e.printStackTrace();
				msg = "삭제 중 오류가 발생했습니다.";
			}
		}

		mav.addObject("msg", msg);
		mav.addObject("goUrl", "/community/" + cId + "/boardDelete");
		mav.setViewName("community/communityMsg");
		return mav;
	}

	////////////////////////////////////////////////////////////////////////
// 커뮤니티 멤버 

	// 멤버 목록 조회 (가입멤버, 가입대기 구분)
	@GetMapping("/community/{cId}/member")
	public ModelAndView memberList(@PathVariable int cId, @RequestParam(defaultValue = "active") String status) {
		ModelAndView mav = new ModelAndView();
		try {
			List<Map<String, Object>> members = "pending".equals(status) ? service.pendingMemberList(cId)
					: service.joinMemberList(cId);

			List<BoardDTO> sidebarBoardList = service.boardListByCommunityId(Map.of("cId", cId));
			CommunityDTO communityInfo = service.communityInfoById(cId);

			mav.setViewName("community/manage/communityMember");
			mav.addObject("members", members);
			mav.addObject("sidebarBoardList", sidebarBoardList);
			mav.addObject("communityInfo", communityInfo);
			mav.addObject("cId", cId);
			mav.addObject("status", status);

		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("community/communityMsg");
			mav.addObject("msg", "멤버 목록 로드 중 오류 발생");
			mav.addObject("goUrl", "/community/" + cId);
		}
		return mav;
	}

	// 가입 승인
	@PostMapping("/community/{cId}/member/{memberId}/approve")
	public ModelAndView approveMember(@PathVariable int cId, @PathVariable int memberId, HttpSession session) {
		return handleMemberAction(session, () -> {
			service.approveMember(cId, memberId);
			return "가입 승인이 완료되었습니다.";
		}, "/community/" + cId + "/member?status=pending");
	}

	// 가입 거절
	@PostMapping("/community/{cId}/member/{memberId}/reject")
	public ModelAndView rejectMember(@PathVariable int cId, @PathVariable int memberId, HttpSession session) {
		return handleMemberAction(session, () -> {
			service.rejectMember(cId, memberId);
			return "가입 요청을 거절했습니다.";
		}, "/community/" + cId + "/member?status=pending");
	}

	// 멤버 탈퇴
	@PostMapping("/community/{cId}/member/{memberId}/remove")
	public ModelAndView removeMember(@PathVariable int cId, @PathVariable int memberId, HttpSession session) {
		return handleMemberAction(session, () -> {
			service.removeMember(cId, memberId);
			return "멤버 탈퇴 완료"; // ㅛ서비시Imple에서 마스터는 탈퇴할 수 없도록 막음 마스ㅊ터면 IllegalStateException을 던짐
		}, "/community/" + cId + "/member?status=active");
	}

	// 공통 액션 처리 메서드
	private ModelAndView handleMemberAction(HttpSession session, ActionExecutor executor, String goUrl) {
		ModelAndView mav = new ModelAndView("community/communityMsg");

		if (session.getAttribute(com.cbo.constant.MemberConst.USER_KEY) == null) {
			mav.addObject("msg", "로그인이 필요합니다.");
			mav.addObject("goUrl", "/memberLogin");
			return mav;
		}

		try {
			String msg = executor.execute();
			mav.addObject("msg", msg);
		} catch (IllegalStateException ise) { // 마스터 탈퇴 불가 메시지 출력
			mav.addObject("msg", ise.getMessage()); // 마스터 탈퇴 막음
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("msg", "처리 중 오류가 발생했습니다.");
		}

		mav.addObject("goUrl", goUrl);
		return mav;
	}

	// 액션 실행용 인터페이스
	@FunctionalInterface
	interface ActionExecutor {
		String execute() throws Exception;
	}

	/////////////////////////// 초대 ///////////////////////////////////////

	// 초대 메인 페이지 (사이드바 + 멤버 초대 버튼 있는 화면)
	@GetMapping("/community/{cId}/invite")
	public ModelAndView getInvitePage(@PathVariable int cId, HttpSession session) {
		ModelAndView mav = new ModelAndView("community/manage/communityMemberInvite");
		try {
			MemberDTO udto = (MemberDTO) session.getAttribute(MemberConst.USER_KEY);
			String userRole = null;
			if (udto != null) {
				userRole = service.getCommunityRole(cId, udto.getId());
			}

			if (!"master".equals(userRole) && !"submaster".equals(userRole)) {
				mav.setViewName("community/communityMsg");
				mav.addObject("msg", "권한이 없습니다.");
				mav.addObject("goUrl", "/community/" + cId);
				return mav;
			}

			mav.addObject("sidebarBoardLists", service.boardListByCommunityId(Map.of("cId", cId)));
			mav.addObject("sidebarMemberLists", service.sidebarMemberList(cId));
			mav.addObject("communityInfo", service.communityInfoById(cId));
			mav.addObject("cId", cId);

		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("community/communityMsg");
			mav.addObject("msg", "페이지 로드 중 오류 발생");
			mav.addObject("goUrl", "/community/" + cId);
		}
		return mav;
	}

	// 팝업 초대 대상 목록
	@GetMapping("/community/{cId}/inviteList")
	public ModelAndView getInviteList(@PathVariable int cId, HttpSession session) {
		ModelAndView mav = new ModelAndView("community/manage/inviteMemberList");
		try {
			MemberDTO udto = (MemberDTO) session.getAttribute(MemberConst.USER_KEY);
			if (udto == null) {
				mav.setViewName("community/communityMsg");
				mav.addObject("msg", "로그인이 필요합니다.");
				mav.addObject("goUrl", "/memberLogin");
				return mav;
			}

			String userRole = service.getCommunityRole(cId, udto.getId());
			if (!"master".equals(userRole) && !"submaster".equals(userRole)) {
				mav.setViewName("community/communityMsg");
				mav.addObject("msg", "초대 권한이 없습니다.");
				mav.addObject("goUrl", "/community/" + cId);
				return mav;
			}

			mav.addObject("inviteList", service.selectMemberInviteList(cId));
			mav.addObject("cId", cId);

		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("community/communityMsg");
			mav.addObject("msg", "멤버 목록 로드 중 오류");
			mav.addObject("goUrl", "/community/" + cId);
		}
		return mav;
	}

	// Ajax 멤버 초대 처리
	@PostMapping("/community/{cId}/invite")
	@ResponseBody
	public Map<String, Object> postInviteMembers(@PathVariable int cId, @RequestBody Map<String, Object> payload,
			HttpSession session) {

		Map<String, Object> result = new HashMap<>();

		try {
			MemberDTO udto = (MemberDTO) session.getAttribute(MemberConst.USER_KEY);
			if (udto == null) {
				result.put("status", "fail");
				result.put("msg", "로그인이 필요합니다.");
				return result;
			}

			String role = service.getCommunityRole(cId, udto.getId());
			if (!"master".equals(role) && !"submaster".equals(role)) {
				result.put("status", "fail");
				result.put("msg", "초대 권한이 없습니다.");
				return result;
			}

			List<?> rawList = (List<?>) payload.get("memberIds");
			List<Integer> memberIds = rawList.stream().map(o -> Integer.parseInt(o.toString()))
					.collect(Collectors.toList());

			List<String> alreadyMembers = new ArrayList<>();

			for (Integer mId : memberIds) {
				try {
					service.insertInviteMember(cId, mId, "user");
				} catch (IllegalStateException ise) {
					alreadyMembers.add(mId.toString());
				}
			}

			if (alreadyMembers.isEmpty()) {
				result.put("status", "success");
				result.put("msg", "모든 멤버가 성공적으로 초대되었습니다.");
			} else if (alreadyMembers.size() < memberIds.size()) {
				result.put("status", "partial");
				result.put("msg", "일부 멤버는 이미 가입되어 초대에서 제외되었습니다: " + String.join(", ", alreadyMembers));
			} else {
				result.put("status", "fail");
				result.put("msg", "선택한 멤버는 모두 이미 가입된 상태입니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "fail");
			result.put("msg", "초대 중 오류 발생");
		}

		return result;
	}

// 커뮤니티 삭제 페이지 GET
	@GetMapping("/community/{cId}/close")
	public ModelAndView closePage(@PathVariable("cId") String cId) {
		ModelAndView mav = new ModelAndView();

		try {
// 사이드바용 게시판 목록
			Map<String, Object> map = new HashMap<>();
			map.put("cId", cId);
			List<BoardDTO> sidebarBoardList = service.boardListByCommunityId(map);
			CommunityDTO communityInfo = service.communityInfoById(Integer.parseInt(cId));

			mav.addObject("sidebarBoardList", sidebarBoardList);
			mav.addObject("communityInfo", communityInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		mav.addObject("cId", cId);
		mav.setViewName("community/manage/communityClose");
		return mav;
	}

// 커뮤니티 삭제 POST
	// 커뮤니티 삭제 POST
	@PostMapping("/community/{cId}/close")
	public ModelAndView postCommunityClose(@PathVariable("cId") int cId) {
		ModelAndView mav = new ModelAndView();
		String msg;
		String goUrl;

		try {
			Map<String, Object> map = new HashMap<>();
			map.put("cId", cId);
			List<BoardDTO> boards = service.boardListByCommunityId(map);

			if (boards != null && !boards.isEmpty()) {
				msg = "게시판이 남아 있어 커뮤니티를 삭제할 수 없습니다. 게시판을 먼저 삭제해 주세요.";
				goUrl = "/community/" + cId + "/close"; // 다시 close 페이지로
			} else {
				int result = service.deleteCommunity(cId);
				if (result > 0) {
					msg = "커뮤니티가 성공적으로 삭제되었습니다.";
					goUrl = "/communityMainNewest"; // 삭제 성공 → 메인
				} else {
					msg = "커뮤니티 삭제에 실패했습니다.";
					goUrl = "/community/" + cId + "/close"; // 실패 → 다시 close
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "삭제 중 오류가 발생했습니다.";
			goUrl = "/community/" + cId + "/close"; // 오류 → 다시 close
		}

		mav.addObject("msg", msg);
		mav.addObject("goUrl", goUrl);
		mav.setViewName("community/communityMsg");
		return mav;
	}

	// 게시판(board) create 생성 url 이동
	@GetMapping("/community/{cId}/board/create")
	public ModelAndView boardCreateForm(@PathVariable("cId") int cId) {
		List<CommunityDTO> communities = null; // 커뮤니티 목록들
		List<BoardDTO> sidebarBoardList = null; // 게시판 목록들
		CommunityDTO communityInfo = null;
		try {
			communities = service.communityList(); // 커뮤니티 목록들
			communityInfo = service.communityInfoById(cId);
			Map<String, Object> map = new HashMap<>();
			map.put("cId", cId); // map= {"cid" : 3} 의미임 !!! cid가 3일때
			sidebarBoardList = service.boardListByCommunityId(map); // map
		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("communities", communities); // 커뮤니티 목록들
		mav.addObject("sidebarBoardList", sidebarBoardList); // 선택된 커뮤니티의 게시판 목록들
		mav.addObject("cId", cId);
		mav.addObject("communityName", communityInfo != null ? communityInfo.getName() : "커뮤니티");
		mav.setViewName("community/board/boardCreate");
		return mav;
	}

	// 게시판(board) create 생성 기능
	@PostMapping("/community/board/create")
	public ModelAndView boardCreate(BoardDTO bdto) {
		int result = 0;
		String msg = null;

		try {
			result = service.insertBoard(bdto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		msg = result > 0 ? "게시판 생성 성공!" : "게시판 생성 실패!";

		String goUrl = result > 0 ? "/community/" + bdto.getCommunity_id() + "/board/" + bdto.getId()
				: "/community/" + bdto.getCommunity_id() + "/board/create";

		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		mav.addObject("goUrl", goUrl);
		mav.setViewName("community/board/boardMsg");
		return mav;
	}
	//////////////////////////////////////////////////////////

	// 게시판 수정 url이동
	@GetMapping("/community/{cId}/board/{boardId}/update")
	public ModelAndView getBoardUpdate(@PathVariable int cId, @PathVariable int boardId) {
		ModelAndView mav = new ModelAndView();

		BoardDTO board = null;
		List<BoardDTO> sidebarBoardList = null;
		String errorMessage = null;

		try {
			board = service.selectBoardById(boardId);
			sidebarBoardList = service.boardListByCommunityId(Map.of("cId", cId));

			if (board == null) {
				errorMessage = "존재하지 않는 게시판입니다.";
				board = new BoardDTO(); // Null 방지용
			}

			if (sidebarBoardList == null) {
				sidebarBoardList = List.of(); // Null 방지용
			}

		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "에러가 발생했습니다.";
			board = new BoardDTO();
			sidebarBoardList = List.of();
		}

		mav.addObject("board", board);
		mav.addObject("sidebarBoardList", sidebarBoardList);
		mav.addObject("cId", cId);
		mav.addObject("boardId", boardId);
		if (errorMessage != null) {
			mav.addObject("errorMessage", errorMessage);
		}

		mav.setViewName("community/board/boardUpdate");
		return mav;
	}

	// 게시판 수정 기능
	@PostMapping("/community/{cId}/board/{boardId}/update")
	public ModelAndView postBoardUpdate(@PathVariable int cId, @PathVariable int boardId, BoardDTO bdto) {
		ModelAndView mav = new ModelAndView();

		BoardDTO board = null;
		List<BoardDTO> sidebarBoardList = null;
		String message = null;
		String messageType = null; // success / error

		try {
			bdto.setId(boardId);
			int result = service.updateBoardInfo(bdto);

			if (result > 0) {
				message = "게시판 정보가 수정되었습니다!";
				messageType = "success";
			} else {
				message = "게시판 정보 수정에 실패했습니다.";
				messageType = "error";
			}

			board = service.selectBoardById(boardId);
			sidebarBoardList = service.boardListByCommunityId(Map.of("cId", cId));

			if (board == null)
				board = new BoardDTO();
			if (sidebarBoardList == null)
				sidebarBoardList = List.of();

		} catch (Exception e) {
			e.printStackTrace();
			message = "에러가 발생했습니다.";
			messageType = "error";
			board = new BoardDTO();
			sidebarBoardList = List.of();
		}

		mav.addObject("board", board);
		mav.addObject("sidebarBoardList", sidebarBoardList);
		mav.addObject("cId", cId);
		mav.addObject("boardId", boardId);
		mav.addObject("message", message);
		mav.addObject("messageType", messageType);
		mav.setViewName("community/board/boardUpdate");

		return mav;
	}

	///////////////////////////////////////////////////////////
//가입 승인 !!

	@GetMapping("/community/{cId}/board/{boardId}")
	public ModelAndView postList(@PathVariable int cId, @PathVariable int boardId, HttpSession session) {
	    ModelAndView mav = new ModelAndView();
	    try {
	        // 1. 게시판 목록 (사이드바용)
	        Map<String, Object> map = new HashMap<>();
	        map.put("cId", cId);
	        List<BoardDTO> boardLists = service.boardListByCommunityId(map);

	        // 2. 게시글 목록 (PostDTO 리스트)
	        List<PostDTO> postLists = service.postListByBoardId(boardId);

	        // 3. 멤버 정보 목록 (id, name 포함된 Map 리스트)
	        List<Map<String, Object>> sidebarMemberLists = service.sidebarMemberList(cId);

	        // 4. 게시글을 Map 형태로 변환해서 작성자 이름 추가
	        List<Map<String, Object>> postViewList = new ArrayList<>();
	        for (PostDTO post : postLists) {
	            Map<String, Object> postMap = new HashMap<>();
	            postMap.put("id", post.getId());
	            postMap.put("title", post.getTitle());
	            postMap.put("member_id", post.getMember_id());
	            postMap.put("write_date", post.getWrite_date());
	            postMap.put("view_num", post.getView_num());
	            postMap.put("upvote", post.getUpvote());

	            // 작성자 이름 찾아서 추가
	            String writer = "탈퇴한 사용자";
	            for (Map<String, Object> m : sidebarMemberLists) {
	                if (((Number) m.get("ID")).intValue() == post.getMember_id()) {
	                    writer = (String) m.get("NAME");
	                    break;
	                }
	            }
	            postMap.put("postName", writer);
	            postViewList.add(postMap);
	        }

	        // 5. 게시판명, 커뮤니티명
	        Map<String, String> names = service.selectBoardAndCommunity(boardId);

	        // 6. 뷰에 전달
	        mav.addObject("boardLists", boardLists);
	        mav.addObject("postLists", postViewList); // ← postDTO 말고 Map 리스트
	        mav.addObject("sidebarMemberLists", sidebarMemberLists);
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
	public ModelAndView postWrite(@PathVariable("cId") int cId, @PathVariable("boardId") int boardId) {
		ModelAndView mav = new ModelAndView();

		try {
			// 해당 커뮤니티의 게시판 목록 (사이드바용 wj정보임 )
			Map<String, Object> map = new HashMap<>();
			map.put("cId", cId); // map에 cid : 3 정보 넣어줌 key,value
			List<BoardDTO> boardList = service.boardListByCommunityId(map); // 3(value)에 대한 게시판 목록들
			mav.addObject("boardList", boardList); // 사이드바에 사용될 게시판 목록 전달
		} catch (Exception e) {
			e.printStackTrace();
		}

		mav.addObject("cId", cId); // 커뮤니티 ID
		mav.addObject("boardId", boardId); // 게시판 ID
		mav.setViewName("community/board/postWrite"); // 게시글 작성 페이지로 이동
		return mav;
	}

	// 게시글 작성 + 이미지 업로드
	@PostMapping("/community/{cId}/board/{boardId}/write")
	public ModelAndView postWriteSubmit(@PathVariable int cId, @PathVariable int boardId, PostDTO pdto,
			@RequestParam(value = "images", required = false) MultipartFile[] images, HttpServletRequest request) {

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
	public ModelAndView postContent(@PathVariable int cId, @PathVariable int boardId, @PathVariable int postId) {
		ModelAndView mav = new ModelAndView();

		try {

			// 게시판 목록 정보
			Map<String, Object> map = new HashMap<>();
			map.put("cId", cId);
			List<BoardDTO> boardList = service.boardListByCommunityId(map);
			mav.addObject("boardList", boardList);

			// 게시글 정보
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

	
	//좋아요
	@PostMapping("/community/{cId}/board/{boardId}/post/{postId}/like")
	@ResponseBody
	public Map<String, Object> upvote(@PathVariable int postId) {
		Map<String, Object> result = new HashMap<>();
		try {
			service.upvotePlus(postId); // 좋아요 +1
			int upvote = service.selectPostById(postId).getUpvote(); // 최신 좋아요 수 가져오기
			result.put("status", "success");
			result.put("upvote", upvote); // upvote 값을 JSON에 담아줌
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "error");
			result.put("msg", "좋아요 처리 실패");
		}
		return result;
	}

	// 게시글 수정 url 이동
	@GetMapping("/community/{cId}/board/{boardId}/post/{postId}/edit")
	public ModelAndView postEditUrl(@PathVariable int cId, @PathVariable int boardId, @PathVariable int postId) {
		ModelAndView mav = new ModelAndView();

		try {
			Map<String, Object> map = new HashMap<>();
			map.put("cId", cId);
			List<BoardDTO> boardList = service.boardListByCommunityId(map);
			mav.addObject("boardList", boardList);

			PostDTO post = service.selectPostById(postId);
			mav.addObject("post", post);

			List<ImageDTO> imageList = service.selectImagesByPostId(postId);
			mav.addObject("imageList", imageList); // d이미지 불러오기 임 이거

		} catch (Exception e) {
			e.printStackTrace();
		}

		mav.addObject("cId", cId);
		mav.addObject("boardId", boardId);
		mav.setViewName("community/board/postEdit");
		return mav;
	}

	// 게시글 수정 기능
	@PostMapping("/community/{cId}/board/{boardId}/post/{postId}/edit")
	public ModelAndView editPostSubmit(@PathVariable int cId, @PathVariable int boardId, @PathVariable int postId,
			PostDTO pdto, @RequestParam(value = "deleteImageIds", required = false) int[] deleteImageIds,
			@RequestParam(value = "images", required = false) MultipartFile[] images, HttpServletRequest request) {
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
							if (file.exists())
								file.delete();
						}
					}
				}

				// 이미지 추가
				if (images != null) {
					new File(path).mkdirs();
					for (MultipartFile file : images) {
						if (!file.isEmpty()) {
							String ext = file.getOriginalFilename()
									.substring(file.getOriginalFilename().lastIndexOf("."));
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

	// 게시글 삭제
	@PostMapping("/community/{cId}/board/{boardId}/post/{postId}/delete")
	public ModelAndView deletePost(@PathVariable int cId, @PathVariable int boardId, @PathVariable int postId) {
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
	@PostMapping("/community/{cId}/board/{boardId}/post/{postId}/reply")
	@ResponseBody
	public Map<String, Object> insertReply(
	        @PathVariable int cId,
	        @PathVariable int boardId,
	        @PathVariable int postId,
	        @RequestBody ReplyDTO rdto,
	        HttpSession session) {
	    
	    Map<String, Object> result = new HashMap<>();
	    try {
	        MemberDTO user = (MemberDTO) session.getAttribute(com.cbo.constant.MemberConst.USER_KEY);
	        if (user == null) {
	            result.put("status", "fail");
	            result.put("msg", "로그인 필요");
	            return result;
	        }
	        rdto.setBoard_post_id(postId);
	        rdto.setMember_id(user.getId());
	        service.insertReply(rdto);
	        result.put("status", "success");
	        result.put("msg", "댓글이 등록되었습니다.");
	    } catch (Exception e) {
	        e.printStackTrace();
	        result.put("status", "error");
	        result.put("msg", "댓글 등록 중 오류 발생");
	    }
	    return result;
	}

	// 답글 등록
	@PostMapping("/community/{cId}/board/{boardId}/post/{postId}/reply/{parentId}/child")
	@ResponseBody
	public Map<String, Object> insertChildReply(
	        @PathVariable int cId,
	        @PathVariable int boardId,
	        @PathVariable int postId,
	        @PathVariable int parentId,
	        @RequestBody ReplyDTO rdto,
	        HttpSession session) {
	    
	    Map<String, Object> result = new HashMap<>();
	    try {
	        MemberDTO user = (MemberDTO) session.getAttribute(com.cbo.constant.MemberConst.USER_KEY);
	        if (user == null) {
	            result.put("status", "fail");
	            result.put("msg", "로그인 필요");
	            return result;
	        }
	        rdto.setBoard_post_id(postId);
	        rdto.setMember_id(user.getId());
	        service.insertChildReply(rdto, parentId);
	        result.put("status", "success");
	        result.put("msg", "답글이 등록되었습니다.");
	    } catch (Exception e) {
	        e.printStackTrace();
	        result.put("status", "error");
	        result.put("msg", "답글 등록 중 오류 발생");
	    }
	    return result;
	}

	// 댓글/답글 수정
	@PostMapping("/community/{cId}/board/{boardId}/post/{postId}/reply/{replyId}/edit")
	@ResponseBody
	public Map<String, Object> updateReply(
	        @PathVariable int cId,
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
	            result.put("msg", "로그인 필요");
	            return result;
	        }
	        rdto.setId(replyId);
	        rdto.setMember_id(user.getId());
	        int updated = service.updateReply(rdto);
	        result.put("status", updated > 0 ? "success" : "fail");
	        result.put("msg", updated > 0 ? "수정되었습니다." : "수정 권한 없음");
	    } catch (Exception e) {
	        e.printStackTrace();
	        result.put("status", "error");
	        result.put("msg", "수정 중 오류 발생");
	    }
	    return result;
	}

	// 댓글/답글 삭제
	@PostMapping("/community/{cId}/board/{boardId}/post/{postId}/reply/{replyId}/delete")
	@ResponseBody
	public Map<String, Object> deleteReply(
	        @PathVariable int cId,
	        @PathVariable int boardId,
	        @PathVariable int postId,
	        @PathVariable int replyId,
	        HttpSession session) {
	    
	    Map<String, Object> result = new HashMap<>();
	    try {
	        MemberDTO user = (MemberDTO) session.getAttribute(com.cbo.constant.MemberConst.USER_KEY);
	        if (user == null) {
	            result.put("status", "fail");
	            result.put("msg", "로그인 필요");
	            return result;
	        }
	        int deleted = service.deleteReply(replyId);
	        result.put("status", deleted > 0 ? "success" : "fail");
	        result.put("msg", deleted > 0 ? "삭제되었습니다." : "삭제 권한 없음");
	    } catch (Exception e) {
	        e.printStackTrace();
	        result.put("status", "error");
	        result.put("msg", "삭제 중 오류 발생");
	    }
	    return result;
	}

	// 댓글/답글 목록 조회
	@GetMapping("/community/{cId}/board/{boardId}/post/{postId}/replyList")
	@ResponseBody
	public List<Map<String, Object>> getReplyList(
	        @PathVariable int cId,
	        @PathVariable int boardId,
	        @PathVariable int postId) {
	    try {
	        return service.selectReplyByPostId(postId);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ArrayList<>();
	    }
	}
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////

}
