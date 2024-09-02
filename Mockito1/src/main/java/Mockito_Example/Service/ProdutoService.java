/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mockito_Example.Service;

import Mockito_Example.Modelo.Produto;

public class ProdutoService {
    
    public double calcularDesconto(Produto produto) {
        return produto.getPreco() * 0.1; 
    }
}
