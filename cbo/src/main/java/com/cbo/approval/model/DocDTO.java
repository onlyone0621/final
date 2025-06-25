package com.cbo.approval.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocDTO {
	private int id;
	private String title;
	private int member_id;
	private String writer;
	private Date write_date;
	private int format_id;
	private String format_name;
	private String content;
	private int retention;
	private String file_name;
}
