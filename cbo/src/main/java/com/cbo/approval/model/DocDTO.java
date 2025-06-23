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
	private int memberId;
	private String writer;
	private Date writeDate;
	private int formatId;
	private String formatName;
	private String content;
	private int retention;
	private String fileName;
}
