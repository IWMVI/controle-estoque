package br.edu.fateczl.controle_estoque.controller;

import br.edu.fateczl.controle_estoque.dto.ProdutoDto;
import br.edu.fateczl.controle_estoque.model.Categoria;
import br.edu.fateczl.controle_estoque.model.Produto;
import br.edu.fateczl.controle_estoque.service.CategoriaService;
import br.edu.fateczl.controle_estoque.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView novoProduto(@Valid ProdutoDto requisicao, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("produto/produtos");
            mv.addObject("produtos", produtoService.todosProdutos());
            mv.addObject("categorias", categoriaService.todasCategorias());
            mv.addObject("produtoDto", requisicao);
            return mv;
        }

        Produto produto = dtoParaProduto(requisicao);
        produtoService.salvarProduto(produto);

        return new ModelAndView("redirect:/produtos");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView exibirFormularioEdicao(@PathVariable Long id, ProdutoDto produtoDto) {
        Produto produtoBuscado = produtoService.produtoId(id);

        if (produtoBuscado != null) {
            ModelAndView mv = new ModelAndView("produto/ProdutoEditar");
            mv.addObject("produtoDto", produtoBuscado);
            mv.addObject("categorias", categoriaService.todasCategorias());

            return mv;
        }

        return new ModelAndView("redirect:/produtos");
    }


    @PostMapping("/editar/{id}")
    public ModelAndView editarProduto(@PathVariable Long id, @ModelAttribute @Valid ProdutoDto produtoDto, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("produto/ProdutoEditar");
            mv.addObject("categorias", categoriaService.todasCategorias());
            return mv;
        }

        Produto produtoAntigo = produtoService.produtoId(id);
        if (produtoAntigo != null) {
            produtoService.atualizarProduto(produtoAntigo, this.dtoParaProduto(produtoDto));
        }

        return new ModelAndView("redirect:/produtos");
    }

    @PostMapping("/excluir/{id}")
    public String excluirProduto(@PathVariable Long id) {
        Produto produto = produtoService.produtoId(id);

        if (produto != null) {
            produtoService.deletarProduto(produto.getId());
        }

        return "redirect:/produtos";
    }

    private ModelAndView criarModelAndViewParaFormulario() {
        ModelAndView mv = new ModelAndView("produto/produtos");
        mv.addObject("produtos", produtoService.todosProdutos());
        mv.addObject("categorias", categoriaService.todasCategorias());
        mv.addObject("produtoDto", new ProdutoDto());
        return mv;
    }

    private Produto dtoParaProduto(ProdutoDto produtoDto) {
        Categoria categoria = categoriaService
                .categoriaId(produtoDto.getCategoria().getId());
        if (categoria == null)
            throw new IllegalArgumentException("Categoria não encontrada");

        Produto produto = new Produto();
        produto.setNome(produtoDto.getNome());
        produto.setDescricao(produtoDto.getDescricao());
        produto.setPreco(produtoDto.getPreco());
        produto.setAtivo(produtoDto.getAtivo());
        produto.setCategoria(categoria);

        return produto;
    }
}