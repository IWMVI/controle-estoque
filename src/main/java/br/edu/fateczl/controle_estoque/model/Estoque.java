package br.edu.fateczl.controle_estoque.model;

import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @MapKey(name = "id")
    private Map<Long, Produto> produtos;

    public void adicionarProduto(Produto produto) {
        produtos.put(produto.getId(), produto);
    }

    public void removerProduto(Produto produto) {
        produtos.remove(produto.getId());
    }

    public Produto buscarProduto(Long id) {
        return produtos.get(id);
    }
}
