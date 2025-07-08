package com.cbo.member.model;
import java.sql.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MemberDTO {
	private int id;
	private String user_id;
	private String pwd;
	private String name;
	private String email;
	private String address;
	private String tel;
	private Date join_date;
	private int dept_id;
	private String dept_name;
	private int grade_id;
	private String grade_name;
	private String profile_image;
}
