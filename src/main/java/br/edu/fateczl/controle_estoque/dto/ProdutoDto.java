package br.edu.fateczl.controle_estoque.dto;

import br.edu.fateczl.controle_estoque.model.Categoria;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDto {
    @NotEmpty(message = "Nome não pode ser vazio")
    @NotBlank(message = "Nome não deve ter apenas espaço")
    @Size(min = 3, max = 50, message = "Nome deve ter entre 3 e 50 caracteres")
    private String nome;

    @Size(max = 100, message = "Descrição não pode passar de 100 caracteres")
    @NotBlank(message = "Descrição não deve ter apenas espaço")
    private String descricao;

    @NotNull(message = "Preço não pode ser nulo")
    @DecimalMin(value = "0.01", message = "Preço deve ser maior que 0.01")
    private BigDecimal preco;

    @NotNull(message = "O campo ativo é obrigatório.")
    private Boolean ativo;

    @NotNull(message = "Categoria não pode ser nula")
    private Categoria categoria;
}
