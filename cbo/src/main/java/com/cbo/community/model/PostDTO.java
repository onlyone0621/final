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

public class PostDTO {
	private int id;
	private int board_id;
	private int member_id;
	private String title;
	private String content;
	private Date write_date;
	private int view_num;
	private int upvote;
}
