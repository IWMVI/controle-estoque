package br.edu.fateczl.controle_estoque.controller;

import br.edu.fateczl.controle_estoque.dto.ItemPedidoDto;
import br.edu.fateczl.controle_estoque.model.ItemPedido;
import br.edu.fateczl.controle_estoque.model.Pedido;
import br.edu.fateczl.controle_estoque.model.Produto;
import br.edu.fateczl.controle_estoque.service.ItemPedidoService;
import br.edu.fateczl.controle_estoque.service.PedidoService;
import br.edu.fateczl.controle_estoque.service.ProdutoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/itens")
public class ItemPedidoController {

    @Autowired
    private final ItemPedidoService itemService;
    private final ProdutoService produtoService;
    private final PedidoService pedidoService;

    public ItemPedidoController(ItemPedidoService itemService, ProdutoService produtoService, PedidoService pedidoService) {
        this.itemService = itemService;
        this.produtoService = produtoService;
        this.pedidoService = pedidoService;
    }


    @GetMapping
    public ModelAndView listarItensPedidos() {
        ModelAndView mv = new ModelAndView("itempedido/ItemPedidoHome");
        mv.addObject("itens", itemService.todosItensPedidos());
        return mv;
    }

    @GetMapping("/adicionar")
    public ModelAndView exibirFormularioCadastro() {
        ModelAndView mv = new ModelAndView("itempedido/ItemPedidoCadastrar");
        mv.addObject("itemPedidoDto", new ItemPedidoDto());
        mv.addObject("produtos", produtoService.apenasProdutosAtivos());
        return mv;
    }

    @PostMapping("/adicionar")
    @Transactional
    public ModelAndView salvarCadastroItemPedido(@Valid ItemPedidoDto requisicao, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("itempedido/ItemPedidoCadastrar");
            mv.addObject("itemPedidoDto", requisicao);
            mv.addObject("produtos", produtoService.apenasProdutosAtivos());
            return mv;
        }

        ItemPedido itemPedidoNovo = dtoParaItemPedido(requisicao);
        itemService.salvarItemPedido(itemPedidoNovo);

        return new ModelAndView("redirect:/itens");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView exibirFormularioEdicao(@PathVariable Long id) {
        ItemPedido itemBuscado = itemService.itemPedidoId(id);
        if (itemBuscado == null) {
            return new ModelAndView("redirect:/itens");
        }

        ModelAndView mv = new ModelAndView("itempedido/ItemPedidoEditar");
        mv.addObject("itemPedidoDto", itemBuscado);
        mv.addObject("produtos", produtoService.apenasProdutosAtivos());
        return mv;
    }

    @PutMapping("/editar/{id}")
    @Transactional
    public ModelAndView salvarEdicaoItemPedido(@PathVariable Long id, @Valid ItemPedidoDto requisicao, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("itempedido/ItemPedidoEditar");
            mv.addObject("itemPedidoDto", requisicao);
            mv.addObject("produtos", produtoService.apenasProdutosAtivos());
            return mv;
        }

        ItemPedido itemPedidoAntigo = itemService.itemPedidoId(id);
        if (itemPedidoAntigo != null) {
            itemService.atualizarItemPedido(itemPedidoAntigo, this.dtoParaItemPedido(requisicao));
        }

        return new ModelAndView("redirect:/itens");
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public String excluirItemPedido(@PathVariable Long id) {
        itemService.deletarItemPedido(id);
        return "redirect:/itens";
    }


    private ItemPedido dtoParaItemPedido(ItemPedidoDto itemPedidoDto) {
        Produto produto = produtoService.produtoId(itemPedidoDto.getProduto().getId());
        if (produto == null)
            throw new IllegalArgumentException("Produto não encontrado");

        Pedido pedido = pedidoService.pedidoId(1L);
        if (pedido == null)
            throw new IllegalArgumentException("Pedido não encontrado");

        ItemPedido item = new ItemPedido();
        item.setQuantidade(itemPedidoDto.getQuantidade());
        item.setProduto(produto);
        item.setPedido(pedido);

        return item;
    }
}
