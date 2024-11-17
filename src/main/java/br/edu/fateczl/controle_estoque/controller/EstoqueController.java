package br.edu.fateczl.controle_estoque.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fateczl.controle_estoque.dto.EstoqueDto;
import br.edu.fateczl.controle_estoque.model.Estoque;
import br.edu.fateczl.controle_estoque.service.EstoqueService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/estoques")
public class EstoqueController {

    private final EstoqueService estoqueService;

    private static final String REDIRECT_ESTOQUES = "redirect:/estoques";

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @GetMapping
    public ModelAndView listarEstoques() {
        ModelAndView mv = new ModelAndView("estoque/estoques");
        mv.addObject("estoqueDto", new EstoqueDto());

        List<Estoque> estoques = estoqueService.todosEstoque();
        if (estoques == null) {
            estoques = new ArrayList();
        }

        mv.addObject("estoques", estoques);

        return mv;
    }

    // TODO: Verificar o motivo de não estar validando o EstoqueDto
    @PostMapping("/adicionar")
    @Transactional
    public ModelAndView novoEstoque(@Valid EstoqueDto requisicao, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("estoque/estoques");
            mv.addObject("estoqueDto", requisicao);
            mv.addObject("estoques", estoqueService.todosEstoque());
            return mv;
        }

        Estoque estoque = dtoParaEstoque(requisicao);
        estoqueService.salvarEstoque(estoque);

        return new ModelAndView(REDIRECT_ESTOQUES);
    }

    @GetMapping("/editar/{id}")
    public ModelAndView exibirFormularioEdicao(@PathVariable Long id, EstoqueDto estoqueDto) {
        Estoque estoqueBuscado = estoqueService.estoqueId(id);
        if (estoqueBuscado == null) {
            return new ModelAndView(REDIRECT_ESTOQUES);
        }

        ModelAndView mv = new ModelAndView("estoque/EstoqueEditar");
        mv.addObject("estoqueDto", estoqueBuscado);
        mv.addObject("estoques", estoqueService.todosEstoque());

        return mv;
    }

    @PutMapping("/editar/{id}")
    @Transactional
    public String editarEstoque(@PathVariable Long id, @Valid EstoqueDto requisicao, BindingResult result) {
        if (!result.hasErrors()) {
            Estoque estoqueAntigo = estoqueService.estoqueId(id);
            estoqueService.atualizarEstoque(estoqueAntigo, dtoParaEstoque(requisicao));
        }

        return REDIRECT_ESTOQUES;
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public String excluirEstoque(@PathVariable Long id) {
        Estoque estoqueBuscado = estoqueService.estoqueId(id);
        if (estoqueBuscado != null) {
            estoqueService.deletarEstoque(estoqueBuscado.getId());
        }

        return REDIRECT_ESTOQUES;
    }

    private Estoque dtoParaEstoque(EstoqueDto estoqueDto) {
        Estoque estoque = new Estoque();
        estoque.setDescricao(estoqueDto.getDescricao());
        estoque.setLocalizacao(estoqueDto.getLocalizacao());
        return estoque;
    }
}
