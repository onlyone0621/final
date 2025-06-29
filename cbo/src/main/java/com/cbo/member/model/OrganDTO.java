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
	private int member_id;
	private String member_name;
	private String dept_name;
	private String grade_name;
	private String profile_image;
}
