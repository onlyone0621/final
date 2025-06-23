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
	private int docId;
	private int memberId;
	private String approver;
	private String deptName;
	private String gradeName;
	private String status;
	private String processDate;
}
