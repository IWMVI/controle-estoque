package br.edu.fateczl.controle_estoque.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class EstoqueProduto {
    @EmbeddedId
    private EstoqueProdutoId id = new EstoqueProdutoId();

    @ManyToOne
    @MapsId("estoqueId")
    @JoinColumn(name = "estoque_id")
    private Estoque estoque;

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private LocalDate ultimaAtualizacao;

    @PreUpdate
    public void atualizarDataEstoqueProduto() {
        this.ultimaAtualizacao = LocalDate.now();
    }
}
