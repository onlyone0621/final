package com.cbo.approval.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalLineDTO {
	private int doc_id;
	private int member_id;
	private String member_name;
	private String dept_name;
	private String grade_name;
	private String status;
	private String process_date;
}
