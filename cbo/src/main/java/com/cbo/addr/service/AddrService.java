package com.cbo.addr.service;

import java.util.List;

import com.cbo.addr.model.AddrDTO;
import com.cbo.addr.model.AddrListDTO;

public interface AddrService {

	public List<AddrListDTO> addrList(int id)throws Exception;
	public List<AddrDTO> personalList(int id)throws Exception;
	public List<AddrDTO> deptList(String saveid)throws Exception;
	public String userDept(String saveid)throws Exception;
	public int findUserId(String saveid)throws Exception;
	public int addPersonalAddr(String name,int id)throws Exception;
	public int addDeptAddr(String name,int id)throws Exception; 
	public List<AddrListDTO> groupAddrList(int groupid)throws Exception;
	public List<AddrListDTO> deptAllList(String dept)throws Exception;
	public int insertPersonalAlladdr(AddrDTO dto,int member_id)throws Exception;
	public int quickAddAddr(AddrDTO dto)throws Exception;
	public int deleteAddr(List<Integer> ids)throws Exception;
	public int quickGroupAddAddr(int addr_id, int groups_id)throws Exception;
	public int findGroupId(String groupName)throws Exception;
	public int countUser(int groupid)throws Exception;
	public int countAllUser()throws Exception;
	public int addAddrGroup(int addrId,int groupid)throws Exception;
	public int deleteGroup(int groupid)throws Exception;
	public int renameGroup(int groupId,String newGroupName)throws Exception;
}
