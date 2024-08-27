
package com.mycompany.mockito1.Test;

import com.mycompany.mockito1.Triangulo;
import org.junit.Assert;
import org.junit.Test;

public class TestTriangulo {
    
    @Test
    public void testGetArea(){
        Triangulo triangulo = new Triangulo(5, 2);
        Assert.assertEquals(5, triangulo.getArea(), 0);
    }
    
}
