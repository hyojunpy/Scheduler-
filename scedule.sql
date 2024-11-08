
CREATE TABLE schedules(
id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT 'id',

todo VARCHAR(200) NOT NULL COMMENT '일정',

writer VARCHAR(20) NOT NULL COMMENT '작성자명',

password VARCHAR(20) NOT NULL COMMENT '비밀번호',

create_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',

update_date TIMESTAMP DEFAULT  CURRENT_TIMESTAMP ON UPDATE  CURRENT_TIMESTAMP NOT NULL COMMENT '수정일'

)

-- 일정 생성
INSERT INTO SHEDULES (ID, TODO, WRITER, PASSWORD, CREATE_DATE, UPDATE_DATE)
VALUES (DEFAULT, '평택 이동', '편효준', '1234', NOW(), NOW());

--전체 일정 조회
SELECT id, todo, writer, create_date, update_date
FROM SCHEDULES
ORDER BY update_date DESC

--선택한 일정 조회
SELECT id, todo, writer, create_date, update_date
FROM SCHEDULES
WHERE ID = ?;

--선택한 일정 수정
UPDATE SCHEDULES
SET TODO = '서울 가기'
WHERE ID = ? AND password = ?;

-- 선택한 일정 삭제
DELETE FROM SCHEDULES
WHERE ID = ? AND password = ?;
