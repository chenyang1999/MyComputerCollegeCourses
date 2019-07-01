/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : moment

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-05-13 01:49:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `theme` varchar(255) DEFAULT NULL,
  `organizer` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `atime` datetime DEFAULT NULL,
  `cost` int(11) NOT NULL,
  `number` int(11) DEFAULT NULL,
  `place` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `picpath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES ('1', '森林和服少女', '月月', '和服少女拍摄，是用摄影机﹑录像机把人﹑物的形象摄下来。不同的场景有不同的拍摄技巧，有夜景拍摄、雨景拍摄、建筑物拍摄、人像拍摄等。随着科技的进步，拍摄也变得越来越简单，越来越符合大众化。', '外拍', '2017-05-11 00:00:00', '45', '1', '广州市', '华农', 'http://ooys7gpai.bkt.clouddn.com/ccba4ff8-900f-4413-a9ca-dc427f0895ed');
INSERT INTO `activity` VALUES ('2', '校园', '月月', '拍摄，是用摄影机﹑录像机把人﹑物的形象摄下来。不同的场景有不同的拍摄技巧，有夜景拍摄、雨景拍摄、建筑物拍摄、人像拍摄等。随着科技的进步，拍摄也变得越来越简单，越来越符合大众化。', '外拍', '2017-05-19 00:00:00', '65', '0', '广州市', '广工', 'http://ooys7gpai.bkt.clouddn.com/ccba4ff8-900f-4413-a9ca-dc427f0895ed');
INSERT INTO `activity` VALUES ('3', '园艺', '花卉', '拍摄，是用摄影机﹑录像机把人﹑物的形象摄下来。不同的场景有不同的拍摄技巧，有夜景拍摄、雨景拍摄、建筑物拍摄、人像拍摄等。随着科技的进步，拍摄也变得越来越简单，越来越符合大众化。', '外拍', '2017-05-17 23:24:45', '34', '4', '广州市', '花都', 'http://ooys7gpai.bkt.clouddn.com/ccba4ff8-900f-4413-a9ca-dc427f0895ed');
INSERT INTO `activity` VALUES ('4', '花镜', '花镜', '拍摄，是用摄影机﹑录像机把人﹑物的形象摄下来。不同的场景有不同的拍摄技巧，有夜景拍摄、雨景拍摄、建筑物拍摄、人像拍摄等。随着科技的进步，拍摄也变得越来越简单，越来越符合大众化。', '外拍', '2017-05-17 23:25:39', '45', '1', '广州', '花城', 'http://ooys7gpai.bkt.clouddn.com/ccba4ff8-900f-4413-a9ca-dc427f0895ed');

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `phonum` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '123', '123', null, '123', '2017-05-04 01:04:25');

-- ----------------------------
-- Table structure for adminrole
-- ----------------------------
DROP TABLE IF EXISTS `adminrole`;
CREATE TABLE `adminrole` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `adminid` int(255) DEFAULT NULL,
  `roleid` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_group_id` (`adminid`),
  KEY `group_user_id` (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adminrole
-- ----------------------------

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '收藏库的名称',
  `description` varchar(255) DEFAULT NULL COMMENT '收藏库的描述',
  `userid` int(255) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collection
-- ----------------------------

-- ----------------------------
-- Table structure for collectpic
-- ----------------------------
DROP TABLE IF EXISTS `collectpic`;
CREATE TABLE `collectpic` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `picid` int(255) NOT NULL,
  `collectionid` int(255) NOT NULL,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collectpic
-- ----------------------------

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL COMMENT '评论人的名字',
  `content` varchar(255) DEFAULT NULL,
  `picid` int(11) NOT NULL,
  `commentid` int(11) DEFAULT NULL COMMENT 'commentid是指回复的评论的id',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for dealreport
-- ----------------------------
DROP TABLE IF EXISTS `dealreport`;
CREATE TABLE `dealreport` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `dealDetail` varchar(255) DEFAULT NULL COMMENT '处理的具体细节',
  `adminid` int(255) NOT NULL,
  `reportid` int(255) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `deal_admin_id` (`adminid`),
  KEY `deal_report_id` (`reportid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dealreport
-- ----------------------------
INSERT INTO `dealreport` VALUES ('1', '删除图片', '1', '2', '2017-05-04 15:16:47');
INSERT INTO `dealreport` VALUES ('2', '删除图片', '1', '2', '2017-05-04 15:18:15');

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `grade` varchar(255) NOT NULL COMMENT '角色名称',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `picnum` int(11) DEFAULT NULL,
  `uploadnum` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES ('1', 'LV0', '每天可以发图2张', '0', '2');
INSERT INTO `grade` VALUES ('2', 'LV1', '上传图片累计30张，每天可发图4张', '30', '4');
INSERT INTO `grade` VALUES ('3', 'LV3', '上传图片累计60张，每天可发图8张', '60', '8');
INSERT INTO `grade` VALUES ('4', 'LV4', '上传图片累计100张，每天可发图12张', '100', '12');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `name` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `description` varchar(255) DEFAULT NULL COMMENT '权限描述',
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '删除照片权限', '可以删除照片');
INSERT INTO `permission` VALUES ('2', '处理举报权限', '对举报信息进行处理，并经处理结果返回给用户');
INSERT INTO `permission` VALUES ('3', '封禁外拍活动', '对用户举报的外拍活动进行处理');
INSERT INTO `permission` VALUES ('4', '查看用户权限', '可以查看用户信息');
INSERT INTO `permission` VALUES ('5', '查看照片权限', '可以查看所有用户上传的照片的权限');
INSERT INTO `permission` VALUES ('6', '查看活动权限', '可以查看用户的发布的活动');
INSERT INTO `permission` VALUES ('7', '查看管理员权限', '可以查看所有的管理员，并对管理员设定权限');
INSERT INTO `permission` VALUES ('8', '权限管理', '可以设置权限内容');

-- ----------------------------
-- Table structure for pic
-- ----------------------------
DROP TABLE IF EXISTS `pic`;
CREATE TABLE `pic` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '图片id',
  `name` varchar(255) NOT NULL COMMENT '用户所起的图片名称',
  `description` varchar(255) DEFAULT NULL COMMENT '图片描述',
  `ispublic` varchar(255) DEFAULT NULL COMMENT '是否公开',
  `picpath` varchar(255) NOT NULL COMMENT '图片路径',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加图片时间',
  `userid` int(255) NOT NULL,
  `type` varchar(255) DEFAULT '其他' COMMENT '图片类别，类别是我们设定好的',
  `piclike` int(11) DEFAULT '0' COMMENT '点赞数',
  `collect` int(11) DEFAULT '0' COMMENT '收藏数',
  `comment` int(255) DEFAULT '0' COMMENT '评论数',
  PRIMARY KEY (`id`),
  KEY `pic_user_id` (`userid`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pic
-- ----------------------------
INSERT INTO `pic` VALUES ('1', 'test', 'nihao', null, 'http://ooys7gpai.bkt.clouddn.com/bd79c11a-473e-4e9d-9853-842bc6b2cf76', '2017-05-04 18:27:17', '5', '黑白', '0', '0', '0');
INSERT INTO `pic` VALUES ('2', 'test', 'nishao', null, 'http://ooys7gpai.bkt.clouddn.com/aea70e7b-d22e-42c7-9755-1d9ebd3f1b2a', '2017-05-04 19:35:00', '5', '其他', '0', '0', '0');
INSERT INTO `pic` VALUES ('3', 'test', 'nishao', null, 'http://ooys7gpai.bkt.clouddn.com/d48bf765-a714-48fa-8901-3031f8105333', '2017-05-04 19:35:14', '5', '其他', '0', '0', '0');
INSERT INTO `pic` VALUES ('4', 'test', 'nishao', null, 'http://ooys7gpai.bkt.clouddn.com/201c306d-f55d-4144-b62c-cfc917c131bc', '2017-05-04 19:35:51', '5', null, '0', '0', '0');
INSERT INTO `pic` VALUES ('5', 'test', 'nihao', null, 'http://ooys7gpai.bkt.clouddn.com/68774239-d48e-45e6-b23c-523aa9d2a70e', '2017-05-04 19:40:39', '5', null, '0', '0', '0');
INSERT INTO `pic` VALUES ('6', 'test', '', null, 'http://ooys7gpai.bkt.clouddn.com/d124477d-2277-4f60-a1e1-f7bf42e5cc22', '2017-05-05 16:17:18', '5', null, '0', '0', '0');
INSERT INTO `pic` VALUES ('7', '冯绍杰', '猜猜我是谁', null, 'http://ooys7gpai.bkt.clouddn.com/ccba4ff8-900f-4413-a9ca-dc427f0895ed', '2017-05-05 21:07:23', '5', '黑白', '0', '0', '0');
INSERT INTO `pic` VALUES ('8', '冰岛银河', '', null, 'http://ooys7gpai.bkt.clouddn.com/03aa621a-8031-461b-b679-55f733791776', '2017-05-06 10:10:49', '4', '静物', '0', '0', '0');
INSERT INTO `pic` VALUES ('11', 'test', '', null, 'http://ooys7gpai.bkt.clouddn.com/de778561-51e4-4e90-a88e-4b1dc99cde56', '2017-05-06 10:27:54', '5', '风光', '0', '0', '0');
INSERT INTO `pic` VALUES ('12', '人物摄影', '人物摄影', null, 'http://ooys7gpai.bkt.clouddn.com/424d6643-f5a8-47f2-9eb0-e5efa6fca340', '2017-05-06 11:37:20', '4', '其他', '0', '0', '0');
INSERT INTO `pic` VALUES ('13', '耶稣之光', '', null, 'http://ooys7gpai.bkt.clouddn.com/2419f461-6511-4450-8967-fdbb7040bd76', '2017-05-06 12:40:32', '6', '其他', '0', '0', '0');

-- ----------------------------
-- Table structure for piccalendar
-- ----------------------------
DROP TABLE IF EXISTS `piccalendar`;
CREATE TABLE `piccalendar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `picpath` varchar(255) DEFAULT NULL COMMENT '图片路径',
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `userid` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of piccalendar
-- ----------------------------
INSERT INTO `piccalendar` VALUES ('1', 'http://ooys7gpai.bkt.clouddn.com/b8f0bf6b-4326-41c8-91ea-03b6c3e09707', '不醉不会', '', '2017-05-05 16:40:51', '4');
INSERT INTO `piccalendar` VALUES ('2', 'http://ooys7gpai.bkt.clouddn.com/b8f0bf6b-4326-41c8-91ea-03b6c3e09707', '不醉不会', null, '2017-05-06 11:29:31', '4');

-- ----------------------------
-- Table structure for piclike
-- ----------------------------
DROP TABLE IF EXISTS `piclike`;
CREATE TABLE `piclike` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '点赞id',
  `userid` int(255) NOT NULL,
  `picid` int(255) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `like_user_id` (`userid`),
  KEY `llike_pic_id` (`picid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of piclike
-- ----------------------------

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '举办记录的id',
  `userid` int(255) NOT NULL COMMENT '举报记录图片作者的id',
  `picid` int(255) DEFAULT NULL COMMENT '举报记录图片的id',
  `description` varchar(255) NOT NULL COMMENT '举报描述',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `report_user_id` (`userid`),
  KEY `report_pic_id` (`picid`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of report
-- ----------------------------
INSERT INTO `report` VALUES ('1', '2', '3', 'jubao', '2017-05-03 00:41:40');
INSERT INTO `report` VALUES ('2', '4', '3', 'jubao,jubao', '2017-05-04 00:03:43');
INSERT INTO `report` VALUES ('3', '4', '3', 'jubao1,jubao1', '2017-05-04 00:04:41');
INSERT INTO `report` VALUES ('4', '4', '3', '1,1', '2017-05-04 00:05:48');
INSERT INTO `report` VALUES ('5', '4', '3', 'jubao,jubao', '2017-05-04 00:06:32');
INSERT INTO `report` VALUES ('6', '4', '3', 'jubao,jubao', '2017-05-04 00:06:32');
INSERT INTO `report` VALUES ('7', '4', '3', '1', '2017-05-04 00:07:26');
INSERT INTO `report` VALUES ('8', '4', '3', 'jubao', '2017-05-04 00:07:53');
INSERT INTO `report` VALUES ('9', '4', '3', 'jubao', '2017-05-04 00:07:53');
INSERT INTO `report` VALUES ('10', '4', '3', 'jubao', '2017-05-04 00:08:33');
INSERT INTO `report` VALUES ('11', '4', '3', 'jubao', '2017-05-04 00:08:33');
INSERT INTO `report` VALUES ('12', '4', '3', 'jubaoshibai', '2017-05-04 00:08:59');
INSERT INTO `report` VALUES ('13', '4', '3', 'jubaoshibai', '2017-05-04 00:08:59');
INSERT INTO `report` VALUES ('14', '4', '3', '1', '2017-05-04 00:09:45');
INSERT INTO `report` VALUES ('15', '4', '3', '1', '2017-05-04 00:09:45');
INSERT INTO `report` VALUES ('16', '4', '3', 'k', '2017-05-04 00:12:31');
INSERT INTO `report` VALUES ('17', '4', '3', 'k', '2017-05-04 00:12:32');
INSERT INTO `report` VALUES ('18', '4', '3', 'e', '2017-05-04 00:14:39');
INSERT INTO `report` VALUES ('19', '4', '3', 'e', '2017-05-04 00:14:39');
INSERT INTO `report` VALUES ('20', '4', '3', '1', '2017-05-04 00:17:09');
INSERT INTO `report` VALUES ('21', '4', '3', 'w', '2017-05-04 00:18:30');
INSERT INTO `report` VALUES ('22', '4', '3', 'w', '2017-05-04 00:18:30');
INSERT INTO `report` VALUES ('23', '4', '3', '1', '2017-05-04 00:22:38');
INSERT INTO `report` VALUES ('24', '4', '3', 'jubao', '2017-05-04 00:23:03');
INSERT INTO `report` VALUES ('25', '4', '3', '侵权', '2017-05-04 00:23:28');
INSERT INTO `report` VALUES ('26', '4', '3', '1', '2017-05-04 00:25:21');
INSERT INTO `report` VALUES ('27', '4', '3', '1', '2017-05-04 00:26:08');
INSERT INTO `report` VALUES ('28', '4', '3', '举报', '2017-05-04 00:26:42');
INSERT INTO `report` VALUES ('29', '4', '3', '举报', '2017-05-04 00:27:20');
INSERT INTO `report` VALUES ('30', '4', '3', 'q', '2017-05-04 00:28:08');
INSERT INTO `report` VALUES ('31', '4', '3', '侵权', '2017-05-04 00:32:16');
INSERT INTO `report` VALUES ('32', '4', '3', '侵权', '2017-05-06 09:47:05');
INSERT INTO `report` VALUES ('33', '4', '3', '侵权', '2017-05-06 10:12:17');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员', '具有一切权限，可以对权限内容进行设置修改');
INSERT INTO `role` VALUES ('2', '用户管理员', '可以对用户信息查看，并对举报的用户进行短期封号等处理');
INSERT INTO `role` VALUES ('3', '图片管理员', '可对图片查看，搜索');
INSERT INTO `role` VALUES ('4', '活动情况管理员', '可查看所有的活动情况，并针对其中某一些举报情况进行审查处理');
INSERT INTO `role` VALUES ('5', '后台管理员', '主要对后台管理员的内容信息设置，比如权限');

-- ----------------------------
-- Table structure for rolepermission
-- ----------------------------
DROP TABLE IF EXISTS `rolepermission`;
CREATE TABLE `rolepermission` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '权限角色关联表（多对多的关系）',
  `roleid` int(255) NOT NULL COMMENT '角色id',
  `permissionid` int(255) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`),
  KEY `role_permission_id` (`roleid`),
  KEY `permission_role_id` (`permissionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rolepermission
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `name` varchar(255) DEFAULT NULL,
  `account` varchar(255) NOT NULL,
  `phonum` varchar(255) DEFAULT NULL COMMENT '手机号码或邮箱至少要有一个',
  `email` varchar(255) DEFAULT NULL COMMENT '可以用邮箱或手机号码注册',
  `salt` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `score` int(11) DEFAULT NULL,
  `gradeid` int(11) DEFAULT NULL COMMENT '积分',
  `picnum` int(11) NOT NULL DEFAULT '0' COMMENT '上传图片数量',
  `collectnum` int(11) NOT NULL DEFAULT '0' COMMENT '收藏库数量',
  `collectPicnum` int(11) NOT NULL DEFAULT '0' COMMENT '收藏图片数量',
  `fansnum` int(255) NOT NULL DEFAULT '0' COMMENT '粉丝数量',
  `concernnum` int(11) NOT NULL DEFAULT '0' COMMENT '关注数量',
  `likenum` int(11) NOT NULL DEFAULT '0',
  `img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', null, 'bb', '1234567890', '1234567890', null, '11', '11', '2017-04-26 16:59:27', null, null, '0', '0', '0', '0', '0', '0', 'http://ooys7gpai.bkt.clouddn.com/8e5ff227-0e2f-4dec-97a4-b623b9a12596');
INSERT INTO `user` VALUES ('3', null, 'cc', 'aa@qq.com', null, 'aa@qq.com', '11', '11', '2017-04-26 16:59:49', null, null, '0', '0', '0', '0', '0', '0', 'http://ooys7gpai.bkt.clouddn.com/8e5ff227-0e2f-4dec-97a4-b623b9a12596');
INSERT INTO `user` VALUES ('4', null, '不醉不会', '13724363808', '13724363808', null, '0e01016f-ed66-4c34-92d5-e3fff89d4c54', '4010fab9aa8e6186d1341145d72e8705', '2017-05-02 14:31:45', null, '1', '4', '0', '0', '0', '0', '0', 'http://ooys7gpai.bkt.clouddn.com/8e5ff227-0e2f-4dec-97a4-b623b9a12596');
INSERT INTO `user` VALUES ('5', null, '0e01016f', '15521077777', '15521077777', null, '5a5b4200-dfd4-4e82-be40-438dbb095a3f', 'cb14f1423a02c77a9a36d38b71f4e63e', '2017-05-06 10:18:43', null, '1', '0', '0', '0', '0', '0', '0', 'http://ooys7gpai.bkt.clouddn.com/8e5ff227-0e2f-4dec-97a4-b623b9a12596');
INSERT INTO `user` VALUES ('6', null, '0e01016f', '13530233446', '13530233446', null, '696834f1-a8bc-4a32-9de7-1966ff04fe09', '2d886d1ece945e59ad79269d353def0c', '2017-05-06 12:39:44', null, '1', '1', '0', '0', '0', '0', '0', 'http://ooys7gpai.bkt.clouddn.com/8e5ff227-0e2f-4dec-97a4-b623b9a12596');
