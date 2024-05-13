package test.java.com.ACMD.app;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.ACMD.app.Salva.awsClient;




/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void evaluatesExpression(){
        
        File f = new File("Test.json");
        try {
            f.createNewFile();
            awsClient.Upload(f);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        awsClient.Upload(f);
        
    }
}
