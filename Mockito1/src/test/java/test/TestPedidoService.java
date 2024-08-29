package test;

import Mockito_Example.Modelo.Pedido;
import Mockito_Example.Modelo.Produto;
import Mockito_Example.Repository.PedidoRepository;
import Mockito_Example.Repository.ProdutoRepository;
import Mockito_Example.Service.PedidoService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class TestPedidoService {

    ProdutoRepository produtoRepository = Mockito.mock(ProdutoRepository.class);
    PedidoRepository pedidoRepository = Mockito.mock(PedidoRepository.class);
    @Test
    public void testBuscarProdutoByID(){
        Produto produto = new Produto(0, "Produto 1", 10);
        Mockito.when(produtoRepository.findById(Mockito.anyInt())).thenReturn(produto);
        
        Produto prodResult = produtoRepository.findById(0);
        
        Assert.assertEquals(produto, prodResult);
    }
    
    @Test
    public void testNovoPedido(){


    }
    
    @Test
    public void testCalcTotalPedido(){
        Produto produto1 = new Produto(1, "Produto 1", 10.0);
        Produto produto2 = new Produto(2, "Produto 2", 20.0);

        //Teste integrando o método de busca pelo ID
        Mockito.when(produtoRepository.findById(1)).thenReturn(produto1);
        Mockito.when(produtoRepository.findById(2)).thenReturn(produto2);

        Pedido pedido = new Pedido();
        pedido.adicionarProduto(produtoRepository.findById(1));
        pedido.adicionarProduto(produtoRepository.findById(2));


        double totalEsperado = 30.0;
        Assert.assertEquals(totalEsperado, pedido.calcularTotal(), 0);

        //Verifica se os métodos findById foram chamados com os IDs corretos
        Mockito.verify(produtoRepository, Mockito.times(1)).findById(1);
        Mockito.verify(produtoRepository, Mockito.times(1)).findById(2);
    }
    
    @Test
    public void testPagamentoPedido(){
        
    }
}
