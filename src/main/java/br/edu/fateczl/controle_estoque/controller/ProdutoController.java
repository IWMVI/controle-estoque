package br.edu.fateczl.controle_estoque.controller;

import br.edu.fateczl.controle_estoque.dto.ProdutoDto;
import br.edu.fateczl.controle_estoque.model.Categoria;
import br.edu.fateczl.controle_estoque.model.Produto;
import br.edu.fateczl.controle_estoque.service.CategoriaService;
import br.edu.fateczl.controle_estoque.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ModelAndView listarProdutos() {
        ModelAndView mv = new ModelAndView("produto/produtos");
        mv.addObject("produtos", produtoService.todosProdutos());
        mv.addObject("categorias", categoriaService.todasCategorias());
        mv.addObject("produtoDto", new ProdutoDto());
        return mv;
    }

    @PostMapping("/adicionar")
    public String novoProduto(@ModelAttribute @Valid ProdutoDto produtoDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaService.todasCategorias());
            return "produto/produtos";
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

    @GetMapping("/editar/{id}")
    public ModelAndView exibirFormularioEdicao(@PathVariable Long id) {
        Produto produto = produtoService.produtoId(id);
        List<Categoria> categorias = categoriaService.todasCategorias();

        ProdutoDto produtoDto = new ProdutoDto();
        produtoDto.setId(produto.getId());
        produtoDto.setNome(produto.getNome());
        produtoDto.setDescricao(produto.getDescricao());
        produtoDto.setPreco(produto.getPreco());
        produtoDto.setQuantidade(produto.getQuantidade());
        produtoDto.setCategoriaId(produto.getCategoria().getId());

        ModelAndView mv = new ModelAndView("produto/ProdutoEditar");
        mv.addObject("produtoDto", produtoDto);
        mv.addObject("categorias", categorias);
        return mv;
    }

    @PostMapping("/editar/{id}")
    public String editarProduto(@PathVariable Long id, @ModelAttribute @Valid ProdutoDto produtoDto,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaService.todasCategorias());
            return "produto/ProdutoEditar";
        }

        Produto produto = produtoService.produtoId(id);
        if (produto != null) {
            // Atualiza os dados do produto
            produto.setNome(produtoDto.getNome());
            produto.setDescricao(produtoDto.getDescricao());
            produto.setPreco(produtoDto.getPreco());
            produto.setQuantidade(produtoDto.getQuantidade());

            // Atualiza a categoria do produto
            Categoria categoria = categoriaService.categoriaId(produtoDto.getCategoriaId());
            if (categoria != null) {
                produto.setCategoria(categoria);
            } else {
                throw new IllegalArgumentException("Categoria não encontrada");
            }

            produtoService.salvarProduto(produto);
        }

        return "redirect:/produtos";  // Redireciona para a lista de produtos após a edição
    }

    @PostMapping("/excluir/{id}")
    public String excluirProduto(@PathVariable Long id) {
        Produto produto = produtoService.produtoId(id);

        if (produto != null) {
            produtoService.deletarProduto(produto.getId());
        }

        return "redirect:/produtos";
    }
}