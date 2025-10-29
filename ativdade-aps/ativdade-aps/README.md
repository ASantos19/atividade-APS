# Sistema de Gerenciamento Universitário - Spring Boot API

## Descrição
API RESTful completa para gerenciamento universitário desenvolvida com Spring Boot 3.x+. O sistema gerencia Estudantes, Turmas, Professores e Cursos com relacionamentos JPA adequados.

## Tecnologias Utilizadas
- **Spring Boot 3.5.6**
- **Spring Data JPA**
- **H2 Database** (desenvolvimento)
- **Lombok**
- **Bean Validation**
- **Maven**

## Estrutura do Projeto

### Entidades e Relacionamentos

#### Estudante
- `id`: Integer (PK)
- `nome`: String (obrigatório, 2-100 caracteres)
- `matricula`: Integer (único, obrigatório)
- **Relacionamento**: ManyToMany com Turma

#### Turma
- `id`: Integer (PK)
- `codigoTurma`: String (único, obrigatório, 3-10 caracteres)
- `horario`: String (obrigatório, 5-20 caracteres)
- `curso`: Curso (ManyToOne)
- `professor`: Professor (ManyToOne)
- `estudantes`: List<Estudante> (ManyToMany)
- **Relacionamento**: ManyToOne com Curso e Professor, ManyToMany com Estudante

#### Professor
- `id`: Integer (PK)
- `nome`: String (obrigatório, 2-100 caracteres)
- `especialidade`: String (obrigatório, 2-50 caracteres)
- **Relacionamento**: OneToMany com Turma

#### Curso
- `id`: Integer (PK)
- `nome`: String (obrigatório, 2-100 caracteres)
- `codigoCurso`: String (único, obrigatório, 3-10 caracteres)
- `especialidade`: String (obrigatório, 2-50 caracteres)
- **Relacionamento**: OneToMany com Turma

## Endpoints da API

### Cursos (`/api/cursos`)
- `GET /api/cursos` - Lista todos os cursos
- `GET /api/cursos/{id}` - Busca curso por ID
- `GET /api/cursos/codigo/{codigoCurso}` - Busca curso por código
- `GET /api/cursos/buscar?nome={nome}` - Busca cursos por nome
- `POST /api/cursos` - Cria novo curso
- `PUT /api/cursos/{id}` - Atualiza curso existente
- `DELETE /api/cursos/{id}` - Exclui curso

### Professores (`/api/professores`)
- `GET /api/professores` - Lista todos os professores
- `GET /api/professores/{id}` - Busca professor por ID
- `GET /api/professores/especialidade/{especialidade}` - Busca por especialidade
- `GET /api/professores/buscar?nome={nome}` - Busca professores por nome
- `POST /api/professores` - Cria novo professor
- `PUT /api/professores/{id}` - Atualiza professor existente
- `DELETE /api/professores/{id}` - Exclui professor

### Turmas (`/api/turmas`)
- `GET /api/turmas` - Lista todas as turmas
- `GET /api/turmas/{id}` - Busca turma por ID
- `GET /api/turmas/codigo/{codigoTurma}` - Busca turma por código
- `GET /api/turmas/curso/{cursoId}` - Busca turmas por curso
- `GET /api/turmas/professor/{professorId}` - Busca turmas por professor
- `GET /api/turmas/horario/{horario}` - Busca turmas por horário
- `GET /api/turmas/com-estudantes` - Busca turmas com estudantes matriculados
- `GET /api/turmas/{id}/contar-estudantes` - Conta estudantes em uma turma
- `POST /api/turmas` - Cria nova turma
- `PUT /api/turmas/{id}` - Atualiza turma existente
- `DELETE /api/turmas/{id}` - Exclui turma

### Estudantes (`/api/estudantes`)
- `GET /api/estudantes` - Lista todos os estudantes
- `GET /api/estudantes/{id}` - Busca estudante por ID
- `GET /api/estudantes/matricula/{matricula}` - Busca estudante por matrícula
- `GET /api/estudantes/buscar?nome={nome}` - Busca estudantes por nome
- `GET /api/estudantes/turma/{turmaId}` - Busca estudantes de uma turma
- `GET /api/estudantes/sem-turma` - Busca estudantes sem turma
- `GET /api/estudantes/{id}/contar-turmas` - Conta turmas de um estudante
- `POST /api/estudantes` - Cria novo estudante
- `PUT /api/estudantes/{id}` - Atualiza estudante existente
- `DELETE /api/estudantes/{id}` - Exclui estudante

## Como Executar

### Pré-requisitos
- Java 21+
- Maven 3.6+

### Executando a Aplicação
```bash
# Compilar o projeto
./mvnw clean compile

# Executar os testes
./mvnw test

# Executar a aplicação
./mvnw spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`

### Console H2 Database
Acesse `http://localhost:8080/h2-console` para visualizar o banco de dados:
- **JDBC URL**: `jdbc:h2:mem:universidade`
- **Username**: `sa`
- **Password**: (vazio)

## Dados de Teste
O sistema já vem com dados de teste pré-carregados:
- **2 Cursos**: Ciência da Computação e Engenharia de Software
- **2 Professores**: Carlos Almeida e Mariana Silva
- **2 Turmas**: TURMA001 e TURMA002
- **10 Estudantes**: Ana Souza até João Vitor
- **Matrículas**: 5 estudantes em cada turma

## Validações Implementadas
- Campos obrigatórios com `@NotBlank` e `@NotNull`
- Tamanhos mínimos e máximos com `@Size`
- Campos únicos (matrícula, código do curso, código da turma)
- Validação de regras de negócio nos Services
- Tratamento global de exceções com `@ControllerAdvice`

## Exemplos de Uso

### Criar um novo curso
```bash
curl -X POST http://localhost:8080/api/cursos \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Sistemas de Informação",
    "codigoCurso": "SI003",
    "especialidade": "Gestão de Sistemas"
  }'
```

### Buscar estudantes de uma turma
```bash
curl http://localhost:8080/api/estudantes/turma/1
```

### Contar estudantes em uma turma
```bash
curl http://localhost:8080/api/turmas/1/contar-estudantes
```

## Arquitetura
- **Model**: Entidades JPA com validações
- **Repository**: Interfaces JpaRepository com queries customizadas
- **Service**: Lógica de negócio e validações
- **Controller**: Endpoints REST com tratamento de HTTP
- **Exception Handler**: Tratamento global de exceções

## Testes
O projeto inclui testes unitários que verificam:
- Carregamento do contexto Spring
- Funcionamento dos Services
- Integração com o banco de dados H2

Execute os testes com:
```bash
./mvnw test
```
