package com.cbo.groups.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GroupsDTO {
	private int id;
	private int member_id;
	private String name;
}
