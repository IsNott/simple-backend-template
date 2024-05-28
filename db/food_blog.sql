/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : food_blog

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 27/02/2023 20:40:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `post_id` bigint(20) NULL DEFAULT NULL COMMENT '文章id',
  `commenter_id` bigint(20) NULL DEFAULT NULL COMMENT '评论人id',
  `comment_time` datetime(0) NULL DEFAULT NULL COMMENT '评论时间',
  `context` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评论内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_comment
-- ----------------------------

-- ----------------------------
-- Table structure for blog_post
-- ----------------------------
DROP TABLE IF EXISTS `blog_post`;
CREATE TABLE `blog_post`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `context` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '正文',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '发送人id',
  `send_time` datetime(0) NULL DEFAULT NULL COMMENT '发送时间',
  `likes` bigint(255) NULL DEFAULT NULL COMMENT '点赞数',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `type_id` bigint(20) NULL DEFAULT NULL COMMENT '分类id',
  `cover_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '封面图片路径',
  `recommend_flag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否推荐',
  `post_view` int(255) NULL DEFAULT NULL COMMENT '浏览量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_post
-- ----------------------------

-- ----------------------------
-- Table structure for blog_type
-- ----------------------------
DROP TABLE IF EXISTS `blog_type`;
CREATE TABLE `blog_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分类名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_type
-- ----------------------------
INSERT INTO `blog_type` VALUES (1, '家常美食');
INSERT INTO `blog_type` VALUES (2, '健康轻食');
INSERT INTO `blog_type` VALUES (3, '外国美食');

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
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'root', '123', '12345', '1');
INSERT INTO `user` VALUES (2, 'test', '1231', '123456', '0');
INSERT INTO `user` VALUES (4, 'testAdmin', '12312', '123456', '1');

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
INSERT INTO `user_type` VALUES (2, '0', '游客');
INSERT INTO `user_type` VALUES (3, '2', '博主');

SET FOREIGN_KEY_CHECKS = 1;
