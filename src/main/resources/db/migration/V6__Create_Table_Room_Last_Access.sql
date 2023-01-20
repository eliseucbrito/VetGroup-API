CREATE TABLE `tb_room_last_access` (
  `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `accessed_at` datetime(6) DEFAULT NULL,
  `room_id` bigint DEFAULT NULL,
  `staff_id` bigint DEFAULT NULL
)