DROP TABLE board_reply CASCADE CONSTRAINTS;
DROP TABLE board_post CASCADE CONSTRAINTS;
DROP TABLE community_member CASCADE CONSTRAINTS;
DROP TABLE community_board CASCADE CONSTRAINTS;
DROP TABLE community CASCADE CONSTRAINTS;
DROP TABLE board_image CASCADE CONSTRAINTS;
drop table approval_line;
drop table doc;
drop table format;
drop table chat_message;
drop table chatroom_user;
drop table chatroom;
drop table drive;
drop table message;
drop table calendar;
drop table addr_group;
drop table groups;
drop table addr;
drop table member CASCADE CONSTRAINTS;
drop table dept CASCADE CONSTRAINTS;
drop table grade CASCADE CONSTRAINTS;


drop sequence sq_member_id;
drop sequence sq_message_id;
drop sequence sq_grade_id;
drop sequence sq_dept_id;
drop sequence sq_drive_id;
drop sequence sq_chatroom_id;
drop sequence sq_chat_message_id;
drop sequence sq_addr_id;
drop sequence sq_groups_id;
drop sequence sq_calendar_id;
drop sequence sq_doc_id;
drop sequence sq_format_id;
drop sequence sq_approval_line_id;
DROP SEQUENCE sq_community_id;
DROP SEQUENCE sq_community_board_id;
DROP SEQUENCE sq_board_post_id;
DROP SEQUENCE sq_board_reply_id;
DROP SEQUENCE sq_board_image_id;
drop sequence sq_message_ref;


create sequence sq_member_id nocache;
create sequence sq_message_id nocache;
create sequence sq_grade_id nocache;
create sequence sq_dept_id nocache;
create sequence sq_drive_id nocache;
create sequence sq_chatroom_id nocache;
create sequence sq_chat_message_id nocache;
create sequence sq_addr_id nocache;
create sequence sq_groups_id nocache;
create sequence sq_calendar_id nocache;
create sequence sq_doc_id nocache; 
create sequence sq_format_id nocache;
create sequence sq_approval_line_id nocache;
CREATE SEQUENCE sq_community_id NOCACHE;
CREATE SEQUENCE sq_community_board_id NOCACHE;
CREATE SEQUENCE sq_board_post_id NOCACHE;
CREATE SEQUENCE sq_board_reply_id NOCACHE;
CREATE SEQUENCE sq_board_image_id NOCACHE;
create SEQUENCE sq_message_ref nocache;





create table dept(

    id number(10) primary key,
    name varchar2(100) not null

);

create table grade(

    id number(10) primary key,
    name varchar2(100) not null,
    seq number(5) not null

);

create table member(

    id number(10) primary key,
    user_id varchar2(100) not null,
    pwd varchar2(100) not null,
    name varchar2(100) not null,
    email varchar2(100) not null,
    address varchar2(100) not null,
    tel varchar2(100) not null,
    join_date date DEFAULT SYSDATE not null,
    dept_id number(10),
    grade_id number(10),
    profile_image varchar2(300),
    foreign key (dept_id) references dept(id) ON DELETE SET NULL,
    foreign key (grade_id) references grade(id) ON DELETE SET NULL 
);

create table message(

    id number(10) primary key,
    title varchar2(100) not null,
    content varchar2(300),
    receiver_id number(10),
    sender_id number(10),
    write_date date DEFAULT SYSDATE NOT NULL,
    is_read varchar2(60) default '안 읽음' CHECK (is_read IN ('읽음', '안 읽음')) not null,
    file_name varchar2(300),
    ref number(10) not null CHECK (ref >= 1),
    lev number(10) not null CHECK (lev >= 0),
    foreign key (receiver_id) references member(id) ON DELETE SET NULL,
    foreign key (sender_id) references member(id) ON DELETE SET NULL 
    
);

create table drive(

    id number(10) primary key,
    member_id number(10),
    uploader varchar2(100) not null,
    upload_date date DEFAULT SYSDATE NOT NULL,
    path varchar2(500) not null,
    foreign key (member_id) references member(id) ON DELETE CASCADE 

);

create table chatroom(

    id number(10) primary key,
    name varchar2(100) not null,
    description varchar2(200) not null,
    create_date date DEFAULT SYSDATE NOT NULL,
    type VARCHAR2(20) NOT NULL
        CHECK (type IN ('group', 'private'))
);

create table chat_message(

    id number(10) primary key,
    chatroom_id number(10) not null,
    member_id number(10),
    write_date date DEFAULT SYSDATE NOT NULL,
    content varchar2(1000) not null,
    type VARCHAR2(20) DEFAULT 'user' NOT NULL
        CHECK (type IN ('user', 'system')),
    foreign key (chatroom_id) references chatroom(id) ON DELETE CASCADE ,
    foreign key (member_id) references member(id) ON DELETE SET NULL  
);

create table chatroom_user(

    chatroom_id number(10) not null,
    member_id number(10) not null,
    join_date date DEFAULT SYSDATE NOT NULL,
    CONSTRAINT pk_chatroom PRIMARY KEY (chatroom_id, member_id),
    foreign key (chatroom_id) references chatroom(id) ON DELETE CASCADE,
    foreign key (member_id) references member(id) ON DELETE CASCADE  

);

create table groups(
    
    id number(10) primary key,
    member_id number(10) not null,
    name varchar2(100) not null,
    division NUMBER(1) NOT NULL,
    foreign key (member_id) references member(id) ON DELETE CASCADE

);

create table addr(

    id number(10) primary key,
    member_id NUMBER(10) not NULL,
    name varchar2(100),
    nickname varchar2(100),
    company varchar2(100), 
    dept varchar2(100), 
    grade varchar2(100), 
    tel varchar2(100),
    companytel varchar2(100),
    email varchar2(100),
    description varchar2(200),
    create_date DATE,
    FOREIGN KEY (member_id) references member(id) ON DELETE CASCADE
);

create table addr_group(

    addr_id number(10) not null,
    groups_id number(10) not null,
    CONSTRAINT pk_addr_group PRIMARY KEY (addr_id, groups_id),
    foreign key (addr_id) references addr(id) ON DELETE CASCADE,
    foreign key (groups_id) references groups(id) ON DELETE CASCADE

);

create table calendar(

    id number(10) primary key,
    member_id number(10),
    dept_id number(10),
    title varchar2(100) not null,
    content varchar2(300) not null,
    start_time date not null,
    end_time date not null,
    create_date date DEFAULT SYSDATE NOT NULL,
    allday NUMBER(1) not NULL,
    foreign key (member_id) references member(id) ON DELETE CASCADE,
    foreign key (dept_id) references dept(id) ON DELETE CASCADE

);

CREATE TABLE format (
    id NUMBER(10) PRIMARY KEY,
    name VARCHAR2(100) UNIQUE NOT NULL,
    template CLOB NOT NULL
);

create table doc(
    
    id number(10) primary key,
    title varchar2(100) not null,
    member_id number(10),
    write_date date DEFAULT SYSDATE NOT NULL,
    format_id NUMBER(10) NOT NULL,
    content CLOB NOT NULL,
    retention NUMBER(5) DEFAULT 5 not null CHECK (retention >= 0),
    file_name VARCHAR2(100),
    foreign key (member_id) references member(id) ON DELETE SET NULL,
    FOREIGN KEY (format_id) REFERENCES format(id)
);

CREATE TABLE approval_line (
    doc_id NUMBER(10) NOT NULL,
    member_id NUMBER(10),
    status VARCHAR2(100) DEFAULT '결재 예정' CHECK (status IN ('기안 상신', '결재 예정', '결재 완료', '참조', '반려')),
    process_date DATE,
    CONSTRAINT pk_approval_line PRIMARY KEY (doc_id, member_id),
    CONSTRAINT fk_approval_line_doc FOREIGN KEY (doc_id) REFERENCES doc(id) ON DELETE CASCADE,
    CONSTRAINT fk_approval_line_member FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE SET NULL
);

--커뮤니티  
CREATE TABLE community(
    id number(10) primary key,--고유번호
    name varchar2(100) not null, --커뮤니티 이름 
    description varchar2(100) not null,--커뮤니티 설명
    create_date date DEFAULT SYSDATE NOT NULL,-- 개설날짜 --sysdate
    member_id number(10),--개설자 아이디 (커뮤니티 최초 사용자)
    foreign key (member_id) references member(id) ON DELETE SET NULL -- 나중에 가입상태 STATUS 추가해야됨(DEFAULT '대기중' 승인, 거절)
);
--------------------------------------------------------------------------------------------
  --status VARCHAR2(20) DEFAULT '대기중' NOT NULL; 

--커뮤니티-사용자 관계
CREATE TABLE community_member (
    community_id NUMBER(10) NOT NULL,
    member_id NUMBER(10) NOT NULL,
    role VARCHAR2(100) NOT NULL -- 'master', 'submaster', 'user', '가입대기', '거절'
         CHECK (role IN ('master', 'submaster', 'user', '가입대기', '거절')), -- 역할 제한
    join_date DATE DEFAULT SYSDATE, -- 가입 승인일 (가입대기일 경우 비어있을 수도 있음)
    PRIMARY KEY (community_id, member_id),
    FOREIGN KEY (community_id) REFERENCES community(id) ON DELETE CASCADE,
    FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE CASCADE
);

----------------------------------------------------------------------------

--게시판 
 
CREATE TABLE community_board ( 
    id NUMBER(10) PRIMARY KEY, --고유번호,
    community_id NUMBER(10) NOT NULL,   --커뮤니티 아이디 --어떤 커뮤니티인지
    name VARCHAR2(100) NOT NULL,    --게시판 이름 
    description VARCHAR2(300) NOT NULL,  --게시판 설명 
    create_date DATE DEFAULT SYSDATE NOT NULL, -- 개설날짜 
    FOREIGN KEY (community_id) REFERENCES community(id) ON DELETE CASCADE
);


-----------------------------------------------------------------------------

--글쓰기 (게시글)
CREATE TABLE board_post (     
    id NUMBER(10) PRIMARY KEY,  --고유번호 
    board_id NUMBER(10) NOT NULL,   --게시판 고유번호 
    member_id NUMBER(10),              --작성자 고유번호
    title VARCHAR2(100) NOT NULL,       --제목
    content VARCHAR2(3000) NOT NULL,     --내용
    write_date DATE DEFAULT SYSDATE NOT NULL,     --작성날짜
    view_num NUMBER(10) DEFAULT 0 NOT NULL,        --조회수
    upvote NUMBER(10) DEFAULT 0 NOT NULL,       --좋아요 수
    FOREIGN KEY (board_id) REFERENCES community_board(id) ON DELETE CASCADE,
    FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE SET NULL
);

--이미지 
CREATE TABLE board_image (
    id NUMBER PRIMARY KEY,                            -- 고유 ID (시퀀스 사용)
    post_id NUMBER NOT NULL,                          -- 어느 게시글의 이미지인지 (FK)
    member_id NUMBER NOT NULL,                        -- 누가 올렸는지 (FK)
    saved_name VARCHAR2(300) NOT NULL,                -- 서버 저장용 이름 "3f2a6f91-abcd-1234-5678_내사진.jpg"
    original_name VARCHAR2(300),                      -- 사용자가 올린 원래 파일명 (다운로드용) "내사진.jpg"
    upload_date DATE DEFAULT SYSDATE NOT NULL,        -- 업로드 시점
    FOREIGN KEY (post_id) REFERENCES board_post(id) ON DELETE CASCADE,
    FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE SET NULL
);

------------------------------------------------------------------------------------


--댓글
CREATE TABLE board_reply ( 
    id NUMBER(10) PRIMARY KEY,
    board_post_id NUMBER(10) NOT NULL,
    member_id NUMBER(10),
    content VARCHAR2(300) NOT NULL,
    write_date DATE DEFAULT SYSDATE NOT NULL,
    ref NUMBER(10) default 0 NOT NULL,     -- 댓글 그룹 (원댓글의 id를 기준)
    lev NUMBER(10) DEFAULT 0 NOT NULL,  -- 깊이 (0: 원댓글, 1: 대댓글)
    sunbun NUMBER(10) DEFAULT 0 NOT NULL, -- 정렬 순서
    FOREIGN KEY (board_post_id) REFERENCES board_post(id) ON DELETE CASCADE,
    FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE SET NULL
);


-- Views
CREATE OR REPLACE VIEW doc_view AS
SELECT doc.id, TO_CHAR(write_date, 'YYYY-MM-DD') AS write_date, 
    CASE
        WHEN '결재 완료' = ALL(SELECT status FROM approval_line WHERE approval_line.doc_id = doc.id AND status NOT IN ('참조', '기안 상신')) THEN TO_CHAR((SELECT MAX(process_date) FROM approval_line WHERE approval_line.doc_id = doc.id AND status = '결재 완료'), 'YYYY-MM-DD')
        ELSE '-'
    END AS completion,
format_id, format.name AS format_name, title, doc.member_id, member.name AS writer, 
    CASE
        WHEN EXISTS (SELECT 1 FROM approval_line WHERE approval_line.doc_id = doc.id AND status = '반려') THEN '반려'
        WHEN '결재 완료' = ALL(SELECT status FROM approval_line WHERE approval_line.doc_id = doc.id AND status NOT IN ('기안 상신', '참조')) THEN '완료'
        ELSE '진행 중'
    END AS status
FROM doc
    JOIN format ON doc.format_id = format.id
    JOIN member ON doc.member_id = member.id;
