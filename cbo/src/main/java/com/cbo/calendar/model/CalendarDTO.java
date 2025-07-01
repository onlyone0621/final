package com.cbo.calendar.model;
import java.sql.*;
import java.time.LocalDateTime;

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
	private LocalDateTime start_time;
	private LocalDateTime end_time;
	private Date create_date;
	private int allday;
}
