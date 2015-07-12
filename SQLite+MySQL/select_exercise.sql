-- MySQL dump 10.13  Distrib 5.5.11, for Win32 (x86)
--
-- Host: localhost    Database: select_exercise
-- ------------------------------------------------------
-- Server version	5.5.11

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
-- Table structure for table `allgroup`
--

DROP TABLE IF EXISTS `allgroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `allgroup` (
  `gid` int(4) NOT NULL AUTO_INCREMENT,
  `gleader` char(15) DEFAULT NULL,
  `gsum` int(4) DEFAULT NULL,
  PRIMARY KEY (`gid`),
  KEY `gleader` (`gleader`),
  CONSTRAINT `allgroup_ibfk_1` FOREIGN KEY (`gleader`) REFERENCES `student` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allgroup`
--

LOCK TABLES `allgroup` WRITE;
/*!40000 ALTER TABLE `allgroup` DISABLE KEYS */;
INSERT INTO `allgroup` VALUES (1,'2012310200619',5),(2,'2012310200608',3),(3,'2012310200630',5),(4,'2012310200601',2),(5,'2012310200613',4),(6,'2012310200610',5),(7,'2012310200624',4);
/*!40000 ALTER TABLE `allgroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exercise`
--

DROP TABLE IF EXISTS `exercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exercise` (
  `eid` int(4) NOT NULL AUTO_INCREMENT,
  `etitle` varchar(50) DEFAULT NULL,
  `eteacher` char(20) DEFAULT NULL,
  `elevel` char(2) DEFAULT NULL,
  `esum` int(4) DEFAULT NULL,
  `eleftcount` int(4) DEFAULT NULL,
  `eispass` tinyint(1) DEFAULT NULL,
  `esummary` varchar(500) DEFAULT NULL,
  `efilepath` char(50) DEFAULT NULL,
  PRIMARY KEY (`eid`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise`
--

LOCK TABLES `exercise` WRITE;
/*!40000 ALTER TABLE `exercise` DISABLE KEYS */;
INSERT INTO `exercise` VALUES (1,'开发车辆调度软件','金星','A',3,5,1,'输入物流任务和车辆信息、计算最优调度方案、图形化输出调度方案。完成基本功能后，可添加动画界面。','D:\\files\\2014年软件综合实训出题金星.pdf'),(2,'营养餐饮软件','金星','A',3,5,1,'营养数据的增删改查、营养诊断和一天的配餐。','D:\\files\\2014年软件综合实训出题金星.pdf'),(3,'基因音乐软件','金星','A',3,5,1,'音乐速度、乐器的设置，输入待转换的氨基酸序列，设置氨基酸到音符的转换规则，实时听到转换出来的音乐','D:\\files\\2014年软件综合实训出题金星.pdf'),(4,'简易智能迷宫','金星','B',3,5,1,'用户可以输入网格的规模，设置迷宫的地图即起点，终点和障碍。','D:\\files\\2014年软件综合实训出题金星.pdf'),(5,'旅游查询系统','金星','B',3,5,1,'根据景点查找相应旅社的位置、安排及对其服务的评价。提供组团及自助游的信息的查询','D:\\files\\2014年软件综合实训出题金星.pdf'),(6,'本地文档管理软件','秦丽','B',3,5,1,'本项目主要实现对本机存放的各种格式的文件进行搜索，管理与分类等功能。','D:\\files\\2014年软件综合实训出题-秦丽.pdf'),(7,'参考文献管理软件','秦丽','A',4,5,1,'实现文献信息的录入，分方向或专业的个人资料库管理，生成指定要求的参考文献目录。','D:\\files\\2014年软件综合实训出题-秦丽.pdf'),(8,'英语词频统计器','秦丽','B',4,5,1,'本项目是一个用于英语文档词频统计与词法分析的软件。','D:\\files\\2014年软件综合实训出题-秦丽.pdf'),(9,'模拟银行存取款管理系统','秦丽','C',3,5,1,'本项目是一个银行定期与活期存取模拟系统。','D:\\files\\2014年软件综合实训出题-秦丽.pdf'),(10,'桌面sina微博','秦丽','A',5,0,1,'本项目是一个桌面sina微博浏览与发布系统。','D:\\files\\2014年软件综合实训出题-秦丽.pdf'),(11,'《计算机算法》网络学习系统的设计与开发','章英','B',5,5,1,'系统设有管理员、教师及学生，可自动生成试卷，学生答题，自动批阅试卷。','D:\\files\\2014年软件综合实训出题章英.pdf'),(12,'库存管理系统的设计与开发','章英','B',4,5,1,'设计与实现基于J2EE技术的库存管理系统，通过各模块的运行，对库存数据进行保存，打印数据分项和数据报表。','D:\\files\\2014年软件综合实训出题章英.pdf'),(13,'网上书店系统的设计与开发','章英','B',4,5,1,'实现图书分类浏览，增加、删除、修改、查询图书，网上购物等功能。','D:\\files\\2014年软件综合实训出题章英.pdf'),(14,'排序算法的动态图形可视化','章英','C',3,5,1,'编写应用软件清晰描述各种排序算法并设计一个演示系统。','D:\\files\\2014年软件综合实训出题章英.pdf'),(15,'基于LZ77标准的压缩软件设计与开发','向金海','A',5,5,1,'系统有数据压缩和数据解压两个功能。','D:\\files\\2014综合实训题目.pdf'),(16,'招牌系统网站的设计与开发','向金海','B',4,5,1,'有系统配置信息管理，普通会员及企业会员信息管理，公共信息管理4个模块。','D:\\files\\2014综合实训题目.pdf'),(17,'基于JAVA的FTP项目开发','黄钰','B',3,5,1,'该系统支持ASCII和二进制的传输方式，支持PORT和Passive，支持断点续传。','D:\\files\\黄钰－实践项目1.pdf'),(18,'基于JAVA的医学图像三维重建系统JV的构建','黄钰','B',5,5,1,'利用可视化开发包VTK，用JAVA实现切片数据的三维重建和可视化。','D:\\files\\黄钰－实践项目2.pdf'),(19,'基于JAVA的序列比对软件的开发与集成','黄钰','A',5,5,1,'利用JAVA实现若干序列对比算法，然后以系统的方式进行集成，有界面调用并能进行各算法结果的比较。','D:\\files\\黄钰－实践项目3.pdf'),(20,'基于Android的找厕所软件的设计与实现','赵良','B',3,5,1,'该系统要根据个人位置找最近厕所，可以查询厕所详情，并有厕所评论墙等。','D:\\files\\基于Andriod的找厕所软件的设计与实现.pdf'),(21,'汉字行编辑程序','赵良','B',3,5,1,'该系统是汉字编辑系统，实现汉字的编辑和输入。','D:\\files\\一个汉字行编辑程序的设计与实现.pdf'),(22,'基于Android的高校住院管理系统的设计与实现','陈仲民','B',5,5,1,'基于Android的高校住院管理系统，有入院、住院、出院管理以及系统维护。','D:\\files\\基于Android的高校住院管理系统的设计与实现陈仲民.pdf'),(23,'生物数据挖掘与分析系统设计与实现','刘建晓','B',4,5,1,'面向生物数据不同材料的基因组、转录组、表型组等多位组学的多种方法分析结果。','D:\\files\\生物数据挖掘与分析系统设计与实现.pdf'),(24,'基于JAVA的综合实训选题系统设计','余文君','A',5,5,1,'基于Java的综合实训选题系统，有出题、选题、学生选题、考勤管理。','D:\\files\\基于JAVA的综合实训选题系统设计-余文君.pdf'),(25,'基于Android平台的垃圾信息拦截系统设计与实现','王海燕','A',3,5,1,'支持短信正常收发，支持终端用户自主拦截，检测所采用算法的拦截频率。','D:\\files\\综合实训题目-王海燕2014.pdf'),(26,'小型关系型DBMS的设计与实现','王海燕','A',4,5,1,'支持磁盘空间管理和缓冲区管理，有树结构索引和哈希索引，支持关系操作求解，支持优化查询等。','D:\\files\\综合实训题目-王海燕2014.pdf'),(27,'课堂考勤管理系统的设计与实现','王海燕','C',3,5,1,'实现教师用户信息管理，考勤管理，统计查询。','D:\\files\\综合实训题目-王海燕2014.pdf'),(28,'小苹果','王海燕','A',5,2,0,'你是我的小苹果','D:\\files\\test1.pdf'),(29,'打土豪','王海燕','A',7,1,NULL,'打死土豪','D:\\files\\打土豪.pdf');
/*!40000 ALTER TABLE `exercise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `g_e`
--

DROP TABLE IF EXISTS `g_e`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `g_e` (
  `gid` int(4) NOT NULL DEFAULT '0',
  `eid` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`gid`,`eid`),
  KEY `eid` (`eid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `g_e`
--

LOCK TABLES `g_e` WRITE;
/*!40000 ALTER TABLE `g_e` DISABLE KEYS */;
INSERT INTO `g_e` VALUES (2,4),(7,9),(4,14),(5,17),(1,22),(3,24),(6,26);
/*!40000 ALTER TABLE `g_e` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_g`
--

DROP TABLE IF EXISTS `s_g`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_g` (
  `sid` char(15) NOT NULL DEFAULT '',
  `gid` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`sid`,`gid`),
  KEY `gid` (`gid`),
  CONSTRAINT `s_g_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `s_g_ibfk_2` FOREIGN KEY (`gid`) REFERENCES `allgroup` (`gid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_g`
--

LOCK TABLES `s_g` WRITE;
/*!40000 ALTER TABLE `s_g` DISABLE KEYS */;
INSERT INTO `s_g` VALUES ('2012310200602',1),('2012310200615',1),('2012310200619',1),('2012310200621',1),('2012310200628',1),('2012310200605',2),('2012310200608',2),('2012310200616',2),('2011311200810',3),('2012310200618',3),('2012310200623',3),('2012310200630',3),('2012310200632',3),('2012310200601',4),('2012310200614',4),('2012310200603',5),('2012310200609',5),('2012310200613',5),('2012310200627',5),('2012310200607',6),('2012310200610',6),('2012310200612',6),('2012310200620',6),('2012310200631',6),('2012310200606',7),('2012310200624',7),('2012310200625',7),('2012310200626',7);
/*!40000 ALTER TABLE `s_g` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `sid` char(15) NOT NULL,
  `sname` char(20) DEFAULT NULL,
  `sclass` char(10) DEFAULT NULL,
  `sphone` char(15) DEFAULT NULL,
  `sqq` char(15) DEFAULT NULL,
  `sisleader` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('','','','','',0),('2011311200810','黄志然','计科1202','13098806842','599953360',0),('2012310200601','吴一','计科1202','18202766849','820007565',1),('2012310200602','曾小豹','计科1202','13476020473','1374413525',0),('2012310200603','刘玮','计科1202','18202726479','952702753',0),('2012310200605','谭精明','计科1202','15927259705','15927259705',0),('2012310200606','杨春','计科1202','13016485041','13016485041',0),('2012310200607','程彬','计科1202','18672378087','545920428',0),('2012310200608','陈泽天','计科1202','18202758091','469819155',1),('2012310200609','温帆','计科1202','18202766191','763127815',0),('2012310200610','孙毅山','计科1202','18202787682','314994347',1),('2012310200612','王鸿洋','计科1202','18202775386','1123575144',0),('2012310200613','华昕','计科1202','18202790809','1589672653',1),('2012310200614','赵绪超','计科1202','18202762217','1037584688',0),('2012310200615','黄伟新','计科1202','18202710956','419321857',0),('2012310200616','胡鹏','计科1202','18202764505','494012931',0),('2012310200618','田奇','计科1202','13237102479','1549722424',0),('2012310200619','周珂','计科1202','18171012354','248384246',1),('2012310200620','吴再聪','计科1202','13026149129','2498819309',0),('2012310200621','张晨','计科1202','13469969772','314095842',0),('2012310200623','胡梦雪','计科1202','18202780953','564310740',0),('2012310200624','张梦亚','计科1202','15927107180','2406715954',1),('2012310200625','孙利','计科1202','18202736393','1518937091',0),('2012310200626','褚慧婷','计科1202','18202759377','1733782490',0),('2012310200627','王鑫','计科1202','18202730978','632470569',0),('2012310200628','余蕊','计科1202','18202774043','610323218',0),('2012310200630','金凯悦','计科1202','18202716500','895520892',1),('2012310200631','王瑶佳','计科1202','13237186892','157725170',0),('2012310200632','姚曼','计科1202','13377858292','122434628',0),('2012310200633','大苹果','计科1202','1231231234','4353453',1),('2012310200634','小天使','计科1202','13145942013','7777777',0),('2012310200635','新土豪','计科1203','1875674754','88888888',0);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_e`
--

DROP TABLE IF EXISTS `t_e`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_e` (
  `tid` char(15) NOT NULL DEFAULT '',
  `eid` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`tid`,`eid`),
  KEY `eid` (`eid`),
  CONSTRAINT `t_e_ibfk_1` FOREIGN KEY (`tid`) REFERENCES `teacher` (`tid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_e_ibfk_2` FOREIGN KEY (`eid`) REFERENCES `exercise` (`eid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_e`
--

LOCK TABLES `t_e` WRITE;
/*!40000 ALTER TABLE `t_e` DISABLE KEYS */;
INSERT INTO `t_e` VALUES ('1',1),('1',2),('1',3),('1',4),('1',5),('2',6),('2',7),('2',8),('2',9),('2',10),('3',11),('3',12),('3',13),('3',14),('4',15),('4',16),('5',17),('5',18),('5',19),('6',20),('6',21),('7',22),('8',23),('9',24),('10',25),('10',26),('10',27),('10',28),('10',29);
/*!40000 ALTER TABLE `t_e` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `tid` char(15) NOT NULL,
  `tname` char(20) DEFAULT NULL,
  `toffice` char(20) DEFAULT NULL,
  `tphone` char(15) DEFAULT NULL,
  `tqq` char(15) DEFAULT NULL,
  `tischecker` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES ('1','金星','逸夫楼B409','15927584398','78666463',0),('10','王海燕','逸夫楼B409','18971677910','57792916',1),('2','秦丽','逸夫楼B408','15607100234','62382668',0),('3','章英','逸夫楼B409','18639666996','25868943',0),('4','向金海','逸夫楼B408','15327112661','34589543',0),('5','黄钰','逸夫楼B411','18086010289','84365256',0),('6','赵良','逸夫楼B409','15527838508','28927835',0),('7','陈仲民','逸夫楼B409','15926493946','35784238',0),('8','刘建晓','逸夫楼B411','15533896423','47272681',0),('9','余文君','逸夫楼B408','13307180988','63578253',0);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `uid` char(15) NOT NULL,
  `password` char(35) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('10','09lEaAKkQll1XTjm0WPoIA=='),('2012310200618','yxFrRVPghJO+O6T/AfgJCA==');
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

-- Dump completed on 2014-08-29 19:53:48
