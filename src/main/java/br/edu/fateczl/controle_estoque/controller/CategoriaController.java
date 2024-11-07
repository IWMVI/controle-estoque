package br.edu.fateczl.controle_estoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.fateczl.controle_estoque.model.Categoria;
import br.edu.fateczl.controle_estoque.repository.CategoriaRepository;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Método para adicionar categoria
    @PostMapping("/adicionar")
    public String adicionarCategoria(@RequestParam String nome, @RequestParam String descricao) {
        Categoria categoria = new Categoria();
        categoria.setNome(nome);
        categoria.setDescricao(descricao);
        categoriaRepository.save(categoria);
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

    // Método para listar todas as categorias
    @GetMapping
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "categoria/categorias";
    }
}
