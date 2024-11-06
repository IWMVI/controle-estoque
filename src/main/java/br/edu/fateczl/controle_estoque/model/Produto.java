package br.edu.fateczl.controle_estoque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "categoria", nullable = false)
    private String categoria;

    @Column(name = "descricao", nullable = true)
    private String descricao;

    @Column(name = "preco", nullable = false)
    private Double preco;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
}
