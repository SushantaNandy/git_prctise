package sus.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class standalone_test3 {
	static ExtentReports extent;
	
	@BeforeTest
	public void config() {
		String path = System.getProperty("user.dir") + "/target/index.html";

		//String path=System.getProperty("/Users/apple/Downloads/selenium_whole_project-master/sus/target"+"index.html");
		ExtentHtmlReporter reporter=new ExtentHtmlReporter(path);
		reporter.config().setReportName("basic_report");
		reporter.config().setDocumentTitle("title_of_report");
		extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("SDET", "SUSHANTA NANDY");
	}
	
	@Test()
	public static void tc1() throws InterruptedException{
	ExtentTest test=extent.createTest("Invocation of driver");
		WebDriverManager.chromiumdriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get("https://rahulshettyacademy.com/client");
		System.out.println(driver.getTitle());
		driver.close();
		test.fail("intentionally failing the case");
		extent.flush();
}
}