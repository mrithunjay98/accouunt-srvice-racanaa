# ************************************************************
# Sequel Ace SQL dump
# Version 20051
#
# https://sequel-ace.com/
# https://github.com/Sequel-Ace/Sequel-Ace
#
# Host: localhost (MySQL 8.1.0)
# Database: accountDb
# Generation Time: 2023-09-27 14:12:04 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE='NO_AUTO_VALUE_ON_ZERO', SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table account
# ------------------------------------------------------------

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `id` varchar(255) NOT NULL,
  `address_line_1` varchar(255) DEFAULT NULL,
  `address_line_2` varchar(255) DEFAULT NULL,
  `api_key` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `contact_masked` bigint DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `country_code` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `date_created` datetime(6) NOT NULL,
  `date_updated` datetime(6) NOT NULL,
  `district` varchar(255) DEFAULT NULL,
  `domain` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `email_masked` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `name_registered` varchar(255) DEFAULT NULL,
  `pin_code` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `tenant_id` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_account_name_tenant` (`name`,`tenant_id`),
  UNIQUE KEY `uk_account_domain_tenant` (`domain`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;

INSERT INTO `account` (`id`, `address_line_1`, `address_line_2`, `api_key`, `city`, `code`, `contact`, `contact_masked`, `country`, `country_code`, `created_by`, `date_created`, `date_updated`, `district`, `domain`, `email`, `email_masked`, `name`, `name_registered`, `pin_code`, `state`, `tenant_id`, `type`, `updated_by`)
VALUES
	('402881888ac3ce6b018ac3ce6f730000','WhiteField',NULL,'6e399983-862e-4435-808a-4056ebc4f841',NULL,NULL,'12333312121l',0,'India','+91',NULL,'2023-09-24 02:20:00.697000','2023-09-24 02:20:00.697000','Bangalore',NULL,'support@racanaa.com',NULL,'Racanaa',NULL,'560078','Karnataka','402881888ac3ce6b018ac3ce6f730000','ROOT',NULL),
	('402881888ac3ce6b018ac3ce6f810001','WhiteField',NULL,'52c468ef-f739-49f1-ab2d-1c476f24472c',NULL,NULL,'12333312121l',0,'India','+91',NULL,'2023-09-24 02:20:00.706000','2023-09-25 20:41:20.205000','Delhi','mnegi.com','mani.mani@racanaa.com',NULL,'MNEGI',NULL,'560078','Karnataka','402881888ac3ce6b018ac3ce6f730000','CLIENT',NULL),
	('402881888ac3ce6b018ac3ce6f840002','WhiteField',NULL,'753306cc-45ea-44fa-9e86-3ae5822c42b4',NULL,NULL,'12333312121l',0,'India','+91',NULL,'2023-09-24 02:20:00.709000','2023-09-24 02:20:00.709000','Bangalore',NULL,'support@racanaa.com',NULL,'Carbon Happy',NULL,'560078','Karnataka','402881888ac3ce6b018ac3ce6f730000','AGENCY',NULL),
	('402881888ac3ce6b018ac3ce6f860003','WhiteField',NULL,'620285e0-835d-49f3-81b7-3c8b270bd7eb',NULL,NULL,'12333312121l',0,'India','+91',NULL,'2023-09-24 02:20:00.715000','2023-09-24 02:20:00.715000','Bangalore',NULL,'support@racanaa.com',NULL,'Beans Coffee',NULL,'560078','Karnataka','402881888ac3ce6b018ac3ce6f730000','CLIENT',NULL),
	('ff8080818acc7db3018acc7e52190000',NULL,NULL,NULL,'Bangalore',NULL,'919900900',0,'India','+91',NULL,'2023-09-25 18:49:05.246000','2023-09-25 18:49:05.246000',NULL,NULL,'derwent@racanaa.com',NULL,'Derwent',NULL,'560000','Karnataka','402881888ac3ce6b018ac3ce6f730000','CLIENT',NULL),
	('ff8080818acccc47018acccc9c4d0000',NULL,NULL,NULL,'Bangalore',NULL,'919900900',0,'India','+91',NULL,'2023-09-25 20:14:36.052000','2023-09-25 20:14:36.052000',NULL,NULL,'derwent@racanaa.com',NULL,'Derwent w',NULL,'560000','Karnataka','402881888ac3ce6b018ac3ce6f730000','CLIENT',NULL),
	('ff8080818accd3cb018accd3f6f00000',NULL,NULL,NULL,'Bangalore',NULL,'919900900',0,'India','+91',NULL,'2023-09-25 20:22:38.006000','2023-09-26 22:47:40.181000','Delhi','cprl113.com','mani.mani@cprl.com',NULL,'CPRL 343',NULL,'560000','Karnataka','402881888ac3ce6b018ac3ce6f730000','CLIENT',NULL),
	('ff8080818accd588018accd6dddf0000',NULL,NULL,NULL,'Bangalore',NULL,'919900900',0,'India','+91',NULL,'2023-09-25 20:25:48.198000','2023-09-25 20:25:48.198000',NULL,'mneg.bb.int','derwent@racanaa.com',NULL,'Derwent bb',NULL,'560000','Karnataka','402881888ac3ce6b018ac3ce6f730000','CLIENT',NULL),
	('ff8080818accf62c018accf630670000','WhiteField',NULL,'1312c904-1bce-4d50-81a4-4808b0fc5022',NULL,NULL,'12333312121l',0,'India','+91',NULL,'2023-09-25 21:00:00.941000','2023-09-25 21:00:00.941000','Bangalore',NULL,'support@racanaa.com',NULL,'Evolve Studio',NULL,'560078','Karnataka','402881888ac3ce6b018ac3ce6f730000','CLIENT',NULL),
	('ff8080818ad1d954018ad1d9ffa80000',NULL,NULL,NULL,'Bangalore',NULL,'919900900',0,'India','+91',NULL,'2023-09-26 19:47:19.534000','2023-09-26 19:47:19.534000',NULL,'new.i.int','m@racanaa.com',NULL,'New account',NULL,'560000','Karnataka','402881888ac3ce6b018ac3ce6f730000','CLIENT',NULL),
	('ff8080818ad274c5018ad27ad5360001',NULL,NULL,NULL,'Bangalore',NULL,'919900900',0,'India','+91',NULL,'2023-09-26 22:42:59.959000','2023-09-26 22:42:59.959000',NULL,'aaaa.i.int','m@racanaa.com',NULL,'New am',NULL,'560000','Karnataka','402881888ac3ce6b018ac3ce6f730000','CLIENT',NULL),
	('ff8080818ad52c59018ad52d1c8f0000',NULL,NULL,NULL,'Bangalore',NULL,'919900900',0,'India','+91',NULL,'2023-09-27 11:16:58.071000','2023-09-27 11:23:46.567000','Delhi','asasa12121.com','mani.mani@cprl.com',NULL,'CPRL 2121',NULL,'560000','Karnataka','402881888ac3ce6b018ac3ce6f730000','CLIENT',NULL);

/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table account_preference
# ------------------------------------------------------------

DROP TABLE IF EXISTS `account_preference`;

CREATE TABLE `account_preference` (
  `id` varchar(255) NOT NULL,
  `account_id` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `date_created` datetime(6) NOT NULL,
  `date_updated` datetime(6) NOT NULL,
  `preference` varchar(255) DEFAULT NULL,
  `tenant_id` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `account_preference` WRITE;
/*!40000 ALTER TABLE `account_preference` DISABLE KEYS */;

INSERT INTO `account_preference` (`id`, `account_id`, `created_by`, `date_created`, `date_updated`, `preference`, `tenant_id`, `updated_by`, `value`, `name`)
VALUES
	('402881888ac83f03018ac83f316b0000','402881888ac3ce6b018ac3ce6f860003',NULL,'2023-09-24 23:01:39.248000','2023-09-24 23:01:39.248000',NULL,'402881888ac3ce6b018ac3ce6f730000',NULL,'IST','timezone'),
	('402881888ac83f03018ac83f6abe0001','402881888ac3ce6b018ac3ce6f860003',NULL,'2023-09-24 23:01:53.919000','2023-09-24 23:01:53.919000',NULL,'402881888ac3ce6b018ac3ce6f730000',NULL,'KVH','energyUnit'),
	('402881888ac83f03018ac83f91f10002','402881888ac3ce6b018ac3ce6f810001',NULL,'2023-09-24 23:02:03.954000','2023-09-24 23:02:03.954000',NULL,'402881888ac3ce6b018ac3ce6f730000',NULL,'celcius','temperature'),
	('402881888ac83f03018ac8400d200003','402881888ac3ce6b018ac3ce6f860003',NULL,'2023-09-24 23:02:35.489000','2023-09-24 23:02:35.489000',NULL,'402881888ac3ce6b018ac3ce6f730000',NULL,'INR','currency'),
	('ff8080818ad274c5018ad2827ed10002','402881888ac3ce6b018ac3ce6f810001',NULL,'2023-09-26 22:51:22.131000','2023-09-26 22:51:22.131000',NULL,'402881888ac3ce6b018ac3ce6f730000',NULL,'KM','distance'),
	('ff8080818ad33f62018ad34647e40001',NULL,NULL,'2023-09-27 02:25:13.125000','2023-09-27 02:25:13.125000',NULL,NULL,NULL,'Kilometers','distance'),
	('ff8080818ad33f62018ad3465d9e0002',NULL,NULL,'2023-09-27 02:25:18.687000','2023-09-27 02:25:18.687000',NULL,NULL,NULL,'MM','distance');

/*!40000 ALTER TABLE `account_preference` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table privilege
# ------------------------------------------------------------

DROP TABLE IF EXISTS `privilege`;

CREATE TABLE `privilege` (
  `id` varchar(255) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `date_created` datetime(6) NOT NULL,
  `date_updated` datetime(6) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `tenant_id` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `privilege` WRITE;
/*!40000 ALTER TABLE `privilege` DISABLE KEYS */;

INSERT INTO `privilege` (`id`, `created_by`, `date_created`, `date_updated`, `name`, `tenant_id`, `updated_by`)
VALUES
	('402881888ac3ce6b018ac3ce6f8a0004',NULL,'2023-09-24 02:20:00.716000','2023-09-24 02:20:00.716000','READ','402881888ac3ce6b018ac3ce6f730000',NULL),
	('402881888ac3ce6b018ac3ce6f8e0005',NULL,'2023-09-24 02:20:00.718000','2023-09-24 02:20:00.718000','WRITE','402881888ac3ce6b018ac3ce6f730000',NULL),
	('402881888ac3ce6b018ac3ce6f8f0006',NULL,'2023-09-24 02:20:00.720000','2023-09-24 02:20:00.720000','UPDATE','402881888ac3ce6b018ac3ce6f730000',NULL),
	('402881888ac3ce6b018ac3ce6f910007',NULL,'2023-09-24 02:20:00.729000','2023-09-24 02:20:00.729000','DELETE','402881888ac3ce6b018ac3ce6f730000',NULL);

/*!40000 ALTER TABLE `privilege` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` varchar(255) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `date_created` datetime(6) NOT NULL,
  `date_updated` datetime(6) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `tenant_id` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;

INSERT INTO `role` (`id`, `created_by`, `date_created`, `date_updated`, `name`, `tenant_id`, `updated_by`)
VALUES
	('402881888ac3ce6b018ac3ce6f950008',NULL,'2023-09-24 02:20:00.730000','2023-09-24 02:20:00.730000','SUPER_ADMIN','402881888ac3ce6b018ac3ce6f730000',NULL),
	('402881888ac3ce6b018ac3ce6fa00009',NULL,'2023-09-24 02:20:00.737000','2023-09-24 02:20:00.737000','ADMIN','402881888ac3ce6b018ac3ce6f730000',NULL),
	('402881888ac3ce6b018ac3ce6fa3000a',NULL,'2023-09-24 02:20:00.830000','2023-09-24 02:20:00.830000','USER','402881888ac3ce6b018ac3ce6f730000',NULL);

/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table roles_privileges
# ------------------------------------------------------------

DROP TABLE IF EXISTS `roles_privileges`;

CREATE TABLE `roles_privileges` (
  `role_id` varchar(255) NOT NULL,
  `privilege_id` varchar(255) NOT NULL,
  KEY `FK5yjwxw2gvfyu76j3rgqwo685u` (`privilege_id`),
  KEY `FK9h2vewsqh8luhfq71xokh4who` (`role_id`),
  CONSTRAINT `FK5yjwxw2gvfyu76j3rgqwo685u` FOREIGN KEY (`privilege_id`) REFERENCES `privilege` (`id`),
  CONSTRAINT `FK9h2vewsqh8luhfq71xokh4who` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `roles_privileges` WRITE;
/*!40000 ALTER TABLE `roles_privileges` DISABLE KEYS */;

INSERT INTO `roles_privileges` (`role_id`, `privilege_id`)
VALUES
	('402881888ac3ce6b018ac3ce6f950008','402881888ac3ce6b018ac3ce6f8a0004'),
	('402881888ac3ce6b018ac3ce6f950008','402881888ac3ce6b018ac3ce6f8e0005'),
	('402881888ac3ce6b018ac3ce6f950008','402881888ac3ce6b018ac3ce6f8f0006'),
	('402881888ac3ce6b018ac3ce6f950008','402881888ac3ce6b018ac3ce6f910007'),
	('402881888ac3ce6b018ac3ce6fa00009','402881888ac3ce6b018ac3ce6f8a0004'),
	('402881888ac3ce6b018ac3ce6fa00009','402881888ac3ce6b018ac3ce6f8e0005'),
	('402881888ac3ce6b018ac3ce6fa3000a','402881888ac3ce6b018ac3ce6f8a0004');

/*!40000 ALTER TABLE `roles_privileges` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table system_config
# ------------------------------------------------------------

DROP TABLE IF EXISTS `system_config`;

CREATE TABLE `system_config` (
  `name` varchar(255) NOT NULL,
  `date_created` datetime(6) NOT NULL,
  `date_updated` datetime(6) NOT NULL,
  `tenant_id` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `system_config` WRITE;
/*!40000 ALTER TABLE `system_config` DISABLE KEYS */;

INSERT INTO `system_config` (`name`, `date_created`, `date_updated`, `tenant_id`, `value`, `created_by`, `updated_by`)
VALUES
	('api.key.header.name','2023-09-26 09:45:26.000000','2023-09-26 09:45:26.000000','402881888abbf1f3018abbf1f6a00007','x-api-key',NULL,NULL),
	('api.key.value','2023-09-26 09:45:26.000000','2023-09-26 09:45:26.000000','402881888abbf1f3018abbf1f6a00007','6e399983-862e-4435-808a-4056ebc4f841',NULL,NULL),
	('beans.inspect','2023-09-26 09:45:26.000000','2023-09-26 09:45:26.000000','402881888abbf1f3018abbf1f6a00007','0',NULL,NULL),
	('jwt.token.expiry.seconds','2023-09-26 09:45:26.000000','2023-09-26 09:45:26.000000','402881888abbf1f3018abbf1f6a00007','86400',NULL,NULL),
	('jwt.token.header.name','2023-09-26 09:45:26.000000','2023-09-26 09:45:26.000000','402881888abbf1f3018abbf1f6a00007','Authorization',NULL,NULL),
	('jwt.token.secret','2023-09-26 09:45:26.000000','2023-09-26 09:45:26.000000','402881888abbf1f3018abbf1f6a00007','A7D2A23FAFF1B9DEA2BE9D6BEAC51',NULL,NULL),
	('service.name','2023-09-26 09:45:26.000000','2023-09-26 09:45:26.000000','402881888abbf1f3018abbf1f6a00007','account',NULL,NULL);

/*!40000 ALTER TABLE `system_config` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `date_created` datetime(6) NOT NULL,
  `date_updated` datetime(6) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `email_masked` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `password_salt` varchar(255) DEFAULT NULL,
  `tenant_id` varchar(255) DEFAULT NULL,
  `token_expired` bit(1) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `contact_masked` varchar(255) DEFAULT NULL,
  `country_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `created_by`, `date_created`, `date_updated`, `email`, `email_masked`, `enabled`, `name`, `password`, `password_salt`, `tenant_id`, `token_expired`, `updated_by`, `contact`, `contact_masked`, `country_code`)
VALUES
	('402881888ac3ce6b018ac3ce6ffd000b',NULL,'2023-09-24 02:20:00.832000','2023-09-24 02:20:00.832000','Racanaa@r.com',NULL,b'0','Racanaa','$2a$10$WwAtMrMGMwXVyGhO4L2Uo.WsmHMHWr.y4Ehy4R.dv7/q4OgPsXX3e','NB~Kd1Ar^D9Xg7TX%Xh$7uWA*TK.Y(HV#dGVzdA==#NB~Kd1Ar^D9Xg7TX%Xh$7uWA*TK.Y(HV','402881888ac3ce6b018ac3ce6f730000',b'0',NULL,NULL,NULL,NULL),
	('402881888ac3ce6b018ac3ce7055000c',NULL,'2023-09-24 02:20:00.918000','2023-09-24 02:20:00.918000','Evolve_Studio@r.com',NULL,b'0','Evolve Studio','$2a$10$e/M6y9.ABgj4AZhmrCcDUue3jAmHOKexe89OugCmakZGeRXu3IX5y','NB~Kd1Ar^D9Xg7TX%Xh$7uWA*TK.Y(HV#dGVzdA==#NB~Kd1Ar^D9Xg7TX%Xh$7uWA*TK.Y(HV','402881888ac3ce6b018ac3ce6f810001',b'0',NULL,NULL,NULL,NULL),
	('402881888ac3ce6b018ac3ce70aa000d',NULL,'2023-09-24 02:20:01.003000','2023-09-24 02:20:01.003000','Carbon_Happy@r.com',NULL,b'0','Carbon Happy','$2a$10$DEJA23G4WQp/P.vfmPRa..NDOthq.92Ql9RG.vk3Wrce0DcZ1jAJy','NB~Kd1Ar^D9Xg7TX%Xh$7uWA*TK.Y(HV#dGVzdA==#NB~Kd1Ar^D9Xg7TX%Xh$7uWA*TK.Y(HV','402881888ac3ce6b018ac3ce6f840002',b'0',NULL,NULL,NULL,NULL),
	('402881888ac3ce6b018ac3ce7100000e',NULL,'2023-09-24 02:20:01.088000','2023-09-24 02:20:01.088000','Beans_Coffee@r.com',NULL,b'0','Beans Coffee','$2a$10$d4QtKH/8USgTTpgGiiogwOZpZMJcZCROwrQZyggkhkgupzlJwC2W2','NB~Kd1Ar^D9Xg7TX%Xh$7uWA*TK.Y(HV#dGVzdA==#NB~Kd1Ar^D9Xg7TX%Xh$7uWA*TK.Y(HV','402881888ac3ce6b018ac3ce6f860003',b'0',NULL,NULL,NULL,NULL),
	('ff8080818acfd063018acfd2b06e0000',NULL,'2023-09-26 10:20:06.069000','2023-09-26 10:20:06.069000','manii@n.com',NULL,b'0','manii',NULL,NULL,NULL,b'0',NULL,NULL,NULL,NULL),
	('ff8080818acfd48d018acfd4a2e10000',NULL,'2023-09-26 10:22:13.672000','2023-09-26 10:22:13.672000','manii@n.com',NULL,b'0','manii',NULL,NULL,NULL,b'0',NULL,NULL,NULL,NULL),
	('ff8080818ad274c5018ad278667b0000',NULL,'2023-09-26 22:40:20.546000','2023-09-26 22:40:20.546000','manii@n.com',NULL,b'0','manii',NULL,NULL,NULL,b'0',NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_preference
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_preference`;

CREATE TABLE `user_preference` (
  `id` varchar(255) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `date_created` datetime(6) NOT NULL,
  `date_updated` datetime(6) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `tenant_id` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `user_preference` WRITE;
/*!40000 ALTER TABLE `user_preference` DISABLE KEYS */;

INSERT INTO `user_preference` (`id`, `created_by`, `date_created`, `date_updated`, `name`, `tenant_id`, `updated_by`, `user_id`, `value`)
VALUES
	('402882328ad6dbb5018ad6e1002f0000',NULL,'2023-09-27 19:13:04.518000','2023-09-27 19:13:04.518000','temperature',NULL,NULL,NULL,'randomm'),
	('ff8080818ad6c59f018ad6c66fb80000',NULL,'2023-09-27 18:44:03.583000','2023-09-27 19:13:04.528000','temperature','402881888ac3ce6b018ac3ce6f730000',NULL,'402881888ac3ce6b018ac3ce6ffd000b','randomm'),
	('ff8080818ad6c8c1018ad6c920b70000',NULL,'2023-09-27 18:46:59.966000','2023-09-27 18:46:59.966000','energy','402881888ac3ce6b018ac3ce6f730000',NULL,'402881888ac3ce6b018ac3ce6ffd000b','kvh'),
	('ff8080818ad6ca6f018ad6caa0120000','402881888ac3ce6b018ac3ce6ffd000b','2023-09-27 18:48:38.104000','2023-09-27 18:48:38.104000','temperature','402881888ac3ce6b018ac3ce6f730000','402881888ac3ce6b018ac3ce6ffd000b','402881888ac3ce6b018ac3ce6ffd000b','celcius'),
	('ff8080818ad6d3c0018ad6d8bf5f0000',NULL,'2023-09-27 19:04:03.624000','2023-09-27 19:04:03.624000',NULL,NULL,NULL,NULL,'randomm');

/*!40000 ALTER TABLE `user_preference` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table users_roles
# ------------------------------------------------------------

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
  `user_id` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`),
  KEY `FKgd3iendaoyh04b95ykqise6qh` (`user_id`),
  CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;

INSERT INTO `users_roles` (`user_id`, `role_id`)
VALUES
	('402881888ac3ce6b018ac3ce6ffd000b','402881888ac3ce6b018ac3ce6f950008'),
	('402881888ac3ce6b018ac3ce7055000c','402881888ac3ce6b018ac3ce6fa3000a'),
	('402881888ac3ce6b018ac3ce70aa000d','402881888ac3ce6b018ac3ce6fa00009'),
	('402881888ac3ce6b018ac3ce70aa000d','402881888ac3ce6b018ac3ce6fa3000a'),
	('402881888ac3ce6b018ac3ce7100000e','402881888ac3ce6b018ac3ce6fa3000a');

/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
