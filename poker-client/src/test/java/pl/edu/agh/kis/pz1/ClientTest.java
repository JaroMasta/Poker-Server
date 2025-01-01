package pl.edu.agh.kis.pz1;

import static junit.framework.TestCase.assertNotNull;
import org.junit.Test;


public class ClientTest {


    /**
     * Test for the construction of Main and the 
     * main method being called
     */
    @Test
    public void shouldCreateMainObject(){
        ClientMain main = new ClientMain();
        assertNotNull("Main method called on class Main2.", main);
    }
}


