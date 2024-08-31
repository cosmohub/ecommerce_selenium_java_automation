package practiceautomation.AbstractComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterTestNG {
	
	public static ExtentReports getReport() {
		String path = System.getProperty("user.dir")+"\\Reports\\index.html";
		ExtentSparkReporter spark = new ExtentSparkReporter(path);
		spark.config().setDocumentTitle("Test Automation Report");
		spark.config().setReportName("Test Automation Report");
		spark.config().setTheme(Theme.DARK);
		
		ExtentReports report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Ecommerce Webshop", "QA Environment");
		return report;
	}

	

}
