DROP DATABASE IF EXISTS netbank;
create database netbank;
use netbank;

create table trade
(
  cardId      varchar(30) not null ,
  AmountTransferred double not null,
  payee varchar(10),
  cardIdOfPayee varchar(30) not null,
  remarks varchar(30) null,
  transferTime datetime null
)ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

create table bankAdmin
(
     AdminId      varchar(30) not null ,
     AdminPassword varchar(30) not null
)ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

create table account
(
    cardID varchar(30) not null,
    status varchar(10) default "正常",
    credit int default 100,
    balance double not null

)ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

create table depositor
(
  id  int  auto_increment  primary key,
  username    varchar(30) not null,
  password    varchar(20) not null,
  name        varchar(10) not null,
  tel         varchar(11) not null,
  cardid      varchar(30) not null,
  pid         varchar(30) not null,
  gender      varchar(6)  not null,
  address     varchar(45) null
)ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

CREATE TABLE `log` (
    `log_id` INT NOT NULL AUTO_INCREMENT COMMENT '日志ID',
    `cardID` varchar(30) NOT NULL COMMENT '账户ID',
    `log_content` TEXT NOT NULL COMMENT '日志内容',
    `log_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '日志时间',
    PRIMARY KEY (`log_id`),
    INDEX `idx_cardID` (`cardID`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS FixedDeposit;
CREATE TABLE FixedDeposit (
   id INT AUTO_INCREMENT PRIMARY KEY,
   account_number VARCHAR(30) NOT NULL,
   start_date DATE NOT NULL,
   end_date DATE NOT NULL,
   deposit_amount DOUBLE NOT NULL,
   deposit_type VARCHAR(30) NOT NULL,
   interest_rate DOUBLE NOT NULL,
   maturity_amount DOUBLE NOT NULL
)ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;


insert into depositor(username,password,name,tel,cardid,pid,gender,address) values ("JiangJiang","123123","陈思静","13800123000","6217280742900642000","44078019900205333X","女","湖北武汉市");
insert into depositor(username,password,name,tel,cardid,pid,gender,address) values ("ZhangShan","123123","张珊","13800123001","6217280742900642001","44078019900205331X","女","海南省");
insert into depositor(username,password,name,tel,cardid,pid,gender,address) values ("LIliJun","123123","李丽君","13800123002","6217280742900642002","44078019900205335X","女","广东省江门市");
insert into depositor(username,password,name,tel,cardid,pid,gender,address) values ("Chuchu","123123","欧楚楚","13800123003","6217280742900642003","440780199002053335","女","广州市");


insert into bankAdmin(AdminId, adminpassword) values ("admin", "123456");
insert into bankAdmin(AdminId, adminpassword) values ("#SYSTEM", "root");

insert into account(cardID,balance) values("6217280937373828274", 50400);
insert into account(cardID,balance) values("6217280937375294751", 50000);
insert into account(cardID,balance) values("6217280937373820000", 25000);
insert into account(cardID,balance) values("6217280937300009494", 18500);
insert into account(cardID,balance) values("6217280937111111111", 185000);
insert into account(cardID,balance) values("6217280937222222222", 1800);
insert into account(cardID,balance) values("6217280000000000000", 1000);
insert into account(cardID,balance) values("6217280742900642000", 1000000);
insert into account(cardID,balance) values("6217280742900642001", 1000);
insert into account(cardID,balance) values("6217280742900642002", 1000);
insert into account(cardID,balance) values("6217280742900642003", 1000);