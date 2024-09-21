![Código Certo Coders](https://utfs.io/f/3b2340e8-5523-4aca-a549-0688fd07450e-j4edu.jfif)

# 📚 Trilha Inicial BackEnd Jr
Este projeto tem como objetivo desenvolver uma API RESTful para gerenciamento de tarefas, proporcionando funcionalidades de CRUD (Create, Read, Update, Delete) de tarefas, autenticação de usuários e armazenamento dos dados em um banco de dados.

## Objetivos:
- Criar uma API que permita CRUD (Create, Read, Update, Delete) de tarefas.
- Implementar autenticação de usuários.
- Utilizar um banco de dados SQLite para armazenar as tarefas.
- Documentar todo o processo e apresentar as conclusões.

---
## Tecnologias Utilizadas
- **Java 17**
- **Spring Boot:**
    - **Spring Data JPA**
    - **Spring Web**
    - **Spring Security**
    - **Springdoc OpenAPI**
- **Lombok**:
- **JWT (Java JWT)**
- **SQLite JDBC**
- **Hibernate Dialects**
- **Docker**
---

## Como Instalar e usar API

* Caso não queire testar localmente, Pode ser acessada via deploy atráves do link: (`https://trilhabackendjr-jun15-render.onrender.com/swagger-ui/index.html#/`)

* 1 Clone o repositório
```bash
git clone https://github.com/Manoel-DJS/TrilhaBackEndJR-JUN15
```
- 2 Instale as dependências
```bash
mvn clean install
```
- 3 Inicie o projeto
```bash
mvn spring-boot:run
```
- 4 A API ficará url
```bash
http://localhost:8080
```
- 5 Teste a API no Insomnia ou Postman
- 6 Também, pode ser testada por meio local: http://localhost:8080/swagger-ui/index.html

---

## *Refatorando Código*
```markdown

### Rotas de Autenticação
POST /auth/register - Registrar Usuário - 
    role = USER ou ADMIN
{
    "username": "string",
    "password": "string",
    "role": "USER"
}

POST /auth/login - Realiza o Login do usuário, gera um token de autenticação

{
    "username": "string",
    "password": "string"
}

### Rotas do usuário - Necessário estar Autenticado

GET /end/users - Listar todos os usuários, caso seja usuário ADMIN

### Rotas de Tarefas
Para criar tarefas do Usuário autenticado.
    POST /tasks/authTask
{
    "titleTask": "string",
    "description": "string"
}

Para listar as tarefas do Usuário autenticado.
    GET /tasks/authTask

Para alterar as tarefas do Usuário autenticado.
    PUT /tasks/authTask/{idTask}
{
    "titleTask": "string",
    "description": "string"
}

Para deleter as tarefas do Usuário autenticado.
    DELETE /tasks/authTask/{idTask}

```
---

🔗 **Mantenha-se Conectado Comigo:**


- [Meu LinkedIn](https://www.linkedin.com/in/manoel-vinicius-844692249/)
- [Meu github](https://github.com/Manoel-DJS)


🌐 **Código Certo:**
- Email: codigocertocoders@gmail.com
- [Desafio-CodigoCerto](http://www.codigocertocoders.com.br/)
- [Discord](https://discord.gg/wzA9FG)

---

**- "Construindo o amanhã, hoje." Código Certo**
