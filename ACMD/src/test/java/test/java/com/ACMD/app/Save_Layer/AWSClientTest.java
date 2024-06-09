package test.java.com.ACMD.app.Save_Layer;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.ACMD.app.Save_Layer.awsClient;




/**
 * Unit test for simple App.
 */
public class AWSClientTest 
{

    
    public void UploadTest(){
        
        File f = new File("Test.json");
        try {
            f.createNewFile();
            //awsClient.Upload(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //awsClient.Upload(f);
        
    }
}
