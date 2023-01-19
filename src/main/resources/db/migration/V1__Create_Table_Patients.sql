DROP TABLE IF EXISTS `tb_patients`;
CREATE TABLE `tb_patients` (
  `id` bigint NOT NULL,
  `avatar_url` varchar(255) NOT NULL,
  `birth_date` datetime(6) NOT NULL,
  `breed` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `kind` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `owner` varchar(255) NOT NULL,
  `owner_contact` varchar(255) NOT NULL
)