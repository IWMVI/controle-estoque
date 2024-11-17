package br.edu.fateczl.controle_estoque.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.fateczl.controle_estoque.model.Estoque;
import br.edu.fateczl.controle_estoque.repository.EstoqueRepository;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;

    public EstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    public List<Estoque> todosEstoque() {
        return estoqueRepository.findAll();
    }

    public void salvarEstoque(Estoque estoque) {
        estoque.setUltimaAtualizacao(LocalDateTime.now().toLocalDate());
        estoqueRepository.save(estoque);
    }

    public Estoque estoqueId(Long id) {
        return estoqueRepository.findById(id).orElse(null);
    }

    public boolean deletarEstoque(Long id) {

        Estoque estoque = this.estoqueId(id);

        if (estoque != null && estoque.getProdutos().isEmpty()) {
            estoqueRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

    public void atualizarEstoque(Estoque antigo, Estoque novo) {
        antigo.setLocalizacao(novo.getLocalizacao());
        antigo.setDescricao(novo.getDescricao());


        estoqueRepository.save(antigo);
    }

    public int quantidadeEstoque() {
        return (int) estoqueRepository.count();
    }

}
