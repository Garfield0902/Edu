/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : edu

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2017-07-08 17:52:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `app_bmpjxx`
-- ----------------------------
DROP TABLE IF EXISTS `app_bmpjxx`;
CREATE TABLE `app_bmpjxx` (
  `id` varchar(36) NOT NULL,
  `hdid` varchar(36) NOT NULL,
  `xydm` varchar(32) DEFAULT NULL,
  `xymc` varchar(64) DEFAULT NULL,
  `zgh` varchar(20) NOT NULL,
  `xm` varchar(30) NOT NULL,
  `bmbz` int(11) NOT NULL,
  `bmsj` datetime DEFAULT NULL,
  `pjbz` int(11) DEFAULT NULL,
  `pjnf` int(11) DEFAULT NULL,
  `pjsj` datetime DEFAULT NULL,
  `pjfs` int(11) DEFAULT NULL,
  `lrr` varchar(16) DEFAULT NULL,
  `record_status` int(32) DEFAULT NULL,
  `record_version` int(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_bmpjxx
-- ----------------------------

-- ----------------------------
-- Table structure for `app_permission`
-- ----------------------------
DROP TABLE IF EXISTS `app_permission`;
CREATE TABLE `app_permission` (
  `id` varchar(32) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `roleId` varchar(32) DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_permission
-- ----------------------------
INSERT INTO `app_permission` VALUES ('1', 'user:create', '/user', '1', '用户创建');

-- ----------------------------
-- Table structure for `app_pxhd`
-- ----------------------------
DROP TABLE IF EXISTS `app_pxhd`;
CREATE TABLE `app_pxhd` (
  `id` varchar(36) NOT NULL,
  `hdid` varchar(36) NOT NULL,
  `xydm` varchar(32) DEFAULT NULL,
  `xymc` varchar(64) DEFAULT NULL,
  `zgh` varchar(20) NOT NULL,
  `xm` varchar(30) NOT NULL,
  `bmbz` int(11) NOT NULL,
  `bmsj` datetime DEFAULT NULL,
  `pjbz` int(11) DEFAULT NULL,
  `pjnf` int(11) DEFAULT NULL,
  `pjsj` datetime DEFAULT NULL,
  `pjfs` int(11) DEFAULT NULL,
  `lrr` varchar(16) DEFAULT NULL,
  `record_status` int(32) DEFAULT NULL,
  `record_version` int(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_pxhd
-- ----------------------------
INSERT INTO `app_pxhd` VALUES ('1', '1', '2', '2', '2', '2', '2', '2017-07-08 16:37:47', null, null, '2017-06-17 16:37:57', '3', '3', null, '3');

-- ----------------------------
-- Table structure for `app_role`
-- ----------------------------
DROP TABLE IF EXISTS `app_role`;
CREATE TABLE `app_role` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_role
-- ----------------------------
INSERT INTO `app_role` VALUES ('1', '管理员', '管理员角色');

-- ----------------------------
-- Table structure for `app_tzgg`
-- ----------------------------
DROP TABLE IF EXISTS `app_tzgg`;
CREATE TABLE `app_tzgg` (
  `id` varchar(32) NOT NULL,
  `bt` varchar(255) DEFAULT NULL,
  `fbsj` datetime NOT NULL,
  `zd` int(11) NOT NULL,
  `nr` blob,
  `cjsj` datetime DEFAULT NULL COMMENT '创建时间',
  `cjr` varchar(50) DEFAULT '' COMMENT '创建人',
  `gxsj` datetime DEFAULT NULL COMMENT '更新时间',
  `gxr` varchar(255) DEFAULT '' COMMENT '更新人',
  `zt` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_tzgg
-- ----------------------------

-- ----------------------------
-- Table structure for `app_user`
-- ----------------------------
DROP TABLE IF EXISTS `app_user`;
CREATE TABLE `app_user` (
  `id` varchar(32) NOT NULL,
  `name` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `status` int(1) DEFAULT NULL COMMENT '1表示可用，0表示不可用',
  `createdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_user
-- ----------------------------
INSERT INTO `app_user` VALUES ('1', 'admin', 'admin', '1', null);

-- ----------------------------
-- Table structure for `app_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `app_user_role`;
CREATE TABLE `app_user_role` (
  `id` varchar(32) NOT NULL,
  `userid` varchar(32) NOT NULL,
  `roleid` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_user_role
-- ----------------------------
INSERT INTO `app_user_role` VALUES ('1', '1', '1');
