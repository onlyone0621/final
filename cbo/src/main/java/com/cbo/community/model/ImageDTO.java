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

public class ImageDTO {

	private int id;
	private int post_id;
	private int member_id;
	private String saved_name;
	private String original_name;
	private Date upload_date;

	
}
