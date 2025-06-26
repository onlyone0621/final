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
public class BoardDTO {

	private int id;
	private int community_id;
	private String name;
	private String description;
	private Date create_date;

}
