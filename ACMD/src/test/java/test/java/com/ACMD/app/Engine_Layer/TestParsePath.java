package test.java.com.ACMD.app.Engine_Layer;
import org.junit.Assert;
import org.junit.Test;

import com.ACMD.app.Engine_Layer.ParsePath;

public class TestParsePath {
    @Test
    public void testGetPath(){
        String path = ParsePath.getPath("/main/", "test");

        if(System.getProperty("os.name").contains("Linux")){
            Assert.assertTrue(path.contains("/ACMD/main/test"));
        }
        else{
            Assert.assertTrue(path.contains("\\ACMD\\main\\test"));
        }

        path = ParsePath.getPath("\\main\\", "test");

        if(System.getProperty("os.name").contains("Linux")){
            Assert.assertTrue(path.contains("/ACMD/main/test"));
        }
        else{
            Assert.assertTrue(path.contains("\\ACMD\\main\\test"));
        }

        
    }
    
}
