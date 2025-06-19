package com.cbo.approval.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalSupportDTO {
	private int id;
	private int docId;
	private String content;
	private String institution;
	private String diagnosis;
	private int requested;
	private int oop;
}
