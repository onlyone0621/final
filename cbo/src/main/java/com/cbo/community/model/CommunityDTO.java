package com.cbo.community.model;

import java.sql.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class CommunityDTO {

	private int id;
	private String name;
	private String description;
	private Date create_date;
	private int member_id;
	
	
}
