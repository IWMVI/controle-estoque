package br.edu.fateczl.controle_estoque.controller;

import br.edu.fateczl.controle_estoque.dto.CategoriaDto;
import br.edu.fateczl.controle_estoque.model.Categoria;
import br.edu.fateczl.controle_estoque.repository.CategoriaRepository;
import br.edu.fateczl.controle_estoque.service.CategoriaService;
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
    CategoriaService categoriaService;
    CategoriaRepository categoriaRepository; //TODO: Remover depois


    // Método para listar todas as categorias
    @GetMapping
    public ModelAndView listarCategorias() {
        ModelAndView mv = new ModelAndView("categoria/categorias");
        Iterable<Categoria> categorias = categoriaService.todasCategorias();

        mv.addObject("categorias", categorias);
        return mv;
    }

    // Método para adicionar categoria
    @PostMapping("/adicionar")
    public String adicionarCategoria(@ModelAttribute @Valid CategoriaDto categoriaDto, BindingResult result) {
        if (result.hasErrors()) {
            return "categoria/adicionar";
        }

        Categoria categoria = new Categoria();

        categoria.setNome(categoriaDto.getNome());
        categoria.setDescricao(categoriaDto.getDescricao());

        categoriaService.salvarCategoria(categoria);
        return "redirect:/categorias";
    }

    // Método para editar categoria
    @PostMapping("/editar/{id}")
    public String editarCategoria(@PathVariable Long id, @RequestParam String nome, @RequestParam String descricao) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));
        categoria.setNome(nome);
        categoria.setDescricao(descricao);
        categoriaRepository.save(categoria);
        return "redirect:/categorias";
    }

    // Método para excluir categoria
    @GetMapping("/excluir/{id}")
    public String excluirCategoria(@PathVariable Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));
        categoriaRepository.delete(categoria);
        return "redirect:/categorias";
    }
}
