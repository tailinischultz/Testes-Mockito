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
        Pedido pedido = pedidoRepository.buscarPorID(pedidoId);
        Produto produto = produtoRepository.buscarPorID(produtoId);
        if (pedido != null && produto != null) {
            pedido.addProduto(produto);
        }
    }

    public double calcularTotalDoPedido(int pedidoId) {
        Pedido pedido = pedidoRepository.buscarPorID(pedidoId);
        if (pedido != null) {
            return pedido.calcularTotal();
        }
        return 0;
    }

    public void pagarPedido(int pedidoId) {
        Pedido pedido = pedidoRepository.buscarPorID(pedidoId);
        if (pedido != null) {
            pedido.pagar();
            pedidoRepository.add(pedido);
        }
    }
    
}

