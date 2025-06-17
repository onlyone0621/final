package com.cbo.drive.model;
import java.sql.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DriveDTO {
	private int id;
	private int member_id;
	private String uploader;
	private Date upload_date;
	private int path;
}
