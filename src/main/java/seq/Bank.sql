DROP DATABASE IF EXISTS db_bank;
CREATE DATABASE db_bank;

DROP TABLE IF EXISTS db_bank.user;
CREATE TABLE db_bank.user (
  id       INT                  AUTO_INCREMENT PRIMARY KEY
  COMMENT 'id pk',
  userName VARCHAR(20) NOT NULL
  COMMENT '用户名',
  password VARCHAR(40) NOT NULL
  COMMENT '密码',
  identity VARCHAR(20) NOT NULL DEFAULT '客户'
  COMMENT '身份'
)
  COMMENT '用户表';

DROP TABLE IF EXISTS db_bank.data;
CREATE TABLE db_bank.data (
  id         INT AUTO_INCREMENT PRIMARY KEY
  COMMENT 'id pk',
  userId     INT         NOT NULL UNIQUE
  COMMENT '用户id',
  birthdate  DATE        NOT NULL
  COMMENT '出生日期',
  gender     CHAR        NOT NULL
  COMMENT '性别',
  profession VARCHAR(20) NOT NULL
  COMMENT '职业'
)
  COMMENT '用户资料' ;

DROP TABLE IF EXISTS db_bank.purse;
CREATE TABLE db_bank.purse (
  id    INT              AUTO_INCREMENT PRIMARY KEY
  COMMENT 'pk id',
  userId     INT         NOT NULL UNIQUE
  COMMENT '用户id',
  money DECIMAL NOT NULL DEFAULT 0
  COMMENT '余额'
)
  COMMENT '用户钱包' ;

DROP TABLE IF EXISTS db_bank.reputation;
CREATE TABLE db_bank.reputation (
  id    INT AUTO_INCREMENT PRIMARY KEY
  COMMENT 'pk id',
  userId     INT         NOT NULL UNIQUE
  COMMENT '用户id',
  grade VARCHAR(10) DEFAULT '良'NOT NULL
  COMMENT '信誉度'
)
  COMMENT '用户信誉';
-- ---------------------------------------------------------------
ALTER TABLE db_bank.data
  ADD CONSTRAINT
  data_fk_userId
FOREIGN KEY (userId)
REFERENCES db_bank.user (id);

ALTER TABLE db_bank.purse
  ADD CONSTRAINT
  purse_fk_userId
FOREIGN KEY (userId)
REFERENCES db_bank.user (id);

ALTER TABLE db_bank.reputation
  ADD CONSTRAINT
  reputation_pk_userId
FOREIGN KEY (userId)
REFERENCES db_bank.user (id);



INSERT INTO db_bank.user (userName, password) VALUES ('tom', 'aaa');

INSERT INTO db_bank.data (userId, birthdate, gender,profession) VALUES (1, '1993-08-08', '男','程序员');
INSERT INTO db_bank.purse (userId, money) VALUES (1, 10000.00);
INSERT INTO db_bank.reputation (userId, grade) VALUES (1, '优');

SELECT d.userId,u.userName,u.password,d.birthdate,d.gender,d.profession,p.money,r.grade
FROM db_bank.user u INNER JOIN db_bank.data d INNER JOIN db_bank.purse p INNER JOIN db_bank.reputation r
ON u.id = d.userId =p.userId=r.userId;

select * from db_gg.account;

update db_gg.account set name = 'tom' where id = 1
