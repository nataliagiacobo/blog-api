# Blog API 

## Sobre

A Blog API é uma API que permite manter usuários, posts, comentários e álbuns de fotos.

## Funcionalidades principais

- Cadastro de Usuários
- Cadastro de Posts
- Cadastro de Comentários
- Cadastro de Álbuns

## Tecnologias utilizadas
- **Linguagem de Programação:** Java 17
- **Framework:** Spring Boot 3.2.1
- **Banco de Dados:** PostgreSQL 14

## Configuração do Banco de Dados

1. **Crie o Banco de Dados:**

  Execute o seguinte comando SQL no PostgreSQL para criar o banco de dados:
  
  ```
  CREATE DATABASE nome_banco;
  ```

2. **Configuração do Application.properties:**

  Caso necessário, atualize as configurações do banco de dados, no arquivo **application.properties**:
   
  ```
  spring.datasource.url=jdbc:postgresql://localhost:5432/nome_banco
  spring.datasource.username=seu_usuario
  spring.datasource.password=sua_senha
  spring.jpa.hibernate.ddl-auto=update
  ```
  Por padrão, o nome do banco de dados é **blog**, o usuário e senha são **postgres** 

## Instalação e Execução
1. **Clone o repositório:**
  ```
  git clone https://github.com/nataliagiacobo/blog-api.git
  ```
2. **Instale as dependências:**
Navegue até o diretório do projeto e execute o seguinte comando:
  ```
  mvn install
  ```
3. **Execução Local:**
Navegue até o diretório do projeto e execute o seguinte comando:
  ```
  mvn spring-boot:run
  ```
O servidor iniciará na porta padrão **8080** (caso não seja alterada).

## Endoints da API

- POST /user: Cria um novo usuário.
- GET /user/{id}: Retorna um usuário específico pelo ID.
- POST /login: Realiza login com a autenticação do usuário.
- POST /post: Cria um novo post.
- GET /post/{id}: Retorna um post específico pelo ID.
- GET /post: Retorna todos os posts.
- DELETE /post/{id}: Deleta um post específico.
- POST /comment: Cria um novo comentário.
- GET /comment/{id}: Retorna um comentário específico pelo ID.
- DELETE /comment/{id}: Deleta um comentário específico.
- POST /album: Cria um novo álbum.
- GET /album/{id}: Retorna um álbum específico pelo ID.
- GET /album: Retorna todos os álbuns.
- DELETE /album/{id}: Deleta um álbum específico.
- POST /image/upload: Salva uma imagem.
- POST /image/upload/album/{id}: Salva uma imagem em um álbum específico.
- GET /image/{id}: Retorna uma imagem específica pelo ID.
