package com.cbo.mapper;

import java.util.List;

import com.cbo.addr.model.AddrDTO;

public interface AddrMapper {

	public List<AddrDTO> addrList()throws Exception;
}
