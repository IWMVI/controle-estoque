package br.edu.fateczl.controle_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.fateczl.controle_estoque.model.Produto;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Iterable<Produto> findByCategoriaId(Long id);
	List<Produto> findByAtivoTrue();

}
