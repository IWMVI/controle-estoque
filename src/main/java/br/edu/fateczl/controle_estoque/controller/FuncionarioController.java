package br.edu.fateczl.controle_estoque.controller;

import br.edu.fateczl.controle_estoque.dto.FuncionarioDto;
import br.edu.fateczl.controle_estoque.enums.Cargo;
import br.edu.fateczl.controle_estoque.model.Funcionario;
import br.edu.fateczl.controle_estoque.service.FuncionarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }


    @GetMapping
    public ModelAndView listarFuncionarios() {
        ModelAndView mv = new ModelAndView("funcionario/FuncionarioHome");
        mv.addObject("funcionarios", service.todosFuncionarios());
        return mv;
    }

    @GetMapping("/adicionar")
    public ModelAndView exibirFormularioCadastro() {
        ModelAndView mv = this.criarModelAndViewParaFormulario("funcionario/FuncionarioCadastrar");
        mv.addObject("funcionarioDto", new FuncionarioDto());
        return mv;
    }

    @PostMapping("/adicionar")
    @Transactional
    public ModelAndView salvarCadastroFuncionario(@Valid FuncionarioDto requisicao, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = this.criarModelAndViewParaFormulario("funcionario/FuncionarioCadastrar");
            mv.addObject("funcionarioDto", requisicao);
            return mv;
        }

        Funcionario funcionarioNovo = dtoParaFuncionario(requisicao);
        service.salvarFuncionario(funcionarioNovo);

        return new ModelAndView("redirect:/funcionarios");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView exibirFormularioEdicao(@PathVariable Long id) {
        Funcionario funcionarioRetornado = service.funcionarioId(id);
        if (funcionarioRetornado == null) {
            return new ModelAndView("redirect:/funcionarios");
        }

        ModelAndView mv = this.criarModelAndViewParaFormulario("funcionario/FuncionarioEditar");
        mv.addObject("funcionarioDto", funcionarioRetornado);
        return mv;
    }

    @PutMapping("/editar/{id}")
    public ModelAndView editarFuncionario(@PathVariable Long id, @Valid FuncionarioDto requisicao, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = this.criarModelAndViewParaFormulario("funcionario/FuncionarioEditar");
            mv.addObject("funcionarioDto", requisicao);
            return mv;
        }

        Funcionario funcionarioAntigo = service.funcionarioId(id);
        if (funcionarioAntigo != null) {
            service.atualizarFuncionario(funcionarioAntigo, this.dtoParaFuncionario(requisicao));
        }

        return new ModelAndView("redirect:/funcionarios");
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public String excluirFuncionario(@PathVariable Long id) {
        service.deletarFuncionarioId(id);
        return "redirect:/funcionarios";
    }


    private ModelAndView criarModelAndViewParaFormulario(String arquivo) {
        ModelAndView mv = new ModelAndView(arquivo);
        mv.addObject("cargos", Cargo.values());
        return mv;
    }

    private Funcionario dtoParaFuncionario(FuncionarioDto funcionarioDto) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioDto.getNome());
        funcionario.setEmail(funcionarioDto.getEmail());
        funcionario.setSenha(funcionarioDto.getSenha());
        funcionario.setCargo(funcionarioDto.getCargo());
        funcionario.setAtivo(funcionarioDto.getAtivo());
        return funcionario;
    }
}
