package com.mycompany.mockito1;

import com.mycompany.mockito1.Test.TestTriangulo;
import com.mycompany.mockito1.Test.TestCubo;
import com.mycompany.mockito1.Test.TestRetangulo;


public class Main {

    public static void main(String[] args) {
        
        Triangulo triangulo = new Triangulo(5, 2);
        System.out.println(triangulo.getArea());

        TestTriangulo testTriangulo = new TestTriangulo();
        testTriangulo.testGetArea();
        
        Retangulo retangulo = new Retangulo(2, 5);
        System.out.println(retangulo.getArea());
        
        TestRetangulo testRetangulo = new TestRetangulo();
        testRetangulo.testGetArea();
        
        Cubo cubo = new Cubo(2);
        System.out.println(cubo.getVolume());
        
        TestCubo testCubo = new TestCubo();
        testCubo.testGetArea();
    }
}
