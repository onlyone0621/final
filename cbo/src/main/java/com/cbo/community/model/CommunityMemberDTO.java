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

public class CommunityMemberDTO {

	private int community_id;
	private int member_id;
	private String role;
	private Date join_date;
}
