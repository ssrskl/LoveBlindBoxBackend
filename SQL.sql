drop database if exists love_blindbox;
create database love_blindbox character set utf8mb4;
use love_blindbox;

# 创建用户表
drop table if exists love_user;
create table love_user
(
    user_id  bigint(255)  not null auto_increment comment '用户ID',
    username varchar(255) not null unique comment '用户名',
    password varchar(255) not null comment '',
    email    varchar(255) not null unique comment '',
    status   bit(1) comment '',
    avater   varchar(255) comment '',
    primary key (user_id)
) engine = innodb
  auto_increment = 1
  character set = utf8mb4
    comment = '用户表';

# 创建纸条表
drop table if exists love_stick;
create table love_stick
(
    stick_id     bigint(255) not null auto_increment comment '纸条ID',
    publisher_id bigint(255) not null comment '',
    receiver_id  bigint(255) default 0 comment '',
    gender       int(2) comment '',
    age          int(10) comment '',
    qq           varchar(255) comment '',
    wechat       varchar(255) comment '',
    hobby        varchar(255) comment '',
    personality  varchar(10) comment '',
    introduction varchar(255) comment '',
    primary key (stick_id)
) engine = innodb
  auto_increment = 1
  character set = utf8mb4
    comment = '纸条表';

# 输入测试数据
insert into love_user (user_id, username, password, email, status, avater)
values (1, '猫颜', 'f180b420d0d522714e566b2befb08342', '1071352028@qq.com', 1, 'http');
insert into love_user (user_id, username, password, email, status, avater)
values (2, '达芬奇', 'f180b420d0d522714e566b2befb08342', 'oimaoyanio@gamil.com', 1, 'http');
insert into love_user (user_id, username, password, email, status, avater)
values (3, '猫颜2', 'f180b420d0d522714e566b2befb08342', '820244680@qq.com', 1, 'http');

insert into love_stick (stick_id, publisher_id, receiver_id, gender, age, qq, wechat, hobby, personality, introduction)
values (1, 1, 0, 1, 21, '1071352028', '17104344673', '爱好', 'ENFP', '个人介绍');
insert into love_stick (stick_id, publisher_id, receiver_id, gender, age, qq, wechat, hobby, personality, introduction)
values (2, 2, 1, 1, 21, '1071352028', '17104344673', '爱好', 'ENFP', '个人介绍');
insert into love_stick (stick_id, publisher_id, receiver_id, gender, age, qq, wechat, hobby, personality, introduction)
values (3, 2, 0, 1, 21, '1071352028', '17104344673', '爱好', 'ENFP', '个人介绍');