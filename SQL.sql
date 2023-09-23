drop database if exists love_blindbox;
create database love_blindbox character set utf8mb4;
use love_blindbox;

# 创建用户表
drop table if exists users;
create table love_user
(
    user_id      bigint(255)  not null auto_increment comment '用户ID',
    username     varchar(255) not null unique comment '用户名',
    password     varchar(255) not null comment '',
    email        varchar(255) not null unique comment '',
    gender       bit(1) comment '',
    status       bit(1) comment '',
    age          varchar(10) comment '',
    qq           varchar(255) comment '',
    wechat       varchar(255) comment '',
    avater       varchar(255) comment '',
    hobby        varchar(255) comment '',
    personality  varchar(10) comment '',
    introduction varchar(255) comment '',
    primary key (user_id)
) engine = innodb
  auto_increment = 1
  character set = utf8mb4
    comment = '用户表';

# 创建纸条表
drop table if exists sticks;
create table love_stick
(
    stick_id         bigint(255) not null auto_increment comment '纸条ID',
    publisher_id     bigint(255) not null comment '',
    receiver_id      bigint(255) not null comment '',
    publisher_gender bit(1) comment '',
    age              varchar(10) comment '',
    qq               varchar(255) comment '',
    wechat           varchar(255) comment '',
    hobby            varchar(255) comment '',
    personality      varchar(10) comment '',
    introduction     varchar(255) comment '',
    primary key (stick_id)
) engine = innodb
  auto_increment = 1
  character set = utf8mb4
    comment = '纸条表';

# 输入测试数据
insert into love_user (user_id, username, password, email, gender, status, age, qq, wechat, avater, hobby, personality,
                   introduction)
values (1, '猫颜', 'ar352878987', '1071352028@qq.com', 1, 1, 21, '1071352028', '17104344673', 'http', '有成就感的事情',
        'ENFP', '个人简介');
