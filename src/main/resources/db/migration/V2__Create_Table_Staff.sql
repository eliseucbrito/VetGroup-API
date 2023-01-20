CREATE TABLE `tb_staff` (
  `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `avatar_url` varchar(255) DEFAULT NULL,
  `base_salary` double DEFAULT NULL,
  `cpf` varchar(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(70) NOT NULL,
  `on_duty` bit(1) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `staff_role` int NOT NULL
)