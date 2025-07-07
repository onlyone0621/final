package com.cbo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cbo.addr.model.AddrDTO;
import com.cbo.addr.model.AddrListDTO;
import com.cbo.addr.service.AddrService;

@Controller
public class AddrController {

	@Autowired
	private AddrService service;

	@GetMapping("/addrModify") //변경
	public String updateAddr() {

		return "addr/addrModify";
	}
	
	@GetMapping("/addrMain") //메인(전체)
	public ModelAndView addrList(@CookieValue(value = "saveid", required = false) String saveid) {
	    List<AddrListDTO> addrLists = null;
	    List<AddrDTO> personalLists = null;
	    List<AddrDTO> deptLists = null;
	    String dept = null;
	    int id = 0;
	    Map<String, Integer> groupUserCountMap = new HashMap<>();
	    int countAllUser = 0;
	    try {
	        id = service.findUserId(saveid); // 접속해있는 유저 고유 아이디 찾기
	        addrLists = service.addrList(id); // 전체 주소록 눌렀을때 연락처 보여주기
	        personalLists = service.personalList(id); // 개인 주소록 그룹 보여주기
	        deptLists = service.deptList(saveid); // 부서 주소록 그룹들 보여주기
	        dept = service.userDept(saveid); // 부서 주소록 부서 이름 나타낼때 쓰는거
	        countAllUser = service.countAllUser();
	        // 그룹별 연락처 수 계산
	        if (personalLists != null) {
	            for (AddrDTO group : personalLists) {
	                int gid = service.findGroupId(group.getName());
	                int cnt = service.countUser(gid);
	                groupUserCountMap.put(group.getName(), cnt);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    ModelAndView mav = new ModelAndView();
	    mav.addObject("addrLists", addrLists);
	    mav.addObject("personalLists", personalLists);
	    mav.addObject("deptLists", deptLists);
	    mav.addObject("dept", dept);
	    mav.addObject("groupUserCountMap", groupUserCountMap); // 추가!
	    mav.addObject("countAllUser", countAllUser); // 추가!
	    mav.setViewName("addr/addrMain");

	    return mav;
	}

	@GetMapping("/deptAddr") //메인(전체)
	public ModelAndView deptAddrList(@CookieValue(value = "saveid", required = false) String saveid) {
	    List<AddrListDTO> addrLists = null;
	    List<AddrDTO> personalLists = null;
	    List<AddrDTO> deptLists = null;
	    String dept = null;
	    int id =0;
	    List<AddrListDTO> deptAddrLists = null;
	    Map<String, Integer> groupUserCountMap = new HashMap<>();
	    try {
	        id = service.findUserId(saveid); // 접속해있는 유저 고유 아이디 찾기
	        addrLists = service.addrList(id); // 전체 주소록 눌렀을때 연락처 보여주기
	        personalLists = service.personalList(id); // 개인 주소록 그룹 보여주기
	        deptLists = service.deptList(saveid); // 부서 주소록 그룹들 보여주기
	        dept = service.userDept(saveid); // 부서 주소록 부서 이름 나타낼때 쓰는거
	        deptAddrLists = service.deptAllList(dept);
	        
	        // 그룹별 연락처 수 계산
	        if (personalLists != null) {
	            for (AddrDTO group : personalLists) {
	                int gid = service.findGroupId(group.getName());
	                int cnt = service.countUser(gid);
	                groupUserCountMap.put(group.getName(), cnt);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    ModelAndView mav = new ModelAndView();
	    mav.addObject("addrLists", addrLists);
	    mav.addObject("personalLists", personalLists);
	    mav.addObject("deptLists", deptLists);
	    mav.addObject("dept", dept);
	    mav.addObject("deptAddrLists", deptAddrLists);
	    mav.addObject("groupUserCountMap", groupUserCountMap); // 추가!
	    mav.setViewName("addr/DeptAddr");

	    return mav;
	}

	@GetMapping("/goInsertPage") //전체에서 연락처 추가페이지
	public ModelAndView goInsertPage(@CookieValue(value = "saveid", required = false) String saveid) {
		List<AddrListDTO> addrLists = null;
		List<AddrDTO> personalLists = null;
		List<AddrDTO> deptLists = null;
		String dept = null;
		int id = 0;
		Map<String, Integer> groupUserCountMap = new HashMap<>();
		int countUser = 0;
		try {
			id = service.findUserId(saveid); // 접속해있는 유저 고유 아이디 찾기
			addrLists = service.addrList(id); // 전체 주소록 눌렀을때 연락처 보여주기
			personalLists = service.personalList(id); // 개인 주소록 그룹 보여주기
			deptLists = service.deptList(saveid); // 부서 주소록 그룹들 보여주기
			dept = service.userDept(saveid); // 부서 주소록 부서 이름 나타낼때 쓰는거
			
			  if (personalLists != null) {
		            for (AddrDTO group : personalLists) {
		                int gid = service.findGroupId(group.getName());
		                int cnt = service.countUser(gid);
		                groupUserCountMap.put(group.getName(), cnt);
		            }
		        }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("addrLists", addrLists);
		mav.addObject("personalLists", personalLists);
		mav.addObject("deptLists", deptLists);
		mav.addObject("dept", dept);
		mav.addObject("groupUserCountMap", groupUserCountMap);
		mav.setViewName("addr/insertPersonalAllAddr");

		return mav;
	}
	
	@GetMapping("/groupAddrList/{groupName}") //그룹내에 연락처 나타내기
	public ModelAndView groupAddrList(@PathVariable("groupName") String name,
			@CookieValue(value = "saveid", required = false) String saveid) {

		List<AddrListDTO> lists = null;
		List<AddrDTO> addrLists = null;
		List<AddrDTO> personalLists = null;
		List<AddrDTO> deptLists = null;
		String dept = null;
		int group_id = 0;
		int countUser = 0;
		Map<String, Integer> groupUserCountMap = new HashMap<>();
		
		try {
			group_id = service.findGroupId(name);
			int id = service.findUserId(saveid);
			lists = service.groupAddrList(group_id);
			personalLists = service.personalList(id);
			deptLists = service.deptList(saveid);
			dept = service.userDept(saveid);
			countUser = service.countUser(group_id);
			
			 if (personalLists != null) {
		            for (AddrDTO group : personalLists) {
		                int gid = service.findGroupId(group.getName());
		                int cnt = service.countUser(gid);
		                groupUserCountMap.put(group.getName(), cnt);
		            }
		        }
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ModelAndView mav = new ModelAndView();
		if (!(name.equals("")) && name != null) {
			mav.addObject("groupName", name);
		}
		mav.addObject("groupAddrLists", lists);
		mav.addObject("addrLists", addrLists);
		mav.addObject("personalLists", personalLists);
		mav.addObject("deptLists", deptLists);
		mav.addObject("dept", dept);
		mav.addObject("groupId", group_id);
		mav.addObject("countUser", countUser);
		mav.addObject("groupUserCountMap", groupUserCountMap); 

		mav.setViewName("/addr/groupAddrList");

		return mav;
	}

	
	@PostMapping("/insertAddr") //연락처 추가
	public ModelAndView insertPersonalAllAddr(
	        AddrDTO dto, 
	        @CookieValue(value = "saveid", required = false) String saveid) {

	    List<AddrListDTO> addrLists = null;
	    List<AddrDTO> personalLists = null;
	    List<AddrDTO> deptLists = null;
	    String dept = null;
	    int id = 0;

	    // 그룹별 인원수 맵
	    Map<String, Integer> groupUserCountMap = new HashMap<>();

	    try {
	        id = service.findUserId(saveid); // 접속해있는 유저 고유 아이디 찾기
	        int result = service.insertPersonalAlladdr(dto, id);

	        addrLists = service.addrList(id); // 전체 주소록
	        personalLists = service.personalList(id); // 개인 주소록 그룹
	        deptLists = service.deptList(saveid); // 부서 주소록 그룹
	        dept = service.userDept(saveid); // 부서 이름

	        // 그룹별 인원수 계산 (예시: personalLists의 groupName 기준)
	        if (personalLists != null) {
	            for (AddrDTO group : personalLists) {
	            	int gid = service.findGroupId(group.getName());
	                int cnt = service.countUser(gid);
	                groupUserCountMap.put(group.getName(), cnt);
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    ModelAndView mav = new ModelAndView();
	    mav.addObject("addrLists", addrLists);
	    mav.addObject("personalLists", personalLists);
	    mav.addObject("deptLists", deptLists);
	    mav.addObject("dept", dept);
	    mav.addObject("groupUserCountMap", groupUserCountMap); // 추가!
	    mav.setViewName("addr/addrMain");
	    return mav;
	}
	  
	 // public int insertPersonalgroupAddr() {// 그룹 연락처
	  
	  
	//  }
	  
	  @PostMapping("/addr/delete") //체크 후 삭제
	  @ResponseBody
	  public int deleteAddrs(@RequestBody Map<String, List<Integer>> param) {
	      List<Integer> ids = param.get("ids");
	      // ids 리스트에 포함된 id만 삭제하는 서비스 호출
	      int result = 0;
	      try {
			result = service.deleteAddr(ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      return result;
	  }
	
	@PostMapping("/addr/addGroup") //그룹 등록
	@ResponseBody
	public int addPersonalAddr(@RequestBody Map<String, String> param,
			@CookieValue(value = "saveid", required = false) String saveid) {

		int result = 0;
		int id = 0;
		String name = param.get("groupName");
		try {
			id = service.findUserId(saveid);
			result = service.addPersonalAddr(name, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}
	
	@PostMapping("/addr/addDeptGroup") //그룹 등록
	@ResponseBody
	public int addDeptAddr(@RequestBody Map<String, String> param,
			@CookieValue(value = "saveid", required = false) String saveid) {

		int result = 0;
		int id = 0;
		String name = param.get("groupName");
		try {
			id = service.findUserId(saveid);
			result = service.addDeptAddr(name, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}
		@PostMapping("/quickGroupAddrAdd") // 그룹내에서 빠른등록
		@ResponseBody
		public int quickGroupAddrAdd(AddrDTO dto, @RequestParam("groupId")int groupsId,@CookieValue(value = "saveid", required = false) String saveid) {
			int result = 0;
			int member_id = 0;
			try {
				member_id = service.findUserId(saveid);
				dto.setMember_id(member_id);
				result = service.quickAddAddr(dto);
				//int groupId= service.findGroupId(groupsName);
				int addr_id = dto.getId();

				
				
				service.quickGroupAddAddr(addr_id,groupsId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
	
	@PostMapping("quickAddrAdd") //전체에서 빠른등록
	@ResponseBody
	public int quickAddrAdd(AddrDTO dto,@CookieValue(value = "saveid", required = false) String saveid) {
		int result = 0;
		int member_id = 0;
		try {
			member_id = service.findUserId(saveid);
			dto.setMember_id(member_id);
			result = service.quickAddAddr(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result; 
	}


	@PostMapping("/addr/assignGroup") // 그룹 지정
	@ResponseBody
	public int assignGroup(@RequestBody Map<String, Object> param) {
        String groupIdStr = String.valueOf(param.get("groupId"));
        Integer groupId = Integer.valueOf(groupIdStr);

        List<?> idListRaw = (List<?>) param.get("ids");
        List<Integer> ids = new ArrayList<>();
        for (Object idObj : idListRaw) {
            ids.add(Integer.valueOf(String.valueOf(idObj)));
        }
	    try {
	        // 각 연락처에 대해 addr_group에 insert (이미 있으면 중복 방지)
	        for (Integer addrId : ids) {
	            service.addAddrGroup(addrId, groupId);
	        }
	        return 1;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0;
	    }
	}
	
	@PostMapping("/addr/renameGroup")
	@ResponseBody
	public int renameGroup(@RequestBody Map<String, Object> param) {
	    int groupId = Integer.parseInt(param.get("groupId").toString());
	    String newGroupName = param.get("newGroupName").toString();
	    try {
			service.renameGroup(groupId, newGroupName);
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} // 실제 이름 변경 로직
	}
	
	@PostMapping("/addr/deleteGroup")
	@ResponseBody
	public int deleteGroup(@RequestBody Map<String, Integer> param) {
	    int groupId = param.get("groupId");
	    try {
			service.deleteGroup(groupId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return 1; // 성공 시 1 반환
	}
}
