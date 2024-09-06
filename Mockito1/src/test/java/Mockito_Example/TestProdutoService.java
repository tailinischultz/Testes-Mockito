/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mockito_Example;

import Mockito_Example.Modelo.Pedido;
import Mockito_Example.Modelo.Produto;
import Mockito_Example.Service.ProdutoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestProdutoService {

    @Mock
    private ProdutoService produtoServiceMock;

    @InjectMocks
    private Pedido pedido;

    @Test
    public void testCalcularValorPedidoComDesconto() {
        Produto produto = new Produto(0, "Produto 1", 20);

        Mockito.when(produtoServiceMock.calcularDesconto(Mockito.any(Produto.class))).thenReturn(5.0);

        pedido.addProduto(produto);

        Assert.assertEquals(15, pedido.calcularTotalComDesconto(), 0.01);
    }

    @Test // Verifica se o método calcularDesconto foi chamado uma vez
    public void testCalcularDescontoChamado() {
        Produto produto = new Produto(1, "Produto A", 100.0);
        Mockito.when(produtoServiceMock.calcularDesconto(Mockito.any(Produto.class))).thenReturn(10.0);

        pedido.addProduto(produto);
        pedido.calcularTotalComDesconto();

        Mockito.verify(produtoServiceMock, Mockito.times(1)).calcularDesconto(produto);
    }
}
