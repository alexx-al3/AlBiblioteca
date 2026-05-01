# 📚 AlBiblioteca

![Java](https://img.shields.io/badge/Java-17+-orange)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![Swing](https://img.shields.io/badge/Swing-UI-lightgrey)
![Status](https://img.shields.io/badge/Status-Funcionando-brightgreen)

Sistema de biblioteca desenvolvido em Java com interface Swing e banco de dados PostgreSQL.  
Arquitetura em camadas (DAO + Service + UI), simulando um sistema real de biblioteca.

---

## 🚀 Funcionalidades

- 📚 Cadastro de livros
- 👤 Cadastro de usuários (aluno/professor)
- 🔄 Empréstimo de livros
- ↩️ Devolução de livros
- 📋 Listagem de livros com status
- 🐘 Persistência total em PostgreSQL

---

## 🧱 Tecnologias utilizadas

- Java 17+
- Swing (Interface gráfica)
- PostgreSQL
- JDBC
- Programação Orientada a Objetos (POO)

---

## 🗂️ Estrutura do projeto

AlBiblioteca
<<<<<<< HEAD
Main
├── Main.java
├── BibliotecaUI.java
├── LivroDAO.java
├── UsuarioDAO.java
├── EmprestimoDAO.java
├── LivroService.java
├── UsuarioService.java
├── EmprestimoService.java
├── ConnectionFactory.java
├── classe/
│   ├── Livro.java
│   ├── Usuario.java
│   ├── Emprestimo.java
=======
├──Main/
│ ├── Main.java
│ ├── BibliotecaUI.java
│ ├── LivroDAO.java
│ ├── UsuarioDAO.java
│ ├── EmprestimoDAO.java
│ ├── LivroService.java
│ ├── UsuarioService.java
│ ├── EmprestimoService.java
│ ├── ConnectionFactory.java
├── classe/
│ ├── Livro.java
│ ├── Usuario.java
│ ├── Emprestimo.java
>>>>>>> a134df1d42f4b99d76c77257d94011a598bdaca3

---

## 🐘 Banco de Dados (PostgreSQL)

### Criar banco:

CREATE DATABASE biblioteca;

---

### Tabela Livro

CREATE TABLE livro (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    autor VARCHAR(200) NOT NULL,
    ano INT NOT NULL,
    disponivel BOOLEAN DEFAULT TRUE
);

---

### Tabela Usuario

CREATE TABLE usuario (
    id VARCHAR(100) PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    tipo VARCHAR(50) NOT NULL
);

---

### Tabela Emprestimo

CREATE TABLE emprestimo (
    id SERIAL PRIMARY KEY,
    livro_id INT NOT NULL,
    usuario_id VARCHAR(100) NOT NULL,
    data_emprestimo TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_devolucao TIMESTAMP,

    FOREIGN KEY (livro_id) REFERENCES livro(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

---

## ⚙️ Configuração da conexão

No arquivo `ConnectionFactory.java`:

private static final String URL = "jdbc:postgresql://localhost:5432/biblioteca";
private static final String USER = "postgres";
private static final String PASSWORD = "sua_senha";

---

## ▶️ Como executar

### 1. Pré-requisitos
- Java 17+
- PostgreSQL instalado
- Driver JDBC (`postgresql-42.x.x.jar`)

---

### 2. Criar banco e tabelas
Execute os comandos SQL acima no PostgreSQL.

---

### 3. Configurar conexão
Edite `ConnectionFactory.java` com sua senha do banco.

---

### 4. Executar sistema
Rode:
- `Main.java` (modo console)
ou
- `BibliotecaUI.java` (interface gráfica)

---

## 🧠 Arquitetura

UI (Swing)
   ↓
Service (regras e validação)
   ↓
DAO (SQL / JDBC)
   ↓
PostgreSQL

---

## 📌 Regras do sistema

- Livro ID gerado automaticamente pelo banco (SERIAL)
- Usuário ID gerado via UUID no Java
- Todas as operações passam pelo banco

---

## 💡 Objetivo

Projeto acadêmico com foco em:
- Arquitetura de software
- Integração Java + Banco de dados
- Boas práticas com DAO e Service
- Interface gráfica Swing
- Simulação de sistema real

---

## 👨‍💻 Autor

<<<<<<< HEAD
Desenvolvido por Alex Alves
=======
Desenvolvido por Alex Alves
>>>>>>> a134df1d42f4b99d76c77257d94011a598bdaca3
