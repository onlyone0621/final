package com.cbo.member.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganDTO {
	private int id;
	private String name;
	private String dept;
	private String grade;
	private String profileImage;
}
