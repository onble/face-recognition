-- 生成职工信息表
create table staff
(
    id int primary key auto_increment comment '职工id',
    account varchar(50) not null unique comment '账号',
    password varchar(200) not null comment '密码'
) comment '职工信息表';


-- 生成管理员信息表
create table admin
(
    id int primary key auto_increment comment '管理员id',
    account varchar(50) not null unique comment '账号',
    password varchar(200) not null comment '密码',
    name varchar(50) comment '姓名',
    gender boolean comment '性别',
    age tinyint comment '年龄'
) comment '管理员信息表';


-- 生成职务信息表


-- 生成个人信息表
create table staff_inf
(
    staff_id int comment '职工id',
    constraint fk_staffInf_staff foreign key (staff_id) references staff(id),
    name varchar(50) comment '姓名',
    age tinyint comment '年龄',
    phone varchar(50) comment '电话',
    gender boolean comment '性别，男1女0',
    is_leader boolean default false comment '是否为部门领导',
    position_inf int comment '职务id',
#     constraint fk_staffInf_ TODO:这里的外键约束没有写
    monthly_salary double comment '月工资',
    department_id int comment '所属部门id',
    birthday date comment '生日',
    folder_size double default 500 comment '文件大小设置',
    occupy_file_size double comment '已使用文件大小'
) comment '个人信息表';


-- 生成待办信息表
create table todo
(
    id int primary key auto_increment comment '待办事项id',
    staff_id int comment '职工id',
    constraint fk_todo_staff foreign key (staff_id) references staff(id),
    title varchar(200) comment '事项标题',
    content varchar(200) comment '事项内容',
    status boolean default false comment '事项状态'
) comment '待办信息表';
