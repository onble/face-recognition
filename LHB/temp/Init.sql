-- 生成client客户表
create table client
(
    idcard char(18) primary key comment '身份证号码',
    name   varchar(20) not null comment '姓名',
    phone  char(11) comment '电话号码'
) comment '客户信息表';


-- 生成room_kind房间种类表
create table room_kind
(
    kind_id        int auto_increment primary key comment '房间种类id',
    deposit        double(7, 2) comment '押金',
    bed            varchar(10) comment '床类型',
    people         tinyint unsigned comment '入住人数',
    casement       char(1) comment '有无窗户',
    original_price double(6, 2) comment '标准价格',
    final_price    double(6, 2) not null comment '现价',
    name           varchar(255) not null comment '房间标题',
    area           tinyint unsigned comment '面积/平方米'
) comment '房间种类信息表';


-- 生成room房间信息表
create table room
(
    room_id   int primary key comment '房间号',
    room_kind int comment '外键，房间种类id',
    constraint fk_room_kind_id foreign key (room_kind) references room_kind (kind_id),
    status    varchar(20) comment '房间状态',
    remark    varchar(255) comment '备注'
) comment '房间信息表';


-- 生成admin管理员表
create table admin
(
    id   int auto_increment primary key comment '员工id',
    name varchar(20) not null comment '姓名',
    dept varchar(20) comment '所属部门'
) comment '管理员信息表';


-- 生成account_admin管理员账号表
create table account_admin
(
    username varchar(20) primary key comment '账号',
    password varchar(20) not null comment '密码',
    admin_id int comment '员工id',
    constraint fk_account_admin_id foreign key (admin_id) references admin (id)
) comment '管理员账户表';


-- 生成订单表
create table orders
(
    id             int auto_increment primary key comment '订单编号',
    order_time     datetime not null comment '下单时间',
    client_id      char(18) comment '客户id',
    constraint fk_orders_client foreign key (client_id) references client (idcard),
    room_kind      int comment '房间类型',
    scheduled_time date     not null comment '入住时间',
    days           int      not null default 1 comment '居住天数',
    spend          double(7, 2) comment '总收入',
    status         tinyint unsigned comment '订单状态'
) comment '订单信息表';


-- 生成入住表
create table distribution
(
    order_id  int comment '订单编号',
    constraint fk_distribution_order foreign key (order_id) references orders (id),
    client_id char(18) comment '客户id',
    constraint fk_distribution_client foreign key (client_id) references client (idcard),
    admin_id  int comment '接待员id',
    constraint fk_distribution_admin foreign key (admin_id) references admin (id),
    room_id   int comment '房间号',
    constraint fk_distribution_room foreign key (room_id) references room (room_id)
) comment '分配信息表';


-- 下面在没有必要的情况下强行增加触发器

-- 总算想出来个合理的触发器
-- 该触发器保护订单数据，保护订单数据的中的固定信息不能被修改
drop trigger if exists protect_order;
# delimiter // -- 这两行感觉没啥意义，注释掉了，正常运行
create trigger protect_order
    before update
    on orders
    for each row
begin
    set NEW.id = OLD.id;
end;
# //

-- 增加管理员
insert into admin(id, name, dept)
values (1, '李昊波', '后台');
insert into admin(id, name, dept)
values (2, '李四', '前台');
insert into admin(id, name, dept)
values (3, '魏治学', '前台');
insert into admin(id, name, dept)
values (4, '陈嘉豪', 'CEO');
insert into admin(id, name, dept)
values (5, '宋华军', '门卫');


-- 增加管理员账号
insert into account_admin(username, password, admin_id)
values ('admin', '123456', 1);
insert into account_admin(username, password, admin_id)
values ('river999', 'priverhe', 2);
insert into account_admin(username, password, admin_id)
values ('leigong', '12345', 2);
insert into account_admin(username, password, admin_id)
values ('chenzhuangyuan', 'wangjie', 2);
insert into account_admin(username, password, admin_id)
values ('Alan', '6crx99tj', 3);
insert into account_admin(username, password, admin_id)
values ('root', '123456', 4);


-- 插入用户信息
insert into client(idcard, name, phone)
VALUES ('370103200203163518', '隋习恺', '18537448960');
insert into client(idcard, name, phone)
VALUES ('371427200205030055', '徐吉豪', '18345448960');
insert into client(idcard, name, phone)
VALUES ('370102200112220022', '李婧卿', '18537448960');
insert into client(idcard, name, phone)
VALUES ('370102200208040027', '周天宇', '18537448960');
insert into client(idcard, name, phone)
VALUES ('370112200202267127', '曹丽娟', '18537448960');
insert into client(idcard, name, phone)
VALUES ('370102200203243714', '邹鸿蔚', '18537448960');
insert into client(idcard, name, phone)
VALUES ('370102200207052536', '葛云龙', '18537448960');
insert into client(idcard, name, phone)
VALUES ('370104200107015810', '赵国皓', '18537448960');
insert into client(idcard, name, phone)
VALUES ('370105200111210324', '陈熙', '18537448960');
insert into client(idcard, name, phone)
VALUES ('370103200201038828', '王雨', '18537448960');
insert into client(idcard, name, phone)
VALUES ('371723200203210088', '袁子涵', '18537448960');
insert into client(idcard, name, phone)
VALUES ('37010320011004881X', '耿世宽', '18537448960');
insert into client(idcard, name, phone)
VALUES ('370112200110237449', '郭欣蕾', '18537448960');
insert into client(idcard, name, phone)
VALUES ('370125200112233822', '侯奕', '18537448960');
insert into client(idcard, name, phone)
VALUES ('370883200112102533', '徐志华', '13789803684');
insert into client(idcard, name, phone)
VALUES ('371427200204060092', '张善卿', '13698646887');
insert into client(idcard, name, phone)
VALUES ('410926200112134413', '许康', '15806632408');
insert into client(idcard, name, phone)
VALUES ('370911200111092023', '刘凯乐', '13854141509');
insert into client(idcard, name, phone)
VALUES ('370112200209097722', '蒋文仙', '13515313812');
insert into client(idcard, name, phone)
VALUES ('371726200108270030', '王鹏程', '13173015213');
insert into client(idcard, name, phone)
VALUES ('370481200211083847', '王淑萍', '15665789899');


-- 定义房间类型
insert into room_kind (kind_id, deposit, bed, people, casement, original_price, final_price, name, area)
values (1, 100, '大床', 2, '有', 138, 96, '大床房', 35);
insert into room_kind (kind_id, deposit, bed, people, casement, original_price, final_price, name, area)
values (2, 100, '双床', 2, '有', 198, 139, '标准间', 30);
insert into room_kind (kind_id, deposit, bed, people, casement, original_price, final_price, name, area)
values (3, 100, '大床', 2, '有', 198, 139, '豪华大床房', 30);
insert into room_kind (kind_id, deposit, bed, people, casement, original_price, final_price, name, area)
values (4, 100, '双床', 2, '有', 238, 166, '豪华标准间', 35);
insert into room_kind (kind_id, deposit, bed, people, casement, original_price, final_price, name, area)
values (5, 100, '双床', 3, '有', 238, 166, '家庭房', 35);
insert into room_kind (kind_id, deposit, bed, people, casement, original_price, final_price, name, area)
values (6, 100, '双床', 3, '有', 278, 172, '豪华家庭房', 35);
insert into room_kind (kind_id, deposit, bed, people, casement, original_price, final_price, name, area)
values (7, 100, '双床', 4, '有', 278, 172, '四人间', 45);
insert into room_kind (kind_id, deposit, bed, people, casement, original_price, final_price, name, area)
values (8, 100, '多床', 3, '有', 298, 185, '豪华三人间', 50);
insert into room_kind (kind_id, deposit, bed, people, casement, original_price, final_price, name, area)
values (9, 100, '多床', 5, '有', 458, 284, '四人间', 99);
insert into room_kind (kind_id, deposit, bed, people, casement, original_price, final_price, name, area)
values (10, 100, '多床', 5, '有', 368, 338, '套间', 57);


-- 插入实际房间的属性
insert into room(room_id, room_kind, status, remark)
VALUES (301, 1, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (302, 1, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (303, 1, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (304, 1, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (305, 1, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (306, 2, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (307, 2, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (308, 2, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (309, 2, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (310, 2, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (401, 3, '空置', '厕所坏了');
insert into room(room_id, room_kind, status, remark)
VALUES (402, 3, '空置', '门坏了');
insert into room(room_id, room_kind, status, remark)
VALUES (403, 4, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (404, 4, '空置', '灯泡坏了');
insert into room(room_id, room_kind, status, remark)
VALUES (405, 5, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (406, 5, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (407, 5, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (408, 6, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (409, 6, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (410, 6, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (501, 7, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (502, 7, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (503, 8, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (504, 8, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (505, 9, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (506, 9, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (507, 9, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (508, 10, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (509, 10, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (510, 10, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (601, 1, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (602, 2, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (603, 3, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (604, 4, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (605, 1, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (606, 2, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (607, 3, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (608, 1, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (609, 2, '空置', '无');
insert into room(room_id, room_kind, status, remark)
VALUES (610, 3, '空置', '无');


-- 初始生成订单
insert into orders(id, order_time, client_id, room_kind, scheduled_time, days, spend, status)
values (1, '2022-6-3-8-20', '370102200112220022', 1, '2022-6-4', 1, 96, 0);
insert into orders(id, order_time, client_id, room_kind, scheduled_time, days, spend, status)
values (2, '2022-6-3-2-1-9', '370103200201038828', 1, '2022-6-4', 1, 296, 0);
insert into orders(id, order_time, client_id, room_kind, scheduled_time, days, spend, status)
values (3, '2022-6-3-13-2-23', '370105200111210324', 1, '2022-6-5', 3, 198, 0);
insert into orders(id, order_time, client_id, room_kind, scheduled_time, days, spend, status)
values (4, '2022-6-3-1-1-1', '370112200110237449', 1, '2022-6-4', 3, 198, 0);
insert into orders(id, order_time, client_id, room_kind, scheduled_time, days, spend, status)
values (5, '2022-6-3', '370112200110237449', 1, '2022-6-5', 3, 198, 0);
insert into orders(id, order_time, client_id, room_kind, scheduled_time, days, spend, status)
values (6, '2022-6-3', '370103200203163518', 2, '2022-6-3', 1, 270, 0);
insert into orders(id, order_time, client_id, room_kind, scheduled_time, days, spend, status)
values (7, '2022-6-3', '370102200208040027', 1, '2022-6-4', 5, 238, 0);
insert into orders(id, order_time, client_id, room_kind, scheduled_time, days, spend, status)
values (8, '2022-6-3', '370104200107015810', 1, '2022-6-4', 8, 298, 0);
insert into orders(id, order_time, client_id, room_kind, scheduled_time, days, spend, status)
values (9, '2022-6-3', '370102200112220022', 1, '2022-6-4', 1, 96, 0);
insert into orders(id, order_time, client_id, room_kind, scheduled_time, days, spend, status)
values (10, '2022-6-3', '370102200112220022', 1, '2022-6-4', 1, 96, 0);
insert into orders(id, order_time, client_id, room_kind, scheduled_time, days, spend, status)
values (11, '2022-6-3', '370102200112220022', 1, '2022-6-4', 1, 96, 0);
insert into orders(id, order_time, client_id, room_kind, scheduled_time, days, spend, status)
values (12, '2022-6-7-3-1', '370883200112102533', 1, '2022-6-8', 1, 96, 0);
insert into orders(id, order_time, client_id, room_kind, scheduled_time, days, spend, status)
values (13, '2022-6-6-3-1', '371427200204060092', 1, '2022-6-7', 1, 96, 0);
insert into orders(id, order_time, client_id, room_kind, scheduled_time, days, spend, status)
values (14, '2022-6-7-13-1', '410926200112134413', 1, '2022-6-8', 1, 96, 0);
insert into orders(id, order_time, client_id, room_kind, scheduled_time, days, spend, status)
values (15, '2022-6-7-9-1', '370911200111092023', 2, '2022-6-8', 1, 139, 0);
insert into orders(id, order_time, client_id, room_kind, scheduled_time, days, spend, status)
values (16, '2022-6-7-19-11', '370112200209097722', 3, '2022-6-8', 1, 139, 0);
insert into orders(id, order_time, client_id, room_kind, scheduled_time, days, spend, status)
values (17, '2022-6-7-22-11', '371726200108270030', 3, '2022-6-8', 1, 139, 0);
insert into orders(id, order_time, client_id, room_kind, scheduled_time, days, spend, status)
values (18, '2022-6-7-23-11', '370481200211083847', 3, '2022-6-10', 1, 139, 0);


-- 生成接待表
insert into distribution(order_id, client_id, admin_id, room_id)
values (1, '370103200201038828', 1, 303);
insert into distribution(order_id, client_id, admin_id, room_id)
values (1, '370105200111210324', 1, 303);
insert into distribution(order_id, client_id, admin_id, room_id)
values (8, '370104200107015810', 2, 503);
insert into distribution(order_id, client_id, admin_id, room_id)
values (8, '370102200207052536', 2, 503);
insert into distribution(order_id, client_id, admin_id, room_id)
values (8, '370102200203243714', 2, 503);
insert into distribution(order_id, client_id, admin_id, room_id)
values (2, '370103200201038828', 3, 305);