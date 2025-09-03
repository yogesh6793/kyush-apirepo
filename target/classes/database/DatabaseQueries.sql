Create Database kyush_db;

CREATE TABLE `applicant` (
  `applicant_id` int NOT NULL AUTO_INCREMENT,
  `applicant_name` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `linkedin` varchar(255) DEFAULT NULL,
  `how_hear` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `current_ctc` varchar(255) DEFAULT NULL,
  `expected_ctc` varchar(255) DEFAULT NULL,
  `notice_period` varchar(255) DEFAULT NULL,
  `resume` varchar(255) DEFAULT NULL,
  `message` varchar(10000) DEFAULT NULL,
  `declaration` tinyint DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`applicant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `contact_us` (
  `contact_us_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `message` varchar(1000) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`contact_us_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb3;


CREATE TABLE `job_application` (
  `job_application_id` int NOT NULL AUTO_INCREMENT,
  `job_board_id` int DEFAULT NULL,
  `applicant_id` int DEFAULT NULL,
  `job_status` varchar(100) DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `modified_by` int DEFAULT NULL,
  `modified_at` datetime DEFAULT NULL,
  PRIMARY KEY (`job_application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `job_board` (
  `job_board_id` int NOT NULL AUTO_INCREMENT,
  `job_title` varchar(255) DEFAULT NULL,
  `job_description` varchar(10000) DEFAULT NULL,
  `location` varchar(1000) DEFAULT NULL,
  `experience` varchar(1000) DEFAULT NULL,
  `qualification` varchar(1000) DEFAULT NULL,
  `skills` varchar(1000) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `modified_by` int DEFAULT NULL,
  `modified_at` datetime DEFAULT NULL,
  PRIMARY KEY (`job_board_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `job_status` (
  `job_status_id` int NOT NULL AUTO_INCREMENT,
  `job_status` varchar(255) NOT NULL,
  `message` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`job_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) NOT NULL,
  `role_description` varchar(255) DEFAULT NULL,
  `role_status` tinyint(1) NOT NULL DEFAULT '1',
  `created_by` int NOT NULL,
  `created_at` datetime NOT NULL,
  `modified_by` int DEFAULT NULL,
  `modified_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `unique_role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8mb3;


CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `password` text NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role_id` int NOT NULL,
  `status` tinyint NOT NULL DEFAULT '0',
  `login_otp` int DEFAULT NULL,
  `otp_generated_at` datetime DEFAULT NULL,
  `created_by` int NOT NULL,
  `created_at` datetime NOT NULL,
  `modified_by` int DEFAULT NULL,
  `modified_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `user_role` (
  `userrole_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  `created_by` int NOT NULL,
  `created_at` datetime NOT NULL,
  `modified_by` int DEFAULT NULL,
  `modified_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`userrole_id`),
  KEY `FK_user_role_1` (`user_id`),
  KEY `FK_user_role_2` (`role_id`),
  CONSTRAINT `FK_user_role_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FK_user_role_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=265 DEFAULT CHARSET=utf8mb3;
