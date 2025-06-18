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
public class ApprovalDocDTO {
	private int id;
	private String title;
	private int memberId;
	private int deptId;
	private Date writeDate;
	private int retention;
	private String fileName;
}
