-- 生成用户信息表
create table user
(
    id       int primary key auto_increment comment '用户id',
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
insert into people_base_info (name, gender, imageNum)
values ('李晨', 1, 12),
       ('陈立农', 1, 101),
       ('王俊凯', 1, 100),
       ('王一博', 1, 100),
       ('许光汉', 1, 84),
       ('薛之谦', 1, 104),
       ('张艺兴', 1, 100),
       ('朱一龙', 1, 100),
#        ('鹿晗', 1, 16),
       ('郑恺', 1, 54),
       ('Angelagbaby', 0, 164),
       ('迪丽热巴', 0, 104),
       ('范冰冰', 0, 102),
       ('古力娜扎', 0, 107),
       ('杨幂', 0, 196),
       ('张子枫', 0, 103),
       ('赵今麦', 0, 103),
       ('赵丽颖', 0, 100);

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


-- 生成四选一题目操作表
create table four_test_action
(
    id      int primary key auto_increment comment '四选一题目操作id',
    action1 int not null default 0 comment '操作1',
    action2 int not null default 0 comment '操作2',
    action3 int not null default 0 comment '操作3',
    action4 int not null default 0 comment '操作4'

) comment '四选一题目操作表';
-- 生成用户做题表
create table user_test
(
    id                 int primary key auto_increment comment '用户测试id',
    test_group_id      int comment '题组信息id',
    done_time          TIMESTAMP comment '做题结束时间',
    group_kind         tinyint not null default 0 comment '题组分类，1：四选一题 2:分类测试 3:寻找测试 0:未分类',
    time_spend_seconds INT comment '做题所耗时间s',
    user_id            int comment '用户id',
    test_action_id     int comment '操作id',
    foreign key (user_id) references user (id)
) comment '用户做题表';
-- 生成分类测试题组信息表
create table classification_test_info
(
    id        int primary key auto_increment comment '分类题组id',
    A_id      int comment '人物A',
    B_id      int comment '人物B',
    test1_id  int comment '测试题1',
    test2_id  int comment '测试题2',
    test3_id  int comment '测试题3',
    test4_id  int comment '测试题4',
    test5_id  int comment '测试题5',
    test6_id  int comment '测试题6',
    test7_id  int comment '测试题7',
    test8_id  int comment '测试题8',
    test9_id  int comment '测试题9',
    test10_id int comment '测试题10',
    status    tinyint not null default 0 comment '状态，0：正常使用 1:禁止使用',
    foreign key (A_id) references test_base_info (id),
    foreign key (B_id) references test_base_info (id),
    foreign key (test1_id) references test_base_info (id),
    foreign key (test2_id) references test_base_info (id),
    foreign key (test3_id) references test_base_info (id),
    foreign key (test4_id) references test_base_info (id),
    foreign key (test5_id) references test_base_info (id),
    foreign key (test6_id) references test_base_info (id),
    foreign key (test7_id) references test_base_info (id),
    foreign key (test8_id) references test_base_info (id),
    foreign key (test9_id) references test_base_info (id),
    foreign key (test10_id) references test_base_info (id)
) comment '分类测试题组信息表';
-- 生成分类测试题目操作表
create table classification_test_action
(
    id       int primary key auto_increment comment '分类题目操作id',
    action1  int not null default 0 comment '操作1',
    action2  int not null default 0 comment '操作2',
    action3  int not null default 0 comment '操作3',
    action4  int not null default 0 comment '操作4',
    action5  int not null default 0 comment '操作5',
    action6  int not null default 0 comment '操作6',
    action7  int not null default 0 comment '操作7',
    action8  int not null default 0 comment '操作8',
    action9  int not null default 0 comment '操作9',
    action10 int not null default 0 comment '操作10'
) comment '分类测试题目操作表';
-- 生成寻找测试题组信息表
create table find_test_info
(
    id        int primary key auto_increment comment '分类题组id',
    target_id int comment '人物id',
    test1_id  int comment '测试题1',
    test2_id  int comment '测试题2',
    test3_id  int comment '测试题3',
    test4_id  int comment '测试题4',
    test5_id  int comment '测试题5',
    test6_id  int comment '测试题6',
    test7_id  int comment '测试题7',
    test8_id  int comment '测试题8',
    status    tinyint not null default 0 comment '状态，0：正常使用 1:禁止使用',
    foreign key (target_id) references test_base_info (id),
    foreign key (test1_id) references test_base_info (id),
    foreign key (test2_id) references test_base_info (id),
    foreign key (test3_id) references test_base_info (id),
    foreign key (test4_id) references test_base_info (id),
    foreign key (test5_id) references test_base_info (id),
    foreign key (test6_id) references test_base_info (id),
    foreign key (test7_id) references test_base_info (id),
    foreign key (test8_id) references test_base_info (id)
) comment '寻找测试题组信息表';
create table find_test_action
(
    id      int primary key auto_increment comment '搜寻题目操作id',
    action1 int not null default 0 comment '操作1',
    action2 int not null default 0 comment '操作2',
    action3 int not null default 0 comment '操作3',
    action4 int not null default 0 comment '操作4',
    action5 int not null default 0 comment '操作5',
    action6 int not null default 0 comment '操作6',
    action7 int not null default 0 comment '操作7',
    action8 int not null default 0 comment '操作8'
) comment '分类测试题目操作表';