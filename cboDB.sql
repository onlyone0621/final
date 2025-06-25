drop table community_comment;
drop table community_post;
drop table community_member;
drop table community;
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
    file_name VARCHAR2(100) DEFAULT NULL,
    foreign key (member_id) references member(id) ON DELETE SET NULL,
    FOREIGN KEY (format_id) REFERENCES format(id)
);

CREATE TABLE approval_line (
    doc_id NUMBER(10) NOT NULL,
    member_id NUMBER(10),
    status VARCHAR2(100) DEFAULT '결재 예정' CHECK (status IN ('기안 상신', '결재 예정', '결재 완료', '참조', '반려')),
    process_date DATE DEFAULT NULL,
    CONSTRAINT pk_approval_line PRIMARY KEY (doc_id, member_id),
    CONSTRAINT fk_approval_line_doc FOREIGN KEY (doc_id) REFERENCES doc(id) ON DELETE CASCADE,
    CONSTRAINT fk_approval_line_member FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE SET NULL
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
format_id, format.name AS format_name, title, doc.member_id, member.name AS writer, 
    CASE
        WHEN EXISTS (SELECT 1 FROM approval_line WHERE approval_line.doc_id = doc.id AND status = '반려') THEN '반려'
        WHEN '결재 완료' = ALL(SELECT status FROM approval_line WHERE approval_line.doc_id = doc.id AND status != '참조') THEN '완료'
        ELSE '진행 중'
    END AS status
FROM doc
    JOIN format ON doc.format_id = format.id
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

INSERT INTO format 
VALUES (sq_format_id.NEXTVAL, '기안문', '<div style="font-family: Arial, sans-serif; margin: 20px;">
  <h1 style="text-align: center; letter-spacing: 15px;">기 안 용 지</h1>
  <div style="text-align: right; margin-top: 20px; margin-bottom: 10px; font-size: 12px;">[결재선]</div>
  <div style="display: flex; justify-content: space-between; gap: 20px;">
    <table style="width: 400px; border-collapse: collapse; margin-bottom: 20px;">
      <tr>
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;">부서</td>
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;" id="dept"></td>
      </tr>
      <tr>
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;">기안일</td>
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;" id="write-date"></td>
      </tr>
      <tr>
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;">기안자</td>
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;" id="writer"></td>
      </tr>
      <tr>
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;">시행일자</td>
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;">
          <input type="text" style="width: 100%; box-sizing: border-box;">
        </td>
      </tr>
      <tr>
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;">결재내용</td>
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;">
          <label><input type="radio" name="type" checked>인가</label>
          <label><input type="radio" name="type">조건부</label>
          <label><input type="radio" name="type">보류</label>
          <label><input type="radio" name="type">부결</label>
        </td>
      </tr>
    </table>
    <div id="approval-line-container" style="display: flex; gap: 10px;">
    </div>
  </div>
  <table style="width: 100%; border-collapse: collapse; margin-bottom: 10px;">
    <tr>
      <th style="border: 1px solid #000; padding: 8px; background-color: #ccc; text-align: left;">제목</th>
      <td style="border: 1px solid #000; padding: 8px;">
        <input type="text" style="width: 100%; box-sizing: border-box;">
      </td>
    </tr>
    <tr>
      <th colspan="2" style="border: 1px solid #000; padding: 8px; background-color: #ccc; text-align: center;">상 세 내 용</th>
    </tr>
  </table>
  <div contenteditable="true" style="width: 100%; height: 300px; border: 1px solid #000; font-size: 14px; box-sizing: border-box; padding: 10px;">
    본문
  </div>
</div>');

INSERT INTO format 
VALUES (sq_format_id.NEXTVAL, '진료비 지원 신청서', '   <div style="font-family: Arial, sans-serif; margin: 20px;">
  <h1 style="text-align: center; letter-spacing: 10px;">진료비지원신청서</h1>
  <div style="text-align: right; font-size: 12px; margin-bottom: 10px;">[결재선]</div>
  <div style="display: flex; justify-content: space-between; gap: 20px;">
    <table style="width: 400px; border-collapse: collapse; margin-bottom: 20px;">
      <tr>
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;">부서</td>
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;" id="dept"></td>
      </tr>
      <tr>
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;">기안일</td>
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;" id="write-date"></td>
      </tr>
      <tr>
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;">기안자</td>
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;" id="writer"></td>
      </tr>
    </table>
    <div id="approval-line-container" style="display: flex; gap: 10px;">
    </div>
  </div>
  <table style="width: 100%; border-collapse: collapse; margin-bottom: 10px;">
    <tr>
      <th style="border: 1px solid #000; padding: 5px; background-color: #ccc;">제목</th>
      <td style="border: 1px solid #000; padding: 5px;">
        <input type="text" style="width: 100%; box-sizing: border-box;">
      </td>
    </tr>
    <tr>
      <td colspan="2" style="border: 1px solid #000; padding: 5px;">
        <div contenteditable="true" style="height: 200px; width: 100%; outline: none;">
          본문
        </div>
      </td>
    </tr>
  </table>
  <table style="width: 100%; border-collapse: collapse; margin-bottom: 20px;">
    <tr>
      <td style="border: 1px solid #000; padding: 5px; width: 120px;">진료기관명</td>
      <td style="border: 1px solid #000; padding: 5px;">
        <input type="text" style="width: 100%; box-sizing: border-box;">
      </td>
      <td style="border: 1px solid #000; padding: 5px; width: 120px;">병명</td>
      <td style="border: 1px solid #000; padding: 5px;">
        <input type="text" style="width: 100%; box-sizing: border-box;">
      </td>
    </tr>
    <tr>
      <td style="border: 1px solid #000; padding: 5px;">신청금액</td>
      <td style="border: 1px solid #000; padding: 5px;">
        <input type="text" style="width: 90%; box-sizing: border-box;"> 원
      </td>
      <td style="border: 1px solid #000; padding: 5px;">본인부담금</td>
      <td style="border: 1px solid #000; padding: 5px;">
        <input type="text" style="width: 90%; box-sizing: border-box;"> 원
      </td>
    </tr>
  </table>
    <div style="font-size: 14px; line-height: 1.6; text-align: center;">
    복리후생 규정 제2장 복리후생 제4조 ⑤ 항에 의거 지원<br>
    "임직원 본인과 직계가족의 질병 및 재판 의료비가 발생하여 부담한 비용이 년간 50만원을 초과한 경우<br>
    그 초과금에 대한 50%(1인 연간 300만원 한도)를 지원한다."<br>
    아래와 같이 진료비를 신청합니다.
    </div>
    <div style="margin-top: 10px; font-size: 13px; text-align: center;">
    * 첨부서류 : 진료비 계산서·영수증(병원발행용), 의사진단서, 가족관계증빙자료(통본 또는 가족관계증명서)
    </div>
</div>');

INSERT INTO format 
VALUES (sq_format_id.NEXTVAL, '휴가 신청서', '    <div style="font-family: Arial, sans-serif; margin: 20px;">
  <h1 style="text-align: center; letter-spacing: 15px;">휴 가 신 청 서</h1>
  <div style="text-align: right; font-size: 12px; margin-bottom: 5px;">[결재선]</div>
  <div style="display: flex; justify-content: space-between; gap: 20px;">
    <table style="width: 400px; border-collapse: collapse; margin-bottom: 20px;">
      <tr>
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;">부서</td>
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;" id="dept"></td>
      </tr>
      <tr>
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;">기안일</td>
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;" id="write-date"></td>
      </tr>
      <tr>
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;">기안자</td>
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;" id="writer"></td>
      </tr>
    </table>
    <div id="approval-line-container" style="display: flex; gap: 10px;">
    </div>
  </div>

  <table style="border-collapse: collapse; width: 100%; margin-bottom: 10px;">
  <tr>
    <th style="border: 1px solid #000; padding: 8px; width: 100px; background-color: #ccc; text-align: center;">제목</th>
    <td style="border: 1px solid #000; padding: 8px;">
      <input type="text" style="width: 100%; box-sizing: border-box;">
    </td>
  </tr>
</table>
  <table style="border-collapse: collapse; width: 100%; margin-bottom: 20px;">
    <tr>
      <th colspan="4" style="border: 1px solid #000; padding: 8px; background-color: #ccc; text-align: center;">신청인</th>
    </tr>
    <tr>
      <td style="border: 1px solid #000; padding: 8px; width: 100px;">소속</td>
      <td style="border: 1px solid #000; padding: 8px;"><input type="text" style="width: 100%; box-sizing: border-box;"></td>
      <td style="border: 1px solid #000; padding: 8px; width: 100px;">직책</td>
      <td style="border: 1px solid #000; padding: 8px;"><input type="text" style="width: 100%; box-sizing: border-box;"></td>
    </tr>
    <tr>
      <td style="border: 1px solid #000; padding: 8px;">성명</td>
      <td colspan="3" style="border: 1px solid #000; padding: 8px;"><input type="text" style="width: 100%; box-sizing: border-box;"></td>
    </tr>
  </table>
  <table style="border-collapse: collapse; width: 100%; margin-bottom: 20px;">
    <tr>
      <td style="border: 1px solid #000; padding: 8px; width: 100px;">휴가종류</td>
      <td colspan="3" style="border: 1px solid #000; padding: 8px;"><input type="text" style="width: 100%; box-sizing: border-box;"></td>
    </tr>
    <tr>
      <td style="border: 1px solid #000; padding: 8px;">휴가기간</td>
      <td style="border: 1px solid #000; padding: 8px;">
        <input type="text" style="width: 100%; box-sizing: border-box;">
      </td>
      <td style="border: 1px solid #000; padding: 8px; width: 100px; text-align: center;">~</td>
      <td style="border: 1px solid #000; padding: 8px;">
        <input type="text" style="width: 100%; box-sizing: border-box;">
      </td>
    </tr>
    <tr>
      <td style="border: 1px solid #000; padding: 8px;">잔여일</td>
      <td colspan="3" style="border: 1px solid #000; padding: 8px;">
        <input type="text" style="width: 90%; box-sizing: border-box;"> 일
      </td>
    </tr>
    <tr>
      <td style="border: 1px solid #000; padding: 8px;">휴가사유</td>
      <td colspan="3" style="border: 1px solid #000; padding: 8px;">
        <input type="text" style="width: 100%; box-sizing: border-box;">
      </td>
    </tr>
  </table>
  <div style="margin-top: 20px; font-size: 14px; text-align: center;">
    휴가기준 제 13조에 의거 휴가를 신청하오니 허락하여 주시기 바랍니다.
  </div>
</div>');

-- Docs sample
INSERT INTO doc (id, title, member_id, format_id, content)
VALUES (sq_doc_id.NEXTVAL, '2025 상반기 예산 집행 보고서', 1, 1, '2025년 상반기 동안의 예산 집행 현황과 결과를 보고합니다.');

INSERT INTO doc (id, title, member_id, format_id, content)
VALUES (sq_doc_id.NEXTVAL, '재택근무 제도 개선안', 2, 1, '현행 재택근무 제도의 문제점과 개선 방안을 제안합니다.');

INSERT INTO doc (id, title, member_id, format_id, content)
VALUES (sq_doc_id.NEXTVAL, '고객 응대 매뉴얼 개정안', 3, 1, '고객 응대 매뉴얼의 개선 필요성과 개정 방향을 제시합니다.');

INSERT INTO doc (id, title, member_id, format_id, content)
VALUES (sq_doc_id.NEXTVAL, '신규 시스템 도입 검토서', 4, 1, '신규 시스템 도입을 위한 기술적 및 경제적 타당성 검토 보고서입니다.');

INSERT INTO doc (id, title, member_id, format_id, content)
VALUES (sq_doc_id.NEXTVAL, '프로젝트 Alpha 종료 보고', 5, 1, '프로젝트 Alpha의 진행 결과와 종료에 대한 종합 보고입니다.');

INSERT INTO doc (id, title, member_id, format_id, content)
VALUES (sq_doc_id.NEXTVAL, '직원 만족도 조사 결과 분석', 6, 1, '직원 만족도 조사의 분석 결과와 향후 개선 방향을 담았습니다.');

INSERT INTO doc (id, title, member_id, format_id, content)
VALUES (sq_doc_id.NEXTVAL, '사내 교육 프로그램 개선안', 7, 1, '사내 교육 프로그램의 효과성과 개선 방향에 대한 제안서입니다.');

INSERT INTO doc (id, title, member_id, format_id, content)
VALUES (sq_doc_id.NEXTVAL, '외부 감사 대응 계획', 8, 2, '예정된 외부 감사를 위한 대응 계획과 준비 사항을 정리한 문서입니다.');

INSERT INTO doc (id, title, member_id, format_id, content)
VALUES (sq_doc_id.NEXTVAL, '근무환경 개선 요청서', 9, 2, '직원들의 근무환경 개선을 위한 요청사항을 담은 문서입니다.');

INSERT INTO doc (id, title, member_id, format_id, content)
VALUES (sq_doc_id.NEXTVAL, '분기별 실적 분석 보고서', 10, 2, '이번 분기의 실적 데이터를 분석하여 보고합니다.');

INSERT INTO doc (id, title, member_id, format_id, content)
VALUES (sq_doc_id.NEXTVAL, '부서 통합에 따른 운영 방안', 11, 2, '부서 통합에 따른 조직 운영 방안과 고려 사항을 기술한 문서입니다.');

INSERT INTO doc (id, title, member_id, format_id, content)
VALUES (sq_doc_id.NEXTVAL, '프로젝트 Beta 인력 요청', 12, 2, '프로젝트 Beta 수행을 위한 추가 인력 요청서입니다.');

INSERT INTO doc (id, title, member_id, format_id, content)
VALUES (sq_doc_id.NEXTVAL, '회계 처리 규정 변경 제안', 13, 2, '회계 처리 규정의 효율성을 위한 변경 제안서입니다.');

INSERT INTO doc (id, title, member_id, format_id, content)
VALUES (sq_doc_id.NEXTVAL, '신입사원 교육 일정안', 14, 2, '신입사원 교육의 세부 일정 및 계획안을 포함한 문서입니다.');

INSERT INTO doc (id, title, member_id, format_id, content)
VALUES (sq_doc_id.NEXTVAL, '내부 보안정책 개편안', 15, 3, '내부 정보 보안정책의 문제점과 개편 방향을 제시합니다.');

INSERT INTO doc (id, title, member_id, format_id, content)
VALUES (sq_doc_id.NEXTVAL, '시스템 점검 결과 보고서', 16, 3, '시스템 정기 점검 결과와 발견된 이슈를 정리한 보고서입니다.');  

INSERT INTO doc (id, title, member_id, format_id, content)
VALUES (sq_doc_id.NEXTVAL, '기술 세미나 개최 계획서', 17, 3, '예정된 기술 세미나의 개최 목적, 일정, 예산 등을 포함한 계획서입니다.');

INSERT INTO doc (id, title, member_id, format_id, content)
VALUES (sq_doc_id.NEXTVAL, '인사평가 기준 개편 제안', 18, 3, '인사평가 기준의 공정성과 객관성 향상을 위한 개편 제안서입니다.');

INSERT INTO doc (id, title, member_id, format_id, content)
VALUES (sq_doc_id.NEXTVAL, '공간 재배치 요청서', 19, 3, '부서 간 공간 재배치를 위한 요청서입니다.');

INSERT INTO doc (id, title, member_id, format_id, content)
VALUES (sq_doc_id.NEXTVAL, '외부 교육 참가 보고', 20, 3, '외부 교육 참가 후 습득한 지식과 느낀 점을 정리한 보고서입니다.');



-- approval line sample


-- 기안일: 2025-06-10
-- 결재: 6/11(사원) → 6/12(대리) → 6/13(과장)

-- 기안자
INSERT INTO approval_line (doc_id, member_id, status, process_date)
VALUES (1, 1, '기안 상신', TO_DATE('2025-06-10', 'YYYY-MM-DD'));

-- 결재자들
INSERT INTO approval_line (doc_id, member_id, status, process_date)
VALUES (1, 7, '결재 완료', TO_DATE('2025-06-11', 'YYYY-MM-DD'));  -- 사원
INSERT INTO approval_line (doc_id, member_id, status, process_date)
VALUES (1, 5, '결재 완료', TO_DATE('2025-06-12', 'YYYY-MM-DD'));  -- 대리
INSERT INTO approval_line (doc_id, member_id, status, process_date)
VALUES (1, 10, '결재 완료', TO_DATE('2025-06-13', 'YYYY-MM-DD')); -- 과장

-- 참조자
INSERT INTO approval_line (doc_id, member_id, status, process_date)
VALUES (1, 6, '참조', TO_DATE('2025-06-10', 'YYYY-MM-DD'));

-- 결재 흐름: 사원 → 과장 → 차장 → 부장(반려)
-- 처리일: 6/11 ~ 6/14

-- 기안자
INSERT INTO approval_line (doc_id, member_id, status, process_date)
VALUES (2, 2, '기안 상신', TO_DATE('2025-06-10', 'YYYY-MM-DD'));

-- 결재 완료자
INSERT INTO approval_line (doc_id, member_id, status, process_date)
VALUES (2, 17, '결재 완료', TO_DATE('2025-06-11', 'YYYY-MM-DD')); -- 사원
INSERT INTO approval_line (doc_id, member_id, status, process_date)
VALUES (2, 20, '결재 완료', TO_DATE('2025-06-12', 'YYYY-MM-DD')); -- 과장
INSERT INTO approval_line (doc_id, member_id, status, process_date)
VALUES (2, 14, '결재 완료', TO_DATE('2025-06-13', 'YYYY-MM-DD')); -- 차장

-- 반려자
INSERT INTO approval_line (doc_id, member_id, status, process_date)
VALUES (2, 9, '반려', TO_DATE('2025-06-14', 'YYYY-MM-DD'));       -- 부장

-- 참조자
INSERT INTO approval_line (doc_id, member_id, status, process_date)
VALUES (2, 11, '참조', TO_DATE('2025-06-10', 'YYYY-MM-DD'));

-- 처리일: 6/11

-- 기안자
INSERT INTO approval_line (doc_id, member_id, status, process_date)
VALUES (3, 3, '기안 상신', TO_DATE('2025-06-10', 'YYYY-MM-DD'));

-- 결재 완료
INSERT INTO approval_line (doc_id, member_id, status, process_date)
VALUES (3, 12, '결재 완료', TO_DATE('2025-06-11', 'YYYY-MM-DD'));  -- 대리

-- 결재 예정
INSERT INTO approval_line (doc_id, member_id, status) VALUES (3, 8, '결재 예정');
INSERT INTO approval_line (doc_id, member_id, status) VALUES (3, 4, '결재 예정');

-- 참조자
INSERT INTO approval_line (doc_id, member_id, status, process_date)
VALUES (3, 13, '참조', TO_DATE('2025-06-10', 'YYYY-MM-DD'));


INSERT INTO approval_line VALUES (4, 4, '기안 상신', TO_DATE('2025-06-10','YYYY-MM-DD'));
INSERT INTO approval_line VALUES (4, 16,'결재 완료', TO_DATE('2025-06-11','YYYY-MM-DD'));
INSERT INTO approval_line VALUES (4, 14,'결재 완료', TO_DATE('2025-06-12','YYYY-MM-DD'));
INSERT INTO approval_line VALUES (4, 12,'결재 완료', TO_DATE('2025-06-13','YYYY-MM-DD'));
INSERT INTO approval_line VALUES (4, 18,'참조',        TO_DATE('2025-06-10','YYYY-MM-DD'));


INSERT INTO approval_line VALUES (5, 5, '기안 상신', TO_DATE('2025-06-10','YYYY-MM-DD'));
INSERT INTO approval_line VALUES (5,19, '결재 완료', TO_DATE('2025-06-11','YYYY-MM-DD'));
INSERT INTO approval_line VALUES (5, 8, '결재 완료', TO_DATE('2025-06-12','YYYY-MM-DD'));
INSERT INTO approval_line VALUES (5,17, '반려',       TO_DATE('2025-06-13','YYYY-MM-DD'));
INSERT INTO approval_line VALUES (5,15, '참조',       TO_DATE('2025-06-10','YYYY-MM-DD'));


INSERT INTO approval_line VALUES (6, 6, '기안 상신', TO_DATE('2025-06-10','YYYY-MM-DD'));
INSERT INTO approval_line VALUES (6, 13, '결재 완료', TO_DATE('2025-06-11','YYYY-MM-DD'));
INSERT INTO approval_line VALUES (6,2, '결재 예정', NULL);
INSERT INTO approval_line VALUES (6,18, '결재 예정', NULL);
INSERT INTO approval_line VALUES (6,11, '참조',       TO_DATE('2025-06-10','YYYY-MM-DD'));


INSERT INTO approval_line VALUES (7, 7, '기안 상신', TO_DATE('2025-06-10','YYYY-MM-DD'));
INSERT INTO approval_line VALUES (7, 3, '결재 완료', TO_DATE('2025-06-11','YYYY-MM-DD'));
INSERT INTO approval_line VALUES (7,10, '결재 완료', TO_DATE('2025-06-12','YYYY-MM-DD'));
INSERT INTO approval_line VALUES (7, 1, '결재 완료', TO_DATE('2025-06-13','YYYY-MM-DD'));
INSERT INTO approval_line VALUES (7, 5, '참조',       TO_DATE('2025-06-10','YYYY-MM-DD'));


INSERT INTO approval_line VALUES (8, 8, '기안 상신', TO_DATE('2025-06-10','YYYY-MM-DD'));
INSERT INTO approval_line VALUES (8, 4, '결재 완료', TO_DATE('2025-06-11','YYYY-MM-DD'));
INSERT INTO approval_line VALUES (8,15, '결재 완료', TO_DATE('2025-06-12','YYYY-MM-DD'));
INSERT INTO approval_line VALUES (8,12, '반려',       TO_DATE('2025-06-13','YYYY-MM-DD'));
INSERT INTO approval_line VALUES (8, 2, '참조',       TO_DATE('2025-06-10','YYYY-MM-DD'));


INSERT INTO approval_line VALUES (9, 9, '기안 상신', TO_DATE('2025-06-10','YYYY-MM-DD'));
INSERT INTO approval_line VALUES (9,17, '결재 완료', TO_DATE('2025-06-11','YYYY-MM-DD'));
INSERT INTO approval_line VALUES (9, 1, '결재 예정', NULL);
INSERT INTO approval_line VALUES (9,10, '참조',       TO_DATE('2025-06-10','YYYY-MM-DD'));


-- 문서 10: 완료
INSERT INTO approval_line VALUES (10, 10, '기안 상신', TO_DATE('2025-06-10', 'YYYY-MM-DD')); -- 기안자
INSERT INTO approval_line VALUES (10, 19, '결재 완료', TO_DATE('2025-06-11', 'YYYY-MM-DD')); -- 사원
INSERT INTO approval_line VALUES (10, 17, '결재 완료', TO_DATE('2025-06-12', 'YYYY-MM-DD'));
INSERT INTO approval_line VALUES (10, 14, '결재 완료', TO_DATE('2025-06-13', 'YYYY-MM-DD'));
INSERT INTO approval_line VALUES (10, 6, '참조', TO_DATE('2025-06-10', 'YYYY-MM-DD'));

-- 문서 11: 완료
INSERT INTO approval_line VALUES (11, 11, '기안 상신', TO_DATE('2025-06-10', 'YYYY-MM-DD'));
INSERT INTO approval_line VALUES (11, 5, '결재 완료', TO_DATE('2025-06-11', 'YYYY-MM-DD'));
INSERT INTO approval_line VALUES (11, 8, '결재 완료', TO_DATE('2025-06-12', 'YYYY-MM-DD'));
INSERT INTO approval_line VALUES (11, 20, '결재 완료', TO_DATE('2025-06-13', 'YYYY-MM-DD'));
INSERT INTO approval_line VALUES (11, 3, '참조', TO_DATE('2025-06-10', 'YYYY-MM-DD'));

-- 문서 12: 반려
INSERT INTO approval_line VALUES (12, 12, '기안 상신', TO_DATE('2025-06-10', 'YYYY-MM-DD'));
INSERT INTO approval_line VALUES (12, 7, '결재 완료', TO_DATE('2025-06-11', 'YYYY-MM-DD'));
INSERT INTO approval_line VALUES (12, 4, '결재 완료', TO_DATE('2025-06-12', 'YYYY-MM-DD'));
INSERT INTO approval_line VALUES (12, 13, '반려', TO_DATE('2025-06-13', 'YYYY-MM-DD'));
INSERT INTO approval_line VALUES (12, 1, '참조', TO_DATE('2025-06-10', 'YYYY-MM-DD'));

-- 문서 13: 반려
INSERT INTO approval_line VALUES (13, 13, '기안 상신', TO_DATE('2025-06-10', 'YYYY-MM-DD'));
INSERT INTO approval_line VALUES (13, 10, '결재 완료', TO_DATE('2025-06-11', 'YYYY-MM-DD'));
INSERT INTO approval_line VALUES (13, 2, '결재 완료', TO_DATE('2025-06-12', 'YYYY-MM-DD'));
INSERT INTO approval_line VALUES (13, 16, '반려', TO_DATE('2025-06-13', 'YYYY-MM-DD'));
INSERT INTO approval_line VALUES (13, 11, '참조', TO_DATE('2025-06-10', 'YYYY-MM-DD'));

-- 문서 14: 진행중
INSERT INTO approval_line VALUES (14, 14, '기안 상신', TO_DATE('2025-06-10', 'YYYY-MM-DD'));
INSERT INTO approval_line VALUES (14, 3, '결재 완료', TO_DATE('2025-06-11', 'YYYY-MM-DD'));
INSERT INTO approval_line VALUES (14, 12, '결재 예정', NULL);
INSERT INTO approval_line VALUES (14, 15, '결재 예정', NULL);
INSERT INTO approval_line VALUES (14, 9, '참조', TO_DATE('2025-06-10', 'YYYY-MM-DD'));

-- 문서 15: 진행중
INSERT INTO approval_line VALUES (15, 15, '기안 상신', TO_DATE('2025-06-10', 'YYYY-MM-DD'));
INSERT INTO approval_line VALUES (15, 7, '결재 완료', TO_DATE('2025-06-11', 'YYYY-MM-DD'));
INSERT INTO approval_line VALUES (15, 6, '결재 예정', NULL);
INSERT INTO approval_line VALUES (15, 18, '결재 예정', NULL);
INSERT INTO approval_line VALUES (15, 4, '참조', TO_DATE('2025-06-10', 'YYYY-MM-DD'));

-- 문서 16: 진행중
INSERT INTO approval_line VALUES (16, 16, '기안 상신', TO_DATE('2025-06-10', 'YYYY-MM-DD'));
INSERT INTO approval_line VALUES (16, 5, '결재 완료', TO_DATE('2025-06-11', 'YYYY-MM-DD'));
INSERT INTO approval_line VALUES (16, 8, '결재 예정', NULL);
INSERT INTO approval_line VALUES (16, 17, '결재 예정', NULL);
INSERT INTO approval_line VALUES (16, 2, '참조', TO_DATE('2025-06-10', 'YYYY-MM-DD'));

-- 문서 17: 진행중
INSERT INTO approval_line VALUES (17, 17, '기안 상신', TO_DATE('2025-06-10', 'YYYY-MM-DD'));
INSERT INTO approval_line VALUES (17, 1, '결재 완료', TO_DATE('2025-06-11', 'YYYY-MM-DD'));
INSERT INTO approval_line VALUES (17, 9, '결재 예정', NULL);
INSERT INTO approval_line VALUES (17, 13, '결재 예정', NULL);
INSERT INTO approval_line VALUES (17, 20, '참조', TO_DATE('2025-06-10', 'YYYY-MM-DD'));

-- 문서 18: 진행중
INSERT INTO approval_line VALUES (18, 18, '기안 상신', TO_DATE('2025-06-10', 'YYYY-MM-DD'));
INSERT INTO approval_line VALUES (18, 14, '결재 완료', TO_DATE('2025-06-11', 'YYYY-MM-DD'));
INSERT INTO approval_line VALUES (18, 12, '결재 예정', NULL);
INSERT INTO approval_line VALUES (18, 15, '결재 예정', NULL);
INSERT INTO approval_line VALUES (18, 7, '참조', TO_DATE('2025-06-10', 'YYYY-MM-DD'));

-- 문서 19: 진행중
INSERT INTO approval_line VALUES (19, 19, '기안 상신', TO_DATE('2025-06-10', 'YYYY-MM-DD'));
INSERT INTO approval_line VALUES (19, 3, '결재 완료', TO_DATE('2025-06-11', 'YYYY-MM-DD'));
INSERT INTO approval_line VALUES (19, 10, '결재 예정', NULL);
INSERT INTO approval_line VALUES (19, 16, '결재 예정', NULL);
INSERT INTO approval_line VALUES (19, 5, '참조', TO_DATE('2025-06-10', 'YYYY-MM-DD'));

-- 문서 20: 진행중
INSERT INTO approval_line VALUES (20, 20, '기안 상신', TO_DATE('2025-06-10', 'YYYY-MM-DD'));
INSERT INTO approval_line VALUES (20, 6, '결재 완료', TO_DATE('2025-06-11', 'YYYY-MM-DD'));
INSERT INTO approval_line VALUES (20, 11, '결재 예정', NULL);
INSERT INTO approval_line VALUES (20, 18, '결재 예정', NULL);
INSERT INTO approval_line VALUES (20, 1, '참조', TO_DATE('2025-06-10', 'YYYY-MM-DD'));

