`drop table community_comment;
drop table community_post;
drop table community_member;
drop table community;
drop table draft;
drop table medical_support;
drop table leave_application;
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
create sequence sq_format_id nocache;
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
    CONSTRAINT pk_chatroom PRIMARY KEY (chatroom_id, member_id),
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
    foreign key (member_id) references member(id) ON DELETE CASCADE,
    foreign key (dept_id) references dept(id) ON DELETE CASCADE

);

CREATE TABLE format (
    id NUMBER(10) PRIMARY KEY,
    name VARCHAR2(100) UNIQUE NOT NULL
)

create table doc(
    
    id number(10) primary key,
    title varchar2(100) not null,
    member_id number(10),
    dept_id number(10),
    write_date date DEFAULT SYSDATE NOT NULL,
    retention NUMBER(5) DEFAULT 5 not null CHECK (retention >= 0),
    foreign key (member_id) references member(id) ON DELETE SET NULL,
    foreign key (dept_id) references dept(id) ON DELETE SET NULL
    
);

CREATE TABLE approval_line (
    doc_id NUMBER(10) NOT NULL,
    member_id NUMBER(10),
    status VARCHAR2(100) DEFAULT '결재 예정' CHECK (status IN ('결재 예정', '결재 완료', '참조', '반려')),
    process_date DATE DEFAULT NULL,
    CONSTRAINT pk_approval_line PRIMARY KEY (doc_id, member_id),
    CONSTRAINT fk_approval_line_doc FOREIGN KEY (doc_id) REFERENCES doc(id) ON DELETE CASCADE,
    CONSTRAINT fk_approval_line_member FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE SET NULL
);

CREATE TABLE draft (
    id NUMBER(10) PRIMARY KEY,
    doc_id NUMBER(10) UNIQUE NOT NULL,
    execution_date DATE NOT NULL,
    type VARCHAR2(100) NOT NULL CHECK (type IN ('인가', '조건부', '보류', '부결')),
    content VARCHAR2(300) NOT NULL,
    foreign key (doc_id) REFERENCES doc(id) ON DELETE CASCADE
);

CREATE TABLE medical_support (
    id NUMBER(10) PRIMARY KEY,
    doc_id NUMBER(10) UNIQUE NOT NULL,
    content VARCHAR2(300) NOT NULL,
    institution VARCHAR2(100) NOT NULL,
    diagnosis VARCHAR2(100) NOT NULL,
    requested NUMBER(10) NOT NULL CHECK (requested >= 0),
    oop NUMBER(10) NOT NULL CHECK (oop >= 0),
    foreign key (doc_id) REFERENCES doc(id) ON DELETE CASCADE
);

CREATE TABLE leave_application (
    id NUMBER(10) PRIMARY KEY,
    doc_id NUMBER(10) UNIQUE NOT NULL,
    dept VARCHAR2(300) NOT NULL,
    grade VARCHAR2(100) NOT NULL,
    name VARCHAR2(100) NOT NULL,
    type VARCHAR2(100) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    remaining NUMBER(5) NOT NULL CHECK (remaining >= 0),
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
    CONSTRAINT pk_community_member PRIMARY KEY (community_id, member_id),
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


-- Views
CREATE OR REPLACE VIEW doc_view AS
SELECT doc.id, TO_CHAR(write_date, 'YYYY-MM-DD') AS write_date, 
    CASE
        WHEN '결재 완료' = ALL(SELECT status FROM approval_line WHERE approval_line.doc_id = doc.id AND status != '참조') THEN TO_CHAR((SELECT MAX(process_date) FROM approval_line WHERE approval_line.doc_id = doc.id AND status = '결재 완료'), 'YYYY-MM-DD')
        ELSE '-'
    END AS completion,
    CASE 
        WHEN EXISTS (SELECT 1 FROM draft WHERE doc_id = doc.id) THEN '기안문'
        WHEN EXISTS (SELECT 1 FROM medical_support WHERE doc_id = doc.id) THEN '진료비 지원 신청서'
        WHEN EXISTS (SELECT 1 FROM leave_application WHERE doc_id = doc.id) THEN '휴가 신청서'
    END AS format,
title, doc.member_id, member.name AS writer, 
    CASE
        WHEN EXISTS (SELECT 1 FROM approval_line WHERE approval_line.doc_id = doc.id AND status = '반려') THEN '반려'
        WHEN '결재 완료' = ALL(SELECT status FROM approval_line WHERE approval_line.doc_id = doc.id AND status != '참조') THEN '완료'
        ELSE '진행 중'
    END AS status
FROM doc
JOIN member ON doc.member_id = member.id;

-- Sample
-- dept sample
INSERT INTO dept (id, name) VALUES (sq_dept_id.NEXTVAL, '기획부');
INSERT INTO dept (id, name) VALUES (sq_dept_id.NEXTVAL, '개발부');
INSERT INTO dept (id, name) VALUES (sq_dept_id.NEXTVAL, '인사부');

-- grade sample
INSERT INTO grade (id, name, seq) VALUES (sq_grade_id.NEXTVAL, '이사', 1);
INSERT INTO grade (id, name, seq) VALUES (sq_grade_id.NEXTVAL, '부장', 2);
INSERT INTO grade (id, name, seq) VALUES (sq_grade_id.NEXTVAL, '차장', 3);
INSERT INTO grade (id, name, seq) VALUES (sq_grade_id.NEXTVAL, '과장', 4);
INSERT INTO grade (id, name, seq) VALUES (sq_grade_id.NEXTVAL, '대리', 5);
INSERT INTO grade (id, name, seq) VALUES (sq_grade_id.NEXTVAL, '사원', 6);

-- member sample
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


-- format table sample
INSERT INTO format (id, name)
VALUES (sq_format_id.NEXTVAL, '기안문'); 

INSERT INTO format (id, name)
VALUES (sq_format_id.NEXTVAL, '진료비 지원 신청서');

INSERT INTO format (id, name)
VALUES (sq_format_id.NEXTVAL, '휴가 신청서');

-- Docs sample
INSERT INTO doc (id, title, member_id, dept_id)
VALUES (sq_doc_id.NEXTVAL, '2025 상반기 예산 집행 보고서', 1, 1);

INSERT INTO doc (id, title, member_id, dept_id)
VALUES (sq_doc_id.NEXTVAL, '재택근무 제도 개선안', 2, 2);

INSERT INTO doc (id, title, member_id, dept_id)
VALUES (sq_doc_id.NEXTVAL, '고객 응대 매뉴얼 개정안', 3, 3);

INSERT INTO doc (id, title, member_id, dept_id)
VALUES (sq_doc_id.NEXTVAL, '신규 시스템 도입 검토서', 4, 1);

INSERT INTO doc (id, title, member_id, dept_id)
VALUES (sq_doc_id.NEXTVAL, '프로젝트 Alpha 종료 보고', 5, 2);

INSERT INTO doc (id, title, member_id, dept_id)
VALUES (sq_doc_id.NEXTVAL, '직원 만족도 조사 결과 분석', 6, 3);

INSERT INTO doc (id, title, member_id, dept_id)
VALUES (sq_doc_id.NEXTVAL, '사내 교육 프로그램 개선안', 7, 1);

INSERT INTO doc (id, title, member_id, dept_id)
VALUES (sq_doc_id.NEXTVAL, '외부 감사 대응 계획', 8, 2);

INSERT INTO doc (id, title, member_id, dept_id)
VALUES (sq_doc_id.NEXTVAL, '근무환경 개선 요청서', 9, 3);

INSERT INTO doc (id, title, member_id, dept_id)
VALUES (sq_doc_id.NEXTVAL, '분기별 실적 분석 보고서', 10, 1);

INSERT INTO doc (id, title, member_id, dept_id)
VALUES (sq_doc_id.NEXTVAL, '부서 통합에 따른 운영 방안', 11, 2);

INSERT INTO doc (id, title, member_id, dept_id)
VALUES (sq_doc_id.NEXTVAL, '프로젝트 Beta 인력 요청', 12, 3);

INSERT INTO doc (id, title, member_id, dept_id)
VALUES (sq_doc_id.NEXTVAL, '회계 처리 규정 변경 제안', 13, 1);

INSERT INTO doc (id, title, member_id, dept_id)
VALUES (sq_doc_id.NEXTVAL, '신입사원 교육 일정안', 14, 2);

INSERT INTO doc (id, title, member_id, dept_id)
VALUES (sq_doc_id.NEXTVAL, '내부 보안정책 개편안', 15, 3);

INSERT INTO doc (id, title, member_id, dept_id)
VALUES (sq_doc_id.NEXTVAL, '시스템 점검 결과 보고서', 16, 1);

INSERT INTO doc (id, title, member_id, dept_id)
VALUES (sq_doc_id.NEXTVAL, '기술 세미나 개최 계획서', 17, 2);

INSERT INTO doc (id, title, member_id, dept_id)
VALUES (sq_doc_id.NEXTVAL, '인사평가 기준 개편 제안', 18, 3);

INSERT INTO doc (id, title, member_id, dept_id)
VALUES (sq_doc_id.NEXTVAL, '공간 재배치 요청서', 19, 1);

INSERT INTO doc (id, title, member_id, dept_id)
VALUES (sq_doc_id.NEXTVAL, '외부 교육 참가 보고', 20, 2);


INSERT INTO draft (id, doc_id, execution_date, type, content)
VALUES (sq_draft_id.NEXTVAL, 1, TO_DATE('2025-06-01', 'YYYY-MM-DD'), '인가', '상반기 예산 승인 요청');

INSERT INTO draft (id, doc_id, execution_date, type, content)
VALUES (sq_draft_id.NEXTVAL, 2, TO_DATE('2025-06-02', 'YYYY-MM-DD'), '조건부', '제도 개선에 대한 조건부 승인');

INSERT INTO draft (id, doc_id, execution_date, type, content)
VALUES (sq_draft_id.NEXTVAL, 3, TO_DATE('2025-06-03', 'YYYY-MM-DD'), '보류', '매뉴얼 개정안 검토 필요');

INSERT INTO draft (id, doc_id, execution_date, type, content)
VALUES (sq_draft_id.NEXTVAL, 4, TO_DATE('2025-06-04', 'YYYY-MM-DD'), '부결', '시스템 도입 불가');

INSERT INTO draft (id, doc_id, execution_date, type, content)
VALUES (sq_draft_id.NEXTVAL, 5, TO_DATE('2025-06-05', 'YYYY-MM-DD'), '인가', '프로젝트 종료 승인');

INSERT INTO draft (id, doc_id, execution_date, type, content)
VALUES (sq_draft_id.NEXTVAL, 6, TO_DATE('2025-06-06', 'YYYY-MM-DD'), '조건부', '만족도 조사 보고');

INSERT INTO draft (id, doc_id, execution_date, type, content)
VALUES (sq_draft_id.NEXTVAL, 7, TO_DATE('2025-06-07', 'YYYY-MM-DD'), '인가', '교육 개선안 승인 요청');

INSERT INTO medical_support (id, doc_id, content, institution, diagnosis, requested, oop)
VALUES (sq_medical_support_id.NEXTVAL, 8, '외부 감사로 인한 스트레스 치료 지원 요청', '서울정신건강센터', '스트레스', 200000, 40000);

INSERT INTO medical_support (id, doc_id, content, institution, diagnosis, requested, oop)
VALUES (sq_medical_support_id.NEXTVAL, 9, '업무 과중으로 인한 허리 통증 치료', '서울의료원', '요통', 250000, 50000);

INSERT INTO medical_support (id, doc_id, content, institution, diagnosis, requested, oop)
VALUES (sq_medical_support_id.NEXTVAL, 10, '잦은 출장으로 인한 무릎 통증 치료', '강남한방병원', '관절염', 300000, 60000);

INSERT INTO medical_support (id, doc_id, content, institution, diagnosis, requested, oop)
VALUES (sq_medical_support_id.NEXTVAL, 11, '장시간 근무로 인한 안구 건조증', '서울안과의원', '안구건조증', 120000, 20000);

INSERT INTO medical_support (id, doc_id, content, institution, diagnosis, requested, oop)
VALUES (sq_medical_support_id.NEXTVAL, 12, '허리디스크 재활 치료 지원 요청', '바른척추병원', '디스크', 400000, 80000);

INSERT INTO medical_support (id, doc_id, content, institution, diagnosis, requested, oop)
VALUES (sq_medical_support_id.NEXTVAL, 13, '장기 근무자 건강검진 비용 지원 요청', '한국의료원', '종합검진', 500000, 100000);

INSERT INTO medical_support (id, doc_id, content, institution, diagnosis, requested, oop)
VALUES (sq_medical_support_id.NEXTVAL, 14, '복부통증 응급 치료 요청', '서울응급병원', '복통', 180000, 30000);

INSERT INTO leave_application (id, doc_id, dept, grade, name, type, start_date, end_date, remaining, reason)
VALUES (sq_leave_application_id.NEXTVAL, 15, '기획부', '과장', '홍길동', '연차', TO_DATE('2025-07-01','YYYY-MM-DD'), TO_DATE('2025-07-03','YYYY-MM-DD'), 5, '개인 사유');

INSERT INTO leave_application (id, doc_id, dept, grade, name, type, start_date, end_date, remaining, reason)
VALUES (sq_leave_application_id.NEXTVAL, 16, '개발부', '대리', '김영희', '병가', TO_DATE('2025-07-05','YYYY-MM-DD'), TO_DATE('2025-07-07','YYYY-MM-DD'), 3, '감기');

INSERT INTO leave_application (id, doc_id, dept, grade, name, type, start_date, end_date, remaining, reason)
VALUES (sq_leave_application_id.NEXTVAL, 17, '총무부', '사원', '박민수', '연차', TO_DATE('2025-07-10','YYYY-MM-DD'), TO_DATE('2025-07-11','YYYY-MM-DD'), 7, '여행');

INSERT INTO leave_application (id, doc_id, dept, grade, name, type, start_date, end_date, remaining, reason)
VALUES (sq_leave_application_id.NEXTVAL, 18, '기획부', '차장', '이은지', '가족돌봄', TO_DATE('2025-07-15','YYYY-MM-DD'), TO_DATE('2025-07-16','YYYY-MM-DD'), 2, '자녀 병원 진료');

INSERT INTO leave_application (id, doc_id, dept, grade, name, type, start_date, end_date, remaining, reason)
VALUES (sq_leave_application_id.NEXTVAL, 19, '영업부', '과장', '조재현', '연차', TO_DATE('2025-07-18','YYYY-MM-DD'), TO_DATE('2025-07-20','YYYY-MM-DD'), 4, '휴식');

INSERT INTO leave_application (id, doc_id, dept, grade, name, type, start_date, end_date, remaining, reason)
VALUES (sq_leave_application_id.NEXTVAL, 20, '인사부', '대리', '정해린', '병가', TO_DATE('2025-07-22','YYYY-MM-DD'), TO_DATE('2025-07-23','YYYY-MM-DD'), 6, '치료');

-- approval line sample
-- 1) 기안자 결재 완료
INSERT INTO approval_line (doc_id, member_id, status, process_date)
SELECT d.id, d.member_id, '결재 완료', d.write_date
FROM doc d
WHERE d.member_id IS NOT NULL AND d.id BETWEEN 1 AND 20;

-- 2) 참조자 랜덤 (기안자 제외, 이미 삽입된 멤버 제외)
INSERT INTO approval_line (doc_id, member_id, status, process_date)
SELECT
  d.id,
  (
    SELECT id FROM (
      SELECT id, DBMS_RANDOM.VALUE() AS rnd
      FROM member
      WHERE id != d.member_id
        AND id NOT IN (
          SELECT member_id FROM approval_line al WHERE al.doc_id = d.id
        )
      ORDER BY rnd
    ) WHERE ROWNUM = 1
  ),
  '참조',
  d.write_date
FROM doc d
WHERE d.id BETWEEN 1 AND 20;

-- 3) 나머지 멤버 결재 예정
INSERT INTO approval_line (doc_id, member_id, status, process_date)
SELECT d.id, m.id, '결재 예정', NULL
FROM doc d
CROSS JOIN member m
WHERE d.id BETWEEN 1 AND 20
  AND m.id NOT IN (
    SELECT member_id FROM approval_line al WHERE al.doc_id = d.id
  );


COMMIT;