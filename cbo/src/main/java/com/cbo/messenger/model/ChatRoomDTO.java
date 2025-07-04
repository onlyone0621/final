package com.cbo.messenger.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChatRoomDTO {
	private int id;
	private String name;
	private String description;
	private Date create_date;
	private String type;
}
