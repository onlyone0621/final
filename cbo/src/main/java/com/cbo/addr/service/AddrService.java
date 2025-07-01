package com.cbo.addr.service;

import java.util.List;

import com.cbo.addr.model.AddrDTO;

public interface AddrService {

	public List<AddrDTO> addrList()throws Exception;
	public List<AddrDTO> personalList(String id)throws Exception;
	public List<AddrDTO> deptList(String saveid)throws Exception;
	public String userDept(String saveid)throws Exception;
	public String findUserId(String saveid)throws Exception;
	public int addPersonalAddr(String name,String id)throws Exception;
	public List<AddrDTO> groupAddrList(String name)throws Exception;
}
