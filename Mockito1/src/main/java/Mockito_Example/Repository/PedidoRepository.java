package Mockito_Example.Repository;

import Mockito_Example.Modelo.Pedido;

public interface PedidoRepository {
    void add(Pedido pedido);
    Pedido findById(int id);
}