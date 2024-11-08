package br.edu.fateczl.controle_estoque.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueProdutoId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long estoqueId;
    private Long produtoId;
}
