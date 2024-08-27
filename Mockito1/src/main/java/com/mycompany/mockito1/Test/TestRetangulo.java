
package com.mycompany.mockito1.Test;

import com.mycompany.mockito1.Retangulo;
import org.junit.Assert;
import org.junit.Test;

public class TestRetangulo {
    
    @Test
    public void testGetArea(){
        Retangulo retangulo = new Retangulo(2, 5);
        Assert.assertEquals(10, retangulo.getArea(), 0);
    }
}
