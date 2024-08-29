package Mockito_Example.Repository;

import Mockito_Example.Modelo.Pedido;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepository {

    List<Pedido> pedidos = new ArrayList();

    public void add(Pedido pedido) {
        pedidos.add(pedido);
    }

    public Pedido findById(int id) {
        for (Pedido ped : pedidos) {
            if (ped.getId() == id) {
                return ped;
            }
        }
        return null;
    }
}
