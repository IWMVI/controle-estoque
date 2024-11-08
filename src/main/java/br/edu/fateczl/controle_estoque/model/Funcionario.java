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

    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    @Column(precision = 8, scale = 2, nullable = false)
    private BigDecimal salario;


    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
