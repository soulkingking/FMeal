/*
Navicat MySQL Data Transfer

Source Server         : CaoBin
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : dblog

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2016-06-28 17:22:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_accountpoint
-- ----------------------------
DROP TABLE IF EXISTS `tb_accountpoint`;
CREATE TABLE `tb_accountpoint` (
  `ap_id` varchar(22) NOT NULL,
  `ap_curpoint` int(11) NOT NULL,
  `ap_maxpoint` int(11) NOT NULL,
  `ap_maxWeekRank` int(11) NOT NULL,
  `ap_maxMonthRank` int(11) NOT NULL,
  `ap_maxRank` int(11) NOT NULL,
  `ap_curWeekDif` int(11) NOT NULL,
  `ap_curMonthDif` int(11) NOT NULL,
  `u_id` varchar(22) NOT NULL,
  PRIMARY KEY (`ap_id`),
  KEY `FK_AP_FK_Reference_User` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_deliveryaddress
-- ----------------------------
DROP TABLE IF EXISTS `tb_deliveryaddress`;
CREATE TABLE `tb_deliveryaddress` (
  `da_id` varchar(22) NOT NULL,
  `da_name` varchar(20) NOT NULL,
  `da_phone` varchar(11) NOT NULL,
  `da_address` varchar(50) NOT NULL,
  `u_id` varchar(22) NOT NULL,
  PRIMARY KEY (`da_id`),
  KEY `FK_DA_FK_Reference_User` (`u_id`),
  KEY `da_name` (`da_name`,`da_phone`,`da_address`),
  KEY `da_name_2` (`da_name`),
  CONSTRAINT `fk_uid` FOREIGN KEY (`u_id`) REFERENCES `tb_userinfo` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_dishtable
-- ----------------------------
DROP TABLE IF EXISTS `tb_dishtable`;
CREATE TABLE `tb_dishtable` (
  `dt_id` varchar(22) NOT NULL,
  `dt_count` int(11) NOT NULL,
  `dt_total` int(11) NOT NULL,
  `to_id` varchar(22) NOT NULL,
  `sd_id` varchar(22) NOT NULL,
  PRIMARY KEY (`dt_id`),
  KEY `FK_DT_FK_Reference_TO` (`to_id`),
  KEY `FK_DT_FK_Reference_Dish` (`sd_id`),
  CONSTRAINT `FK_DT_FK_Reference_Dish` FOREIGN KEY (`sd_id`) REFERENCES `tb_sellerdish` (`sd_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_DT_FK_Reference_TO` FOREIGN KEY (`to_id`) REFERENCES `tb_takeoutorder` (`to_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_feedback
-- ----------------------------
DROP TABLE IF EXISTS `tb_feedback`;
CREATE TABLE `tb_feedback` (
  `fb_id` varchar(22) NOT NULL,
  `fb_content` varchar(100) NOT NULL,
  `fb_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `u_id` varchar(22) NOT NULL,
  PRIMARY KEY (`fb_id`),
  KEY `FK_Reference_29` (`u_id`),
  CONSTRAINT `FK_Reference_29` FOREIGN KEY (`u_id`) REFERENCES `tb_userinfo` (`u_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_foodring
-- ----------------------------
DROP TABLE IF EXISTS `tb_foodring`;
CREATE TABLE `tb_foodring` (
  `fr_id` varchar(22) NOT NULL,
  `fr_icon` varchar(100) DEFAULT NULL,
  `fr_name` varchar(20) NOT NULL,
  `fr_createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fr_visible` tinyint(1) NOT NULL,
  `u_id` varchar(22) NOT NULL,
  PRIMARY KEY (`fr_id`),
  KEY `FK_FR_FK_Reference_User` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_foodringmembers
-- ----------------------------
DROP TABLE IF EXISTS `tb_foodringmembers`;
CREATE TABLE `tb_foodringmembers` (
  `frm_id` varchar(22) NOT NULL,
  `u_id` varchar(22) NOT NULL,
  `fr_id` varchar(22) NOT NULL,
  `fr_jointime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`frm_id`),
  KEY `FK_FRM_FK_Reference_User` (`u_id`),
  KEY `FK_FRM_FK_Reference_FR` (`fr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_remark
-- ----------------------------
DROP TABLE IF EXISTS `tb_remark`;
CREATE TABLE `tb_remark` (
  `re_id` varchar(22) NOT NULL,
  `re_userid` varchar(22) NOT NULL,
  `u_id` varchar(22) NOT NULL,
  `fr_id` varchar(22) NOT NULL,
  `re_name` varchar(20) NOT NULL,
  PRIMARY KEY (`re_id`),
  KEY `FK_RE_FK_Reference_RemarkUser` (`re_userid`),
  KEY `FK_Reference_33` (`fr_id`),
  KEY `FK_Reference_34` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_restaurant
-- ----------------------------
DROP TABLE IF EXISTS `tb_restaurant`;
CREATE TABLE `tb_restaurant` (
  `r_id` varchar(22) NOT NULL,
  `r_icon` varchar(100) DEFAULT NULL,
  `r_name` varchar(30) NOT NULL,
  `r_degree` int(11) NOT NULL,
  `r_address` varchar(50) NOT NULL,
  `r_starttime` time NOT NULL,
  `r_endtime` time NOT NULL,
  `r_advandays` int(11) NOT NULL,
  `r_intro` varchar(100) DEFAULT NULL,
  `r_contact` varchar(11) DEFAULT NULL,
  `r_status` tinyint(1) NOT NULL,
  `r_notice` varchar(50) DEFAULT NULL,
  `r_longitude` double DEFAULT NULL,
  `r_latitude` double DEFAULT NULL,
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_restcollection
-- ----------------------------
DROP TABLE IF EXISTS `tb_restcollection`;
CREATE TABLE `tb_restcollection` (
  `rcoll_id` varchar(22) NOT NULL,
  `u_id` varchar(22) DEFAULT NULL,
  `r_id` varchar(22) DEFAULT NULL,
  PRIMARY KEY (`rcoll_id`),
  KEY `FK_RColl_FK_Reference_User` (`u_id`),
  KEY `FK_RColl_FK_Reference_Rest` (`r_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_restcomment
-- ----------------------------
DROP TABLE IF EXISTS `tb_restcomment`;
CREATE TABLE `tb_restcomment` (
  `rc_id` varchar(22) NOT NULL,
  `rc_eat` int(11) NOT NULL,
  `rc_service` int(11) NOT NULL,
  `rc_env` int(11) NOT NULL,
  `rc_content` varchar(100) DEFAULT NULL,
  `r_id` varchar(22) NOT NULL,
  `u_id` varchar(22) NOT NULL,
  PRIMARY KEY (`rc_id`),
  KEY `FK_RC_FK_Reference_Rest` (`r_id`),
  KEY `FK_RC_FK_Reference_User` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_restdish
-- ----------------------------
DROP TABLE IF EXISTS `tb_restdish`;
CREATE TABLE `tb_restdish` (
  `rd_id` varchar(22) NOT NULL,
  `rd_name` varchar(50) NOT NULL,
  `rd_price` int(11) NOT NULL,
  `rmt_id` varchar(22) NOT NULL,
  `rd_icon` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`rd_id`),
  KEY `FK_RD_FK_Reference_RMT` (`rmt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_restmenutype
-- ----------------------------
DROP TABLE IF EXISTS `tb_restmenutype`;
CREATE TABLE `tb_restmenutype` (
  `rmt_id` varchar(22) NOT NULL,
  `r_id` varchar(22) NOT NULL,
  `rmt_name` varchar(20) NOT NULL,
  PRIMARY KEY (`rmt_id`),
  KEY `FK_RMT_FK_Reference_Rest` (`r_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_seatinfo
-- ----------------------------
DROP TABLE IF EXISTS `tb_seatinfo`;
CREATE TABLE `tb_seatinfo` (
  `si_id` varchar(20) NOT NULL,
  `u_id` varchar(20) NOT NULL,
  `si_name` varchar(20) NOT NULL,
  `si_phone` varchar(11) NOT NULL,
  PRIMARY KEY (`si_id`),
  KEY `FK_SI_FK_Reference_User` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_seatorder
-- ----------------------------
DROP TABLE IF EXISTS `tb_seatorder`;
CREATE TABLE `tb_seatorder` (
  `so_id` varchar(22) NOT NULL,
  `so_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `so_count` int(11) NOT NULL,
  `so_extra` varchar(50) DEFAULT NULL,
  `so_name` varchar(20) NOT NULL,
  `so_phone` varchar(11) NOT NULL,
  `r_id` varchar(22) NOT NULL,
  `u_id` varchar(22) NOT NULL,
  PRIMARY KEY (`so_id`),
  KEY `FK_News_FK_Reference_Rest` (`r_id`),
  KEY `FK_SO_FK_Reference_User` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='seat order';

-- ----------------------------
-- Table structure for tb_seatorderstatus
-- ----------------------------
DROP TABLE IF EXISTS `tb_seatorderstatus`;
CREATE TABLE `tb_seatorderstatus` (
  `sos_id` varchar(22) NOT NULL,
  `sos_status` varchar(50) NOT NULL,
  `sos_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `so_id` varchar(22) DEFAULT NULL,
  PRIMARY KEY (`sos_id`),
  KEY `FK_SOS_FK_Reference_SO` (`so_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_seller
-- ----------------------------
DROP TABLE IF EXISTS `tb_seller`;
CREATE TABLE `tb_seller` (
  `s_id` varchar(22) NOT NULL,
  `seller_name` varchar(30) NOT NULL,
  `seller_degree` int(11) NOT NULL,
  `seller_sendprice` int(11) NOT NULL,
  `seller_deliverytime` int(11) NOT NULL,
  `seller_contact` varchar(11) DEFAULT NULL,
  `seller_status` int(1) NOT NULL,
  `seller_notice` varchar(50) DEFAULT NULL,
  `seller_icon` varchar(100) DEFAULT NULL,
  `seller_intro` varchar(100) DEFAULT NULL,
  `seller_starttime` time(6) NOT NULL,
  `seller_endtime` time(6) NOT NULL,
  `seller_df` int(11) DEFAULT NULL,
  `seller_longitude` double(50,3) DEFAULT NULL,
  `seller_latitude` double(50,3) DEFAULT NULL,
  `species` varchar(50) NOT NULL,
  PRIMARY KEY (`s_id`),
  KEY `seller_df` (`seller_df`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_sellercollection
-- ----------------------------
DROP TABLE IF EXISTS `tb_sellercollection`;
CREATE TABLE `tb_sellercollection` (
  `scoll_id` varchar(22) NOT NULL,
  `s_id` varchar(22) NOT NULL,
  `u_id` varchar(22) NOT NULL,
  PRIMARY KEY (`scoll_id`),
  KEY `FK_Coll_FK_Reference_S` (`s_id`),
  KEY `FK_C_FK_Reference_User` (`u_id`),
  CONSTRAINT `fk_1` FOREIGN KEY (`u_id`) REFERENCES `tb_userinfo` (`u_id`),
  CONSTRAINT `fk_2` FOREIGN KEY (`s_id`) REFERENCES `tb_seller` (`s_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_sellercomment
-- ----------------------------
DROP TABLE IF EXISTS `tb_sellercomment`;
CREATE TABLE `tb_sellercomment` (
  `sc_id` varchar(22) NOT NULL,
  `sc_eat` int(11) NOT NULL,
  `sc_service` int(11) NOT NULL,
  `sc_content` varchar(50) DEFAULT NULL,
  `s_id` varchar(22) NOT NULL,
  `u_id` varchar(22) NOT NULL,
  `to_id` varchar(255) NOT NULL,
  PRIMARY KEY (`sc_id`),
  KEY `FK_SC_FK_Reference_S` (`s_id`),
  KEY `FK_SC_FK_Reference_User` (`u_id`),
  KEY `fk_5` (`to_id`),
  CONSTRAINT `fk_3` FOREIGN KEY (`u_id`) REFERENCES `tb_userinfo` (`u_id`),
  CONSTRAINT `fk_4` FOREIGN KEY (`s_id`) REFERENCES `tb_seller` (`s_id`),
  CONSTRAINT `fk_5` FOREIGN KEY (`to_id`) REFERENCES `tb_takeoutorder` (`to_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_sellerdish
-- ----------------------------
DROP TABLE IF EXISTS `tb_sellerdish`;
CREATE TABLE `tb_sellerdish` (
  `sd_id` varchar(22) NOT NULL,
  `sd_icon` varchar(100) DEFAULT NULL,
  `sd_name` varchar(20) NOT NULL,
  `sd_salecount` int(11) NOT NULL,
  `sd_price` int(11) NOT NULL,
  `smt_id` varchar(22) NOT NULL,
  PRIMARY KEY (`sd_id`),
  KEY `FK_Dish_FK_Reference_MT` (`smt_id`),
  CONSTRAINT `fk_6` FOREIGN KEY (`smt_id`) REFERENCES `tb_sellermenutype` (`smt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_sellermenutype
-- ----------------------------
DROP TABLE IF EXISTS `tb_sellermenutype`;
CREATE TABLE `tb_sellermenutype` (
  `smt_id` varchar(22) NOT NULL,
  `smt_name` varchar(10) NOT NULL,
  `s_id` varchar(22) NOT NULL,
  PRIMARY KEY (`smt_id`),
  KEY `FK_MT_FK_Reference_Seller` (`s_id`),
  CONSTRAINT `fk_7` FOREIGN KEY (`s_id`) REFERENCES `tb_seller` (`s_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_takeoutorder
-- ----------------------------
DROP TABLE IF EXISTS `tb_takeoutorder`;
CREATE TABLE `tb_takeoutorder` (
  `to_id` varchar(22) NOT NULL,
  `to_df` int(11) NOT NULL,
  `to_bf` int(11) NOT NULL,
  `to_extra` varchar(50) DEFAULT NULL,
  `to_name` varchar(20) NOT NULL,
  `to_phone` varchar(11) NOT NULL,
  `to_address` varchar(50) NOT NULL,
  `s_id` varchar(22) NOT NULL,
  `u_id` varchar(22) NOT NULL,
  PRIMARY KEY (`to_id`),
  KEY `FK_TO_FK_Reference_Seller` (`s_id`),
  KEY `FK_TO_FK_Reference_User` (`u_id`),
  KEY `fk_10` (`to_df`),
  CONSTRAINT `fk_10` FOREIGN KEY (`to_df`) REFERENCES `tb_seller` (`seller_df`),
  CONSTRAINT `fk_8` FOREIGN KEY (`s_id`) REFERENCES `tb_seller` (`s_id`),
  CONSTRAINT `fk_9` FOREIGN KEY (`u_id`) REFERENCES `tb_userinfo` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_takeoutorderinfo
-- ----------------------------
DROP TABLE IF EXISTS `tb_takeoutorderinfo`;
CREATE TABLE `tb_takeoutorderinfo` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `to_id` varchar(50) NOT NULL,
  `sd_id` varchar(50) NOT NULL,
  `num` int(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_11` (`to_id`),
  KEY `fk_12` (`sd_id`),
  CONSTRAINT `fk_11` FOREIGN KEY (`to_id`) REFERENCES `tb_takeoutorder` (`to_id`),
  CONSTRAINT `fk_12` FOREIGN KEY (`sd_id`) REFERENCES `tb_sellerdish` (`sd_id`)
) ENGINE=InnoDB AUTO_INCREMENT=170 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_takeoutorderstatus
-- ----------------------------
DROP TABLE IF EXISTS `tb_takeoutorderstatus`;
CREATE TABLE `tb_takeoutorderstatus` (
  `tos_id` varchar(22) NOT NULL,
  `tos_status` varchar(50) NOT NULL,
  `tos_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `to_id` varchar(22) NOT NULL,
  PRIMARY KEY (`tos_id`),
  KEY `FK_TOS_FK_Reference_TO` (`to_id`),
  CONSTRAINT `fk_13` FOREIGN KEY (`to_id`) REFERENCES `tb_takeoutorder` (`to_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_userinfo
-- ----------------------------
DROP TABLE IF EXISTS `tb_userinfo`;
CREATE TABLE `tb_userinfo` (
  `u_id` varchar(22) NOT NULL COMMENT '用户编号',
  `user_icon` varchar(100) DEFAULT NULL,
  `user_alias` varchar(20) NOT NULL,
  `user_sex` varchar(2) NOT NULL,
  `user_age` int(11) DEFAULT NULL,
  `user_signature` varchar(100) DEFAULT NULL,
  `username` varchar(24) NOT NULL,
  `pword` varchar(32) NOT NULL,
  `token` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- View structure for accountpoint_view
-- ----------------------------
DROP VIEW IF EXISTS `accountpoint_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `accountpoint_view` AS select `ap`.`ap_id` AS `ap_id`,`ap`.`ap_curpoint` AS `ap_curpoint`,`ap`.`ap_maxpoint` AS `ap_maxpoint`,`ap`.`ap_maxWeekRank` AS `ap_maxWeekRank`,`ap`.`ap_maxMonthRank` AS `ap_maxMonthRank`,`ap`.`ap_maxRank` AS `ap_maxRank`,`ap`.`ap_curWeekDif` AS `ap_curWeekDif`,`ap`.`ap_curMonthDif` AS `ap_curMonthDif`,`ap`.`u_id` AS `u_id`,`ui`.`user_alias` AS `user_alias`,`ui`.`user_icon` AS `user_icon` from (`tb_accountpoint` `ap` left join `tb_userinfo` `ui` on((`ap`.`u_id` = `ui`.`u_id`))) ;

-- ----------------------------
-- View structure for buyorder
-- ----------------------------
DROP VIEW IF EXISTS `buyorder`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `buyorder` AS select `tb_takeoutorder`.`to_id` AS `to_id`,`tb_takeoutorder`.`to_df` AS `to_df`,`tb_takeoutorder`.`to_bf` AS `to_bf`,`tb_takeoutorder`.`to_extra` AS `to_extra`,`tb_takeoutorder`.`to_name` AS `to_name`,`tb_takeoutorder`.`to_phone` AS `to_phone`,`tb_takeoutorder`.`to_address` AS `to_address`,`tb_takeoutorder`.`s_id` AS `s_id`,`tb_takeoutorder`.`u_id` AS `u_id`,`tb_takeoutorderstatus`.`tos_status` AS `tos_status`,`tb_takeoutorderstatus`.`tos_time` AS `tos_time`,`tb_takeoutorderstatus`.`tos_id` AS `tos_id`,`tb_takeoutorderinfo`.`id` AS `id`,`tb_takeoutorderinfo`.`num` AS `num`,`tb_takeoutorderinfo`.`sd_id` AS `sd_id`,`tb_sellerdish`.`sd_icon` AS `sd_icon`,`tb_sellerdish`.`sd_name` AS `sd_name`,`tb_sellerdish`.`sd_salecount` AS `sd_salecount`,`tb_sellerdish`.`sd_price` AS `sd_price`,`tb_sellerdish`.`smt_id` AS `smt_id`,`tb_sellermenutype`.`smt_name` AS `smt_name`,`tb_seller`.`seller_name` AS `seller_name`,`tb_seller`.`seller_degree` AS `seller_degree`,`tb_seller`.`seller_sendprice` AS `seller_sendprice`,`tb_seller`.`seller_deliverytime` AS `seller_deliverytime`,`tb_seller`.`seller_contact` AS `seller_contact`,`tb_seller`.`seller_status` AS `seller_status`,`tb_seller`.`seller_notice` AS `seller_notice`,`tb_seller`.`seller_icon` AS `seller_icon`,`tb_seller`.`seller_intro` AS `seller_intro`,`tb_seller`.`seller_starttime` AS `seller_starttime`,`tb_seller`.`seller_endtime` AS `seller_endtime`,`tb_seller`.`seller_df` AS `seller_df`,`tb_seller`.`seller_longitude` AS `seller_longitude`,`tb_seller`.`seller_latitude` AS `seller_latitude`,`tb_userinfo`.`user_alias` AS `user_alias` from ((((((`tb_takeoutorder` join `tb_takeoutorderstatus`) join `tb_takeoutorderinfo`) join `tb_seller`) join `tb_sellerdish`) join `tb_sellermenutype`) join `tb_userinfo`) where ((`tb_takeoutorder`.`to_id` = `tb_takeoutorderstatus`.`to_id`) and (`tb_takeoutorder`.`to_id` = `tb_takeoutorderinfo`.`to_id`) and (`tb_takeoutorderinfo`.`to_id` = `tb_takeoutorderstatus`.`to_id`) and (`tb_takeoutorder`.`s_id` = `tb_seller`.`s_id`) and (`tb_takeoutorderinfo`.`sd_id` = `tb_sellerdish`.`sd_id`) and (`tb_sellerdish`.`smt_id` = `tb_sellermenutype`.`smt_id`) and (`tb_takeoutorder`.`u_id` = `tb_userinfo`.`u_id`)) ;

-- ----------------------------
-- View structure for deliveryaddress_view
-- ----------------------------
DROP VIEW IF EXISTS `deliveryaddress_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `deliveryaddress_view` AS select `da`.`da_id` AS `da_id`,`da`.`da_name` AS `da_name`,`da`.`da_phone` AS `da_phone`,`da`.`da_address` AS `da_address`,`da`.`u_id` AS `u_id`,`ui`.`user_alias` AS `user_alias` from (`tb_deliveryaddress` `da` left join `tb_userinfo` `ui` on((`da`.`u_id` = `ui`.`u_id`))) ;

-- ----------------------------
-- View structure for dishtable_view
-- ----------------------------
DROP VIEW IF EXISTS `dishtable_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dishtable_view` AS select `dt`.`dt_id` AS `dt_id`,`dt`.`dt_count` AS `dt_count`,`dt`.`dt_total` AS `dt_total`,`dt`.`to_id` AS `to_id`,`tao`.`u_id` AS `u_id`,`ui`.`user_alias` AS `user_alias`,`dt`.`sd_id` AS `sd_id`,`sd`.`sd_name` AS `sd_name` from (((`tb_dishtable` `dt` left join `tb_sellerdish` `sd` on((`dt`.`sd_id` = `sd`.`sd_id`))) left join `tb_takeoutorder` `tao` on((`dt`.`to_id` = `tao`.`to_id`))) left join `tb_userinfo` `ui` on((`tao`.`u_id` = `ui`.`u_id`))) ;

-- ----------------------------
-- View structure for feedback_view
-- ----------------------------
DROP VIEW IF EXISTS `feedback_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `feedback_view` AS select `fb`.`fb_id` AS `fb_id`,`fb`.`fb_content` AS `fb_content`,`fb`.`fb_date` AS `fb_date`,`fb`.`u_id` AS `u_id`,`ui`.`user_alias` AS `user_alias` from (`tb_feedback` `fb` left join `tb_userinfo` `ui` on((`fb`.`u_id` = `ui`.`u_id`))) ;

-- ----------------------------
-- View structure for foodringmembers_view
-- ----------------------------
DROP VIEW IF EXISTS `foodringmembers_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `foodringmembers_view` AS select `frm`.`frm_id` AS `frm_id`,`frm`.`u_id` AS `u_id`,`ui`.`user_alias` AS `user_alias`,`ui`.`user_icon` AS `user_icon`,`ui`.`user_signature` AS `user_signature`,`frm`.`fr_id` AS `fr_id`,`fr`.`fr_name` AS `fr_name`,`frm`.`fr_jointime` AS `fr_jointime` from ((`tb_foodringmembers` `frm` left join `tb_userinfo` `ui` on((`frm`.`u_id` = `ui`.`u_id`))) left join `tb_foodring` `fr` on((`frm`.`fr_id` = `fr`.`fr_id`))) ;

-- ----------------------------
-- View structure for foodring_view
-- ----------------------------
DROP VIEW IF EXISTS `foodring_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `foodring_view` AS select `fr`.`fr_id` AS `fr_id`,`fr`.`fr_icon` AS `fr_icon`,`fr`.`fr_name` AS `fr_name`,`fr`.`fr_createtime` AS `fr_createtime`,`fr`.`fr_visible` AS `fr_visible`,`fr`.`u_id` AS `u_id`,`ui`.`user_alias` AS `user_alias`,`ui`.`user_icon` AS `user_icon`,`ui`.`user_signature` AS `user_signature` from (`tb_foodring` `fr` left join `tb_userinfo` `ui` on((`fr`.`u_id` = `ui`.`u_id`))) ;

-- ----------------------------
-- View structure for remark_view
-- ----------------------------
DROP VIEW IF EXISTS `remark_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `remark_view` AS select `re`.`re_id` AS `re_id`,`re`.`re_userid` AS `re_userid`,`uif`.`user_alias` AS `remarked_name`,`re`.`u_id` AS `u_id`,`uis`.`user_alias` AS `remark_name`,`re`.`fr_id` AS `fr_id`,`fr`.`fr_name` AS `fr_name`,`re`.`re_name` AS `re_name` from (((`tb_remark` `re` left join `tb_foodring` `fr` on((`re`.`fr_id` = `fr`.`fr_id`))) left join `tb_userinfo` `uif` on((`re`.`re_userid` = `uif`.`u_id`))) left join `tb_userinfo` `uis` on((`re`.`u_id` = `uis`.`u_id`))) ;

-- ----------------------------
-- View structure for restcollection_view
-- ----------------------------
DROP VIEW IF EXISTS `restcollection_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `restcollection_view` AS select `rc`.`rcoll_id` AS `rcoll_id`,`rc`.`r_id` AS `r_id`,`rest`.`r_name` AS `r_name`,`rc`.`u_id` AS `u_id`,`ui`.`user_alias` AS `user_alias` from ((`tb_restcollection` `rc` left join `tb_restaurant` `rest` on((`rc`.`r_id` = `rest`.`r_id`))) left join `tb_userinfo` `ui` on((`rc`.`u_id` = `ui`.`u_id`))) ;

-- ----------------------------
-- View structure for restcomment_view
-- ----------------------------
DROP VIEW IF EXISTS `restcomment_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `restcomment_view` AS select `rc`.`rc_id` AS `rc_id`,`rc`.`rc_eat` AS `rc_eat`,`rc`.`rc_service` AS `rc_service`,`rc`.`rc_env` AS `rc_env`,`rc`.`rc_content` AS `rc_content`,`rc`.`r_id` AS `r_id`,`rest`.`r_name` AS `r_name`,`rc`.`u_id` AS `u_id`,`ui`.`user_alias` AS `user_alias` from ((`tb_restcomment` `rc` left join `tb_restaurant` `rest` on((`rc`.`r_id` = `rest`.`r_id`))) left join `tb_userinfo` `ui` on((`rc`.`u_id` = `ui`.`u_id`))) ;

-- ----------------------------
-- View structure for restdish_view
-- ----------------------------
DROP VIEW IF EXISTS `restdish_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `restdish_view` AS select `rd`.`rd_id` AS `rd_id`,`rd`.`rd_icon` AS `rd_icon`,`rd`.`rd_name` AS `rd_name`,`rd`.`rd_price` AS `rd_price`,`rd`.`rmt_id` AS `rmt_id`,`rmt`.`rmt_name` AS `rmt_name`,`rmt`.`r_id` AS `r_id`,`r`.`r_name` AS `r_name` from ((`tb_restdish` `rd` left join `tb_restmenutype` `rmt` on((`rd`.`rmt_id` = `rmt`.`rmt_id`))) left join `tb_restaurant` `r` on((`rmt`.`r_id` = `r`.`r_id`))) ;

-- ----------------------------
-- View structure for restmenutype_view
-- ----------------------------
DROP VIEW IF EXISTS `restmenutype_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `restmenutype_view` AS select `rmt`.`rmt_id` AS `rmt_id`,`rmt`.`rmt_name` AS `rmt_name`,`rmt`.`r_id` AS `r_id`,`rest`.`r_name` AS `r_name` from (`tb_restmenutype` `rmt` left join `tb_restaurant` `rest` on((`rmt`.`r_id` = `rest`.`r_id`))) ;

-- ----------------------------
-- View structure for seatinfo_view
-- ----------------------------
DROP VIEW IF EXISTS `seatinfo_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `seatinfo_view` AS select `si`.`si_id` AS `si_id`,`si`.`u_id` AS `u_id`,`si`.`si_name` AS `si_name`,`si`.`si_phone` AS `si_phone`,`ui`.`user_alias` AS `user_alias` from (`tb_seatinfo` `si` left join `tb_userinfo` `ui` on((`si`.`u_id` = `ui`.`u_id`))) ;

-- ----------------------------
-- View structure for seatorderstatus_view
-- ----------------------------
DROP VIEW IF EXISTS `seatorderstatus_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `seatorderstatus_view` AS select `sos`.`sos_id` AS `sos_id`,`sos`.`sos_status` AS `sos_status`,`sos`.`sos_time` AS `sos_time`,`sos`.`so_id` AS `so_id`,`so`.`u_id` AS `u_id`,`ui`.`user_alias` AS `user_alias` from ((`tb_seatorderstatus` `sos` left join `tb_seatorder` `so` on((`sos`.`so_id` = `so`.`so_id`))) left join `tb_userinfo` `ui` on((`so`.`u_id` = `ui`.`u_id`))) ;

-- ----------------------------
-- View structure for seatorder_view
-- ----------------------------
DROP VIEW IF EXISTS `seatorder_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `seatorder_view` AS select `so`.`so_id` AS `so_id`,`so`.`so_time` AS `so_time`,`so`.`so_count` AS `so_count`,`so`.`so_extra` AS `so_extra`,`so`.`so_name` AS `so_name`,`so`.`so_phone` AS `so_phone`,`so`.`r_id` AS `r_id`,`so`.`u_id` AS `u_id`,`r`.`r_name` AS `r_name`,`ui`.`user_alias` AS `user_alias` from ((`tb_seatorder` `so` left join `tb_restaurant` `r` on((`so`.`r_id` = `r`.`r_id`))) left join `tb_userinfo` `ui` on((`so`.`u_id` = `ui`.`u_id`))) ;

-- ----------------------------
-- View structure for sellercollection_view
-- ----------------------------
DROP VIEW IF EXISTS `sellercollection_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `sellercollection_view` AS select `sc`.`scoll_id` AS `scoll_id`,`sc`.`s_id` AS `s_id`,`s`.`seller_name` AS `seller_name`,`sc`.`u_id` AS `u_id`,`ui`.`user_alias` AS `user_alias` from ((`tb_sellercollection` `sc` left join `tb_seller` `s` on((`sc`.`s_id` = `s`.`s_id`))) left join `tb_userinfo` `ui` on((`sc`.`u_id` = `ui`.`u_id`))) ;

-- ----------------------------
-- View structure for sellercomment_view
-- ----------------------------
DROP VIEW IF EXISTS `sellercomment_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `sellercomment_view` AS select `sc`.`sc_id` AS `sc_id`,`sc`.`sc_eat` AS `sc_eat`,`sc`.`sc_service` AS `sc_service`,`sc`.`sc_content` AS `sc_content`,`sc`.`s_id` AS `s_id`,`s`.`seller_name` AS `seller_name`,`sc`.`u_id` AS `u_id`,`ui`.`user_alias` AS `user_alias` from ((`tb_sellercomment` `sc` left join `tb_seller` `s` on((`sc`.`s_id` = `s`.`s_id`))) left join `tb_userinfo` `ui` on((`sc`.`u_id` = `ui`.`u_id`))) ;

-- ----------------------------
-- View structure for sellerdish_view
-- ----------------------------
DROP VIEW IF EXISTS `sellerdish_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `sellerdish_view` AS select `sd`.`sd_id` AS `sd_id`,`sd`.`sd_icon` AS `sd_icon`,`sd`.`sd_name` AS `sd_name`,`sd`.`sd_salecount` AS `sd_salecount`,`sd`.`sd_price` AS `sd_price`,`sd`.`smt_id` AS `smt_id`,`smt`.`smt_name` AS `smt_name`,`smt`.`s_id` AS `s_id`,`s`.`seller_name` AS `seller_name` from ((`tb_sellerdish` `sd` left join `tb_sellermenutype` `smt` on((`sd`.`smt_id` = `smt`.`smt_id`))) left join `tb_seller` `s` on((`smt`.`s_id` = `s`.`s_id`))) ;

-- ----------------------------
-- View structure for sellermenutype_view
-- ----------------------------
DROP VIEW IF EXISTS `sellermenutype_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `sellermenutype_view` AS select `smt`.`smt_id` AS `smt_id`,`smt`.`smt_name` AS `smt_name`,`smt`.`s_id` AS `s_id`,`s`.`seller_name` AS `seller_name` from (`tb_sellermenutype` `smt` left join `tb_seller` `s` on((`smt`.`s_id` = `s`.`s_id`))) ;

-- ----------------------------
-- View structure for takeoutorderstatus_view
-- ----------------------------
DROP VIEW IF EXISTS `takeoutorderstatus_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `takeoutorderstatus_view` AS select `tos`.`tos_id` AS `tos_id`,`tos`.`tos_status` AS `tos_status`,`tos`.`tos_time` AS `tos_time`,`tos`.`to_id` AS `to_id`,`tot`.`u_id` AS `u_id`,`ui`.`user_alias` AS `user_alias`,`tot`.`s_id` AS `s_id` from ((`tb_takeoutorderstatus` `tos` left join `tb_takeoutorder` `tot` on((`tos`.`to_id` = `tot`.`to_id`))) left join `tb_userinfo` `ui` on((`tot`.`u_id` = `ui`.`u_id`))) ;

-- ----------------------------
-- View structure for takeoutorder_view
-- ----------------------------
DROP VIEW IF EXISTS `takeoutorder_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `takeoutorder_view` AS select `tao`.`to_id` AS `to_id`,`tao`.`to_df` AS `to_df`,`tao`.`to_bf` AS `to_bf`,`tao`.`to_extra` AS `to_extra`,`tao`.`to_name` AS `to_name`,`tao`.`to_phone` AS `to_phone`,`tao`.`to_address` AS `to_address`,`s`.`s_id` AS `s_id`,`s`.`seller_name` AS `seller_name`,`ui`.`u_id` AS `u_id`,`ui`.`user_alias` AS `user_alias` from ((`tb_takeoutorder` `tao` left join `tb_seller` `s` on((`tao`.`s_id` = `s`.`s_id`))) left join `tb_userinfo` `ui` on((`tao`.`u_id` = `ui`.`u_id`))) ;

-- ----------------------------
-- View structure for usercollection
-- ----------------------------
DROP VIEW IF EXISTS `usercollection`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `usercollection` AS select `tb_sellercollection`.`scoll_id` AS `scoll_id`,`tb_sellercollection`.`u_id` AS `u_id`,`tb_sellercollection`.`s_id` AS `s_id`,`tb_seller`.`seller_name` AS `seller_name`,`tb_seller`.`seller_degree` AS `seller_degree`,`tb_seller`.`seller_sendprice` AS `seller_sendprice`,`tb_seller`.`seller_deliverytime` AS `seller_deliverytime`,`tb_seller`.`seller_contact` AS `seller_contact`,`tb_seller`.`seller_status` AS `seller_status`,`tb_seller`.`seller_notice` AS `seller_notice`,`tb_seller`.`seller_icon` AS `seller_icon`,`tb_seller`.`seller_intro` AS `seller_intro`,`tb_seller`.`seller_starttime` AS `seller_starttime`,`tb_seller`.`seller_endtime` AS `seller_endtime`,`tb_seller`.`seller_df` AS `seller_df`,`tb_seller`.`seller_longitude` AS `seller_longitude`,`tb_seller`.`seller_latitude` AS `seller_latitude` from (`tb_seller` join `tb_sellercollection`) where (`tb_seller`.`s_id` = `tb_sellercollection`.`s_id`) ;

-- ----------------------------
-- View structure for view_comment
-- ----------------------------
DROP VIEW IF EXISTS `view_comment`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_comment` AS select `tb_sellercomment`.`sc_id` AS `sc_id`,`tb_sellercomment`.`sc_eat` AS `sc_eat`,`tb_sellercomment`.`sc_service` AS `sc_service`,`tb_sellercomment`.`sc_content` AS `sc_content`,`tb_sellercomment`.`s_id` AS `s_id`,`tb_sellercomment`.`u_id` AS `u_id`,`tb_sellercomment`.`to_id` AS `to_id`,`tb_seller`.`seller_name` AS `seller_name`,`tb_seller`.`seller_degree` AS `seller_degree`,`tb_seller`.`seller_sendprice` AS `seller_sendprice`,`tb_seller`.`seller_deliverytime` AS `seller_deliverytime`,`tb_seller`.`seller_contact` AS `seller_contact`,`tb_seller`.`seller_status` AS `seller_status`,`tb_seller`.`seller_notice` AS `seller_notice`,`tb_seller`.`seller_icon` AS `seller_icon`,`tb_seller`.`seller_intro` AS `seller_intro`,`tb_seller`.`seller_starttime` AS `seller_starttime`,`tb_seller`.`seller_endtime` AS `seller_endtime`,`tb_seller`.`seller_df` AS `seller_df`,`tb_seller`.`seller_longitude` AS `seller_longitude`,`tb_seller`.`seller_latitude` AS `seller_latitude`,`tb_seller`.`species` AS `species`,`tb_userinfo`.`user_icon` AS `user_icon`,`tb_userinfo`.`user_alias` AS `user_alias`,`tb_userinfo`.`user_sex` AS `user_sex`,`tb_userinfo`.`user_age` AS `user_age`,`tb_userinfo`.`user_signature` AS `user_signature`,`tb_takeoutorder`.`to_df` AS `to_df`,`tb_takeoutorder`.`to_bf` AS `to_bf`,`tb_takeoutorder`.`to_extra` AS `to_extra`,`tb_takeoutorder`.`to_name` AS `to_name`,`tb_takeoutorder`.`to_phone` AS `to_phone`,`tb_takeoutorder`.`to_address` AS `to_address` from (((`tb_seller` join `tb_sellercomment`) join `tb_userinfo`) join `tb_takeoutorder`) where ((`tb_sellercomment`.`s_id` = `tb_seller`.`s_id`) and (`tb_sellercomment`.`u_id` = `tb_userinfo`.`u_id`) and (`tb_sellercomment`.`to_id` = `tb_takeoutorder`.`to_id`)) ;

-- ----------------------------
-- Procedure structure for get_ranking
-- ----------------------------
DROP PROCEDURE IF EXISTS `get_ranking`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_ranking`(in userId varchar(22), out weekRank int,out monthRank int, out totalRank int)
BEGIN
	set @weekNo = 0;
	select (@weekNo:=@weekNo+1) into weekRank FROM dblog.accountpoint_view where u_id=userId order by ap_curWeekDif desc;
	set @monthNo = 0;
	select (@monthNo:=@monthNo+1) into monthRank FROM dblog.accountpoint_view where u_id=userId order by ap_curMonthDif desc;
	set @totalNo = 0;
	select (@totalNo:=@totalNo+1) into totalRank FROM dblog.accountpoint_view where u_id=userId order by ap_maxpoint desc;

END
;;
DELIMITER ;
