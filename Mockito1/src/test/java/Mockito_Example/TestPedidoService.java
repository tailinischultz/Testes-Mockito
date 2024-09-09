package Mockito_Example;

import Mockito_Example.Modelo.Pedido;
import Mockito_Example.Modelo.Produto;
import Mockito_Example.Repository.PedidoRepository;
import Mockito_Example.Repository.ProdutoRepository;
import Mockito_Example.Service.PedidoService;
import Mockito_Example.Service.ProdutoService;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestPedidoService {

    @Mock
    private ProdutoRepository produtoRepositoryMock;

    @Mock
    private PedidoRepository pedidoRepositoryMock;

    @Mock
    private ProdutoService produtoServiceMock;
    
    @InjectMocks
    private PedidoService pedidoService;
    
    
    @Test
    public void testBuscarProdutoByIDEspecifico() throws Exception{
        Produto produto = new Produto(0, "Produto 1", 10);
        Mockito.when(produtoRepositoryMock.buscarPorID(0)).thenReturn(produto);
        
        Produto prodResult = produtoRepositoryMock.buscarPorID(0);
        
        Assert.assertEquals(produto, prodResult);
    }
    
    @Test
    public void testBuscarProdutoByQualquerID() throws Exception{
        Produto produto = new Produto(0, "Produto 1", 10);
        Mockito.when(produtoRepositoryMock.buscarPorID(Mockito.anyInt())).thenReturn(produto);
        
        Produto prodResult = produtoRepositoryMock.buscarPorID(10);
        Assert.assertEquals(produto, prodResult);
    }
   
    @Test
    public void testCalcTotalPedido() throws Exception{
        Produto produto1 = new Produto(1, "Produto 1", 10.0);
        Produto produto2 = new Produto(2, "Produto 2", 20.0);

        //Teste integrando o método de busca pelo ID
        Mockito.when(produtoRepositoryMock.buscarPorID(1)).thenReturn(produto1);
        Mockito.when(produtoRepositoryMock.buscarPorID(2)).thenReturn(produto2);

        Pedido pedido = new Pedido(1, produtoServiceMock);
        pedido.addProduto(produtoRepositoryMock.buscarPorID(1));
        pedido.addProduto(produtoRepositoryMock.buscarPorID(2));

        double totalEsperado = 30.0;
        Assert.assertEquals(totalEsperado, pedido.calcularTotal(), 0);

        //Verifica se os métodos findById foram chamados com os IDs corretos
        Mockito.verify(produtoRepositoryMock, Mockito.times(1)).buscarPorID(1);
        Mockito.verify(produtoRepositoryMock, Mockito.times(1)).buscarPorID(2);
    }
    
    @Test
    public void testPagamentoPedido() throws Exception {
        //Simula o comportamento dos métodos do mock
        Produto produto = new Produto(1, "Produto A", 100.0);
        Produto produto2 = new Produto(2, "Produto B", 50.0);
        Pedido pedido = new Pedido(1, produtoServiceMock);

        //Simula os repositórios retornando os produtos e o pedido
        Mockito.when(produtoRepositoryMock.buscarPorID(1)).thenReturn(produto);
        Mockito.when(produtoRepositoryMock.buscarPorID(2)).thenReturn(produto2);
        Mockito.when(pedidoRepositoryMock.buscarPorID(1)).thenReturn(pedido);

        //Adiciona os produtos ao pedido através do serviço
        pedidoService.adicionarProdutoAoPedido(1, 1);
        pedidoService.adicionarProdutoAoPedido(1, 2);

        //Verifica o total antes do pagamento
        double totalAntesPagamento = pedidoService.calcularTotalDoPedido(1);
        Assert.assertEquals(150.0, totalAntesPagamento, 0.01);

        //Realiza o pagamento
        pedidoService.pagarPedido(1);

        //Verifica se o pedido foi marcado como pago
        Assert.assertTrue(pedido.isPago());

        //Verifica se o pedido pago foi persistido no repositório
        Mockito.verify(pedidoRepositoryMock, Mockito.times(1)).add(pedido);
    }

}
