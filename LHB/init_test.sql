-- 生成用户信息表
create table user
(
    id       int primary key auto_increment comment '管理员id',
    account  varchar(50)  not null unique comment '账号',
    password varchar(200) not null comment '密码',
    status   tinyint      not null default 0 comment '账号状态，0：正常使用 1:禁止使用 2:刚注册',
    avatar   varchar(100) not null default '/images/default.jpg' comment '头像路径'
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
-- 生成人物基础信息表
create table people_base_info
(
    id       int primary key auto_increment comment '人物信息id',
    name     varchar(50) not null comment '人物姓名',
    gender   TINYINT     not null default 1 comment '性别,0:女,1:男',
    imageNum int         not null default 0 comment '题库照片数量',
    status   tinyint     not null default 0 comment '状态，0：正常使用 1:禁止使用'
) comment '人物基础信息表';
insert into people_base_info (id, name, gender, imageNum)
values (1, '李晨', 1, 12),
       (2, '鹿晗', 1, 16),
       (3, '郑恺', 1, 20),
       (4, 'Angelagbaby', 0, 8),
       (5, '迪丽热巴', 0, 36),
       (6, '范冰冰', 0, 9),
       (7, '杨幂', 0, 46),
       (8, '赵丽颖', 0, 9);

-- 生成题目记录表
create table test_base_info
(
    id          int primary key auto_increment comment '题目id',
    people_id   int comment '人物id',
    imageIndex  varchar(200) not null comment '图片存储路径',
    status      tinyint      not null default 0 comment '状态，0：正常使用 1:禁止使用',
    wrong_times int                   default 0 comment '做错次数',
    right_times int                   default 0 comment '做对次数',
    foreign key (people_id) references people_base_info (id)
) comment '题目记录表';
insert into test_base_info (people_id, imageIndex)
values (1, 'public/face/李晨/1.jpg'),
       (1, 'public/face/李晨/2.jpg'),
       (1, 'public/face/李晨/3.jpg'),
       (1, 'public/face/李晨/4.jpg'),
       (1, 'public/face/李晨/5.jpg'),
       (1, 'public/face/李晨/6.jpg'),
       (1, 'public/face/李晨/7.jpg'),
       (1, 'public/face/李晨/8.jpg'),
       (1, 'public/face/李晨/9.jpg'),
       (1, 'public/face/李晨/10.jpg'),
       (1, 'public/face/李晨/11.jpg'),
       (1, 'public/face/李晨/12.jpg'),
       (2, 'public/face/鹿晗/1.jpg'),
       (2, 'public/face/鹿晗/2.jpg'),
       (2, 'public/face/鹿晗/3.jpg'),
       (2, 'public/face/鹿晗/4.jpg'),
       (2, 'public/face/鹿晗/5.jpg'),
       (2, 'public/face/鹿晗/6.jpg'),
       (2, 'public/face/鹿晗/7.jpg'),
       (2, 'public/face/鹿晗/8.jpg'),
       (2, 'public/face/鹿晗/9.jpg'),
       (2, 'public/face/鹿晗/10.jpg'),
       (2, 'public/face/鹿晗/11.jpg'),
       (2, 'public/face/鹿晗/12.jpg'),
       (2, 'public/face/鹿晗/13.jpg'),
       (2, 'public/face/鹿晗/14.jpg'),
       (2, 'public/face/鹿晗/15.jpg'),
       (2, 'public/face/鹿晗/16.jpg');

-- 生成四选一题组信息表
create table four_test_info
(
    id       int primary key auto_increment comment '题组id',
    test1_id int comment '测试题1',
    test2_id int comment '测试题2',
    test3_id int comment '测试题3',
    test4_id int comment '测试题4',
    status   tinyint not null default 0 comment '状态，0：正常使用 1:禁止使用',
    foreign key (test1_id) references test_base_info (id),
    foreign key (test2_id) references test_base_info (id),
    foreign key (test3_id) references test_base_info (id),
    foreign key (test4_id) references test_base_info (id)
) comment '四选一题组信息表';
insert into four_test_info (test1_id, test2_id, test3_id, test4_id)
values (1, 2, 3, 4),
       (1, 3, 2, 4),
       (1, 3, 4, 5),
       (2, 3, 4, 5),
       (2, 4, 5, 6),
       (1, 4, 5, 6),
       (2, 5, 3, 1),
       (2, 3, 4, 1);
-- 生成用户做题表
create table user_test
(
    id                 int primary key auto_increment comment '用户测试id',
    test_group_id      int comment '题组信息id',
    done_time          TIMESTAMP comment '做题结束时间',
    group_kind         tinyint not null default 0 comment '状态，1：四选一题 2:分类测试 3:寻找测试 0:未分类',
    time_spent_seconds INT comment '做题所耗时间s',
    user_id            int,
    foreign key (test_group_id) references test_base_info (id),
    foreign key (user_id) references user (id)
) comment '用户做题表';
