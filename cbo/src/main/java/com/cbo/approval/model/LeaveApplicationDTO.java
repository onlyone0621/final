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
public class LeaveApplicationDTO {
	private int id;
	private int doc_id;
	private String dept;
	private String grade;
	private String name;
	private String type;
	private Date startDate;
	private Date endDate;
	private int remaining;
}
