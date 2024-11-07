package br.edu.fateczl.controle_estoque.controller;

import br.edu.fateczl.controle_estoque.model.Produto;
import br.edu.fateczl.controle_estoque.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // Exibir lista de produtos
    @GetMapping
    public String listarProdutos(Model model) {
        List<Produto> produtos = produtoService.listarProdutos();
        model.addAttribute("produtos", produtos);
        return "produtos/produtos"; // Página onde os produtos serão exibidos
    }

    // Página de adicionar produto
    @GetMapping("/adicionar-produto")
    public String mostrarPaginaAdicionarProduto() {
        return "produtos/adicionar-produto"; // Retorna o template adicionar-produto.html
    }

    // Obter produto por ID
    @GetMapping("/{id}")
    @ResponseBody
    public Produto obterProduto(@PathVariable Long id) {
        return produtoService.obterProduto(id);
    }

    // Adicionar produto via AJAX
    @PostMapping("/adicionar-produto")
    @ResponseBody
    public Produto adicionarProduto(@RequestBody Produto produto) {
        return produtoService.salvarProduto(produto);
    }

    // Editar produto
    @PostMapping("/editar-produto/{id}")
    @ResponseBody
    public Produto editarProduto(@PathVariable Long id, @RequestBody Produto produto) {
        return produtoService.editarProduto(id, produto);
    }

    // Deletar produto
    @DeleteMapping("/deletar-produto/{id}")
    @ResponseBody
    public void deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
    }
}
