package br.edu.fateczl.controle_estoque.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 100)
    private String descricao;

    @Column(precision = 8, scale = 2, nullable = false)
    private BigDecimal preco;

    private Boolean ativo;

    @Column(nullable = false, updatable = false)
    private LocalDate dataCadastro;

    @Column(nullable = false)
    private LocalDate dataAlteracao;


    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;


    @PrePersist
    public void adicionarDataProduto() {
        this.dataCadastro = LocalDate.now();
        this.dataAlteracao = LocalDate.now();
    }

    @PreUpdate
    public void alterarDataProduto() {
        this.dataAlteracao = LocalDate.now();
    }
}
