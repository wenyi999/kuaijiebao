/*
Navicat MySQL Data Transfer

Source Server         : connection
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : kjb

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-09-09 20:00:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for apply
-- ----------------------------
DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply` (
  `aid` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `amount` double(8,2) NOT NULL,
  `rate` double(8,2) NOT NULL,
  `repaytime` int(11) NOT NULL,
  `creditorname` varchar(255) DEFAULT NULL,
  `status` int(255) NOT NULL,
  PRIMARY KEY (`aid`),
  KEY `username` (`username`) USING BTREE,
  KEY `creditorname` (`creditorname`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apply
-- ----------------------------
INSERT INTO `apply` VALUES ('0', 'songboyi@777.cn', '1000.00', '0.05', '2', 'null', '0');
INSERT INTO `apply` VALUES ('1', 'luyixing@777.cn', '200.00', '0.01', '2', 'songboyi@777.cn', '1');
INSERT INTO `apply` VALUES ('2', 'songboyi@777.cn', '300.00', '0.03', '1', 'chenyonghua@777.cn', '2');
INSERT INTO `apply` VALUES ('3', 'songboyi@777.cn', '400.00', '0.10', '3', 'zhangxiao@777.cn', '2');
INSERT INTO `apply` VALUES ('4', 'songboyi@777.cn', '100.00', '0.02', '2', 'luyixing@777.cn', '2');
INSERT INTO `apply` VALUES ('5', 'chenyonghua@777.cn', '100.00', '0.08', '5', 'songboyi@777.cn', '1');
INSERT INTO `apply` VALUES ('6', 'chenyonghua@777.cn', '12300.00', '0.02', '12', '674041200@777.cn', '1');
INSERT INTO `apply` VALUES ('7', 'songboyi@777.cn', '1200.00', '0.05', '3', 'luyixing@777.cn', '2');
INSERT INTO `apply` VALUES ('8', '674041200@777.cn', '1200.00', '0.05', '4', 'songboyi@777.cn', '1');
INSERT INTO `apply` VALUES ('9', 'zhangxiao@777.cn', '100.00', '0.12', '15', 'luyixing@777.cn', '2');
INSERT INTO `apply` VALUES ('10', 'chenyonghua@777.cn', '100.00', '0.03', '11', 'songboyi@777.cn', '1');
INSERT INTO `apply` VALUES ('11', 'luyixing@777.cn', '100.00', '0.03', '12', 'songboyi@777.cn', '1');
INSERT INTO `apply` VALUES ('12', 'luyixing@777.cn', '123400.00', '0.07', '24', 'songboyi@777.cn', '1');
INSERT INTO `apply` VALUES ('13', 'songboyi@777.cn', '100.00', '0.05', '12', 'null', '0');
INSERT INTO `apply` VALUES ('14', 'songboyi@777.cn', '1000.00', '0.05', '12', 'null', '0');
INSERT INTO `apply` VALUES ('15', 'songboyi@777.cn', '1000.00', '0.05', '12', 'null', '0');
INSERT INTO `apply` VALUES ('16', 'songboyi@777.cn', '1000.00', '0.05', '10', 'null', '0');
INSERT INTO `apply` VALUES ('17', 'zhangxiao@777.cn', '2000.00', '0.05', '3', 'null', '0');
INSERT INTO `apply` VALUES ('18', 'songboyi@777.cn', '1000.00', '0.05', '12', 'null', '0');
INSERT INTO `apply` VALUES ('19', 'luyixing@777.cn', '123400.00', '0.01', '10', 'songboyi@777.cn', '1');

-- ----------------------------
-- Table structure for ask
-- ----------------------------
DROP TABLE IF EXISTS `ask`;
CREATE TABLE `ask` (
  `username` varchar(255) NOT NULL,
  `edubg` varchar(255) DEFAULT NULL,
  `job` varchar(255) DEFAULT NULL,
  `income` varchar(255) DEFAULT NULL,
  `ask` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ask
-- ----------------------------
INSERT INTO `ask` VALUES ('chenyonghua@777.cn', 'university', 'SJTU', '4000', '4000');
INSERT INTO `ask` VALUES ('luyixing@777.cn', 'work', 'Teacher', '100000', '50000');
INSERT INTO `ask` VALUES ('zhangxiao@777.cn', 'university', 'SJTU', '3000', '3000');

-- ----------------------------
-- Table structure for buy
-- ----------------------------
DROP TABLE IF EXISTS `buy`;
CREATE TABLE `buy` (
  `username` varchar(255) NOT NULL,
  `itemname` varchar(255) NOT NULL,
  `amount` double(8,2) NOT NULL,
  PRIMARY KEY (`username`,`itemname`),
  KEY `itemname` (`itemname`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of buy
-- ----------------------------
INSERT INTO `buy` VALUES ('674041200@777.cn', 'StateGrid', '3000.00');
INSERT INTO `buy` VALUES ('chenyonghua@777.cn', 'Benz', '3000.00');
INSERT INTO `buy` VALUES ('chenyonghua@777.cn', 'BMW', '2000.00');
INSERT INTO `buy` VALUES ('chenyonghua@777.cn', 'STimmortal', '5000.00');
INSERT INTO `buy` VALUES ('luyixing@777.cn', 'Benz', '2000.00');
INSERT INTO `buy` VALUES ('luyixing@777.cn', 'BMW', '2000.00');
INSERT INTO `buy` VALUES ('luyixing@777.cn', 'HuaWei', '3000.00');
INSERT INTO `buy` VALUES ('songboyi@777.cn', 'Benz', '2000.00');
INSERT INTO `buy` VALUES ('songboyi@777.cn', 'BMW', '2000.00');
INSERT INTO `buy` VALUES ('songboyi@777.cn', 'HuaWei', '3000.00');
INSERT INTO `buy` VALUES ('songboyi@777.cn', 'STimmortal', '2000.00');
INSERT INTO `buy` VALUES ('zhangxiao@777.cn', 'HuaWei', '2000.00');
INSERT INTO `buy` VALUES ('zhangxiao@777.cn', 'StateGrid', '1000.00');
INSERT INTO `buy` VALUES ('zhangxiao@777.cn', 'STimmortal', '3000.00');

-- ----------------------------
-- Table structure for card
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card` (
  `credictnumber` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`credictnumber`),
  UNIQUE KEY `credictnumber` (`credictnumber`),
  KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of card
-- ----------------------------
INSERT INTO `card` VALUES ('0217002710000684092', '674041200@777.cn');
INSERT INTO `card` VALUES ('0217002710000684123', 'luyixing@777.cn');
INSERT INTO `card` VALUES ('0217001210024455220', 'songboyi@777.cn');
INSERT INTO `card` VALUES ('0217002710000684874', 'songboyi@777.cn');
INSERT INTO `card` VALUES ('0222000200124846494', 'songboyi@777.cn');
INSERT INTO `card` VALUES ('0222600260001072444', 'songboyi@777.cn');
INSERT INTO `card` VALUES ('0217002710000684871', 'zhangxiao@777.cn');

-- ----------------------------
-- Table structure for change
-- ----------------------------
DROP TABLE IF EXISTS `change`;
CREATE TABLE `change` (
  `username` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of change
-- ----------------------------

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `itemname` varchar(255) NOT NULL,
  `price` float(10,2) NOT NULL,
  `itemrate` float NOT NULL,
  PRIMARY KEY (`itemname`),
  UNIQUE KEY `itemname` (`itemname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('Benz', '1000.00', '0.04');
INSERT INTO `goods` VALUES ('BMW', '1000.00', '0.03');
INSERT INTO `goods` VALUES ('HuaWei', '1000.00', '0.08');
INSERT INTO `goods` VALUES ('StateGrid', '1000.00', '0.03');
INSERT INTO `goods` VALUES ('STimmortal', '1000.00', '0.05');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `ID` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `credit` int(11) NOT NULL,
  `line` double(8,2) NOT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `ID` (`ID`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0', '674041200@777.cn', '123', '030206198837483920', '11111111112', '张三', '9', '1000.00');
INSERT INTO `user` VALUES ('1', 'admin', 'admin123', '020203199810010459', '11111111110', 'admin', '10', '1000.00');
INSERT INTO `user` VALUES ('2', 'songboyi@777.cn', '123', '020203199710040353', '11111111111', '宋博仪', '100', '10000.00');
INSERT INTO `user` VALUES ('3', 'zhangxiao@777.cn', '123', '03020620000229011X', '11111111113', '张啸', '10', '3000.00');
INSERT INTO `user` VALUES ('4', 'luyixing@777.cn', '123', '010102199604015647', '11111111114', '陆一行', '8', '100.00');
INSERT INTO `user` VALUES ('5', 'chenyonghua@777.cn', '123', '010102199706014236', '11111111115', '陈永桦', '8', '8000.00');
INSERT INTO `user` VALUES ('7', '727106198@777.cn', '123', '030206192837483920', '11111111116', '王辰', '10', '100.00');
