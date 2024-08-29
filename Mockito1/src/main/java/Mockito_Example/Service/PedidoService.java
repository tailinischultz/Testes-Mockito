package Mockito_Example.Service;

import Mockito_Example.Modelo.Pedido;
import Mockito_Example.Modelo.Produto;
import Mockito_Example.Repository.PedidoRepository;
import Mockito_Example.Repository.ProdutoRepository;

public class PedidoService {
    private PedidoRepository pedidoRepository;
    private ProdutoRepository produtoRepository;

    public PedidoService() {
        this.pedidoRepository = new PedidoRepository();
        this.produtoRepository = new ProdutoRepository();
    }

    public void adicionarProdutoAoPedido(int pedidoId, int produtoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId);
        Produto produto = produtoRepository.findById(produtoId);
        if (pedido != null && produto != null) {
            pedido.adicionarProduto(produto);
        }
    }

    public double calcularTotalDoPedido(int pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId);
        if (pedido != null) {
            return pedido.calcularTotal();
        }
        return 0;
    }

    public void pagarPedido(int pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId);
        if (pedido != null) {
            pedido.pagar();
            pedidoRepository.add(pedido);
        }
    }
}

