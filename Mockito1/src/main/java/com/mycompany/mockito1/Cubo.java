/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mockito1;

/**
 *
 * @author 11835692974
 */
public class Cubo {
    
    private double tamanho;

    public Cubo(double tamanho) {
        this.tamanho = tamanho;
    }
    
    public double getVolume(){
        return (getTamanho() * getTamanho() * getTamanho());
    }

    public double getTamanho() {
        return tamanho;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return "Cubo{" + "tamanho=" + tamanho + '}';
    }
   
}
