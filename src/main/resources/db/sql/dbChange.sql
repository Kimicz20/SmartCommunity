use smart_community_config;

alter table parcel add is_received int after receiver;
/* added by Yang, 2014.08.13 */
alter table life add location varchar(50);
/* added by Yang, 2014.08.14 */
alter table user add icon varchar(100);
/*added by Yang, 2014.12.09*/
create table suggestion_history (id int primary key auto_increment, suggestion_id int, status int, last_editor int, time timestamp, last_edit_time timestamp, comment varchar(255));
create table repair_history (id int primary key auto_increment, repair_id int, status int, last_editor int, time timestamp, last_edit_time timestamp, comment varchar(255));
/*added by Yang, 2015.01.12*/
alter table repair add column type int;
update repair set type = 2;
update repair set type = 1 where id between 1 and 10;
/*added by Yang, 2015.01.13*/
alter table user add column phone varchar(20);
update user set sex = '男' where sex is null;
update user set sex = '女' where id between 8 and 10;
update user set role = 6 where id = 22;
update user set role = 6 where id = 18;
/*added by Yang, 2015.01.15*/
alter table repair add column repair_worker_name varchar(20);
alter table repair add column repair_worker_phone varchar(20);
/*added by Yang, 2015.01.20*/
alter table suggestion add column comment varchar(50);
alter table suggestion add column status int;
update suggestion set status = 0;
/*added by Yang, 2015.04.09*/
alter table user modify `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
alter table family modify `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
alter table family modify `texts` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
alter table family_comment modify `texts` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
alter table grocery modify `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
alter table grocery modify `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
alter table grocery modify `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
alter table grocery_feedback modify `texts` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
alter table life modify `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
alter table life modify `intro` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
alter table life modify `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
alter table life modify `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
alter table life modify `discount` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
alter table notification modify `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
alter table notification modify `detail` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
alter table parcel modify `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
alter table phone_number modify `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
alter table product modify `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
alter table product modify `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
alter table repair modify `texts` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
alter table repair modify `room` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
alter table repair modify `contact` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
alter table repair modify `repair_worker_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
alter table repair_history modify `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
alter table suggestion modify `texts` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
alter table suggestion modify `comment` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
alter table suggestion_history modify `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
alter table user modify `room` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
alter table user modify `job` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

/*added by Yang, 2015.07.03*/
CREATE TABLE `advertise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `click_count` int(11) DEFAULT NULL,
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `due_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `type` int(11) DEFAULT NULL,
  `picture` varchar(100) DEFAULT NULL,
  `texts` varchar(100) DEFAULT NULL,
  `video` varchar(100) DEFAULT NULL,
  `responsible_person` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8

CREATE TABLE `advertise_click` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `click_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ad_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

/*added by Yang, 2015.07.06*/
alter table suggestion add column type int;
alter table notification add column type int;

/*added by Yang, 2015.07.07*/
alter table advertise add column hypelink varchar(100);