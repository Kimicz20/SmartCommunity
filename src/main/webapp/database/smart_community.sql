-- MySQL dump 10.13  Distrib 5.6.24, for FreeBSD10.1 (amd64)
--
-- Host: localhost    Database: smart_community
-- ------------------------------------------------------
-- Server version	5.6.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `advertise`
--

DROP TABLE IF EXISTS `advertise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  `hypelink` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertise`
--

LOCK TABLES `advertise` WRITE;
/*!40000 ALTER TABLE `advertise` DISABLE KEYS */;
INSERT INTO `advertise` VALUES (1,2,'2015-06-30 16:00:00','2017-06-29 16:00:00',2,NULL,NULL,NULL,3,NULL),(2,2,'2015-04-30 16:00:00','2017-04-29 16:00:00',2,NULL,NULL,NULL,4,NULL),(3,NULL,'2015-07-07 00:00:00','2016-07-08 00:00:00',1,'292ec37c-59d2-439a-80b5-12b4af7fb3c5.jpg','可口可乐',NULL,34,'http://www.duckduckgo.com'),(4,NULL,'2015-07-07 00:00:00','2015-07-31 00:00:00',3,'979c94ab-27cc-41ea-b5c7-4ac6f0092257.jpg','2015文艺汇演',NULL,31,NULL),(5,NULL,'2015-07-07 00:00:00','2015-08-04 00:00:00',3,'8c3b0d28-c533-4646-bed0-86024a82b18a.jpg','妙手回春中药堂',NULL,30,'http://www.baidu.com');
/*!40000 ALTER TABLE `advertise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `advertise_click`
--

DROP TABLE IF EXISTS `advertise_click`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertise_click` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `click_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ad_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertise_click`
--

LOCK TABLES `advertise_click` WRITE;
/*!40000 ALTER TABLE `advertise_click` DISABLE KEYS */;
INSERT INTO `advertise_click` VALUES (1,'2015-07-08 03:17:43',3,1),(2,'2015-07-08 03:20:01',3,1);
/*!40000 ALTER TABLE `advertise_click` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `family`
--

DROP TABLE IF EXISTS `family`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `family` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `type` int(11) DEFAULT NULL,
  `texts` varchar(500) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `last_editor` int(11) DEFAULT NULL,
  `last_edit_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `picture_one` varchar(50) DEFAULT NULL,
  `picture_two` varchar(50) DEFAULT NULL,
  `picture_three` varchar(50) DEFAULT NULL,
  `popularity` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=188 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `family`
--

LOCK TABLES `family` WRITE;
/*!40000 ALTER TABLE `family` DISABLE KEYS */;
INSERT INTO `family` VALUES (1,'有没有一起看球的啊','2014-07-14 02:13:59',2,'欧冠决赛唉，大爱C罗啊，有没有一起看的啊～',1,1,'2014-07-14 02:07:50','d7ad453d-081f-4836-a893-aad9e188e6cc.png','5f5f3be8-b743-4527-8927-792cfca4832a.png','eb9c160b-39b8-457d-8baa-544098817cf4.png',2),(2,'六一儿童节期间的活动','2014-08-01 03:06:37',1,'六一儿童节期间，小区将举办多种多样的精彩活动，包括武术表演，亲子小游戏等，欢迎大家参加！',1,1,'2014-08-01 03:06:37',NULL,NULL,NULL,0),(6,'小区跳蚤市场正式上线了','2014-08-01 07:23:57',1,'小区跳蚤市场正式上线了，有闲置物品的赶快上去晒晒吧！',1,1,'2014-08-01 07:23:57',NULL,NULL,NULL,0),(7,'冯博士','2014-08-18 07:16:08',1,'下午大家一起去看俊俊吧?有没有人一起的?',1,1,'2014-08-18 07:16:08','af336e19-e8eb-4ab1-b854-c72d1e8a9cf3.png',NULL,NULL,0),(8,'高大神','2014-08-18 07:29:47',2,'高大神,明天开会靠你来讲了!!![给力]',1,1,'2014-08-18 07:29:47','f8ad5790-ce6e-42e4-a5d6-f6c8a4f23f1b.png',NULL,NULL,0),(9,'马博士','2014-08-18 08:16:28',2,'马博士',1,1,'2014-08-18 08:16:28',NULL,NULL,NULL,0),(10,'9成新iPhone便宜卖','2014-08-18 08:30:05',3,'9成新iPhone便宜卖',1,1,'2014-08-18 08:30:05',NULL,NULL,NULL,0),(11,'周末钓鱼','2014-08-18 08:31:15',4,'周末钓鱼',1,1,'2014-08-18 08:31:15',NULL,NULL,NULL,0),(12,'唱歌','2014-08-18 08:35:42',4,'唱歌',1,1,'2014-08-18 08:35:42',NULL,NULL,NULL,0),(13,'吃饭','2014-08-18 08:36:57',4,'吃饭',1,1,'2014-08-18 08:36:57',NULL,NULL,NULL,0),(14,'睡觉','2014-08-18 08:38:26',4,'睡觉',1,1,'2014-08-18 08:38:26',NULL,NULL,NULL,0),(161,'11月“亲子活动日”马上就要开始了','2014-11-27 02:17:54',1,'11月“亲子活动日”马上就要开始了！欢迎大家的参加！',1,1,'2014-11-27 02:17:54',NULL,NULL,NULL,0),(162,'1223','2014-11-28 07:00:14',2,'1233',12,12,'2014-11-28 07:00:14',NULL,NULL,NULL,0),(163,'4553','2014-11-28 07:05:55',2,'88889',12,12,'2014-11-28 07:05:55',NULL,NULL,NULL,0),(164,'666','2014-11-28 07:53:44',2,'3',12,12,'2014-11-28 07:53:44','18f2d4c6-0bbc-4063-9e78-6f9468e35e51.jpg',NULL,NULL,0),(165,'45666','2014-11-28 07:56:59',2,'666',12,12,'2014-11-28 07:56:59','9ef3631f-cf82-49cf-b6fe-78e9387f0d45.jpg',NULL,NULL,0),(166,'99999','2014-11-28 08:08:35',2,'99999',12,12,'2014-11-28 08:08:35','3b615107-5a06-40e1-a992-f3f046494685.jpg',NULL,NULL,0),(167,'222','2014-11-28 11:20:16',4,'ggv',12,12,'2014-11-28 11:20:16','66a486d7-1c8c-471d-a9b5-468ea21c3d7d.jpg',NULL,NULL,0),(168,'123','2014-12-11 12:40:12',4,'123',12,12,'2014-12-11 12:40:12','6ae4f294-a58b-482a-9e96-5c90e216e836.jpg',NULL,NULL,0),(169,'fyhh','2014-12-22 07:01:57',3,'dyuu',12,12,'2014-12-22 07:01:57','79e23a3e-8840-409a-b78f-afc98f7425a6.jpg',NULL,NULL,0),(170,'12','2014-12-30 12:08:12',2,'12',21,21,'2014-12-30 12:08:12','ab37c919-ddff-4a4a-b1e1-596cc33748da.jpg',NULL,NULL,0),(171,'12','2014-12-30 12:09:12',4,'1',21,21,'2014-12-30 12:09:12','e8944182-bd86-469e-acd8-ca9793dacd96.jpg',NULL,NULL,0),(172,'12','2014-12-30 12:18:36',3,'12',23,23,'2014-12-30 12:18:36','6a5e746d-73c5-4dcd-a726-ebed9ea48e57.jpg',NULL,NULL,0),(173,'a','2014-12-30 12:33:03',1,'q',23,23,'2014-12-30 12:33:03','db786942-3626-4215-a6e7-8a30347696de.jpg',NULL,NULL,0),(174,'698','2014-12-30 12:33:31',1,'635',23,23,'2014-12-30 12:33:31','55093efc-138c-4729-b58f-c2c8c8d2da68.jpg',NULL,NULL,0),(175,'159','2014-12-30 12:34:17',4,'698',23,23,'2014-12-30 12:34:17','92e22f43-85a4-489a-8eb7-1d7ee1093770.jpg',NULL,NULL,0),(176,'223','2015-01-14 09:45:18',1,'223',23,23,'2015-01-14 09:45:18','0d25e472-90be-466f-ad1a-ed68de075988.png',NULL,NULL,0),(177,'156','2015-01-14 09:50:26',1,'156',23,23,'2015-01-14 09:50:26','f3affb8c-e169-4b3b-9273-6535980aedad.png',NULL,NULL,0),(178,'268','2015-01-14 09:55:35',2,'268',23,23,'2015-01-14 09:55:35','9a396ddd-ad21-4b88-be09-8921028d0600.png',NULL,NULL,0),(179,'ttttttttttttt','2015-01-19 09:11:21',3,'ttttttttttttt',1,1,'2015-01-19 09:11:21',NULL,NULL,NULL,0),(180,'556','2015-01-21 05:56:33',2,'5566',23,23,'2015-01-21 05:56:33','f1b21927-d532-44df-9876-68a0fd138f36.png',NULL,NULL,0),(181,'42333','2015-01-21 07:27:27',3,'53333',23,23,'2015-01-21 07:27:27','91a413e4-987a-4bae-b7fa-ac33e5e6a023.png',NULL,NULL,0),(182,'4666','2015-01-22 12:07:02',2,'75566',23,23,'2015-01-22 12:07:02','b3b42364-1b9e-4dc2-8169-80a1b3b450f5.png',NULL,NULL,0),(183,'明天打牌的有木有','2015-01-22 12:23:20',4,'21号要打牌的来报名啦',27,27,'2015-01-22 12:23:20','bb9dc4a0-a4e9-4f2b-8edb-3cf69c6cd930.png',NULL,NULL,0),(184,'1234567890','2015-01-28 07:30:32',4,'1234567890',30,30,'2015-01-28 07:30:32',NULL,NULL,NULL,0),(185,'abcdefg','2015-02-01 12:29:18',4,'abcdefg',30,30,'2015-02-01 12:29:18',NULL,NULL,NULL,0),(186,'前排出售一堆苹果设备','2015-04-01 11:33:17',3,'前排出售一堆苹果设备',30,30,'2015-04-01 11:33:17',NULL,NULL,NULL,0),(187,'偷偷卖苹果设备啦','2015-04-01 11:40:38',3,'有很多设备，低调出来。有意来603面谈[爱你][爱你]',30,30,'2015-04-01 11:40:38',NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `family` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `family_comment`
--

DROP TABLE IF EXISTS `family_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `family_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `thread` int(11) DEFAULT NULL,
  `texts` varchar(500) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `last_editor` int(11) DEFAULT NULL,
  `last_edit_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=227 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `family_comment`
--

LOCK TABLES `family_comment` WRITE;
/*!40000 ALTER TABLE `family_comment` DISABLE KEYS */;
INSERT INTO `family_comment` VALUES (1,1,'沙发，看我迪马利亚如何虐科斯塔，呵呵',1,1,'2014-07-14 02:19:59',NULL),(2,1,'我[嘻嘻]',1,1,'2014-08-18 03:00:58',NULL),(3,1,'e[熊猫][熊猫][熊猫][熊猫][熊猫]',1,1,'2014-08-18 03:02:53',NULL),(4,1,'e[熊猫][熊猫][熊猫][熊猫][熊猫]',1,1,'2014-08-18 03:03:03',NULL),(5,1,'突突突[嘻嘻]',1,1,'2014-08-18 03:04:05',NULL),(6,1,'www[弱]',1,1,'2014-08-18 03:05:40',NULL),(7,1,'吞吞吐吐[花心]',1,1,'2014-08-18 03:07:20',NULL),(8,1,'请求权[浮云]',1,1,'2014-08-18 03:08:10',NULL),(9,1,'去你的',1,1,'2014-08-18 03:09:16',NULL),(10,1,'饿',1,1,'2014-08-18 03:10:53',NULL),(11,8,'Rrrrr',1,1,'2014-08-18 08:05:33',NULL),(12,1,'饿饿饿',1,1,'2014-08-19 05:05:03',NULL),(13,1,'饿饿饿',1,1,'2014-08-19 05:05:21',NULL),(14,1,'仍然而',1,1,'2014-08-19 05:07:12',NULL),(15,1,'Tatty',1,1,'2014-08-19 06:22:03',NULL),(16,2,'13',1,1,'2014-08-28 02:44:21',NULL),(17,23,'Zhende me?',1,1,'2014-08-29 07:58:49',NULL),(18,1,'怎么，你们不想看了？',1,1,'2014-09-17 11:31:34',NULL),(19,1,'科斯塔上不了了……',1,1,'2014-09-17 11:36:25',NULL),(20,1,'楼上，科斯塔上了，打脸了吧……',1,1,'2014-09-17 13:56:35',NULL),(21,1,'肯了了',1,1,'2014-09-25 13:36:05',NULL),(22,1,'看看',1,1,'2014-09-25 13:37:09',NULL),(23,1,'看见',1,1,'2014-09-25 14:03:47',NULL),(24,1,'咯了咯',1,1,'2014-09-25 14:07:13',NULL),(25,1,'写了啊',1,1,'2014-09-25 14:10:29',NULL),(26,1,'他咯哦了',1,1,'2014-09-25 14:10:47',NULL),(35,1,'咯图咯',1,1,'2014-09-25 14:14:37',NULL),(36,99,'halsllo',1,1,'2014-10-13 07:29:14',NULL),(37,99,'浏览器',1,1,'2014-10-13 07:37:12',NULL),(38,99,'是',1,1,'2014-10-13 07:40:57',NULL),(39,12,'同',1,1,'2014-10-13 07:44:53',NULL),(40,11,'我去',1,1,'2014-10-13 07:56:48',NULL),(41,8,'haha',1,1,'2014-10-13 13:38:12',NULL),(42,97,'他咯了',1,1,'2014-10-13 13:38:32',NULL),(43,10,'娱乐',1,1,'2014-10-13 13:41:38',NULL),(44,105,'破纪录',1,1,'2014-10-14 02:16:20',NULL),(45,6,'123',1,1,'2014-10-14 02:22:48',NULL),(46,6,'456',1,1,'2014-10-14 02:24:13',NULL),(47,27,'865',1,1,'2014-10-14 02:26:37',NULL),(48,106,'123',1,1,'2014-10-14 02:55:37',NULL),(49,106,'85236',1,1,'2014-10-14 02:55:46',NULL),(50,106,'85523',1,1,'2014-10-14 02:55:56',NULL),(51,106,'.85366',1,1,'2014-10-14 02:56:14',NULL),(52,75,'222',1,1,'2014-10-14 06:25:20',NULL),(53,75,'1',1,1,'2014-10-14 06:25:37',NULL),(54,83,'85',1,1,'2014-10-14 06:28:55',NULL),(55,83,'96',1,1,'2014-10-14 06:29:02',NULL),(98,2,'86',1,1,'2014-10-14 07:24:51',NULL),(116,11,'09',1,1,'2014-10-14 07:25:36',NULL),(123,31,'823',1,1,'2014-10-14 07:28:07',NULL),(124,123,'595',1,1,'2014-10-14 07:37:12',NULL),(125,115,'58',1,1,'2014-10-14 07:40:06',NULL),(126,115,'8566',1,1,'2014-10-14 07:40:48',NULL),(127,11,'55',1,1,'2014-10-14 07:41:00',NULL),(128,90,'853',1,1,'2014-10-14 07:41:29',NULL),(129,122,'808',1,1,'2014-10-14 08:07:24',NULL),(130,122,'963',1,1,'2014-10-14 08:07:32',NULL),(171,2,'08',1,1,'2014-10-14 08:41:19',NULL),(175,133,'5566',1,1,'2014-10-14 12:30:31',NULL),(178,136,'55',1,1,'2014-10-14 12:39:38',NULL),(179,137,'556356',1,1,'2014-10-14 12:57:12',NULL),(180,137,'568533',1,1,'2014-10-14 12:57:33',NULL),(181,138,'222222',1,1,'2014-10-14 13:10:01',NULL),(182,143,'585',1,1,'2014-10-15 00:52:37',NULL),(183,144,'去了',1,1,'2014-10-21 02:14:11',NULL),(184,145,'555',1,1,'2014-10-21 02:14:41',NULL),(185,1,'55666',12,12,'2014-10-30 07:17:05',NULL),(186,148,'566566',12,12,'2014-10-30 07:17:24',NULL),(187,2,'1234',12,12,'2014-10-30 07:17:40',NULL),(188,148,'5666',12,12,'2014-11-06 07:10:35',NULL),(189,2,'好',12,12,'2014-11-13 05:57:06',NULL),(190,18,'dgghh',12,12,'2014-11-17 10:49:11',NULL),(191,27,'fghj',12,12,'2014-11-17 14:10:32',NULL),(192,133,'fhhj',12,12,'2014-11-18 02:56:04',NULL),(193,68,'4556',12,12,'2014-11-18 02:56:39',NULL),(194,68,'7889',12,12,'2014-11-18 02:57:08',NULL),(195,35,'556',12,12,'2014-11-18 07:27:04',NULL),(196,67,'556',12,12,'2014-11-18 07:37:53',NULL),(197,17,'8',12,12,'2014-11-18 08:03:05',NULL),(198,139,'fhj',12,12,'2014-11-24 05:51:44',NULL),(199,158,'45566',12,12,'2014-11-24 06:57:53',NULL),(200,159,'111111',12,12,'2014-11-26 13:34:12',NULL),(202,160,'8888',12,12,'2014-11-26 13:35:21',NULL),(203,161,'有时间的话，我还是挺想带孩子一起去的。',1,1,'2014-11-27 02:31:58',NULL),(205,163,'555',12,12,'2014-11-28 07:06:19',NULL),(206,164,'111',12,12,'2014-11-28 07:54:25',NULL),(207,161,'那你就去吧',12,12,'2014-11-28 10:17:12',NULL),(209,14,'5888',12,12,'2014-11-28 11:21:07',NULL),(210,2,'136',23,23,'2015-01-09 10:01:35',NULL),(211,176,'ghjj',23,23,'2015-01-21 05:37:46',NULL),(212,178,'vhj',23,23,'2015-01-21 05:40:02',NULL),(213,179,'5666',23,23,'2015-01-21 05:53:28',NULL),(214,183,'5',23,23,'2015-01-22 12:27:19',NULL),(215,183,'5',23,23,'2015-01-22 12:27:20',NULL),(216,183,'5',23,23,'2015-01-22 12:27:22',NULL),(217,183,'哈哈哈',27,27,'2015-01-22 12:29:14',NULL),(218,183,'哈哈',27,27,'2015-01-22 12:29:39',NULL),(219,183,'哈哈',23,23,'2015-01-22 12:32:01',NULL),(220,183,'8266',23,23,'2015-01-22 12:33:58',NULL),(221,183,'85668',23,23,'2015-01-22 12:41:39',NULL),(222,184,'666',30,30,'2015-01-28 10:11:40',NULL),(223,184,'12345',30,30,'2015-01-28 10:14:32',NULL),(224,184,'123456',30,30,'2015-01-28 10:15:33',NULL),(225,184,'asdfghjkl[晕]',30,30,'2015-01-30 11:11:02',NULL),(226,186,'求价格[酷][酷]',30,30,'2015-04-01 11:33:59',NULL);
/*!40000 ALTER TABLE `family_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grocery`
--

DROP TABLE IF EXISTS `grocery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grocery` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `last_editor` int(11) DEFAULT NULL,
  `last_edit_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `icon` varchar(100) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  `rate` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grocery`
--

LOCK TABLES `grocery` WRITE;
/*!40000 ALTER TABLE `grocery` DISABLE KEYS */;
INSERT INTO `grocery` VALUES (1,1,'黑猫水果店','下沙商贸城','13754636373','本店水果品种丰富，包您满意',1,1,'2014-07-15 08:45:13','ea9edbb9-08f2-4418-84bf-9aac49d3888f.png','120.34599,30.312228',3),(2,1,'榴莲小店','福雷德','13232232323','本店专卖榴莲',1,1,'2014-07-15 08:42:38','5a30975b-89e4-49eb-a681-e1630e45a04a.png','120.352626,30.323974',2),(3,2,'小小蔬菜店','下沙经济技术开发区文泽路99号','1878655234','品种丰富，价格公道的蔬菜店',1,4,'2014-08-15 08:36:08','2ced5cf6-dbec-434a-a74c-ae8838571cc0.jpg','120.35469328317,30.327219871257',2);
/*!40000 ALTER TABLE `grocery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grocery_feedback`
--

DROP TABLE IF EXISTS `grocery_feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grocery_feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rate` int(11) DEFAULT NULL,
  `texts` varchar(500) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `last_editor` int(11) DEFAULT NULL,
  `last_edit_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `grocery` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grocery_feedback`
--

LOCK TABLES `grocery_feedback` WRITE;
/*!40000 ALTER TABLE `grocery_feedback` DISABLE KEYS */;
INSERT INTO `grocery_feedback` VALUES (1,3,'不是很新鲜，口感一般',1,1,'2014-07-15 02:08:12',2),(2,4,'还行吧，不算太差',1,1,'2014-07-15 02:40:23',2),(3,5,'你们的苹果好好吃呢，爱死了，哈哈',1,1,'2014-07-15 08:12:12',2),(4,1,'都是烂的，你们有点良心好不好啊',1,1,'2014-07-15 08:20:00',2),(5,1,'难吃死了',1,1,'2014-07-15 08:34:29',2),(6,3,'古天乐',1,1,'2014-09-15 14:07:25',3),(7,3,'乱了',1,1,'2014-09-15 14:08:58',3),(8,3,'腾',1,1,'2014-09-15 14:09:21',2),(9,3,'顾虑',1,1,'2014-09-15 14:11:33',1),(10,3,'古巨基',1,1,'2014-09-15 14:13:23',3),(11,3,'过来了',1,1,'2014-09-15 14:15:44',2),(12,3,'路你咯',1,1,'2014-09-15 14:16:07',2),(13,2,'1图提图',1,1,'2014-09-15 14:17:39',2),(14,3,'呼呼呼',1,1,'2014-09-15 14:17:50',3),(15,3,'1亏哦了',1,1,'2014-09-15 14:20:43',2),(16,3,'45555',12,12,'2014-11-07 02:53:38',2),(17,2,'fhh',12,12,'2014-11-17 12:16:30',3),(18,2,'r\'(-热=9:的－7男士山东的5',12,12,'2014-11-18 08:04:04',3),(19,3,'dghj',12,12,'2014-11-24 11:58:17',1),(20,3,'ghu',23,23,'2015-01-21 05:36:17',2),(21,3,'556',23,23,'2015-01-21 05:47:19',2),(22,3,'来了556',23,23,'2015-01-21 05:50:11',2),(23,3,'5688',23,23,'2015-01-22 06:57:12',2),(24,3,'very  good!',23,23,'2015-01-22 07:01:50',2),(25,3,'55666',23,23,'2015-01-22 12:48:44',2),(26,3,'466',23,23,'2015-01-22 12:51:35',3),(27,3,'8236',23,23,'2015-01-22 12:52:41',2);
/*!40000 ALTER TABLE `grocery_feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `life`
--

DROP TABLE IF EXISTS `life`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `life` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `intro` varchar(100) DEFAULT NULL,
  `icon` varchar(50) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `last_editor` int(11) DEFAULT NULL,
  `last_edit_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_discount` int(11) DEFAULT NULL,
  `discount` varchar(500) DEFAULT NULL,
  `picture` varchar(100) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `life`
--

LOCK TABLES `life` WRITE;
/*!40000 ALTER TABLE `life` DISABLE KEYS */;
INSERT INTO `life` VALUES (1,2,'小蓉书吧','修身养性，就来书吧','b56aa9c8-a493-48bf-b6e6-8510625e0db9.gif','杭州市江干区下沙福雷德广场1幢1-2','13783546371','修身养性，就来书吧',5,5,'2014-08-14 08:26:26',NULL,'凭学生证95折优惠哦～','2ef3a38c-fa6b-4099-a5bd-6092562a105a.jpg','120.35411387611,30.323658884966'),(2,2,'心缘美容院','心缘美容院，圆您一个美丽的梦','86973051-8aa0-4575-a5dc-112d22e4866d.jpg','学源街887','136566565','心缘美容院座落于下沙福雷德附近，是您休闲美容的极佳场所',6,6,'2014-08-14 13:30:06',NULL,'女人就要懂得爱自己！三月八日全场8折优惠！','916e77b4-fb54-46e7-ae14-2cc6e2c7ede3.jpg','120.353557825,30.324754245275'),(3,4,'开心茶馆','古色古香，适合情侣、文艺青年聚会的极佳场所','c4fef6d3-3eef-4a25-98aa-780f260a6bf5.jpg','学源街887','1378977565','开心茶馆很有特色哦，古色古香，适合情侣、文艺青年、或者聚会等等。在那边还有可能遇到那个心仪对象哦。吼吼。一定要去的地方。',7,7,'2014-08-15 03:33:49',NULL,'中秋佳节前100位客户赠送月饼哦！','a5817540-72dc-41a5-8a38-f685644a550c.jpg','120.353557825,30.324754245275');
/*!40000 ALTER TABLE `life` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `receiver` int(11) DEFAULT NULL,
  `sender` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(11) DEFAULT NULL,
  `parent_obj_id` int(11) DEFAULT NULL,
  `this_obj_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (2,1,1,1,'2014-09-17 13:58:25',1,1,19),(3,1,1,1,'2014-09-17 13:56:35',2,1,20),(4,1,1,1,'2014-09-25 13:36:05',2,1,21),(5,1,1,1,'2014-09-25 13:37:09',2,1,22),(6,1,1,1,'2014-09-25 14:03:47',2,1,23),(7,1,1,1,'2014-09-25 14:07:13',2,1,24),(8,1,1,1,'2014-09-25 14:10:29',2,1,25),(9,1,1,1,'2014-09-25 14:10:47',2,1,26),(10,1,1,1,'2014-09-25 14:14:37',2,1,35),(11,1,1,1,'2014-10-13 07:29:14',2,99,36),(12,1,1,1,'2014-10-13 07:37:12',2,99,37),(13,1,1,1,'2014-10-13 07:40:57',2,99,38),(14,1,1,1,'2014-10-13 07:44:53',2,12,39),(15,1,1,1,'2014-10-13 07:56:48',2,11,40),(16,1,1,1,'2014-10-13 13:38:12',2,8,41),(17,1,1,1,'2014-10-13 13:38:32',2,97,42),(18,1,1,1,'2014-10-13 13:41:38',2,10,43),(19,1,1,1,'2014-10-14 02:16:20',2,105,44),(20,1,1,1,'2014-10-14 02:22:48',2,6,45),(21,1,1,1,'2014-10-14 02:24:13',2,6,46),(22,1,1,1,'2014-10-14 02:26:37',2,27,47),(23,1,1,1,'2014-10-14 02:55:37',2,106,48),(24,1,1,1,'2014-10-14 02:55:46',2,106,49),(25,1,1,1,'2014-10-14 02:55:57',2,106,50),(26,1,1,1,'2014-10-14 02:56:14',2,106,51),(27,1,1,1,'2014-10-14 06:25:20',2,75,52),(28,1,1,1,'2014-10-14 06:25:37',2,75,53),(29,1,1,1,'2014-10-14 06:28:55',2,83,54),(30,1,1,1,'2014-10-14 06:29:02',2,83,55),(31,1,1,1,'2014-10-14 07:24:51',2,2,98),(32,1,1,1,'2014-10-14 07:25:36',2,11,116),(33,1,1,1,'2014-10-14 07:28:07',2,31,123),(34,1,1,1,'2014-10-14 07:37:12',2,123,124),(35,1,1,1,'2014-10-14 07:40:06',2,115,125),(36,1,1,1,'2014-10-14 07:40:48',2,115,126),(37,1,1,1,'2014-10-14 07:41:00',2,11,127),(38,1,1,1,'2014-10-14 07:41:29',2,90,128),(39,1,1,1,'2014-10-14 08:07:24',2,122,129),(40,1,1,1,'2014-10-14 08:07:32',2,122,130),(41,1,1,1,'2014-10-14 08:41:19',2,2,171),(42,1,1,1,'2014-10-14 12:30:31',2,133,175),(43,1,1,1,'2014-10-14 12:39:38',2,136,178),(44,1,1,1,'2014-10-14 12:57:12',2,137,179),(45,1,1,1,'2014-10-14 12:57:33',2,137,180),(46,1,1,1,'2014-10-14 13:10:01',2,138,181),(47,1,1,1,'2014-10-15 00:52:37',2,143,182),(48,1,1,1,'2014-10-21 02:14:11',2,144,183),(49,1,1,1,'2014-10-21 02:14:41',2,145,184),(50,1,12,1,'2014-10-30 07:17:05',2,1,185),(51,1,12,1,'2014-10-30 07:17:24',2,148,186),(52,1,12,1,'2014-10-30 07:17:40',2,2,187),(53,1,12,1,'2014-11-06 07:10:35',2,148,188),(54,1,12,1,'2014-11-13 05:57:06',2,2,189),(55,1,12,1,'2014-11-17 10:49:11',2,18,190),(56,1,12,1,'2014-11-17 14:10:32',2,27,191),(57,1,12,1,'2014-11-18 02:56:04',2,133,192),(58,1,12,1,'2014-11-18 02:56:39',2,68,193),(59,1,12,1,'2014-11-18 02:57:08',2,68,194),(60,1,12,1,'2014-11-18 07:27:04',2,35,195),(61,1,12,1,'2014-11-18 07:37:53',2,67,196),(62,1,12,1,'2014-11-18 08:03:05',2,17,197),(63,1,12,1,'2014-11-24 05:51:44',2,139,198),(64,12,12,1,'2014-11-24 06:57:53',2,158,199),(65,12,12,1,'2014-11-26 13:34:12',2,159,200),(66,12,12,1,'2014-11-26 13:35:21',2,160,202),(67,1,1,1,'2014-11-27 02:31:58',2,161,203),(68,12,12,1,'2014-11-28 07:06:19',2,163,205),(69,12,12,1,'2014-11-28 07:54:25',2,164,206),(70,1,12,1,'2014-11-28 10:17:12',2,161,207),(71,1,12,1,'2014-11-28 11:21:07',2,14,209),(72,1,23,1,'2015-01-09 10:01:35',2,2,210),(73,23,23,1,'2015-01-21 05:37:46',2,176,211),(74,23,23,1,'2015-01-21 05:40:02',2,178,212),(75,1,23,1,'2015-01-21 05:53:28',2,179,213),(76,27,23,1,'2015-01-22 12:27:19',2,183,214),(77,27,23,1,'2015-01-22 12:27:20',2,183,215),(78,27,23,1,'2015-01-22 12:27:22',2,183,216),(79,27,27,1,'2015-01-22 12:29:14',2,183,217),(80,27,27,1,'2015-01-22 12:29:39',2,183,218),(81,27,23,1,'2015-01-22 12:32:01',2,183,219),(82,27,23,1,'2015-01-22 12:33:58',2,183,220),(83,27,23,1,'2015-01-22 12:41:39',2,183,221),(84,30,30,1,'2015-01-28 10:11:40',2,184,222),(85,30,30,1,'2015-01-28 10:14:32',2,184,223),(86,30,30,1,'2015-01-28 10:15:33',2,184,224),(87,30,30,1,'2015-01-30 11:11:02',2,184,225),(88,30,30,1,'2015-04-01 11:33:59',2,186,226);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `detail` varchar(500) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `last_editor` int(11) DEFAULT NULL,
  `last_edit_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,'车主须知','2014-07-04 03:02:22','车辆请停放在指定的位置，以免影响到其他住户的日常生活，谢谢。',NULL,NULL,'2014-07-04 03:03:32',1),(2,'2014物业费开始收取','2014-06-20 03:03:51','今年的物业费开始收取了……',NULL,NULL,'2014-07-04 03:05:22',1),(3,'社区活动月开始啦','2014-06-04 11:50:48','今年的社区活动月即将开始，主题为“邻里互助，创造美好未来”。',NULL,NULL,'2014-07-04 11:51:23',1),(4,'有关最近广场舞投诉的一些意见','2014-07-08 08:22:00','最近一段时间，已有不少住户向我们反映了广场舞的问题，我们希望在广场上活动健身的人，能够尽量降低音量，为他人着想，谢谢。',2,2,'2014-07-08 08:22:00',1),(5,'关于做好三伏天防暑工作的通知','2014-07-29 11:14:00','请大家做好三伏天里的防暑降温工作，尽量避免在正午时候出门。',1,1,'2014-07-29 11:14:00',1),(6,'请大家做好防台风的工作','2014-08-05 07:43:06','最近三天，台风“卡特里娜”将登陆我省，请大家做好放台的准备，下雨天尽量不要出门，谢谢。',1,1,'2014-08-05 07:43:06',1),(7,'中秋节小区联谊就要开始了','2014-08-15 08:15:12','今年的中秋节小区联谊活动就要开始了，欢迎大家踊跃参加！',1,1,'2014-08-15 08:15:12',1),(8,'中秋佳节','2014-09-05 06:19:27','中秋佳节即将到来',1,1,'2014-09-05 06:19:27',1),(9,'2014年秋季物业费可以开始交了','2014-11-26 13:02:00','2014年秋季物业费可以开始交了，希望业主朋友们在月底前上交。谢谢。',1,1,'2014-11-26 13:02:00',1),(10,'12月“亲子活动日”马上就要开始了','2014-12-09 06:08:05','还记得11月的“亲子活动日”的精彩瞬间吗？如果您错过了，没有关系，12月的“亲子活动日”又来了！',1,1,'2014-12-09 06:08:05',1),(11,'七月话费大赠送开始啦！','2015-07-06 10:41:43','想要话费的你赶紧行动起来！',1,1,'2015-07-06 10:41:43',1),(12,'七月的水费可以开始交了','2015-07-06 11:02:47','请各位住户务必在本周结束前上交水费，谢谢合作！',1,1,'2015-07-06 11:02:47',2);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification_read`
--

DROP TABLE IF EXISTS `notification_read`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification_read` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) DEFAULT NULL,
  `notification` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification_read`
--

LOCK TABLES `notification_read` WRITE;
/*!40000 ALTER TABLE `notification_read` DISABLE KEYS */;
INSERT INTO `notification_read` VALUES (2,2,2),(3,2,3),(4,1,1),(5,1,2),(6,1,7),(7,1,8),(8,1,5),(9,1,4),(10,1,6),(11,1,3),(12,12,8),(13,12,6),(14,12,1),(15,12,7),(16,12,3),(17,12,5),(18,12,2),(19,12,4),(20,1,9),(21,12,9),(22,12,10),(23,23,4),(24,23,9),(25,23,8),(26,23,7),(27,23,1),(28,23,2),(29,23,6),(30,23,5),(31,27,10),(32,27,9),(33,27,8),(34,27,7),(35,27,4),(36,35,10),(37,23,10),(38,29,10),(39,37,8),(40,38,9),(41,30,10),(42,29,9),(43,29,8),(44,29,7),(45,29,6),(46,29,5),(47,29,3),(48,38,8),(49,30,3),(50,30,2),(51,30,1),(52,30,4),(53,30,5),(54,30,9),(55,30,8),(56,30,6),(57,30,7);
/*!40000 ALTER TABLE `notification_read` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parcel`
--

DROP TABLE IF EXISTS `parcel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parcel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `receive_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `receiver` int(11) DEFAULT NULL,
  `is_received` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parcel`
--

LOCK TABLES `parcel` WRITE;
/*!40000 ALTER TABLE `parcel` DISABLE KEYS */;
INSERT INTO `parcel` VALUES (1,'申通快递','2014-07-09 02:50:37','2014-10-20 02:11:07',1,1),(2,'EMS','2014-08-15 08:20:20','2014-08-15 08:20:20',1,0),(3,'顺丰快递','2014-11-26 13:11:30','2014-11-26 13:11:30',1,0);
/*!40000 ALTER TABLE `parcel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phone_number`
--

DROP TABLE IF EXISTS `phone_number`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phone_number` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `last_editor` int(11) DEFAULT NULL,
  `last_edit_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phone_number`
--

LOCK TABLES `phone_number` WRITE;
/*!40000 ALTER TABLE `phone_number` DISABLE KEYS */;
INSERT INTO `phone_number` VALUES (1,'0571-65783764','小区报警电话',NULL,NULL,'2014-07-03 08:25:49'),(2,'88756355','小区医院',NULL,NULL,'2014-08-07 07:50:03'),(3,'87656666','物管中心联系电话',1,1,'2014-08-07 07:54:54'),(4,'15787635343','老年活动中心',1,1,'2014-08-15 08:19:32'),(5,'15876543421','小区服务热线',1,1,'2014-11-26 12:40:51');
/*!40000 ALTER TABLE `phone_number` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `last_editor` int(11) DEFAULT NULL,
  `last_edit_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `grocery` int(11) DEFAULT NULL,
  `icon` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (2,'猕猴桃',26.7,'元/斤','新鲜上市的猕猴桃，鲜嫩可口～',4,4,'2014-10-24 06:20:35',3,'2d43d526-5e48-430e-a88e-dbcdf0e0514b.jpg'),(3,'红富士',12,'元/斤','进口红富士，个头大，价格实惠',4,4,'2014-08-12 13:17:21',3,'1c35b478-662a-4de0-a5ff-41bdec4ebc6e.png'),(6,'小苹果',8.3,'元/斤','可爱的小苹果',4,4,'2014-08-12 13:48:26',3,'b0530dcc-76db-435a-81a5-cac97cd8f5c2.gif');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repair`
--

DROP TABLE IF EXISTS `repair`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `repair` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `texts` varchar(500) DEFAULT NULL,
  `room` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `contact` varchar(20) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `last_editor` int(11) DEFAULT NULL,
  `last_edit_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `picture_one` varchar(50) DEFAULT NULL,
  `picture_two` varchar(50) DEFAULT NULL,
  `picture_three` varchar(50) DEFAULT NULL,
  `time_one` varchar(100) DEFAULT NULL,
  `time_two` varchar(100) DEFAULT NULL,
  `time_three` varchar(100) DEFAULT NULL,
  `voice` varchar(100) DEFAULT NULL,
  `is_fixed` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `repair_worker_name` varchar(20) DEFAULT NULL,
  `repair_worker_phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repair`
--

LOCK TABLES `repair` WRITE;
/*!40000 ALTER TABLE `repair` DISABLE KEYS */;
INSERT INTO `repair` VALUES (1,'你们物业都在干嘛啊，水龙头坏了这么多天，你们人呢！！','33-502','13783546371','小六',1,1,'2015-01-13 13:47:39','da1eb1d7-b456-41f0-b6ca-7859d8af1dca.png','54bfa16f-85fe-4fff-b557-175ef110a67d.png',NULL,'2014-07-11 11:00','2014-07-13 17:00',NULL,'success.wav',1,1,NULL,NULL),(2,'请填写您需要维修的内容。','123','15990124590','高大神',1,1,'2015-01-13 13:47:39','753b52fe-3899-4e5c-913f-cbe8e0ce2837.png','c9b42ede-fc5c-4dba-8bdd-1bb70aa3ad3e.png','69e88084-8b50-4d6e-8435-8552e7528afc.png','开始时间: 2014/8/19-12:49---延后 30 分钟','开始时间: 2014/8/20-12:49---延后 30 分钟','开始时间: 2014/8/21-12:49---延后 15 分钟','624206c6-44e7-4071-84f1-d9a4ad8c24d2.amr.wav',0,1,NULL,NULL),(3,'请填写您需要维修的内容。','66','88','66',1,1,'2015-01-13 13:47:39','fa605f60-b53d-4f48-9556-ca10841e1968.png','bb0c7604-6810-4393-a7b3-e03624dc6798.png',NULL,'开始时间: 2009/7/19-01:18---延后 60 分钟','开始时间: 2009/1/19-01:18---延后 60 分钟','开始时间: 2009/1/19-01:18---延后 60 分钟',NULL,1,1,NULL,NULL),(4,'请填写您需要维修的内容。','123','123','123',1,1,'2015-01-13 13:47:39','178fd341-f66a-411a-a136-724ddcb22111.png','bc7a3bc7-4af4-4a8d-8856-c83a2e90f4e2.png','9717c044-2736-43d8-b34a-f647ba02ea7a.png',NULL,NULL,NULL,'ee3424f3-a437-498d-90cc-55d213a39c30.amr.wav',0,1,NULL,NULL),(5,'请填写您需要维修的内容。','803-2','110','chen',1,1,'2015-01-13 13:47:39',NULL,NULL,NULL,'开始时间: 2014/8/29-04:02---延后 60 分钟',NULL,NULL,'694b58a6-b351-4568-9feb-43f99826cb46.amr.wav',0,1,NULL,NULL),(6,NULL,'566','559','是咯我',1,1,'2015-01-13 13:47:39',NULL,NULL,NULL,'2014-10-23 16:04:16','你还没有选择好时间',NULL,NULL,0,1,NULL,NULL),(7,NULL,'扣囖','8566','同咯',1,1,'2015-01-13 13:47:39',NULL,NULL,NULL,'2014-10-23 16:05:48','2014-10-23 19:05:12',NULL,NULL,0,1,NULL,NULL),(8,'好','5566','5556','56666',1,1,'2015-01-13 13:47:39',NULL,NULL,NULL,'你还没有选择好时间','2014-10-23 16:13:46',NULL,NULL,0,1,NULL,NULL),(9,'咯里了','哈啊','555','fj',1,1,'2015-01-13 13:47:39','6ef1103b-0771-414d-8694-364e90cf4ec1.jpg','9af5ad1e-64e1-49d6-9ae4-f94893b5fe70.jpg','44808bc5-1b76-4866-9c2f-70080592cf94.jpg','你还没有选择好时间','2014-10-23 16:16:50',NULL,'c9d0ff1e-6cf7-484c-b4cc-daea883ead6f.jpg',0,1,NULL,NULL),(10,'龙猫','好咯哦哦','5565666','路咯哦哦哦',1,1,'2015-01-13 13:47:39','21a0f4e3-35e0-4533-b06c-0bcb805d0e58.jpg',NULL,NULL,'2014-10-23 16:21:23','2014-10-23 22:21:42',NULL,'1e651766-d2d6-4171-933e-58eb9d1ae35e.jpg',0,1,NULL,NULL),(11,'556656655556','55685666','5554559','好了里哦哦哦',1,1,'2015-01-13 13:47:39',NULL,NULL,NULL,'你还没有选择好时间','2014-10-23 16:24:35',NULL,NULL,0,2,NULL,NULL),(12,'是','唾沫','5566','8966661',1,1,'2015-01-13 13:47:39',NULL,NULL,NULL,'2014-10-23 16:39:21','2014-10-23 23:39:32',NULL,NULL,0,2,NULL,NULL),(13,'55658','了咯哦哦','85366','同哦哦',1,1,'2015-01-13 13:47:39',NULL,NULL,NULL,'2014-10-23 16:44:00','你还没有选择好时间',NULL,NULL,0,2,NULL,NULL),(14,'1','123','123','123',1,1,'2015-01-13 13:47:39','ac00512d-9270-4c9f-ac60-2256db39aa12.jpg','cfd67546-d22f-426a-a4e1-84aa49597484.jpg','8b988b37-a926-4bc9-8443-55a63a3aeaf1.jpg','2014-10-24 09:31:07','2014-10-24 11:31:14',NULL,'eaec8c12-b218-4c18-a2c6-7ea202694bed.jpg',0,2,NULL,NULL),(15,'123','123','123','123',1,1,'2015-01-13 13:47:39',NULL,NULL,NULL,'2014-10-24 09:38:51','2014-10-24 15:40:59',NULL,'992d30bb-6b0c-4d91-8d0f-352b93ecfc11.jpg',0,2,NULL,NULL),(16,'456','456','15165628578','5696',1,1,'2015-01-13 13:47:39','005a6302-c58a-4200-b5ac-163de3054f7d.jpg',NULL,NULL,'2014-10-24 09:41:17','2014-10-24 14:41:22',NULL,NULL,0,2,NULL,NULL),(17,'5698666','5685666','45555566','556556',1,1,'2015-01-13 13:47:39','8a7e9a9b-3b72-482c-aeef-ed7ebf6fb5b2.jpg','771e06de-58c9-41f4-aa40-d10cf721d04a.jpg','5d1e526c-b0f5-4981-b419-618c7c46681c.jpg','2014-10-27 20:48:05','2014-10-27 23:48:14',NULL,'c6fdbe70-469a-442f-8487-b96daac275d0.jpg',2,2,NULL,NULL),(18,'556666','86663','1874523689652','5668666',1,1,'2015-01-13 13:47:39','601f838f-5bd2-4a61-8aad-c84db61ee306.jpg','14764ebb-c2c8-4eaa-b220-745b4fbc0640.jpg',NULL,'2014-10-27 20:58:46','2014-10-27 22:58:53',NULL,'c94d58b3-1629-4b04-bd65-b5072ac3324d.jpg',1,2,NULL,NULL),(19,'556666','555556','455556','55666',1,1,'2015-01-13 13:47:39','4d113425-ba9d-4886-b35b-f395c338f55d.jpg','200ab8c3-44f8-4cf2-b8ae-f12b3578f1f9.jpg','560a304b-a0a3-4544-bb02-d3ff6a9822f0.jpg','2014-10-28 10:44:10','2014-10-28 11:44:21',NULL,NULL,1,2,NULL,NULL),(20,'5569555','5566','8555','5666',10,10,'2015-01-13 13:47:39',NULL,NULL,NULL,'2014-10-29 16:16:16','2014-10-29 18:16:20',NULL,NULL,1,2,NULL,NULL),(21,'55666','8566','455566','5566',12,12,'2015-01-13 13:47:39',NULL,NULL,NULL,'2014-10-30 14:52:14','2014-10-30 15:52:18',NULL,NULL,1,2,NULL,NULL),(22,'家里的水管坏了，请尽快帮忙找一个维修师傅。','1幢3单元502室','13535871024','小洋葱',1,1,'2015-01-13 13:47:39','5e310cbb-f29c-40cf-9268-5152f982a1b4.png','f179d8d0-6cff-4346-83eb-59899eadf88a.png','3dfc513a-bc08-4756-89e6-f5dc9c458602.png','开始时间: 2014/11月/26日-1时:49---延后 30 分钟','开始时间: 2014/11月/29日-5时:53---延后 30 分钟','开始时间: 2014/11月/31日-10时:57---延后 60 分钟',NULL,1,2,NULL,NULL),(23,'门坏了','360','13456977360','tom',12,12,'2015-01-13 13:47:39','f1302397-04e7-4181-aea0-8879bc04bcbc.jpg',NULL,NULL,'2014-12-11 19:08:11','2014-12-11 22:08:15',NULL,'bf361177-b1b3-4090-b23e-a703ef6a2660.jpg',1,2,NULL,NULL),(24,'465','456','15165624770','tom',12,12,'2015-01-14 03:01:37','5b52658f-7086-47aa-a420-5265a2bae01c.jpg',NULL,NULL,'2014-12-12 11:06:55','2014-12-12 15:06:58',NULL,'1d210f03-a4fa-42f0-ba7c-f3d0a90c97cf.jpg',1,2,NULL,NULL),(25,'。','55658','4586385252','fhjgj',23,23,'2015-01-13 13:47:39',NULL,NULL,NULL,'2015-01-12 14:17:17','2015-01-12 18:17:21',NULL,'930ef5e0-fd5a-447e-b680-61bd8e01ab4e.amr.wav',0,2,NULL,NULL),(26,'dgg','edf','13456977360','gao',23,23,'2015-01-13 13:47:39','1f1c0136-0a69-4bb7-9d03-6225f9ffb698.png',NULL,NULL,'2015-01-12 14:36:51','2015-01-12 20:36:55',NULL,'b6cc8762-42b4-4984-b916-a6cbae0b699e.amr.wav',0,2,NULL,NULL),(27,'jhii','86666','15165624770','ghd',23,23,'2015-01-13 13:47:39',NULL,NULL,NULL,'2015-01-12 14:37:54','2015-01-12 20:37:57',NULL,'5067693d-007a-44ea-baaf-b44aa973744e.amr.wav',0,2,NULL,NULL),(28,'qqq','603','15165635896','gao',23,23,'2015-01-13 13:47:39',NULL,NULL,NULL,'2015-01-12 15:05:27','2015-01-14 15:05:31',NULL,'c457d8f8-08a3-49a0-a05e-4546c42fcf20.amr.wav',0,2,NULL,NULL),(29,'666','666','13456977360','hff',23,23,'2015-01-13 14:02:04',NULL,NULL,NULL,'2015-01-13 21:59:28','2015-01-13 23:59:34',NULL,'9ae994a2-7923-4eb9-a350-993979f62c50.amr.wav',0,2,NULL,NULL),(30,'vgd','566','13466977360','f&',23,23,'2015-01-14 03:01:14',NULL,NULL,NULL,'2015-01-13 22:02:29','2015-01-14 21:02:51',NULL,'aa32fde5-7fe7-4461-8b5f-60a13bdfa153.amr.wav',2,4,NULL,NULL),(31,'Abcdefg','601','7758258','tom',1,1,'2015-01-15 01:56:06',NULL,NULL,NULL,'开始时间: 2015/1月/15日-9时:51---延后 15 分钟','开始时间: 2015/1月/15日-9时:51---延后 30 分钟',NULL,'3005ffd5-a9e2-4fb1-913f-05491fe96b2c.amr.wav',0,NULL,NULL,NULL),(32,'Asdfghjkl','601','7758258','66',1,1,'2015-01-15 03:49:28',NULL,NULL,NULL,'开始时间: 2015/1月/15日-10时:4---延后 15 分钟','开始时间: 2015/1月/15日-10时:4---延后 30 分钟',NULL,'c6bf533b-e7cb-4c39-8677-f7dd3398fce5.amr.wav',2,3,'六妹','13784563098'),(33,'556','556','456652','天籁',23,23,'2015-01-21 06:10:32',NULL,NULL,NULL,'2015-01-21 14:08:05','2015-01-21 20:08:08',NULL,'2fcaf499-0f22-4d19-b69c-b1dfa6cc979a.amr.wav',0,3,NULL,NULL),(34,'实验室的灯坏了，过来修好它','7#605','15165624889','justin',23,23,'2015-01-22 12:55:44','1505f775-41c4-4799-9212-7e721d848b1c.png','1abec2ea-31cc-4696-993a-d8531ec5a83f.png','b372f39e-360c-46f9-a2b0-325fcb90728e.png','2015-01-22 20:51:12','2015-01-22 21:51:15',NULL,'2f6ab70c-f231-44ad-ab18-4381ca96cc14.amr.wav',0,2,NULL,NULL),(35,'111','111','111','111',36,36,'2015-01-23 04:39:11',NULL,NULL,NULL,'2015-01-20 12:37:18','2015-01-27 12:37:09',NULL,NULL,0,1,NULL,NULL),(36,'11','11','11','11',36,36,'2015-01-23 04:44:49',NULL,NULL,NULL,'2015-01-27 12:37:07','2015-01-27 13:37:15',NULL,NULL,0,1,NULL,NULL),(37,'qqq','qqq','111111','qqq',35,35,'2015-01-23 06:25:57',NULL,NULL,NULL,'2015-01-23 14:24:16','2015-01-24 14:24:21',NULL,NULL,0,1,NULL,NULL),(38,'qqq','8#11','444444444','go',35,35,'2015-01-23 07:05:27',NULL,NULL,NULL,'2015-01-23 15:02:49','2015-01-28 15:02:55',NULL,NULL,0,1,NULL,NULL),(39,'好好干','99','999','999',35,35,'2015-01-23 07:41:10',NULL,NULL,NULL,'2015-01-17 15:38:24','2015-01-20 15:38:33',NULL,NULL,0,1,NULL,NULL),(40,'553','高563','4553','5563',23,23,'2015-01-23 07:41:46',NULL,NULL,NULL,'2015-01-23 15:40:21','2015-01-23 16:40:25',NULL,'a267a6af-0a8c-4bde-b9de-da455ce4f2dd.amr.wav',0,2,NULL,NULL),(41,'哥们','111','111','1111',29,29,'2015-01-23 07:47:32',NULL,NULL,NULL,'2015-01-23 15:45:49','2015-01-27 15:45:55',NULL,NULL,0,3,NULL,NULL),(42,'3445','43444','55555656','444',23,23,'2015-01-23 08:02:50',NULL,NULL,NULL,'2015-01-23 07:54:38','2015-01-23 10:54:42',NULL,NULL,0,5,NULL,NULL),(43,'空','556','526','8566',23,23,'2015-01-27 01:29:19',NULL,NULL,NULL,'2015-01-27 08:59:05','2015-01-27 10:26:41',NULL,NULL,0,3,NULL,NULL),(44,'5563','8566','456882','5566',23,23,'2015-01-27 01:30:40',NULL,NULL,NULL,'2015-01-27 09:28:05','2015-01-27 10:28:26',NULL,NULL,0,2,NULL,NULL),(45,'asdfghjkl','601','110','gaolaoshi',30,30,'2015-02-01 11:26:30',NULL,NULL,NULL,'2015-02-01 07:23:27','2015-02-01 09:23:31',NULL,'8547dd58-603e-4f0b-b987-f837d546dcc8.amr.wav',0,5,NULL,NULL),(46,'123456','601','110','gaogaogao',30,30,'2015-02-01 13:04:28',NULL,NULL,NULL,'2015-02-01 09:01:47','2015-02-01 10:01:49',NULL,'13e263c9-f9d0-4dfa-901b-b30d3db54492.amr.wav',0,1,NULL,NULL);
/*!40000 ALTER TABLE `repair` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repair_history`
--

DROP TABLE IF EXISTS `repair_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `repair_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `repair_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `last_editor` int(11) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_edit_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repair_history`
--

LOCK TABLES `repair_history` WRITE;
/*!40000 ALTER TABLE `repair_history` DISABLE KEYS */;
INSERT INTO `repair_history` VALUES (1,22,2,1,'2014-12-08 04:07:40','2014-12-08 04:07:40','您好，已派人前来维修'),(2,22,2,1,'2014-12-11 07:49:35','2014-12-11 07:49:35','您好，已派出六师傅前来维修，您可以用电话联系：139876723。'),(3,22,2,3,'2014-12-11 08:00:04','2014-12-11 07:59:12','没看到人来……你们物业真没责任感'),(4,22,1,1,'2014-12-11 10:38:25','2014-12-11 10:38:25',NULL),(5,23,0,12,'2014-12-11 11:09:13','2014-12-11 11:09:13',NULL),(6,23,0,1,'2014-12-11 11:22:02','2014-12-11 11:22:02','您好，您的报修单已提交，我们会尽快为您处理，谢谢！'),(7,23,2,1,'2014-12-11 11:40:45','2014-12-11 11:40:45',NULL),(8,23,1,1,'2014-12-11 11:45:48','2014-12-11 11:45:48',NULL),(9,21,2,1,'2014-12-11 11:53:34','2014-12-11 11:53:34','您好，正在处理您的报修，我们会尽快派出专业人员来为您服务，谢谢！'),(10,21,1,1,'2014-12-11 11:53:47','2014-12-11 11:53:47','您好，您的报修已完成，请对我们的服务进行评价，谢谢！'),(11,20,2,1,'2014-12-11 11:55:49','2014-12-11 11:55:49','马上就会处理的，急啥子急哟'),(12,20,2,1,'2014-12-11 12:41:31','2014-12-11 12:41:31','六师傅出来了，你再等等'),(13,20,1,1,'2014-12-11 12:41:58','2014-12-11 12:41:58','您好，您的报修已完成，请对我们的服务进行评价，谢谢！'),(14,20,1,1,'2014-12-11 12:57:39','2014-12-11 12:57:39','您对我们的服务还满意么？'),(15,19,2,1,'2014-12-11 13:00:17','2014-12-11 13:00:17','您好，正在处理您的报修，我们会尽快派出专业人员来为您服务，谢谢！'),(16,19,1,1,'2014-12-11 13:01:57','2014-12-11 13:01:57','您好，您的报修已完成，请对我们的服务进行评价，谢谢！'),(17,21,1,1,'2014-12-11 13:09:21','2014-12-11 13:09:21','您对我们的服务有什么意见或建议呢？'),(18,18,2,1,'2014-12-11 13:50:12','2014-12-11 13:50:12','您好，正在处理您的报修，我们会尽快派出专业人员来为您服务，谢谢！'),(19,18,1,1,'2014-12-11 13:51:26','2014-12-11 13:51:26','您好，您的报修已完成，请对我们的服务进行评价，谢谢！'),(20,17,2,1,'2014-12-11 13:52:51','2014-12-11 13:52:51','您好，正在处理您的报修，我们会尽快派出专业人员来为您服务，谢谢！'),(21,24,0,1,'2014-12-12 03:06:50','2014-12-12 03:06:50','您好，您的报修单已提交，我们会尽快为您处理，谢谢！'),(22,22,1,1,'2014-12-12 12:27:58','2014-12-12 12:27:58','大舌头'),(23,24,2,1,'2014-12-29 01:03:36','2014-12-29 01:03:36','您好，正在处理您的报修，我们会尽快派出专业人员来为您服务，谢谢！'),(24,3,0,1,'2015-01-09 07:54:45','2015-01-09 07:54:45','已收到'),(25,3,2,1,'2015-01-09 07:55:02','2015-01-09 07:55:02','您好，正在处理您的报修，我们会尽快派出专业人员来为您服务，谢谢！'),(26,3,1,1,'2015-01-09 07:56:07','2015-01-09 07:56:07','您好，您的报修已完成，请对我们的服务进行评价，谢谢！'),(27,25,0,1,'2015-01-12 06:19:52','2015-01-12 06:19:52','您好，您的报修单已提交，我们会尽快为您处理，谢谢！'),(28,26,0,1,'2015-01-12 06:38:35','2015-01-12 06:38:35','您好，您的报修单已提交，我们会尽快为您处理，谢谢！'),(29,27,0,1,'2015-01-12 06:39:23','2015-01-12 06:39:23','您好，您的报修单已提交，我们会尽快为您处理，谢谢！'),(30,28,0,1,'2015-01-12 07:06:59','2015-01-12 07:06:59','您好，您的报修单已提交，我们会尽快为您处理，谢谢！'),(31,17,2,1,'2015-01-13 13:52:06','2015-01-13 13:52:06','维修人员：cccc\n联系电话：1578976766'),(32,29,0,1,'2015-01-13 14:02:04','2015-01-13 14:02:04','您好，您的报修单已提交，我们会尽快为您处理，谢谢！'),(33,30,0,1,'2015-01-13 14:04:20','2015-01-13 14:04:20','您好，您的报修单已提交，我们会尽快为您处理，谢谢！'),(34,30,2,1,'2015-01-14 03:01:14','2015-01-14 03:01:14','您好，正在处理您的报修，我们会尽快派出专业人员来为您服务，谢谢！'),(35,24,2,1,'2015-01-14 03:01:35','2015-01-14 03:01:35','维修人员：cccc\n联系电话：1578976766'),(36,24,1,1,'2015-01-14 03:01:37','2015-01-14 03:01:37','您好，您的报修已完成，请对我们的服务进行评价，谢谢！'),(37,17,2,1,'2015-01-14 10:52:48','2015-01-14 10:52:48','维修人员：六妹\n联系电话：13784563098'),(38,31,0,1,'2015-01-15 01:56:06','2015-01-15 01:56:06','您好，您的报修单已提交，我们会尽快为您处理，谢谢！'),(39,32,0,1,'2015-01-15 02:06:58','2015-01-15 02:06:58','您好，您的报修单已提交，我们会尽快为您处理，谢谢！'),(40,32,2,1,'2015-01-15 03:49:13','2015-01-15 03:49:13','您好，正在处理您的报修，我们会尽快派出专业人员来为您服务，谢谢！'),(41,32,2,1,'2015-01-15 03:49:28','2015-01-15 03:49:28','维修人员：六妹\n联系电话：13784563098'),(42,33,0,1,'2015-01-21 06:10:32','2015-01-21 06:10:32','您好，您的报修单已提交，我们会尽快为您处理，谢谢！'),(43,1,1,23,'2015-01-22 07:33:25','2015-01-22 07:33:25','五星,dt'),(44,21,1,27,'2015-01-22 11:39:28','2015-01-22 11:39:28','五星,好'),(45,21,1,27,'2015-01-22 11:39:28','2015-01-22 11:39:28','五星,好'),(46,21,1,27,'2015-01-22 11:39:28','2015-01-22 11:39:28','五星,好'),(47,1,1,27,'2015-01-22 11:39:53','2015-01-22 11:39:53','四星,好'),(48,20,1,23,'2015-01-22 12:02:17','2015-01-22 12:02:17','五星,hw'),(49,20,1,27,'2015-01-22 12:09:10','2015-01-22 12:09:10','四星,好'),(50,24,1,27,'2015-01-22 12:10:16','2015-01-22 12:10:16','三星,fhjjwe'),(51,34,0,1,'2015-01-22 12:55:44','2015-01-22 12:55:44','您好，您的报修单已提交，我们会尽快为您处理，谢谢！'),(52,20,1,29,'2015-01-22 13:06:35','2015-01-22 13:06:35','五星,速度很快喔。'),(53,35,0,1,'2015-01-23 04:39:11','2015-01-23 04:39:11','您好，您的报修单已提交，我们会尽快为您处理，谢谢！'),(54,36,0,1,'2015-01-23 04:44:49','2015-01-23 04:44:49','您好，您的报修单已提交，我们会尽快为您处理，谢谢！'),(55,37,0,1,'2015-01-23 06:25:57','2015-01-23 06:25:57','您好，您的报修单已提交，我们会尽快为您处理，谢谢！'),(56,38,0,1,'2015-01-23 07:05:27','2015-01-23 07:05:27','您好，您的报修单已提交，我们会尽快为您处理，谢谢！'),(57,39,0,1,'2015-01-23 07:41:10','2015-01-23 07:41:10','您好，您的报修单已提交，我们会尽快为您处理，谢谢！'),(58,40,0,1,'2015-01-23 07:41:46','2015-01-23 07:41:46','您好，您的报修单已提交，我们会尽快为您处理，谢谢！'),(59,41,0,1,'2015-01-23 07:47:32','2015-01-23 07:47:32','您好，您的报修单已提交，我们会尽快为您处理，谢谢！'),(60,42,0,1,'2015-01-23 08:02:50','2015-01-23 08:02:50','您好，您的报修单已提交，我们会尽快为您处理，谢谢！'),(61,43,0,1,'2015-01-27 01:29:20','2015-01-27 01:29:20','您好，您的报修单已提交，我们会尽快为您处理，谢谢！'),(62,44,0,1,'2015-01-27 01:30:40','2015-01-27 01:30:40','您好，您的报修单已提交，我们会尽快为您处理，谢谢！'),(63,45,0,1,'2015-02-01 11:26:30','2015-02-01 11:26:30','您好，您的报修单已提交，我们会尽快为您处理，谢谢！'),(64,46,0,1,'2015-02-01 13:04:28','2015-02-01 13:04:28','您好，您的报修单已提交，我们会尽快为您处理，谢谢！');
/*!40000 ALTER TABLE `repair_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suggestion`
--

DROP TABLE IF EXISTS `suggestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suggestion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `texts` varchar(500) DEFAULT NULL,
  `voice` varchar(50) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `last_editor` int(11) DEFAULT NULL,
  `last_edit_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `picture_one` varchar(50) DEFAULT NULL,
  `picture_two` varchar(50) DEFAULT NULL,
  `picture_three` varchar(50) DEFAULT NULL,
  `comment` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suggestion`
--

LOCK TABLES `suggestion` WRITE;
/*!40000 ALTER TABLE `suggestion` DISABLE KEYS */;
INSERT INTO `suggestion` VALUES (1,'18幢附近有很多垃圾没人清理唉，你们物业都在干嘛啊！','6bd87d37-6ad3-4e5e-ac31-7834ef3593d6.amr',1,1,'2014-07-10 07:37:11','4a2a26bb-8710-4308-85d1-8dee831e030c.jpg','c7a336e8-a808-4b79-b62e-90f6c66f4353.jpg',NULL,NULL,0,1),(2,'下水道最近又有问题了……','1be0b7fc-63c9-4411-bff1-c7bdb9fca7a5.ogg',1,1,'2014-07-10 08:10:10','377d7b42-f0e3-4476-9bea-5c82ed8a8d27.png','08f32bd3-859a-4017-b8f1-56ceb4c01bd7.png',NULL,NULL,0,1),(3,'18幢的垃圾还是没有人清理……','d7afff94-80cf-40ab-8261-715f1b06791c.amr',1,1,'2014-07-11 03:08:46','86ad5131-cd5e-4a5c-85d4-1c2fd4e81827.png','3e346900-1e3a-49dd-8fe9-3258120a73f3.png',NULL,NULL,0,1),(4,'希望物业能够改善服务态度，真正地为我们服务，而不是只在收物业费的时候出现。','976878f7-d3e5-4afc-a8f9-4f38d11764ec.amr',1,1,'2014-07-11 04:22:46','79ab66fd-4b4a-48b7-9c09-fe0849336707.png','7fe5e0bc-447a-4dc8-a666-c07cfd72fe88.png',NULL,NULL,0,1),(5,'希望能就物业费开一次听证会。','2ce1ed72-4337-4f94-9663-98daeeaee72d.amr',1,1,'2014-07-11 04:24:05','0237dc06-1100-494a-be10-fa11f6a44f4b.png','766f3ea4-cf72-4887-b670-99c80f81b658.png',NULL,NULL,0,1),(6,'水龙头坏了，你们看着办吧，我已经说烦了','d7faf7a3-0b65-4b80-badd-845378aa8778.amr',1,1,'2014-07-11 08:16:06','36854f26-0328-484e-8db8-67413b12f0fe.png','66551cb1-73b3-4912-aa6d-81d529e06c78.png',NULL,NULL,0,1),(7,NULL,NULL,1,1,'2014-07-15 02:04:06','e955406e-d280-4a8c-8036-e2a8bf6d435a.png',NULL,NULL,NULL,0,1),(8,NULL,NULL,1,1,'2014-08-11 12:06:41','05715b0c-5c3a-421f-ac3d-e3c22b2f55f1.','76d32c47-fdf0-4867-9730-abcb3b75531e.',NULL,NULL,0,1),(9,NULL,NULL,1,1,'2014-08-11 12:07:20','5289eefe-565a-4c6a-b2a3-72bf4b8b341a.','31305365-5b14-4ed9-918e-e953aec51da9.',NULL,NULL,0,1),(10,NULL,NULL,1,1,'2014-08-12 06:47:46','cb888461-1e13-40ee-9fe4-125ca479204a.','1c532a85-cd5e-4bea-b0d0-de2595e3ba6a.',NULL,NULL,0,1),(11,NULL,NULL,1,1,'2014-08-12 06:50:29','b6bae70d-95ce-4518-8500-a6ae9c3a38e2.','7336bbf4-aea5-428b-bbff-cb4a9d3e9a93.',NULL,NULL,0,1),(12,NULL,NULL,1,1,'2014-08-12 06:53:38','694cf940-24a1-47bd-8415-5a13b07a7b89.',NULL,NULL,NULL,0,1),(13,NULL,NULL,1,1,'2014-08-12 06:54:05','4b1404dd-f49d-43b0-ba15-ef415ddcf330.','8927366e-f4ee-41d7-bc5d-a8d3489ffab9.',NULL,NULL,0,1),(14,NULL,NULL,1,1,'2014-08-12 06:55:22','074eddc7-5b1d-47f7-b2c3-ffe6fe0e9b0e.','71cde22a-d136-4678-a12d-0f00b0987a46.','9d002609-3d4c-405b-beb0-d24642e541ac.',NULL,0,1),(15,NULL,NULL,1,1,'2014-08-12 07:40:03','6fcc32e1-65b8-4b49-9ee2-a38bc7fe1295.',NULL,NULL,NULL,0,1),(16,NULL,NULL,1,1,'2014-08-12 07:44:44','7f0dc9e8-6ce5-41bf-a492-61507318c9b8.',NULL,NULL,NULL,0,1),(17,'物业啊，你们不厚道',NULL,1,1,'2014-08-12 08:18:54','8858da4b-1eda-4817-acd6-95b18adaca9f.gif','d064f559-999b-4700-9040-58e77d03d098.jpg',NULL,NULL,0,1),(18,'无','52e26330-d203-4d13-a9c3-c8fa2d321801.',1,1,'2014-08-12 08:21:37',NULL,NULL,NULL,NULL,0,1),(19,'无','10cfbe42-ae17-4eab-b261-01132b70e2f8.',1,1,'2014-08-12 08:24:24',NULL,NULL,NULL,NULL,0,1),(20,'请填写您的建议。','success.wav',1,1,'2014-08-12 08:24:47',NULL,NULL,NULL,NULL,0,1),(21,'请填写您的建议。','ae99d3ac-04a0-4e01-a761-735e14253684.',1,1,'2014-08-12 08:29:56','2e895453-8020-42de-add2-91d20ed36997.','cabd54e5-fac5-4f72-b40b-5e36554cdd42.','8c5a20ec-4333-48aa-b281-9292a7c31237.',NULL,0,1),(22,'请填写您的建议。',NULL,1,1,'2014-08-14 14:18:08',NULL,NULL,NULL,NULL,0,1),(23,'请填写您的建议。',NULL,1,1,'2014-08-19 06:45:59',NULL,NULL,NULL,NULL,0,1),(24,'请填写您的建议。','4001bae3-b4c7-45d8-a0c4-12fc1db2ed7c.amr.wav',1,1,'2014-08-28 02:44:04','bc00ccf6-6ddc-4377-b7a9-c665007aa80f.png','48ab7152-0ef4-41d0-8f36-46873f1005f1.png','37d87bc3-b5a9-4446-a11c-fa89c107c144.png',NULL,0,1),(25,'1111',NULL,1,1,'2014-10-21 08:17:50',NULL,NULL,NULL,NULL,0,1),(26,'2222',NULL,1,1,'2014-10-21 08:18:45',NULL,NULL,NULL,NULL,0,1),(27,'无',NULL,1,1,'2014-10-21 08:54:20','96d1790f-5593-4e08-b09d-ae67ef8726e7.jpg',NULL,NULL,NULL,0,1),(28,'556656',NULL,1,1,'2014-10-21 08:57:54',NULL,NULL,NULL,NULL,0,1),(29,'无','cb1366e5-e19f-447f-b283-49660a9be164.jpg',1,1,'2014-10-21 09:02:54',NULL,NULL,NULL,NULL,0,1),(30,'无','9b7448dd-6a4f-4fda-8f3c-386b9f001f1a.jpg',1,1,'2014-10-21 09:06:24',NULL,NULL,NULL,NULL,0,1),(31,'无','f98c9e6f-663f-4774-b7a3-7cec667b765f.jpg',1,1,'2014-10-21 09:07:18',NULL,NULL,NULL,NULL,0,1),(32,'466',NULL,1,1,'2014-10-21 09:07:41',NULL,NULL,NULL,NULL,0,1),(33,'45555',NULL,1,1,'2014-10-21 09:10:44',NULL,NULL,NULL,NULL,0,1),(34,'dgjj',NULL,1,1,'2014-10-21 10:51:48',NULL,NULL,NULL,NULL,0,1),(35,'ertuu',NULL,1,1,'2014-10-21 10:52:55',NULL,NULL,NULL,NULL,0,1),(36,'无','231509a6-fb45-4788-973a-1661d1c996c1.jpg',1,1,'2014-10-21 11:34:23',NULL,NULL,NULL,NULL,0,1),(37,'6552',NULL,1,1,'2014-10-21 11:53:48',NULL,NULL,NULL,NULL,0,1),(38,'无','e7ca0e0b-5647-4c6e-91f4-4fb3004b0ead.jpg',1,1,'2014-10-21 11:54:53',NULL,NULL,NULL,NULL,0,1),(39,'某天',NULL,1,1,'2014-10-21 12:02:10',NULL,NULL,NULL,NULL,0,1),(40,'6866522',NULL,1,1,'2014-10-21 12:22:48',NULL,NULL,NULL,NULL,0,1),(41,'55666665',NULL,1,1,'2014-10-21 12:26:55',NULL,NULL,NULL,NULL,0,1),(42,'566756',NULL,1,1,'2014-10-21 12:33:52',NULL,NULL,NULL,NULL,0,1),(43,'66526',NULL,1,1,'2014-10-21 12:42:12',NULL,NULL,NULL,NULL,0,1),(44,'566656',NULL,1,1,'2014-10-21 12:44:09',NULL,NULL,NULL,NULL,0,1),(45,'866666',NULL,1,1,'2014-10-21 12:45:50',NULL,NULL,NULL,NULL,0,1),(46,'53666',NULL,1,1,'2014-10-21 12:56:13',NULL,NULL,NULL,NULL,0,1),(47,'556669',NULL,1,1,'2014-10-21 13:06:13',NULL,NULL,NULL,NULL,0,1),(48,'6553666',NULL,1,1,'2014-10-21 13:16:46',NULL,NULL,NULL,NULL,0,1),(49,'242666666',NULL,1,1,'2014-10-21 13:24:24',NULL,NULL,NULL,NULL,0,1),(50,'55566',NULL,1,1,'2014-10-21 13:32:26',NULL,NULL,NULL,NULL,0,1),(51,'无',NULL,1,1,'2014-10-21 13:35:25','d830cf23-5c8f-41a5-afb7-20c561d4cbb4.jpg',NULL,NULL,NULL,0,1),(52,'无',NULL,1,1,'2014-10-21 13:35:28','44ab477d-c5a4-4d39-bc8c-071dbbfc9ac7.jpg',NULL,NULL,NULL,0,1),(53,'无',NULL,1,1,'2014-10-21 13:35:32','3bc8ec4e-c728-4224-9f00-c11a01099aed.jpg',NULL,NULL,NULL,0,1),(54,'无',NULL,1,1,'2014-10-21 13:35:35','27e2e2a2-524d-4721-a6dc-c1ed7dadf903.jpg',NULL,NULL,NULL,0,1),(55,'5566',NULL,1,1,'2014-10-21 13:50:52',NULL,NULL,NULL,NULL,0,1),(56,'566555',NULL,1,1,'2014-10-21 13:55:16',NULL,NULL,NULL,NULL,0,1),(57,'55666',NULL,1,1,'2014-10-21 13:57:16',NULL,NULL,NULL,NULL,0,1),(58,'856666',NULL,1,1,'2014-10-21 14:00:48',NULL,NULL,NULL,NULL,0,1),(59,'55666',NULL,1,1,'2014-10-21 14:02:28',NULL,NULL,NULL,NULL,0,1),(60,'5565556566',NULL,1,1,'2014-10-21 14:05:55',NULL,NULL,NULL,NULL,0,1),(61,'5566',NULL,1,1,'2014-10-21 14:09:16',NULL,NULL,NULL,NULL,0,1),(62,'55666',NULL,1,1,'2014-10-21 14:15:55',NULL,NULL,NULL,NULL,0,1),(63,'5666',NULL,1,1,'2014-10-21 14:27:14',NULL,NULL,NULL,NULL,0,1),(64,'55666',NULL,1,1,'2014-10-22 00:33:56',NULL,NULL,NULL,NULL,0,1),(65,'无',NULL,1,1,'2014-10-22 00:41:32','c87ef416-816d-4f71-8eae-2c05677cf633.jpg',NULL,NULL,NULL,0,1),(66,'无',NULL,1,1,'2014-10-22 00:55:14','31e86668-86bd-4021-a859-05b4f7bbe2c2.jpg',NULL,NULL,NULL,0,1),(67,'无',NULL,1,1,'2014-10-22 01:11:38','ce35a722-88ed-491d-a30a-da020557f508.jpg',NULL,NULL,NULL,0,1),(68,'无',NULL,1,1,'2014-10-22 01:12:26','0bd5b2a0-4dea-4086-96bc-a65c6f2541dc.jpg',NULL,NULL,NULL,0,1),(69,'无',NULL,1,1,'2014-10-22 01:29:42','1a4081bc-19cf-478e-b16f-b63700204fe0.jpg','77249668-6196-4f20-a25a-f6dd0baeb631.jpg','6341ebbd-c463-46fc-8eca-9bf384b4e054.jpg',NULL,0,1),(70,'无',NULL,1,1,'2014-10-22 01:33:03','3f62521f-0efb-4a31-b237-1222abbebb4d.jpg','a3b1911b-4fc3-461e-93be-398e890d4169.jpg','483f8933-7d82-4c72-bddc-9a240d796c98.jpg',NULL,0,1),(71,'111111111111','e6c21fa5-8fef-44e5-8710-2d71532357e1.jpg',1,1,'2014-10-22 01:37:47','b0d7f075-70b5-4c3f-b2b9-771b3e8267eb.jpg','bfef81b0-53e3-4f40-bd77-0ba3a216a1f1.jpg','4c9dd98b-431b-4c03-9598-7bd49c7de447.jpg',NULL,0,1),(72,'6666666','768db5ec-2e2d-4261-9c5d-0caa8b9abdf7.jpg',1,1,'2014-10-22 01:43:06','a71c1f3f-8f13-4732-bad8-ce48aef2e1c4.jpg','406e596c-473c-4cd7-b349-31d12e5fd019.jpg','d1fc2d06-b779-496e-a1e0-991613f9591d.jpg',NULL,0,1),(73,'龙猫',NULL,1,1,'2014-10-22 02:38:11','26caddd6-6447-4e68-b9a7-870c233549f4.jpg','efa42f00-4c0d-4a35-acdd-73c12f4e1649.jpg','971a4c81-760e-4b2f-970d-b17cea733cbf.jpg',NULL,0,1),(74,'龙虾米',NULL,1,1,'2014-10-22 02:40:36',NULL,NULL,NULL,NULL,0,1),(75,'龙虾米',NULL,1,1,'2014-10-22 02:41:42',NULL,NULL,NULL,NULL,0,1),(76,'无','2798cc20-6aa6-462b-a4b4-cfcd085b011d.jpg',1,1,'2014-10-22 02:55:18',NULL,NULL,NULL,NULL,0,1),(77,'etyy',NULL,1,1,'2014-10-22 05:19:01',NULL,NULL,NULL,NULL,0,1),(78,'cbb',NULL,1,1,'2014-10-22 05:19:43',NULL,NULL,NULL,NULL,0,1),(79,'driii',NULL,1,1,'2014-10-22 05:20:03',NULL,NULL,NULL,NULL,0,1),(80,'dfhki',NULL,1,1,'2014-10-22 05:22:33',NULL,NULL,NULL,NULL,0,1),(81,'dfhh',NULL,1,1,'2014-10-22 05:25:31',NULL,NULL,NULL,NULL,0,1),(82,'sdjjjk',NULL,1,1,'2014-10-22 05:39:18',NULL,NULL,NULL,NULL,0,1),(83,'sdjjjk',NULL,1,1,'2014-10-22 05:40:33',NULL,NULL,NULL,NULL,0,1),(84,'556886666.','a917eeb3-09cc-43c2-a0e3-f08c9f9a991b.jpg',1,1,'2014-10-22 05:41:51','0d4f2b07-9dcb-42c1-9c1e-ec262b887341.jpg','c2c032f2-947e-41eb-96fa-255045d76434.jpg','ab17674b-dd03-4391-a1e1-39b0b47674a9.jpg',NULL,0,1),(85,'统统',NULL,1,1,'2014-10-22 05:45:08',NULL,NULL,NULL,NULL,0,1),(86,'sdjjjk',NULL,1,1,'2014-10-22 05:45:17',NULL,NULL,NULL,NULL,0,1),(87,'5566852',NULL,1,1,'2014-10-22 05:54:32',NULL,NULL,NULL,NULL,0,1),(88,'856523:9',NULL,1,1,'2014-10-22 05:55:35','1bcedacb-e15b-42f9-9ca9-ca7113aded3d.jpg',NULL,NULL,NULL,0,1),(89,'5565566',NULL,1,1,'2014-10-22 05:58:05',NULL,NULL,NULL,NULL,0,1),(90,'xcghjj',NULL,1,1,'2014-10-22 05:59:57',NULL,NULL,NULL,NULL,0,1),(91,'xdgjjkkouu',NULL,1,1,'2014-10-22 06:01:18',NULL,NULL,NULL,NULL,0,1),(92,'5565666','ebace62c-2f95-4909-a666-c0826cef0497.jpg',1,1,'2014-10-22 06:05:10','49437802-a9ce-407e-b9c6-193e28f13e5c.jpg',NULL,NULL,NULL,0,1),(93,'fjjjj',NULL,1,1,'2014-10-22 06:05:27',NULL,NULL,NULL,NULL,0,1),(94,'56655566','d250d034-828d-493e-9973-8fa7b15d8777.jpg',1,1,'2014-10-22 06:13:36','117c16c5-d54b-4eeb-9471-c3677627627e.jpg',NULL,NULL,NULL,0,1),(95,'4268996566','c986ef18-f9e1-47ce-b4b5-98657af6a6fa.jpg',1,1,'2014-10-22 06:15:18','6e54f62c-e2f3-425f-b11e-0f60c58cfcfc.jpg','e60f10f1-9074-40f3-94fd-bd88682c5739.jpg','ec2f6c4b-39af-4d37-b35e-0c729e8f6b2f.jpg',NULL,0,1),(96,'4285365566','8206993d-0e5f-4634-b58d-3d51318541ea.jpg',1,1,'2014-10-22 06:17:07','fa0e825e-e0fd-42e3-945d-a785163293dc.jpg','f89552e4-bf63-4328-92c5-d37aa2d7b7de.jpg','8b3a11e3-c121-41e9-90d7-8b8791021a99.jpg',NULL,0,1),(97,'456856652669665','4799cb30-6f90-47c6-b6be-8b0b36ebd847.jpg',1,1,'2014-10-22 06:19:01','b691975e-60ce-4dc2-9310-d1c2be6be191.jpg','901eb15a-6c0b-4987-a06d-4eb1f36beef4.jpg','529d8856-8faf-4468-b5d6-d2e79de94f60.jpg',NULL,0,1),(98,'无','0dd83000-29f9-4efe-ae9b-541d133c23c2.jpg',1,1,'2014-10-22 06:41:20',NULL,NULL,NULL,NULL,0,1),(99,'无','d50baac4-2e17-425f-8a7b-f478c945014d.jpg',1,1,'2014-10-22 06:41:49','17a696bc-d5ed-4d46-894e-1b9d963d942b.jpg','fec5496f-dd97-4dfa-97dd-7eb507645de0.jpg',NULL,NULL,0,1),(100,'556866669','51e909be-513a-4037-a848-8100b684df14.jpg',1,1,'2014-10-22 06:43:22','5804205e-92c7-4f95-b705-2b37b6a5a673.jpg','013cf1de-3e76-4280-aab1-cd18641dbb6e.jpg','f32e085e-9ef6-42ae-92a4-8794d29b5d14.jpg',NULL,0,1),(101,'无','e3da371a-a997-42fe-95ae-34e8771e4500.jpg',1,1,'2014-10-22 06:48:37',NULL,NULL,NULL,NULL,0,1),(102,'了咯了了咯',NULL,1,1,'2014-10-23 08:22:47',NULL,NULL,NULL,NULL,0,1),(103,'同咯',NULL,1,1,'2014-10-24 01:48:14',NULL,NULL,NULL,NULL,0,1),(104,'565566',NULL,10,10,'2014-10-29 08:14:04',NULL,NULL,NULL,NULL,0,1),(105,'556666',NULL,1,1,'2014-10-30 05:42:42',NULL,NULL,NULL,NULL,0,1),(106,'856666',NULL,12,12,'2014-10-30 06:51:58',NULL,NULL,NULL,NULL,0,1),(107,'856665',NULL,12,12,'2014-10-30 06:59:17',NULL,NULL,NULL,NULL,0,1),(108,'请填写您的建议。',NULL,1,1,'2014-11-24 13:13:40',NULL,NULL,NULL,NULL,0,1),(109,'小区门口的路灯坏了，找师傅修一下。','c1333e45-f45e-47a1-864e-2ba4d5a72718.amr',1,1,'2014-11-26 12:27:25','4969b00f-2221-4d2d-9dc3-06f3eb375556.png','815ddd2e-1d96-4aea-af0d-79688b8e4fe0.png','d93313cc-9cd4-4506-b2dc-9a8502686a88.png',NULL,0,1),(110,'请填写您的建议。',NULL,1,1,'2014-11-26 12:29:23',NULL,NULL,NULL,NULL,0,1),(111,'请填写您的建议。',NULL,1,1,'2014-11-26 12:29:37',NULL,NULL,NULL,NULL,0,1),(112,'请填写您的hahhaha',NULL,1,1,'2014-11-26 12:31:21',NULL,NULL,NULL,NULL,0,1),(113,'小区门口的路灯坏了，找师傅修一下。','4707aec1-3c84-402c-90dc-5956422e0616.amr',1,1,'2014-11-26 12:36:36','814b12f0-ffd8-4a23-989c-f2060bb2aa8c.png','241a6f3b-4f3a-4b69-a5b6-6a22c2e9d863.png','02de9d5f-06cf-47be-935c-8244d734a69f.png',NULL,0,1),(114,'小区门口的路灯坏了，找师傅修一下。','a816d222-95c8-4ecb-bf19-00134b15c4c7.amr',1,1,'2014-11-26 12:37:53','3564fdeb-e3ff-4024-9b77-a7cac7f2f9a5.png','1edbbd13-0ac9-4c6b-99cb-041ff99512eb.png','b786c134-6117-4b64-b07f-f100adb40a11.png','您的投诉已受理，我们会尽快进行处理，谢谢！',1,1),(115,'1542','474c2798-345f-46c9-b8f9-25aafbb969b8.jpg',12,12,'2014-11-28 08:10:16','8c54917b-ee7b-4100-b2a7-ac7819c901fb.jpg',NULL,NULL,NULL,0,1),(116,'无','ed8dbafa-7a19-4e2e-8282-21975309835a.jpg',12,12,'2014-12-11 02:49:55',NULL,NULL,NULL,NULL,0,1),(117,'无','9651ed43-bede-4877-8d52-a2093da56899.jpg',23,23,'2015-01-04 12:45:06',NULL,NULL,NULL,NULL,0,1),(118,'无','1d2a4430-e255-4a1e-86d2-0710adb13887.jpg',23,23,'2015-01-04 12:46:36',NULL,NULL,NULL,NULL,0,1),(119,'无','5bece256-a9ec-4992-9125-1c34b7bb33d8.jpg',23,23,'2015-01-04 14:07:50',NULL,NULL,NULL,NULL,0,1),(120,'无','7e85cd4f-c22c-4206-a2b4-f7b105bdc06b.jpg',23,23,'2015-01-04 14:12:32',NULL,NULL,NULL,NULL,0,1),(121,'无','bfc5aca6-952d-42b3-b4ba-9a7699826062.jpg',23,23,'2015-01-05 01:00:47',NULL,NULL,NULL,NULL,0,1),(122,'无','3f1ab5a4-bc8a-4473-91ee-4aeac205c50c.jpg',23,23,'2015-01-05 01:18:05','606b9da2-ca55-4993-8a44-6a324e71b72c.jpg',NULL,NULL,NULL,0,1),(123,'无','d3610a35-d3c7-4fcf-a6ff-f161823429d2.amr.wav',23,23,'2015-01-05 01:32:59','00d57ea9-4caf-4196-9cd7-9ed7c31384af.png','fbb26abe-200f-4eef-a2d8-fbb08a62f3b0.png',NULL,NULL,0,1),(124,'计算',NULL,23,23,'2015-01-21 05:46:36',NULL,NULL,NULL,NULL,1,1),(125,'8555',NULL,23,23,'2015-01-21 06:12:15',NULL,NULL,NULL,NULL,1,1),(126,'55669655555','221ca2d1-0e02-4d6c-8ae7-2141d38fb496.amr.wav',23,23,'2015-01-21 06:40:52',NULL,NULL,NULL,NULL,0,1),(127,'无','5f2e51ad-ba02-4034-9b09-0d8103d46562.amr.wav',23,23,'2015-01-21 08:41:55','b0459bbb-8b37-4034-93bb-9c328cbab9d6.png','fb29cf62-2469-4b7e-9283-a23a63697bf7.png','f7dc7d79-99ac-43be-b587-99fc94230be0.png',NULL,0,1),(128,'fgjjk','0fb4891a-20bd-4302-8b6c-369d0785c865.amr.wav',23,23,'2015-01-22 12:58:12','b27c0289-be12-4ab3-8f43-2982d8b7dcee.png','12188805-8b32-47bc-afa5-e3a20e2813b6.png','8cc133ec-dae5-41c7-abca-20aa84f05fda.png',NULL,0,1),(129,'小区的停车位不够用，58712552854588241262844265446358272539393828415239392717283939253636392839','8b41c543-8513-4508-9d0a-d85c3e1bf6e1.amr.wav',23,23,'2015-01-23 02:00:20',NULL,NULL,NULL,NULL,0,1),(130,'挺好的',NULL,35,35,'2015-01-23 07:06:51',NULL,NULL,NULL,NULL,0,1),(131,'无','dc23e96a-edfb-4190-b580-30af4df8f263.amr.wav',23,23,'2015-01-23 07:36:44','24987461-9b3b-4d2a-a6e7-c1c93f17702c.png',NULL,NULL,NULL,0,1),(132,'Asdfghjkl','409e59dd-fc97-4264-a9c9-09d392595d24.amr.wav',30,30,'2015-02-01 11:50:33',NULL,NULL,NULL,NULL,0,1);
/*!40000 ALTER TABLE `suggestion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suggestion_history`
--

DROP TABLE IF EXISTS `suggestion_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suggestion_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `suggestion_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `last_editor` int(11) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_edit_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suggestion_history`
--

LOCK TABLES `suggestion_history` WRITE;
/*!40000 ALTER TABLE `suggestion_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `suggestion_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `room` varchar(100) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `job` varchar(100) DEFAULT NULL,
  `passwd` varchar(100) DEFAULT NULL,
  `icon` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'justin','男','33-502',1,24,'医生','a76b7f25b6ba5ec51bd9fa42f4143b63c2495996e783baa4d9f8459d314f6ad2','86973051-8aa0-4575-a5dc-112d22e4866d.jpg',NULL),(2,'小六','男','33-303',3,40,'教师','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','86973051-8aa0-4575-a5dc-112d22e4866d.jpg',NULL),(3,'马博洋','男','33-503',3,25,'学生','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','86973051-8aa0-4575-a5dc-112d22e4866d.jpg',NULL),(4,'小小蔬菜店','男',NULL,5,NULL,NULL,'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',NULL,NULL),(5,'小蓉书吧','男',NULL,5,NULL,NULL,'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',NULL,NULL),(6,'心缘美容院','男','business',5,NULL,NULL,'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',NULL,NULL),(7,'开心茶馆','男','business',5,NULL,NULL,'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',NULL,NULL),(8,'_gao','女','306',3,NULL,'教练','94edf28c6d6da38fd35d7ad53e485307f89fbeaf120485c8d17a43f323deee71',NULL,NULL),(9,'wgao','女','658',3,NULL,NULL,'937377f056160fc4b15e0b770c67136a5f03c15205b4d3bf918268fefa2c6d0a',NULL,NULL),(10,'qqqqqq','女','655',3,NULL,NULL,'937377f056160fc4b15e0b770c67136a5f03c15205b4d3bf918268fefa2c6d0a',NULL,NULL),(11,'gggggg','男','5566',5,1,'教练','937377f056160fc4b15e0b770c67136a5f03c15205b4d3bf918268fefa2c6d0a',NULL,NULL),(13,'aaaa','男','568',3,NULL,NULL,'937377f056160fc4b15e0b770c67136a5f03c15205b4d3bf918268fefa2c6d0a',NULL,NULL),(14,'hhhh','男','812',3,NULL,NULL,'937377f056160fc4b15e0b770c67136a5f03c15205b4d3bf918268fefa2c6d0a',NULL,NULL),(15,'ssss','男','701',3,NULL,NULL,'937377f056160fc4b15e0b770c67136a5f03c15205b4d3bf918268fefa2c6d0a',NULL,NULL),(18,'cccc','男','505',6,NULL,NULL,'94edf28c6d6da38fd35d7ad53e485307f89fbeaf120485c8d17a43f323deee71',NULL,'1578976766'),(20,'wwww','男','3-1-1104',3,NULL,NULL,'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',NULL,NULL),(22,'nnnn','男','156',6,NULL,NULL,'dca3af231e5e1c6bf93f35a3fdd1c235c9fea05821696d4c1fcf718743f0fe00',NULL,'13568786767'),(23,'sssss','男','603',3,1,'工人','937377f056160fc4b15e0b770c67136a5f03c15205b4d3bf918268fefa2c6d0a','41d3dcf8-6718-4277-b011-6ab6982be726.png',NULL),(24,'六妹','女',NULL,6,NULL,NULL,NULL,NULL,'13784563098'),(25,'hjjgggfj',NULL,'566',3,NULL,NULL,'937377f056160fc4b15e0b770c67136a5f03c15205b4d3bf918268fefa2c6d0a',NULL,NULL),(26,'baidu',NULL,'5663',3,NULL,NULL,'937377f056160fc4b15e0b770c67136a5f03c15205b4d3bf918268fefa2c6d0a',NULL,NULL),(27,'jiaowogao','女','sd',3,1111,'sa','1f603ec546cdce03c8dccba64fdcdddac49abc6a9dd32fc58c8da680914766a2','b9f18ecb-472a-48d1-8291-a9347852d44e.png',NULL),(28,'jiaowoga',NULL,'8',3,NULL,NULL,'ef130314bf8a5dd8de79129575ef4daf5aaf0c474ea97ad5ad6bdef344cc6437',NULL,NULL),(29,'gaodatui',NULL,'603',3,NULL,NULL,'fad443c53e8aa0c246d381b9c2bb852af1785f852fdb8c9b4605435b15168d4e',NULL,NULL),(30,'gaogaogao','男','603',1,23,'student','a428b0aa0a921d071580903e509dda854af57e6e9ec5483b8f77988e011b0f1b','59225672-a259-4081-a09c-c5e21299c358.png',NULL),(31,'xiaogaogao',NULL,'603',1,NULL,NULL,'e77082ab643a3671a4fbfd06979f64608559d5f451ee849f31864d769d596192',NULL,NULL),(32,'gjfyghh',NULL,'906',3,NULL,NULL,'af41e68e1309fa29a5044cbdc36b90a3821d8807e68c7675a6c495112bc8a55f',NULL,NULL),(33,'djfjjgh',NULL,'866',3,NULL,NULL,'94edf28c6d6da38fd35d7ad53e485307f89fbeaf120485c8d17a43f323deee71',NULL,NULL),(34,'小面包',NULL,'110',1,NULL,NULL,'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855',NULL,NULL),(35,'qqqq','女','解决了',3,32,'医生','03dd9827702e5fbb556e32c1bc33a6c178b454c03d0a3ca8336f02c553d478e9','ea2ad171-1c4b-479b-9ac9-6de57e1c990f.png',NULL),(36,'rrrr',NULL,'6',3,NULL,NULL,'72239e8b21c5b0d1435b672ce16340acb3d9672bcfa890a1517a495853c61366',NULL,NULL),(37,'bbbb',NULL,'203',3,NULL,NULL,'4625fd63b0e96fc0d656ae7381605e48d4a0f63a319fc743adf22688613883c7',NULL,NULL),(38,'wwwww',NULL,'4667',3,NULL,NULL,'937377f056160fc4b15e0b770c67136a5f03c15205b4d3bf918268fefa2c6d0a',NULL,NULL),(39,'qqqqq',NULL,'qqqq',0,0,NULL,'b6197fe0d62a4e463edd2925382d4d268c4fce0859378682608efa4fda326f26',NULL,NULL),(40,'qqqw',NULL,'111',0,0,NULL,'b6197fe0d62a4e463edd2925382d4d268c4fce0859378682608efa4fda326f26',NULL,NULL),(41,'aaaaa',NULL,'aaaa',0,0,NULL,'ed02457b5c41d964dbd2f2a609d63fe1bb7528dbe55e1abf5b52c249cd735797',NULL,NULL),(42,'chen111',NULL,'111',0,0,NULL,'bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a',NULL,NULL),(43,'chen1111',NULL,'111',0,0,NULL,'bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a',NULL,NULL),(44,'a1111',NULL,'111',0,0,NULL,'bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a',NULL,NULL),(45,'q111111',NULL,'111',0,0,NULL,'bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a',NULL,NULL),(46,'q222222',NULL,'577',0,0,NULL,'937377f056160fc4b15e0b770c67136a5f03c15205b4d3bf918268fefa2c6d0a',NULL,NULL),(47,'q2222222',NULL,'32',0,0,NULL,'937377f056160fc4b15e0b770c67136a5f03c15205b4d3bf918268fefa2c6d0a',NULL,NULL),(48,'q333333',NULL,'23',0,23,NULL,'bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a',NULL,NULL),(49,'q555555',NULL,'34',0,23,NULL,'bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a',NULL,NULL),(50,'qq11',NULL,'11',0,11,NULL,'bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a',NULL,NULL),(51,'qqqqqq','女','1',3,15,'112','bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a','33a24a41-28b9-4393-908c-1874f91942ef.png',NULL),(52,'gaodada',NULL,'603',1,NULL,NULL,'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',NULL,NULL),(53,'fffff',NULL,'35',3,NULL,NULL,'937377f056160fc4b15e0b770c67136a5f03c15205b4d3bf918268fefa2c6d0a',NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-08 14:39:47
