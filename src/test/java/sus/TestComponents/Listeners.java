package sus.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	
	//we have to create the test and then we have to flush it.
	ExtentTest test;
	ExtentReports extent=ExtentReporterNG.getReporterObject();
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		 test=extent.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.PASS, "PASS");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String Path;
		
		test.fail(result.getThrowable());
		
		//initilisating life of driver from failed testcases
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().
					getField("driver").get(result.getInstance());
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//taking screen shot
	try {
		// this getscreenshot method returining me back the path
				//in which the screen shot is saved
		 Path=getScreenShot(result.getMethod().getMethodName(),driver);
		 test.addScreenCaptureFromPath(Path,result.getMethod().getMethodName());
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//after I got the path 
	
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
		
	}

}
