package br.edu.fateczl.controle_estoque.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueDto {

    @NotEmpty(message = "Localização não pode ser vazia")
    @NotBlank(message = "Localização não deve ter apenas espaço")
    @Size(min = 3, max = 50, message = "Localização deve ter entre 3 e 50 caracteres")
    private String localizacao;

    @NotEmpty(message = "Descrição não pode ser vazia")
    @NotBlank(message = "Descrição não deve ter apenas espaço")
    @Size(min = 3, max = 100, message = "Descrição deve ter entre 3 e 100 caracteres")
    private String descricao;

}
