package com.cbo.addr.service;

import java.util.List;

import com.cbo.addr.model.AddrDTO;

public interface AddrService {

	public List<AddrDTO> addrList()throws Exception;
}
