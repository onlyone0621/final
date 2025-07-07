package com.cbo.community.model;
import java.sql.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class PostListDTO {
	
    private int post_id;
    private int board_id;  // ★ board_id 포함 (Thymeleaf URL에서 사용)
    private String title;
    private String content;
    private Date write_date;
    private String board_name;
    private String community_name;
    private String member_name;
    private int upvote;
    private int reply_count;
    
}
