package Utils;

import Test.RestfulBookerTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigExcelIO {

    public  static final Logger logger = LogManager.getLogger(RestfulBookerTest.class);
    static protected Properties properties=new Properties();
    static FileInputStream fis = null;
    public static ExcelFileIO reader = null;

    static {

        try {
            String strProjectLoc = System.getProperty("user.dir");
            reader = new ExcelFileIO(strProjectLoc+ "\\src\\test\\java\\TestData\\Payload.xlsx");
            System.out.println("Excel File location"+strProjectLoc+ "//src//test//java//TestData//Payload.xlsx");
        }
        catch(Exception e) {

            logger.error(e.getMessage());
        }
    }

}
