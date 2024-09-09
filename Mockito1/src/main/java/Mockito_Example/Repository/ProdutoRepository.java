package Mockito_Example.Repository;

import Mockito_Example.Modelo.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {

    private List<Produto> produtos = new ArrayList();

    public Produto buscarPorID(int id) throws Exception {
        for (Produto prod : produtos) {
            if (prod.getId() == id) {
                return prod;
            }
        }
        throw new Exception("Produto não encontrado com o ID: " + id);
    }

    public void add(Produto produto) {
        produtos.add(produto);
    }
}
