package com.cbo.approval.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocViewDTO {
	private int id;
	private String write_date;
	private String completion;
	private int format_id;
	private String format_name;
	private String title;
	private int member_id;
	private String writer;
	private String status;
	private int max_rows;
}
