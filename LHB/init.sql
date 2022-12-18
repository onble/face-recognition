-- 生成职工信息表
create table staff
(
    id       int primary key auto_increment comment '职工id',
    account  varchar(50)  not null unique comment '账号',
    password varchar(200) not null comment '密码',
    status tinyint not null default 0 comment '账号状态，0：正常使用 1:禁止使用 2:刚注册'
) comment '职工信息表';


-- 生成管理员信息表
create table admin
(
    id       int primary key auto_increment comment '管理员id',
    account  varchar(50)  not null unique comment '账号',
    password varchar(200) not null comment '密码',
    name     varchar(50) comment '姓名',
    gender   boolean comment '性别',
    age      tinyint comment '年龄'
) comment '管理员信息表';


-- 生成职务信息表
create table post
(
    id   int primary key auto_increment comment '职务id',
    name varchar(50) comment '职务名称',
    duty varchar(200) comment '职务职责'
) comment '职务信息表';


-- 生成部门信息表
create table department
(
    id        int primary key auto_increment comment '部门id',
    name      varchar(50) not null comment '部门名称',
    home_page varchar(50) comment '部门主页'
) comment '部门信息表';
# TODO:部门信息应该加部门经理字段


-- 生成个人信息表
create table staff_inf
(
    staff_id         int not null comment '职工id',
    constraint fk_staffInf_staff foreign key (staff_id) references staff (id),
    name             varchar(50) comment '姓名',
    age              tinyint comment '年龄',
    phone            varchar(50) comment '电话',
    gender           boolean comment '性别，男1女0',
    is_leader        boolean default false comment '是否为部门领导',
    position_id      int comment '职务id',
    constraint fk_staffInf_position foreign key (position_id) references post(id),
    monthly_salary   double comment '月工资',
    department_id    int comment '所属部门id',
    constraint fk_staffInf_department foreign key (department_id) references department (id),
    birthday         date comment '生日',
    folder_size      double  default 500 comment '文件大小设置',
    occupy_file_size double comment '已使用文件大小'
) comment '个人信息表';


-- 生成待办事项表
create table todo
(
    id       int primary key auto_increment comment '待办事项id',
    staff_id int comment '职工id',
    constraint fk_todo_staff foreign key (staff_id) references staff (id),
    title    varchar(200) comment '事项标题',
    content  varchar(200) comment '事项内容',
    status   boolean default false comment '事项状态'
) comment '待办事项表';


-- 生成名片表
create table carte
(
    carte_id       int primary key auto_increment comment '名片id',
    staff_id       int not null comment '职工id',
    constraint fk_carte_staff foreign key (staff_id) references staff (id),
    title          varchar(50) comment '名称',
    content        varchar(200) comment '自我介绍',
    background_img varchar(50) comment '背景图片',
    avatar_img     varchar(50) comment '头像'
) comment '名片表';


-- 生成日程表
create table schedule
(
    schedule_id int primary key auto_increment comment '日程id',
    title       varchar(50) not null comment '日程标题',
    date        datetime comment '预定日程日期',
    content     varchar(200) comment '日程内容',
    staff_id    int comment '职工id',
    constraint fk_schedule_staff foreign key (staff_id) references staff (id)
) comment '日程表';


-- 生成文件夹信息表
create table folder
(
    id          int primary key auto_increment comment '文件id',
    path        varchar(50) not null comment '文件存放路径',
    staff_id    int         not null comment '所属职工id',
    constraint fk_folder_staff foreign key (staff_id) references staff (id),
    upload_time datetime comment '上传时间',
    name        varchar(50) not null comment '文件名'
) comment '文件夹信息表';
# TODO:应该有文件大小字段


-- 生成会议信息管理表
create table meeting
(
    meeting_id int primary key auto_increment comment '会议id',
    title      varchar(50)  not null comment '会议标题',
    address    varchar(200) not null comment '会议地址信息',
    content    varchar(200) comment '会议内容通知',
    staff_id   int          not null comment '创建职工id',
    constraint fk_meeting_staff foreign key (staff_id) references staff (id),
    start_time datetime     not null comment '会议开始时间',
    stop_time  datetime comment '会议结束时间',
    status tinyint not null default 0 comment '会议状态,0:未开始,1:正在进行,2:已结束'
) comment '会议信息管理表';


-- 生成参会人员表
create table participants
(
    meeting_id int not null comment '会议id',
    constraint fk_participants_meeting foreign key (meeting_id) references meeting (meeting_id),
    staff_id   int not null comment '参会职工id',
    constraint fk_participants_staff foreign key (staff_id) references staff (id)
) comment '参会人员表';


-- 生成在线信息表
create table online
(
    id     int         not null comment '职员id',
    constraint fk_online_staff foreign key (id) references staff (id),
    status varchar(50) not null comment '在线状态'
) comment '在线信息表';


-- 增加职工账号
insert into staff(id, account, password)
values (1, 'xiaoming', '123456'),
       (2, 'xiaohong', '123456'),
       (3, 'tiger', 'tiger'),
       (4, 'Boni', '123456'),
       (5, 'dolly', '123456');


-- 增加 管理员信息
insert into admin(id, account, password, name, gender, age)
values (1, 'admin', 'admin', '超级管理员', true, 1),
       (2, 'test', '123456', '测试账号', false, 0),
       (3, 'root', '123456', '管理员', true, 20);


-- 增加职务信息
insert into post(id, name, duty)
values (default, '前端工程师', '编写前端页面'),
       (2, '后端工程师', '编写后端页面'),
       (3, '测试工程师', '测试项目'),
       (4, '大数据工程师', '编写ppt'),
       (5, '项目经理', '组织开发项目');



-- 增加部门信息
insert into department(id, name, home_page)
values (1, '研发部', '研发部主页'),
       (2, '业务部', '业务部主页'),
       (3, '销售部', '销售部主页'),
       (4, '客服部', '客服部主页');


-- 增加个人信息
insert into staff_inf
(staff_id, name, age, phone, gender, is_leader, position_id, monthly_salary, department_id, birthday, folder_size,
 occupy_file_size)
values (1, '小明', 1, '1234324242', true, true, 1, 7000, 1, '2011-01-01', 500, 0),
       (2, '小红', 18, '432498230948', false, true, 5, 8000, 2, '2002-1-1', 1000, 0),
       (3, '小猫', 2, '123131', false, false, 3, 8000, 3, '2002-1-1', 400, 0),
       (4, '小英', 4, '3242442', true, false, 4, 5000, 4, '2002-5-1', 300, 0);


-- 增加待办事项
insert into todo
    (id, staff_id, title, content, status)
values (1, 1, '准备早会', '早会安排项目进度', false),
       (2, 1, '买水果', '买3个苹果', false),
       (3, 1, '打印材料', 'A4黑白', true);


-- 增加名片
insert into carte(carte_id, staff_id, title, content, background_img, avatar_img)
values (1, 1, '时长两年半练习生', '唱跳rap篮球', '/imgs/背景图片', '/imgs/鸡');


-- 增加日程
insert into schedule(schedule_id, title, date, content, staff_id)
values (1, '统计分数', '2022-12-1', '做excel', 1),
       (2, '学习js', '2022-12-2', '学习基础知识', 1);


-- 增加文件夹信息
insert into folder(id, path, staff_id, upload_time, name)
values (1, 'folder/1.txt', 1, '2022-12-1', '1.txt'),
       (2, 'folder/note.md', 2, '2022-12-1', 'note.md'),
       (3, 'test.py', 3, '2022-12-2', 'test.py');

-- 增加会议信息
insert into meeting(meeting_id, title, address, content, staff_id, start_time, stop_time)
values (1, '开早会', '腾讯会议879879', '分析项目进度', 1, '2022-12-4 8:00', '2022-12-4 8:30'),
       (2, '沟通需求', '腾讯会议90798798', '分析项目需求', 2, '2022-12-15 10:30', '2022-12-15 11:00');


-- 增加参会人员信息
insert into participants(meeting_id, staff_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (2, 4);


-- 增加在线信息
insert into online(id, status)
values (4, '在线'),
       (2, '请勿打扰');