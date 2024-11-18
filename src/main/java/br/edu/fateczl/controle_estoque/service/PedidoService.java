package br.edu.fateczl.controle_estoque.service;

import br.edu.fateczl.controle_estoque.model.Pedido;
import br.edu.fateczl.controle_estoque.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }


    public List<Pedido> todosPedidos() {
        return repository.findAll();
    }

    public void salvarPedido(Pedido pedido) {
        repository.save(pedido);
    }

    public Pedido pedidoId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public boolean deletarPedido(Long id) {
        Pedido retorno = this.pedidoId(id);

        if (retorno != null) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public void atualizarPedido(Pedido antigo, Pedido novo) {

    }
}
