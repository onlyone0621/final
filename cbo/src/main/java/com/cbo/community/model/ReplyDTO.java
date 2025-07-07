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
public class ReplyDTO {

	private int id;
	private int board_post_id;
	private int member_id;
	private String content;
	private String write_date;
	private int upvote;
	private int ref;
	private int lev;
	private int sunbun;
}
