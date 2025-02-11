package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
Admin    -> Admin- será agente que criou o app
parceiro -> Owner - o dono do imóvel ou do hotel
cliente  -> Client- o que vai fazer a reserva.
* */

@Entity
@Table(name = "usuario")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id", nullable = false)
    private Integer id;
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    @Column(name = "email", nullable = false, length = 150, unique = true)
    private String email;
    @Column(name = "sexo", length = 1)
    private Sexo sexo;
    @Column(name = "foto", columnDefinition = "TEXT")
    private String foto;
    @Column(name = "perfil", length = 50)
    private PerfilUsuario perfil;
    @Column(name = "password", nullable = false)
    private String senha;
    @Column(name = "data_de_nascimento")
    private LocalDate dataDeNascimento;
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "data_de_criacao", updatable = false)
    private Instant dataDeCriacao;
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "data_ultima_modificacao")
    private Instant dataUltimaModificacao;
    public void setDataDeNascimento(@NotNull String dataNascimento) {
        this.dataDeNascimento = LocalDate.parse(dataNascimento, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}