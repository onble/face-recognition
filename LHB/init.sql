-- 生成职工信息表
create table staff
(
    id       int primary key auto_increment comment '职工id',
    account  varchar(50)  not null unique comment '账号',
    password varchar(200) not null comment '密码',
    status   tinyint      not null default 0 comment '账号状态，0：正常使用 1:禁止使用 2:刚注册'
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
    is_leader        boolean      default false comment '是否为部门领导',
    position_id      int comment '职务id',
    constraint fk_staffInf_position foreign key (position_id) references post (id),
    monthly_salary   double comment '月工资',
    department_id    int comment '所属部门id',
    constraint fk_staffInf_department foreign key (department_id) references department (id),
    birthday         date comment '生日',
    folder_size      double       default 500 comment '文件大小设置',
    occupy_file_size double comment '已使用文件大小',
    header_file      varchar(200) default './images/default.jpg' comment '头像文件'
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
    path        varchar(200) not null comment '文件存放路径',
    staff_id    int          not null comment '所属职工id',
    constraint fk_folder_staff foreign key (staff_id) references staff (id),
    upload_time datetime comment '上传时间',
    name        varchar(50)  not null comment '文件名',
    size        bigint       not null default 0 comment '文件大小'
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
    status     tinyint      not null default 0 comment '会议状态,0:未开始,1:正在进行,2:已结束'
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
       (5, 'Mark', '123456'),
       (6, 'Baka', '123456'),
       (7, 'CC', '123456'),
       (8, 'GG', '123456'),
       (9, 'CX', '123456'),
       (10, 'LHB', '123456'),
       (11, 'Daily', '123456'),
       (12, 'Tour', '123456');


-- 增加 管理员信息
insert into admin(id, account, password, name, gender, age)
values (1, 'admin', 'admin', '超级管理员', true, 34),
       (2, 'test', '123456', '测试账号', false, 30),
       (3, 'root', '123456', '管理员', true, 20),
       (4, 'root2', '123456', '管理员2号', true, 21),
       (5, 'root3', '123456', '管理员2号', true, 24),
       (6, 'root4', '123456', '管理员3号', false, 24),
       (7, 'root5', '123456', '管理员4号', true, 24),
       (8, 'root6', '123456', '管理员5号', true, 23),
       (9, 'root7', '123456', '管理员6号', true, 25),
       (10, 'root8', '123456', '管理员7号', false, 24),
       (11, 'root9', '123456', '管理员8号', true, 23),
       (12, 'root0', '123456', '管理员9号', true, 21);


-- 增加职务信息
insert into post(id, name, duty)
values (default, '前端工程师', '编写前端页面'),
       (2, '后端工程师', '编写后端页面'),
       (3, '测试工程师', '测试项目'),
       (4, 'JAVA工程师', '编写JAVA'),
       (5, '项目经理', '组织开发项目'),
       (6, '数据库工程师', '编写数据库'),
       (7, '系统分析师', '对系统分析'),
       (8, '网页设计师', '设计网页'),
       (9, '动画制作师', '制作动画'),
       (10, '需求分析师', '分析需求'),
       (11, '系统运维工程师', '运行维护系统'),
       (12, '算法工程师', '算法');




-- 增加部门信息
insert into department(id, name, home_page)
values (1, '研发部', '研发部主页'),
       (2, '业务部', '业务部主页'),
       (3, '销售部', '销售部主页'),
       (4, '客服部', '客服部主页'),
       (5, '运营部', '运营部主页'),
       (6, '技术部', '技术部主页'),
       (7, '客户服务部', '客户服务部主页'),
       (8, '财务部', '财务部主页'),
       (9, '人事部', '人事部主页'),
       (10, '后勤部', '后勤部主页'),
       (11, '营销部', '营销部主页'),
       (12, '行政部', '行政部主页');


-- 增加个人信息
insert into staff_inf
(staff_id, name, age, phone, gender, is_leader, position_id, monthly_salary, department_id, birthday, folder_size,
 occupy_file_size)
values (1, '小明', 1, '1234324242', true, true, 1, 7000, 1, '2011-01-01', 500, 0),
       (2, '小红', 18, '432498230948', false, true, 5, 8000, 2, '2002-1-1', 1000, 0),
       (3, '小猫', 2, '123131', false, false, 3, 8000, 3, '2002-1-1', 400, 0),
       (4, '小英', 4, '3242442', true, false, 4, 5000, 4, '2005-3-5', 300, 0),
       (5, '阿音', 1, '125468947', true, true, 1, 7000, 1, '2001-01-01', 700, 0),
       (6, '文文', 18, '789544563', false, true, 5, 8000, 2, '2003-1-1', 800, 0),
       (7, '张张', 2, '7077621', false, false, 3, 8000, 3, '2004-1-13', 900, 0),
       (8, '锅锅', 4, '70957656', true, false, 4, 5000, 4, '2003-3-23', 400, 0),
       (9, '安', 1, '143324314', true, true, 1, 7000, 1, '2011-08-9', 300, 0),
       (10, '小苏', 18, '9709706', false, true, 5, 8000, 2, '2004-7-8', 700, 0),
       (11, '阿俞', 2, '99999991', false, false, 3, 8000, 3, '2002-6-6', 400, 0),
       (12, '腻腻', 4, '98797083', true, false, 4, 5000, 4, '2002-6-18', 300, 0);


-- 增加待办事项
insert into todo
    (id, staff_id, title, content, status)
values (1, 1, '准备早会', '早会安排项目进度', false),
       (2, 1, '买水果', '买3个苹果', false),
       (3, 1, '打印材料', 'A4黑白', true),
       (4, 1, '购买办公用具', '笔没油了', false),
       (5, 1, '交日报', '写当天进度', false),
       (6, 1, '交周报', '写本周进度', true),
       (7, 1, '与客户见面', '交流项目', false),
       (8, 1, '修改BUG', 'BUG还没改完', false),
       (9, 1, '开会', '再开个会', true),
       (10, 1, '订餐', '中午订餐', true),
       (11, 1, '通知', '通知同事这周有例会', false),
       (12, 1, '计划', '计划下周任务', false);


-- 增加名片
insert into carte(carte_id, staff_id, title, content, background_img, avatar_img)
values (1, 1, '时长两年半练习生', '唱跳rap篮球', '/imgs/背景图片', '/imgs/鸡'),
       (2, 1, '在岗十年', '跑步', '/imgs/背景图片', '/imgs/鸡'),
       (3, 1, '应届生', '唱歌', '/imgs/背景图片', '/imgs/鸡'),
       (4, 1, '应届生', '看剧', '/imgs/背景图片', '/imgs/鸡'),
       (5, 1, '应届生', '打代码', '/imgs/背景图片', '/imgs/鸡'),
       (6, 1, '应届生', '尬聊王者玩家', '/imgs/背景图片', '/imgs/鸡'),
       (7, 1, '应届生', '打游戏', '/imgs/背景图片', '/imgs/鸡'),
       (8, 1, '应届生', '思考人生', '/imgs/背景图片', '/imgs/鸡'),
       (9, 1, '应届生', '国家一级抬杠运动员', '/imgs/背景图片', '/imgs/鸡'),
       (10, 1, '应届生', '退役熬夜选手', '/imgs/背景图片', '/imgs/鸡'),
       (11, 1, '应届生', '互联网冲浪金牌选手', '/imgs/背景图片', '/imgs/鸡'),
       (12, 1, '应届生', '发呆业务爱好者', '/imgs/背景图片', '/imgs/鸡');


-- 增加日程
insert into schedule(schedule_id, title, date, content, staff_id)
values (1, '统计分数', '2022-12-1', '做excel', 1),
       (2, '学习js', '2022-12-2', '学习基础知识', 1),
       (3, '学习Java', '2022-12-3', '学习基础知识', 1),
       (4, '学习前端', '2022-12-4', '学习基础知识', 1),
       (5, '学习高数', '2022-12-5', '学习基础知识', 1),
       (6, '学习金融', '2022-12-6', '学习基础知识', 1),
       (7, '反思总结', '2022-12-7', '总结', 1),
       (8, '日报阿日报', '2022-12-8', '记得写日报', 1),
       (9, '学习js', '2022-12-9', '学习基础知识', 1),
       (10, '学习数据库', '2022-12-10', '学习基础知识', 1),
       (11, '学习c++', '2022-12-11', '学习基础知识', 1),
       (12, '学习web', '2022-12-12', '学习基础知识', 1);


-- 增加文件夹信息
insert into folder(id, path, staff_id, upload_time, name, size)
values (1, './folder/default.txt', 1, '2022-12-1', '1.txt', 1),
       (2, './folder/default.txt', 2, '2022-12-1', 'note.md', 1),
       (3, './folder/default.txt', 3, '2022-12-2', 'test.py', 1),
       (4, './folder/default.txt', 1, '2022-12-1', '2.txt', 1),
       (5, './folder/default.txt', 1, '2022-12-1', '3.txt', 1),
       (6, './folder/default.txt', 1, '2022-12-1', '4.txt', 1),
       (7, './folder/default.txt', 1, '2022-12-1', '5.txt', 1),
       (8, './folder/default.txt', 1, '2022-12-1', '6.txt', 1),
       (9, './folder/default.txt', 1, '2022-12-1', '7.txt', 1),
       (10, './folder/default.txt', 1, '2022-12-1', '8.txt', 1),
       (11, './folder/default.txt', 1, '2022-12-1', '9.txt', 1),
       (12, './folder/default.txt', 1, '2022-12-1', '10.txt', 1),
       (13, './folder/default.txt', 1, '2022-12-1', '11.txt', 1),
       (14, './folder/default.txt', 1, '2022-12-1', '12.txt', 1),
       (15, './folder/default.txt', 1, '2022-12-1', '13.txt', 1),
       (16, './folder/default.txt', 1, '2022-12-1', '14.txt', 1),
       (17, './folder/default.txt', 1, '2022-12-1', '15.txt', 1),
       (18, './folder/default.txt', 1, '2022-12-1', '16.txt', 1),
       (19, './folder/default.txt', 1, '2022-12-1', '17.txt', 1),
       (20, './folder/default.txt', 1, '2022-12-1', '18.txt', 1),
       (21, './folder/default.txt', 1, '2022-12-1', '19.txt', 1),
       (22, './folder/default.txt', 1, '2022-12-1', '20.txt', 1),
       (23, './folder/default.txt', 1, '2022-12-1', '21.txt', 1);

-- 增加会议信息
insert into meeting(meeting_id, title, address, content, staff_id, start_time, stop_time)
values (1, '开早会', '腾讯会议879879', '分析项目进度', 1, '2022-12-4 8:00', '2022-12-4 8:30'),
       (2, '沟通需求', '腾讯会议90798798', '分析项目需求', 2, '2022-12-15 10:30', '2022-12-15 11:00'),
       (3, '交流进程', '腾讯会议879879', '分析项目进度', 1, '2022-12-4 8:00', '2022-12-4 8:30'),
       (4, '未来计划', '腾讯会议90798798', '分析项目需求', 2, '2022-12-15 10:30', '2022-12-15 11:00'),
       (5, '讨论', '腾讯会议879879', '分析项目进度', 1, '2022-12-4 8:00', '2022-12-4 8:30'),
       (6, '开会', '腾讯会议879879', '分析项目进度', 1, '2022-12-4 8:00', '2022-12-4 8:30'),
       (7, '开早会', '腾讯会议90798798', '分析项目需求', 2, '2022-12-15 10:30', '2022-12-15 11:00'),
       (8, '沟通顾客需求', '腾讯会议90798798', '分析项目需求', 2, '2022-12-15 10:30', '2022-12-15 11:00'),
       (9, '讨论', '腾讯会议879879', '分析项目进度', 1, '2022-12-4 8:00', '2022-12-4 8:30'),
       (10, '沟通需求', '腾讯会议879879', '分析项目进度', 1, '2022-12-4 8:00', '2022-12-4 8:30'),
       (11, '开会', '腾讯会议90798798', '分析项目需求', 2, '2022-12-15 10:30', '2022-12-15 11:00'),
       (12, '沟通需求', '腾讯会议90798798', '分析项目需求', 2, '2022-12-15 10:30', '2022-12-15 11:00');




-- 增加参会人员信息
insert into participants(meeting_id, staff_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (2, 5),
       (2, 4),
       (1, 12),
       (2, 11),
       (1, 6);



-- 增加在线信息
insert into online(id, status)
values (4, '在线'),
       (2, '请勿打扰'),
       (5, '在线'),
       (6, '请勿打扰'),
       (4, '在线'),
       (7, '请勿打扰'),
       (8, '在线'),
       (9, '请勿打扰'),
       (11, '在线'),
       (2, '请勿打扰'),
       (10, '在线'),
       (12, '请勿打扰');