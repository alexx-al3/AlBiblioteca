# ALLibrary (versão simples)

Projeto Java básico sem Maven para teste de conexão com Supabase (PostgreSQL).

## Estrutura
- Main.java (menu simples)
- ConnectionFactory.java (conexão)
- lib/ (coloque aqui o driver postgresql.jar)

## Como rodar

### Compilar
javac -cp ".;lib/*" *.java

### Executar
java -cp ".;lib/*" Main

## Importante
Edite ConnectionFactory.java com seus dados do Supabase.
