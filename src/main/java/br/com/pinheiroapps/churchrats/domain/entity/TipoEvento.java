package br.com.pinheiroapps.churchrats.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoEvento {
    CULTO_DOMINGO_MANHA("Culto de Domingo de manhã"),
    CULTO_DOMINGO_NOITE("Culto de Domingo à noite"),
    CULTO_ORACAO("Culto de oração"),
    CULTO_REDES("Culto de rede ministerial"),
    EBD("Escola Bíblica Dominical"),
    OUTRO("Outro");

    private final String descricao;

}
