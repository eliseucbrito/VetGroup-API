INSERT INTO `tb_staff` (`id`, `avatar_url`, `base_salary`, `cpf`, `created_at`, `email`, `full_name`, `on_duty`, `role`, `weekly_work_load`, `work_load_completed`) VALUES
(1, 'https://i.pinimg.com/564x/00/28/f4/0028f47df0895761a4dcc6563619a1c1.jpg', 20000000, '08854006076', '2023-02-23 16:54:04.320234', 'eliseubrito@gmail.com', 'Eliseu Britol', b'1', 1, 2400, NULL),
(8, 'https://i.pinimg.com/564x/00/28/f4/0028f47df0895761a4dcc6563619a1c1.jpg', 20000000, '06441890453', '2023-02-27 10:55:23.508569', 'fabianaLA@gmail.com', 'Fabiana Larissa Araújo', b'0', 1, 2400, NULL),
(9, 'https://i.pinimg.com/564x/00/28/f4/0028f47df0895761a4dcc6563619a1c1.jpg', 20000000, '26914471460', '2023-02-27 11:03:11.724379', 'gabrielCC@gmail.com', 'Gabriel Calebe Carvalho', b'0', 1, 2400, NULL),
(10, 'https://i.pinimg.com/564x/00/28/f4/0028f47df0895761a4dcc6563619a1c1.jpg', 20000000, '47427204409', '2023-02-27 11:05:39.901909', 'eduardaLJ@gmail.com', 'Eduarda Luna Jéssica', b'0', 1, 2400, NULL),
(11, 'https://i.pinimg.com/564x/00/28/f4/0028f47df0895761a4dcc6563619a1c1.jpg', 20000000, '65089604401', '2023-02-27 11:06:34.448754', 'daniloCVC@gmail.com', 'Danilo Carlos Vitor Carvalho', b'0', 1, 2400, NULL);

INSERT INTO `tb_patients` (`id`, `avatar_url`, `birth_date`, `breed`, `created_at`, `kind`, `name`, `owner`, `owner_contact`) VALUES
(1, 'https://source.unsplash.com/random', '2018-09-28 00:00:00.000000', 'Poodle', '2023-01-17 15:37:50.227241', 2, 'diggle', 'Eliseu Brito', '87999999999'),
(2, 'https://unsplash.com/random', '2018-09-28 00:00:00.000000', 'Poodle', '2023-01-20 02:13:00.279915', 2, 'diggle', 'Eliseu Brito', '87999999999');

INSERT INTO `tb_role_historic` (`id`, `base_salary`, `role`, `started_in`, `weekly_work_load`, `promoted_by`, `staff_id`) VALUES
(15, 20000000, 1, '2023-02-27 10:55:23.508569', 2400, 1, 8),
(16, 20000000, 1, '2023-02-27 11:03:11.724379', 2400, 1, 9),
(17, 20000000, 1, '2023-02-27 11:05:39.901909', 2400, 1, 10),
(18, 20000000, 1, '2023-02-27 11:06:34.448754', 2400, 1, 11);

INSERT INTO `tb_rooms` (`id`, `created_at`, `in_use`, `name`, `type`, `staff_id`) VALUES
(1, '2023-01-26 15:40:20.682735', b'0', 'Consultório 1', 1, NULL);
