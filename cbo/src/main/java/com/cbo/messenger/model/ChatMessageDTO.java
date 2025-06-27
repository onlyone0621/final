package com.cbo.messenger.model;
import java.sql.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChatMessageDTO {
	private int id;
	private int chatroom_id;
	private int member_id;
	private Date write_date;
	private String content;
}
