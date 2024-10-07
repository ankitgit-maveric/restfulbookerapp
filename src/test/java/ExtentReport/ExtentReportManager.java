package ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReportManager {

    public static ExtentReports extentReports;
    public static ExtentReports createInstance(String fileName, String reportName, String DocumentTitle){
        ExtentSparkReporter extentSparkReporter= new ExtentSparkReporter(fileName);
        extentSparkReporter.config().setReportName(reportName);
        extentSparkReporter.config().getDocumentTitle();
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setEncoding("utf-8");
         extentReports= new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        return extentReports;
    }


    public static String getReportNameWithTimeStamp(){
        DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        LocalDateTime localDateTime= LocalDateTime.now();
       String formattedTime=  dateTimeFormatter.format(localDateTime);
       String reportName= "TestReport" + formattedTime + ".html";
       return reportName;

    }


    public static void logPassDetail(String log){
        TestNGListener.extentTest.get().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));

    }

    public static void logFailureDetail(String log){
        TestNGListener.extentTest.get().fail(MarkupHelper.createLabel(log, ExtentColor.RED));

    }

    public static void logInfoDetail(String log){
        TestNGListener.extentTest.get().info(MarkupHelper.createLabel(log, ExtentColor.GREY));

    }
    public static void logWarningDetail(String log){
        TestNGListener.extentTest.get().pass(MarkupHelper.createLabel(log, ExtentColor.YELLOW));

    }
}
