CREATE TABLE tb_registro_checkin (
    id BIGSERIAL NOT NULL,
    tb_usuario_id BIGINT NOT NULL,
    data_hora TIMESTAMP NOT NULL,
    localizacao VARCHAR(100),
    tipo_evento VARCHAR(50),
    CONSTRAINT PK_tb_registro_checkin PRIMARY KEY (id),
    CONSTRAINT FK_tb_registro_checkin_tb_usuario_id FOREIGN KEY (tb_usuario_id) REFERENCES tb_usuarios (id)
);

CREATE INDEX IX_tb_registro_checkin_tb_usuario_id ON tb_registro_checkin (tb_usuario_id);
