package br.com.pinheiroapps.pibsheeps.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_registro_checkin")
@Data // Gera getters, setters, toString, equals e hashCode
@NoArgsConstructor // Gera o construtor padrão
@AllArgsConstructor // Gera o construtor com todos os campos
@Builder // Permite o uso do padrão Builder para criar instâncias
public class RegistroCheckin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "tb_usuario_id")
    private Usuario usuario;

    @NotNull
    private LocalDateTime dataHora;

    private String localização;

    private TipoEvento tipoEvento;

}
