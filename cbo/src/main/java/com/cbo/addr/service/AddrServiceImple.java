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
}
