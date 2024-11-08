package br.edu.fateczl.controle_estoque.service;

import br.edu.fateczl.controle_estoque.model.Categoria;
import br.edu.fateczl.controle_estoque.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repository;


    public Iterable<Categoria> todasCategorias() {
        return repository.findAll();
    }

    public void salvarCategoria(Categoria categoria) {
        repository.save(categoria);
    }

    public Categoria categoriaId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public boolean deletarCategoria(Long id) {
        Categoria retorno = this.categoriaId(id);

        if (retorno != null) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public void atualizarCategoria(Categoria antiga, Categoria atualizada) {
        antiga.setNome(atualizada.getNome());
        antiga.setDescricao(atualizada.getDescricao());

        repository.save(antiga);
    }
}
