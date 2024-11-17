package br.edu.fateczl.controle_estoque.controller;

import br.edu.fateczl.controle_estoque.dto.CategoriaDto;
import br.edu.fateczl.controle_estoque.model.Categoria;
import br.edu.fateczl.controle_estoque.service.CategoriaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }


    @GetMapping
    public ModelAndView listarCategorias() {
        ModelAndView mv = new ModelAndView("categoria/categorias");
        mv.addObject("categorias", categoriaService.todasCategorias());
        return mv;
    }

    @PostMapping("/adicionar")
    @Transactional
    public String adicionarCategoria(@Valid CategoriaDto requisicao, BindingResult result) {
        //TODO: ao dar erro, ele não abre o modal
        if (!result.hasErrors()) {
            categoriaService.salvarCategoria(dtoParaCategoria(requisicao));
        }

        return "redirect:/categorias";
    }

    @PutMapping("/editar/{id}")
    @Transactional
    public String editarCategoria(@PathVariable Long id, @Valid CategoriaDto requisicao, BindingResult result) {
        if (!result.hasErrors()) {
            Categoria categoriaAntiga = categoriaService.categoriaId(id);
            categoriaService.atualizarCategoria(categoriaAntiga, dtoParaCategoria(requisicao));
        }

        return "redirect:/categorias";
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public String excluirCategoria(@PathVariable Long id) {
        Categoria categoriaBuscada = categoriaService.categoriaId(id);
        if (categoriaBuscada != null) {
            categoriaService.deletarCategoria(categoriaBuscada.getId());
        }

        return "redirect:/categorias";
    }


    private Categoria dtoParaCategoria(CategoriaDto categoriaDto) {
        Categoria categoria = new Categoria();
        categoria.setNome(categoriaDto.getNome());
        categoria.setDescricao(categoriaDto.getDescricao());
        return categoria;
    }
}
