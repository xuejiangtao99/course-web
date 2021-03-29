-- 大章
drop table if exists `chapter`;
create table `chapter` (
                           `id` char(8) not null comment 'id',
                           `course_id` char(8) comment '课程id',
                           `name` varchar(50) comment '名称',
                           primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='大章';


-- 小节
drop table if exists `section`;
create table `section` (
                           `id` char(8) not null default '' comment 'id',
                           `title` varchar(50) not null comment '标题',
                           `course_id` char(8) comment '课程|course.id',
                           `chapter_id` char(8) comment '大章|chapter.id',
                           `video` varchar(200) comment '视频',
                           `time` int comment '时长|单位秒',
                           `charge` char(1) comment '收费|C 收费；F 免费',
                           `sort` int comment '顺序',
                           `created_at` datetime(3) comment '创建时间',
                           `updated_at` datetime(3) comment '修改时间',
                           primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='小节';

alter table `section` add column (`vod` char(32) comment 'vod|阿里云vod');

insert into `section` (id, title, course_id, chapter_id, video, time, charge, sort, created_at, updated_at)
values ('00000001', '测试小节01', '00000001', '00000000', '', 500, 'f', 1, now(), now());


-- 课程
drop table if exists course;
create table course (
                        id char(8) not null default '' comment 'id',
                        name varchar(50) not null comment '名称',
                        summary varchar(2000) comment '概述',
                        time int default 0 comment '时长|单位秒',
                        price decimal(8,2) default 0.00 comment '价格（元）',
                        image varchar(100) comment '封面',
                        level char(1) comment '级别|枚举[CourseLevelEnum]：ONE("1", "初级"),TWO("2", "中级"),THREE("3", "高级")',
                        charge char(1) comment '收费|枚举[CourseChargeEnum]：CHARGE("C", "收费"),FREE("F", "免费")',
                        status char(1) comment '状态|枚举[CourseStatusEnum]：PUBLISH("P", "发布"),DRAFT("D", "草稿")',
                        enroll integer default 0 comment '报名数',
                        sort int comment '顺序',
                        created_at datetime(3) comment '创建时间',
                        updated_at datetime(3) comment '修改时间',
                        primary key (id)
) engine=innodb default charset=utf8mb4 comment='课程';
insert into course (id, name, summary, time, price, image, level, charge, status, enroll, sort, created_at, updated_at)
values ('00000001', '测试课程01', '这是一门测试课程', 7200, 19.9, '', 1, 'C', 'P', 100, 0, now(), now());


drop table if exists `user`;
create table `user` (
                        `id` char(8) not null default '' comment 'id',
                        `login_name` varchar(50) not null comment '登陆名',
                        `name` varchar(50) comment '昵称',
                        `password` char(32) not null comment '密码',
                        primary key (`id`),
                        unique key `login_name_unique` (`login_name`)
) engine=innodb default charset=utf8mb4 comment='用户';
# 初始test/test
insert into `user` (id, login_name, name, password) values ('10000000', 'test', '测试', 'e70e2222a9d67c4f2eae107533359aa4');
