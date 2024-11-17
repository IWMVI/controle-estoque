package br.edu.fateczl.controle_estoque.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String localizacao;

    private String descricao;

    @Column(nullable = false)
    private LocalDate ultimaAtualizacao;

    @OneToMany(mappedBy = "estoque", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EstoqueProduto> produtos; // Lista de produtos no estoque

    @PreUpdate
    public void atualizarDataEstoque() {
        this.ultimaAtualizacao = LocalDate.now();
    }

}
