package br.edu.fateczl.controle_estoque.model;

import br.edu.fateczl.controle_estoque.enums.Cargo;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    private Boolean ativo;
}
