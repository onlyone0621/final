package com.cbo.addr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AddrDTO {
	private int id;
	private String name;
	private String nickname;
	private String company;
	private String dept;
	private String grade;
	private String tel;
	private String email;
	private String description;
}
