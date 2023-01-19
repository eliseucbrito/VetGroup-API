DROP TABLE IF EXISTS `tb_rooms`;
CREATE TABLE `tb_rooms` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `in_use` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` int NOT NULL,
  `staff_id` bigint DEFAULT NULL
)