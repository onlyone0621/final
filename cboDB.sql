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
    file_name varchar2(300) not null,
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
INSERT INTO dept (name) VALUES ('기획부');
INSERT INTO dept (name) VALUES ('개발부');
INSERT INTO dept (name) VALUES ('인사부');

INSERT INTO grade (name, seq) VALUES ('이사', 1);
INSERT INTO grade (name, seq) VALUES ('부장', 2);
INSERT INTO grade (name, seq) VALUES ('차장', 3);
INSERT INTO grade (name, seq) VALUES ('과장', 4);
INSERT INTO grade (name, seq) VALUES ('대리', 5);
INSERT INTO grade (name, seq) VALUES ('사원', 6);

INSERT INTO member (
    user_id, pwd, name, email, address, tel, dept_id, grade_id, profile_image
) VALUES (
    'user01', '1234', '홍길동', 'user01@example.com', '서울시 강남구', '010-1111-1111', 1, 2, NULL
);

INSERT INTO member (
    user_id, pwd, name, email, address, tel, dept_id, grade_id, profile_image
) VALUES (
    'user02', '1234', '김철수', 'user02@example.com', '서울시 서초구', '010-2222-2222', 2, 4, NULL
);

INSERT INTO member (
    user_id, pwd, name, email, address, tel, dept_id, grade_id, profile_image
) VALUES (
    'user03', '1234', '이영희', 'user03@example.com', '서울시 송파구', '010-3333-3333', 3, 6, NULL
);

INSERT INTO member (
    user_id, pwd, name, email, address, tel, dept_id, grade_id, profile_image
) VALUES (
    'user04', '1234', '박민수', 'user04@example.com', '경기도 수원시', '010-4444-4444', 1, 3, NULL
);

INSERT INTO member (
    user_id, pwd, name, email, address, tel, dept_id, grade_id, profile_image
) VALUES (
    'user05', '1234', '최수정', 'user05@example.com', '인천시 연수구', '010-5555-5555', 2, 5, NULL
);

INSERT INTO member (
    user_id, pwd, name, email, address, tel, dept_id, grade_id, profile_image
) VALUES (
    'user06', '1234', '조현우', 'user06@example.com', '서울시 동작구', '010-6666-6666', 3, 1, NULL
);

INSERT INTO member (
    user_id, pwd, name, email, address, tel, dept_id, grade_id, profile_image
) VALUES (
    'user07', '1234', '한예진', 'user07@example.com', '서울시 은평구', '010-7777-7777', 1, 6, NULL
);

INSERT INTO member (
    user_id, pwd, name, email, address, tel, dept_id, grade_id, profile_image
) VALUES (
    'user08', '1234', '정재훈', 'user08@example.com', '경기도 용인시', '010-8888-8888', 2, 3, NULL
);

INSERT INTO member (
    user_id, pwd, name, email, address, tel, dept_id, grade_id, profile_image
) VALUES (
    'user09', '1234', '서지수', 'user09@example.com', '서울시 마포구', '010-9999-9999', 3, 2, NULL
);

INSERT INTO member (
    user_id, pwd, name, email, address, tel, dept_id, grade_id, profile_image
) VALUES (
    'user10', '1234', '장도연', 'user10@example.com', '서울시 종로구', '010-1010-1010', 1, 4, NULL
);

INSERT INTO member (
    user_id, pwd, name, email, address, tel, dept_id, grade_id, profile_image
) VALUES (
    'user11', '1234', '백민정', 'user11@example.com', '서울시 중랑구', '010-1112-1112', 2, 3, NULL
);

INSERT INTO member (
    user_id, pwd, name, email, address, tel, dept_id, grade_id, profile_image
) VALUES (
    'user12', '1234', '윤정우', 'user12@example.com', '서울시 양천구', '010-1212-1212', 3, 4, NULL
);

INSERT INTO member (
    user_id, pwd, name, email, address, tel, dept_id, grade_id, profile_image
) VALUES (
    'user13', '1234', '강서연', 'user13@example.com', '경기도 부천시', '010-1313-1313', 1, 6, NULL
);

INSERT INTO member (
    user_id, pwd, name, email, address, tel, dept_id, grade_id, profile_image
) VALUES (
    'user14', '1234', '문상혁', 'user14@example.com', '서울시 구로구', '010-1414-1414', 2, 2, NULL
);

INSERT INTO member (
    user_id, pwd, name, email, address, tel, dept_id, grade_id, profile_image
) VALUES (
    'user15', '1234', '오하늘', 'user15@example.com', '경기도 고양시', '010-1515-1515', 3, 5, NULL
);

INSERT INTO member (
    user_id, pwd, name, email, address, tel, dept_id, grade_id, profile_image
) VALUES (
    'user16', '1234', '신도현', 'user16@example.com', '서울시 노원구', '010-1616-1616', 1, 1, NULL
);

INSERT INTO member (
    user_id, pwd, name, email, address, tel, dept_id, grade_id, profile_image
) VALUES (
    'user17', '1234', '김다은', 'user17@example.com', '경기도 남양주시', '010-1717-1717', 2, 6, NULL
);

INSERT INTO member (
    user_id, pwd, name, email, address, tel, dept_id, grade_id, profile_image
) VALUES (
    'user18', '1234', '조민기', 'user18@example.com', '서울시 강서구', '010-1818-1818', 3, 3, NULL
);

INSERT INTO member (
    user_id, pwd, name, email, address, tel, dept_id, grade_id, profile_image
) VALUES (
    'user19', '1234', '서유리', 'user19@example.com', '서울시 은평구', '010-1919-1919', 1, 2, NULL
);

INSERT INTO member (
    user_id, pwd, name, email, address, tel, dept_id, grade_id, profile_image
) VALUES (
    'user20', '1234', '임재훈', 'user20@example.com', '서울시 관악구', '010-2020-2020', 2, 4, NULL
);
