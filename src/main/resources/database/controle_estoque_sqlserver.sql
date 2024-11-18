-- Criação do banco de dados
IF NOT EXISTS (
    SELECT
        *
    FROM
        sys.databases
    WHERE
        name = 'controle_estoque'
) BEGIN CREATE DATABASE controle_estoque;

END GO USE controle_estoque;

GO
-- controle_estoque.categoria definição
CREATE TABLE
    categoria (
        id BIGINT NOT NULL IDENTITY (1, 1),
        descricao VARCHAR(100) NULL,
        nome VARCHAR(50) NOT NULL,
        PRIMARY KEY (id)
    );

GO
-- controle_estoque.estoque definição
CREATE TABLE
    estoque (
        id BIGINT NOT NULL IDENTITY (1, 1),
        descricao VARCHAR(255) NULL,
        localizacao VARCHAR(255) NOT NULL,
        ultima_atualizacao DATE NOT NULL,
        PRIMARY KEY (id)
    );

GO
-- controle_estoque.funcionario definição
CREATE TABLE
    funcionario (
        id BIGINT NOT NULL IDENTITY (1, 1),
        ativo BIT NULL,
        cargo VARCHAR(50) CHECK (cargo IN ('FUNCIONARIO', 'GERENTE')) NULL,
        email VARCHAR(50) NOT NULL,
        nome VARCHAR(50) NOT NULL,
        senha VARCHAR(50) NOT NULL,
        PRIMARY KEY (id)
    );

GO
-- controle_estoque.pedido definição
CREATE TABLE
    pedido (
        id BIGINT NOT NULL IDENTITY (1, 1),
        data_pedido DATE NOT NULL,
        funcionario_id BIGINT NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT FK_funcionario_pedido FOREIGN KEY (funcionario_id) REFERENCES funcionario (id)
    );

GO
-- controle_estoque.produto definição
CREATE TABLE
    produto (
        id BIGINT NOT NULL IDENTITY (1, 1),
        ativo BIT NULL,
        data_alteracao DATE NOT NULL,
        data_cadastro DATE NOT NULL,
        descricao VARCHAR(100) NULL,
        nome VARCHAR(50) NOT NULL,
        preco DECIMAL(8, 2) NOT NULL,
        categoria_id BIGINT NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT FK_categoria_produto FOREIGN KEY (categoria_id) REFERENCES categoria (id)
    );

GO
-- controle_estoque.estoque_produto definição
CREATE TABLE
    estoque_produto (
        quantidade INT NOT NULL,
        ultima_atualizacao DATE NOT NULL,
        estoque_id BIGINT NOT NULL,
        produto_id BIGINT NOT NULL,
        PRIMARY KEY (estoque_id, produto_id),
        CONSTRAINT FK_estoque_produto_estoque FOREIGN KEY (estoque_id) REFERENCES estoque (id),
        CONSTRAINT FK_estoque_produto_produto FOREIGN KEY (produto_id) REFERENCES produto (id)
    );

GO
-- controle_estoque.item_pedido definição
CREATE TABLE
    item_pedido (
        id BIGINT NOT NULL IDENTITY (1, 1),
        preco_total DECIMAL(8, 2) NOT NULL,
        quantidade INT NOT NULL,
        produto_id BIGINT NOT NULL,
        pedido_id BIGINT NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT FK_item_pedido_produto FOREIGN KEY (produto_id) REFERENCES produto (id),
        CONSTRAINT FK_item_pedido_pedido FOREIGN KEY (pedido_id) REFERENCES pedido (id)
    );

GO