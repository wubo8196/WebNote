/*
Navicat MySQL Data Transfer

Source Server         : 阿里云ECS
Source Server Version : 50721
Source Host           : 106.14.124.85:3306
Source Database       : WebNote

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-04-21 13:55:20
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of NoteBook
-- ----------------------------
INSERT INTO `NoteBook` VALUES ('1', '1', '1', '回收站', null, '2018-04-20 17:05:21');
INSERT INTO `NoteBook` VALUES ('2', '1', '2', '默认笔记本', null, '2018-04-20 17:05:21');
INSERT INTO `NoteBook` VALUES ('3', '1', '2', 'JAVA', null, '2018-04-21 00:38:34');

-- ----------------------------
-- Table structure for NoteFavorite
-- ----------------------------
DROP TABLE IF EXISTS `NoteFavorite`;
CREATE TABLE `NoteFavorite` (
  `favoriteId` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`favoriteId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of NoteFavorite
-- ----------------------------
INSERT INTO `NoteFavorite` VALUES ('1');

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
  PRIMARY KEY (`userId`),
  CONSTRAINT `FK8ul22csbs4aicm03jbrprrat7` FOREIGN KEY (`userId`) REFERENCES `NoteFavorite` (`favoriteId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of NoteUser
-- ----------------------------
INSERT INTO `NoteUser` VALUES ('1', '吴波', '18506188196@163.com', '1582723e7326b3a9e2995694e9ffbca0', '26b3d407-bf9c-4ca8-a9ce-75b0f92aaa4a', '1', '2018-04-20', '2018-04-20', '0', '我的云笔记', 'file-857fc1f1-0e43-4c38-8d1d-d2640c760281.jpg');

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
  `body` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  PRIMARY KEY (`noteId`),
  KEY `FKmjy2ym8q1tjcbfag795y6s5xu` (`noteUserId`),
  KEY `FK7ib7jphmt5wos41mt6he8swo8` (`note_bookId`),
  KEY `FK5kopr26u7nm6ywpwaqk6byq9u` (`note_favoriteId`),
  CONSTRAINT `FK5kopr26u7nm6ywpwaqk6byq9u` FOREIGN KEY (`note_favoriteId`) REFERENCES `NoteFavorite` (`favoriteId`),
  CONSTRAINT `FK7ib7jphmt5wos41mt6he8swo8` FOREIGN KEY (`note_bookId`) REFERENCES `NoteBook` (`bookId`),
  CONSTRAINT `FKmjy2ym8q1tjcbfag795y6s5xu` FOREIGN KEY (`noteUserId`) REFERENCES `NoteUser` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OneNote
-- ----------------------------
INSERT INTO `OneNote` VALUES ('1', '1', '1', '1', '欢迎使用云笔记', '欢迎来到您的笔记，您可以分享此笔记', '2018-04-20 17:05:21');

-- ----------------------------
-- Table structure for ShareNote
-- ----------------------------
DROP TABLE IF EXISTS `ShareNote`;
CREATE TABLE `ShareNote` (
  `shareId` bigint(20) NOT NULL AUTO_INCREMENT,
  `shareTitle` varchar(255) DEFAULT NULL,
  `shareDate` datetime DEFAULT NULL,
  `noteUser` varchar(255) DEFAULT NULL,
  `currency` double DEFAULT NULL,
  `viewTotal` int(11) DEFAULT NULL,
  PRIMARY KEY (`shareId`),
  CONSTRAINT `FK7dfye67nictkqu21m2391je0g` FOREIGN KEY (`shareId`) REFERENCES `OneNote` (`noteId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ShareNote
-- ----------------------------
