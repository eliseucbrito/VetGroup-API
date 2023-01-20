CREATE TABLE `tb_reports` (
  `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `approved` bit(1) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `title` varchar(70) NOT NULL,
  `type` int NOT NULL,
  `staff_id` bigint DEFAULT NULL
)