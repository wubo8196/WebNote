/*
Navicat MySQL Data Transfer

Source Server         : 阿里云ECS
Source Server Version : 50722
Source Host           : 106.14.124.85:3306
Source Database       : WebNote

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-06-19 22:39:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for NoteBook
-- ----------------------------
DROP TABLE IF EXISTS `NoteBook`;
CREATE TABLE `NoteBook` (
  `bookId` bigint(20) NOT NULL AUTO_INCREMENT,
  `book_userId` bigint(20) DEFAULT NULL,
  `noteType` bigint(20) DEFAULT NULL,
  `bookName` varchar(255) DEFAULT NULL,
  `bookDesc` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  PRIMARY KEY (`bookId`),
  KEY `FKl2fie5j3y2a5vi60x8ni32hqg` (`book_userId`),
  KEY `FKn64mpfvexueu76ljphmbk382d` (`noteType`),
  CONSTRAINT `FKl2fie5j3y2a5vi60x8ni32hqg` FOREIGN KEY (`book_userId`) REFERENCES `NoteUser` (`userId`),
  CONSTRAINT `FKn64mpfvexueu76ljphmbk382d` FOREIGN KEY (`noteType`) REFERENCES `NoteType` (`typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of NoteBook
-- ----------------------------
INSERT INTO `NoteBook` VALUES ('17', '7', '1', '回收站', null, '2018-05-19 01:06:22');
INSERT INTO `NoteBook` VALUES ('18', '7', '2', '默认笔记本', null, '2018-05-19 01:06:22');
INSERT INTO `NoteBook` VALUES ('19', '8', '1', '回收站', null, '2018-05-19 08:56:33');
INSERT INTO `NoteBook` VALUES ('20', '8', '2', '默认笔记本', null, '2018-05-19 08:56:33');
INSERT INTO `NoteBook` VALUES ('21', '8', '2', '笔记本一', '笔记本一', '2018-05-19 08:57:32');
INSERT INTO `NoteBook` VALUES ('22', '9', '2', '默认笔记本', null, '2018-05-31 17:26:50');
INSERT INTO `NoteBook` VALUES ('23', '9', '1', '回收站', null, '2018-05-31 17:26:50');
INSERT INTO `NoteBook` VALUES ('24', '10', '1', '回收站', null, '2018-05-31 17:32:43');
INSERT INTO `NoteBook` VALUES ('25', '10', '2', '默认笔记本', null, '2018-05-31 17:32:43');

-- ----------------------------
-- Table structure for NoteFavorite
-- ----------------------------
DROP TABLE IF EXISTS `NoteFavorite`;
CREATE TABLE `NoteFavorite` (
  `favoriteId` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`favoriteId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of NoteFavorite
-- ----------------------------
INSERT INTO `NoteFavorite` VALUES ('7');
INSERT INTO `NoteFavorite` VALUES ('8');
INSERT INTO `NoteFavorite` VALUES ('9');
INSERT INTO `NoteFavorite` VALUES ('10');

-- ----------------------------
-- Table structure for NoteType
-- ----------------------------
DROP TABLE IF EXISTS `NoteType`;
CREATE TABLE `NoteType` (
  `typeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(255) DEFAULT NULL,
  `typeDesc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of NoteType
-- ----------------------------
INSERT INTO `NoteType` VALUES ('1', '回收站', '');
INSERT INTO `NoteType` VALUES ('2', '默认笔记本', null);

-- ----------------------------
-- Table structure for NoteUser
-- ----------------------------
DROP TABLE IF EXISTS `NoteUser`;
CREATE TABLE `NoteUser` (
  `userId` bigint(20) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) DEFAULT NULL,
  `userEmail` varchar(255) DEFAULT NULL,
  `userPassword` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `vipEndDate` date DEFAULT NULL,
  `currency` double DEFAULT NULL,
  `userDesc` varchar(255) DEFAULT NULL,
  `userIcon` varchar(255) DEFAULT NULL,
  `privilege` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  CONSTRAINT `FK8ul22csbs4aicm03jbrprrat7` FOREIGN KEY (`userId`) REFERENCES `NoteFavorite` (`favoriteId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of NoteUser
-- ----------------------------
INSERT INTO `NoteUser` VALUES ('7', '雷为伟', '1451446750@qq.com', '1582723e7326b3a9e2995694e9ffbca0', '384ae2b1-9117-4143-8cfa-00fd4c90f5d7', '1', '2018-05-19', '2018-07-01', '20', '我是l', 'file-e419ccea-e63e-4af2-90b9-b816f7b31626.jpg', '9');
INSERT INTO `NoteUser` VALUES ('8', '吴波', '1850618819@163.COM', '1582723e7326b3a9e2995694e9ffbca0', 'c79603d6-980f-4342-8f1a-06c38226d44e', '1', '2018-05-19', '2018-05-01', '50', '我是吴波', 'file-4c34fa50-26c4-4ac7-90e4-351a9aa9f5d5.jpg', '0');
INSERT INTO `NoteUser` VALUES ('9', '123', '18506188196@qq.com', '1582723e7326b3a9e2995694e9ffbca0', 'ed2473b7-5d8b-414c-bcd9-c62a2ea0d67f', '0', '2018-05-31', '2018-05-01', '10', '123', '', '0');
INSERT INTO `NoteUser` VALUES ('10', '伍智光', '18506188196@163.com', '1582723e7326b3a9e2995694e9ffbca0', '475e444c-2849-4df6-8ef7-29b70e214685', '1', '2018-05-31', '2018-05-01', '10', '我是伍智光', 'file-469c7d0e-dc77-48b0-93ed-bca1ecb9e240.jpg', '0');

-- ----------------------------
-- Table structure for NoteUser_shareNoteSet
-- ----------------------------
DROP TABLE IF EXISTS `NoteUser_shareNoteSet`;
CREATE TABLE `NoteUser_shareNoteSet` (
  `user_shareId` bigint(20) NOT NULL,
  `elt` bigint(20) NOT NULL,
  PRIMARY KEY (`user_shareId`,`elt`),
  KEY `FK4p3mnf3p5ajbeugsdd3h414bl` (`elt`),
  CONSTRAINT `FK4p3mnf3p5ajbeugsdd3h414bl` FOREIGN KEY (`elt`) REFERENCES `ShareNote` (`shareId`),
  CONSTRAINT `FK6nx642j82wyw104sxjc6uhe0p` FOREIGN KEY (`user_shareId`) REFERENCES `NoteUser` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of NoteUser_shareNoteSet
-- ----------------------------
INSERT INTO `NoteUser_shareNoteSet` VALUES ('7', '35');
INSERT INTO `NoteUser_shareNoteSet` VALUES ('8', '35');
INSERT INTO `NoteUser_shareNoteSet` VALUES ('7', '36');
INSERT INTO `NoteUser_shareNoteSet` VALUES ('8', '36');

-- ----------------------------
-- Table structure for OneNote
-- ----------------------------
DROP TABLE IF EXISTS `OneNote`;
CREATE TABLE `OneNote` (
  `noteId` bigint(20) NOT NULL AUTO_INCREMENT,
  `noteUserId` bigint(20) DEFAULT NULL,
  `note_bookId` bigint(20) DEFAULT NULL,
  `note_favoriteId` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `body` text,
  `createDate` datetime DEFAULT NULL,
  PRIMARY KEY (`noteId`),
  KEY `FKmjy2ym8q1tjcbfag795y6s5xu` (`noteUserId`),
  KEY `FK7ib7jphmt5wos41mt6he8swo8` (`note_bookId`),
  KEY `FK5kopr26u7nm6ywpwaqk6byq9u` (`note_favoriteId`),
  CONSTRAINT `FK5kopr26u7nm6ywpwaqk6byq9u` FOREIGN KEY (`note_favoriteId`) REFERENCES `NoteFavorite` (`favoriteId`),
  CONSTRAINT `FK7ib7jphmt5wos41mt6he8swo8` FOREIGN KEY (`note_bookId`) REFERENCES `NoteBook` (`bookId`),
  CONSTRAINT `FKmjy2ym8q1tjcbfag795y6s5xu` FOREIGN KEY (`noteUserId`) REFERENCES `NoteUser` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OneNote
-- ----------------------------
INSERT INTO `OneNote` VALUES ('32', '7', '18', null, '欢迎使用云笔记', '欢迎来到您的笔记，您可以分享此笔记', '2018-05-19 01:06:22');
INSERT INTO `OneNote` VALUES ('33', '8', '20', null, '欢迎使用云笔记', '欢迎来到您的笔记，您可以分享此笔记', '2018-05-19 08:56:33');
INSERT INTO `OneNote` VALUES ('35', '7', '18', null, '笔记本1收费', '			大师傅士大夫十分<img src=\"/WebNote/oneNoteImg/file-f6020029-7164-4915-b903-e859fd24958e.jpg\" alt=\"undefined\"><img src=\"http://106.14.124.85:8080/WebNote/layui/images/face/2.gif\" alt=\"[哈哈]\">\r\n			', '2018-05-19 08:59:59');
INSERT INTO `OneNote` VALUES ('36', '8', '20', null, '笔记本1收费', '			大师傅士大夫十分<img src=\"/WebNote/oneNoteImg/file-f6020029-7164-4915-b903-e859fd24958e.jpg\" alt=\"undefined\"><img src=\"http://106.14.124.85:8080/WebNote/layui/images/face/2.gif\" alt=\"[哈哈]\">\r\n			', '2018-05-19 09:01:14');
INSERT INTO `OneNote` VALUES ('37', '7', '18', null, '笔记本1收费', '			大师傅士大夫十分<img src=\"/WebNote/oneNoteImg/file-f6020029-7164-4915-b903-e859fd24958e.jpg\" alt=\"undefined\"><img src=\"http://106.14.124.85:8080/WebNote/layui/images/face/2.gif\" alt=\"[哈哈]\">\r\n			', '2018-05-19 09:03:53');
INSERT INTO `OneNote` VALUES ('38', '8', '20', '8', '笔记一', '						大师傅士大夫十分<img src=\"/WebNote/oneNoteImg/file-f6020029-7164-4915-b903-e859fd24958e.jpg\" alt=\"undefined\"><img src=\"http://106.14.124.85:8080/WebNote/layui/images/face/2.gif\" alt=\"[哈哈]\">\r\n			\r\n			', '2018-05-24 04:40:01');
INSERT INTO `OneNote` VALUES ('39', '9', '22', '9', '欢迎使用云笔记', '欢迎来到您的笔记，您可以分享此笔记', '2018-05-31 17:26:50');
INSERT INTO `OneNote` VALUES ('40', '10', '25', '10', '欢迎使用云笔记', '欢迎来到您的笔记，您可以分享此笔记', '2018-05-31 17:32:43');

-- ----------------------------
-- Table structure for ShareNote
-- ----------------------------
DROP TABLE IF EXISTS `ShareNote`;
CREATE TABLE `ShareNote` (
  `shareId` bigint(20) NOT NULL AUTO_INCREMENT,
  `shareTitle` varchar(255) DEFAULT NULL,
  `shareDate` datetime DEFAULT NULL,
  `currency` double DEFAULT NULL,
  `viewTotal` int(11) DEFAULT NULL,
  `shareNote_userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`shareId`),
  KEY `FK94lnflgor7emj4v6mge4o6f3r` (`shareNote_userId`),
  CONSTRAINT `FK7dfye67nictkqu21m2391je0g` FOREIGN KEY (`shareId`) REFERENCES `OneNote` (`noteId`),
  CONSTRAINT `FK94lnflgor7emj4v6mge4o6f3r` FOREIGN KEY (`shareNote_userId`) REFERENCES `NoteUser` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ShareNote
-- ----------------------------
INSERT INTO `ShareNote` VALUES ('35', '笔记本1收费', '2018-05-19 09:00:25', '10', '3', '7');
INSERT INTO `ShareNote` VALUES ('36', '笔记本2收费', '2018-05-19 09:01:44', '50', '1', '7');

-- ----------------------------
-- Table structure for vipOrder
-- ----------------------------
DROP TABLE IF EXISTS `vipOrder`;
CREATE TABLE `vipOrder` (
  `vipOrderId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `orderState` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `orderMonth` bigint(20) DEFAULT NULL,
  `orderPrice` double DEFAULT NULL,
  PRIMARY KEY (`vipOrderId`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vipOrder
-- ----------------------------
INSERT INTO `vipOrder` VALUES ('33', '2018-05-19 02:40:01', '1', '7', '1', '10');
INSERT INTO `vipOrder` VALUES ('34', '2018-05-19 09:02:43', '1', '7', '1', '10');
INSERT INTO `vipOrder` VALUES ('35', '2018-05-31 17:34:14', '0', '10', '1', '10');
INSERT INTO `vipOrder` VALUES ('36', '2018-05-31 17:35:35', '0', '10', '1', '10');
INSERT INTO `vipOrder` VALUES ('37', '2018-05-31 17:35:36', '0', '10', '1', '10');
