package br.edu.fateczl.controle_estoque.service;

import br.edu.fateczl.controle_estoque.model.Produto;
import br.edu.fateczl.controle_estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Listar todos os produtos
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    // Obter um produto por ID
    public Produto obterProduto(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.orElse(null); // Retorna null caso não encontre o produto
    }

    // Adicionar um novo produto
    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    // Editar um produto existente
    public Produto editarProduto(Long id, Produto produto) {
        if (produtoRepository.existsById(id)) {
            produto.setId(id); // Garantir que o ID do produto seja mantido
            return produtoRepository.save(produto);
        }
        return null;
    }

    // Excluir um produto por ID
    public void deletarProduto(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
        }
    }
}
