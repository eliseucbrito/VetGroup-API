CREATE TABLE `tb_role_historic` (
  `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `base_salary` int DEFAULT NULL,
  `started_in` datetime(6) DEFAULT NULL,
  `role` int NOT NULL,
  `weekly_work_load` int,
  `promoted_by` bigint DEFAULT NULL,
  `staff_id` bigint DEFAULT NULL
)