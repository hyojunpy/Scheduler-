
--일정 테이블 생성
CREATE TABLE `schedules` (
    'id' INT AUTO_INCREMENT NOT NULL,
    'todo' VARCHAR(200) NOT NULL,
    'writer' VARCHAR(20) NOT NULL,
    'password' VARCHAR(20) NOT NULL,
<<<<<<< HEAD
    'create_date' TIMESTAMP  NOT NULL,
    'update_date' TIMESTAMP  NOT NULL
=======
    'create_date' DATETIME NOT NULL,
    'update_date' DATETIME NOT NULL
>>>>>>> 9c0647939b81f8198eb9bd1a9ff6417b3de6692d
    PRIMARY KEY ('ID')
);

-- 일정 생성
INSERT INTO SHEDULES (ID, TODO, WRITER, PASSWORD, CREATE_DATE, UPDATE_DATE)
VALUES (DEFAULT, '평택 이동', '편효준', '1234', NOW(), NOW());

--전체 일정 조회
SELECT *
FROM SCHEDULES
ORDER BY UDATE_DATE DESC

--선택한 일정 조회
SELECT *
FROM SCHEDULES
WHERE ID = 5;

--선택한 일정 수정
UPDATE SCHEDULES
SET TODO = '서울 가기'
WHERE ID = 5;

-- 선택한 일정 삭제
DELETE FROM SCHEDULES
WHERE ID = 5;
