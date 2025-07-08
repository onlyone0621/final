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
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;" id="writeDate"></td>
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
    <div id="approvalLineContainer" style="display: flex; gap: 10px;">
    </div>
  </div>
  <table style="width: 100%; border-collapse: collapse; margin-bottom: 10px; table-layout: fixed;">
  <colgroup>
    <col style="width: 12.5%;">   
    <col style="width: 87.5%;">   
  </colgroup>
  <tr>
    <th style="border: 1px solid #000; padding: 8px; background-color: #ccc; text-align: left;">제목</th>
    <td style="border: 1px solid #000; padding: 8px;">
      <input type="text" id="title" required style="width: 100%; box-sizing: border-box;">
    </td>
  </tr>
  <tr>
    <th colspan="2" style="border: 1px solid #000; padding: 8px; background-color: #ccc; text-align: center;">
      상 세 내 용
    </th>
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
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;" id="writeDate"></td>
      </tr>
      <tr>
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;">기안자</td>
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;" id="writer"></td>
      </tr>
    </table>
    <div id="approvalLineContainer" style="display: flex; gap: 10px;">
    </div>
  </div>
  <table style="width: 100%; border-collapse: collapse; margin-bottom: 10px; table-layout: fixed;">
  <colgroup>
    <col style="width: 12.5%;">   
    <col style="width: 87.5%;">   
  </colgroup>
  <tr>
    <th style="border: 1px solid #000; padding: 5px; background-color: #ccc;">제목</th>
    <td style="border: 1px solid #000; padding: 5px;">
      <input type="text" id="title" required style="width: 100%; box-sizing: border-box;">
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
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;" id="writeDate"></td>
      </tr>
      <tr>
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;">기안자</td>
        <td style="border: 1px solid #000; padding: 5px; font-size: 14px;" id="writer"></td>
      </tr>
    </table>
    <div id="approvalLineContainer" style="display: flex; gap: 10px;">
    </div>
  </div>
  <table style="border-collapse: collapse; width: 100%; margin-bottom: 10px; table-layout: fixed;">
  <colgroup>
    <col style="width: 12.5%;">  
    <col style="width: 87.5%;">  
  </colgroup>
  <tr>
    <th style="border: 1px solid #000; padding: 8px; background-color: #ccc; text-align: center;">
      제목
    </th>
    <td style="border: 1px solid #000; padding: 8px;">
      <input type="text" id="title" required style="width: 100%; box-sizing: border-box;">
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


-- Message sample insert queries
-- 원글 1 (2025-06-20)
INSERT INTO message (id, title, content, receiver_id, sender_id, write_date, is_read, file_name, ref, lev)
VALUES (
  sq_message_id.NEXTVAL,
  '첫 번째 메시지',
  '이것은 첫 번째 원글입니다.',
  2, 1,
  TO_DATE('2025-06-20', 'YYYY-MM-DD'),
  '안 읽음',
  NULL,
  sq_message_ref.NEXTVAL,
  0
);

-- 답글 1-1 (2025-06-21)
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  'RE: 첫 번째 메시지',
  '답변드립니다.',
  1, 2,
  TO_DATE('2025-06-21', 'YYYY-MM-DD'),
  '읽음',
  NULL,
  1,
  1
);

-- 답글 1-2 (2025-06-22)
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  'RE: RE: 첫 번째 메시지',
  '추가 답변입니다.',
  2, 1,
  TO_DATE('2025-06-22', 'YYYY-MM-DD'),
  '읽음',
  NULL,
  1,
  2
);

-- 원글 2 (2025-06-23)
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  '두 번째 메시지',
  '다른 원글입니다.',
  3, 4,
  TO_DATE('2025-06-23', 'YYYY-MM-DD'),
  '안 읽음',
  NULL,
  sq_message_ref.NEXTVAL,
  0
);

-- 답글 2-1 (2025-06-24)
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  'RE: 두 번째 메시지',
  '네 확인했습니다.',
  4, 3,
  TO_DATE('2025-06-24', 'YYYY-MM-DD'),
  '읽음',
  NULL,
  2,
  1
);

-- 원글 3 (2025-06-25)
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  '세 번째 메시지',
  '추가로 문의드립니다.',
  5, 6,
  TO_DATE('2025-06-25', 'YYYY-MM-DD'),
  '안 읽음',
  NULL,
  sq_message_ref.NEXTVAL,
  0
);

-- 원글 4 (2025-06-26)
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  '네 번째 메시지',
  '공지사항입니다.',
  7, 8,
  TO_DATE('2025-06-26', 'YYYY-MM-DD'),
  '안 읽음',
  NULL,
  sq_message_ref.NEXTVAL,
  0
);

-- 원글 5 (2025-06-27)
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  '다섯 번째 메시지',
  '업무 협조 부탁드립니다.',
  9, 10,
  TO_DATE('2025-06-27', 'YYYY-MM-DD'),
  '읽음',
  NULL,
  sq_message_ref.NEXTVAL,
  0
);

-- 답글 5-1 (2025-06-28)
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  'RE: 다섯 번째 메시지',
  '확인했습니다.',
  10, 9,
  TO_DATE('2025-06-28', 'YYYY-MM-DD'),
  '읽음',
  NULL,
  5,
  1
);

-- 답글 5-2 (2025-06-28)
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  'RE: RE: 다섯 번째 메시지',
  '감사합니다.',
  9, 10,
  TO_DATE('2025-06-28', 'YYYY-MM-DD'),
  '읽음',
  NULL,
  5,
  2
);

-- 원글 6 (2025-06-19)
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  '여섯 번째 메시지',
  '새로운 공지사항입니다.',
  11, 12,
  TO_DATE('2025-06-19', 'YYYY-MM-DD'),
  '안 읽음',
  NULL,
  sq_message_ref.NEXTVAL,
  0
);

-- 답글 6-1 (2025-06-18)
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  'RE: 여섯 번째 메시지',
  '확인했습니다.',
  12, 11,
  TO_DATE('2025-06-18', 'YYYY-MM-DD'),
  '읽음',
  NULL,
  6,
  1
);

-- 원글 7 (2025-06-17)
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  '일곱 번째 메시지',
  '자료 요청드립니다.',
  13, 14,
  TO_DATE('2025-06-17', 'YYYY-MM-DD'),
  '안 읽음',
  NULL,
  sq_message_ref.NEXTVAL,
  0
);

-- 답글 7-1 (2025-06-16)
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  'RE: 일곱 번째 메시지',
  '자료 전달드립니다.',
  14, 13,
  TO_DATE('2025-06-16', 'YYYY-MM-DD'),
  '읽음',
  NULL,
  7,
  1
);

-- 원글 8 (2025-06-15)
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  '여덟 번째 메시지',
  '긴급 상황 보고합니다.',
  15, 16,
  TO_DATE('2025-06-15', 'YYYY-MM-DD'),
  '안 읽음',
  NULL,
  sq_message_ref.NEXTVAL,
  0
);

-- 원글 9 (2025-06-14)
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  '아홉 번째 메시지',
  '프로젝트 상황 공유드립니다.',
  17, 18,
  TO_DATE('2025-06-14', 'YYYY-MM-DD'),
  '안 읽음',
  NULL,
  sq_message_ref.NEXTVAL,
  0
);

-- 답글 9-1 (2025-06-13)
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  'RE: 아홉 번째 메시지',
  '공유 감사합니다.',
  18, 17,
  TO_DATE('2025-06-13', 'YYYY-MM-DD'),
  '읽음',
  NULL,
  9,
  1
);

-- 원글 10 (2025-06-12)
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  '열 번째 메시지',
  '휴가 일정 문의드립니다.',
  19, 20,
  TO_DATE('2025-06-12', 'YYYY-MM-DD'),
  '안 읽음',
  NULL,
  sq_message_ref.NEXTVAL,
  0
);

-- 답글 10-1 (2025-06-11)
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  'RE: 열 번째 메시지',
  '승인해드리겠습니다.',
  20, 19,
  TO_DATE('2025-06-11', 'YYYY-MM-DD'),
  '읽음',
  NULL,
  10,
  1
);

-- 답글 10-2 (2025-06-10)
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  'RE: RE: 열 번째 메시지',
  '감사합니다!',
  19, 20,
  TO_DATE('2025-06-10', 'YYYY-MM-DD'),
  '읽음',
  NULL,
  10,
  2
);

-- 원글 11 (2025-06-29)
INSERT INTO message (id, title, content, receiver_id, sender_id, write_date, is_read, file_name, ref, lev)
VALUES (
  sq_message_id.NEXTVAL,
  '회의 일정 확인 요청',
  '7월 초 회의 일정 확인 부탁드립니다.',
  2, 1,
  TO_DATE('2025-06-29', 'YYYY-MM-DD'),
  '안 읽음',
  NULL,
  sq_message_ref.NEXTVAL,
  0
);

-- 답글 11-1 (2025-06-30)
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  'RE: 회의 일정 확인 요청',
  '7월 3일 오전으로 괜찮을까요?',
  1, 2,
  TO_DATE('2025-06-30', 'YYYY-MM-DD'),
  '읽음',
  NULL,
  11,
  1
);

-- 답글 11-2 (2025-07-01)
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  'RE: RE: 회의 일정 확인 요청',
  '네 그날 오전 10시로 하겠습니다.',
  2, 1,
  TO_DATE('2025-07-01', 'YYYY-MM-DD'),
  '읽음',
  NULL,
  11,
  2
);

-- 원글 12 (2025-06-28)
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  '업무 분장 관련 문의',
  '담당 업무 관련해서 조정이 필요한 것 같습니다.',
  3, 1,
  TO_DATE('2025-06-28', 'YYYY-MM-DD'),
  '안 읽음',
  NULL,
  sq_message_ref.NEXTVAL,
  0
);

-- 답글 12-1 (2025-06-29)
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  'RE: 업무 분장 관련 문의',
  '어떤 부분이 조정이 필요한지요?',
  1, 3,
  TO_DATE('2025-06-29', 'YYYY-MM-DD'),
  '읽음',
  NULL,
  12,
  1
);

-- 답글 12-2 (2025-06-30)
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  'RE: RE: 업무 분장 관련 문의',
  '2팀의 업무 범위와 중복되는 부분이 있습니다.',
  3, 1,
  TO_DATE('2025-06-30', 'YYYY-MM-DD'),
  '읽음',
  NULL,
  12,
  2
);

-- 원글 13 (2025-06-27)
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  '근무표 수정 요청',
  '7월 첫째주 근무표에 수정 요청드립니다.',
  1, 2,
  TO_DATE('2025-06-27', 'YYYY-MM-DD'),
  '안 읽음',
  NULL,
  sq_message_ref.NEXTVAL,
  0
);

-- 답글 13-1 (2025-06-28)
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  'RE: 근무표 수정 요청',
  '어떤 부분을 수정해드리면 될까요?',
  2, 1,
  TO_DATE('2025-06-28', 'YYYY-MM-DD'),
  '읽음',
  NULL,
  13,
  1
);

-- 답글 13-2 (2025-06-29)
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  'RE: RE: 근무표 수정 요청',
  '수정 요청 내용은 메일로 드렸습니다.',
  1, 2,
  TO_DATE('2025-06-29', 'YYYY-MM-DD'),
  '읽음',
  NULL,
  13,
  2
);

-- 원글 14
INSERT INTO message (
  id, title, content, receiver_id, sender_id, write_date,
  is_read, file_name, ref, lev
)
VALUES (
  sq_message_id.NEXTVAL,
  '회의자료 요청',
  '금요일 회의자료 공유 부탁드립니다.',
  2, 1,
  TO_DATE('2025-06-30', 'YYYY-MM-DD'),
  '안 읽음',
  NULL,
  sq_message_ref.NEXTVAL,
  0
);

-- 답글 14-1
INSERT INTO message (
  id, title, content, receiver_id, sender_id, write_date,
  is_read, file_name, ref, lev
)
VALUES (
  sq_message_id.NEXTVAL,
  'RE: 회의자료 요청',
  '자료 금일 오후까지 전달드리겠습니다.',
  1, 2,
  TO_DATE('2025-07-01', 'YYYY-MM-DD'),
  '읽음',
  NULL,
  14,
  1
);

-- 답글 14-2
INSERT INTO message (
  id, title, content, receiver_id, sender_id, write_date,
  is_read, file_name, ref, lev
)
VALUES (
  sq_message_id.NEXTVAL,
  'RE: RE: 회의자료 요청',
  '확인했습니다. 감사합니다.',
  2, 1,
  TO_DATE('2025-07-02', 'YYYY-MM-DD'),
  '읽음',
  NULL,
  14,
  2
);


-- 원글 15
INSERT INTO message (
  id, title, content, receiver_id, sender_id, write_date,
  is_read, file_name, ref, lev
)
VALUES (
  sq_message_id.NEXTVAL,
  '출장 보고서 제출 요청',
  '출장 후 보고서 제출 부탁드립니다.',
  3, 1,
  TO_DATE('2025-07-02', 'YYYY-MM-DD'),
  '안 읽음',
  NULL,
  sq_message_ref.NEXTVAL,
  0
);

-- 답글 15-1
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  'RE: 출장 보고서 제출 요청',
  '내일 오전까지 제출하겠습니다.',
  1, 3,
  TO_DATE('2025-07-03', 'YYYY-MM-DD'),
  '읽음',
  NULL,
  15,
  1
);


-- 원글 16
INSERT INTO message (
  id, title, content, receiver_id, sender_id, write_date,
  is_read, file_name, ref, lev
)
VALUES (
  sq_message_id.NEXTVAL,
  '서버 점검 일정 안내',
  '서버 점검이 7월 5일 01시부터 예정되어 있습니다.',
  1, 3,
  TO_DATE('2025-07-01', 'YYYY-MM-DD'),
  '안 읽음',
  NULL,
  sq_message_ref.NEXTVAL,
  0
);

-- 답글 16-1
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  'RE: 서버 점검 일정 안내',
  '알겠습니다. 점검 전 백업 완료하겠습니다.',
  3, 1,
  TO_DATE('2025-07-01', 'YYYY-MM-DD'),
  '읽음',
  NULL,
  16,
  1
);

-- 답글 16-2
INSERT INTO message VALUES (
  sq_message_id.NEXTVAL,
  'RE: RE: 서버 점검 일정 안내',
  '감사합니다.',
  1, 3,
  TO_DATE('2025-07-02', 'YYYY-MM-DD'),
  '읽음',
  NULL,
  16,
  2
);




-- Chatroom
INSERT INTO chatroom (id, name, description, create_date, type)
VALUES (sq_chatroom_id.NEXTVAL, '1:1 Chat A', '유저 1과 2의 개인 채팅', TO_DATE('2025-05-05', 'YYYY-MM-DD'), 'private');

INSERT INTO chatroom (id, name, description, create_date, type)
VALUES (sq_chatroom_id.NEXTVAL, '1:1 Chat B', '유저 3과 4의 개인 채팅', TO_DATE('2025-05-20', 'YYYY-MM-DD'), 'private');

INSERT INTO chatroom (id, name, description, create_date, type)
VALUES (sq_chatroom_id.NEXTVAL, 'Team Chat', '팀 단체 채팅방', TO_DATE('2025-06-10', 'YYYY-MM-DD'), 'group');


-- Chatroom_user
-- Chatroom 1 (private): user 1, 2
INSERT INTO chatroom_user (chatroom_id, member_id, join_date)
VALUES (1, 1, TO_DATE('2025-05-05', 'YYYY-MM-DD'));
INSERT INTO chatroom_user (chatroom_id, member_id, join_date)
VALUES (1, 2, TO_DATE('2025-05-05', 'YYYY-MM-DD'));

-- Chatroom 2 (private): user 3, 4
INSERT INTO chatroom_user (chatroom_id, member_id, join_date)
VALUES (2, 3, TO_DATE('2025-05-20', 'YYYY-MM-DD'));
INSERT INTO chatroom_user (chatroom_id, member_id, join_date)
VALUES (2, 4, TO_DATE('2025-05-20', 'YYYY-MM-DD'));

-- Chatroom 3 (group): user 5, 6, 7
INSERT INTO chatroom_user (chatroom_id, member_id, join_date)
VALUES (3, 5, TO_DATE('2025-06-10', 'YYYY-MM-DD'));
INSERT INTO chatroom_user (chatroom_id, member_id, join_date)
VALUES (3, 6, TO_DATE('2025-06-10', 'YYYY-MM-DD'));
INSERT INTO chatroom_user (chatroom_id, member_id, join_date)
VALUES (3, 1, TO_DATE('2025-06-10', 'YYYY-MM-DD'));

-- Chat_message
-- Chatroom 1
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 1, 1, TO_DATE('2025-05-05 10:00', 'YYYY-MM-DD HH24:MI'), '홍길동님이 입장하였습니다.', 'system');

INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 1, 2, TO_DATE('2025-05-05 10:01', 'YYYY-MM-DD HH24:MI'), '김철수님이 입장하였습니다.', 'system');

INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 1, 1, TO_DATE('2025-05-05 10:02', 'YYYY-MM-DD HH24:MI'), '안녕하세요.', 'user');

INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 1, 2, TO_DATE('2025-05-05 10:03', 'YYYY-MM-DD HH24:MI'), '안녕하세요~ 반갑습니다.', 'user');

-- user 1 (홍길동)
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 1, 1, TO_DATE('2025-05-05 10:04', 'YYYY-MM-DD HH24:MI'), '오늘 회의자료 준비하셨나요?', 'user');

-- user 2 (김철수)
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 1, 2, TO_DATE('2025-05-05 10:05', 'YYYY-MM-DD HH24:MI'), '네, 어제 밤에 마무리했습니다.', 'user');

-- user 1 (홍길동)
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 1, 1, TO_DATE('2025-05-05 10:07', 'YYYY-MM-DD HH24:MI'), '완료된 버전 파일 보내주세요.', 'user');

-- user 2 (김철수)
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 1, 2, TO_DATE('2025-05-05 10:08', 'YYYY-MM-DD HH24:MI'), '메일로 보냈습니다. 확인해보세요.', 'user');

-- user 1 (홍길동)
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 1, 1, TO_DATE('2025-05-05 10:10', 'YYYY-MM-DD HH24:MI'), '네, 잘 받았습니다. 수고하셨어요.', 'user');

-- user 2 (김철수)
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 1, 2, TO_DATE('2025-05-05 10:12', 'YYYY-MM-DD HH24:MI'), '감사합니다. 회의는 예정대로 2시죠?', 'user');

-- user 1 (홍길동)
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 1, 1, TO_DATE('2025-05-05 10:15', 'YYYY-MM-DD HH24:MI'), '네, 2시에 회의실 A입니다.', 'user');

-- user 2 (김철수)
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 1, 2, TO_DATE('2025-05-05 10:16', 'YYYY-MM-DD HH24:MI'), '회의자료에 조직도도 추가했어요.', 'user');

-- user 1 (홍길동)
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 1, 1, TO_DATE('2025-05-05 10:17', 'YYYY-MM-DD HH24:MI'), '좋아요. 발표는 제가 하고, 김 대리는 뒷부분 정리 부탁드릴게요.', 'user');

-- user 2 (김철수)
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 1, 2, TO_DATE('2025-05-05 10:19', 'YYYY-MM-DD HH24:MI'), '네 알겠습니다. 시간 배분은 어떻게 할까요?', 'user');

-- user 1 (홍길동)
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 1, 1, TO_DATE('2025-05-05 10:21', 'YYYY-MM-DD HH24:MI'), '저 15분, 김 대리 10분 정도로 하면 될 것 같아요.', 'user');

-- user 2 (김철수)
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 1, 2, TO_DATE('2025-05-05 10:22', 'YYYY-MM-DD HH24:MI'), '네 그렇게 준비하겠습니다.', 'user');

-- user 1 (홍길동)
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 1, 1, TO_DATE('2025-05-05 10:24', 'YYYY-MM-DD HH24:MI'), '그리고 발표 끝나고 Q&A는 5분만 잡아두죠.', 'user');

-- user 2 (김철수)
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 1, 2, TO_DATE('2025-05-05 10:25', 'YYYY-MM-DD HH24:MI'), '네, 발표 자료에 Q&A 슬라이드도 추가해둘게요.', 'user');

-- user 1 (홍길동)
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 1, 1, TO_DATE('2025-05-05 10:27', 'YYYY-MM-DD HH24:MI'), '좋습니다. 준비 완료되면 메시지 주세요.', 'user');

-- user 2 (김철수)
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 1, 2, TO_DATE('2025-05-05 10:28', 'YYYY-MM-DD HH24:MI'), '조금 뒤에 최종본 보내드릴게요.', 'user');

-- user 1 (홍길동)
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 1, 1, TO_DATE('2025-05-05 10:30', 'YYYY-MM-DD HH24:MI'), '넵! 감사합니다~', 'user');


-- Chatroom 2
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 2, 3, TO_DATE('2025-05-20 09:00', 'YYYY-MM-DD HH24:MI'), '이영희님이 입장하였습니다.', 'system');

INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 2, 4, TO_DATE('2025-05-20 09:01', 'YYYY-MM-DD HH24:MI'), '박민수님이 입장하였습니다.', 'system');

INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 2, 3, TO_DATE('2025-05-20 09:02', 'YYYY-MM-DD HH24:MI'), '자료 전달드릴게요.', 'user');

INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 2, 4, TO_DATE('2025-05-20 09:03', 'YYYY-MM-DD HH24:MI'), '확인했습니다. 감사합니다.', 'user');

-- user 3 (이영희)
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 2, 3, TO_DATE('2025-05-20 09:04', 'YYYY-MM-DD HH24:MI'), '3페이지 도표가 조금 흐릿하게 보이네요.', 'user');

-- user 4 (박민수)
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 2, 4, TO_DATE('2025-05-20 09:05', 'YYYY-MM-DD HH24:MI'), '아, 다시 캡처해서 교체할게요.', 'user');

-- user 3 (이영희)
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 2, 3, TO_DATE('2025-05-20 09:07', 'YYYY-MM-DD HH24:MI'), '감사합니다. 나머지는 잘 정리되어 있어요.', 'user');

-- user 4 (박민수)
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 2, 4, TO_DATE('2025-05-20 09:08', 'YYYY-MM-DD HH24:MI'), '오늘 오전 중으로 최종본 보내드릴게요.', 'user');

-- user 3 (이영희)
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 2, 3, TO_DATE('2025-05-20 09:10', 'YYYY-MM-DD HH24:MI'), '넵. 회의는 오후 3시죠?', 'user');

-- user 4 (박민수)
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 2, 4, TO_DATE('2025-05-20 09:11', 'YYYY-MM-DD HH24:MI'), '맞아요. 회의실 B에서 진행됩니다.', 'user');

-- user 3 (이영희)
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 2, 3, TO_DATE('2025-05-20 09:13', 'YYYY-MM-DD HH24:MI'), 'PPT에 타이틀 애니메이션도 하나 넣어주세요.', 'user');

-- user 4 (박민수)
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 2, 4, TO_DATE('2025-05-20 09:15', 'YYYY-MM-DD HH24:MI'), '넵 추가했습니다. 바로 보내드릴게요.', 'user');

-- user 3 (이영희)
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 2, 3, TO_DATE('2025-05-20 09:17', 'YYYY-MM-DD HH24:MI'), '좋아요~ 수고 많으셨어요!', 'user');

-- user 4 (박민수)
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 2, 4, TO_DATE('2025-05-20 09:20', 'YYYY-MM-DD HH24:MI'), '감사합니다. 오후에 뵐게요!', 'user');



-- Chatroom 3 (group)
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 3, 5, TO_DATE('2025-06-10 14:00', 'YYYY-MM-DD HH24:MI'), '최수정님이 입장하였습니다.', 'system');

INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 3, 6, TO_DATE('2025-06-10 14:01', 'YYYY-MM-DD HH24:MI'), '조현우님이 입장하였습니다.', 'system');

INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 3, 1, TO_DATE('2025-06-10 14:02', 'YYYY-MM-DD HH24:MI'), '홍길동님이 입장하였습니다.', 'system');

INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 3, 5, TO_DATE('2025-06-10 14:05', 'YYYY-MM-DD HH24:MI'), '다들 접속하셨나요?', 'user');

INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 3, 6, TO_DATE('2025-06-10 14:06', 'YYYY-MM-DD HH24:MI'), '네 접속했습니다.', 'user');

INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 3, 1, TO_DATE('2025-06-10 14:07', 'YYYY-MM-DD HH24:MI'), '회의 시작하겠습니다.', 'user');

-- 홍길동
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 3, 1, TO_DATE('2025-06-10 14:08', 'YYYY-MM-DD HH24:MI'), '먼저 디자인 검토 내용부터 말씀드릴게요.', 'user');

-- 최수정
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 3, 5, TO_DATE('2025-06-10 14:09', 'YYYY-MM-DD HH24:MI'), '네, 화면 시안은 메일로 공유받았습니다.', 'user');

-- 조현우
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 3, 6, TO_DATE('2025-06-10 14:10', 'YYYY-MM-DD HH24:MI'), '전체적으로 깔끔하고 구조도 잘 잡혔어요.', 'user');

-- 홍길동
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 3, 1, TO_DATE('2025-06-10 14:11', 'YYYY-MM-DD HH24:MI'), '감사합니다. 컬러톤은 기존 가이드 유지했습니다.', 'user');

-- 조현우
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 3, 6, TO_DATE('2025-06-10 14:13', 'YYYY-MM-DD HH24:MI'), '기능 구현은 다음주 화요일까지 가능할 것 같아요.', 'user');

-- 최수정
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 3, 5, TO_DATE('2025-06-10 14:15', 'YYYY-MM-DD HH24:MI'), '일정 여유 있어서 다행이네요. QA는 그 후 바로 시작하죠.', 'user');

-- 홍길동
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 3, 1, TO_DATE('2025-06-10 14:17', 'YYYY-MM-DD HH24:MI'), '네, QA 체크리스트도 제가 정리해둘게요.', 'user');

-- 조현우
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 3, 6, TO_DATE('2025-06-10 14:18', 'YYYY-MM-DD HH24:MI'), '저는 오늘 중으로 개발 서버 반영해둘게요.', 'user');

-- 최수정
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 3, 5, TO_DATE('2025-06-10 14:20', 'YYYY-MM-DD HH24:MI'), '좋습니다. 회의 정리해서 메일로 공유드릴게요!', 'user');

-- 조현우
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 3, 6, TO_DATE('2025-06-10 14:21', 'YYYY-MM-DD HH24:MI'), '내일 예정된 회의 안건도 미리 준비해두겠습니다.', 'user');

-- 홍길동
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 3, 1, TO_DATE('2025-06-10 14:23', 'YYYY-MM-DD HH24:MI'), '좋아요. 다음 주 일정과 관련된 자료도 공유 부탁해요.', 'user');

-- 최수정
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 3, 5, TO_DATE('2025-06-10 14:25', 'YYYY-MM-DD HH24:MI'), '그럼 저는 QA 테스트 환경 세팅을 마무리하겠습니다.', 'user');

-- 조현우
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 3, 6, TO_DATE('2025-06-10 14:26', 'YYYY-MM-DD HH24:MI'), '네, 테스트 후 버그는 바로 공유할게요.', 'user');

-- 홍길동
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 3, 1, TO_DATE('2025-06-10 14:28', 'YYYY-MM-DD HH24:MI'), '피드백은 구체적으로 남겨주시면 처리하기 편합니다.', 'user');

-- 최수정
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 3, 5, TO_DATE('2025-06-10 14:30', 'YYYY-MM-DD HH24:MI'), '네, 체크리스트에 반영해서 관리하겠습니다.', 'user');

-- 조현우
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 3, 6, TO_DATE('2025-06-10 14:32', 'YYYY-MM-DD HH24:MI'), '다음 릴리즈 일정도 다시 공유드릴게요.', 'user');

-- 홍길동
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 3, 1, TO_DATE('2025-06-10 14:33', 'YYYY-MM-DD HH24:MI'), '모두 수고 많으셨습니다. 좋은 결과 있길 바랍니다.', 'user');

-- 최수정
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 3, 5, TO_DATE('2025-06-10 14:34', 'YYYY-MM-DD HH24:MI'), '네, 수고 많으셨어요!', 'user');

-- 조현우
INSERT INTO chat_message (id, chatroom_id, member_id, write_date, content, type)
VALUES (sq_chat_message_id.NEXTVAL, 3, 6, TO_DATE('2025-06-10 14:35', 'YYYY-MM-DD HH24:MI'), '내일 뵙겠습니다.', 'user');


-- Groups
-- 홍길동 (id = 1)이 만든 개인 그룹과 부서 그룹
-- 개인 그룹
INSERT INTO groups (id, member_id, name, division) VALUES (sq_groups_id.NEXTVAL, 1, '가족', 0);       -- ID: 1
INSERT INTO groups (id, member_id, name, division) VALUES (sq_groups_id.NEXTVAL, 1, '지인', 0);       -- ID: 2

-- 부서 그룹
INSERT INTO groups (id, member_id, name, division) VALUES (sq_groups_id.NEXTVAL, 1, '개발부', 1);     -- ID: 3
INSERT INTO groups (id, member_id, name, division) VALUES (sq_groups_id.NEXTVAL, 1, '디자인팀', 1);   -- ID: 4


-- Addr

--가족
-- 김정화 (어머니)
INSERT INTO addr (id, member_id, name, nickname, company, dept, grade, tel, companytel, email, description, create_date)
VALUES (sq_addr_id.NEXTVAL, 1, '김정화', '엄마', NULL, NULL, NULL, '010-1111-2222', NULL, 'mom@email.com', '어머니', TO_DATE('2025-05-01', 'YYYY-MM-DD'));

-- 이영호 (아버지)
INSERT INTO addr VALUES (sq_addr_id.NEXTVAL, 1, '이영호', '아빠', NULL, NULL, NULL, '010-1111-3333', NULL, 'dad@email.com', '아버지', TO_DATE('2025-05-02', 'YYYY-MM-DD'));

-- 장은비 (누나)
INSERT INTO addr VALUES (sq_addr_id.NEXTVAL, 1, '장은비', '은비누나', NULL, NULL, NULL, '010-1111-4444', NULL, 'sister@email.com', '누나', TO_DATE('2025-05-03', 'YYYY-MM-DD'));

--지인
INSERT INTO addr VALUES (sq_addr_id.NEXTVAL, 1, '박진우', '진우형', NULL, NULL, NULL, '010-2222-1111', NULL, 'jinwoo@email.com', '고등학교 친구', TO_DATE('2025-05-04', 'YYYY-MM-DD'));
INSERT INTO addr VALUES (sq_addr_id.NEXTVAL, 1, '최성훈', NULL, NULL, NULL, NULL, '010-2222-2222', NULL, 'choi@email.com', '대학교 선배', TO_DATE('2025-05-05', 'YYYY-MM-DD'));
INSERT INTO addr VALUES (sq_addr_id.NEXTVAL, 1, '김지은', NULL, NULL, NULL, NULL, '010-2222-3333', NULL, 'jieun@email.com', '스터디 멤버', TO_DATE('2025-05-06', 'YYYY-MM-DD'));

-- 개발부
INSERT INTO addr VALUES (sq_addr_id.NEXTVAL, 1, '서지훈', NULL, '네이버', '개발부', '사원', '010-3333-1111', '02-111-1111', 'jihun@naver.com', '백엔드 담당', TO_DATE('2025-05-07', 'YYYY-MM-DD'));
INSERT INTO addr VALUES (sq_addr_id.NEXTVAL, 1, '장예린', NULL, '네이버', '개발부', '대리', '010-3333-2222', '02-222-2222', 'yerin@naver.com', '프론트엔드', TO_DATE('2025-05-08', 'YYYY-MM-DD'));
INSERT INTO addr VALUES (sq_addr_id.NEXTVAL, 1, '안도현', NULL, '네이버', '개발부', '과장', '010-3333-3333', '02-333-3333', 'dohyun@naver.com', 'PM', TO_DATE('2025-05-09', 'YYYY-MM-DD'));

-- 디자인 팀
INSERT INTO addr VALUES (sq_addr_id.NEXTVAL, 1, '이수빈', NULL, '라인', '디자인팀', '과장', '010-4444-1111', '031-111-1111', 'soobin@line.com', 'UI 디자이너', TO_DATE('2025-05-10', 'YYYY-MM-DD'));
INSERT INTO addr VALUES (sq_addr_id.NEXTVAL, 1, '정윤호', NULL, '라인', '디자인팀', '대리', '010-4444-2222', '031-222-2222', 'yunho@line.com', 'UX 디자이너', TO_DATE('2025-05-11', 'YYYY-MM-DD'));
INSERT INTO addr VALUES (sq_addr_id.NEXTVAL, 1, '하유진', NULL, '라인', '디자인팀', '사원', '010-4444-3333', '031-333-3333', 'yujin@line.com', '그래픽 디자이너', TO_DATE('2025-05-12', 'YYYY-MM-DD'));



-- addr_group
-- addr_id와 groups_id는 시퀀스로 생성된 실제 ID에 따라 매칭해야 합니다.
INSERT INTO addr_group VALUES (1, 1);
INSERT INTO addr_group VALUES (2, 1);
INSERT INTO addr_group VALUES (3, 1);

INSERT INTO addr_group VALUES (4, 2);
INSERT INTO addr_group VALUES (5, 2);
INSERT INTO addr_group VALUES (6, 2);

INSERT INTO addr_group VALUES (7, 3);
INSERT INTO addr_group VALUES (8, 3);
INSERT INTO addr_group VALUES (9, 3);

INSERT INTO addr_group VALUES (10, 4);
INSERT INTO addr_group VALUES (11, 4);
INSERT INTO addr_group VALUES (12, 4);


-- calendar
-- 홍길동: 워크숍 참석 (종일)
INSERT INTO calendar (id, member_id, dept_id, title, content, start_time, end_time, create_date, allday)
VALUES (
  sq_calendar_id.NEXTVAL, 1, NULL, '워크숍 참석', '전사 워크숍에 참석합니다.',
  TO_DATE('2025-07-15', 'YYYY-MM-DD'), TO_DATE('2025-07-15', 'YYYY-MM-DD'), SYSDATE, 0
);

-- 김철수: 프로젝트 회의 (시간 지정)
INSERT INTO calendar (id, member_id, dept_id, title, content, start_time, end_time, create_date, allday)
VALUES (
  sq_calendar_id.NEXTVAL, 2, NULL, '프로젝트 회의', '개발팀과 주간 회의 진행',
  TO_DATE('2025-07-16 10:00', 'YYYY-MM-DD HH24:MI'), TO_DATE('2025-07-16 11:00', 'YYYY-MM-DD HH24:MI'), SYSDATE, 1
);

-- 이영희: 외근 일정 (시간 지정)
INSERT INTO calendar (id, member_id, dept_id, title, content, start_time, end_time, create_date, allday)
VALUES (
  sq_calendar_id.NEXTVAL, 3, NULL, '외근 일정', '고객사 미팅',
  TO_DATE('2025-07-18 14:00', 'YYYY-MM-DD HH24:MI'), TO_DATE('2025-07-18 16:00', 'YYYY-MM-DD HH24:MI'), SYSDATE, 1
);

-- 최수정: 자율 근무일 (종일)
INSERT INTO calendar (id, member_id, dept_id, title, content, start_time, end_time, create_date, allday)
VALUES (
  sq_calendar_id.NEXTVAL, 5, NULL, '자율 근무일', '재택근무 예정',
  TO_DATE('2025-07-19', 'YYYY-MM-DD'), TO_DATE('2025-07-19', 'YYYY-MM-DD'), SYSDATE, 0
);

-- 조현우: 기술 세미나 참석 (시간 지정)
INSERT INTO calendar (id, member_id, dept_id, title, content, start_time, end_time, create_date, allday)
VALUES (
  sq_calendar_id.NEXTVAL, 6, NULL, '기술 세미나 참석', '외부 기술 세미나 참석',
  TO_DATE('2025-07-22 13:00', 'YYYY-MM-DD HH24:MI'), TO_DATE('2025-07-22 17:00', 'YYYY-MM-DD HH24:MI'), SYSDATE, 1
);

-- 한예진: 휴가 (종일)
INSERT INTO calendar (id, member_id, dept_id, title, content, start_time, end_time, create_date, allday)
VALUES (
  sq_calendar_id.NEXTVAL, 7, NULL, '휴가', '하계 휴가',
  TO_DATE('2025-07-29', 'YYYY-MM-DD'), TO_DATE('2025-07-29', 'YYYY-MM-DD'), SYSDATE, 0
);

-- 홍길동 (member_id = 1): 종일 일정 - 리프레시 데이
INSERT INTO calendar (id, member_id, dept_id, title, content, start_time, end_time, create_date, allday)
VALUES (
  sq_calendar_id.NEXTVAL, 1, NULL, '리프레시 데이', '개인 정비 및 휴식 시간',
  TO_DATE('2025-07-17', 'YYYY-MM-DD'), TO_DATE('2025-07-17', 'YYYY-MM-DD'), SYSDATE, 0
);

-- 홍길동 (member_id = 1): 시간 지정 일정 - 고객 미팅
INSERT INTO calendar (id, member_id, dept_id, title, content, start_time, end_time, create_date, allday)
VALUES (
  sq_calendar_id.NEXTVAL, 1, NULL, '고객 미팅', 'A사와 프로젝트 협의 미팅',
  TO_DATE('2025-07-24 15:00', 'YYYY-MM-DD HH24:MI'), TO_DATE('2025-07-24 16:30', 'YYYY-MM-DD HH24:MI'), SYSDATE, 1
);

-- 김철수 (member_id = 2): 종일 일정 - 세무 정리
INSERT INTO calendar (id, member_id, dept_id, title, content, start_time, end_time, create_date, allday)
VALUES (
  sq_calendar_id.NEXTVAL, 2, NULL, '세무 정리', '반기 세무 자료 정리 예정',
  TO_DATE('2025-07-20', 'YYYY-MM-DD'), TO_DATE('2025-07-20', 'YYYY-MM-DD'), SYSDATE, 0
);

-- 김철수 (member_id = 2): 시간 지정 일정 - 부서 내부 회의
INSERT INTO calendar (id, member_id, dept_id, title, content, start_time, end_time, create_date, allday)
VALUES (
  sq_calendar_id.NEXTVAL, 2, NULL, '내부 회의', '주간 업무 공유 회의',
  TO_DATE('2025-07-26 11:00', 'YYYY-MM-DD HH24:MI'), TO_DATE('2025-07-26 12:00', 'YYYY-MM-DD HH24:MI'), SYSDATE, 1
);

-- 이영희 (member_id = 3): 종일 일정 - 연차
INSERT INTO calendar (id, member_id, dept_id, title, content, start_time, end_time, create_date, allday)
VALUES (
  sq_calendar_id.NEXTVAL, 3, NULL, '연차', '개인 사유로 연차 사용',
  TO_DATE('2025-07-30', 'YYYY-MM-DD'), TO_DATE('2025-07-30', 'YYYY-MM-DD'), SYSDATE, 0
);

-- 이영희 (member_id = 3): 시간 지정 일정 - 보고서 작성
INSERT INTO calendar (id, member_id, dept_id, title, content, start_time, end_time, create_date, allday)
VALUES (
  sq_calendar_id.NEXTVAL, 3, NULL, '보고서 작성', '월간 실적 보고서 마감 작업',
  TO_DATE('2025-07-31 09:00', 'YYYY-MM-DD HH24:MI'), TO_DATE('2025-07-31 12:00', 'YYYY-MM-DD HH24:MI'), SYSDATE, 1
);

-- 홍길동 (member_id = 1): 시간 지정 일정 - 시스템 점검
INSERT INTO calendar (id, member_id, dept_id, title, content, start_time, end_time, create_date, allday)
VALUES (
  sq_calendar_id.NEXTVAL, 1, NULL, '시스템 점검', '사내 시스템 정기 점검',
  TO_DATE('2025-07-21 14:00', 'YYYY-MM-DD HH24:MI'), TO_DATE('2025-07-21 16:00', 'YYYY-MM-DD HH24:MI'), SYSDATE, 1
);

-- 홍길동 (member_id = 1): 종일 일정 - 부서 친목회
INSERT INTO calendar (id, member_id, dept_id, title, content, start_time, end_time, create_date, allday)
VALUES (
  sq_calendar_id.NEXTVAL, 1, NULL, '부서 친목회', '기획부 전체 회식 및 친목 행사',
  TO_DATE('2025-07-25', 'YYYY-MM-DD'), TO_DATE('2025-07-25', 'YYYY-MM-DD'), SYSDATE, 0
);

-- 김철수 (member_id = 2): 시간 지정 일정 - 외부 강의 참석
INSERT INTO calendar (id, member_id, dept_id, title, content, start_time, end_time, create_date, allday)
VALUES (
  sq_calendar_id.NEXTVAL, 2, NULL, '외부 강의 참석', 'AI 트렌드 관련 외부 세미나 참여',
  TO_DATE('2025-07-23 15:00', 'YYYY-MM-DD HH24:MI'), TO_DATE('2025-07-23 17:00', 'YYYY-MM-DD HH24:MI'), SYSDATE, 1
);

-- 김철수 (member_id = 2): 종일 일정 - 독서의 날
INSERT INTO calendar (id, member_id, dept_id, title, content, start_time, end_time, create_date, allday)
VALUES (
  sq_calendar_id.NEXTVAL, 2, NULL, '독서의 날', '하루 동안 업무 외 독서 집중',
  TO_DATE('2025-07-28', 'YYYY-MM-DD'), TO_DATE('2025-07-28', 'YYYY-MM-DD'), SYSDATE, 0
);

-- 이영희 (member_id = 3): 시간 지정 일정 - 인턴 교육 발표
INSERT INTO calendar (id, member_id, dept_id, title, content, start_time, end_time, create_date, allday)
VALUES (
  sq_calendar_id.NEXTVAL, 3, NULL, '인턴 교육 발표', '인턴 대상 교육 발표 진행',
  TO_DATE('2025-07-19 10:00', 'YYYY-MM-DD HH24:MI'), TO_DATE('2025-07-19 11:30', 'YYYY-MM-DD HH24:MI'), SYSDATE, 1
);

-- 이영희 (member_id = 3): 종일 일정 - 업무 집중 시간
INSERT INTO calendar (id, member_id, dept_id, title, content, start_time, end_time, create_date, allday)
VALUES (
  sq_calendar_id.NEXTVAL, 3, NULL, '업무 집중 시간', '보고서, 기획서 정리 집중',
  TO_DATE('2025-07-27', 'YYYY-MM-DD'), TO_DATE('2025-07-27', 'YYYY-MM-DD'), SYSDATE, 0
);


-- community
-- community 3개 (member_id가 개설자, 즉 master)
INSERT INTO community (id, name, description, create_date, member_id) VALUES (sq_community_id.NEXTVAL, '프로그래밍 동호회', '프로그래밍 관련 정보 공유 커뮤니티', TO_DATE('2025-06-01', 'YYYY-MM-DD'), 1);
INSERT INTO community (id, name, description, create_date, member_id) VALUES (sq_community_id.NEXTVAL, '취미 여행 모임', '여행 및 취미 생활을 나누는 모임', TO_DATE('2025-06-10', 'YYYY-MM-DD'), 2);
INSERT INTO community (id, name, description, create_date, member_id) VALUES (sq_community_id.NEXTVAL, '사진 사랑방', '사진 찍기와 공유를 좋아하는 모임', TO_DATE('2025-06-15', 'YYYY-MM-DD'), 3);

-- community_member (개설자는 master 역할)
-- 커뮤니티 1: 개설자 1번 (master), 멤버 1번 포함
INSERT INTO community_member (community_id, member_id, role, join_date) VALUES (1, 1, 'master', TO_DATE('2025-06-01', 'YYYY-MM-DD'));
INSERT INTO community_member (community_id, member_id, role, join_date) VALUES (1, 4, 'user', TO_DATE('2025-06-05', 'YYYY-MM-DD'));
INSERT INTO community_member (community_id, member_id, role, join_date) VALUES (1, 5, 'submaster', TO_DATE('2025-06-07', 'YYYY-MM-DD'));
INSERT INTO community_member (community_id, member_id, role, join_date) VALUES (1, 8, 'user', TO_DATE('2025-06-08', 'YYYY-MM-DD'));
INSERT INTO community_member (community_id, member_id, role, join_date) VALUES (1, 9, 'submaster', TO_DATE('2025-06-09', 'YYYY-MM-DD'));

-- 커뮤니티 2: 개설자 2번 (master), 멤버 1번도 포함
INSERT INTO community_member (community_id, member_id, role, join_date) VALUES (2, 2, 'master', TO_DATE('2025-06-10', 'YYYY-MM-DD'));
INSERT INTO community_member (community_id, member_id, role, join_date) VALUES (2, 1, 'user', TO_DATE('2025-06-12', 'YYYY-MM-DD'));
INSERT INTO community_member (community_id, member_id, role, join_date) VALUES (2, 6, 'user', TO_DATE('2025-06-13', 'YYYY-MM-DD'));
INSERT INTO community_member (community_id, member_id, role, join_date) VALUES (2, 10, 'user', TO_DATE('2025-06-14', 'YYYY-MM-DD'));
INSERT INTO community_member (community_id, member_id, role, join_date) VALUES (2, 11, 'submaster', TO_DATE('2025-06-15', 'YYYY-MM-DD'));

-- 커뮤니티 3: 개설자 3번 (master), 멤버 1번도 포함
INSERT INTO community_member (community_id, member_id, role, join_date) VALUES (3, 3, 'master', TO_DATE('2025-06-15', 'YYYY-MM-DD'));
INSERT INTO community_member (community_id, member_id, role, join_date) VALUES (3, 1, 'submaster', TO_DATE('2025-06-16', 'YYYY-MM-DD'));
INSERT INTO community_member (community_id, member_id, role, join_date) VALUES (3, 7, 'user', TO_DATE('2025-06-18', 'YYYY-MM-DD'));
INSERT INTO community_member (community_id, member_id, role, join_date) VALUES (3, 12, 'user', TO_DATE('2025-06-19', 'YYYY-MM-DD'));
INSERT INTO community_member (community_id, member_id, role, join_date) VALUES (3, 13, 'user', TO_DATE('2025-06-20', 'YYYY-MM-DD'));

-- 게시판
INSERT INTO community_board (id, community_id, name, description, create_date) VALUES (sq_community_board_id.NEXTVAL, 1, '자유 게시판', '자유롭게 이야기하는 공간', TO_DATE('2025-06-02', 'YYYY-MM-DD'));
INSERT INTO community_board (id, community_id, name, description, create_date) VALUES (sq_community_board_id.NEXTVAL, 1, '질문과 답변', '질문 올리고 답변 받는 공간', TO_DATE('2025-06-02', 'YYYY-MM-DD'));

INSERT INTO community_board (id, community_id, name, description, create_date) VALUES (sq_community_board_id.NEXTVAL, 2, '여행 후기', '여행 다녀온 후기 공유', TO_DATE('2025-06-11', 'YYYY-MM-DD'));
INSERT INTO community_board (id, community_id, name, description, create_date) VALUES (sq_community_board_id.NEXTVAL, 2, '맛집 추천', '좋은 맛집을 추천하는 게시판', TO_DATE('2025-06-11', 'YYYY-MM-DD'));

INSERT INTO community_board (id, community_id, name, description, create_date) VALUES (sq_community_board_id.NEXTVAL, 3, '사진 공유', '사진을 올리고 공유하는 공간', TO_DATE('2025-06-16', 'YYYY-MM-DD'));
INSERT INTO community_board (id, community_id, name, description, create_date) VALUES (sq_community_board_id.NEXTVAL, 3, '사진 촬영 팁', '촬영 노하우를 나누는 게시판', TO_DATE('2025-06-16', 'YYYY-MM-DD'));

-- 게시글
-- 게시글 예시 (게시판 1)
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES 
(sq_board_post_id.NEXTVAL, 1, 1, '프로그래밍 입문 질문', '자바 기초 관련 질문입니다.', TO_DATE('2025-06-03', 'YYYY-MM-DD'), 120, 15);
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES 
(sq_board_post_id.NEXTVAL, 1, 2, '프로젝트 아이디어 공유', '새로운 프로젝트 아이디어를 소개합니다.', TO_DATE('2025-06-04', 'YYYY-MM-DD'), 85, 9);
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES 
(sq_board_post_id.NEXTVAL, 1, 3, '개발 도구 추천', '효율적인 개발 도구를 추천해주세요.', TO_DATE('2025-06-05', 'YYYY-MM-DD'), 75, 8);
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES 
(sq_board_post_id.NEXTVAL, 1, 4, '오류 해결 방법', '이 오류 어떻게 해결하나요?', TO_DATE('2025-06-06', 'YYYY-MM-DD'), 60, 5);
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES 
(sq_board_post_id.NEXTVAL, 1, 5, '학습 자료 공유', '좋은 학습 자료 링크를 공유합니다.', TO_DATE('2025-06-07', 'YYYY-MM-DD'), 100, 12);

-- 게시판 2 (id 6~10)
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES 
(sq_board_post_id.NEXTVAL, 2, 1, 'Spring Boot 질문', 'Spring Boot 설정 관련 질문입니다.', TO_DATE('2025-06-03', 'YYYY-MM-DD'), 90, 10);
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES 
(sq_board_post_id.NEXTVAL, 2, 2, 'API 설계 팁', 'REST API 설계 방법에 대해 논의해요.', TO_DATE('2025-06-04', 'YYYY-MM-DD'), 70, 6);
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES 
(sq_board_post_id.NEXTVAL, 2, 3, 'DB 마이그레이션', '효율적인 DB 마이그레이션 전략을 공유합니다.', TO_DATE('2025-06-05', 'YYYY-MM-DD'), 80, 7);
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES 
(sq_board_post_id.NEXTVAL, 2, 4, '테스트 코드 작성', '테스트 코드 작성법 알려주세요.', TO_DATE('2025-06-06', 'YYYY-MM-DD'), 65, 5);
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES 
(sq_board_post_id.NEXTVAL, 2, 5, '빌드 자동화', 'CI/CD 도구 관련 질문입니다.', TO_DATE('2025-06-07', 'YYYY-MM-DD'), 55, 4);

-- 게시판 3 (id 11~15)
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES
(sq_board_post_id.NEXTVAL, 3, 1, '이번 여행 후기', '이번 제주도 여행 후기입니다.', TO_DATE('2025-06-12', 'YYYY-MM-DD'), 130, 18);
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES 
(sq_board_post_id.NEXTVAL, 3, 2, '추천 여행지', '여름에 갈 만한 여행지 추천해요.', TO_DATE('2025-06-13', 'YYYY-MM-DD'), 100, 11);
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES 
(sq_board_post_id.NEXTVAL, 3, 3, '여행 팁', '여행 준비 팁을 공유합니다.', TO_DATE('2025-06-14', 'YYYY-MM-DD'), 90, 10);
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES 
(sq_board_post_id.NEXTVAL, 3, 4, '교통편 정보', '편리한 교통편 정보를 알려주세요.', TO_DATE('2025-06-15', 'YYYY-MM-DD'), 70, 7);
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES 
(sq_board_post_id.NEXTVAL, 3, 5, '숙소 후기', '좋은 숙소 후기입니다.', TO_DATE('2025-06-16', 'YYYY-MM-DD'), 85, 9);

-- 게시판 4 (id 16~20)
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES
(sq_board_post_id.NEXTVAL, 4, 1, '서울 맛집 추천', '서울에 맛있는 식당 추천합니다.', TO_DATE('2025-06-12', 'YYYY-MM-DD'), 110, 15);
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES 
(sq_board_post_id.NEXTVAL, 4, 2, '여름철 음식', '더운 여름철에 좋은 음식 소개해요.', TO_DATE('2025-06-13', 'YYYY-MM-DD'), 95, 8);
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES 
(sq_board_post_id.NEXTVAL, 4, 3, '식당 리뷰', '최근 다녀온 식당 리뷰입니다.', TO_DATE('2025-06-14', 'YYYY-MM-DD'), 80, 6);
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES 
(sq_board_post_id.NEXTVAL, 4, 4, '특별한 요리법', '특별한 요리법을 공유합니다.', TO_DATE('2025-06-15', 'YYYY-MM-DD'), 60, 5);
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES 
(sq_board_post_id.NEXTVAL, 4, 5, '맛집 사진', '맛집 사진 올립니다.', TO_DATE('2025-06-16', 'YYYY-MM-DD'), 70, 7);

-- 게시판 5 (id 21~25)
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES
(sq_board_post_id.NEXTVAL, 5, 1, '사진 공유 첫 글', '첫 번째 사진 공유 게시글입니다.', TO_DATE('2025-06-17', 'YYYY-MM-DD'), 150, 20);
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES 
(sq_board_post_id.NEXTVAL, 5, 2, '풍경 사진', '최근 찍은 풍경 사진입니다.', TO_DATE('2025-06-18', 'YYYY-MM-DD'), 120, 14);
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES 
(sq_board_post_id.NEXTVAL, 5, 3, '인물 사진 팁', '인물 사진 촬영 팁 공유합니다.', TO_DATE('2025-06-19', 'YYYY-MM-DD'), 110, 13);
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES 
(sq_board_post_id.NEXTVAL, 5, 4, '사진 편집 프로그램', '사진 편집 프로그램 추천합니다.', TO_DATE('2025-06-20', 'YYYY-MM-DD'), 90, 10);
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES 
(sq_board_post_id.NEXTVAL, 5, 5, '카메라 리뷰', '최신 카메라 리뷰입니다.', TO_DATE('2025-06-21', 'YYYY-MM-DD'), 85, 9);

-- 게시판 6 (id 26~30)
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES
(sq_board_post_id.NEXTVAL, 6, 1, '촬영 팁 공유', '촬영 팁을 공유하는 글입니다.', TO_DATE('2025-06-17', 'YYYY-MM-DD'), 130, 17);
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES 
(sq_board_post_id.NEXTVAL, 6, 2, '렌즈 추천', '사용하기 좋은 렌즈 추천합니다.', TO_DATE('2025-06-18', 'YYYY-MM-DD'), 115, 15);
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES 
(sq_board_post_id.NEXTVAL, 6, 3, '촬영 장비 소개', '촬영 장비를 소개합니다.', TO_DATE('2025-06-19', 'YYYY-MM-DD'), 100, 12);
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES 
(sq_board_post_id.NEXTVAL, 6, 4, '야외 촬영 팁', '야외 촬영 노하우 공유합니다.', TO_DATE('2025-06-20', 'YYYY-MM-DD'), 85, 9);
INSERT INTO board_post (id, board_id, member_id, title, content, write_date, view_num, upvote) VALUES 
(sq_board_post_id.NEXTVAL, 6, 5, '조명 사용법', '조명 사용법에 대해 설명합니다.', TO_DATE('2025-06-21', 'YYYY-MM-DD'), 80, 8);

