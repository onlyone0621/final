drop table community_comment;
drop table community_post;
drop table community_member;
drop table community;
drop table draft;
drop table medical_support;
drop table leave_application;
drop table approval_line;
drop table doc;
drop table chat_message;
drop table chatroom_user;
drop table chatroom;
drop table drive;
drop table message;
drop table calendar;
drop table addr_group;
drop table groups;
drop table addr;
drop table member;
drop table dept;
drop table grade;


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
drop sequence sq_approval_line_id;
drop sequence sq_draft_id;
drop sequence sq_medical_support_id;
drop sequence sq_leave_application_id;
drop sequence sq_community_id;
drop sequence sq_community_post_id;
drop sequence sq_community_comment_id;


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
create sequence sq_approval_line_id nocache;
create sequence sq_draft_id nocache;
create sequence sq_medical_support_id nocache;
create sequence sq_leave_application_id nocache;
create sequence sq_community_id nocache;
create sequence sq_community_post_id nocache;
create sequence sq_community_comment_id nocache;





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
    is_read varchar2(60) default '안 읽음' not null,
    file_name varchar2(300),
    ref number(10) not null,
    lev number(10) not null,
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
    create_date date DEFAULT SYSDATE NOT NULL

);

create table chat_message(

    id number(10) primary key,
    chatroom_id number(10) not null,
    member_id number(10),
    write_date date DEFAULT SYSDATE NOT NULL,
    content varchar2(1000) not null,
    foreign key (chatroom_id) references chatroom(id) ON DELETE CASCADE ,
    foreign key (member_id) references member(id) ON DELETE SET NULL  
);

create table chatroom_user(

    chatroom_id number(10) not null,
    member_id number(10) not null,
    join_date date DEFAULT SYSDATE NOT NULL,
    foreign key (chatroom_id) references chatroom(id) ON DELETE CASCADE,
    foreign key (member_id) references member(id) ON DELETE CASCADE  

);

create table groups(
    
    id number(10) primary key,
    member_id number(10) not null,
    name varchar2(100) not null,
    foreign key (member_id) references member(id) ON DELETE CASCADE

);

create table addr(

    id number(10) primary key,
    name varchar2(100) not null,
    nickname varchar2(100) not null,
    company varchar2(100) not null, 
    dept varchar2(100) not null, 
    grade varchar2(100) not null, 
    tel varchar2(100) not null,
    email varchar2(100) not null,
    description varchar2(200) not null

);

create table addr_group(

    addr_id number(10) not null,
    groups_id number(10) not null,
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
    foreign key (member_id) references member(id) ON DELETE CASCADE,
    foreign key (dept_id) references dept(id) ON DELETE CASCADE

);

create table doc(
    
    id number(10) primary key,
    title varchar2(100) not null,
    member_id number(10),
    dept_id number(10),
    write_date date DEFAULT SYSDATE NOT NULL,
    retention NUMBER(5) not null,
    foreign key (member_id) references member(id) ON DELETE SET NULL,
    foreign key (dept_id) references dept(id) ON DELETE SET NULL
    
);

create table approval_line( 

    doc_id number(10) NOT NULL,
    member_id number(10),
    status varchar2(100) default '결재 예정',
    process_date DATE DEFAULT NULL,     
    foreign key (doc_id) references doc(id) ON DELETE CASCADE,
    foreign key (member_id) references member(id) ON DELETE SET NULL
);

CREATE TABLE draft (
    id NUMBER(10) PRIMARY KEY,
    doc_id NUMBER(10) UNIQUE NOT NULL,
    execution_date DATE NOT NULL,
    type VARCHAR2(100) NOT NULL,
    content VARCHAR2(300) NOT NULL,
    foreign key (doc_id) REFERENCES doc(id) ON DELETE CASCADE
);

CREATE TABLE medical_support (
    id NUMBER(10) PRIMARY KEY,
    doc_id NUMBER(10) UNIQUE NOT NULL,
    content VARCHAR2(300) NOT NULL,
    institution VARCHAR2(100) NOT NULL,
    diagnosis VARCHAR2(100) NOT NULL,
    requested NUMBER(10) NOT NULL,
    oop NUMBER(10) NOT NULL,
    foreign key (doc_id) REFERENCES doc(id) ON DELETE CASCADE
);

CREATE TABLE leave_application (
    id NUMBER(10) PRIMARY KEY,
    doc_id NUMBER(10) UNIQUE NOT NULL,
    dept VARCHAR2(300) NOT NULL,
    grade VARCHAR2(100) NOT NULL,
    name VARCHAR2(100) NOT NULL,
    type NUMBER(10) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    remaining NUMBER(5) NOT NULL,
    reason VARCHAR2(300) NOT NULL,
    foreign key (doc_id) REFERENCES doc(id) ON DELETE CASCADE
);


create table community(

    id number(10) primary key,
    name varchar2(100) not null,
    description varchar2(100) not null,
    create_date date DEFAULT SYSDATE NOT NULL,
    member_id number(10),
    foreign key (member_id) references member(id) ON DELETE SET NULL

);

create table community_post(

    id number(10) primary key,
    community_id number(10) not null,
    title varchar2(100) not null,
    member_id number(10),
    content varchar2(300) not null,
    write_date date DEFAULT SYSDATE NOT NULL,
    view_num number(10) default 0 not null,
    upvote number(10) default 0 not null,
    foreign key (community_id) references community(id) ON DELETE CASCADE,
    foreign key (member_id) references member(id) ON DELETE SET NULL

);

create table community_member(

    community_id number(10) NOT NULL,
    member_id number(10) NOT NULL,
    type varchar2(100),
    join_date date DEFAULT SYSDATE NOT NULL,
    foreign key (community_id) references community(id) ON DELETE CASCADE,
    foreign key (member_id) references member(id) ON DELETE CASCADE

);

create table community_comment(

    id number(10) primary key,
    community_post_id number(10) not null,
    member_id number(10),
    content varchar2(300) not null,
    write_date date DEFAULT SYSDATE NOT NULL,
    upvote number(10) default 0 not null,
    ref NUMBER(10) NOT NULL,
    lev NUMBER(10) DEFAULT 0 NOT NULL,
    sunbun NUMBER(10) DEFAULT 0 NOT NULL,
    foreign key (community_post_id) references community_post(id) ON DELETE CASCADE,
    foreign key (member_id) references member(id) ON DELETE SET NULL

);


-- Sample
INSERT INTO dept (id, name) VALUES (sq_dept_id.NEXTVAL, '기획부');
INSERT INTO dept (id, name) VALUES (sq_dept_id.NEXTVAL, '개발부');
INSERT INTO dept (id, name) VALUES (sq_dept_id.NEXTVAL, '인사부');

INSERT INTO grade (id, name, seq) VALUES (sq_grade_id.NEXTVAL, '이사', 1);
INSERT INTO grade (id, name, seq) VALUES (sq_grade_id.NEXTVAL, '부장', 2);
INSERT INTO grade (id, name, seq) VALUES (sq_grade_id.NEXTVAL, '차장', 3);
INSERT INTO grade (id, name, seq) VALUES (sq_grade_id.NEXTVAL, '과장', 4);
INSERT INTO grade (id, name, seq) VALUES (sq_grade_id.NEXTVAL, '대리', 5);
INSERT INTO grade (id, name, seq) VALUES (sq_grade_id.NEXTVAL, '사원', 6);

INSERT INTO member (
    id, user_id, pwd, name, email, address, tel, join_date, dept_id, grade_id, profile_image
) VALUES (
    sq_member_id.NEXTVAL, 'user001', '1234', '홍길동', 'user001@example.com', '서울시 강남구', '010-1001-1001', DEFAULT, 1, 2, NULL
);

INSERT INTO member (
    id, user_id, pwd, name, email, address, tel, join_date, dept_id, grade_id, profile_image
) VALUES (
    sq_member_id.NEXTVAL, 'user002', '1234', '김철수', 'user002@example.com', '서울시 서초구', '010-1002-1002', DEFAULT, 2, 4, NULL
);

INSERT INTO member (
    id, user_id, pwd, name, email, address, tel, join_date, dept_id, grade_id, profile_image
) VALUES (
    sq_member_id.NEXTVAL, 'user003', '1234', '이영희', 'user003@example.com', '서울시 송파구', '010-1003-1003', DEFAULT, 3, 6, NULL
);

INSERT INTO member (
    id, user_id, pwd, name, email, address, tel, join_date, dept_id, grade_id, profile_image
) VALUES (
    sq_member_id.NEXTVAL, 'user004', '1234', '박민수', 'user004@example.com', '서울시 강북구', '010-1004-1004', DEFAULT, 1, 3, NULL
);

INSERT INTO member (
    id, user_id, pwd, name, email, address, tel, join_date, dept_id, grade_id, profile_image
) VALUES (
    sq_member_id.NEXTVAL, 'user005', '1234', '최수정', 'user005@example.com', '경기도 성남시', '010-1005-1005', DEFAULT, 2, 5, NULL
);

INSERT INTO member (
    id, user_id, pwd, name, email, address, tel, join_date, dept_id, grade_id, profile_image
) VALUES (
    sq_member_id.NEXTVAL, 'user006', '1234', '조현우', 'user006@example.com', '서울시 종로구', '010-1006-1006', DEFAULT, 3, 1, NULL
);

INSERT INTO member (
    id, user_id, pwd, name, email, address, tel, join_date, dept_id, grade_id, profile_image
) VALUES (
    sq_member_id.NEXTVAL, 'user007', '1234', '한예진', 'user007@example.com', '서울시 은평구', '010-1007-1007', DEFAULT, 1, 6, NULL
);

INSERT INTO member (
    id, user_id, pwd, name, email, address, tel, join_date, dept_id, grade_id, profile_image
) VALUES (
    sq_member_id.NEXTVAL, 'user008', '1234', '정재훈', 'user008@example.com', '경기도 안양시', '010-1008-1008', DEFAULT, 2, 3, NULL
);

INSERT INTO member (
    id, user_id, pwd, name, email, address, tel, join_date, dept_id, grade_id, profile_image
) VALUES (
    sq_member_id.NEXTVAL, 'user009', '1234', '서지수', 'user009@example.com', '서울시 마포구', '010-1009-1009', DEFAULT, 3, 2, NULL
);

INSERT INTO member (
    id, user_id, pwd, name, email, address, tel, join_date, dept_id, grade_id, profile_image
) VALUES (
    sq_member_id.NEXTVAL, 'user010', '1234', '장도연', 'user010@example.com', '서울시 관악구', '010-1010-1010', DEFAULT, 1, 4, NULL
);

INSERT INTO member (
    id, user_id, pwd, name, email, address, tel, join_date, dept_id, grade_id, profile_image
) VALUES (
    sq_member_id.NEXTVAL, 'user011', '1234', '이수정', 'user011@example.com', '서울시 강남구', '010-1011-1011', DEFAULT, 2, 1, NULL
);

INSERT INTO member (
    id, user_id, pwd, name, email, address, tel, join_date, dept_id, grade_id, profile_image
) VALUES (
    sq_member_id.NEXTVAL, 'user012', '1234', '김지훈', 'user012@example.com', '서울시 서대문구', '010-1012-1012', DEFAULT, 3, 5, NULL
);

INSERT INTO member (
    id, user_id, pwd, name, email, address, tel, join_date, dept_id, grade_id, profile_image
) VALUES (
    sq_member_id.NEXTVAL, 'user013', '1234', '박시은', 'user013@example.com', '경기도 성남시', '010-1013-1013', DEFAULT, 1, 6, NULL
);

INSERT INTO member (
    id, user_id, pwd, name, email, address, tel, join_date, dept_id, grade_id, profile_image
) VALUES (
    sq_member_id.NEXTVAL, 'user014', '1234', '최민준', 'user014@example.com', '서울시 노원구', '010-1014-1014', DEFAULT, 2, 3, NULL
);

INSERT INTO member (
    id, user_id, pwd, name, email, address, tel, join_date, dept_id, grade_id, profile_image
) VALUES (
    sq_member_id.NEXTVAL, 'user015', '1234', '한지민', 'user015@example.com', '서울시 동작구', '010-1015-1015', DEFAULT, 3, 2, NULL
);

INSERT INTO member (
    id, user_id, pwd, name, email, address, tel, join_date, dept_id, grade_id, profile_image
) VALUES (
    sq_member_id.NEXTVAL, 'user016', '1234', '오세훈', 'user016@example.com', '서울시 광진구', '010-1016-1016', DEFAULT, 1, 4, NULL
);

INSERT INTO member (
    id, user_id, pwd, name, email, address, tel, join_date, dept_id, grade_id, profile_image
) VALUES (
    sq_member_id.NEXTVAL, 'user017', '1234', '신예은', 'user017@example.com', '경기도 부천시', '010-1017-1017', DEFAULT, 2, 6, NULL
);

INSERT INTO member (
    id, user_id, pwd, name, email, address, tel, join_date, dept_id, grade_id, profile_image
) VALUES (
    sq_member_id.NEXTVAL, 'user018', '1234', '정동현', 'user018@example.com', '서울시 중구', '010-1018-1018', DEFAULT, 3, 1, NULL
);

INSERT INTO member (
    id, user_id, pwd, name, email, address, tel, join_date, dept_id, grade_id, profile_image
) VALUES (
    sq_member_id.NEXTVAL, 'user019', '1234', '강하늘', 'user019@example.com', '서울시 마포구', '010-1019-1019', DEFAULT, 1, 5, NULL
);

INSERT INTO member (
    id, user_id, pwd, name, email, address, tel, join_date, dept_id, grade_id, profile_image
) VALUES (
    sq_member_id.NEXTVAL, 'user020', '1234', '유인나', 'user020@example.com', '서울시 성북구', '010-1020-1020', DEFAULT, 2, 4, NULL
);
