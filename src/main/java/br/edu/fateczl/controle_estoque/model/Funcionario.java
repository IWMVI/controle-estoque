package br.edu.fateczl.controle_estoque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cargo;
    private double salario;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public void registrarEntrada() {
        // Lógica para registrar entrada
    }

    public void registrarSaida() {
        // Lógica para registrar saída
    }

    public void emitirPedido(Pedido pedido) {
        // Lógica para emitir pedido
    }
}
