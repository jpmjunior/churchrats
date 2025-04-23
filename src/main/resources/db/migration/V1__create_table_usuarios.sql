CREATE TABLE tb_usuarios (
    id BIGSERIAL NOT NULL,
    nome VARCHAR(300),
    whatsappId VARCHAR(100) NOT NULL,
    CONSTRAINT PK_tb_usuarios PRIMARY KEY (id)
);