package br.com.pinheiroapps.pibsheeps.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_usuarios")
@Data // Gera getters, setters, toString, equals e hashCode
@NoArgsConstructor // Gera o construtor padrão
@AllArgsConstructor // Gera o construtor com todos os campos
@Builder // Permite o uso do padrão Builder para criar instâncias
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @NotBlank
    private String whatsappId;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegistroCheckin> registroCheckinList = new ArrayList<>();

}
