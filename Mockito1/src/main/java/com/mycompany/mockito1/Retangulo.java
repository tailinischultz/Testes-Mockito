
package com.mycompany.mockito1;

public class Retangulo {
    
    private double altura;
    private double base;

    public Retangulo(double altura, double base) {
        this.altura = altura;
        this.base = base;
    }
    
    public double getArea(){
        return getBase() * getAltura();
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    @Override
    public String toString() {
        return "Retangulo{" + "altura=" + altura + ", base=" + base + '}';
    }
}
