package br.edu.fateczl.controle_estoque.dto;

import br.edu.fateczl.controle_estoque.enums.Cargo;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDto {
    @NotEmpty(message = "Nome não pode ser vazio")
    @NotBlank(message = "Nome não deve ter apenas espaço")
    @Size(min = 3, max = 50, message = "Nome deve ter entre 3 e 50 caracteres")
    private String nome;

    @NotEmpty(message = "E-mail não pode ser vazio")
    @NotBlank(message = "E-mail não deve ter apenas espaço")
    @Size(min = 5, max = 50, message = "E-mail deve ter entre 5 e 50 caracteres")
    @Email(message = "E-mail inválido") //TODO: "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"
    private String email;

    @NotEmpty(message = "Senha não pode ser vazia")
    @NotBlank(message = "Senha não deve ter apenas espaço")
    @Size(min = 3, max = 50, message = "Senha deve ter entre 5 e 50 caracteres")
    private String senha;

    @NotNull(message = "Cargo selecionado inválido")
    private Cargo cargo;

    @NotNull(message = "Status de ativo inválido")
    private Boolean ativo;
}
