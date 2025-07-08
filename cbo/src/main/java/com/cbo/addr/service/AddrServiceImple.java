package com.cbo.addr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbo.addr.model.AddrDTO;
import com.cbo.addr.model.AddrListDTO;
import com.cbo.mapper.AddrMapper;

@Service
public class AddrServiceImple implements AddrService {

	@Autowired
	private AddrMapper mapper;

	@Override
	public List<AddrListDTO> addrList(int id) throws Exception {
		
		List<AddrListDTO> list = mapper.addrList(id);
		
		return list;
	}
	
	@Override
	public List<AddrDTO> deptList(String saveid) throws Exception {
		
		List<AddrDTO> list = mapper.deptList(saveid);
		return list;
	}
	
	@Override
	public List<AddrDTO> personalList(int id) throws Exception {
		List<AddrDTO> list = mapper.personalList(id);
		return list;
	}
	
	@Override
	public String userDept(String saveid) throws Exception {
		String dept = mapper.userDept(saveid);
		return dept;
	}
	
	@Override
	public int addPersonalAddr(String name,int id) throws Exception {
		int result = mapper.addPersonalAddr(name,id);
		return result;
	}
	
	@Override
	public List<AddrListDTO> groupAddrList(int groupid) throws Exception {
		List<AddrListDTO> lists = mapper.groupAddrList(groupid);
		
		return lists;
	}
	
	@Override
	public int findUserId(String saveid) throws Exception {
		int id = mapper.findUserId(saveid);
		return id;
	} 
	@Override
	public int insertPersonalAlladdr(AddrDTO dto,int member_id) throws Exception {
		
		int result = mapper.insertPersonalAlladdr(dto,member_id);
		
		return result;
	}
	
	@Override
	public int quickAddAddr(AddrDTO dto) throws Exception {
		
		int result = mapper.quickAddAddr(dto);
		
		return result;
	}
	@Override
	public int deleteAddr(List<Integer> ids) throws Exception {
		int result = mapper.deleteAddr(ids);
		return result;
	}
	
	@Override
	public int quickGroupAddAddr(int addr_id, int groups_id) throws Exception {
		int result = mapper.quickGroupAddAddr(addr_id, groups_id);
		return result;
	}
	
	@Override
	public int findGroupId(String groupName) throws Exception {
		int groupId = mapper.findGroupId(groupName);
		return groupId;
	}
	
	@Override
	public int countUser(int groupid) throws Exception {
		int result = mapper.countUser(groupid);
		return result;
	}
	
	@Override
	public int countAllUser() throws Exception {
		int result = mapper.countAllUser();
		return result;
	}
	
	@Override
	public int addAddrGroup(int addrId, int groupid) throws Exception {
		int result = mapper.addAddrGroup(addrId, groupid);
		return result;
	}
	@Override
	public int deleteGroup(int groupid) throws Exception {
		int result = mapper.deleteGroup(groupid);
		return result;
	}
	
	@Override
	public int renameGroup(int groupId, String newGroupName) throws Exception {
		int result = mapper.renameGroup(groupId, newGroupName);
		return result;
	}
	
	@Override
	public int addDeptAddr(String name, int id) throws Exception {
		int result = mapper.addDeptAddr(name, id);
		return result;
	}
	
	@Override
	public List<AddrListDTO> deptAllList(String dept) throws Exception {
		List<AddrListDTO> deptAddrLists = mapper.deptAllList(dept);
		return deptAddrLists;
	}
	
	@Override
	public AddrDTO updateAddr(int id) throws Exception {
		AddrDTO dto = mapper.updateAddr(id);
		return dto;
	}
	@Override
	public int replaceAddr(AddrDTO dto) throws Exception {
		int result = mapper.replaceAddr(dto);
		return result;
	}
}
