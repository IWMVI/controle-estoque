package br.edu.fateczl.controle_estoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fateczl.controle_estoque.dto.ProdutoDto;
import br.edu.fateczl.controle_estoque.model.Categoria;
import br.edu.fateczl.controle_estoque.model.Produto;
import br.edu.fateczl.controle_estoque.service.CategoriaService;
import br.edu.fateczl.controle_estoque.service.ProdutoService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping
	public ModelAndView listarProdutos() {
		ModelAndView mv = new ModelAndView("produto/produtos");
		mv.addObject("produtos", produtoService.todosProdutos());
		mv.addObject("categorias", categoriaService.todasCategorias());
		return mv;
	}

	@RequestMapping("/adicionar")
	public String novoProduto(@ModelAttribute @Valid ProdutoDto produtoDto, BindingResult result) {
		if (result.hasErrors()) {
			return "produtos/form";
		}

		Produto produto = new Produto();
		produto.setNome(produtoDto.getNome());
		produto.setDescricao(produtoDto.getDescricao());
		produto.setPreco(produtoDto.getPreco());
		produto.setQuantidade(produtoDto.getQuantidade());

		Categoria categoria = categoriaService.categoriaId(produtoDto.getCategoriaId());

		if (categoria == null) {
			throw new IllegalArgumentException("Categoria não encontrada");
		}

		produto.setCategoria(categoria);

		produtoService.salvarProduto(produto);

		return "redirect:/produtos";
	}

	@RequestMapping("/editar/{id}")
	public String editarProduto(@ModelAttribute ProdutoDto produtoDto, BindingResult result) {
		if (result.hasErrors()) {
			return "produtos/form";
		}

		Produto produto = produtoService.produtoId(produtoDto.getId());
		produto.setNome(produtoDto.getNome());
		produto.setDescricao(produtoDto.getDescricao());
		produto.setPreco(produtoDto.getPreco());
		produto.setQuantidade(produtoDto.getQuantidade());

		Categoria categoria = categoriaService.categoriaId(produtoDto.getCategoriaId());

		if (categoria == null) {
			throw new IllegalArgumentException("Categoria não encontrada");
		}

		produto.setCategoria(categoria);

		return "redirect:/produtos";
	}

	@RequestMapping("/excluir/{id}")
	public String excluirProduto(@ModelAttribute ProdutoDto produtoDto) {
		Produto produto = produtoService.produtoId(produtoDto.getId());

		if (produto != null) {
			produtoService.deletarProduto(produto.getId());
		}

		return "redirect:/produtos";
	}
}
