package br.edu.fateczl.controle_estoque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fateczl.controle_estoque.model.Produto;
import br.edu.fateczl.controle_estoque.repository.ProdutoRepository;

import java.util.List;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Produto> todosProdutos() {
		return produtoRepository.findAll();
	}

	public void salvarProduto(Produto produto) {
		produtoRepository.save(produto);
	}

	public Produto produtoId(Long id) {
		return produtoRepository.findById(id).orElse(null);
	}

	public boolean deletarProduto(Long id) {
		Produto retorno = this.produtoId(id);

		if (retorno != null) {
			produtoRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	public void atualizarProduto(Produto antigo, Produto novo) {
		antigo.setNome(novo.getNome());
		antigo.setDescricao(novo.getDescricao());
		antigo.setPreco(novo.getPreco());
		antigo.setAtivo(novo.getAtivo());
		antigo.setCategoria(novo.getCategoria());

		produtoRepository.save(antigo);
	}

	public Iterable<Produto> produtosPorCategoria(Long id) {
		return produtoRepository.findByCategoriaId(id);
	}
}
