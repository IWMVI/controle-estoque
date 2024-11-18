package br.edu.fateczl.controle_estoque.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, updatable = false)
    private LocalDate dataPedido;


    @ManyToOne(optional = false)
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    @OneToMany(mappedBy = "pedido") // mapeamento é feito em ItemPedido
    private List<ItemPedido> itens;


    @PrePersist
    public void adicionarPedido() {
        this.dataPedido = LocalDate.now();
    }
}
