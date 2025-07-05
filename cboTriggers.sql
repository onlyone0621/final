-- Triggers

-- Auto incrementing id 
CREATE OR REPLACE TRIGGER trg_member_id
BEFORE INSERT ON member
FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        :NEW.id := sq_member_id.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER trg_dept_id
BEFORE INSERT ON dept
FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        :NEW.id := sq_dept_id.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER trg_grade_id
BEFORE INSERT ON grade
FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        :NEW.id := sq_grade_id.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER trg_message_id
BEFORE INSERT ON message
FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        :NEW.id := sq_message_id.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER trg_drive_id
BEFORE INSERT ON drive
FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        :NEW.id := sq_drive_id.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER trg_chatroom_id
BEFORE INSERT ON chatroom
FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        :NEW.id := sq_chatroom_id.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER trg_chat_message_id
BEFORE INSERT ON chat_message
FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        :NEW.id := sq_chat_message_id.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER trg_groups_id
BEFORE INSERT ON groups
FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        :NEW.id := sq_groups_id.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER trg_addr_id
BEFORE INSERT ON addr
FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        :NEW.id := sq_addr_id.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER trg_calendar_id
BEFORE INSERT ON calendar
FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        :NEW.id := sq_calendar_id.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER trg_doc_id
BEFORE INSERT ON doc
FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        :NEW.id := sq_doc_id.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER trg_community_id
BEFORE INSERT ON community
FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        :NEW.id := sq_community_id.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER trg_community_post_id
BEFORE INSERT ON community_post
FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        :NEW.id := sq_community_post_id.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER trg_community_comment_id
BEFORE INSERT ON community_comment
FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        :NEW.id := sq_community_comment_id.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER trg_format_id
BEFORE INSERT ON format
FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        :NEW.id := sq_format_id.NEXTVAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER trg_message_ref
BEFORE INSERT ON message
FOR EACH ROW
BEGIN
    IF :NEW.ref = 0 AND :NEW.lev = 0 THEN
        :NEW.ref := sq_message_ref.NEXTVAL;
    END IF;
END;
/
