package Mockito_Example.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<Produto> produtos = new ArrayList<>();
    private boolean pago = false;

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

    public void pagar() {
        this.pago = true;
    }

    public boolean isPago() {
        return pago;
    }
}