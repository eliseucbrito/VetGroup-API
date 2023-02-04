INSERT INTO `tb_staff` (`id`, `avatar_url`, `base_salary`, `cpf`, `created_at`, `email`, `full_name`, `on_duty`, `role`, `weekly_work_load`, `work_load_completed`) VALUES
(1, 'https://github.com/eliseubrito7z.png', 19000, '23748672420', '2023-01-10 18:39:38.866183', 'eliseu@gmail.com', 'Eliseu Cordeiro de Brito', b'1', 1, 2400, NULL),
(2, 'https://cdn.mindminers.com/blog/uploads/2021/05/Dani-Almeida_auto_x1.png', 9000000, '06441890453', '2023-01-10 18:40:12.290797', 'fabianaLA@gmail.com', 'Fabiana Larissa Araújo', b'1', 2, 2400, NULL),
(4, 'https://i.pinimg.com/564x/b1/97/eb/b197ebb9583b2bf0576996464b39ed3a.jpg', 8000, '26914471460', '2023-01-10 18:40:55.856525', 'gabrielCC@gmail.com', 'Gabriel Calebe Carvalho', b'0', 4, NULL, NULL),
(5, 'https://i.pinimg.com/564x/ee/ad/cd/eeadcd7204257f3ee1e339f5b0f16d8c.jpg', 8000, '47427204409', '2023-01-10 18:41:28.425029', 'eduardaLJ@gmail.com', 'Eduarda Luna Jéssica', b'1', 4, NULL, NULL),
(6, 'https://i.pinimg.com/564x/1a/c9/fb/1ac9fb7fa0f880278dc8071d502f86ee.jpg', 8000, '65089604401', '2023-01-10 18:41:56.814378', 'daniloCVC@gmail.com', 'Danilo Carlos Vitor Carvalho', b'1', 4, NULL, NULL);

INSERT INTO `tb_patients` (`id`, `avatar_url`, `birth_date`, `breed`, `created_at`, `kind`, `name`, `owner`, `owner_contact`) VALUES
(1, 'https://source.unsplash.com/random', '2018-09-28 00:00:00.000000', 'Poodle', '2023-01-17 15:37:50.227241', 2, 'diggle', 'Eliseu Brito', '87999999999'),
(2, 'https://unsplash.com/random', '2018-09-28 00:00:00.000000', 'Poodle', '2023-01-20 02:13:00.279915', 2, 'diggle', 'Eliseu Brito', '87999999999');

INSERT INTO `tb_reports` (`id`, `approved`, `created_at`, `description`, `payment_value`, `title`, `type`, `staff_id`) VALUES
(1, NULL, '2023-01-25 21:09:42.361914', 'Prezados, solicitamos informar o valor investido, por ano, pelo Ministério da Saúde na divulgação de campanhas nacionais de vacinação de 2010 a 2020', NULL, 'Pagamento de um lote de vacinas para cachorro', 2, 1),
(2, NULL, '2023-01-25 21:11:49.537159', 'Prezados, solicitamos informar o valor investido, por ano, pelo Ministério da Saúde na divulgação de campanhas nacionais de vacinação de 2010 a 2020', NULL, 'Pagamento de um lote de vacinas para cachorro', 2, 1),
(3, NULL, '2023-01-26 00:48:57.843444', 'Prezados, solicitamos informar o valor investido, por ano, pelo Ministério da Saúde na divulgação de campanhas nacionais de vacinação de 2010 a 2020', NULL, 'Pagamento de um lote de vacinas para cachorro', 2, 1),
(4, NULL, '2023-01-26 00:48:59.005223', 'Prezados, solicitamos informar o valor investido, por ano, pelo Ministério da Saúde na divulgação de campanhas nacionais de vacinação de 2010 a 2020', NULL, 'Pagamento de um lote de vacinas para cachorro', 2, 1),
(5, NULL, '2023-01-26 00:48:59.673987', 'Prezados, solicitamos informar o valor investido, por ano, pelo Ministério da Saúde na divulgação de campanhas nacionais de vacinação de 2010 a 2020', NULL, 'Pagamento de um lote de vacinas para cachorro', 2, 1);

INSERT INTO `tb_role_historic` (`id`, `base_salary`, `role`, `started_in`, `weekly_work_load`, `promoted_by`, `staff_id`) VALUES
(1, 1300000, 5, '2020-02-20 09:00:00.026747', 1200, 1, 2),
(2, 3200000, 4, '2020-11-12 09:00:00.026747', 2400, 1, 2),
(3, 6000000, 3, '2021-12-25 09:00:00.026747', 2400, 1, 2),
(4, 9000000, 2, '2023-01-10 09:00:00.026747', 2400, 1, 2);

INSERT INTO `tb_rooms` (`id`, `created_at`, `in_use`, `name`, `type`, `staff_id`) VALUES
(1, '2023-01-26 15:40:20.682735', b'0', 'Consultório 1', 1, NULL);

INSERT INTO `tb_service` (`id`, `city`, `created_at`, `description`, `price`, `status`, `type`, `patient_id`, `staff_id`, `service_date`, `title`) VALUES
(1, 1, '2023-01-25 21:10:37.122670', 'CIRURGIA MARCADA PARA O PACIENTE XXXX NO DIA XX/XX/XXXX', 1000, 6, 4, 1, 2, NULL, ''),
(2, 1, '2023-01-25 22:17:33.771909', '222 CIRURGIA MARCADA PARA O PACIENTE XXXX NO DIA XX/XX/XXXX', 1000, 6, 4, 1, 2, '2023-01-29 23:34:32.507728', ''),
(3, 1, '2023-01-26 21:42:26.491055', 'TESTE DATA TESTE DATA TESTE DATA TESTE DATA', 100000, 3, 4, 2, 2, '2023-01-26 21:42:26.491055', 'title for insert in db'),
(4, 1, '2023-01-26 21:43:10.408893', 'TESTE DATA TESTE DATA TESTE DATA TESTE DATA', 100000, 3, 4, 2, 2, '2023-01-26 21:43:10.408893', 'title for insert in db'),
(5, 1, '2023-01-26 21:43:58.297725', 'TESTE DATA TESTE DATA TESTE DATA TESTE DATA', 100000, 3, 4, 2, 2, '2023-01-26 21:43:58.297725', 'title for insert in db'),
(6, 1, '2023-01-26 21:44:33.300254', 'TESTE DATA TESTE DATA TESTE DATA TESTE DATA', 100000, 3, 4, 2, 2, '2023-01-26 21:44:33.300254', 'title for insert in db'),
(7, 1, '2023-01-26 21:45:11.014766', 'TESTE DATA TESTE DATA TESTE DATA TESTE DATA', 100000, 3, 4, 2, 2, '2023-01-26 21:45:11.014766', 'title for insert in db'),
(8, 1, '2023-01-26 21:47:32.096132', 'TESTE DATA TESTE DATA TESTE DATA TESTE DATA', 100000, 3, 4, 2, 1, '2023-01-26 21:47:32.096132', 'title for insert in db'),
(9, 1, '2023-01-26 21:48:34.312245', 'TESTE DATA TESTE DATA TESTE DATA TESTE DATA', 100000, 1, 4, 2, 1, '2023-01-26 21:39:00.000000', 'title for insert in db'),
(10, 1, '2023-01-26 21:50:36.133405', 'TESTE DATA TESTE DATA TESTE DATA TESTE DATA', 100000, 1, 4, 2, 1, '2023-01-26 21:39:00.000000', 'title for insert in db'),
(11, 1, '2023-01-28 14:38:26.044424', 'Atendimento cachorro com febre', 100000, 2, 1, 1, 1, '2023-01-28 14:38:26.044424', 'title for insert in db'),
(12, 1, '2023-01-28 14:39:05.271106', 'Exame geral com banho e tosa', 500000, 6, 1, 1, 1, '2023-01-28 14:39:05.271106', 'title for insert in db'),
(13, 1, '2023-01-28 15:33:02.186396', 'TESTE DATA TESTE DATA TESTE DATA TESTE DATA', 100000, 1, 4, 2, 1, '2023-01-26 21:39:00.000000', 'title for insert in db'),
(14, 1, '2023-01-28 15:34:35.809899', 'TESTE SERVICO AGENDADO COM DATA', 100000, 1, 1, 1, 5, '2023-01-29 15:00:00.000000', 'title for insert in db'),
(15, 3, '2023-01-29 23:34:32.507728', 'TESTE UPDATE description 2', 1000000, 3, 1, 1, 1, '2023-01-31 23:31:00.000000', 'Não come a 2 dias e chora'),
(16, 1, '2023-01-29 23:44:05.156996', 'Teste de atualização da descrição pelo frontend DNV 9', 1000000, 6, 1, 1, 1, '2023-01-29 23:44:05.156996', 'Não come a 3 dias');
