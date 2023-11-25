INSERT INTO ESPECIALIZACAO (DESCRICAO, STATUS) 
VALUES 
('Clínico Geral', 0),
('Cardiologista', 0),
('Oftamologista', 0);


INSERT INTO ENDERECO (LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, CIDADE, CEP, UF) 
VALUES 
('Rua Teste', 1, 'Complemento Teste1', 'Vila Teste', 'São Paulo', '11111-111', 24),
('Rua Teste2', 2, 'Complemento Teste2', 'Vila Teste2', 'São Paulo', '22222-222', 24),
('Rua Teste3', 3, 'Complemento Teste3', 'Vila Teste3', 'São Paulo', '33333-333', 24),
('Rua Teste4', 4, 'Complemento Teste4', 'Vila Teste4', 'São Paulo', '44444-444', 24),
('Rua Teste5', 5, 'Complemento Teste5', 'Vila Teste5', 'São Paulo', '55555-555', 24),
('Rua Teste6', 6, 'Complemento Teste6', 'Vila Teste6', 'São Paulo', '66666-666', 24),
('Rua Teste7', 7, 'Complemento Teste7', 'Vila Teste7', 'São Paulo', '77777-777', 24),
('Rua Teste8', 7, 'Complemento Teste8', 'Vila Teste8', 'São Paulo', '88888-888', 24),
('Rua Teste9', 9, 'Complemento Teste9', 'Vila Teste9', 'São Paulo', '99999-999', 24);


INSERT INTO UNIDADE (DESCRICAO, STATUS, ENDERECO_ID)
VALUES 
('Unidade Santo Amaro', 0, 1),
('Unidade Penha', 0, 2),
('Unidade Morumbi', 0, 3),
('Unidade Pinheiros', 0, 4);


INSERT INTO LOGIN (SENHA, USUARIO)
VALUES 
('$2a$10$5DkTh9Vdg9etHZig.1w1puvxHCtYNDto8CwJT6nNvlcDX1fyIoXi2', 'usuario.teste1'),
('$2a$10$5DkTh9Vdg9etHZig.1w1puvxHCtYNDto8CwJT6nNvlcDX1fyIoXi2', 'usuario.teste2'),
('$2a$10$5DkTh9Vdg9etHZig.1w1puvxHCtYNDto8CwJT6nNvlcDX1fyIoXi2', 'usuario.teste3'),
('$2a$10$5DkTh9Vdg9etHZig.1w1puvxHCtYNDto8CwJT6nNvlcDX1fyIoXi2', 'usuario.teste4');


INSERT INTO CONTATO (EMAIL, TELEFONE)
VALUES
('email.teste1@teste.com.br', '1111111-1111'),
('email.teste2@teste.com.br', '2222222-2222'),
('email.teste3@teste.com.br','3333333-3333'),
('email.teste4@teste.com.br','4444444-4444');


INSERT INTO PESSOA (CARGO, CONTATO_ID, DATA_NASCIMENTO, ENDERECO_ID, ESPECIALIZACAO_ID, GENERO, LOGIN_ID, STATUS, UNIDADE_ID, CPF, CRM, NOME, DTYPE)
VALUES
(0, 1, '1990-10-10', 1, null, 0, 1, 0, 1, '147.228.350-39', 'null', 'Adminsitrador Teste', 'Funcionario'),
(1, 2, '1990-10-10', 2, null, 1, 2, 0, 2, '239.812.690-04', null, 'Atendente Teste', 'Funcionario'),
(null, 3, '1990-10-10', 3, null, 0, 3, 0, 3, '804.635.770-78', null, 'Paciente Teste', 'Paciente'),
(null, 4, '1990-10-10', 4, 1, 0, 4, 0, 4, '508.868.960-88', 111111, 'Medico Teste', 'Medico');

INSERT INTO AGENDAMENTO (ANDAMENTO, DATA_AGENDAMENTO, HORA_INICIO, HORA_TERMINO, MEDICO_ID, PACIENTE_ID, UNIDADE_ID)
VALUES
    (0, '2023-01-10', '09:00', '10:00', 4, 3, 1),
    (0, '2023-01-10', '13:00', '14:00', 4, 3, 2),
    (0, '2023-01-10', '17:00', '18:00', 4, 3, 3),
    (0, '2023-01-11', '08:00', '09:00', 4, 3, 4),
    (0, '2023-01-11', '16:00', '17:00', 4, 3, 1),
    (0, '2023-01-11', '11:00', '12:00', 4, 3, 2),
    (0, '2023-01-12', '15:00', '16:00', 4, 3, 3),
    (0, '2023-01-12', '10:00', '11:00', 4, 3, 4),
    (0, '2023-01-12', '14:00', '15:00', 4, 3, 1),
    (0, '2023-01-12', '13:00', '10:00', 4, 3, 4);