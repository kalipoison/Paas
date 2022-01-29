/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : paas_db

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2022-01-30 00:10:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for notify
-- ----------------------------
DROP TABLE IF EXISTS `notify`;
CREATE TABLE `notify` (
  `notify_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '通知id',
  `create_time` datetime DEFAULT NULL COMMENT '通知时间',
  `notify_content` varchar(255) DEFAULT NULL COMMENT '通知内容',
  PRIMARY KEY (`notify_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=435 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of notify
-- ----------------------------
INSERT INTO `notify` VALUES ('420', '2021-06-24 14:51:51', 'Initialization');
INSERT INTO `notify` VALUES ('424', '2022-01-17 16:40:40', 'deploy kubernetes');
INSERT INTO `notify` VALUES ('425', '2022-01-17 16:41:11', 'addition kubernetest pod,deployment,service manage');
INSERT INTO `notify` VALUES ('426', '2022-01-17 16:41:38', 'addition security framework to authorization and authentication');
INSERT INTO `notify` VALUES ('427', '2022-01-17 16:41:52', 'add request record as PV');
INSERT INTO `notify` VALUES ('428', '2022-01-17 16:42:13', 'add ternimal to remote link');
INSERT INTO `notify` VALUES ('429', '2022-01-17 16:42:21', 'addition shopping');
INSERT INTO `notify` VALUES ('430', '2022-01-17 16:42:30', 'addition vouchers');
INSERT INTO `notify` VALUES ('431', '2022-01-17 16:42:39', 'add notification');
INSERT INTO `notify` VALUES ('432', '2022-01-18 23:23:03', null);
INSERT INTO `notify` VALUES ('433', '2022-01-18 23:24:43', null);
INSERT INTO `notify` VALUES ('434', '2022-01-18 23:27:05', null);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `user_id` bigint(20) NOT NULL COMMENT '订购用户ID',
  `prod_id` bigint(20) NOT NULL COMMENT '产品ID',
  `order_number` varchar(50) NOT NULL COMMENT '订购流水号',
  `total` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '总值',
  `actual_total` decimal(15,2) DEFAULT NULL COMMENT '实际总值',
  `pay_type` int(1) DEFAULT NULL COMMENT '支付方式 0 手动代付 1 微信支付 2 支付宝',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '订单状态 1:成功 0:失败',
  `create_time` datetime NOT NULL COMMENT '订购时间',
  `update_time` datetime DEFAULT NULL COMMENT '订单更新时间',
  `pay_time` datetime DEFAULT NULL COMMENT '付款时间',
  `finally_time` datetime DEFAULT NULL COMMENT '完成时间',
  `cancel_time` datetime DEFAULT NULL COMMENT '取消时间',
  `is_payed` int(4) DEFAULT NULL COMMENT '是否已经支付，1：已经支付过，0：没有支付过',
  `delete_status` int(4) DEFAULT '0' COMMENT '用户订单删除状态，0：没有删除， 1：回收站， 2：永久删除',
  `refund_sts` int(4) DEFAULT '0' COMMENT '1:在处理, 0:处理完成',
  `reduce_amount` decimal(15,2) DEFAULT NULL COMMENT '优惠总额',
  `close_type` int(4) DEFAULT NULL COMMENT '订单关闭原因 1-超时未支付 2-退款关闭 3-买家取消',
  `image_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  UNIQUE KEY `order_number_unique_ind` (`order_number`) USING BTREE,
  UNIQUE KEY `order_number_userid_unique_ind` (`user_id`,`order_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单表';

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('72', '1', '1', '1484472334105579520', '10.67', '10.67', null, '1', '2022-01-21 18:26:03', null, null, null, null, '1', '0', '0', null, null, null);
INSERT INTO `order` VALUES ('73', '1', '2', '1484547048807206912', '29.99', '29.99', null, '0', '2022-01-21 23:22:53', null, null, null, null, '0', '0', '0', null, null, null);
INSERT INTO `order` VALUES ('74', '1', '1', '1484913659330301952', '10.67', '10.67', '2', '0', '2022-01-22 23:39:41', null, null, null, null, '0', '0', '0', null, null, null);
INSERT INTO `order` VALUES ('75', '1', '1', '1484915336439533568', '10.67', '10.67', '1', '0', '2022-01-22 23:46:19', null, null, null, null, '0', '0', '0', null, null, null);
INSERT INTO `order` VALUES ('76', '1', '1', '1484916113581150208', '10.67', '10.67', '1', '0', '2022-01-22 23:49:25', null, null, null, null, '0', '0', '0', null, null, null);
INSERT INTO `order` VALUES ('77', '1', '1', '1484917116523122688', '10.67', '10.67', '1', '0', '2022-01-22 23:53:24', null, null, null, null, '0', '0', '0', null, null, null);
INSERT INTO `order` VALUES ('78', '1', '1', '1484917517968347136', '10.67', '10.67', '1', '0', '2022-01-22 23:54:59', null, null, null, null, '0', '0', '0', null, null, 'centos6');
INSERT INTO `order` VALUES ('79', '1', '1', '1484918190105563136', '10.67', '10.67', '2', '0', '2022-01-22 23:57:40', null, null, null, null, '0', '0', '0', null, null, 'centos6');
INSERT INTO `order` VALUES ('80', '1', '1', '1484918315913711616', '10.67', '10.67', '1', '0', '2022-01-22 23:58:10', null, null, null, null, '0', '0', '0', null, null, 'centos6');
INSERT INTO `order` VALUES ('81', '1', '1', '1484918511921926144', '10.67', '10.67', '1', '0', '2022-01-22 23:58:56', null, null, null, null, '0', '0', '0', null, null, 'centos6');
INSERT INTO `order` VALUES ('82', '1', '1', '1484919162919849984', '10.67', '10.67', '1', '0', '2022-01-23 00:01:32', null, null, null, null, '0', '0', '0', null, null, 'centos6');
INSERT INTO `order` VALUES ('83', '1', '1', '1484919516038303744', '10.67', '10.67', '0', '0', '2022-01-23 00:02:56', null, null, null, null, '0', '0', '0', null, null, 'centos6');
INSERT INTO `order` VALUES ('84', '1', '1', '1484919886391152640', '10.67', '10.67', '1', '0', '2022-01-23 00:04:24', null, null, null, null, '0', '0', '0', null, null, 'Debian');
INSERT INTO `order` VALUES ('85', '1', '1', '1484920968441892864', '10.67', '10.67', '1', '1', '2022-01-23 00:08:42', '2022-01-23 00:08:59', '2022-01-23 00:08:59', null, '2022-01-23 00:08:50', '1', '0', '0', null, null, 'centos6');
INSERT INTO `order` VALUES ('86', '1', '1', '1485137969781280768', '10.67', '10.67', '2', '0', '2022-01-23 14:30:59', null, null, null, null, '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('87', '1', '1', '1485138357876035584', '10.67', '10.67', '2', '0', '2022-01-23 14:32:32', null, null, null, null, '0', '0', '0', null, null, 'centos6');
INSERT INTO `order` VALUES ('88', '1', '1', '1485139111214977024', '10.67', '10.67', '1', '0', '2022-01-23 14:35:31', null, null, null, '2022-01-23 14:35:41', '0', '0', '0', null, null, 'centos6');
INSERT INTO `order` VALUES ('89', '1', '1', '1485991695689584640', '10.67', '10.67', '1', '0', '2022-01-25 23:03:23', null, null, null, null, '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('90', '1', '1', '1485992144345894912', '10.67', '10.67', '1', '0', '2022-01-25 23:05:10', null, null, null, null, '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('91', '1', '1', '1485996973902401536', '10.67', '10.67', '0', '0', '2022-01-25 23:24:22', null, null, null, '2022-01-25 23:24:40', '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('92', '1', '1', '1485997467257409536', '10.67', '10.67', '1', '0', '2022-01-25 23:26:19', null, null, null, null, '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('93', '1', '1', '1485997729057476608', '10.67', '10.67', '1', '0', '2022-01-25 23:27:22', null, null, null, '2022-01-25 23:27:44', '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('94', '1', '1', '1485998154145992704', '10.67', '10.67', '2', '0', '2022-01-25 23:29:03', null, null, null, null, '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('95', '1', '1', '1485998815860363264', '10.67', '10.67', '2', '0', '2022-01-25 23:31:41', null, null, null, '2022-01-25 23:31:51', '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('96', '1', '1', '1485999652053585920', '10.67', '10.67', '1', '0', '2022-01-25 23:35:00', null, null, null, '2022-01-25 23:35:10', '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('97', '1', '1', '1486006653236154368', '10.67', '10.67', '1', '0', '2022-01-26 00:02:49', null, null, null, '2022-01-26 00:02:59', '0', '0', '0', null, null, 'centos6');
INSERT INTO `order` VALUES ('98', '1', '1', '1486008539662127104', '10.67', '10.67', '1', '0', '2022-01-26 00:10:19', null, null, null, '2022-01-26 00:10:29', '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('99', '1', '1', '1486010850501332992', '10.67', '10.67', '2', '0', '2022-01-26 00:19:30', null, null, null, null, '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('100', '1', '1', '1486011353448714240', '10.67', '10.67', '0', '0', '2022-01-26 00:21:30', null, null, null, '2022-01-26 00:21:40', '0', '0', '0', null, null, 'Ubuntu21');
INSERT INTO `order` VALUES ('101', '1', '1', '1486013544095944704', '10.67', '10.67', '2', '0', '2022-01-26 00:30:12', null, null, null, '2022-01-26 00:30:22', '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('102', '1', '1', '1486013824208343040', '10.67', '10.67', '1', '0', '2022-01-26 00:31:19', null, null, null, '2022-01-26 00:31:29', '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('103', '1', '1', '1486219938426392576', '10.67', '10.67', '1', '0', '2022-01-26 14:10:21', null, null, null, '2022-01-26 14:10:31', '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('104', '1', '1', '1486256216534552576', '10.67', '10.67', '0', '0', '2022-01-26 16:34:30', null, null, null, '2022-01-26 16:34:40', '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('105', '1', '1', '1486257293497602048', '10.67', '10.67', '1', '0', '2022-01-26 16:38:47', null, null, null, '2022-01-26 16:38:57', '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('106', '1', '1', '1486378266901221376', '10.67', '10.67', '1', '0', '2022-01-27 00:39:29', null, null, null, null, '0', '0', '0', null, null, 'Debian');
INSERT INTO `order` VALUES ('107', '1', '1', '1486379082630434816', '10.67', '10.67', '1', '0', '2022-01-27 00:42:44', null, null, null, null, '0', '0', '0', null, null, 'centos6');
INSERT INTO `order` VALUES ('108', '1', '1', '1486379815018827776', '10.67', '10.67', '1', '0', '2022-01-27 00:45:38', null, null, null, null, '0', '0', '0', null, null, 'centos6');
INSERT INTO `order` VALUES ('109', '1', '1', '1486379974184275968', '10.67', '10.67', '1', '0', '2022-01-27 00:46:16', null, null, null, null, '0', '0', '0', null, null, 'centos6');
INSERT INTO `order` VALUES ('110', '1', '1', '1486380088583917568', '10.67', '10.67', '1', '0', '2022-01-27 00:46:43', null, null, null, null, '0', '0', '0', null, null, 'centos6');
INSERT INTO `order` VALUES ('111', '1', '1', '1486382387981062144', '10.67', '10.67', '1', '0', '2022-01-27 00:55:52', null, null, null, '2022-01-27 00:59:38', '0', '0', '0', null, null, 'centos6');
INSERT INTO `order` VALUES ('112', '1', '1', '1486383428164259840', '10.67', '10.67', '1', '0', '2022-01-27 01:00:00', null, null, null, null, '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('113', '1', '1', '1486383778799685632', '10.67', '10.67', '2', '0', '2022-01-27 01:01:23', null, null, null, null, '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('114', '1', '1', '1486384044223631360', '10.67', '10.67', '1', '0', '2022-01-27 01:02:26', null, null, null, null, '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('115', '1', '1', '1486386604472930304', '10.67', '10.67', '1', '0', '2022-01-27 01:12:37', null, null, null, null, '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('116', '1', '1', '1486389615291142144', '10.67', '10.67', '1', '0', '2022-01-27 01:24:35', null, null, null, null, '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('117', '1', '1', '1486561310811492352', '10.67', '10.67', '1', '0', '2022-01-27 12:46:50', null, null, null, '2022-01-27 12:46:54', '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('118', '1', '1', '1486562625381863424', '10.67', '10.67', '0', '0', '2022-01-27 12:52:04', null, null, null, '2022-01-27 12:53:02', '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('119', '1', '1', '1486562952583712768', '10.67', '10.67', '1', '0', '2022-01-27 12:53:22', null, null, null, '2022-01-27 12:53:24', '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('120', '1', '1', '1486563080602259456', '10.67', '10.67', '0', '0', '2022-01-27 12:53:52', null, null, null, '2022-01-27 12:53:58', '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('121', '1', '1', '1486686267927302144', '10.67', '10.67', '1', '0', '2022-01-27 21:03:22', null, null, null, null, '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('122', '1', '1', '1486687079516737536', '10.67', '10.67', '1', '0', '2022-01-27 21:06:36', null, null, null, '2022-01-27 21:06:38', '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('123', '1', '1', '1486687306806071296', '10.67', '10.67', '1', '0', '2022-01-27 21:07:30', null, null, null, null, '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('124', '1', '1', '1486687891756290048', '10.67', '10.67', '1', '0', '2022-01-27 21:09:49', null, null, null, null, '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('125', '1', '1', '1486688349627486208', '10.67', '10.67', '1', '0', '2022-01-27 21:11:39', null, null, null, '2022-01-27 21:11:46', '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('126', '1', '1', '1486750845524643840', '10.67', '10.67', '1', '0', '2022-01-28 01:19:59', null, null, null, '2022-01-28 01:20:06', '0', '0', '0', null, null, 'centos6');
INSERT INTO `order` VALUES ('127', '1', '1', '1486754902179975168', '10.67', '10.67', '0', '0', '2022-01-28 01:36:06', null, null, null, '2022-01-28 01:36:09', '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('128', '1', '1', '1486757151417438208', '10.67', '10.67', '1', '0', '2022-01-28 01:45:02', null, null, null, '2022-01-28 01:45:05', '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('129', '1', '2', '1486763892075008000', '29.99', '29.99', '1', '0', '2022-01-28 02:11:49', null, null, null, '2022-01-28 02:11:50', '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('130', '1', '2', '1486763971745812480', '29.99', '29.99', '0', '0', '2022-01-28 02:12:08', null, null, null, '2022-01-28 02:12:08', '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('131', '1', '1', '1486765091444625408', '10.67', '10.67', '1', '0', '2022-01-28 02:16:35', null, null, null, '2022-01-28 02:16:35', '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('132', '1', '1', '1486768694083325952', '10.67', '10.67', '1', '0', '2022-01-28 02:30:54', null, null, null, '2022-01-28 02:31:04', '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('133', '1', '1', '1486769653618446336', '10.67', '10.67', '0', '0', '2022-01-28 02:34:43', null, null, null, '2022-01-28 02:34:53', '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('134', '1', '1', '1486978922489974784', '10.67', '10.67', '1', '0', '2022-01-28 16:26:17', null, null, null, null, '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('135', '1', '1', '1487097859395424256', '10.67', '10.67', '1', '0', '2022-01-29 00:18:53', null, null, null, null, '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('136', '1', '1', '1487098181908041728', '10.67', '10.67', '0', '0', '2022-01-29 00:20:10', null, null, null, '2022-01-29 00:20:23', '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('137', '1', '1', '1487102037714931712', '10.67', '10.67', '0', '0', '2022-01-29 00:35:29', null, null, null, null, '0', '0', '0', null, null, 'Ubuntu18');
INSERT INTO `order` VALUES ('138', '1', '1', '1487102607112671232', '10.67', '10.67', '0', '0', '2022-01-29 00:37:45', null, null, null, '2022-01-29 00:37:55', '0', '0', '0', null, null, 'Ubuntu18');

-- ----------------------------
-- Table structure for prod
-- ----------------------------
DROP TABLE IF EXISTS `prod`;
CREATE TABLE `prod` (
  `prod_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '产品ID',
  `prod_name` varchar(300) NOT NULL DEFAULT '' COMMENT '商品名称',
  `price` decimal(15,2) DEFAULT NULL COMMENT '现价',
  `brief` varchar(500) DEFAULT '' COMMENT '简要描述,卖点等',
  `content` text COMMENT '详细描述',
  `status` int(1) DEFAULT '0' COMMENT '默认是1，表示正常状态, -1表示删除, 0下架',
  `create_time` datetime DEFAULT NULL COMMENT '录入时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `putaway_time` datetime DEFAULT NULL COMMENT '上架时间',
  `version` int(11) DEFAULT NULL COMMENT '版本 乐观锁',
  PRIMARY KEY (`prod_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商品';

-- ----------------------------
-- Records of prod
-- ----------------------------
INSERT INTO `prod` VALUES ('1', 'Basic', '10.67', '2G CPU,8G Memory', '2G CPU,8G Memory,ccc', '1', '2021-06-24 14:51:51', '2022-01-14 00:34:55', '2022-01-14 00:34:55', '0');
INSERT INTO `prod` VALUES ('2', 'Pro', '29.99', '8C CPU，64G Memory', '8C CPU，64G Memory ccc', '1', '2021-06-24 14:51:51', '2022-01-17 22:04:08', '2022-01-17 22:04:08', '0');
INSERT INTO `prod` VALUES ('3', 'Team', '69.99', '4核 CPU、8G 内存、1TB磁盘', '4核 CPU、8G 内存、1TB磁盘', '1', '2022-01-17 22:05:45', '2022-01-17 22:05:45', '2022-01-17 22:05:45', '0');

-- ----------------------------
-- Table structure for prod_prop
-- ----------------------------
DROP TABLE IF EXISTS `prod_prop`;
CREATE TABLE `prod_prop` (
  `prop_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '属性id',
  `prop_name` varchar(20) DEFAULT NULL COMMENT '属性名称',
  PRIMARY KEY (`prop_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of prod_prop
-- ----------------------------
INSERT INTO `prod_prop` VALUES ('1', 'Memory');
INSERT INTO `prod_prop` VALUES ('2', 'CPU');
INSERT INTO `prod_prop` VALUES ('3', 'Disk');

-- ----------------------------
-- Table structure for prod_prop_value
-- ----------------------------
DROP TABLE IF EXISTS `prod_prop_value`;
CREATE TABLE `prod_prop_value` (
  `value_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '属性值ID',
  `prop_value` varchar(20) DEFAULT NULL COMMENT '属性值名称',
  `prop_id` bigint(20) DEFAULT NULL COMMENT '属性ID',
  PRIMARY KEY (`value_id`) USING BTREE,
  KEY `prop_id` (`prop_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of prod_prop_value
-- ----------------------------
INSERT INTO `prod_prop_value` VALUES ('1', '2 G', '1');
INSERT INTO `prod_prop_value` VALUES ('2', '4 G', '1');
INSERT INTO `prod_prop_value` VALUES ('3', '8 G', '1');
INSERT INTO `prod_prop_value` VALUES ('4', '1 Core', '2');
INSERT INTO `prod_prop_value` VALUES ('5', '2 Core', '2');
INSERT INTO `prod_prop_value` VALUES ('6', '4 Core', '2');
INSERT INTO `prod_prop_value` VALUES ('7', '50GB', '3');
INSERT INTO `prod_prop_value` VALUES ('8', '200GB', '3');
INSERT INTO `prod_prop_value` VALUES ('9', '1TB', '3');

-- ----------------------------
-- Table structure for sku
-- ----------------------------
DROP TABLE IF EXISTS `sku`;
CREATE TABLE `sku` (
  `sku_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '单品ID',
  `prod_id` bigint(20) unsigned NOT NULL COMMENT '商品ID',
  `value_id` bigint(20) NOT NULL COMMENT '属性值ID',
  PRIMARY KEY (`sku_id`) USING BTREE,
  KEY `prod_id` (`prod_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=438 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='单品SKU表';

-- ----------------------------
-- Records of sku
-- ----------------------------
INSERT INTO `sku` VALUES ('1', '1', '1');
INSERT INTO `sku` VALUES ('2', '1', '4');
INSERT INTO `sku` VALUES ('3', '1', '7');
INSERT INTO `sku` VALUES ('4', '2', '2');
INSERT INTO `sku` VALUES ('432', '2', '5');
INSERT INTO `sku` VALUES ('434', '2', '8');
INSERT INTO `sku` VALUES ('435', '3', '3');
INSERT INTO `sku` VALUES ('436', '3', '6');
INSERT INTO `sku` VALUES ('437', '3', '9');

-- ----------------------------
-- Table structure for sys_dau
-- ----------------------------
DROP TABLE IF EXISTS `sys_dau`;
CREATE TABLE `sys_dau` (
  `dau_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `year` varchar(50) DEFAULT NULL COMMENT '年',
  `month` varchar(200) DEFAULT NULL COMMENT '月',
  `day` varchar(500) DEFAULT NULL COMMENT '日',
  `count` int(11) DEFAULT NULL COMMENT '访问次数',
  PRIMARY KEY (`dau_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=222 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='访问量统计';

-- ----------------------------
-- Records of sys_dau
-- ----------------------------
INSERT INTO `sys_dau` VALUES ('202', '2021', '1', '1', '100');
INSERT INTO `sys_dau` VALUES ('203', '2021', '1', '5', '124');
INSERT INTO `sys_dau` VALUES ('204', '2021', '1', '21', '300');
INSERT INTO `sys_dau` VALUES ('205', '2021', '2', '5', '78');
INSERT INTO `sys_dau` VALUES ('206', '2021', '2', '10', '167');
INSERT INTO `sys_dau` VALUES ('207', '2021', '2', '20', '400');
INSERT INTO `sys_dau` VALUES ('208', '2021', '3', '4', '222');
INSERT INTO `sys_dau` VALUES ('209', '2021', '3', '5', '22');
INSERT INTO `sys_dau` VALUES ('210', '2021', '4', '18', '500');
INSERT INTO `sys_dau` VALUES ('211', '2021', '5', '19', '700');
INSERT INTO `sys_dau` VALUES ('212', '2021', '6', '20', '420');
INSERT INTO `sys_dau` VALUES ('216', '2021', '7', '22', '222');
INSERT INTO `sys_dau` VALUES ('217', '2021', '8', '11', '344');
INSERT INTO `sys_dau` VALUES ('218', '2021', '9', '7', '654');
INSERT INTO `sys_dau` VALUES ('219', '2021', '10', '1', '555');
INSERT INTO `sys_dau` VALUES ('220', '2021', '11', '2', '666');
INSERT INTO `sys_dau` VALUES ('221', '2021', '12', '3', '333');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1355 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('829', 'admin', '保存定时任务', 'com.yami.shop.quartz.controller.ScheduleJobController.save()', '{\"jobId\":14,\"beanName\":\"orderTask\",\"methodName\":\"cancelOrder\",\"cronExpression\":\"0 * * * * ?\",\"status\":0,\"remark\":\"取消超时未支付订单\",\"createTime\":\"2019-06-24T06:51:51.074+0000\"}', '167', '127.0.0.1', '2021-06-24 14:51:51');
INSERT INTO `sys_log` VALUES ('830', 'admin', '保存定时任务', 'com.yami.shop.quartz.controller.ScheduleJobController.save()', '{\"jobId\":16,\"beanName\":\"orderTask\",\"methodName\":\"confirmOrder\",\"cronExpression\":\"0 0/5 * * * ?\",\"status\":0,\"remark\":\"系统自动确认收货订单\",\"createTime\":\"2019-06-24T06:52:47.482+0000\"}', '62', '127.0.0.1', '2021-06-24 14:52:48');
INSERT INTO `sys_log` VALUES ('831', 'admin', '删除菜单', 'com.yami.shop.sys.controller.SysMenuController.delete()', '168', '8', '127.0.0.1', '2021-06-26 14:04:22');
INSERT INTO `sys_log` VALUES ('832', 'admin', '删除菜单', 'com.yami.shop.sys.controller.SysMenuController.delete()', '229', '1', '127.0.0.1', '2021-06-26 14:31:31');
INSERT INTO `sys_log` VALUES ('833', 'admin', '删除菜单', 'com.yami.shop.sys.controller.SysMenuController.delete()', '231', '6', '127.0.0.1', '2021-11-07 06:41:55');
INSERT INTO `sys_log` VALUES ('834', 'admin', '删除菜单', 'com.yami.shop.sys.controller.SysMenuController.delete()', '232', '8', '127.0.0.1', '2021-11-07 06:41:55');
INSERT INTO `sys_log` VALUES ('835', 'admin', '删除菜单', 'com.yami.shop.sys.controller.SysMenuController.delete()', '233', '25', '127.0.0.1', '2021-11-07 06:41:55');
INSERT INTO `sys_log` VALUES ('836', 'admin', '删除菜单', 'com.yami.shop.sys.controller.SysMenuController.delete()', '229', '6', '127.0.0.1', '2021-11-07 06:41:55');
INSERT INTO `sys_log` VALUES ('837', 'admin', '删除分类', 'com.yami.shop.admin.controller.CategoryController.delete()', '92', '536', '127.0.0.1', '2021-11-07 06:41:55');
INSERT INTO `sys_log` VALUES ('838', 'admin', '保存角色', 'com.yami.shop.sys.controller.SysRoleController.save()', '{\"roleId\":1,\"roleName\":\"管理员\",\"remark\":\"测试\",\"menuIdList\":[34,70,71,72,73,74,75,136,137,138,139,140,239,240,241,242,243,306,307,308,309,310,51,57,58,59,60,63,300,301,302,303,305,131,132,133,134,135,163,164,165,166,167,174,175,176,177,178,201,202,203,204,205,206,125,126,127,128,129,130,230,91,92,93,99,100,101,107,108,146,184,185,1,312,313,314,315,316,2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,6,7,8,9,10,11,12,13,14,27,29,-666666],\"createTime\":\"2019-07-03T00:39:49.339+0000\"}', '106', '127.0.0.1', '2021-11-07 06:41:55');
INSERT INTO `sys_log` VALUES ('839', 'admin', '保存配置', 'com.yami.shop.sys.controller.SysConfigController.save()', '{\"id\":1,\"paramKey\":\"测试\",\"paramValue\":\"1\",\"remark\":\"1\"}', '17', '127.0.0.1', '2021-11-07 06:41:55');
INSERT INTO `sys_log` VALUES ('840', 'admin', '删除配置', 'com.yami.shop.sys.controller.SysConfigController.delete()', '[1]', '18', '127.0.0.1', '2021-11-07 06:41:55');
INSERT INTO `sys_log` VALUES ('841', 'admin', '新增公告管理', 'com.yami.shop.admin.controller.NoticeController.save()', '{\"id\":4,\"shopId\":1,\"title\":\"测试\",\"content\":\"<p>1111</p>\",\"status\":1,\"isTop\":1,\"publishTime\":\"2019-07-03T02:05:55.730+0000\",\"updateTime\":\"2019-07-03T02:05:55.730+0000\"}', '26', '127.0.0.1', '2021-11-07 06:41:55');
INSERT INTO `sys_log` VALUES ('842', 'admin', '删除公告管理', 'com.yami.shop.admin.controller.NoticeController.removeById()', '4', '37', '127.0.0.1', '2021-11-07 06:41:55');
INSERT INTO `sys_log` VALUES ('843', 'admin', 'findByPage', null, '[{\"total\":0,\"current\":1,\"size\":10,\"records\":[],\"orders\":[],\"isSearchCount\":true},{}]', '281', null, null);
INSERT INTO `sys_log` VALUES ('844', 'admin', '分页获取管理员列表', 'public org.springframework.http.ResponseEntity com.sxt.shop.controller.AdminController.findByPage(com.baomidou.mybatisplus.extension.plugins.pagination.Page,com.sxt.shop.entity.SysUser)', '[{\"total\":0,\"current\":1,\"size\":10,\"records\":[],\"orders\":[],\"isSearchCount\":true},{}]', '57', null, null);
INSERT INTO `sys_log` VALUES ('845', 'admin', null, 'public org.springframework.http.ResponseEntity com.sxt.shop.controller.AdminController.findPage(com.baomidou.mybatisplus.extension.plugins.pagination.Page,com.sxt.shop.entity.SysRole)', '[{\"total\":0,\"current\":1,\"size\":10,\"records\":[],\"orders\":[],\"isSearchCount\":true},{}]', '54', '127.0.0.1', null);
INSERT INTO `sys_log` VALUES ('846', 'admin', '分页获取管理员列表', 'public org.springframework.http.ResponseEntity com.sxt.shop.controller.AdminController.findByPage(com.baomidou.mybatisplus.extension.plugins.pagination.Page,com.sxt.shop.entity.SysUser)', '[{\"total\":0,\"current\":1,\"size\":10,\"records\":[],\"orders\":[],\"isSearchCount\":true},{}]', '50', '127.0.0.1', null);
INSERT INTO `sys_log` VALUES ('847', 'admin', '分页获取管理员列表', 'public org.springframework.http.ResponseEntity com.sxt.shop.controller.AdminController.findByPage(com.baomidou.mybatisplus.extension.plugins.pagination.Page,com.sxt.shop.entity.SysUser)', '[{\"total\":0,\"current\":1,\"size\":10,\"records\":[],\"orders\":[],\"isSearchCount\":true},{}]', '46', '127.0.0.1', null);
INSERT INTO `sys_log` VALUES ('848', 'admin', '分页获取管理员列表', 'public org.springframework.http.ResponseEntity com.sxt.shop.controller.AdminController.findByPage(com.baomidou.mybatisplus.extension.plugins.pagination.Page,com.sxt.shop.entity.SysUser)', '[{\"total\":0,\"current\":1,\"size\":10,\"records\":[],\"orders\":[],\"isSearchCount\":true},{}]', '44', '192.168.78.1', null);
INSERT INTO `sys_log` VALUES ('849', 'admin', null, 'public org.springframework.http.ResponseEntity com.sxt.shop.controller.AdminController.findPage(com.baomidou.mybatisplus.extension.plugins.pagination.Page,com.sxt.shop.entity.SysRole)', '[{\"total\":0,\"current\":1,\"size\":10,\"records\":[],\"orders\":[],\"isSearchCount\":true},{}]', '45', '127.0.0.1', null);
INSERT INTO `sys_log` VALUES ('850', 'admin', '分页获取管理员列表', 'public org.springframework.http.ResponseEntity com.sxt.shop.controller.AdminController.findByPage(com.baomidou.mybatisplus.extension.plugins.pagination.Page,com.sxt.shop.entity.SysUser)', '[{\"total\":0,\"current\":1,\"size\":10,\"records\":[],\"orders\":[],\"isSearchCount\":true},{}]', '56', '127.0.0.1', null);
INSERT INTO `sys_log` VALUES ('851', 'admin', null, 'public org.springframework.http.ResponseEntity com.sxt.shop.controller.AdminController.listSysMenu()', null, '150', '127.0.0.1', null);
INSERT INTO `sys_log` VALUES ('852', 'admin', '分页获取管理员列表', 'public org.springframework.http.ResponseEntity com.sxt.shop.controller.AdminController.findByPage(com.baomidou.mybatisplus.extension.plugins.pagination.Page,com.sxt.shop.entity.SysUser)', '[{\"total\":0,\"current\":1,\"size\":10,\"records\":[],\"orders\":[],\"isSearchCount\":true},{}]', '33', '127.0.0.1', null);
INSERT INTO `sys_log` VALUES ('853', 'admin', '分页获取管理员列表', 'public org.springframework.http.ResponseEntity com.sxt.shop.controller.AdminController.findByPage(com.baomidou.mybatisplus.extension.plugins.pagination.Page,com.sxt.shop.entity.SysUser)', '[{\"total\":0,\"current\":1,\"size\":10,\"records\":[],\"orders\":[],\"isSearchCount\":true},{}]', '48', '127.0.0.1', null);
INSERT INTO `sys_log` VALUES ('854', 'admin', '分页获取管理员列表', 'public org.springframework.http.ResponseEntity com.sxt.shop.controller.AdminController.findByPage(com.baomidou.mybatisplus.extension.plugins.pagination.Page,com.sxt.shop.entity.SysUser)', '[{\"total\":0,\"current\":1,\"size\":100,\"records\":[],\"orders\":[],\"isSearchCount\":true},{}]', '50', '127.0.0.1', null);
INSERT INTO `sys_log` VALUES ('855', 'admin', null, 'public org.springframework.http.ResponseEntity com.sxt.shop.controller.AdminController.findPage(com.baomidou.mybatisplus.extension.plugins.pagination.Page,com.sxt.shop.entity.SysRole)', '[{\"total\":0,\"current\":1,\"size\":10,\"records\":[],\"orders\":[],\"isSearchCount\":true},{}]', '200', '127.0.0.1', null);
INSERT INTO `sys_log` VALUES ('856', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '62', '192.168.10.1', '2021-11-07 06:41:55');
INSERT INTO `sys_log` VALUES ('857', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '16', '192.168.10.1', '2021-11-07 06:41:55');
INSERT INTO `sys_log` VALUES ('858', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '41', '192.168.10.1', '2021-11-07 07:45:10');
INSERT INTO `sys_log` VALUES ('859', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '9', '192.168.10.1', '2021-11-07 07:45:10');
INSERT INTO `sys_log` VALUES ('860', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '125', '192.168.10.1', '2021-11-07 07:46:17');
INSERT INTO `sys_log` VALUES ('861', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '17', '192.168.10.1', '2021-11-07 07:46:17');
INSERT INTO `sys_log` VALUES ('862', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '8', '192.168.10.1', '2021-11-07 07:46:50');
INSERT INTO `sys_log` VALUES ('863', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '7', '192.168.10.1', '2021-11-07 07:46:51');
INSERT INTO `sys_log` VALUES ('864', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '6', '192.168.10.1', '2021-11-07 07:47:08');
INSERT INTO `sys_log` VALUES ('865', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '8', '192.168.10.1', '2021-11-07 07:47:08');
INSERT INTO `sys_log` VALUES ('866', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-07 07:47:27');
INSERT INTO `sys_log` VALUES ('867', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '5', '192.168.10.1', '2021-11-07 07:47:27');
INSERT INTO `sys_log` VALUES ('868', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-07 07:47:41');
INSERT INTO `sys_log` VALUES ('869', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '4', '192.168.10.1', '2021-11-07 07:47:41');
INSERT INTO `sys_log` VALUES ('870', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-07 07:55:05');
INSERT INTO `sys_log` VALUES ('871', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '5', '192.168.10.1', '2021-11-07 07:55:06');
INSERT INTO `sys_log` VALUES ('872', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '91', '192.168.10.1', '2021-11-07 08:09:32');
INSERT INTO `sys_log` VALUES ('873', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-07 08:09:58');
INSERT INTO `sys_log` VALUES ('874', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '15', '192.168.10.1', '2021-11-07 08:09:58');
INSERT INTO `sys_log` VALUES ('875', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-07 08:10:35');
INSERT INTO `sys_log` VALUES ('876', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '4', '192.168.10.1', '2021-11-07 08:10:36');
INSERT INTO `sys_log` VALUES ('877', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '4', '192.168.10.1', '2021-11-07 08:10:50');
INSERT INTO `sys_log` VALUES ('878', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '7', '192.168.10.1', '2021-11-07 08:10:50');
INSERT INTO `sys_log` VALUES ('879', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '70', '192.168.10.1', '2021-11-07 08:32:21');
INSERT INTO `sys_log` VALUES ('880', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '17', '192.168.10.1', '2021-11-07 08:32:21');
INSERT INTO `sys_log` VALUES ('881', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '8', '192.168.10.1', '2021-11-07 08:32:31');
INSERT INTO `sys_log` VALUES ('882', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '8', '192.168.10.1', '2021-11-07 08:32:31');
INSERT INTO `sys_log` VALUES ('883', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '49', '192.168.10.1', '2021-11-07 09:29:25');
INSERT INTO `sys_log` VALUES ('884', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '8', '192.168.10.1', '2021-11-07 09:29:26');
INSERT INTO `sys_log` VALUES ('885', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-07 09:30:08');
INSERT INTO `sys_log` VALUES ('886', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '11', '192.168.10.1', '2021-11-07 09:30:08');
INSERT INTO `sys_log` VALUES ('887', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '6', '192.168.10.1', '2021-11-07 09:30:56');
INSERT INTO `sys_log` VALUES ('888', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '8', '192.168.10.1', '2021-11-07 09:30:56');
INSERT INTO `sys_log` VALUES ('889', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '73', '192.168.10.1', '2021-11-07 09:45:40');
INSERT INTO `sys_log` VALUES ('890', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '10', '192.168.10.1', '2021-11-07 09:45:41');
INSERT INTO `sys_log` VALUES ('891', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '22', '192.168.10.1', '2021-11-07 10:07:53');
INSERT INTO `sys_log` VALUES ('892', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '4', '192.168.10.1', '2021-11-07 10:07:53');
INSERT INTO `sys_log` VALUES ('893', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '9', '192.168.10.1', '2021-11-07 10:08:02');
INSERT INTO `sys_log` VALUES ('894', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '7', '192.168.10.1', '2021-11-07 10:08:03');
INSERT INTO `sys_log` VALUES ('895', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '114', '192.168.10.1', '2021-11-07 11:39:28');
INSERT INTO `sys_log` VALUES ('896', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '13', '192.168.10.1', '2021-11-07 11:39:28');
INSERT INTO `sys_log` VALUES ('897', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '75', '192.168.10.1', '2021-11-07 11:47:15');
INSERT INTO `sys_log` VALUES ('898', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '20', '192.168.10.1', '2021-11-07 11:47:16');
INSERT INTO `sys_log` VALUES ('899', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[{\"asc\":false,\"column\":\"create_time\"}],\"pages\":0,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"},{\"createTime\":1564794355000,\"createUserId\":1,\"email\":\"123@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"status\":1,\"userId\":4,\"username\":\"test\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '16', '192.168.10.1', '2021-11-07 11:47:19');
INSERT INTO `sys_log` VALUES ('900', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '24', '192.168.10.1', '2021-11-07 12:12:11');
INSERT INTO `sys_log` VALUES ('901', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '7', '192.168.10.1', '2021-11-07 12:12:11');
INSERT INTO `sys_log` VALUES ('902', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[{\"asc\":false,\"column\":\"create_time\"}],\"pages\":0,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"},{\"createTime\":1564794355000,\"createUserId\":1,\"email\":\"123@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"status\":1,\"userId\":4,\"username\":\"test\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '11', '192.168.10.1', '2021-11-07 12:12:13');
INSERT INTO `sys_log` VALUES ('903', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '76', '192.168.10.1', '2021-11-07 12:33:02');
INSERT INTO `sys_log` VALUES ('904', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '8', '192.168.10.1', '2021-11-07 12:33:02');
INSERT INTO `sys_log` VALUES ('905', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '143', '192.168.10.1', '2021-11-07 12:36:37');
INSERT INTO `sys_log` VALUES ('906', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '18', '192.168.10.1', '2021-11-07 12:36:38');
INSERT INTO `sys_log` VALUES ('907', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '97', '192.168.10.1', '2021-11-07 13:04:05');
INSERT INTO `sys_log` VALUES ('908', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '16', '192.168.10.1', '2021-11-07 13:04:05');
INSERT INTO `sys_log` VALUES ('909', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[{\"asc\":false,\"column\":\"create_time\"}],\"pages\":0,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"},{\"createTime\":1564794355000,\"createUserId\":1,\"email\":\"123@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"status\":1,\"userId\":4,\"username\":\"test\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '12', '192.168.10.1', '2021-11-07 13:04:09');
INSERT INTO `sys_log` VALUES ('910', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '79', '192.168.10.1', '2021-11-07 13:07:14');
INSERT INTO `sys_log` VALUES ('911', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '20', '192.168.10.1', '2021-11-07 13:07:14');
INSERT INTO `sys_log` VALUES ('912', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[{\"asc\":false,\"column\":\"create_time\"}],\"pages\":0,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"},{\"createTime\":1564794355000,\"createUserId\":1,\"email\":\"123@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"status\":1,\"userId\":4,\"username\":\"test\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '21', '192.168.10.1', '2021-11-07 13:07:17');
INSERT INTO `sys_log` VALUES ('913', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '4', '192.168.10.1', '2021-11-07 13:10:14');
INSERT INTO `sys_log` VALUES ('914', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '7', '192.168.10.1', '2021-11-07 13:10:14');
INSERT INTO `sys_log` VALUES ('915', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[{\"asc\":false,\"column\":\"create_time\"}],\"pages\":0,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"},{\"createTime\":1564794355000,\"createUserId\":1,\"email\":\"123@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"status\":1,\"userId\":4,\"username\":\"test\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '192', '192.168.10.1', '2021-11-07 13:16:12');
INSERT INTO `sys_log` VALUES ('916', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '93', '192.168.10.1', '2021-11-07 13:16:15');
INSERT INTO `sys_log` VALUES ('917', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '12', '192.168.10.1', '2021-11-07 13:16:15');
INSERT INTO `sys_log` VALUES ('918', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[{\"asc\":false,\"column\":\"create_time\"}],\"pages\":0,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"},{\"createTime\":1564794355000,\"createUserId\":1,\"email\":\"123@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"status\":1,\"userId\":4,\"username\":\"test\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '7', '192.168.10.1', '2021-11-07 13:16:16');
INSERT INTO `sys_log` VALUES ('919', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '80', '192.168.10.1', '2021-11-07 13:20:09');
INSERT INTO `sys_log` VALUES ('920', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '18', '192.168.10.1', '2021-11-07 13:20:09');
INSERT INTO `sys_log` VALUES ('921', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[{\"asc\":false,\"column\":\"create_time\"}],\"pages\":0,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"},{\"createTime\":1564794355000,\"createUserId\":1,\"email\":\"123@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"status\":1,\"userId\":4,\"username\":\"test\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '24', '192.168.10.1', '2021-11-07 13:20:12');
INSERT INTO `sys_log` VALUES ('922', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '84', '192.168.10.1', '2021-11-07 13:22:17');
INSERT INTO `sys_log` VALUES ('923', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '18', '192.168.10.1', '2021-11-07 13:22:17');
INSERT INTO `sys_log` VALUES ('924', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '27', '192.168.10.1', '2021-11-07 15:16:43');
INSERT INTO `sys_log` VALUES ('925', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '4', '192.168.10.1', '2021-11-07 15:16:44');
INSERT INTO `sys_log` VALUES ('926', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '77', '192.168.10.1', '2021-11-07 15:42:01');
INSERT INTO `sys_log` VALUES ('927', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '18', '192.168.10.1', '2021-11-07 15:42:01');
INSERT INTO `sys_log` VALUES ('928', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '31', '192.168.10.1', '2021-11-07 16:22:47');
INSERT INTO `sys_log` VALUES ('929', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '18', '192.168.10.1', '2021-11-07 16:22:48');
INSERT INTO `sys_log` VALUES ('930', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[{\"asc\":false,\"column\":\"create_time\"}],\"pages\":0,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"},{\"createTime\":1564794355000,\"createUserId\":1,\"email\":\"123@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"status\":1,\"userId\":4,\"username\":\"test\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '28', '192.168.10.1', '2021-11-07 16:23:09');
INSERT INTO `sys_log` VALUES ('931', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[{\"asc\":false,\"column\":\"create_time\"}],\"pages\":0,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"},{\"createTime\":1564794355000,\"createUserId\":1,\"email\":\"123@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"status\":1,\"userId\":4,\"username\":\"test\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '65', '192.168.10.1', '2021-11-07 16:27:12');
INSERT INTO `sys_log` VALUES ('932', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '26691', '192.168.10.1', '2021-11-07 16:50:32');
INSERT INTO `sys_log` VALUES ('933', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '16', '192.168.10.1', '2021-11-07 16:50:32');
INSERT INTO `sys_log` VALUES ('934', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '229703', '192.168.10.1', '2021-11-07 17:35:34');
INSERT INTO `sys_log` VALUES ('935', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '9275', '192.168.10.1', '2021-11-07 17:36:49');
INSERT INTO `sys_log` VALUES ('936', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '4', '192.168.10.1', '2021-11-07 17:36:49');
INSERT INTO `sys_log` VALUES ('937', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '15927', '192.168.10.1', '2021-11-07 17:38:00');
INSERT INTO `sys_log` VALUES ('938', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '4', '192.168.10.1', '2021-11-07 17:38:00');
INSERT INTO `sys_log` VALUES ('939', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[{\"asc\":false,\"column\":\"create_time\"}],\"pages\":0,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"},{\"createTime\":1564794355000,\"createUserId\":1,\"email\":\"123@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"status\":1,\"userId\":4,\"username\":\"test\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '24', '192.168.10.1', '2021-11-07 17:38:10');
INSERT INTO `sys_log` VALUES ('940', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '8460', '192.168.10.1', '2021-11-07 17:40:30');
INSERT INTO `sys_log` VALUES ('941', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '19', '192.168.10.1', '2021-11-07 17:40:32');
INSERT INTO `sys_log` VALUES ('942', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[{\"asc\":false,\"column\":\"create_time\"}],\"pages\":0,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"},{\"createTime\":1564794355000,\"createUserId\":1,\"email\":\"123@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"status\":1,\"userId\":4,\"username\":\"test\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '6', '192.168.10.1', '2021-11-07 17:40:56');
INSERT INTO `sys_log` VALUES ('943', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '47007', '192.168.10.1', '2021-11-07 17:48:16');
INSERT INTO `sys_log` VALUES ('944', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '31586', '192.168.10.1', '2021-11-07 17:51:18');
INSERT INTO `sys_log` VALUES ('945', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '1840', '192.168.10.1', '2021-11-07 17:52:10');
INSERT INTO `sys_log` VALUES ('946', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-07 17:52:10');
INSERT INTO `sys_log` VALUES ('947', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[{\"asc\":false,\"column\":\"create_time\"}],\"pages\":0,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"},{\"createTime\":1564794355000,\"createUserId\":1,\"email\":\"123@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"status\":1,\"userId\":4,\"username\":\"test\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '5', '192.168.10.1', '2021-11-07 17:52:15');
INSERT INTO `sys_log` VALUES ('948', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[{\"asc\":false,\"column\":\"create_time\"}],\"pages\":0,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"},{\"createTime\":1564794355000,\"createUserId\":1,\"email\":\"123@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"status\":1,\"userId\":4,\"username\":\"test\"},{\"createTime\":1636278803000,\"createUserId\":1,\"email\":\"aaa@qq.com\",\"mobile\":\"13111111111\",\"password\":\"$2a$10$/WMR6kEnw2QF.PH5XXFSYeN9oImFnha3W0x9EquyVdu/mRWhdy97G\",\"shopId\":1,\"status\":1,\"userId\":5,\"username\":\"test3\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '11', '192.168.10.1', '2021-11-07 17:53:25');
INSERT INTO `sys_log` VALUES ('949', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '8358', '192.168.10.1', '2021-11-07 18:03:34');
INSERT INTO `sys_log` VALUES ('950', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '5', '192.168.10.1', '2021-11-07 18:03:34');
INSERT INTO `sys_log` VALUES ('951', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '70', '192.168.10.1', '2021-11-07 18:11:48');
INSERT INTO `sys_log` VALUES ('952', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '18', '192.168.10.1', '2021-11-07 18:11:48');
INSERT INTO `sys_log` VALUES ('953', 'admin', '分页查询角色列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[{\"asc\":false,\"column\":\"create_time\"}],\"pages\":0,\"records\":[{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"admin\",\"roleId\":1,\"roleName\":\"admin\"},{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"每天上货，清理库存\",\"roleId\":2,\"roleName\":\"上货员\"},{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"test\",\"roleId\":11,\"roleName\":\"test\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '28', '192.168.10.1', '2021-11-07 18:11:52');
INSERT INTO `sys_log` VALUES ('954', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[{\"asc\":false,\"column\":\"create_time\"}],\"pages\":0,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"},{\"createTime\":1564794355000,\"createUserId\":1,\"email\":\"123@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"status\":1,\"userId\":4,\"username\":\"test\"},{\"createTime\":1636278803000,\"createUserId\":1,\"email\":\"aaa@qq.com\",\"mobile\":\"13111111111\",\"password\":\"$2a$10$/WMR6kEnw2QF.PH5XXFSYeN9oImFnha3W0x9EquyVdu/mRWhdy97G\",\"shopId\":1,\"status\":1,\"userId\":5,\"username\":\"test3\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '12', '192.168.10.1', '2021-11-07 18:11:59');
INSERT INTO `sys_log` VALUES ('955', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-07 18:12:18');
INSERT INTO `sys_log` VALUES ('956', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '10', '192.168.10.1', '2021-11-07 18:12:18');
INSERT INTO `sys_log` VALUES ('957', 'admin', '分页查询角色列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[{\"asc\":false,\"column\":\"create_time\"}],\"pages\":0,\"records\":[{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"admin\",\"roleId\":1,\"roleName\":\"admin\"},{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"每天上货，清理库存\",\"roleId\":2,\"roleName\":\"上货员\"},{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"test\",\"roleId\":11,\"roleName\":\"test\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '3', '192.168.10.1', '2021-11-07 18:12:19');
INSERT INTO `sys_log` VALUES ('958', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[{\"asc\":false,\"column\":\"create_time\"}],\"pages\":0,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"},{\"createTime\":1564794355000,\"createUserId\":1,\"email\":\"123@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"status\":1,\"userId\":4,\"username\":\"test\"},{\"createTime\":1636278803000,\"createUserId\":1,\"email\":\"aaa@qq.com\",\"mobile\":\"13111111111\",\"password\":\"$2a$10$/WMR6kEnw2QF.PH5XXFSYeN9oImFnha3W0x9EquyVdu/mRWhdy97G\",\"shopId\":1,\"status\":1,\"userId\":5,\"username\":\"test3\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '14', '192.168.10.1', '2021-11-07 18:13:26');
INSERT INTO `sys_log` VALUES ('959', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '82', '192.168.10.1', '2021-11-07 22:05:39');
INSERT INTO `sys_log` VALUES ('960', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '82', '192.168.10.1', '2021-11-07 22:05:39');
INSERT INTO `sys_log` VALUES ('961', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '17', '192.168.10.1', '2021-11-07 22:05:39');
INSERT INTO `sys_log` VALUES ('962', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '4', '192.168.10.1', '2021-11-07 22:05:57');
INSERT INTO `sys_log` VALUES ('963', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '4', '192.168.10.1', '2021-11-07 22:05:57');
INSERT INTO `sys_log` VALUES ('964', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '6437', '192.168.10.1', '2021-11-07 22:38:38');
INSERT INTO `sys_log` VALUES ('965', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '19', '192.168.10.1', '2021-11-07 22:38:38');
INSERT INTO `sys_log` VALUES ('966', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2733', '192.168.10.1', '2021-11-07 22:38:59');
INSERT INTO `sys_log` VALUES ('967', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '7', '192.168.10.1', '2021-11-07 22:38:59');
INSERT INTO `sys_log` VALUES ('968', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '7959', '192.168.10.1', '2021-11-08 08:40:01');
INSERT INTO `sys_log` VALUES ('969', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '8046', '192.168.10.1', '2021-11-08 08:40:01');
INSERT INTO `sys_log` VALUES ('970', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '8007', '192.168.10.1', '2021-11-08 08:40:01');
INSERT INTO `sys_log` VALUES ('971', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-08 08:40:26');
INSERT INTO `sys_log` VALUES ('972', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '6', '192.168.10.1', '2021-11-08 08:40:26');
INSERT INTO `sys_log` VALUES ('973', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-08 08:44:25');
INSERT INTO `sys_log` VALUES ('974', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '6', '192.168.10.1', '2021-11-08 08:44:25');
INSERT INTO `sys_log` VALUES ('975', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '75', '192.168.10.1', '2021-11-09 10:12:33');
INSERT INTO `sys_log` VALUES ('976', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '75', '192.168.10.1', '2021-11-09 10:12:33');
INSERT INTO `sys_log` VALUES ('977', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '19', '192.168.10.1', '2021-11-09 10:12:33');
INSERT INTO `sys_log` VALUES ('978', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '4', '192.168.10.1', '2021-11-09 10:14:43');
INSERT INTO `sys_log` VALUES ('979', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '5', '192.168.10.1', '2021-11-09 10:14:44');
INSERT INTO `sys_log` VALUES ('980', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '6', '192.168.10.1', '2021-11-09 10:15:27');
INSERT INTO `sys_log` VALUES ('981', 'admin', '分页查询角色列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[{\"asc\":false,\"column\":\"create_time\"}],\"pages\":0,\"records\":[{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"admin\",\"roleId\":1,\"roleName\":\"admin\"},{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"每天上货，清理库存\",\"roleId\":2,\"roleName\":\"上货员\"},{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"test\",\"roleId\":11,\"roleName\":\"test\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '42', '192.168.10.1', '2021-11-09 10:15:32');
INSERT INTO `sys_log` VALUES ('982', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[{\"asc\":false,\"column\":\"create_time\"}],\"pages\":0,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"},{\"createTime\":1564794355000,\"createUserId\":1,\"email\":\"123@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"status\":1,\"userId\":4,\"username\":\"test\"},{\"createTime\":1636278803000,\"createUserId\":1,\"email\":\"aaa@qq.com\",\"mobile\":\"13111111111\",\"password\":\"$2a$10$/WMR6kEnw2QF.PH5XXFSYeN9oImFnha3W0x9EquyVdu/mRWhdy97G\",\"shopId\":1,\"status\":1,\"userId\":5,\"username\":\"test3\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '12', '192.168.10.1', '2021-11-09 10:15:36');
INSERT INTO `sys_log` VALUES ('983', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '82', '192.168.10.1', '2021-11-09 10:18:58');
INSERT INTO `sys_log` VALUES ('984', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '23', '192.168.10.1', '2021-11-09 10:18:58');
INSERT INTO `sys_log` VALUES ('985', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[{\"asc\":false,\"column\":\"create_time\"}],\"pages\":0,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"},{\"createTime\":1564794355000,\"createUserId\":1,\"email\":\"123@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"status\":1,\"userId\":4,\"username\":\"test\"},{\"createTime\":1636278803000,\"createUserId\":1,\"email\":\"aaa@qq.com\",\"mobile\":\"13111111111\",\"password\":\"$2a$10$/WMR6kEnw2QF.PH5XXFSYeN9oImFnha3W0x9EquyVdu/mRWhdy97G\",\"shopId\":1,\"status\":1,\"userId\":5,\"username\":\"test3\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '16', '192.168.10.1', '2021-11-09 10:19:04');
INSERT INTO `sys_log` VALUES ('986', 'admin', '分页查询角色列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[{\"asc\":false,\"column\":\"create_time\"}],\"pages\":0,\"records\":[{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"admin\",\"roleId\":1,\"roleName\":\"admin\"},{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"每天上货，清理库存\",\"roleId\":2,\"roleName\":\"上货员\"},{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"test\",\"roleId\":11,\"roleName\":\"test\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '20', '192.168.10.1', '2021-11-09 10:19:06');
INSERT INTO `sys_log` VALUES ('987', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-09 10:23:21');
INSERT INTO `sys_log` VALUES ('988', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '13', '192.168.10.1', '2021-11-09 10:23:21');
INSERT INTO `sys_log` VALUES ('989', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '6', '192.168.10.1', '2021-11-09 10:24:06');
INSERT INTO `sys_log` VALUES ('990', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '4', '192.168.10.1', '2021-11-09 10:24:07');
INSERT INTO `sys_log` VALUES ('991', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '70', '192.168.10.1', '2021-11-09 10:49:48');
INSERT INTO `sys_log` VALUES ('992', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '20', '192.168.10.1', '2021-11-09 10:49:48');
INSERT INTO `sys_log` VALUES ('993', 'admin', '分页查询角色列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[],\"pages\":0,\"records\":[{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"admin\",\"roleId\":1,\"roleName\":\"admin\"},{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"每天上货，清理库存\",\"roleId\":2,\"roleName\":\"上货员\"},{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"test\",\"roleId\":11,\"roleName\":\"test\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '31', '192.168.10.1', '2021-11-09 10:49:55');
INSERT INTO `sys_log` VALUES ('994', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '4', '192.168.10.1', '2021-11-09 10:50:02');
INSERT INTO `sys_log` VALUES ('995', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '14', '192.168.10.1', '2021-11-09 10:50:02');
INSERT INTO `sys_log` VALUES ('996', 'admin', '分页查询角色列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[],\"pages\":0,\"records\":[{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"admin\",\"roleId\":1,\"roleName\":\"admin\"},{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"每天上货，清理库存\",\"roleId\":2,\"roleName\":\"上货员\"},{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"test\",\"roleId\":11,\"roleName\":\"test\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '6', '192.168.10.1', '2021-11-09 10:50:02');
INSERT INTO `sys_log` VALUES ('997', 'admin', '分页查询角色列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[],\"pages\":0,\"records\":[{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"admin\",\"roleId\":1,\"roleName\":\"admin\"},{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"每天上货，清理库存\",\"roleId\":2,\"roleName\":\"上货员\"},{\"createTime\":1636426215000,\"createUserId\":1,\"remark\":\"test11\",\"roleId\":11,\"roleName\":\"test11\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '3', '192.168.10.1', '2021-11-09 10:50:17');
INSERT INTO `sys_log` VALUES ('998', 'admin', '分页查询角色列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[],\"pages\":0,\"records\":[{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"admin\",\"roleId\":1,\"roleName\":\"admin\"},{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"每天上货，清理库存\",\"roleId\":2,\"roleName\":\"上货员\"},{\"createTime\":1636426215000,\"createUserId\":1,\"remark\":\"test11\",\"roleId\":11,\"roleName\":\"test11\"},{\"createTime\":1636426236000,\"createUserId\":1,\"remark\":\"test2\",\"roleId\":12,\"roleName\":\"test2\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '13', '192.168.10.1', '2021-11-09 10:50:38');
INSERT INTO `sys_log` VALUES ('999', 'admin', '分页查询角色列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[],\"pages\":0,\"records\":[{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"admin\",\"roleId\":1,\"roleName\":\"admin\"},{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"每天上货，清理库存\",\"roleId\":2,\"roleName\":\"上货员\"},{\"createTime\":1636426215000,\"createUserId\":1,\"remark\":\"test11\",\"roleId\":11,\"roleName\":\"test11\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '8', '192.168.10.1', '2021-11-09 10:50:43');
INSERT INTO `sys_log` VALUES ('1000', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-09 10:51:52');
INSERT INTO `sys_log` VALUES ('1001', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '7', '192.168.10.1', '2021-11-09 10:51:53');
INSERT INTO `sys_log` VALUES ('1002', 'admin', '分页查询角色列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[],\"pages\":0,\"records\":[{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"admin\",\"roleId\":1,\"roleName\":\"admin\"},{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"每天上货，清理库存\",\"roleId\":2,\"roleName\":\"上货员\"},{\"createTime\":1636426215000,\"createUserId\":1,\"remark\":\"test11\",\"roleId\":11,\"roleName\":\"test11\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '4', '192.168.10.1', '2021-11-09 10:51:53');
INSERT INTO `sys_log` VALUES ('1003', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[{\"asc\":false,\"column\":\"create_time\"}],\"pages\":0,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"},{\"createTime\":1564794355000,\"createUserId\":1,\"email\":\"123@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"status\":1,\"userId\":4,\"username\":\"test\"},{\"createTime\":1636278803000,\"createUserId\":1,\"email\":\"aaa@qq.com\",\"mobile\":\"13111111111\",\"password\":\"$2a$10$/WMR6kEnw2QF.PH5XXFSYeN9oImFnha3W0x9EquyVdu/mRWhdy97G\",\"shopId\":1,\"status\":1,\"userId\":5,\"username\":\"test3\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '10', '192.168.10.1', '2021-11-09 10:51:56');
INSERT INTO `sys_log` VALUES ('1004', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '85', '192.168.10.1', '2021-11-09 11:08:58');
INSERT INTO `sys_log` VALUES ('1005', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '52', '192.168.10.1', '2021-11-09 11:08:58');
INSERT INTO `sys_log` VALUES ('1006', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[],\"pages\":0,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"},{\"createTime\":1564794355000,\"createUserId\":1,\"email\":\"123@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"status\":1,\"userId\":4,\"username\":\"test\"},{\"createTime\":1636278803000,\"createUserId\":1,\"email\":\"aaa@qq.com\",\"mobile\":\"13111111111\",\"password\":\"$2a$10$/WMR6kEnw2QF.PH5XXFSYeN9oImFnha3W0x9EquyVdu/mRWhdy97G\",\"shopId\":1,\"status\":1,\"userId\":5,\"username\":\"test3\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '19', '192.168.10.1', '2021-11-09 11:08:59');
INSERT INTO `sys_log` VALUES ('1007', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[],\"pages\":0,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"},{\"createTime\":1564794355000,\"createUserId\":1,\"email\":\"123@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"status\":1,\"userId\":4,\"username\":\"test\"},{\"createTime\":1636278803000,\"createUserId\":1,\"email\":\"aaa@qq.com\",\"mobile\":\"13111111111\",\"password\":\"$2a$10$/WMR6kEnw2QF.PH5XXFSYeN9oImFnha3W0x9EquyVdu/mRWhdy97G\",\"shopId\":1,\"status\":1,\"userId\":5,\"username\":\"test3\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '5', '192.168.10.1', '2021-11-09 11:09:14');
INSERT INTO `sys_log` VALUES ('1008', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-09 11:09:27');
INSERT INTO `sys_log` VALUES ('1009', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '12', '192.168.10.1', '2021-11-09 11:09:27');
INSERT INTO `sys_log` VALUES ('1010', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[],\"pages\":0,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"},{\"createTime\":1564794355000,\"createUserId\":1,\"email\":\"123@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"status\":1,\"userId\":4,\"username\":\"test\"},{\"createTime\":1636278803000,\"createUserId\":1,\"email\":\"aaa@qq.com\",\"mobile\":\"13111111111\",\"password\":\"$2a$10$/WMR6kEnw2QF.PH5XXFSYeN9oImFnha3W0x9EquyVdu/mRWhdy97G\",\"shopId\":1,\"status\":1,\"userId\":5,\"username\":\"test3\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '13', '192.168.10.1', '2021-11-09 11:09:28');
INSERT INTO `sys_log` VALUES ('1011', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[],\"pages\":0,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"}],\"searchCount\":true,\"size\":10,\"total\":0},{\"username\":\"admin\"}]', '9', '192.168.10.1', '2021-11-09 11:09:35');
INSERT INTO `sys_log` VALUES ('1012', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '7', '192.168.10.1', '2021-11-09 11:09:54');
INSERT INTO `sys_log` VALUES ('1013', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-09 11:09:55');
INSERT INTO `sys_log` VALUES ('1014', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[],\"pages\":0,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"},{\"createTime\":1564794355000,\"createUserId\":1,\"email\":\"123@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"status\":1,\"userId\":4,\"username\":\"test\"},{\"createTime\":1636278803000,\"createUserId\":1,\"email\":\"aaa@qq.com\",\"mobile\":\"13111111111\",\"password\":\"$2a$10$/WMR6kEnw2QF.PH5XXFSYeN9oImFnha3W0x9EquyVdu/mRWhdy97G\",\"shopId\":1,\"status\":1,\"userId\":5,\"username\":\"test3\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '9', '192.168.10.1', '2021-11-09 11:09:56');
INSERT INTO `sys_log` VALUES ('1015', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '75', '192.168.10.1', '2021-11-09 11:15:04');
INSERT INTO `sys_log` VALUES ('1016', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '50', '192.168.10.1', '2021-11-09 11:15:04');
INSERT INTO `sys_log` VALUES ('1017', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[],\"pages\":0,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"},{\"createTime\":1564794355000,\"createUserId\":1,\"email\":\"123@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"status\":1,\"userId\":4,\"username\":\"test\"},{\"createTime\":1636278803000,\"createUserId\":1,\"email\":\"aaa@qq.com\",\"mobile\":\"13111111111\",\"password\":\"$2a$10$/WMR6kEnw2QF.PH5XXFSYeN9oImFnha3W0x9EquyVdu/mRWhdy97G\",\"shopId\":1,\"status\":1,\"userId\":5,\"username\":\"test3\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '13', '192.168.10.1', '2021-11-09 11:15:19');
INSERT INTO `sys_log` VALUES ('1018', 'admin', '修改一个用户', 'update', '[{\"email\":\"12344@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$GRYBoK0h6KRqtKzTzw338Oj4.lGwEtp7QaGGWWp6N9q5qe7dTSMl2\",\"status\":1,\"userId\":4,\"username\":\"test\"}]', '152', '192.168.10.1', '2021-11-09 11:15:40');
INSERT INTO `sys_log` VALUES ('1019', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[],\"pages\":0,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"},{\"createTime\":1564794355000,\"createUserId\":1,\"email\":\"12344@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$GRYBoK0h6KRqtKzTzw338Oj4.lGwEtp7QaGGWWp6N9q5qe7dTSMl2\",\"status\":1,\"userId\":4,\"username\":\"test\"},{\"createTime\":1636278803000,\"createUserId\":1,\"email\":\"aaa@qq.com\",\"mobile\":\"13111111111\",\"password\":\"$2a$10$/WMR6kEnw2QF.PH5XXFSYeN9oImFnha3W0x9EquyVdu/mRWhdy97G\",\"shopId\":1,\"status\":1,\"userId\":5,\"username\":\"test3\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '9', '192.168.10.1', '2021-11-09 11:15:42');
INSERT INTO `sys_log` VALUES ('1020', 'admin', '分页查询角色列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[],\"pages\":0,\"records\":[{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"admin\",\"roleId\":1,\"roleName\":\"admin\"},{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"每天上货，清理库存\",\"roleId\":2,\"roleName\":\"上货员\"},{\"createTime\":1636426215000,\"createUserId\":1,\"remark\":\"test11\",\"roleId\":11,\"roleName\":\"test11\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '9', '192.168.10.1', '2021-11-09 11:16:44');
INSERT INTO `sys_log` VALUES ('1021', 'admin', '删除一个用户', 'delete', '[5]', '15', '192.168.10.1', '2021-11-09 11:20:36');
INSERT INTO `sys_log` VALUES ('1022', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[],\"pages\":0,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"},{\"createTime\":1564794355000,\"createUserId\":1,\"email\":\"12344@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$GRYBoK0h6KRqtKzTzw338Oj4.lGwEtp7QaGGWWp6N9q5qe7dTSMl2\",\"status\":1,\"userId\":4,\"username\":\"test\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '4', '192.168.10.1', '2021-11-09 11:20:38');
INSERT INTO `sys_log` VALUES ('1023', 'admin', '新增一个用户', 'saveSysUser', '[{\"createTime\":1636428093129,\"createUserId\":1,\"email\":\"11@qq.com\",\"mobile\":\"13111111111\",\"password\":\"$2a$10$XVCYyWWBPPif.CwABaptuuoH46WkOECcup4w.RSGJpMBxUcGQrB6y\",\"roleIdList\":[1],\"shopId\":1,\"status\":1,\"userId\":7,\"username\":\"wangwu\"}]', '158', '192.168.10.1', '2021-11-09 11:21:33');
INSERT INTO `sys_log` VALUES ('1024', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[],\"pages\":0,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"},{\"createTime\":1564794355000,\"createUserId\":1,\"email\":\"12344@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$GRYBoK0h6KRqtKzTzw338Oj4.lGwEtp7QaGGWWp6N9q5qe7dTSMl2\",\"status\":1,\"userId\":4,\"username\":\"test\"},{\"createTime\":1636428093000,\"createUserId\":1,\"email\":\"11@qq.com\",\"mobile\":\"13111111111\",\"password\":\"$2a$10$XVCYyWWBPPif.CwABaptuuoH46WkOECcup4w.RSGJpMBxUcGQrB6y\",\"shopId\":1,\"status\":1,\"userId\":7,\"username\":\"wangwu\"}],\"searchCount\":true,\"size\":10,\"total\":0},{}]', '4', '192.168.10.1', '2021-11-09 11:21:35');
INSERT INTO `sys_log` VALUES ('1025', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '41', '192.168.10.1', '2021-11-09 14:17:33');
INSERT INTO `sys_log` VALUES ('1026', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-09 14:17:34');
INSERT INTO `sys_log` VALUES ('1027', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '64', '192.168.10.1', '2021-11-09 14:19:46');
INSERT INTO `sys_log` VALUES ('1028', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '21', '192.168.10.1', '2021-11-09 14:19:46');
INSERT INTO `sys_log` VALUES ('1029', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '8', '192.168.10.1', '2021-11-09 14:24:09');
INSERT INTO `sys_log` VALUES ('1030', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '1', '192.168.10.1', '2021-11-09 14:24:09');
INSERT INTO `sys_log` VALUES ('1031', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '26', '192.168.10.1', '2021-11-09 14:25:39');
INSERT INTO `sys_log` VALUES ('1032', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '10', '192.168.10.1', '2021-11-09 14:25:39');
INSERT INTO `sys_log` VALUES ('1033', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-09 14:27:18');
INSERT INTO `sys_log` VALUES ('1034', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-09 14:27:18');
INSERT INTO `sys_log` VALUES ('1035', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '35', '192.168.10.1', '2021-11-09 23:01:24');
INSERT INTO `sys_log` VALUES ('1036', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '1', '192.168.10.1', '2021-11-09 23:01:24');
INSERT INTO `sys_log` VALUES ('1037', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '52', '192.168.10.1', '2021-11-14 10:48:21');
INSERT INTO `sys_log` VALUES ('1038', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '22', '192.168.10.1', '2021-11-14 10:48:21');
INSERT INTO `sys_log` VALUES ('1039', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-14 10:49:19');
INSERT INTO `sys_log` VALUES ('1040', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-14 10:49:20');
INSERT INTO `sys_log` VALUES ('1041', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '28', '192.168.10.1', '2021-11-14 12:18:04');
INSERT INTO `sys_log` VALUES ('1042', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-14 12:18:04');
INSERT INTO `sys_log` VALUES ('1043', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '67', '192.168.10.1', '2021-11-14 12:20:47');
INSERT INTO `sys_log` VALUES ('1044', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '26', '192.168.10.1', '2021-11-14 12:20:48');
INSERT INTO `sys_log` VALUES ('1045', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '79', '192.168.10.1', '2021-11-14 12:22:12');
INSERT INTO `sys_log` VALUES ('1046', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '21', '192.168.10.1', '2021-11-14 12:22:12');
INSERT INTO `sys_log` VALUES ('1047', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-14 12:22:21');
INSERT INTO `sys_log` VALUES ('1048', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-14 12:22:21');
INSERT INTO `sys_log` VALUES ('1049', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-14 12:22:53');
INSERT INTO `sys_log` VALUES ('1050', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-14 12:22:53');
INSERT INTO `sys_log` VALUES ('1051', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '24', '192.168.10.1', '2021-11-14 12:23:49');
INSERT INTO `sys_log` VALUES ('1052', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '5', '192.168.10.1', '2021-11-14 12:23:49');
INSERT INTO `sys_log` VALUES ('1053', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '4', '192.168.10.1', '2021-11-14 12:24:08');
INSERT INTO `sys_log` VALUES ('1054', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-14 12:24:08');
INSERT INTO `sys_log` VALUES ('1055', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '12', '192.168.10.1', '2021-11-14 12:25:51');
INSERT INTO `sys_log` VALUES ('1056', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '1', '192.168.10.1', '2021-11-14 12:25:51');
INSERT INTO `sys_log` VALUES ('1057', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-14 12:25:59');
INSERT INTO `sys_log` VALUES ('1058', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-14 12:25:59');
INSERT INTO `sys_log` VALUES ('1059', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '28', '192.168.10.1', '2021-11-14 12:47:18');
INSERT INTO `sys_log` VALUES ('1060', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-14 12:47:18');
INSERT INTO `sys_log` VALUES ('1061', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '6', '192.168.10.1', '2021-11-14 12:47:41');
INSERT INTO `sys_log` VALUES ('1062', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-14 12:47:41');
INSERT INTO `sys_log` VALUES ('1063', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '28', '192.168.10.1', '2021-11-14 13:33:28');
INSERT INTO `sys_log` VALUES ('1064', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '7', '192.168.10.1', '2021-11-14 13:33:29');
INSERT INTO `sys_log` VALUES ('1065', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '19', '192.168.10.1', '2021-11-14 14:12:21');
INSERT INTO `sys_log` VALUES ('1066', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '4', '192.168.10.1', '2021-11-14 14:12:21');
INSERT INTO `sys_log` VALUES ('1067', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-14 14:12:48');
INSERT INTO `sys_log` VALUES ('1068', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '5', '192.168.10.1', '2021-11-14 14:12:49');
INSERT INTO `sys_log` VALUES ('1069', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '19', '192.168.10.1', '2021-11-14 14:44:42');
INSERT INTO `sys_log` VALUES ('1070', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-14 14:44:42');
INSERT INTO `sys_log` VALUES ('1071', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '58', '192.168.10.1', '2021-11-14 14:50:25');
INSERT INTO `sys_log` VALUES ('1072', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-14 14:50:26');
INSERT INTO `sys_log` VALUES ('1073', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-14 14:51:44');
INSERT INTO `sys_log` VALUES ('1074', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '1', '192.168.10.1', '2021-11-14 14:51:44');
INSERT INTO `sys_log` VALUES ('1075', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '4', '192.168.10.1', '2021-11-14 14:55:51');
INSERT INTO `sys_log` VALUES ('1076', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-14 14:55:52');
INSERT INTO `sys_log` VALUES ('1077', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '19', '192.168.10.1', '2021-11-14 14:56:06');
INSERT INTO `sys_log` VALUES ('1078', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-14 14:56:06');
INSERT INTO `sys_log` VALUES ('1079', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-14 14:57:10');
INSERT INTO `sys_log` VALUES ('1080', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-14 14:57:10');
INSERT INTO `sys_log` VALUES ('1081', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '28', '192.168.10.1', '2021-11-14 15:01:18');
INSERT INTO `sys_log` VALUES ('1082', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '11', '192.168.10.1', '2021-11-14 15:01:18');
INSERT INTO `sys_log` VALUES ('1083', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-14 15:01:36');
INSERT INTO `sys_log` VALUES ('1084', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '5', '192.168.10.1', '2021-11-14 15:01:37');
INSERT INTO `sys_log` VALUES ('1085', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '7', '192.168.10.1', '2021-11-14 15:03:37');
INSERT INTO `sys_log` VALUES ('1086', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '19', '192.168.10.1', '2021-11-14 15:03:37');
INSERT INTO `sys_log` VALUES ('1087', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '6', '192.168.10.1', '2021-11-14 15:03:55');
INSERT INTO `sys_log` VALUES ('1088', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '1', '192.168.10.1', '2021-11-14 15:03:55');
INSERT INTO `sys_log` VALUES ('1089', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '62', '192.168.10.1', '2021-11-14 15:18:23');
INSERT INTO `sys_log` VALUES ('1090', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-14 15:18:23');
INSERT INTO `sys_log` VALUES ('1091', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '5', '192.168.10.1', '2021-11-14 15:18:29');
INSERT INTO `sys_log` VALUES ('1092', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-14 15:18:29');
INSERT INTO `sys_log` VALUES ('1093', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-14 15:19:01');
INSERT INTO `sys_log` VALUES ('1094', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-14 15:19:01');
INSERT INTO `sys_log` VALUES ('1095', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-14 15:19:08');
INSERT INTO `sys_log` VALUES ('1096', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-14 15:19:08');
INSERT INTO `sys_log` VALUES ('1097', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '4', '192.168.10.1', '2021-11-14 15:19:50');
INSERT INTO `sys_log` VALUES ('1098', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-14 15:19:50');
INSERT INTO `sys_log` VALUES ('1099', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-14 15:20:14');
INSERT INTO `sys_log` VALUES ('1100', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '1', '192.168.10.1', '2021-11-14 15:20:14');
INSERT INTO `sys_log` VALUES ('1101', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '29', '192.168.10.1', '2021-11-14 15:21:36');
INSERT INTO `sys_log` VALUES ('1102', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-14 15:21:36');
INSERT INTO `sys_log` VALUES ('1103', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-14 15:21:42');
INSERT INTO `sys_log` VALUES ('1104', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-14 15:21:43');
INSERT INTO `sys_log` VALUES ('1105', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '4', '192.168.10.1', '2021-11-14 15:21:50');
INSERT INTO `sys_log` VALUES ('1106', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-14 15:21:51');
INSERT INTO `sys_log` VALUES ('1107', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-14 15:21:59');
INSERT INTO `sys_log` VALUES ('1108', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '5', '192.168.10.1', '2021-11-14 15:21:59');
INSERT INTO `sys_log` VALUES ('1109', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-14 15:22:07');
INSERT INTO `sys_log` VALUES ('1110', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-14 15:22:07');
INSERT INTO `sys_log` VALUES ('1111', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '1', '192.168.10.1', '2021-11-14 15:22:21');
INSERT INTO `sys_log` VALUES ('1112', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-14 15:22:21');
INSERT INTO `sys_log` VALUES ('1113', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-14 15:22:36');
INSERT INTO `sys_log` VALUES ('1114', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-14 15:22:37');
INSERT INTO `sys_log` VALUES ('1115', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-14 15:22:44');
INSERT INTO `sys_log` VALUES ('1116', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '1', '192.168.10.1', '2021-11-14 15:22:44');
INSERT INTO `sys_log` VALUES ('1117', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-14 15:23:00');
INSERT INTO `sys_log` VALUES ('1118', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-14 15:23:00');
INSERT INTO `sys_log` VALUES ('1119', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-14 15:23:14');
INSERT INTO `sys_log` VALUES ('1120', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '1', '192.168.10.1', '2021-11-14 15:23:15');
INSERT INTO `sys_log` VALUES ('1121', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '30', '192.168.10.1', '2021-11-14 15:24:05');
INSERT INTO `sys_log` VALUES ('1122', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-14 15:24:05');
INSERT INTO `sys_log` VALUES ('1123', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '27', '192.168.10.1', '2021-11-14 15:32:05');
INSERT INTO `sys_log` VALUES ('1124', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-14 15:32:05');
INSERT INTO `sys_log` VALUES ('1125', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-14 15:32:58');
INSERT INTO `sys_log` VALUES ('1126', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-14 15:32:59');
INSERT INTO `sys_log` VALUES ('1127', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-14 15:34:17');
INSERT INTO `sys_log` VALUES ('1128', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-14 15:34:18');
INSERT INTO `sys_log` VALUES ('1129', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-14 15:34:31');
INSERT INTO `sys_log` VALUES ('1130', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '5', '192.168.10.1', '2021-11-14 15:34:32');
INSERT INTO `sys_log` VALUES ('1131', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-14 15:34:50');
INSERT INTO `sys_log` VALUES ('1132', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-14 15:34:51');
INSERT INTO `sys_log` VALUES ('1133', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-14 15:34:59');
INSERT INTO `sys_log` VALUES ('1134', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-14 15:35:00');
INSERT INTO `sys_log` VALUES ('1135', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-14 15:35:16');
INSERT INTO `sys_log` VALUES ('1136', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-14 15:35:16');
INSERT INTO `sys_log` VALUES ('1137', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-14 15:35:25');
INSERT INTO `sys_log` VALUES ('1138', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-14 15:35:25');
INSERT INTO `sys_log` VALUES ('1139', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-14 15:36:13');
INSERT INTO `sys_log` VALUES ('1140', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '1', '192.168.10.1', '2021-11-14 15:36:13');
INSERT INTO `sys_log` VALUES ('1141', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '1', '192.168.10.1', '2021-11-14 15:36:59');
INSERT INTO `sys_log` VALUES ('1142', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-14 15:36:59');
INSERT INTO `sys_log` VALUES ('1143', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-14 15:37:31');
INSERT INTO `sys_log` VALUES ('1144', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '1', '192.168.10.1', '2021-11-14 15:37:31');
INSERT INTO `sys_log` VALUES ('1145', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '127', '192.168.10.1', '2021-11-14 16:43:28');
INSERT INTO `sys_log` VALUES ('1146', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-14 16:43:29');
INSERT INTO `sys_log` VALUES ('1147', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '6', '192.168.10.1', '2021-11-14 16:44:07');
INSERT INTO `sys_log` VALUES ('1148', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '23', '192.168.10.1', '2021-11-14 16:44:08');
INSERT INTO `sys_log` VALUES ('1149', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-14 16:44:51');
INSERT INTO `sys_log` VALUES ('1150', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-14 16:44:51');
INSERT INTO `sys_log` VALUES ('1151', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-14 16:45:02');
INSERT INTO `sys_log` VALUES ('1152', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '4', '192.168.10.1', '2021-11-14 16:45:02');
INSERT INTO `sys_log` VALUES ('1153', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-14 16:45:43');
INSERT INTO `sys_log` VALUES ('1154', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-14 16:45:43');
INSERT INTO `sys_log` VALUES ('1155', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '308', '192.168.10.1', '2021-11-16 17:30:44');
INSERT INTO `sys_log` VALUES ('1156', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '53', '192.168.10.1', '2021-11-16 17:30:44');
INSERT INTO `sys_log` VALUES ('1157', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '11', '192.168.10.1', '2021-11-16 17:32:19');
INSERT INTO `sys_log` VALUES ('1158', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '1', '192.168.10.1', '2021-11-16 17:32:19');
INSERT INTO `sys_log` VALUES ('1159', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '21', '192.168.10.1', '2021-11-16 17:32:45');
INSERT INTO `sys_log` VALUES ('1160', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-16 17:32:45');
INSERT INTO `sys_log` VALUES ('1161', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '75', '192.168.10.1', '2021-11-16 23:04:08');
INSERT INTO `sys_log` VALUES ('1162', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '19', '192.168.10.1', '2021-11-16 23:04:09');
INSERT INTO `sys_log` VALUES ('1163', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '6', '192.168.10.1', '2021-11-16 23:04:47');
INSERT INTO `sys_log` VALUES ('1164', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '4', '192.168.10.1', '2021-11-16 23:04:48');
INSERT INTO `sys_log` VALUES ('1165', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '92', '192.168.10.1', '2021-11-16 23:38:55');
INSERT INTO `sys_log` VALUES ('1166', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '92', '192.168.10.1', '2021-11-16 23:38:55');
INSERT INTO `sys_log` VALUES ('1167', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '29', '192.168.10.1', '2021-11-16 23:38:56');
INSERT INTO `sys_log` VALUES ('1168', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '7', '192.168.10.1', '2021-11-17 10:26:57');
INSERT INTO `sys_log` VALUES ('1169', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '4', '192.168.10.1', '2021-11-17 10:26:57');
INSERT INTO `sys_log` VALUES ('1170', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '20', '192.168.10.1', '2021-11-17 13:17:23');
INSERT INTO `sys_log` VALUES ('1171', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-17 13:17:24');
INSERT INTO `sys_log` VALUES ('1172', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '126', '192.168.10.1', '2021-11-17 13:21:58');
INSERT INTO `sys_log` VALUES ('1173', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '18', '192.168.10.1', '2021-11-17 13:21:58');
INSERT INTO `sys_log` VALUES ('1174', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '99', '192.168.10.1', '2021-11-17 13:33:10');
INSERT INTO `sys_log` VALUES ('1175', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '4', '192.168.10.1', '2021-11-17 13:33:10');
INSERT INTO `sys_log` VALUES ('1176', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-17 13:35:17');
INSERT INTO `sys_log` VALUES ('1177', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-17 13:35:18');
INSERT INTO `sys_log` VALUES ('1178', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '25', '192.168.10.1', '2021-11-17 13:48:18');
INSERT INTO `sys_log` VALUES ('1179', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-17 13:48:18');
INSERT INTO `sys_log` VALUES ('1180', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '193', '192.168.10.1', '2021-11-17 14:12:06');
INSERT INTO `sys_log` VALUES ('1181', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '34', '192.168.10.1', '2021-11-17 14:12:07');
INSERT INTO `sys_log` VALUES ('1182', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '45', '192.168.10.1', '2021-11-17 15:03:38');
INSERT INTO `sys_log` VALUES ('1183', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-17 15:03:38');
INSERT INTO `sys_log` VALUES ('1184', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '45', '192.168.10.1', '2021-11-17 15:07:06');
INSERT INTO `sys_log` VALUES ('1185', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-17 15:07:06');
INSERT INTO `sys_log` VALUES ('1186', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-17 15:09:35');
INSERT INTO `sys_log` VALUES ('1187', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '4', '192.168.10.1', '2021-11-17 15:09:35');
INSERT INTO `sys_log` VALUES ('1188', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '136', '192.168.10.1', '2021-11-17 16:17:33');
INSERT INTO `sys_log` VALUES ('1189', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '32', '192.168.10.1', '2021-11-17 16:17:34');
INSERT INTO `sys_log` VALUES ('1190', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[],\"pages\":1,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"},{\"createTime\":1564794355000,\"createUserId\":1,\"email\":\"12344@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$GRYBoK0h6KRqtKzTzw338Oj4.lGwEtp7QaGGWWp6N9q5qe7dTSMl2\",\"status\":1,\"userId\":4,\"username\":\"test\"},{\"createTime\":1636428093000,\"createUserId\":1,\"email\":\"11@qq.com\",\"mobile\":\"13111111111\",\"password\":\"$2a$10$XVCYyWWBPPif.CwABaptuuoH46WkOECcup4w.RSGJpMBxUcGQrB6y\",\"shopId\":1,\"status\":1,\"userId\":7,\"username\":\"wangwu\"}],\"searchCount\":true,\"size\":10,\"total\":3},{}]', '67', '192.168.10.1', '2021-11-17 16:17:46');
INSERT INTO `sys_log` VALUES ('1191', 'admin', '分页查询角色列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[],\"pages\":1,\"records\":[{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"admin\",\"roleId\":1,\"roleName\":\"admin\"},{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"每天上货，清理库存\",\"roleId\":2,\"roleName\":\"上货员\"},{\"createTime\":1636426215000,\"createUserId\":1,\"remark\":\"test11\",\"roleId\":11,\"roleName\":\"test11\"}],\"searchCount\":true,\"size\":10,\"total\":3},{}]', '27', '192.168.10.1', '2021-11-17 16:17:48');
INSERT INTO `sys_log` VALUES ('1192', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '89', '192.168.10.1', '2021-11-18 01:01:50');
INSERT INTO `sys_log` VALUES ('1193', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '24', '192.168.10.1', '2021-11-18 01:01:51');
INSERT INTO `sys_log` VALUES ('1194', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '60', '192.168.10.1', '2021-11-18 01:04:48');
INSERT INTO `sys_log` VALUES ('1195', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '4', '192.168.10.1', '2021-11-18 01:04:49');
INSERT INTO `sys_log` VALUES ('1196', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '6', '192.168.10.1', '2021-11-18 01:09:29');
INSERT INTO `sys_log` VALUES ('1197', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-18 01:09:29');
INSERT INTO `sys_log` VALUES ('1198', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '29', '192.168.10.1', '2021-11-18 01:33:53');
INSERT INTO `sys_log` VALUES ('1199', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '6', '192.168.10.1', '2021-11-18 01:33:54');
INSERT INTO `sys_log` VALUES ('1200', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '137', '192.168.10.1', '2021-11-18 02:39:53');
INSERT INTO `sys_log` VALUES ('1201', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '55', '192.168.10.1', '2021-11-18 02:39:54');
INSERT INTO `sys_log` VALUES ('1202', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '56', '192.168.10.1', '2021-11-18 02:46:01');
INSERT INTO `sys_log` VALUES ('1203', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-18 02:46:02');
INSERT INTO `sys_log` VALUES ('1204', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-18 02:46:28');
INSERT INTO `sys_log` VALUES ('1205', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '6', '192.168.10.1', '2021-11-18 02:46:29');
INSERT INTO `sys_log` VALUES ('1206', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '121', '192.168.10.1', '2021-11-18 03:00:33');
INSERT INTO `sys_log` VALUES ('1207', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-18 03:00:33');
INSERT INTO `sys_log` VALUES ('1208', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-18 03:01:37');
INSERT INTO `sys_log` VALUES ('1209', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '1', '192.168.10.1', '2021-11-18 03:01:37');
INSERT INTO `sys_log` VALUES ('1210', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-18 03:03:29');
INSERT INTO `sys_log` VALUES ('1211', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-18 03:03:29');
INSERT INTO `sys_log` VALUES ('1212', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '5', '192.168.10.1', '2021-11-18 03:04:44');
INSERT INTO `sys_log` VALUES ('1213', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-18 03:04:44');
INSERT INTO `sys_log` VALUES ('1214', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '60', '192.168.10.1', '2021-11-18 03:10:57');
INSERT INTO `sys_log` VALUES ('1215', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '5', '192.168.10.1', '2021-11-18 03:10:57');
INSERT INTO `sys_log` VALUES ('1216', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-18 03:11:39');
INSERT INTO `sys_log` VALUES ('1217', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-18 03:11:40');
INSERT INTO `sys_log` VALUES ('1218', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-18 03:12:19');
INSERT INTO `sys_log` VALUES ('1219', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-18 03:12:19');
INSERT INTO `sys_log` VALUES ('1220', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-18 03:13:07');
INSERT INTO `sys_log` VALUES ('1221', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '4', '192.168.10.1', '2021-11-18 03:13:07');
INSERT INTO `sys_log` VALUES ('1222', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '200', '192.168.10.1', '2021-11-18 16:52:33');
INSERT INTO `sys_log` VALUES ('1223', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '29', '192.168.10.1', '2021-11-18 16:52:33');
INSERT INTO `sys_log` VALUES ('1224', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '24', '192.168.10.1', '2021-11-18 19:21:51');
INSERT INTO `sys_log` VALUES ('1225', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '5', '192.168.10.1', '2021-11-18 19:21:51');
INSERT INTO `sys_log` VALUES ('1226', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '51', '192.168.10.1', '2021-11-18 19:28:01');
INSERT INTO `sys_log` VALUES ('1227', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-18 19:28:02');
INSERT INTO `sys_log` VALUES ('1228', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '6', '192.168.10.1', '2021-11-18 19:28:48');
INSERT INTO `sys_log` VALUES ('1229', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-18 19:28:48');
INSERT INTO `sys_log` VALUES ('1230', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '5', '192.168.10.1', '2021-11-18 19:30:03');
INSERT INTO `sys_log` VALUES ('1231', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-18 19:30:03');
INSERT INTO `sys_log` VALUES ('1232', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '35', '192.168.10.1', '2021-11-18 19:40:06');
INSERT INTO `sys_log` VALUES ('1233', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '56', '192.168.10.1', '2021-11-18 19:40:08');
INSERT INTO `sys_log` VALUES ('1234', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '78', '192.168.10.1', '2021-11-18 19:51:08');
INSERT INTO `sys_log` VALUES ('1235', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-18 19:51:11');
INSERT INTO `sys_log` VALUES ('1236', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '62', '192.168.10.1', '2021-11-18 19:52:25');
INSERT INTO `sys_log` VALUES ('1237', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '4', '192.168.10.1', '2021-11-18 19:52:26');
INSERT INTO `sys_log` VALUES ('1238', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '6', '192.168.10.1', '2021-11-18 19:52:44');
INSERT INTO `sys_log` VALUES ('1239', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '1', '192.168.10.1', '2021-11-18 19:52:44');
INSERT INTO `sys_log` VALUES ('1240', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '29', '192.168.10.1', '2021-11-18 20:04:04');
INSERT INTO `sys_log` VALUES ('1241', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-18 20:04:04');
INSERT INTO `sys_log` VALUES ('1242', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '5', '192.168.10.1', '2021-11-18 20:04:05');
INSERT INTO `sys_log` VALUES ('1243', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-18 20:04:06');
INSERT INTO `sys_log` VALUES ('1244', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '45', '192.168.10.1', '2021-11-18 20:15:07');
INSERT INTO `sys_log` VALUES ('1245', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '6', '192.168.10.1', '2021-11-18 20:15:08');
INSERT INTO `sys_log` VALUES ('1246', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '33', '192.168.10.1', '2021-11-18 20:19:05');
INSERT INTO `sys_log` VALUES ('1247', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-18 20:19:05');
INSERT INTO `sys_log` VALUES ('1248', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-18 20:19:06');
INSERT INTO `sys_log` VALUES ('1249', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '4', '192.168.10.1', '2021-11-18 20:19:06');
INSERT INTO `sys_log` VALUES ('1250', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '4', '192.168.10.1', '2021-11-18 20:20:34');
INSERT INTO `sys_log` VALUES ('1251', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '1', '192.168.10.1', '2021-11-18 20:20:34');
INSERT INTO `sys_log` VALUES ('1252', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '10', '192.168.10.1', '2021-11-18 20:21:14');
INSERT INTO `sys_log` VALUES ('1253', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-18 20:21:15');
INSERT INTO `sys_log` VALUES ('1254', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '4', '192.168.10.1', '2021-11-18 20:22:21');
INSERT INTO `sys_log` VALUES ('1255', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '7', '192.168.10.1', '2021-11-18 20:22:22');
INSERT INTO `sys_log` VALUES ('1256', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '33', '192.168.10.1', '2021-11-18 20:43:55');
INSERT INTO `sys_log` VALUES ('1257', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-18 20:43:56');
INSERT INTO `sys_log` VALUES ('1258', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '5', '192.168.10.1', '2021-11-18 20:44:09');
INSERT INTO `sys_log` VALUES ('1259', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '5', '192.168.10.1', '2021-11-18 20:44:09');
INSERT INTO `sys_log` VALUES ('1260', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-18 20:44:16');
INSERT INTO `sys_log` VALUES ('1261', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-18 20:44:17');
INSERT INTO `sys_log` VALUES ('1262', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-18 20:44:46');
INSERT INTO `sys_log` VALUES ('1263', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-18 20:44:46');
INSERT INTO `sys_log` VALUES ('1264', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '4', '192.168.10.1', '2021-11-18 20:44:56');
INSERT INTO `sys_log` VALUES ('1265', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '4', '192.168.10.1', '2021-11-18 20:44:56');
INSERT INTO `sys_log` VALUES ('1266', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-18 20:45:01');
INSERT INTO `sys_log` VALUES ('1267', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-18 20:45:01');
INSERT INTO `sys_log` VALUES ('1268', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '199', '192.168.10.1', '2021-11-19 13:40:14');
INSERT INTO `sys_log` VALUES ('1269', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '53', '192.168.10.1', '2021-11-19 13:40:14');
INSERT INTO `sys_log` VALUES ('1270', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '15', '192.168.10.1', '2021-11-19 13:40:24');
INSERT INTO `sys_log` VALUES ('1271', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-19 13:40:24');
INSERT INTO `sys_log` VALUES ('1272', 'admin', '分页查询角色列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[],\"pages\":1,\"records\":[{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"admin\",\"roleId\":1,\"roleName\":\"admin\"},{\"createTime\":1564735609000,\"createUserId\":1,\"remark\":\"每天上货，清理库存\",\"roleId\":2,\"roleName\":\"上货员\"},{\"createTime\":1636426215000,\"createUserId\":1,\"remark\":\"test11\",\"roleId\":11,\"roleName\":\"test11\"}],\"searchCount\":true,\"size\":10,\"total\":3},{}]', '48', '192.168.10.1', '2021-11-19 13:40:56');
INSERT INTO `sys_log` VALUES ('1273', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '155', '192.168.10.1', '2021-11-19 13:53:20');
INSERT INTO `sys_log` VALUES ('1274', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-19 13:53:21');
INSERT INTO `sys_log` VALUES ('1275', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-19 13:54:00');
INSERT INTO `sys_log` VALUES ('1276', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-19 13:54:01');
INSERT INTO `sys_log` VALUES ('1277', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '5', '192.168.10.1', '2021-11-19 13:55:15');
INSERT INTO `sys_log` VALUES ('1278', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-19 13:55:15');
INSERT INTO `sys_log` VALUES ('1279', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '4', '192.168.10.1', '2021-11-19 13:59:12');
INSERT INTO `sys_log` VALUES ('1280', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '5', '192.168.10.1', '2021-11-19 13:59:13');
INSERT INTO `sys_log` VALUES ('1281', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '32', '192.168.10.1', '2021-11-19 14:02:31');
INSERT INTO `sys_log` VALUES ('1282', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-19 14:02:31');
INSERT INTO `sys_log` VALUES ('1283', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-19 14:02:32');
INSERT INTO `sys_log` VALUES ('1284', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-19 14:02:33');
INSERT INTO `sys_log` VALUES ('1285', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '8', '192.168.10.1', '2021-11-19 14:18:51');
INSERT INTO `sys_log` VALUES ('1286', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-19 14:18:51');
INSERT INTO `sys_log` VALUES ('1287', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '5', '192.168.10.1', '2021-11-19 14:21:38');
INSERT INTO `sys_log` VALUES ('1288', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-19 14:21:38');
INSERT INTO `sys_log` VALUES ('1289', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-19 14:21:40');
INSERT INTO `sys_log` VALUES ('1290', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-19 14:21:41');
INSERT INTO `sys_log` VALUES ('1291', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-19 14:21:41');
INSERT INTO `sys_log` VALUES ('1292', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-19 14:21:42');
INSERT INTO `sys_log` VALUES ('1293', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-19 14:22:22');
INSERT INTO `sys_log` VALUES ('1294', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '9', '192.168.10.1', '2021-11-19 14:22:23');
INSERT INTO `sys_log` VALUES ('1295', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '169', '192.168.10.1', '2021-11-19 23:25:24');
INSERT INTO `sys_log` VALUES ('1296', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '38', '192.168.10.1', '2021-11-19 23:25:25');
INSERT INTO `sys_log` VALUES ('1297', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '16', '192.168.10.1', '2021-11-19 23:25:36');
INSERT INTO `sys_log` VALUES ('1298', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-19 23:25:36');
INSERT INTO `sys_log` VALUES ('1299', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-19 23:25:52');
INSERT INTO `sys_log` VALUES ('1300', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '4', '192.168.10.1', '2021-11-19 23:25:52');
INSERT INTO `sys_log` VALUES ('1301', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '216', '192.168.10.1', '2021-11-20 16:03:59');
INSERT INTO `sys_log` VALUES ('1302', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '57', '192.168.10.1', '2021-11-20 16:03:59');
INSERT INTO `sys_log` VALUES ('1303', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '28', '192.168.10.1', '2021-11-20 16:04:21');
INSERT INTO `sys_log` VALUES ('1304', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '7', '192.168.10.1', '2021-11-20 16:04:22');
INSERT INTO `sys_log` VALUES ('1305', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-20 16:04:22');
INSERT INTO `sys_log` VALUES ('1306', 'admin', '分页查询管理员列表', 'getSysUserPage', '[{\"current\":1,\"hitCount\":false,\"optimizeCountSql\":true,\"orders\":[],\"pages\":1,\"records\":[{\"createTime\":1478833871000,\"createUserId\":1,\"email\":\"root@123.com\",\"mobile\":\"13612345678\",\"password\":\"$2a$10$qzWbXq5P5ibLNNgFSDDCzezjjOPQ6SJoq95nf1TwGJMAP9Qgxm8DS\",\"shopId\":1,\"status\":1,\"userId\":1,\"username\":\"admin\"},{\"createTime\":1564794355000,\"createUserId\":1,\"email\":\"12344@qq.com\",\"mobile\":\"11111111111\",\"password\":\"$2a$10$GRYBoK0h6KRqtKzTzw338Oj4.lGwEtp7QaGGWWp6N9q5qe7dTSMl2\",\"status\":1,\"userId\":4,\"username\":\"test\"},{\"createTime\":1636428093000,\"createUserId\":1,\"email\":\"11@qq.com\",\"mobile\":\"13111111111\",\"password\":\"$2a$10$XVCYyWWBPPif.CwABaptuuoH46WkOECcup4w.RSGJpMBxUcGQrB6y\",\"shopId\":1,\"status\":1,\"userId\":7,\"username\":\"wangwu\"}],\"searchCount\":true,\"size\":10,\"total\":3},{}]', '169', '192.168.10.1', '2021-11-20 16:13:58');
INSERT INTO `sys_log` VALUES ('1307', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '38', '192.168.10.1', '2021-11-20 16:25:18');
INSERT INTO `sys_log` VALUES ('1308', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '4', '192.168.10.1', '2021-11-20 16:25:19');
INSERT INTO `sys_log` VALUES ('1309', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-20 16:25:35');
INSERT INTO `sys_log` VALUES ('1310', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-20 16:25:36');
INSERT INTO `sys_log` VALUES ('1311', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-20 16:25:44');
INSERT INTO `sys_log` VALUES ('1312', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-20 16:25:45');
INSERT INTO `sys_log` VALUES ('1313', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '4', '192.168.10.1', '2021-11-20 16:25:55');
INSERT INTO `sys_log` VALUES ('1314', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-20 16:25:56');
INSERT INTO `sys_log` VALUES ('1315', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-20 16:26:38');
INSERT INTO `sys_log` VALUES ('1316', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-20 16:26:38');
INSERT INTO `sys_log` VALUES ('1317', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '7', '192.168.10.1', '2021-11-20 16:27:42');
INSERT INTO `sys_log` VALUES ('1318', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-20 16:27:43');
INSERT INTO `sys_log` VALUES ('1319', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-20 16:27:52');
INSERT INTO `sys_log` VALUES ('1320', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '5', '192.168.10.1', '2021-11-20 16:27:52');
INSERT INTO `sys_log` VALUES ('1321', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '4', '192.168.10.1', '2021-11-20 16:30:13');
INSERT INTO `sys_log` VALUES ('1322', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-20 16:30:13');
INSERT INTO `sys_log` VALUES ('1323', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '4', '192.168.10.1', '2021-11-20 16:30:30');
INSERT INTO `sys_log` VALUES ('1324', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-20 16:30:31');
INSERT INTO `sys_log` VALUES ('1325', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '5', '192.168.10.1', '2021-11-20 16:30:55');
INSERT INTO `sys_log` VALUES ('1326', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '4', '192.168.10.1', '2021-11-20 16:30:55');
INSERT INTO `sys_log` VALUES ('1327', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '32', '192.168.10.1', '2021-11-20 16:35:35');
INSERT INTO `sys_log` VALUES ('1328', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '5', '192.168.10.1', '2021-11-20 16:35:35');
INSERT INTO `sys_log` VALUES ('1329', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-20 16:36:12');
INSERT INTO `sys_log` VALUES ('1330', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '1', '192.168.10.1', '2021-11-20 16:36:12');
INSERT INTO `sys_log` VALUES ('1331', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-20 16:36:50');
INSERT INTO `sys_log` VALUES ('1332', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '9', '192.168.10.1', '2021-11-20 16:36:50');
INSERT INTO `sys_log` VALUES ('1333', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-20 16:37:17');
INSERT INTO `sys_log` VALUES ('1334', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-20 16:37:17');
INSERT INTO `sys_log` VALUES ('1335', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '4', '192.168.10.1', '2021-11-20 16:37:34');
INSERT INTO `sys_log` VALUES ('1336', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-20 16:37:34');
INSERT INTO `sys_log` VALUES ('1337', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '2', '192.168.10.1', '2021-11-20 16:37:40');
INSERT INTO `sys_log` VALUES ('1338', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-20 16:37:40');
INSERT INTO `sys_log` VALUES ('1339', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '8', '192.168.10.1', '2021-11-20 16:37:47');
INSERT INTO `sys_log` VALUES ('1340', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-20 16:37:47');
INSERT INTO `sys_log` VALUES ('1341', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-20 16:38:07');
INSERT INTO `sys_log` VALUES ('1342', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-20 16:38:07');
INSERT INTO `sys_log` VALUES ('1343', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '39', '192.168.10.1', '2021-11-20 16:41:40');
INSERT INTO `sys_log` VALUES ('1344', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '3', '192.168.10.1', '2021-11-20 16:41:41');
INSERT INTO `sys_log` VALUES ('1345', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '4', '192.168.10.1', '2021-11-20 16:44:25');
INSERT INTO `sys_log` VALUES ('1346', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '4', '192.168.10.1', '2021-11-20 16:44:25');
INSERT INTO `sys_log` VALUES ('1347', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '5', '192.168.10.1', '2021-11-20 16:44:31');
INSERT INTO `sys_log` VALUES ('1348', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-20 16:44:32');
INSERT INTO `sys_log` VALUES ('1349', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '6', '192.168.10.1', '2021-11-20 16:44:37');
INSERT INTO `sys_log` VALUES ('1350', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '4', '192.168.10.1', '2021-11-20 16:44:37');
INSERT INTO `sys_log` VALUES ('1351', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-20 16:44:44');
INSERT INTO `sys_log` VALUES ('1352', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-20 16:44:44');
INSERT INTO `sys_log` VALUES ('1353', 'admin', '加载用户菜单和权限的接口', 'loadMenuAndAuths', '[]', '3', '192.168.10.1', '2021-11-20 16:47:55');
INSERT INTO `sys_log` VALUES ('1354', 'admin', '查询当前登录用户的信息', 'getUserInfo', '[]', '2', '192.168.10.1', '2021-11-20 16:47:55');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=403 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', null, null, '0', null, '1');
INSERT INTO `sys_menu` VALUES ('2', '1', '管理员列表', '/sys/user', null, '1', null, '1');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', '/sys/role', null, '1', null, '1');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', '/sys/menu', null, '1', null, '1');
INSERT INTO `sys_menu` VALUES ('6', '1', '日志管理', '/sys/log', null, '1', null, '1');
INSERT INTO `sys_menu` VALUES ('10', '0', '容器管理', null, null, '0', null, '1');
INSERT INTO `sys_menu` VALUES ('11', '10', 'Node管理', '/container/node', null, '1', null, '1');
INSERT INTO `sys_menu` VALUES ('12', '10', 'namespace管理', '/container/namespace', null, '1', null, '1');
INSERT INTO `sys_menu` VALUES ('13', '10', 'Deployment管理', '/container/deployment', null, '1', null, '1');
INSERT INTO `sys_menu` VALUES ('14', '10', 'Pod管理', '/container/pod', null, '1', null, '1');
INSERT INTO `sys_menu` VALUES ('15', '10', 'Service管理', '/container/service', null, '1', null, '1');
INSERT INTO `sys_menu` VALUES ('50', '0', '商品', null, null, '0', null, '1');
INSERT INTO `sys_menu` VALUES ('51', '50', '产品', '/prod/product', null, '0', null, '1');
INSERT INTO `sys_menu` VALUES ('52', '50', '产品属性', '/prod/prodProp', null, '1', null, '1');
INSERT INTO `sys_menu` VALUES ('53', '50', '产品属性值', '/prod/prodPropValue', null, '1', null, '1');
INSERT INTO `sys_menu` VALUES ('54', '50', 'Sku', '/prod/sku', null, '1', null, '1');
INSERT INTO `sys_menu` VALUES ('55', '50', '订单', '/prod/order', null, '1', null, '1');
INSERT INTO `sys_menu` VALUES ('100', '0', '仪表盘', null, null, '0', null, '1');
INSERT INTO `sys_menu` VALUES ('101', '100', '访问量', '/dau/request', null, '1', null, '1');
INSERT INTO `sys_menu` VALUES ('150', '0', '兑换券', null, null, '0', null, '1');
INSERT INTO `sys_menu` VALUES ('151', '150', '兑换券', '/voucher/voucher', null, '1', null, '1');
INSERT INTO `sys_menu` VALUES ('200', '0', '终端', null, null, '0', null, '1');
INSERT INTO `sys_menu` VALUES ('201', '200', 'Terminal', '/terminal/terminal', null, '1', null, '1');
INSERT INTO `sys_menu` VALUES ('250', '0', '通知', null, null, '0', null, '1');
INSERT INTO `sys_menu` VALUES ('251', '250', '通知栏信息', '/notify/notify', null, '1', null, '1');
INSERT INTO `sys_menu` VALUES ('400', '0', '用户管理', null, null, '0', null, '1');
INSERT INTO `sys_menu` VALUES ('401', '400', '用户信息', '/user/info', null, '1', null, '1');
INSERT INTO `sys_menu` VALUES ('402', '400', '用户产品信息', '/user/userProd', null, '1', null, '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', 'admin', '1', '2019-08-02 16:46:49');
INSERT INTO `sys_role` VALUES ('2', 'employee', '雇员', '1', '2019-08-02 16:46:49');
INSERT INTO `sys_role` VALUES ('11', 'test11', 'test11', '1', '2021-11-09 10:50:15');
INSERT INTO `sys_role` VALUES ('12', 'aa', '11a23', '1', '2021-11-09 10:50:15');
INSERT INTO `sys_role` VALUES ('13', 'a2', '2222', null, '2021-11-09 10:50:15');
INSERT INTO `sys_role` VALUES ('14', 'a3', '111', null, '2021-12-22 22:38:13');
INSERT INTO `sys_role` VALUES ('15', 'a4', null, null, '2021-12-22 22:48:09');
INSERT INTO `sys_role` VALUES ('17', 'a5', 'a5', null, '2021-12-22 22:48:46');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=197 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('169', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('170', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('171', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('172', '1', '4');
INSERT INTO `sys_role_menu` VALUES ('173', '1', '6');
INSERT INTO `sys_role_menu` VALUES ('174', '1', '10');
INSERT INTO `sys_role_menu` VALUES ('175', '1', '11');
INSERT INTO `sys_role_menu` VALUES ('176', '1', '12');
INSERT INTO `sys_role_menu` VALUES ('177', '1', '13');
INSERT INTO `sys_role_menu` VALUES ('178', '1', '14');
INSERT INTO `sys_role_menu` VALUES ('179', '1', '15');
INSERT INTO `sys_role_menu` VALUES ('180', '1', '100');
INSERT INTO `sys_role_menu` VALUES ('181', '1', '101');
INSERT INTO `sys_role_menu` VALUES ('182', '1', '200');
INSERT INTO `sys_role_menu` VALUES ('183', '1', '201');
INSERT INTO `sys_role_menu` VALUES ('184', '1', '50');
INSERT INTO `sys_role_menu` VALUES ('185', '1', '51');
INSERT INTO `sys_role_menu` VALUES ('186', '1', '52');
INSERT INTO `sys_role_menu` VALUES ('187', '1', '53');
INSERT INTO `sys_role_menu` VALUES ('188', '1', '54');
INSERT INTO `sys_role_menu` VALUES ('189', '1', '150');
INSERT INTO `sys_role_menu` VALUES ('190', '1', '151');
INSERT INTO `sys_role_menu` VALUES ('191', '1', '250');
INSERT INTO `sys_role_menu` VALUES ('192', '1', '251');
INSERT INTO `sys_role_menu` VALUES ('193', '1', '400');
INSERT INTO `sys_role_menu` VALUES ('194', '1', '401');
INSERT INTO `sys_role_menu` VALUES ('195', '1', '402');
INSERT INTO `sys_role_menu` VALUES ('196', '1', '55');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '$2a$10$cOiaVsqTRrWZeYQgySWRM.Flg8MDINQZnEfSfQZb3eMKaxB4WVOJq', 'admin@qq.com', '13888888888', '1', null, '2021-12-22 00:18:21');
INSERT INTO `sys_user` VALUES ('4', 'test', '$2a$10$zS3z/CdG1/dqwIeJRR0CnujAzZDH39sSRhol5rl02NVhE5ri9kn/6', '12345@qq.com', '13888888888', '1', null, '2021-12-22 00:18:21');
INSERT INTO `sys_user` VALUES ('9', 't1', '$2a$10$QSYpE9F6RGk9jNEwc4U/fuVIByfBhyJtd8eHoFTf1NjonSwElpZ4e', '11@qq.com', '1388888888', '1', null, '2021-12-22 00:18:21');
INSERT INTO `sys_user` VALUES ('10', 't2', '$2a$10$dfaFOC1ISPL2VO5gH8rsoeVW1B8CO0e3ZZ9U6AA4NOwQYPeXLgR7K', '11@qq.com', '13888888888', '0', null, '2021-12-22 00:18:21');
INSERT INTO `sys_user` VALUES ('11', 't3', '$2a$10$7ibY5Br88wiZd.pMPgosJuW1DI3ZdW91EL511ia59UFZSM35O06zq', '123455@qq.com', '13888888888', '1', null, '2022-01-18 13:48:23');
INSERT INTO `sys_user` VALUES ('12', 't4', '$2a$10$p9eEAvog7hlNoH.v37z34uHJtW/PCOwK399wLC/mK6X.eX96QK7mK', '11@qq.com', '13888888888', '1', null, '2022-01-18 13:50:43');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('4', '4', '2');
INSERT INTO `sys_user_role` VALUES ('5', '5', '1');
INSERT INTO `sys_user_role` VALUES ('6', '7', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `member` tinyint(4) DEFAULT NULL COMMENT '是否会员  0：不是   1：是',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '11', '$2a$10$EpyT.60lG105/cG3YPqRgus/XlB5lCdiSsTbvkbAh.ED/DGTUCbdq', '1', '2022-01-17 01:47:27', '1');
INSERT INTO `user` VALUES ('2', '22', '$2a$10$EpyT.60lG105/cG3YPqRgus/XlB5lCdiSsTbvkbAh.ED/DGTUCbdq', '1', '2022-01-18 16:23:30', '0');

-- ----------------------------
-- Table structure for user_prod
-- ----------------------------
DROP TABLE IF EXISTS `user_prod`;
CREATE TABLE `user_prod` (
  `user_prod_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `prod_id` bigint(20) NOT NULL COMMENT '产品id',
  `image` varchar(255) DEFAULT NULL COMMENT '镜像',
  `container_name` varchar(255) DEFAULT NULL COMMENT '容器名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `expire_time` datetime DEFAULT NULL COMMENT '到期时间',
  PRIMARY KEY (`user_prod_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_prod
-- ----------------------------
INSERT INTO `user_prod` VALUES ('24', '1', '2', 'centos7', 'centos-deployment-test-cf5bd88d4-4gprn ', '2021-12-22 00:18:21', '2021-12-29 00:18:21');
INSERT INTO `user_prod` VALUES ('25', '1', '1', 'centos7', 'centos-deployment-cf5bd88d4-7pwxd', '2021-12-22 00:18:21', '2021-12-22 00:18:21');
INSERT INTO `user_prod` VALUES ('26', '2', '1', 'centos7', 'centos-deployment-cf5bd88d4-kclqs', '2021-12-22 00:18:21', '2021-12-22 00:18:21');
INSERT INTO `user_prod` VALUES ('27', '1', '1', 'centos7', 'centos-deployment-cf5bd88d4-q2nvw ', '2022-01-18 23:27:49', '2033-01-18 23:27:55');
INSERT INTO `user_prod` VALUES ('28', '1', '2', 'centos8', 'centos-deployment-cf5bd88d4-q7dbs ', '2022-01-18 23:31:36', '2025-01-18 23:31:36');

-- ----------------------------
-- Table structure for voucher
-- ----------------------------
DROP TABLE IF EXISTS `voucher`;
CREATE TABLE `voucher` (
  `voucher_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '兑换券ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '使用用户id',
  `voucher_code` varchar(2000) DEFAULT '' COMMENT '兑换码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态  0：未使用   1：已使用',
  PRIMARY KEY (`voucher_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=450 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='兑换券表';

-- ----------------------------
-- Records of voucher
-- ----------------------------
INSERT INTO `voucher` VALUES ('430', '19', 'N3ZJPB999', '2022-01-16 22:22:44', '2023-01-16 22:22:45', '0');
INSERT INTO `voucher` VALUES ('431', '19', 'LJDQXNJCA', '2022-01-16 22:22:48', '2023-01-16 22:22:49', '0');
INSERT INTO `voucher` VALUES ('432', null, 'JGDZLRDS6', '2022-01-16 22:22:52', '2023-01-16 22:22:53', '0');
INSERT INTO `voucher` VALUES ('433', null, 'DRHL9WKLL', '2022-01-16 22:22:56', '2023-01-16 22:22:56', '0');
INSERT INTO `voucher` VALUES ('434', null, 'H6ZDDNFPM', '2022-01-16 22:23:05', '2023-01-16 22:23:05', '0');
INSERT INTO `voucher` VALUES ('435', null, 'AD7ENL8HT', '2022-01-16 22:23:17', '2023-01-16 22:23:17', '0');
INSERT INTO `voucher` VALUES ('436', null, 'NY2Y7UTJL', '2022-01-16 22:23:17', '2023-01-16 22:23:17', '0');
INSERT INTO `voucher` VALUES ('437', null, 'RGK23KQHT', '2022-01-16 22:23:17', '2023-01-16 22:23:17', '0');
INSERT INTO `voucher` VALUES ('438', null, 'FVEZAF4CS', '2022-01-16 22:23:17', '2023-01-16 22:23:17', '0');
INSERT INTO `voucher` VALUES ('439', null, 'C95FKBGTW', '2022-01-16 22:23:17', '2023-01-16 22:23:17', '0');
INSERT INTO `voucher` VALUES ('440', null, 'FJAXQEB6R', '2022-01-16 22:23:29', '2023-01-16 22:23:29', '0');
INSERT INTO `voucher` VALUES ('441', null, 'FZCUJZAEB', '2022-01-16 22:23:29', '2023-01-16 22:23:29', '0');
INSERT INTO `voucher` VALUES ('442', null, 'KYZKAC6DA', '2022-01-16 22:23:29', '2023-01-16 22:23:29', '0');
INSERT INTO `voucher` VALUES ('443', null, 'NJ3QFKBJL', '2022-01-16 22:23:29', '2023-01-16 22:23:29', '0');
INSERT INTO `voucher` VALUES ('444', null, 'E9VP7Q6DA', '2022-01-16 22:23:29', '2023-01-16 22:23:29', '0');
INSERT INTO `voucher` VALUES ('445', null, 'CZ3ELE69H', '2022-01-16 22:24:03', '2023-01-16 22:24:03', '0');
INSERT INTO `voucher` VALUES ('446', null, 'QDEC89Z79', '2022-01-18 17:15:59', '2023-01-18 17:15:59', '0');
INSERT INTO `voucher` VALUES ('447', null, 'G3EA45K8R', '2022-01-18 17:17:08', '2023-01-18 17:17:08', '0');
INSERT INTO `voucher` VALUES ('448', null, 'L965NFRZ7', '2022-01-18 17:17:53', '2022-02-18 17:17:53', '0');
INSERT INTO `voucher` VALUES ('449', null, 'C6BNS32LC', '2022-01-18 17:18:45', '2022-02-18 17:18:45', '0');
