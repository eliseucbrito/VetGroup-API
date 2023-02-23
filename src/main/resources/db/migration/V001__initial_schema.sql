CREATE TABLE `tb_staff` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `avatar_url` varchar(255) DEFAULT NULL,
  `base_salary` int DEFAULT NULL,
  `cpf` varchar(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(70) NOT NULL,
  `on_duty` bit(1) DEFAULT NULL,
  `role` int NOT NULL,
  `weekly_work_load` int DEFAULT NULL,
  `work_load_completed` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6gpyore2l3ir2ui0jxum3ejyt` (`cpf`),
  UNIQUE KEY `UK_hfjwdqubb4nlamvw0h5jeqoe9` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_patients` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `avatar_url` varchar(255) NOT NULL,
  `birth_date` datetime(6) NOT NULL,
  `breed` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `kind` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `owner` varchar(255) NOT NULL,
  `owner_contact` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_service` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city` int NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `description` text NOT NULL,
  `price` int DEFAULT NULL,
  `status` int NOT NULL,
  `type` int NOT NULL,
  `patient_id` bigint DEFAULT NULL,
  `staff_id` bigint DEFAULT NULL,
  `service_date` datetime(6) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_service_patient_id_patients_id` (`patient_id`),
  KEY `fk_service_staff_id_staff_id` (`staff_id`),
  CONSTRAINT `fk_service_staff_id_staff_id` FOREIGN KEY (`staff_id`) REFERENCES `tb_staff` (`id`),
  CONSTRAINT `fk_service_patient_id_patients_id` FOREIGN KEY (`patient_id`) REFERENCES `tb_patients` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_reports` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `approved` bit(1) DEFAULT NULL,
  `approved_by` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `payment_value` int DEFAULT NULL,
  `title` varchar(70) NOT NULL,
  `type` int NOT NULL,
  `staff_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reports_staff_id_staff_id` (`staff_id`),
  CONSTRAINT `fk_reports_staff_id_staff_id` FOREIGN KEY (`staff_id`) REFERENCES `tb_staff` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_role_historic` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `base_salary` int NOT NULL,
  `role` int NOT NULL,
  `started_in` datetime(6) NOT NULL,
  `weekly_work_load` int DEFAULT NULL,
  `promoted_by` bigint DEFAULT NULL,
  `staff_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_role_historic_promoted_by_staff_id` (`promoted_by`),
  KEY `fk_role_historic_staff_id_staff_id` (`staff_id`),
  CONSTRAINT `fk_role_historic_promoted_by_staff_id` FOREIGN KEY (`promoted_by`) REFERENCES `tb_staff` (`id`),
  CONSTRAINT `fk_role_historic_staff_id_staff_id` FOREIGN KEY (`staff_id`) REFERENCES `tb_staff` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_rooms` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `in_use` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` int NOT NULL,
  `staff_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o19w49e4hij38la1677iwlw4i` (`name`),
  KEY `fk_rooms_staff_id_staff_id` (`staff_id`),
  CONSTRAINT `fk_rooms_staff_id_staff_id` FOREIGN KEY (`staff_id`) REFERENCES `tb_staff` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_room_last_access` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `accessed_at` datetime(6) DEFAULT NULL,
  `room_id` bigint DEFAULT NULL,
  `staff_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_room_last_access_room_id_rooms_id` (`room_id`),
  KEY `fk_room_last_access_staff_id_staff_id` (`staff_id`),
  CONSTRAINT `fk_room_last_access_staff_id_staff_id` FOREIGN KEY (`staff_id`) REFERENCES `tb_staff` (`id`),
  CONSTRAINT `fk_room_last_access_room_id_rooms_id` FOREIGN KEY (`room_id`) REFERENCES `tb_rooms` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



