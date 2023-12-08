package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReporterNG {
	
	static ExtentReports extent;
	
		public static ExtentReports getReporterObject() {
			String path = System.getProperty("user.dir") + "/target/index.html";

			//String path=System.getProperty("/Users/apple/Downloads/selenium_whole_project-master/sus/target"+"index.html");
			ExtentHtmlReporter reporter=new ExtentHtmlReporter(path);
			reporter.config().setReportName("basic_report");
			reporter.config().setDocumentTitle("title_of_report");
			extent=new ExtentReports();
			extent.attachReporter(reporter);
			extent.setSystemInfo("SDET", "SUSHANTA NANDY");
			return extent;
		}
}
