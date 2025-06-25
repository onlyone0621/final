package com.cbo.calendar.model;
import java.sql.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CalendarDTO {
	private Integer id;
	private int member_id;
	private int dept_id;
	private String title;
	private String content;
	private Date start_time;
	private Date end_time;
	private Date create_date;
}
