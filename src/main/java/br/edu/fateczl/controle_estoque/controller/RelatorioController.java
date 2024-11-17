package br.edu.fateczl.controle_estoque.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.fateczl.controle_estoque.service.RelatorioService;

@Controller
@RequestMapping("/relatorios")
public class RelatorioController {

    private RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping
    public String exibirFormularioRelatorio(
            @RequestParam(value = "categoriaId", required = false) Long categoriaId,
            @RequestParam(value = "status", required = false) Boolean status,
            Model model) {

        // Lista que irá armazenar o relatório
        List<Object[]> relatorio = null;

        // Verifica se os parâmetros de categoriaId e status foram passados e chama o
        // serviço para gerar o relatório
        if (categoriaId != null && status != null) {
            relatorio = relatorioService.relatorioPorCategoria(categoriaId, status);
        }

        // Adiciona os atributos necessários no modelo para a página
        model.addAttribute("relatorio", relatorio);
        model.addAttribute("categoriaId", categoriaId);
        model.addAttribute("status", status);

        return "relatorio/formularioRelatorio"; // Página com o formulário e relatório
    }
}
