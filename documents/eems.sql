-- 企业员工管理系统 Enterprise Employee Management System
CREATE DATABASE eems DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE eems;

/*==============================================================*/
  --  用户      EEMS_USER
/*==============================================================*/
drop table if exists EEMS_USER;
create table EEMS_USER(
    USER_ID                       char(36)            character set utf8 collate utf8_bin    not null comment '用户编号',
    NICKNAME                      varchar(32)         character set utf8 collate utf8_bin    not null comment '用户昵称',
    ACCOUNT                       varchar(32)         character set utf8 collate utf8_bin    not null comment '用户账号',
    PASSWORD                      varchar(32)         character set utf8 collate utf8_bin    not null comment '用户密码',    
    CREATE_TIMESTAMPS             bigint                                                     not null comment '创建时间',
    primary key(USER_ID),
    unique key (ACCOUNT)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '用户';

/*==============================================================*/
  --  员工      EEMS_STAFF
/*==============================================================*/
drop table if exists EEMS_STAFF;
create table EEMS_STAFF(
    STAFF_ID                      char(36)            character set utf8 collate utf8_bin    not null comment '员工编号',
    NAME                          varchar(32)         character set utf8 collate utf8_bin    not null comment '员工名称',
    ACCOUNT                       varchar(32)         character set utf8 collate utf8_bin        null comment '员工账号',
    PASSWORD                      varchar(32)         character set utf8 collate utf8_bin        null comment '员工密码', 
    PHONEN                        varchar(16)         character set utf8 collate utf8_bin        null comment '员工电话', 
    ADDRESS                       varchar(128)        character set utf8 collate utf8_bin        null comment '员工地址',
    WECHATN                       varchar(16)         character set utf8 collate utf8_bin        null comment '员工微信',
    QQN                           varchar(16)         character set utf8 collate utf8_bin        null comment '员工QQ',      
    CREATE_TIMESTAMPS             bigint                                                     not null comment '创建时间',
    primary key(STAFF_ID),
    unique key (ACCOUNT)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '员工';

/*==============================================================*/
  --  奖惩记录      EEMS_JC_RECORDING
/*==============================================================*/
drop table if exists EEMS_JC_RECORDING;
create table EEMS_JC_RECORDING(
    RECORDING_ID                  char(36)            character set utf8 collate utf8_bin    not null comment '记录编号',
    STAFF_ID                      char(36)            character set utf8 collate utf8_bin    not null comment '员工编号',
    JC_TYPE                       char(1)             character set utf8 collate utf8_bin    not null comment '奖惩类型',
    AMOUNT                        decimal(13,4)                                              not null comment '奖惩金额',
    CREATE_TIMESTAMPS             bigint                                                     not null comment '创建时间',
    primary key(RECORDING_ID)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '奖惩记录';
alter table EEMS_JC_RECORDING add constraint FK_EEMS_JC_R_STAFF_ID foreign key (STAFF_ID) references EEMS_STAFF(STAFF_ID);

/*==============================================================*/
  --  工资      EEMS_WAGE
/*==============================================================*/
drop table if exists EEMS_WAGE;
create table EEMS_WAGE(
    WAGE_ID                       char(36)            character set utf8 collate utf8_bin    not null comment '记录编号',
    STAFF_ID                      char(36)            character set utf8 collate utf8_bin    not null comment '员工编号',
    BASE_AMOUNT                   decimal(13,4)                                              not null comment '基本金额',
    J_COUNT                       int                                                        not null comment '奖励次数',
    C_COUNT                       int                                                        not null comment '惩罚次数',
    J_AMOUNT                      decimal(13,4)                                              not null comment '奖励金额',
    C_AMOUNT                      decimal(13,4)                                              not null comment '惩罚金额',
    FINALLY_AMOUNT                decimal(13,4)                                              not null comment '最终金额',
    CREATE_TIMESTAMPS             bigint                                                     not null comment '创建时间',
    primary key(WAGE_ID)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '工资';
alter table EEMS_WAGE add constraint FK_EEMS_WAGE_STAFF_ID foreign key (STAFF_ID) references EEMS_STAFF(STAFF_ID);

