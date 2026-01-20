package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir") + "//reports/index.html";

//		Creates a Spark HTML reporter
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);

//		Report Name → shown inside the report
		reporter.config().setReportName("Test Report");

//		browser tab title
		reporter.config().setDocumentTitle("Report title");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Ankit");
		
//		this line serves as an entry point for each test, so we cannot write this line in each test.
//		extent.createTest("demo");
		
		return extent;
	}
}
