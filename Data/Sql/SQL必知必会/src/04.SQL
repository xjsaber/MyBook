# 04.SQL
CREATE DATABASE nba; 
DROP DATABASE nba;

# CREATE TABLE [](字段名 数据类型, ....)

DROP TABLE IF EXISTS `player`;
CREATE TABLE `player` (
	`player_id` int(11) NOT NULL AUTO_INCREMENT,
	`team_id` int(11) NOT NULL,
	`player_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	`height` float(3, 2) NULL DEFAULT 0.00,
	PRIMARY KEY(`player_id`) USING BTREE,
	UNIQUE INDEX `player_name`(`player_name`) USING BTREE
) ENGINE = INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;