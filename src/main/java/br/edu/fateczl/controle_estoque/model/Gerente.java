package br.edu.fateczl.controle_estoque.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Gerente extends Funcionario {

    @Override
    public void registrarEntrada() {
        // Lógica específica de gerente para registrar entrada
    }

    @Override
    public void registrarSaida() {
        // Lógica específica de gerente para registrar saída
    }

    public void autorizarPedido(Pedido pedido) {
        // Lógica para autorizar pedido
    }

    public void autorizarDesconto(Produto produto, double desconto) {
        // Lógica para autorizar desconto
    }
}
