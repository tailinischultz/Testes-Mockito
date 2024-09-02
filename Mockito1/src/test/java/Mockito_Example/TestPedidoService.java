package Mockito_Example;

import Mockito_Example.Modelo.Pedido;
import Mockito_Example.Modelo.Produto;
import Mockito_Example.Repository.PedidoRepository;
import Mockito_Example.Repository.ProdutoRepository;
import Mockito_Example.Service.PedidoService;
import Mockito_Example.Service.ProdutoService;
import org.junit.Assert;
import org.junit.Before;
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
    public void testCalcularValorPedidoComDesconto(){
        Produto produto = new Produto(0, "Produto 1", 20);
        
        Mockito.when(produtoServiceMock.calcularDesconto(Mockito.any(Produto.class))).thenReturn(5.0);
        Pedido pedido = new Pedido(0, produtoServiceMock);

        pedido.adicionarProduto(produto);
        
        Assert.assertEquals(15, pedido.calcularTotalComDesconto(), 0.01);
    }
    

    @Test // Verifica se o método calcularDesconto foi chamado uma vez
    public void testCalcularDescontoChamado() {
        Produto produto = new Produto(1, "Produto A", 100.0);
        Mockito.when(produtoServiceMock.calcularDesconto(Mockito.any(Produto.class))).thenReturn(10.0);

        Pedido pedido = new Pedido(1, produtoServiceMock);
        pedido.adicionarProduto(produto);
        pedido.calcularTotalComDesconto();

        Mockito.verify(produtoServiceMock, Mockito.times(1)).calcularDesconto(produto);
    }
     
    @Test
    public void testProdutoInteracao(){
        Pedido pedido = new Pedido(0, produtoServiceMock);
        pedido.adicionarProduto(produtoMock);

        Mockito.verify(produtoMock, Mockito.timeout(1)).getId();
        Mockito.verify(produtoMock, Mockito.never()).setPreco(Mockito.anyDouble());
    }
    
    @Test
    public void testCalcTotalPedido(){
        Produto produto1 = new Produto(1, "Produto 1", 10.0);
        Produto produto2 = new Produto(2, "Produto 2", 20.0);

        //Teste integrando o método de busca pelo ID
        Mockito.when(produtoRepositoryMock.findById(1)).thenReturn(produto1);
        Mockito.when(produtoRepositoryMock.findById(2)).thenReturn(produto2);

        Pedido pedido = new Pedido(1, produtoServiceMock);
        pedido.adicionarProduto(produtoRepositoryMock.findById(1));
        pedido.adicionarProduto(produtoRepositoryMock.findById(2));

        double totalEsperado = 30.0;
        Assert.assertEquals(totalEsperado, pedido.calcularTotal(), 0);

        //Verifica se os métodos findById foram chamados com os IDs corretos
        Mockito.verify(produtoRepositoryMock, Mockito.times(1)).findById(1);
        Mockito.verify(produtoRepositoryMock, Mockito.times(1)).findById(2);
    }

}
