/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : bill_system

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 19/02/2023 20:59:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `bill_sum` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `bill_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `bill_time` datetime(0) NULL DEFAULT NULL,
  `audit_time` datetime(0) NULL DEFAULT NULL,
  `person_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `bill_status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `cancel_flag` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `audit_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bill
-- ----------------------------
INSERT INTO `bill` VALUES (2, 2, '199.2', '出差', '2023-02-18 16:59:05', '2023-02-18 17:07:54', '张三', '123456789', '1', '0', '通过');
INSERT INTO `bill` VALUES (3, 2, '199.2', '出差', '2023-02-18 16:59:32', NULL, '张三', '123456789', '0', '0', NULL);
INSERT INTO `bill` VALUES (4, 2, '199.2', '出差', '2023-02-18 16:59:33', NULL, '张三', '123456789', '0', '0', NULL);
INSERT INTO `bill` VALUES (5, 2, '199.2', '出差', '2023-02-18 16:59:34', NULL, '张三', '123456789', '0', '0', NULL);
INSERT INTO `bill` VALUES (6, 2, '199.2', '出差', '2023-02-18 16:59:34', NULL, '张三', '123456789', '0', '0', NULL);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `menu_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
  `sender_id` bigint(20) NULL DEFAULT NULL,
  `send_time` datetime(0) NULL DEFAULT NULL,
  `resp_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, '测试发送', '测试回复', 1, '2023-02-18 15:25:29', '2023-02-18 15:28:47');
INSERT INTO `message` VALUES (2, '测试发送', NULL, 1, '2023-02-18 15:25:58', NULL);
INSERT INTO `message` VALUES (5, '测试发送', NULL, 1, '2023-02-18 15:27:10', NULL);
INSERT INTO `message` VALUES (6, '测试发送', NULL, 1, '2023-02-18 15:42:08', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'root', '12345', '1');
INSERT INTO `user` VALUES (2, 'test', '123456', '0');
INSERT INTO `user` VALUES (4, 'testAdmin', '123456', '1');

-- ----------------------------
-- Table structure for user_type
-- ----------------------------
DROP TABLE IF EXISTS `user_type`;
CREATE TABLE `user_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_type
-- ----------------------------
INSERT INTO `user_type` VALUES (1, '1', '管理员');
INSERT INTO `user_type` VALUES (2, '0', '普通用户');

SET FOREIGN_KEY_CHECKS = 1;
