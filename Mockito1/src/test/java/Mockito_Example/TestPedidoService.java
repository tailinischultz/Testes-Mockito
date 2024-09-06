package Mockito_Example;

import Mockito_Example.Modelo.Pedido;
import Mockito_Example.Modelo.Produto;
import Mockito_Example.Repository.PedidoRepository;
import Mockito_Example.Repository.ProdutoRepository;
import Mockito_Example.Service.PedidoService;
import Mockito_Example.Service.ProdutoService;
import org.junit.Assert;
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
    private PedidoService pedidoServiceMock;
    
    @Mock
    private ProdutoService produtoServiceMock;
    
    @Mock 
    private Produto produtoMock;
    
    @InjectMocks
    private PedidoService pedidoService;
    
    
    /* Substitui as linhas de @Mock e @InjectMocks, pois cria os mocks manualmente. 
    Útil qnd é preciso configurações mt específicas ou n pode utilizar anotações
    
    ProdutoRepository produtoRepository = Mockito.mock(ProdutoRepository.class);
    PedidoRepository pedidoRepository = Mockito.mock(PedidoRepository.class);
    */
    
    @Test
    public void testBuscarProdutoByID(){
        Produto produto = new Produto(0, "Produto 1", 10);
        Mockito.when(produtoRepositoryMock.findById(Mockito.anyInt())).thenReturn(produto);
        
        Produto prodResult = produtoRepositoryMock.findById(0);
        
        Assert.assertEquals(produto, prodResult);
    }
    
    @Test
    public void testCalcTotalPedido(){
        Produto produto1 = new Produto(1, "Produto 1", 10.0);
        Produto produto2 = new Produto(2, "Produto 2", 20.0);

        //Teste integrando o método de busca pelo ID
        Mockito.when(produtoRepositoryMock.findById(1)).thenReturn(produto1);
        Mockito.when(produtoRepositoryMock.findById(2)).thenReturn(produto2);

        Pedido pedido = new Pedido(1, produtoServiceMock);
        pedido.addProduto(produtoRepositoryMock.findById(1));
        pedido.addProduto(produtoRepositoryMock.findById(2));

        double totalEsperado = 30.0;
        Assert.assertEquals(totalEsperado, pedido.calcularTotal(), 0);

        //Verifica se os métodos findById foram chamados com os IDs corretos
        Mockito.verify(produtoRepositoryMock, Mockito.times(1)).findById(1);
        Mockito.verify(produtoRepositoryMock, Mockito.times(1)).findById(2);
    }
    
    @Test
    public void testPagamentoPedido() {
        //Simula o comportamento dos métodos do mock
        Produto produto = new Produto(1, "Produto A", 100.0);
        Produto produto2 = new Produto(2, "Produto B", 50.0);
        Pedido pedido = new Pedido(1, produtoServiceMock);

        //Simula os repositórios retornando os produtos e o pedido
        Mockito.when(produtoRepositoryMock.findById(1)).thenReturn(produto);
        Mockito.when(produtoRepositoryMock.findById(2)).thenReturn(produto2);
        Mockito.when(pedidoRepositoryMock.findById(1)).thenReturn(pedido);

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
