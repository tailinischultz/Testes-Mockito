package Mockito_Example.Repository;

import Mockito_Example.Modelo.Produto;

public interface ProdutoRepository {
    Produto findById(int id);
    void add(Produto produto);
}
