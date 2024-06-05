package test.java.com.ACMD.app.Engine_Layer;
import java.io.File;
import java.util.Vector;

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

    @Test
    public void testGetFilesNameIn(){
        String dir = "\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Engine_Layer\\Entita\\";
        String thisDir = ParsePath.getPath(dir, "");
        Vector<String> files = ParsePath.getFilesNameIn(dir);

        for(String file: files){
            Assert.assertTrue(new File(thisDir+file).exists());
        }
    }
    
}
