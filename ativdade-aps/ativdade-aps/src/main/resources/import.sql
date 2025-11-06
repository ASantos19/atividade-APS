-- ========================
-- CURSOS
-- ========================
INSERT INTO curso (id, nome, codigo_curso, especialidade) VALUES (1, 'Ciência da Computação', 'CC001', 'Tecnologia da Informação');
INSERT INTO curso (id, nome, codigo_curso, especialidade) VALUES (2, 'Engenharia de Software', 'ES002', 'Desenvolvimento de Software');

-- ========================
-- PROFESSORES
-- ========================
INSERT INTO professor (id, nome, especialidade) VALUES (1, 'Carlos Almeida', 'Banco de Dados');
INSERT INTO professor (id, nome, especialidade) VALUES (2, 'Mariana Silva', 'Desenvolvimento Web');

-- ========================
-- TURMAS
-- ========================
INSERT INTO turma (id, codigoTurma, horario, curso_id, professor_id) VALUES (1, 'TURMA001', '08:00-10:00', 1, 1);
INSERT INTO turma (id, codigoTurma, horario, curso_id, professor_id) VALUES (2, 'TURMA002', '10:00-12:00', 2, 2);

-- ========================
-- ESTUDANTES
-- ========================
INSERT INTO estudante (id, nome, matricula) VALUES (1, 'Ana Souza', 2025001);
INSERT INTO estudante (id, nome, matricula) VALUES (2, 'Bruno Lima', 2025002);
INSERT INTO estudante (id, nome, matricula) VALUES (3, 'Carla Mendes', 2025003);
INSERT INTO estudante (id, nome, matricula) VALUES (4, 'Diego Costa', 2025004);
INSERT INTO estudante (id, nome, matricula) VALUES (5, 'Eduarda Nunes', 2025005);
INSERT INTO estudante (id, nome, matricula) VALUES (6, 'Felipe Santos', 2025006);
INSERT INTO estudante (id, nome, matricula) VALUES (7, 'Gabriela Rocha', 2025007);
INSERT INTO estudante (id, nome, matricula) VALUES (8, 'Henrique Oliveira', 2025008);
INSERT INTO estudante (id, nome, matricula) VALUES (9, 'Isabela Castro', 2025009);
INSERT INTO estudante (id, nome, matricula) VALUES (10, 'João Vitor', 2025010);

-- ========================
-- MATRÍCULAS DE ESTUDANTES EM TURMAS (Tabela de Junção)
-- ========================
-- 5 estudantes na Turma 1
INSERT INTO turma_estudante (turma_id, estudante_id) VALUES (1, 1);
INSERT INTO turma_estudante (turma_id, estudante_id) VALUES (1, 2);
INSERT INTO turma_estudante (turma_id, estudante_id) VALUES (1, 3);
INSERT INTO turma_estudante (turma_id, estudante_id) VALUES (1, 4);
INSERT INTO turma_estudante (turma_id, estudante_id) VALUES (1, 5);

-- 5 estudantes na Turma 2
INSERT INTO turma_estudante (turma_id, estudante_id) VALUES (2, 6);
INSERT INTO turma_estudante (turma_id, estudante_id) VALUES (2, 7);
INSERT INTO turma_estudante (turma_id, estudante_id) VALUES (2, 8);
INSERT INTO turma_estudante (turma_id, estudante_id) VALUES (2, 9);
INSERT INTO turma_estudante (turma_id, estudante_id) VALUES (2, 10);
