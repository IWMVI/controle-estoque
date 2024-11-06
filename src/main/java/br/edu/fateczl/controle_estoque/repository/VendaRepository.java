package br.edu.fateczl.controle_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.fateczl.controle_estoque.model.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

}
