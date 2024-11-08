package br.edu.fateczl.controle_estoque.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoriaDto {
    @NotEmpty(message = "Nome não pode ser vazio")
    @NotBlank(message = "Nome não deve ter apenas espaço")
    @Size(min = 3, max = 50, message = "Nome deve ter entre 3 e 50 caracteres")
    private String nome;

    @Size(max = 100, message = "Descrição não pode passar de 100 caracteres")
    private String descricao;
}
