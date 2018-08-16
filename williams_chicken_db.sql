/*
SQLyog Ultimate v9.10 
MySQL - 5.7.23-0ubuntu0.16.04.1 : Database - williams_chicken
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`williams_chicken` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `williams_chicken`;

/*Table structure for table `access_log` */

DROP TABLE IF EXISTS `access_log`;

CREATE TABLE `access_log` (
  `ACCESS_LOG_ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ACCESS_DATE` datetime NOT NULL,
  `IP` varchar(15) NOT NULL,
  `USERNAME` varchar(50) NOT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  `SUCCESS` tinyint(1) NOT NULL,
  `OUTCOME` varchar(20) NOT NULL,
  PRIMARY KEY (`ACCESS_LOG_ID`),
  KEY `FK_access_log_USER_ID` (`USER_ID`),
  CONSTRAINT `FK_access_log_user_id` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `access_log` */

/*Table structure for table `bank_register` */

DROP TABLE IF EXISTS `bank_register`;

CREATE TABLE `bank_register` (
  `BANK_REGISTER_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `INITIALS` varchar(10) NOT NULL DEFAULT 'NA',
  `STORE_ID` int(11) NOT NULL,
  `PAYMENT_MODE_ID` int(11) unsigned NOT NULL,
  `AMOUNT` double NOT NULL,
  `SUBMIT_DATE` date NOT NULL,
  `CREATED_DATE` datetime NOT NULL,
  `CREATED_BY` int(11) NOT NULL,
  `LAST_MODIFIED_DATE` date DEFAULT NULL,
  `LAST_MODIFIED_BY` int(11) DEFAULT NULL,
  PRIMARY KEY (`BANK_REGISTER_ID`),
  KEY `FK_bank_register_payment_mode` (`PAYMENT_MODE_ID`),
  CONSTRAINT `FK_bank_register_payment_mode` FOREIGN KEY (`PAYMENT_MODE_ID`) REFERENCES `payment_mode` (`PAYMENT_MODE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `bank_register` */

/*Table structure for table `business_function` */

DROP TABLE IF EXISTS `business_function`;

CREATE TABLE `business_function` (
  `BUSINESS_FUNCTION` varchar(50) NOT NULL,
  `BUSINESS_FUNCTION_DESC` varchar(50) NOT NULL,
  PRIMARY KEY (`BUSINESS_FUNCTION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `business_function` */

insert  into `business_function`(`BUSINESS_FUNCTION`,`BUSINESS_FUNCTION_DESC`) values ('ROLE_BF_CREATE_EMPLOYEE','create employee'),('ROLE_BF_CREATE_MANAGER','create manager'),('ROLE_BF_CREATE_STORE','create store'),('ROLE_BF_CREATE_USER','create user'),('ROLE_BF_CREATE_VENDOR','create vendor'),('ROLE_BF_EDIT_USER','edit user'),('ROLE_BF_EMPLOYEE_LIST','employee list'),('ROLE_BF_LIST_USER','list user'),('ROLE_BF_MANAGER_LIST','manager list'),('ROLE_BF_PASSWORD_EXPIRED','password expired'),('ROLE_BF_RELOAD_APP_LAYER','reload app layer'),('ROLE_BF_REPORT_ACCESS_LOG','access log'),('ROLE_BF_SHOW_HOME','show home'),('ROLE_BF_STORE_LIST','store list'),('ROLE_BF_VENDOR_LIST','vendor list');

/*Table structure for table `can_create_roles` */

DROP TABLE IF EXISTS `can_create_roles`;

CREATE TABLE `can_create_roles` (
  `ROLE_ID` varchar(30) DEFAULT NULL,
  `CAN_CREATE_ROLE` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `can_create_roles` */

insert  into `can_create_roles`(`ROLE_ID`,`CAN_CREATE_ROLE`) values ('ROLE_SUPER','ROLE_SUPER');

/*Table structure for table `emailer` */

DROP TABLE IF EXISTS `emailer`;

CREATE TABLE `emailer` (
  `EMAIL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TO_SEND` varchar(100) DEFAULT NULL,
  `CC_SEND_TO` varchar(100) DEFAULT NULL,
  `BCC_SEND_TO` varchar(100) DEFAULT NULL,
  `SUBJECT` varchar(100) DEFAULT NULL,
  `BODY` text,
  `CREATED_DATE` datetime DEFAULT NULL,
  `TO_SEND_DATE` datetime DEFAULT NULL,
  `LAST_MODIFIED_DATE` datetime DEFAULT NULL,
  `STATUS` tinyint(4) DEFAULT NULL,
  `ATTEMPTS` tinyint(4) DEFAULT NULL,
  `RESPONSE` text,
  PRIMARY KEY (`EMAIL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `emailer` */

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `EMPLOYEE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(20) DEFAULT NULL,
  `LAST_NAME` varchar(20) DEFAULT NULL,
  `EMAIL_ID` varchar(20) DEFAULT NULL,
  `PHONE_NO` varchar(20) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `LAST_MODIFIED_DATE` datetime DEFAULT NULL,
  `LAST_MODIFIED_BY` int(11) DEFAULT NULL,
  `ACTIVE` tinyint(4) DEFAULT NULL,
  `STORE_ID` int(11) DEFAULT NULL,
  `PAY_RATE` int(10) DEFAULT NULL,
  PRIMARY KEY (`EMPLOYEE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `employee` */

/*Table structure for table `fcw` */

DROP TABLE IF EXISTS `fcw`;

CREATE TABLE `fcw` (
  `FCW_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STORE_ID` int(11) NOT NULL,
  `VENDOR_ID` int(11) NOT NULL,
  `INVOICE` varchar(40) DEFAULT NULL,
  `AMOUNT` double NOT NULL,
  `SUBMIT_DATE` datetime NOT NULL,
  `NO_OF_STORE_TRANSACTION` int(11) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `LAST_MODIFIED_DATE` datetime DEFAULT NULL,
  `LAST_MODIFIED_BY` int(11) DEFAULT NULL,
  `DUMMY_VENDOR` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`FCW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `fcw` */

/*Table structure for table `fcw_paid_outs` */

DROP TABLE IF EXISTS `fcw_paid_outs`;

CREATE TABLE `fcw_paid_outs` (
  `STORE_ID` int(11) NOT NULL,
  `SUBMIT_DATE` datetime NOT NULL,
  `PAID_OUTS` double(6,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `fcw_paid_outs` */

/*Table structure for table `manager` */

DROP TABLE IF EXISTS `manager`;

CREATE TABLE `manager` (
  `MANAGER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(20) DEFAULT NULL,
  `LAST_NAME` varchar(20) DEFAULT NULL,
  `EMAIL_ID` varchar(40) DEFAULT NULL,
  `PHONE_NUMBER` varchar(20) DEFAULT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `LAST_MODIFIED_DATE` datetime DEFAULT NULL,
  `LAST_MODIFIED_BY` int(11) DEFAULT NULL,
  `ACTIVE` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`MANAGER_ID`),
  KEY `managerUserId_userId` (`USER_ID`),
  CONSTRAINT `managerUserId_userId` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `manager` */

/*Table structure for table `payment_mode` */

DROP TABLE IF EXISTS `payment_mode`;

CREATE TABLE `payment_mode` (
  `PAYMENT_MODE_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `PAYMENT_MODE_NAME` varchar(50) NOT NULL,
  `CREATED_DATE` datetime NOT NULL,
  `CREATED_BY` int(11) NOT NULL,
  PRIMARY KEY (`PAYMENT_MODE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `payment_mode` */

/*Table structure for table `payroll` */

DROP TABLE IF EXISTS `payroll`;

CREATE TABLE `payroll` (
  `PAYROLL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STORE_ID` int(11) DEFAULT NULL,
  `EMPLOYEE_ID` int(11) DEFAULT NULL,
  `REG_HOUR` double DEFAULT NULL,
  `OT` int(11) DEFAULT NULL,
  `PAY_RATE` double DEFAULT NULL,
  `SUBMIT_DATE` datetime DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `LAST_MODIFIED_DATE` datetime DEFAULT NULL,
  `LAST_MODIFIED_BY` int(11) DEFAULT NULL,
  `START_DATE` datetime DEFAULT NULL,
  `STOP_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`PAYROLL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `payroll` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `ROLE_ID` varchar(25) NOT NULL,
  `ROLE_NAME` varchar(25) NOT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `role` */

insert  into `role`(`ROLE_ID`,`ROLE_NAME`) values ('ROLE_SUPER','Super User');

/*Table structure for table `role_business_function` */

DROP TABLE IF EXISTS `role_business_function`;

CREATE TABLE `role_business_function` (
  `ROLE_ID` varchar(25) DEFAULT NULL,
  `BUSINESS_FUNCTION_ID` varchar(50) DEFAULT NULL,
  KEY `FK_role_business_function_ROLE_ID` (`ROLE_ID`),
  KEY `FK_role_business_function_BUSINESS_FUNCTION_ID` (`BUSINESS_FUNCTION_ID`),
  CONSTRAINT `FK_role_business_function_BUSINESS_FUNCTION_ID` FOREIGN KEY (`BUSINESS_FUNCTION_ID`) REFERENCES `business_function` (`BUSINESS_FUNCTION`),
  CONSTRAINT `FK_role_business_function_ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `role_business_function` */

insert  into `role_business_function`(`ROLE_ID`,`BUSINESS_FUNCTION_ID`) values ('ROLE_SUPER','ROLE_BF_CREATE_USER'),('ROLE_SUPER','ROLE_BF_EDIT_USER'),('ROLE_SUPER','ROLE_BF_LIST_USER'),('ROLE_SUPER','ROLE_BF_PASSWORD_EXPIRED'),('ROLE_SUPER','ROLE_BF_RELOAD_APP_LAYER'),('ROLE_SUPER','ROLE_BF_REPORT_ACCESS_LOG'),('ROLE_SUPER','ROLE_BF_SHOW_HOME'),('ROLE_SUPER','ROLE_BF_CREATE_MANAGER'),('ROLE_SUPER','ROLE_BF_MANAGER_LIST'),('ROLE_SUPER','ROLE_BF_CREATE_VENDOR'),('ROLE_SUPER','ROLE_BF_VENDOR_LIST'),('ROLE_SUPER','ROLE_BF_CREATE_EMPLOYEE'),('ROLE_SUPER','ROLE_BF_EMPLOYEE_LIST'),('ROLE_SUPER','ROLE_BF_CREATE_STORE'),('ROLE_SUPER','ROLE_BF_STORE_LIST');

/*Table structure for table `sales` */

DROP TABLE IF EXISTS `sales`;

CREATE TABLE `sales` (
  `SALE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STORE_ID` int(11) DEFAULT NULL,
  `SUBMIT_DATE` datetime DEFAULT NULL,
  `TOTAL_SALES` double DEFAULT NULL,
  `NON_TAX_SALES` double DEFAULT NULL,
  `NET_SALES` double DEFAULT NULL,
  `SALES_TAX` double DEFAULT NULL,
  `GROSS_SALES` double DEFAULT NULL,
  `ACCOUNT_RECEIVABLE` double DEFAULT NULL,
  `TOTAl_PAID_OUT` double DEFAULT NULL,
  `UBER_ACCOUNT` double DEFAULT NULL,
  `AMOUNT_PER_BIRD` double DEFAULT NULL,
  `BEGNING_HEAD_COUNT` int(11) DEFAULT NULL,
  `STORE_TRANSFER` int(11) DEFAULT NULL,
  `PURCHASE` int(11) DEFAULT NULL,
  `CHICKEN_USAGE` int(11) DEFAULT NULL,
  `BIRDS_WASTED` int(11) DEFAULT NULL,
  `BIRDS_ON_HAND` int(11) DEFAULT NULL,
  `ENDING_INVENTORY` int(11) DEFAULT NULL,
  `VARIANCE` int(11) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `LAST_MODIFIED_DATE` datetime DEFAULT NULL,
  `LAST_MODIFIED_BY` int(11) DEFAULT NULL,
  `CUSTOMER_COUNT` int(11) DEFAULT NULL,
  `TOTAL_DEPOSIT` double DEFAULT NULL,
  `DOOR_DASH_ACCOUNT` double DEFAULT NULL,
  `CASH` double DEFAULT NULL,
  `CHECK_AVERAGE` double DEFAULT NULL,
  `REFOUNDS` double DEFAULT NULL,
  PRIMARY KEY (`SALE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `sales` */

/*Table structure for table `state` */

DROP TABLE IF EXISTS `state`;

CREATE TABLE `state` (
  `STATE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STATE_NAME` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`STATE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `state` */

/*Table structure for table `store` */

DROP TABLE IF EXISTS `store`;

CREATE TABLE `store` (
  `STORE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STORE_NAME` varchar(40) DEFAULT NULL,
  `STORE_CITY` varchar(30) DEFAULT NULL,
  `STATE_ID` int(11) DEFAULT NULL,
  `STORE_ADDRESS` varchar(50) DEFAULT NULL,
  `CREATED_DATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` int(11) DEFAULT NULL,
  `LAST_MODIFIED_DATE` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LAST_MODIFIED_BY` int(11) DEFAULT NULL,
  `ACTIVE` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`STORE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `store` */

/*Table structure for table `store_manager_mapping` */

DROP TABLE IF EXISTS `store_manager_mapping`;

CREATE TABLE `store_manager_mapping` (
  `STORE_MANAGER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STORE_ID` int(11) DEFAULT NULL,
  `MANAGER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`STORE_MANAGER_ID`),
  KEY `storeId_store_manager_id` (`STORE_ID`),
  KEY `managerId_store_manager_id` (`MANAGER_ID`),
  CONSTRAINT `managerId_store_manager_id` FOREIGN KEY (`MANAGER_ID`) REFERENCES `manager` (`MANAGER_ID`),
  CONSTRAINT `storeId_store_manager_id` FOREIGN KEY (`STORE_ID`) REFERENCES `store` (`STORE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `store_manager_mapping` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(25) NOT NULL,
  `PASSWORD` tinyblob NOT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  `EXPIRED` tinyint(1) NOT NULL,
  `FAILED_ATTEMPTS` tinyint(3) NOT NULL,
  `EXPIRES_ON` date NOT NULL,
  `CREATED_BY` int(11) NOT NULL,
  `CREATED_DATE` datetime NOT NULL,
  `OUTSIDE_ACCESS` tinyint(4) NOT NULL DEFAULT '0',
  `LAST_LOGIN_DATE` datetime DEFAULT NULL,
  `LAST_MODIFIED_BY` int(11) NOT NULL,
  `LAST_MODIFIED_DATE` datetime NOT NULL,
  `EMAIL_ID` varchar(100) DEFAULT NULL,
  `PHONE_NO` varchar(20) DEFAULT NULL,
  `TOKEN` varchar(20) DEFAULT NULL COMMENT 'TOKEN FOR VALIDATION',
  `AUTHORISED_FLAG` tinyint(4) DEFAULT NULL,
  `EXPIRY_DATE` datetime DEFAULT NULL COMMENT 'TOKEN EXPIRY DATE',
  `ISSUED_DATE` datetime DEFAULT NULL COMMENT 'TOKEN ISSUED DATE',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `unq_USERNAME` (`USERNAME`),
  UNIQUE KEY `unq_email_id` (`EMAIL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`USER_ID`,`USERNAME`,`PASSWORD`,`ACTIVE`,`EXPIRED`,`FAILED_ATTEMPTS`,`EXPIRES_ON`,`CREATED_BY`,`CREATED_DATE`,`OUTSIDE_ACCESS`,`LAST_LOGIN_DATE`,`LAST_MODIFIED_BY`,`LAST_MODIFIED_DATE`,`EMAIL_ID`,`PHONE_NO`,`TOKEN`,`AUTHORISED_FLAG`,`EXPIRY_DATE`,`ISSUED_DATE`) values (1,'super','$2a$10$/5RlTQcogDqgchR7F2ZWVOKKt4Y2MSbud3ovdo720vCQetaUfQ3Em',1,0,0,'2020-06-25',1,'2018-06-18 18:21:09',1,'2018-08-06 17:42:20',1,'2018-06-18 18:21:09','super@williamschicken.com',NULL,'JW7RWG1mEjA2JXwH',1,'2019-07-16 17:42:05','2018-06-12 17:42:59');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `USER_ID` int(10) NOT NULL,
  `ROLE_ID` varchar(25) NOT NULL,
  KEY `FK_user_role_USER_ID` (`USER_ID`),
  KEY `FK_user_role_ROLE_ID` (`ROLE_ID`),
  CONSTRAINT `FK_user_role_roleId` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ROLE_ID`),
  CONSTRAINT `FK_user_role_userId` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_role` */

insert  into `user_role`(`USER_ID`,`ROLE_ID`) values (1,'ROLE_SUPER');

/*Table structure for table `vendor` */

DROP TABLE IF EXISTS `vendor`;

CREATE TABLE `vendor` (
  `VENDOR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `VENDOR_NAME` varchar(40) DEFAULT NULL,
  `VENDOR_CITY` varchar(30) DEFAULT NULL,
  `STATE_ID` int(11) DEFAULT NULL,
  `VENDOR_ADDRESS` text,
  `CREATED_DATE` datetime DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `LAST_MODIFIED_DATE` datetime DEFAULT NULL,
  `LAST_MODIFIED_BY` int(11) DEFAULT NULL,
  `ACTIVE` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`VENDOR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `vendor` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
