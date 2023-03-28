/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : activity

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 28/03/2023 08:54:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for active
-- ----------------------------
DROP TABLE IF EXISTS `active`;
CREATE TABLE `active`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `act_creater` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `act_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动名称',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `audit_status` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核状态',
  `act_context` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动内容',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '简介',
  `act_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of active
-- ----------------------------

-- ----------------------------
-- Table structure for donate
-- ----------------------------
DROP TABLE IF EXISTS `donate`;
CREATE TABLE `donate`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `doneter_id` bigint(20) NULL DEFAULT NULL COMMENT '捐赠人',
  `donater_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '捐赠人署名',
  `donate_time` datetime(0) NULL DEFAULT NULL COMMENT '捐赠时间',
  `donate_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '捐赠类型',
  `donate_destination` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '捐赠地址',
  `donater_phone` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '捐赠人联系电话',
  `receive_destination` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '接收人地址',
  `receiver_phone` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '接收人电话',
  `receiver_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '接收人姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of donate
-- ----------------------------
INSERT INTO `donate` VALUES (2, 1, '小孩', '2023-03-28 08:46:59', '扶贫捐赠1', '广州市海珠区', '1234125', '广州市白云区', '123325', '物资');
INSERT INTO `donate` VALUES (3, 1, '小孩', '2023-03-28 08:47:02', '扶贫捐赠12', '广州市海珠区', '1234125', '广州市白云区', '123325', '物资');
INSERT INTO `donate` VALUES (4, 1, '小孩', '2023-03-28 08:47:04', '扶贫捐赠124', '广州市海珠区', '1234125', '广州市白云区', '123325', '物资');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '上级id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, -1, '报账');
INSERT INTO `menu` VALUES (2, -1, '管理');
INSERT INTO `menu` VALUES (3, -1, '注册登录');
INSERT INTO `menu` VALUES (4, -1, '我的');
INSERT INTO `menu` VALUES (5, -1, '内容管理');
INSERT INTO `menu` VALUES (6, -1, '菜单管理');
INSERT INTO `menu` VALUES (7, -1, '留言查看与回复');
INSERT INTO `menu` VALUES (8, 1, '小狗');
INSERT INTO `menu` VALUES (9, 1, '小猫');

-- ----------------------------
-- Table structure for menu_view
-- ----------------------------
DROP TABLE IF EXISTS `menu_view`;
CREATE TABLE `menu_view`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '可见用户类型',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu_view
-- ----------------------------
INSERT INTO `menu_view` VALUES (1, '0', 1);
INSERT INTO `menu_view` VALUES (2, '0', 3);
INSERT INTO `menu_view` VALUES (3, '0', 4);
INSERT INTO `menu_view` VALUES (4, '0', 8);
INSERT INTO `menu_view` VALUES (5, '0', 9);
INSERT INTO `menu_view` VALUES (6, '1', 1);
INSERT INTO `menu_view` VALUES (7, '1', 2);
INSERT INTO `menu_view` VALUES (8, '1', 3);
INSERT INTO `menu_view` VALUES (9, '1', 4);
INSERT INTO `menu_view` VALUES (10, '1', 5);
INSERT INTO `menu_view` VALUES (11, '1', 6);
INSERT INTO `menu_view` VALUES (12, '1', 7);
INSERT INTO `menu_view` VALUES (13, '1', 8);
INSERT INTO `menu_view` VALUES (14, '1', 9);
INSERT INTO `menu_view` VALUES (15, '1', NULL);
INSERT INTO `menu_view` VALUES (16, '1', NULL);
INSERT INTO `menu_view` VALUES (17, '0', NULL);
INSERT INTO `menu_view` VALUES (18, '1', NULL);
INSERT INTO `menu_view` VALUES (19, '0', NULL);
INSERT INTO `menu_view` VALUES (20, '1', NULL);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `context` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `resp_context` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `audit_status` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sender_id` bigint(20) NULL DEFAULT NULL,
  `send_time` datetime(0) NULL DEFAULT NULL,
  `resp_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, '测试发送', '测试回复', NULL, 1, '2023-02-18 15:25:29', '2023-02-18 15:28:47');
INSERT INTO `message` VALUES (2, '测试发送', NULL, NULL, 1, '2023-02-18 15:25:58', NULL);
INSERT INTO `message` VALUES (5, '测试发送', NULL, NULL, 1, '2023-02-18 15:27:10', NULL);
INSERT INTO `message` VALUES (6, '测试发送', NULL, NULL, 1, '2023-02-18 15:42:08', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `birth` date NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'root', '12345', '1', NULL, NULL, NULL);
INSERT INTO `user` VALUES (2, 'test', '123456', '0', NULL, NULL, NULL);
INSERT INTO `user` VALUES (4, 'testAdmin', '123456', '1', NULL, NULL, NULL);
INSERT INTO `user` VALUES (5, 'test1', '123', '0', '1', '2023-04-05', '123455678');
INSERT INTO `user` VALUES (6, 'admin1', '123', '1', '1', '2023-04-05', '123455678');

-- ----------------------------
-- Table structure for user_type
-- ----------------------------
DROP TABLE IF EXISTS `user_type`;
CREATE TABLE `user_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_type
-- ----------------------------
INSERT INTO `user_type` VALUES (1, '1', '管理员');
INSERT INTO `user_type` VALUES (2, '0', '普通用户');

SET FOREIGN_KEY_CHECKS = 1;
