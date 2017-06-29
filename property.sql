/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : property

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-06-19 20:14:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `ID` int(10) NOT NULL,
  `USERNAME` varchar(16) CHARACTER SET gb2312 NOT NULL,
  `PASSWORD` varchar(16) CHARACTER SET gb2312 NOT NULL,
  `STATE` int(10) NOT NULL,
  `REGDATE` date NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '123456', '1', '2017-05-31');
INSERT INTO `admin` VALUES ('5', '1234', '123456', '1', '2017-05-25');
INSERT INTO `admin` VALUES ('6', 'test1', '123456', '0', '2017-06-15');
INSERT INTO `admin` VALUES ('7', 'test', '123456', '1', '2017-06-15');

-- ----------------------------
-- Table structure for complaintmgt
-- ----------------------------
DROP TABLE IF EXISTS `complaintmgt`;
CREATE TABLE `complaintmgt` (
  `id` int(10) NOT NULL,
  `user` varchar(32) DEFAULT NULL,
  `reason` varchar(128) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `state` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of complaintmgt
-- ----------------------------
INSERT INTO `complaintmgt` VALUES ('1', '59553259', '维修人态度不好', '2017-05-07', '未处理');
INSERT INTO `complaintmgt` VALUES ('2', '59553259', '啦啦啦', '2017-06-08', '已处理');
INSERT INTO `complaintmgt` VALUES ('3', 'user', '水龙头坏了', '2017-06-09', '已处理');
INSERT INTO `complaintmgt` VALUES ('4', 'user', '服务态度差', '2017-06-15', '未处理');
INSERT INTO `complaintmgt` VALUES ('5', 'user', '系统', '2017-06-22', '未处理');

-- ----------------------------
-- Table structure for costmgt
-- ----------------------------
DROP TABLE IF EXISTS `costmgt`;
CREATE TABLE `costmgt` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `User` varchar(32) DEFAULT NULL,
  `Project` varchar(128) DEFAULT NULL,
  `Receivable` varchar(32) DEFAULT NULL,
  `Actual` varchar(32) DEFAULT NULL,
  `Time` varchar(64) DEFAULT NULL,
  `State` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of costmgt
-- ----------------------------
INSERT INTO `costmgt` VALUES ('1', '59553259', '水费', '100', '100', '2017.5.27', '未缴费');
INSERT INTO `costmgt` VALUES ('2', '59553259', '电费', '200', '200', '2017.5.27', '已缴费');
INSERT INTO `costmgt` VALUES ('3', 'rebekah', '水费', '100', '0', '2017.5.27', '未缴费');
INSERT INTO `costmgt` VALUES ('5', 'user', '水费', '200', '200', '2017.5.27', '已缴费');
INSERT INTO `costmgt` VALUES ('6', 'test1', '水费', '200', '0', '2017.5.27', '未缴费');
INSERT INTO `costmgt` VALUES ('7', 'rebekah', '电费', '200', '100', '2017.5.26', '欠费');
INSERT INTO `costmgt` VALUES ('8', 'user', '电费', '200', '0', '2017.5.27', '未缴费');
INSERT INTO `costmgt` VALUES ('9', 'user', '物业费', '200', '100', '2017.5.27', '欠费');

-- ----------------------------
-- Table structure for dealmgt
-- ----------------------------
DROP TABLE IF EXISTS `dealmgt`;
CREATE TABLE `dealmgt` (
  `id` int(10) NOT NULL,
  `user` varchar(32) DEFAULT NULL,
  `reason` varchar(128) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `state` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dealmgt
-- ----------------------------
INSERT INTO `dealmgt` VALUES ('1', '59553259', '门坏了了', '2017-06-02', '正在维修');
INSERT INTO `dealmgt` VALUES ('3', '59553259', '水龙头坏了', '2017-06-23', '已维修');
INSERT INTO `dealmgt` VALUES ('4', 'user', '水龙头坏了', '2017-06-24', '已维修');
INSERT INTO `dealmgt` VALUES ('5', 'user', '床坏了', '2017-06-10', '未维修');
INSERT INTO `dealmgt` VALUES ('6', 'user', '门坏了了', '2017-06-10', '已维修');

-- ----------------------------
-- Table structure for expressmgt
-- ----------------------------
DROP TABLE IF EXISTS `expressmgt`;
CREATE TABLE `expressmgt` (
  `id` int(10) NOT NULL,
  `CustomName` varchar(32) DEFAULT NULL,
  `CustomPhone` varchar(32) DEFAULT NULL,
  `ArriveTime` date DEFAULT NULL,
  `State` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of expressmgt
-- ----------------------------
INSERT INTO `expressmgt` VALUES ('1', '瑞瑞', '18074308593', '2017-05-31', '未签收');
INSERT INTO `expressmgt` VALUES ('3', '丁丁', '13114348470', '2017-05-30', '代签');

-- ----------------------------
-- Table structure for housemgt
-- ----------------------------
DROP TABLE IF EXISTS `housemgt`;
CREATE TABLE `housemgt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `HouseNumber` int(32) DEFAULT NULL,
  `Residential` varchar(128) DEFAULT NULL,
  `Building` varchar(128) DEFAULT NULL,
  `Unit` varchar(32) DEFAULT NULL,
  `Park` varchar(32) DEFAULT NULL,
  `Location` varchar(128) DEFAULT NULL,
  `Facilities` varchar(128) DEFAULT NULL,
  `HouseType` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of housemgt
-- ----------------------------
INSERT INTO `housemgt` VALUES ('1', '418', '花园小区', '楼盘', '1单元', '无', '地下', '无', '普通住房');
INSERT INTO `housemgt` VALUES ('2', '628', '花园小区', '花园', '1单元', '无', '车库1', '无', '高级住房');
INSERT INTO `housemgt` VALUES ('3', '417', '草坪小区', '草坪', '1单元', '无', '车库2', '无', '普通住房');
INSERT INTO `housemgt` VALUES ('4', '416', '南区', '中南民大', '26', '无', '无', '无', '学生宿舍');
INSERT INTO `housemgt` VALUES ('5', '415', '南区', '中南民大', '25', '无', '无', '无', '学生宿舍');
INSERT INTO `housemgt` VALUES ('6', '414', '南区', '中南民大', '25', '无', '无', '无', '学生宿舍');
INSERT INTO `housemgt` VALUES ('7', '413', '南区', '中南民大', '25', '无', '无', '无', '学生宿舍');
INSERT INTO `housemgt` VALUES ('8', '412', '南区', '中南民大', '25', '无', '无', '无', '学生宿舍');
INSERT INTO `housemgt` VALUES ('9', '411', '南区', '中南民大', '25', '无', '无', '无', '学生宿舍');

-- ----------------------------
-- Table structure for noticemgt
-- ----------------------------
DROP TABLE IF EXISTS `noticemgt`;
CREATE TABLE `noticemgt` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `InfoTitle` varchar(32) DEFAULT NULL,
  `InfoContent` varchar(128) DEFAULT NULL,
  `InfoTime` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of noticemgt
-- ----------------------------
INSERT INTO `noticemgt` VALUES ('2', '中南民族大学2017年6月“校领导接待日”预告', '中南民族大学2017年6月“校领导接待日”预告', '2017-06-13');
INSERT INTO `noticemgt` VALUES ('3', '中南民族大学', '中南民族大学2017年6月“校领导接待日”预告中南民族大学2017年6月“校领导接待日”预告', '2017-06-13');
INSERT INTO `noticemgt` VALUES ('4', '嘿嘿', '嘻嘻', '2017-05-27');
INSERT INTO `noticemgt` VALUES ('5', '16号验收', '实训16号星期五开始验收', '2017-06-15');
INSERT INTO `noticemgt` VALUES ('6', '智能物业管理系统', '智能物业管理系统智能物业管理系统', '2017-06-15');

-- ----------------------------
-- Table structure for repairmgt
-- ----------------------------
DROP TABLE IF EXISTS `repairmgt`;
CREATE TABLE `repairmgt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `RepairTime` varchar(64) DEFAULT NULL,
  `RepairReason` varchar(128) DEFAULT NULL,
  `RepairCondition` varchar(32) DEFAULT NULL,
  `RepairPerson` varchar(32) DEFAULT NULL,
  `RepairType` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repairmgt
-- ----------------------------
INSERT INTO `repairmgt` VALUES ('1', '2017.5.26', '门坏', '已维修', '瑞瑞', '门');
INSERT INTO `repairmgt` VALUES ('2', '2017.5.26', '无', '已维修', '丁丁', '门');
INSERT INTO `repairmgt` VALUES ('3', '2017.6.25', '床坏', '未维修', '拉拉', '床');
INSERT INTO `repairmgt` VALUES ('4', '2017.6.26', '床坏', '正在维修', '嘻嘻', '床');
INSERT INTO `repairmgt` VALUES ('5', '2017.6.20', '床坏', '未维修', '呼呼', '床');
INSERT INTO `repairmgt` VALUES ('6', '2017.6.25', '鞋柜', '正在维修', '亲亲', '鞋柜');
INSERT INTO `repairmgt` VALUES ('7', '2017.6.25', '桌子坏了', '未维修', '拉拉', '桌子');
INSERT INTO `repairmgt` VALUES ('8', '2017.6.26', '桌子', '未维修', '拉拉', '桌子');

-- ----------------------------
-- Table structure for usermgt
-- ----------------------------
DROP TABLE IF EXISTS `usermgt`;
CREATE TABLE `usermgt` (
  `id` int(10) NOT NULL,
  `User` varchar(32) DEFAULT NULL,
  `Name` varchar(32) DEFAULT NULL,
  `Password` varchar(32) DEFAULT NULL,
  `Sex` varchar(16) DEFAULT NULL,
  `IdCard` varchar(32) DEFAULT NULL,
  `Email` varchar(32) DEFAULT NULL,
  `Phone` varchar(32) DEFAULT NULL,
  `Pald` varchar(16) DEFAULT NULL,
  `Hold` varchar(16) DEFAULT '',
  `REGDATE` date DEFAULT NULL,
  `STATE` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usermgt
-- ----------------------------
INSERT INTO `usermgt` VALUES ('1', 'user', '瑞瑞', '123456', '男', '433122198810219016', '56156156156@163.com', '18974317135', '25', '111', '2017-05-31', '1');
INSERT INTO `usermgt` VALUES ('2', '59553259', '杨哲瑞', '59553259', '男', '433122199610219016', '59553259@qq.com', '18074308593', '25', '418', '2017-05-31', '1');
INSERT INTO `usermgt` VALUES ('3', 'rebekah', 'rebekah', 'dxq1218', '女', '500243100512185909', '1207494979@qq.com', '17784059945', '02', '606', '2017-05-31', '1');
INSERT INTO `usermgt` VALUES ('5', 'userr', 'userr', '123456', '男', '433122198810218928', '4115165@qq.com', '18974317137', '25', '000', '2017-06-03', '1');
INSERT INTO `usermgt` VALUES ('6', 'test1', 'test1', '123456', '女', '433122198810218936', '56156169@qq.com', '177840515616', '22', '555', '2017-06-05', '1');
INSERT INTO `usermgt` VALUES ('7', 'test', '鱼鱼', '123456', '男', '155211522525756951', '156156156156@163.com', '18245685555', '5', '222', '2017-06-15', '1');
INSERT INTO `usermgt` VALUES ('8', 'user1', '鱼鱼', '123456', '男', '155211522525756951', '156156156156@163.com', '18245685555', '5', '222', '2017-06-15', '1');
