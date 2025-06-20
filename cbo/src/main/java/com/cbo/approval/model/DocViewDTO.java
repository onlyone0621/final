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
	private String writeDate;
	private String completion;
	private String format;
	private String title;
	private int memberId;
	private String writer;
	private String status;
}
