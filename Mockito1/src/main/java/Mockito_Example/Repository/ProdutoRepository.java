package Mockito_Example.Repository;

import Mockito_Example.Modelo.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {

    List<Produto> produtos = new ArrayList();

    public Produto findById(int id) {
        for (Produto prod : produtos) {
            if (prod.getId() == id) {
                return prod;
            }
        }
        return null;
    }

    public void add(Produto produto) {
        produtos.add(produto);
    }
}
