DROP TABLE IF EXISTS produto;

CREATE TABLE produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(250) NULL,
    preco FLOAT NOT NULL
);

INSERT INTO produto (nome, descricao, preco) VALUES ('Produto 1', 'Descrição 1', 35);
INSERT INTO produto (nome, descricao, preco) VALUES ('Produto 2', 'Descrição 2', 5.89);