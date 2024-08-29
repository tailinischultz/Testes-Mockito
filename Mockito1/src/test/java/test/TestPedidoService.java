package test;

import Mockito_Example.Modelo.Produto;
import Mockito_Example.Repository.ProdutoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class TestPedidoService {
    
    ProdutoRepository produtoRepository = Mockito.mock(ProdutoRepository.class);
    
    @Test
    public void testBuscarProdutoByID(){
        Produto produto = new Produto(0, "Produto 1", 10);
        Mockito.when(produtoRepository.findById(Mockito.anyInt())).thenReturn(produto);
        
        Produto prodResult = produtoRepository.findById(0);
        
        Assert.assertEquals(produto, prodResult);
    }
    
    @Test
    public void testAddProduto(){
        
    }
    
    @Test
    public void testNovoPedido(){
        
    }
    
    @Test
    public void testCalcTotalPedido(){
        
    }
    
    @Test
    public void testPagamentoPedido(){
        
    }
}
