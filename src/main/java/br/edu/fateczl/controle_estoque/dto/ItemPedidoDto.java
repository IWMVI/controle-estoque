package br.edu.fateczl.controle_estoque.dto;

import br.edu.fateczl.controle_estoque.model.Pedido;
import br.edu.fateczl.controle_estoque.model.Produto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoDto {
    @NotNull(message = "Quantidade é obrigatória")
    @Positive(message = "Quantidade deve ser maior que zero")
    @Max(value = 1000, message = "Quantidade máxima permitida é 1000")
    //TODO: constraint personalizada que verifica quantidade disponível
    private int quantidade;

    @NotNull(message = "Produto não pode ser nulo")
    private Produto produto;

    @NotNull(message = "Pedido não pode ser nulo")
    private Pedido pedido;
}
