/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : pet

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 19/02/2023 21:00:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, -1, '首页');
INSERT INTO `menu` VALUES (2, -1, '领养');
INSERT INTO `menu` VALUES (3, -1, '售后');
INSERT INTO `menu` VALUES (4, -1, '留言墙');
INSERT INTO `menu` VALUES (5, -1, '内容管理');
INSERT INTO `menu` VALUES (6, -1, '菜单管理');
INSERT INTO `menu` VALUES (7, -1, '留言查看');
INSERT INTO `menu` VALUES (8, -1, '账号管理');
INSERT INTO `menu` VALUES (9, 5, '发布领养信息');

-- ----------------------------
-- Table structure for menu_view
-- ----------------------------
DROP TABLE IF EXISTS `menu_view`;
CREATE TABLE `menu_view`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `menu_id` bigint(20) NULL DEFAULT NULL,
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
  `sender_id` bigint(20) NULL DEFAULT NULL,
  `send_time` datetime(0) NULL DEFAULT NULL,
  `resp_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, '测试发送', '测试回复', 1, '2023-02-18 15:25:29', '2023-02-18 15:28:47');
INSERT INTO `message` VALUES (2, '测试发送', NULL, 1, '2023-02-18 15:25:58', NULL);
INSERT INTO `message` VALUES (5, '测试发送', NULL, 1, '2023-02-18 15:27:10', NULL);
INSERT INTO `message` VALUES (6, '测试发送', NULL, 1, '2023-02-18 15:42:08', NULL);

-- ----------------------------
-- Table structure for pet_catalog
-- ----------------------------
DROP TABLE IF EXISTS `pet_catalog`;
CREATE TABLE `pet_catalog`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `catalog_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pet_catalog
-- ----------------------------
INSERT INTO `pet_catalog` VALUES (1, '猫猫');
INSERT INTO `pet_catalog` VALUES (2, '狗狗');
INSERT INTO `pet_catalog` VALUES (3, '鸟鸟');
INSERT INTO `pet_catalog` VALUES (4, '鱼鱼');
INSERT INTO `pet_catalog` VALUES (5, '其他');

-- ----------------------------
-- Table structure for pet_info
-- ----------------------------
DROP TABLE IF EXISTS `pet_info`;
CREATE TABLE `pet_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `catalog_id` bigint(20) NULL DEFAULT NULL,
  `pet_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `release_time` datetime(0) NULL DEFAULT NULL,
  `adopt_flag` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '0-未领养 1-领养',
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pet_gender` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '0-女生 1-男生',
  `adopter_id` bigint(20) NULL DEFAULT NULL,
  `adopter_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `adopter_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `adopter_area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `adopt_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pet_info
-- ----------------------------
INSERT INTO `pet_info` VALUES (1, 1, '花花', '2023-02-19 11:28:13', '0', '广州', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pet_info` VALUES (2, 2, '大黄', '2023-02-19 11:28:53', '0', '东莞', '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pet_info` VALUES (3, 3, 'cc', '2023-02-19 11:30:16', '1', '广州', '0', 2, '123456789', '王大龙', '清远', NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_type
-- ----------------------------
INSERT INTO `user_type` VALUES (1, '1', '管理员');
INSERT INTO `user_type` VALUES (2, '0', '普通用户');

SET FOREIGN_KEY_CHECKS = 1;
