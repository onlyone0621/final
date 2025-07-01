package com.cbo.addr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbo.addr.model.AddrDTO;
import com.cbo.mapper.AddrMapper;

@Service
public class AddrServiceImple implements AddrService {

	@Autowired
	private AddrMapper mapper;

	@Override
	public List<AddrDTO> addrList() throws Exception {
		
		List<AddrDTO> list = mapper.addrList();
		
		return list;
	}
	
	@Override
	public List<AddrDTO> deptList(String saveid) throws Exception {
		
		List<AddrDTO> list = mapper.deptList(saveid);
		return list;
	}
	
	@Override
	public List<AddrDTO> personalList(String id) throws Exception {
		List<AddrDTO> list = mapper.personalList(id);
		return list;
	}
	
	@Override
	public String userDept(String saveid) throws Exception {
		String dept = mapper.userDept(saveid);
		return dept;
	}
	
	@Override
	public int addPersonalAddr(String name,String id) throws Exception {
		int result = mapper.addPersonalAddr(name,id);
		return result;
	}
	
	@Override
	public List<AddrDTO> groupAddrList(String name) throws Exception {
		List<AddrDTO> lists = mapper.groupAddrList(name);
		
		return lists;
	}
	
	@Override
	public String findUserId(String saveid) throws Exception {
		String id = mapper.findUserId(saveid);
		return id;
	}
}
