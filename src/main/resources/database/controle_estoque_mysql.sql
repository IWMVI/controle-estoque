CREATE DATABASE IF NOT EXISTS controle_estoque;

-- controle_estoque.categoria definição
CREATE TABLE
  `categoria` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `descricao` VARCHAR(100) DEFAULT NULL,
    `nome` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
  );

-- controle_estoque.estoque definição
CREATE TABLE
  `estoque` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `descricao` VARCHAR(255) DEFAULT NULL,
    `localizacao` VARCHAR(255) NOT NULL,
    `ultima_atualizacao` DATE NOT NULL,
    PRIMARY KEY (`id`)
  );

-- controle_estoque.funcionario definição
CREATE TABLE
  `funcionario` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `ativo` bit (1) DEFAULT NULL,
    `cargo` enum ('FUNCIONARIO', 'GERENTE') DEFAULT NULL,
    `email` VARCHAR(50) NOT NULL,
    `nome` VARCHAR(50) NOT NULL,
    `senha` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
  );

-- controle_estoque.pedido definição
CREATE TABLE
  `pedido` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `data_pedido` DATE NOT NULL,
    `funcionario_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FKi23ikc3j8n2eng9xk4qrgt3w5` (`funcionario_id`),
    CONSTRAINT `FKi23ikc3j8n2eng9xk4qrgt3w5` FOREIGN KEY (`funcionario_id`) REFERENCES `funcionario` (`id`)
  );

-- controle_estoque.produto definição
CREATE TABLE
  `produto` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `ativo` bit (1) DEFAULT NULL,
    `data_alteracao` DATE NOT NULL,
    `data_cadastro` DATE NOT NULL,
    `descricao` VARCHAR(100) DEFAULT NULL,
    `nome` VARCHAR(50) NOT NULL,
    `preco` DECIMAL(8, 2) NOT NULL,
    `categoria_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FKopu9jggwnamfv0c8k2ri3kx0a` (`categoria_id`),
    CONSTRAINT `FKopu9jggwnamfv0c8k2ri3kx0a` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`)
  );

-- controle_estoque.estoque_produto definição
CREATE TABLE
  `estoque_produto` (
    `quantidade` INT NOT NULL,
    `ultima_atualizacao` DATE NOT NULL,
    `estoque_id` BIGINT NOT NULL,
    `produto_id` BIGINT NOT NULL,
    PRIMARY KEY (`estoque_id`, `produto_id`),
    KEY `FKki03d34hdy3i6128684pui1ny` (`produto_id`),
    CONSTRAINT `FKjk9axbgyugc9n96nnoap9w19n` FOREIGN KEY (`estoque_id`) REFERENCES `estoque` (`id`),
    CONSTRAINT `FKki03d34hdy3i6128684pui1ny` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`)
  );

-- controle_estoque.item_pedido definição
CREATE TABLE
  `item_pedido` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `preco_total` DECIMAL(8, 2) NOT NULL,
    `quantidade` INT NOT NULL,
    `produto_id` BIGINT NOT NULL,
    `pedido_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FKtk55mn6d6bvl5h0no5uagi3sf` (`produto_id`),
    KEY `FK60ym08cfoysa17wrn1swyiuda` (`pedido_id`),
    CONSTRAINT `FK60ym08cfoysa17wrn1swyiuda` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`),
    CONSTRAINT `FKtk55mn6d6bvl5h0no5uagi3sf` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`)
  );