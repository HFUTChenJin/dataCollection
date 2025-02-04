use mybatis_plus;
-- 员工
create table if not exists tb_emp
(
    id          int unsigned primary key auto_increment comment 'ID',
    username    varchar(20) not null unique comment '用户名',
    password    varchar(32) default '123456' comment '密码',
    name        varchar(10) not null comment '姓名',
    gender      tinyint unsigned not null comment '性别, 说明: 1 男, 2 女',
    image       varchar(300) comment '图像',
    job         tinyint unsigned comment '职位, 说明: 1 班主任,2 讲师, 3 学工主管, 4 教研主管',
    entrydate   date comment '入职时间',
    dept_id     int unsigned comment '归属的部门ID',
    create_time datetime    not null comment '创建时间',
    update_time datetime    not null comment '修改时间'
) comment '员工表';


# 添加外键
alter table emp
    add constraint emp_dept
        foreign key (dept_id)
            references tb_dept(id);


-- 部门
create table tb_dept
(
    id          int unsigned primary key auto_increment comment 'ID',
    name        varchar(10) not null unique comment '部门名称',
    create_time datetime    not null comment '创建时间',
    update_time datetime    not null comment '修改时间'
) comment '部门表';


-- 插入测试数据
insert into tb_dept (id, name, create_time, update_time)
values (1, '学工部', now(), now()),
       (2, '教研部', now(), now()),
       (3, '咨询部', now(), now()),
       (4, '就业部', now(), now()),
       (5, '人事部', now(), now());

INSERT INTO emp
(id, username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time)
VALUES (1, 'jinyong', '123456', '金庸', 1, '1.jpg', 4, '2000-01-01', 2, now(), now()),
       (2, 'zhangwuji', '123456', '张无忌', 1, '2.jpg', 2, '2015-01-01', 2, now(), now()),
       (3, 'yangxiao', '123456', '杨逍', 1, '3.jpg', 2, '2008-05-01', 2, now(), now()),
       (4, 'weiyixiao', '123456', '韦一笑', 1, '4.jpg', 2, '2007-01-01', 2, now(), now()),
       (5, 'changyuchun', '123456', '常遇春', 1, '5.jpg', 2, '2012-12-05', 2, now(), now()),
       (6, 'xiaozhao', '123456', '小昭', 2, '6.jpg', 3, '2013-09-05', 1, now(), now()),
       (7, 'jixiaofu', '123456', '纪晓芙', 2, '7.jpg', 1, '2005-08-01', 1, now(), now()),
       (8, 'zhouzhiruo', '123456', '周芷若', 2, '8.jpg', 1, '2014-11-09', 1, now(), now()),
       (9, 'dingminjun', '123456', '丁敏君', 2, '9.jpg', 1, '2011-03-11', 1, now(), now()),
       (10, 'zhaomin', '123456', '赵敏', 2, '10.jpg', 1, '2013-09-05', 1, now(), now()),
       (11, 'luzhangke', '123456', '鹿杖客', 1, '11.jpg', 1, '2007-02-01', 1, now(), now()),
       (12, 'hebiweng', '123456', '鹤笔翁', 1, '12.jpg', 1, '2008-08-18', 1, now(), now()),
       (13, 'fangdongbai', '123456', '方东白', 1, '13.jpg', 2, '2012-11-01', 2, now(), now()),
       (14, 'zhangsanfeng', '123456', '张三丰', 1, '14.jpg', 2, '2002-08-01', 2, now(), now()),
       (15, 'yulianzhou', '123456', '俞莲舟', 1, '15.jpg', 2, '2011-05-01', 2, now(), now()),
       (16, 'songyuanqiao', '123456', '宋远桥', 1, '16.jpg', 2, '2010-01-01', 2, now(), now()),
       (17, 'chenyouliang', '123456', '陈友谅', 1, '17.jpg', NULL, '2015-03-21', NULL, now(), now());


-- 一对一: 用户 与 身份证
create table tb_user
(
    id     int unsigned primary key auto_increment comment 'ID',
    name   varchar(10) not null comment '姓名',
    gender tinyint unsigned not null comment '性别, 1 男  2 女',
    phone  char(11) comment '手机号',
    degree varchar(10) comment '学历'
) comment '用户信息表';


insert into tb_user
values (1, '白眉鹰王', 1, '18812340001', '初中'),
       (2, '青翼蝠王', 1, '18812340002', '大专'),
       (3, '金毛狮王', 1, '18812340003', '初中'),
       (4, '紫衫龙王', 2, '18812340004', '硕士');


create table tb_user_card
(
    id           int unsigned primary key auto_increment comment 'ID',
    nationality  varchar(10) not null comment '民族',
    birthday     date        not null comment '生日',
    idcard       char(18)    not null comment '身份证号',
    issued       varchar(20) not null comment '签发机关',
    expire_begin date        not null comment '有效期限-开始',
    expire_end   date comment '有效期限-结束',
    user_id      int unsigned not null unique comment '用户ID',
    constraint fk_user_id foreign key (user_id) references tb_user (id)
) comment '用户信息表';

insert into tb_user_card
values (1, '汉', '1960-11-06', '100000100000100001', '朝阳区公安局', '2000-06-10', null, 1),
       (2, '汉', '1971-11-06', '100000100000100002', '静安区公安局', '2005-06-10', '2025-06-10', 2),
       (3, '汉', '1963-11-06', '100000100000100003', '昌平区公安局', '2006-06-10', null, 3),
       (4, '回', '1980-11-06', '100000100000100004', '海淀区公安局', '2008-06-10', '2028-06-10', 4);


--  多对多: 学生 与 课程
create table tb_student
(
    id   int auto_increment primary key comment '主键ID',
    name varchar(10) comment '姓名',
    no   varchar(10) comment '学号'
) comment '学生表';
insert into tb_student(name, no)
values ('黛绮丝', '2000100101'),
       ('谢逊', '2000100102'),
       ('殷天正', '2000100103'),
       ('韦一笑', '2000100104');


create table tb_course
(
    id   int auto_increment primary key comment '主键ID',
    name varchar(10) comment '课程名称'power_realtime
) comment '课程表';
insert into tb_course (name)
values ('Java'),
       ('PHP'),
       ('MySQL'),
       ('Hadoop');


create table tb_student_course
(
    id         int auto_increment comment '主键' primary key,
    student_id int not null comment '学生ID',
    course_id  int not null comment '课程ID',
    constraint fk_courseid foreign key (course_id) references tb_course (id),
    constraint fk_studentid foreign key (student_id) references tb_student (id)
)comment '学生课程中间表';

insert into tb_student_course(student_id, course_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (2, 2),
       (2, 3),
       (3, 4);



-- 案例
-- 1. 分类表category

-- 2. 菜品表 dish

-- 3. 套餐表 setmeal

-- 4. 套餐菜品关系表 setmeal_dish

-- 分类表
create table category
(
    id          int unsigned primary key auto_increment comment '主键ID',
    name        varchar(20) not null unique comment '分类名称',
    type        tinyint unsigned not null comment '类型 1 菜品分类 2 套餐分类',
    sort        tinyint unsigned not null comment '顺序',
    status      tinyint unsigned not null default 0 comment '状态 0 禁用，1 启用',
    create_time datetime    not null comment '创建时间',
    update_time datetime    not null comment '更新时间'
) comment '分类表';

-- 菜品表
create table dish
(
    id          int unsigned primary key auto_increment comment '主键ID',
    name        varchar(20)   not null unique comment '菜品名称',
    category_id int unsigned not null comment '菜品分类ID',
    price       decimal(8, 2) not null comment '菜品价格',
    image       varchar(300)  not null comment '菜品图片',
    description varchar(200) comment '描述信息',
    status      tinyint unsigned not null default 0 comment '状态, 0 停售 1 起售',
    create_time datetime      not null comment '创建时间',
    update_time datetime      not null comment '更新时间'
) comment '菜品表';


-- 套餐表
create table setmeal
(
    id          int unsigned primary key auto_increment comment '主键ID',
    name        varchar(20)   not null unique comment '套餐名称',
    category_id int unsigned not null comment '分类id',
    price       decimal(8, 2) not null comment '套餐价格',
    image       varchar(300)  not null comment '图片',
    description varchar(200) comment '描述信息',
    status      tinyint unsigned not null default 0 comment '状态 0 停售 1 起售',
    create_time datetime      not null comment '创建时间',
    update_time datetime      not null comment '更新时间'
)comment '套餐';


-- 套餐菜品关联表
create table setmeal_dish
(
    id         int unsigned primary key auto_increment comment '主键ID',
    setmeal_id int unsigned not null comment '套餐id ',
    dish_id    int unsigned not null comment '菜品id',
    copies     tinyint unsigned not null comment '份数'
)comment '套餐菜品关系';

