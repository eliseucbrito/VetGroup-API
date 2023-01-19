DROP TABLE IF EXISTS `tb_reports`;
CREATE TABLE `tb_reports` (
  `id` bigint NOT NULL,
  `approved` bit(1) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `title` varchar(70) NOT NULL,
  `type` int NOT NULL,
  `staff_id` bigint DEFAULT NULL
)