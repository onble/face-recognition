-- 生成用户信息表
create table user
(
    id       int primary key auto_increment comment '管理员id',
    account  varchar(50)  not null unique comment '账号',
    password varchar(200) not null comment '密码'
) comment '用户信息表';
-- 增加用户账号
insert into user(id, account, password)
values (1, 'root', '123456'),
    (2, 'xiaohong', '123456'),
    (3, 'tiger', 'tiger'),
    (4, 'Boni', '123456'),
    (5, 'Mark', '123456'),
    (6, 'Baka', '123456'),
    (7, 'CC', '123456'),
    (8, 'GG', '123456'),
    (9, 'CX', '123456'),
    (10, 'LHB', '123456'),
    (11, 'Daily', '123456'),
    (12, 'Tour', '123456');
