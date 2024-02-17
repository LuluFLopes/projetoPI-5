INSERT INTO ESPECIALIZACAO (DESCRICAO, STATUS)
VALUES ('Clínico Geral', 0),
       ('Cardiologista', 0),
       ('Oftamologista', 0);


INSERT INTO ENDERECO (LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, CIDADE, CEP, UF)
VALUES ('Rua Andréa Bernasconi', 1, 'Box 1', 'Jardim das Palmeiras', 'São Paulo', '04870-170', 24),
       ('Rua Manuel Borges de Sousa', 2, '', 'Jardim Leme', 'São Paulo', '05790-330', 24),
       ('Rua Ivaí', 3, '', 'Tatuapé', 'São Paulo', '03080-900', 24),
       ('Rua Diogo Braga', 4, '', 'Jardim Primavera', 'São Paulo', '04812-160', 24),
       ('Estrada do Barro Branco', 5, '', 'Jardim Lucélia', 'São Paulo', '04852-220', 24),
       ('Rua Isanga', 6, '', 'Vila América', 'São Paulo', '02928-040', 24),
       ('Rua São Raimundo', 7, '', 'Vila Califórnia', 'São Paulo', '03216-040', 24),
       ('Rua José Francisco Chaves', 7, '', 'Jardim Guanhembu', 'São Paulo', '04814-450', 24),
       ('Rua da Despedida', 9, '', 'Vila dos Remédios', 'São Paulo', '05102-050', 24);


INSERT INTO UNIDADE (DESCRICAO, STATUS, ENDERECO_ID)
VALUES ('Unidade Santo Amaro', 0, 1),
       ('Unidade Penha', 0, 2),
       ('Unidade Morumbi', 0, 3),
       ('Unidade Pinheiros', 0, 4);


INSERT INTO LOGIN (SENHA, USUARIO)
VALUES ('$2a$10$cIZ1krIsXzG6rfpxC/hSc.D35fshC5FXNpkocrwl0A4KrwJfE3OjS', 'luciano.lopes'),
       ('$2a$10$6YU7Bta1M5TtiyfsXZe3geL9/p734y5qiPjFlVuvdcPQR2In7r3uK', 'stevart.guerra'),
       ('$2a$10$eAt6ErDstG4u7mfsIt4O1.6WSHhIo9beu3pKgZ960ziI9PsvJjm.q', 'vitor.lucats'),
       ('$2a$10$dU4J3fjWL8grNJe0gkYrWuJcS1r.n/rMAb9zhzEyb9H6YkAKnvKiq', 'fernando.martiniano');


INSERT INTO CONTATO (EMAIL, TELEFONE)
VALUES ('luciano.lopes@gmail.com.br', '(11) 92458-7562'),
       ('stevart.guerra@gmail.com.br', '(11) 94585-1597'),
       ('vitor.lucats@gmail.com.br', '(11) 94512-9742'),
       ('fernando.martiniano@gmail.com.br', '(11) 99742-5147');


INSERT INTO PESSOA (CARGO, CONTATO_ID, DATA_NASCIMENTO, ENDERECO_ID, ESPECIALIZACAO_ID, GENERO, LOGIN_ID, STATUS,
                    UNIDADE_ID, CPF, CRM, NOME, DTYPE, TIPO_CADASTRO, NOME_FOTO)
VALUES (0, 1, '1990-10-10', 1, null, 0, 1, 0, 1, '147.228.350-39', 'null', 'Luciano Freire', 'Funcionario', 0, 'doc-gen-img.png'),
       (1, 2, '1990-10-10', 2, null, 1, 2, 0, 2, '239.812.690-04', null, 'José Stevart', 'Funcionario', 1, 'doc-gen-img.png'),
       (null, 3, '1990-10-10', 3, null, 0, 3, 0, 3, '804.635.770-78', null, 'Vitor Carlos', 'Paciente', 3, 'doc-gen-img.png'),
       (null, 4, '1990-10-10', 4, 1, 0, 4, 0, 4, '508.868.960-88', 111111, 'Fernando Martiniano', 'Medico', 2, 'doc-gen-img.png');

INSERT INTO AGENDAMENTO (ANDAMENTO, DATA_AGENDAMENTO, HORA_INICIO, HORA_TERMINO, MEDICO_ID, PACIENTE_ID, UNIDADE_ID)
VALUES (0, '2023-12-03', '09:00', '10:00', 4, 3, 1),
       (0, '2023-12-03', '13:00', '14:00', 4, 3, 2),
       (0, '2023-12-03', '17:00', '18:00', 4, 3, 3),
       (0, '2023-12-03', '08:00', '09:00', 4, 3, 4),
       (0, '2023-12-03', '16:00', '17:00', 4, 3, 1),
       (0, '2023-12-03', '11:00', '12:00', 4, 3, 2),
       (0, '2023-12-03', '15:00', '16:00', 4, 3, 3),
       (0, '2023-12-03', '10:00', '11:00', 4, 3, 4),
       (0, '2023-12-03', '14:00', '15:00', 4, 3, 1),
       (0, '2023-12-03', '12:00', '11:00', 4, 3, 4);