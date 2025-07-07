package com.cbo.addr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AddrListDTO {
	private int id;
	private int member_id;
	private String name;
	private String nickname;
	private String company;
	private String companytel;	
	private String dept;
	private String grade;
	private String tel;
	private String email;
	private String description;
	private String groupNames;
}
