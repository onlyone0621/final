package com.cbo.message.model;
import java.sql.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MessageDTO {
	private int id;
	private String title;
	private String content;
	private int receiver_id;
	private String receiver;
	private int sender_id;
	private String sender;
	private String write_date;
	private String is_read;
	private String file_name;
	private int ref;
	private int lev;
	private int max_rows;
}
