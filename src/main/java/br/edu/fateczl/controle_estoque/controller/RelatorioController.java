package br.edu.fateczl.controle_estoque.controller;

import java.util.List;

import br.edu.fateczl.controle_estoque.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.fateczl.controle_estoque.service.RelatorioService;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/relatorios")
public class RelatorioController {

    private final RelatorioService relatorioService;
    private final CategoriaService categoriaService;

    public RelatorioController(RelatorioService relatorioService, CategoriaService categoriaService) {
        this.relatorioService = relatorioService;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ModelAndView exibirFormularioRelatorio(
            @RequestParam(value = "categoriaId", required = false, defaultValue = "1") Long categoriaId,
            @RequestParam(value = "status", required = false, defaultValue = "true") Boolean status) {

        // Lista que irá armazenar o relatório
        List<Object[]> relatorio = null;

        // Verifica se os parâmetros de categoriaId e status foram passados e chama o
        // serviço para gerar o relatório
        if (categoriaId != null && status != null) {
            relatorio = relatorioService.relatorioPorCategoria(categoriaId, status);
        }

        // Adiciona os atributos necessários no modelo para a página
        ModelAndView mv = new ModelAndView("relatorio/formularioRelatorio");
        mv.addObject("relatorio", relatorio);
        mv.addObject("categoriaId", categoriaId);
        mv.addObject("status", status);
        mv.addObject("categorias", categoriaService.todasCategorias());

        return mv; // Página com o formulário e relatório
    }
}
