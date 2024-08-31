package practiceautomation.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import practiceautomation.AbstractComponents.ExtentReporterTestNG;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentReports extReport = ExtentReporterTestNG.getReport();
	ExtentTest test;
	ThreadLocal<ExtentTest> extTest = new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
	    test = extReport.createTest(result.getMethod().getMethodName());
	    extTest.set(test);
	  }

	 public void onTestSuccess(ITestResult result) {
	    extTest.get().log(Status.PASS, "Test Case passed succesfully");
	  }

	  public void onTestFailure(ITestResult result) {
		  String filePath = null;
	   extTest.get().fail(result.getThrowable());
	   try {
		driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	} catch (Exception e) {
		e.printStackTrace();
	}
	   try {
		 filePath = takeScreenshot(result.getMethod().getMethodName(),driver);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   extTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	  }

	  
	  public void onFinish(ITestContext context) {
	    extReport.flush();
	  }
}
