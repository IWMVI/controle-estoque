package br.edu.fateczl.controle_estoque.service;

import br.edu.fateczl.controle_estoque.model.ItemPedido;
import br.edu.fateczl.controle_estoque.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ItemPedidoService {

    @Autowired
    private final ItemPedidoRepository repository;

    public ItemPedidoService(ItemPedidoRepository repository) {
        this.repository = repository;
    }


    public List<ItemPedido> todosItensPedidos() {
        return repository.findAll();
    }

    public void salvarItemPedido(ItemPedido itemPedido) {
        this.calcularPrecoTotal(itemPedido);
        repository.save(itemPedido);
    }

    public ItemPedido itemPedidoId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public boolean deletarItemPedido(Long id) {
        ItemPedido retorno = this.itemPedidoId(id);

        if (retorno != null) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public void atualizarItemPedido(ItemPedido antigo, ItemPedido novo) {
        antigo.setQuantidade(novo.getQuantidade());
        antigo.setProduto(novo.getProduto());
        this.calcularPrecoTotal(antigo);

        repository.save(antigo);
    }


    public void calcularPrecoTotal(ItemPedido itemPedido) {
        if (itemPedido.getProduto() == null || itemPedido.getQuantidade() <= 0) {
            throw new IllegalStateException("Produto ou quantidade inválidos para cálculo do preço total");
        }
        if (itemPedido.getProduto().getPreco() == null) {
            throw new IllegalStateException("Preço do produto não pode ser nulo");
        }

        BigDecimal precoTotal = itemPedido.getProduto()
                .getPreco()
                .multiply(BigDecimal.valueOf(itemPedido.getQuantidade()));

        itemPedido.setPrecoTotal(precoTotal);
    }
}
