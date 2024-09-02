package Mockito_Example.Modelo;

import Mockito_Example.Service.ProdutoService;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    
    private int id;
    private boolean pago = false;
    private List<Produto> produtos = new ArrayList<>();
    private ProdutoService produtoService;

    public Pedido(int id, ProdutoService produtoService) {
        this.id = id;
        this.produtoService = produtoService;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public double calcularTotal() {
        double total = 0;
        for (Produto produto : produtos) {
            total += produto.getPreco();
        }
        return total;
    }
    
     public double calcularTotalComDesconto() {
        double total = 0;
        for (Produto produto : produtos) {
            double desconto = produtoService.calcularDesconto(produto);
            total += produto.getPreco() - desconto;
        }
        return total;
    }

    public void pagar() {
        this.pago = true;
    }

    public boolean isPago() {
        return pago;
    }
}