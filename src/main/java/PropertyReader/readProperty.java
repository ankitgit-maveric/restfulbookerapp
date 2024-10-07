package PropertyReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class readProperty {

    private String propertyFilePath= System.getProperty("user.dir")+"\\src\\main\\resources\\execution.properties";
    public String readPropertyFile(String PropertyName) {
        String prop=null;
        try {
            Properties property = new Properties();
            // Create a FileInputStream for the file
            FileInputStream fileInputStream = new FileInputStream(propertyFilePath);
            //Load the property file
            property.load(fileInputStream);
            // Read property from File
            prop= property.getProperty(PropertyName);

            // Close the FileInputStream
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return prop;
    }
}


