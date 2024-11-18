package br.edu.fateczl.controle_estoque.service;

import br.edu.fateczl.controle_estoque.model.Funcionario;
import br.edu.fateczl.controle_estoque.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.repository = funcionarioRepository;
    }


    public List<Funcionario> todosFuncionarios() {
        return repository.findAll();
    }

    public void salvarFuncionario(Funcionario funcionario) {
        repository.save(funcionario);
    }

    public Funcionario funcionarioId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public boolean deletarFuncionarioId(Long id) {
        Funcionario retorno = this.funcionarioId(id);

        if (retorno != null) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public void atualizarFuncionario(Funcionario antigo, Funcionario novo) {
        antigo.setNome(novo.getNome());
        antigo.setEmail(novo.getEmail());
        antigo.setSenha(novo.getSenha());
        antigo.setCargo(novo.getCargo());
        antigo.setAtivo(novo.getAtivo());

        repository.save(antigo);
    }
}
