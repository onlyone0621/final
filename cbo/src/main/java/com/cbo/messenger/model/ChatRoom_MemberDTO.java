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
public class ChatRoom_MemberDTO {
	private int chatRoom_id;
	private int member_id;
	private Date join_date;
}
