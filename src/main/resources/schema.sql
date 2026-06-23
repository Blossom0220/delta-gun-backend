-- ================================================
-- 三角洲行动改枪小程序 - 表结构定义（仅建表，不含数据）
-- 完整初始化请使用 sql/init.sql
-- ================================================

CREATE DATABASE IF NOT EXISTS delta_gs DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE delta_gs;

-- 用户表
CREATE TABLE IF NOT EXISTS `app_user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `openid` VARCHAR(64) DEFAULT NULL COMMENT '微信openid',
    `unionid` VARCHAR(64) DEFAULT NULL COMMENT '微信unionid',
    `nickname` VARCHAR(64) DEFAULT NULL COMMENT '用户昵称',
    `avatar_url` VARCHAR(256) DEFAULT NULL COMMENT '头像URL',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `last_login_at` DATETIME DEFAULT NULL COMMENT '最后登录时间',
    PRIMARY KEY (`id`),
    KEY `idx_openid` (`openid`),
    KEY `idx_unionid` (`unionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 武器表（属性来自三角洲行动Wiki）
CREATE TABLE IF NOT EXISTS `weapon` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(64) NOT NULL COMMENT '武器名称',
    `game_id` VARCHAR(50) DEFAULT NULL COMMENT '游戏内ID',
    `category` VARCHAR(20) NOT NULL COMMENT '武器类型',
    `caliber` VARCHAR(20) NOT NULL COMMENT '口径',
    `image_url` VARCHAR(255) DEFAULT NULL COMMENT '武器图片',
    `description` TEXT COMMENT '武器描述',
    `damage` INT NOT NULL DEFAULT 0 COMMENT '伤害',
    `range_stat` INT NOT NULL DEFAULT 0 COMMENT '射程',
    `recoil_control` INT NOT NULL DEFAULT 0 COMMENT '后坐力控制',
    `handling` INT NOT NULL DEFAULT 0 COMMENT '操控速度',
    `fire_rate` INT NOT NULL DEFAULT 0 COMMENT '射速',
    `magazine_capacity` INT NOT NULL DEFAULT 0 COMMENT '弹容量',
    `muzzle_velocity` INT NOT NULL DEFAULT 0 COMMENT '子弹初速',
    `sort_order` INT DEFAULT 0 COMMENT '排序',
    `is_active` TINYINT DEFAULT 1 COMMENT '是否启用',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_game_id` (`game_id`),
    KEY `idx_category` (`category`),
    KEY `idx_caliber` (`caliber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='武器表';

-- 弹药表
CREATE TABLE IF NOT EXISTS `ammo` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL COMMENT '弹药名称',
    `caliber` VARCHAR(20) NOT NULL COMMENT '口径',
    `game_id` VARCHAR(50) DEFAULT NULL COMMENT '游戏内ID',
    `description` TEXT COMMENT '弹药描述',
    `stats_effect` JSON DEFAULT NULL COMMENT '弹药效果JSON',
    `sort_order` INT DEFAULT 0 COMMENT '排序',
    `is_active` TINYINT DEFAULT 1 COMMENT '是否启用',
    PRIMARY KEY (`id`),
    KEY `idx_caliber` (`caliber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='弹药表';

-- 武器-弹药关联表
CREATE TABLE IF NOT EXISTS `weapon_ammo` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `weapon_id` BIGINT NOT NULL COMMENT '武器ID',
    `ammo_id` BIGINT NOT NULL COMMENT '弹药ID',
    PRIMARY KEY (`id`),
    KEY `idx_weapon_id` (`weapon_id`),
    KEY `idx_ammo_id` (`ammo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='武器弹药关联表';

-- 武器槽位表
CREATE TABLE IF NOT EXISTS `weapon_slot` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `weapon_id` BIGINT NOT NULL COMMENT '武器ID',
    `game_slot_id` VARCHAR(50) DEFAULT NULL COMMENT '游戏内槽位ID',
    `slot_type` VARCHAR(30) NOT NULL COMMENT '槽位类型：枪口/枪管/前握把/后握把/枪托/瞄具/弹匣/镭射/护木片',
    `slot_name` VARCHAR(50) NOT NULL COMMENT '槽位名称',
    `sort_order` INT DEFAULT 0 COMMENT '排序',
    PRIMARY KEY (`id`),
    KEY `idx_weapon_id` (`weapon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='武器槽位表';

-- 配件表
CREATE TABLE IF NOT EXISTS `attachment` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL COMMENT '配件名称',
    `game_id` VARCHAR(50) DEFAULT NULL COMMENT '游戏内ID',
    `slot_type` VARCHAR(30) NOT NULL COMMENT '适用槽位类型',
    `rarity` VARCHAR(20) DEFAULT NULL COMMENT '稀有度',
    `image_url` VARCHAR(255) DEFAULT NULL COMMENT '配件图片',
    `stats_effect` JSON DEFAULT NULL COMMENT '属性加成JSON，键名与weapon属性一致',
    `is_active` TINYINT DEFAULT 1 COMMENT '是否启用',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_game_id` (`game_id`),
    KEY `idx_slot_type` (`slot_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='配件表';

-- 用户改枪方案表
CREATE TABLE IF NOT EXISTS `user_scheme` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `weapon_id` BIGINT NOT NULL COMMENT '武器ID',
    `scheme_name` VARCHAR(64) DEFAULT NULL COMMENT '方案名称',
    `config_json` JSON NOT NULL COMMENT '改枪配置JSON',
    `gunsmith_code` VARCHAR(512) DEFAULT NULL COMMENT '改枪码',
    `is_public` TINYINT DEFAULT 0 COMMENT '是否公开',
    `like_count` INT DEFAULT 0 COMMENT '点赞数',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_weapon_id` (`weapon_id`),
    KEY `idx_gunsmith_code` (`gunsmith_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户改枪方案表';
