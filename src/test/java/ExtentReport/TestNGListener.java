package ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener{
    private static ExtentReports extentReports;
    public static ThreadLocal<ExtentTest> extentTest=   new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        String fileName= ExtentReportManager.getReportNameWithTimeStamp();
        String fullReportPath= System.getProperty("user.dir") + "\\reports\\" +fileName;
        extentReports=  ExtentReportManager.createInstance(fullReportPath,"Restful Booker App","Test Execution Report");

      //  ITestListener.super.onStart(context);
    }


    @Override
    public void onFinish(ITestContext context) {
        if(extentReports != null){
            extentReports.flush();
        }
        ITestListener.super.onFinish(context);
    }

    @Override
    public void onTestStart(ITestResult result) {

      ExtentTest test=  extentReports.createTest("Test Name "+result.getTestClass().getName()+"     -      "+result.getMethod().getMethodName());

      extentTest.set(test);
      //ITestListener.super.onTestStart(result);
    }


}
