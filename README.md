# Controle de Estoque

Este é um projeto de controle de estoque desenvolvido em **Spring Boot** com **Java**. Ele permite gerenciar categorias, produtos, estoques, pedidos, itens de pedidos e funcionários. O banco de dados utilizado é o **H2** para testes, **MySQL** e **SQL Server** para produção.

## Funcionalidades

- **Categorias**: Cadastro, edição, exclusão e listagem de categorias de produtos.
- **Produtos**: Cadastro, edição, exclusão e listagem de produtos, com filtros por categoria e status (ativo/inativo).
- **Estoques**: Gerenciamento de estoques, incluindo localização e descrição.
- **Pedidos**: Cadastro e gerenciamento de pedidos, com itens de pedidos associados.
- **Funcionários**: Cadastro, edição, exclusão e listagem de funcionários, com controle de cargos (Funcionário/Gerente).
- **Relatórios**: Geração de relatórios de produtos por categoria e status.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **Spring Data JPA**: Para persistência de dados e operações de banco de dados.
- **H2 Database**: Banco de dados em memória para testes.
- **MySQL** e **SQL Server**: Banco de dados para ambiente de produção.
- **Lombok**: Para reduzir a verbosidade do código com anotações como `@Data`, `@Getter`, `@Setter`, etc.
- **JUnit 5**: Para testes unitários e de integração.
- **Mockito**: Para simulação de comportamentos em testes.

## Pré-requisitos

- **Java 21**: Certifique-se de ter o JDK 21 instalado.
- **Maven**: Para gerenciamento de dependências e build do projeto.
- **MySQL** e/ou **SQL Server** (opcional): Se for usar um banco de dados externo em produção.
- **Docker** (opcional): Para executar o MySQL e/ou SQL Server em containers.

## Configuração do Projeto

### 1. Clone o Repositório

```bash
git clone https://github.com/IWMVI/controle-estoque.git
cd controle-estoque
```

### 2. Configuração do Banco de Dados

#### Para Ambiente de Testes (H2)
O projeto já está configurado para usar o **H2** em memória durante os testes. Nenhuma configuração adicional é necessária.

#### Para Ambiente de Produção (MySQL ou outro banco)
1. Crie um banco de dados no MySQL (ou outro banco de sua escolha).
2. Edite o arquivo `application-prod.properties` na pasta `src/main/resources` com as credenciais do banco de dados:

```properties
# application-prod.properties
spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
``` 

### 3. Para iniciar o container do MySQL com Docker

```
docker-compose up -d mysql
``` 