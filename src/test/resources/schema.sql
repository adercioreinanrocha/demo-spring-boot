CREATE TABLE IF NOT EXISTS STUDENT
(
    ID NUMBER(10,0) NOT NULL,
    NAME VARCHAR2(60) NULL,
    PASSPORT_NUMBER VARCHAR2(60) NULL
);

CREATE TABLE IF NOT EXISTS USER_ALL
(
    ID NUMBER(10,0) NOT NULL,
    USER_NAME VARCHAR2(60) NULL,
    PASSWORD VARCHAR2(60) NULL,
    ROLE VARCHAR2(20) NULL
);