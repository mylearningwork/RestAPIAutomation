package utilities;
//package com.cover4PM.utils;
///*package com.utils;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//public class TestListener implements ITestListener {
//
//	WebDriver driver = null;
//	GenericUtilities util;
//	
//	String filePath =  System.getProperty("user.dir")+"\\test-output\\screenshot\\" ;
//	
//	@Override
//	public void onTestSuccess(ITestResult result) {
//
//		System.out.println("***** Success " + result.getName()
//				+ " test has passed *****");
//		String methodName = result.getName().toString().trim();
//		util.takeScreenShot(methodName);
//		
//
//	}
//	
//	@Override
//	public void onTestFailure(ITestResult result) {
//
//		System.out.println("***** Error " + result.getName()
//				+ " test has failed *****");
//		String methodName = result.getName().toString().trim();
//		util.takeScreenShot(methodName);
//	}
//	
//	public void takeScreenShot(String methodName) {
//		// get the driver
//		driver= new TestBase().getDriver();
//		driver.manage().window().maximize();
//		Calendar calendar = Calendar.getInstance();
//		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
//		String destFileName = formater.format(calendar.getTime());
//		File scrFile = ((TakesScreenshot) driver)
//				.getScreenshotAs(OutputType.FILE);
//		// The below method will save the screen shot in d drive with test
//		// method name
//	
//		
//		try {
//			FileUtils.copyFile(scrFile,
//					new File(filePath + methodName + destFileName+".png"));
//			System.out.println("***Placed screen shot in " + filePath + " ***");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void onTestSkipped(ITestResult result) {
//	}
//
//	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//	}
//
//	public void onStart(ITestContext context) {
//	}
//
//	public void onFinish(ITestContext context) {
//	}
//
//	public void onTestStart(ITestResult result) {
//	}
//}
//*/
//
//
//
//
//
//
//package utils;
//
//import java.io.File;
//import java.io.IOException;
//
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.testng.IClassListener;
//import org.testng.IMethodInstance;
//import org.testng.ITestClass;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//import org.testng.Reporter;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.MediaEntityModelProvider;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.markuputils.ExtentColor;
//import com.aventstack.extentreports.markuputils.MarkupHelper;
//import com.aventstack.extentreports.model.Media;
//import com.aventstack.extentreports.model.MediaType;
//import com.aventstack.extentreports.model.ScreenCapture;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//
//import BaseClasses.Driver;
//
//public class TestListener implements ITestListener, IClassListener {
//
//	private static ExtentReports extent;
//	private static ExtentTest test;
//	private static ExtentHtmlReporter htmlReporter;
//	private static String filePathex = System.getProperty("user.dir")
//			+ "//Report//extentreport.html"; 
//												
//												 
//	private static Driver webDriver = new Driver();
//	private static Media med;
//	private static MediaEntityModelProvider mp;
//	String screen, screen1;
//	WebDriver driver;
//	String filePath = System.getProperty("user.dir")
//			+ "//Failure Screenshots//"; 
//											
//											 
//	int count = 1, count1 = 1;
//	String line = "<td class=\"result\">";
//	String method[] = new String[100];
//	String method1[] = new String[100];
//	ITestResult result;
//
//	public void onStart(ITestContext context) {
//		System.out.println("Suite start");
//	}
//
//
//	public void onBeforeClass(ITestClass testclass) {
//		extent = getExtent();
//		test = extent.createTest(testclass.getRealClass().getSimpleName());
//		System.out.println("Class beginning " + testclass.getRealClass().getSimpleName());
//	}
//
//	public void onTestStart(ITestResult result) {
//		System.out.println(" Test case is started");
//	}
//
//	public void onTestSuccess(ITestResult result) {
//		test.createNode(result.getMethod().getMethodName(), result.getMethod().getDescription())
//				.pass(MarkupHelper.createLabel("PASS", ExtentColor.GREEN)).log(Status.PASS, result.getTestName());
//		System.out.println("Test case is executed successfully");
//	}
//
//	public void onTestSkipped(ITestResult result) {
//		System.out.println("***** Skip " + result.getName() + " test has failed *****");
//		method1[count1 - 1] = result.getName().toString().trim();
//		this.result = result;
//		takeScreenShot(method1[count1 - 1], webDriver.getCurrentWebDriver());
//		med = new ScreenCapture();
//		med.setMediaType(MediaType.IMG);
//		med.setPath(screen1);
//		mp = new MediaEntityModelProvider(med);
//		test.createNode(result.getMethod().getMethodName(), result.getMethod().getDescription())
//				.skip(MarkupHelper.createLabel("SKIP", ExtentColor.BLUE))
//				.skip("Screenshot " + result.getMethod().getMethodName(), mp).log(Status.SKIP, result.getThrowable());
//		count++;
//		System.out.println("Test case is skipped");
//	}
//
//	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//	}
//
//	public void onTestFailure(ITestResult result) {
//		System.out.println("***** Error " + result.getName() + " test has failed *****");
//		method[count - 1] = result.getName().toString().trim();
//		this.result = result;
//		takeScreenShot(method[count - 1], webDriver.getCurrentWebDriver());
//		med = new ScreenCapture();
//		med.setMediaType(MediaType.IMG);
//		med.setPath(screen1);
//		mp = new MediaEntityModelProvider(med);
//		test.createNode(result.getMethod().getMethodName(), result.getMethod().getDescription())
//				.fail(MarkupHelper.createLabel("FAIL", ExtentColor.RED))
//				.fail("Screenshot " + result.getMethod().getMethodName(), mp).log(Status.FAIL, result.getThrowable());
//		count++;
//		System.out.println("Test case is failed");
//	}
//
//	public void onAfterClass(ITestClass testclass) {
//		System.out.println("Class ending" + testclass.getRealClass().getSimpleName());
//	}
//
//	public void onFinish(ITestContext context) {
//		extent.flush();
//		System.out.println("Suite Finish");
//	}
//
//	public void takeScreenShot(String methodName, WebDriver driver) {
//		this.driver = driver;
//		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		try {
//			File scrFile2 = new File(filePath + methodName + ".png");
//			System.setProperty("org.uncommons.reportng.escape-output", "false");
//			FileUtils.copyFile(scrFile, scrFile2);
//			System.out.println("***Placed screen shot in " + filePath + " ***");
//			screen1 = scrFile2.toString();
//			screen = "<img src='" + scrFile2.toString() + "' width='200' height='200'  > ";
//			Reporter.setEscapeHtml(false);
//			Reporter.log(screen);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static ExtentReports getExtent() {
//		if (extent == null) {
//			extent = new ExtentReports();
//			extent.setSystemInfo("Name", "Chitra");
//			extent.setSystemInfo("Browser", Driver.getBrowserType());
//			extent.attachReporter(getHtmlReporter());
//		}
//		return extent;
//	}
//
//	private static ExtentHtmlReporter getHtmlReporter() {
//		htmlReporter = new ExtentHtmlReporter(filePathex);
//		htmlReporter.config().setChartVisibilityOnOpen(true);
//		htmlReporter.config().setTheme(Theme.DARK);
//		htmlReporter.config().setDocumentTitle("PG&E Automation");
//		htmlReporter.config().setReportName("PG&E Automation Report");
//		return htmlReporter;
//	}
//
//	@Override
//	public void onAfterClass(ITestClass arg0, IMethodInstance arg1) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void onBeforeClass(ITestClass arg0, IMethodInstance arg1) {
//		// TODO Auto-generated method stub
//		
//	}
//
//}
