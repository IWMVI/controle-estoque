package br.edu.fateczl.controle_estoque.repository;

import br.edu.fateczl.controle_estoque.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
