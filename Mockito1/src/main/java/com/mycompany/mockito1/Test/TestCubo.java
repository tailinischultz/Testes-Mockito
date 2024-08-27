
package com.mycompany.mockito1.Test;

import com.mycompany.mockito1.Cubo;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class TestCubo {
    
    @Test
    public void testGetArea(){
        
        Mockito.when(this);
        
        Cubo cubo = new Cubo(2);
        Assert.assertEquals(8, cubo.getVolume(), 0);
    }   
}
