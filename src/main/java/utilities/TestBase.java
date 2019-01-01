package utilities;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.log4j.Logger;
import org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {

	public static String log4jPropertyFilePath = System.getProperty("user.dir")
			+ "\\src\\main\\resources\\log4j.properties";
	public static String g_strDownloadPath = System.getProperty("user.dir") + "\\Resources\\Downloads";
	public final static Logger logger = Logger.getLogger(TestBase.class.getName());
	public static ExtentReports extentReport;
	public static ExtentTest eTest;
	public static Properties prop;
	public FileInputStream fis = null;
	static String chromeDriverPath = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
	static String gerkoDriverPath = System.getProperty("user.dir") + "\\drivers\\geckodriver.exe";
	static String extentReportPath = System.getProperty("user.dir") + "\\extentReports";
	static String downloadsFolderPart = System.getProperty("user.dir") + "\\Resources\\Downloads";
	// static String extentConfigFilePath = System.getProperty("user.dir") +
	// "\\src\\main\\resources\\extent-config.xml";
	// LoginPage loginPage;
	static Log log;

	public static WebDriver driver = null;
	// Latest Element which has been found and used in findAnd... Method
	public static WebElement g_eleLatest = null;
	// Default Max wait time in seconds
	public static int g_nMaxWaitTime = 60;
	// Default Min wait time in seconds
	public static int g_nMinWaitTime = 3;
	// Default No wait time in seconds
	public static int g_nNoWaitTime = 1;
	// Max wait time in seconds for Error messages
	public static int g_nMaxErrMsgWaitTime = 3;
	// Sleep time in milliseconds between steps
	public static int g_nSleepTime = 3000;
	// SeleniumUtil Globals

	static {

		extentReport = new ExtentReports(System.getProperty("user.dir") + "\\extentReports\\"
				+ new SimpleDateFormat("dd_mm_yyyy_hh_mm_ss").format(Calendar.getInstance().getTime()) + ".html",
				false);
		
		
		// extentReport.loadConfig(new File(extentConfigFilePath));
		extentReport.addSystemInfo(" App env", "Dev2");
		extentReport.addSystemInfo("Tester", "Alok");

	}

	public TestBase() {

		prop = new Properties();
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties");
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// This will initialize page factory web elements of the class who extends this
		// class.
		// PageFactory.initElements(driver, this);
	}

	public static WebDriver getDriver() {
		return driver;
	}

	/*
	 * @BeforeTest() public void driverAndExtentReportSetup1() throws Exception {
	 * 
	 * //Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe"); //
	 * Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
	 * Runtime.getRuntime().exec("taskkill /F /IM Excel.exe");
	 * 
	 * //System.out.println("Killed process chromedriver.exe"); //
	 * System.out.println("Killed process chrome.exe");
	 * System.out.println("Killed process excel.exe");
	 * 
	 * logger.info("Configuring extent report and launching browsser.");
	 * 
	 * launchBrowser();
	 * 
	 * 
	 * }
	 */

	@BeforeMethod()
	public void driverAndExtentReportSetup(Method method) throws Exception {

		eTest = extentReport.startTest(method.getName());
		eTest.assignCategory("API Testing");
		logger.info(method.getName() + " test started");

	}

	@AfterMethod()
	public void afterMethod(ITestResult result) throws InterruptedException {
		getResult(result);

		if (result.getStatus() == ITestResult.SUCCESS) {
			//String screenshot = Utils.takeScreenShot(result.getName());
			//String image = eTest.addScreenCapture(screenshot);
			eTest.log(LogStatus.PASS, result.getName()+ " has passed.");
		}
		else if (result.getStatus() == ITestResult.FAILURE) {
			Thread.sleep(2000);
			//String screenshot = Utils.takeScreenShot(result.getName());
		//	String image = eTest.addScreenCapture(screenshot);
			eTest.log(LogStatus.FAIL,result.getName()+" test has failed");
		}
		// log.info("Quitting driver and closing browser");
		// driver.quit();
	}

	@BeforeClass

	public void thisClassTestStarted() {
		logger.info("***** Test case execution of Class " + getClass().getName() + "started******");
	}

	@AfterClass(alwaysRun = true)
	public void endTest() {
		logger.info("***************All test cases of Class " + getClass().getName() + " executed******");

	}

	@BeforeSuite()
	public void driverAndExtentReportSetup() throws Exception {
		try {
			
		}

		catch (Exception e) {
		
			e.printStackTrace();
		}
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() throws Exception {
		// to delete directory
		FileUtils.deleteDirectory(extentReportPath);
		FileUtils.mkdir(extentReportPath);
		// extent report related code below
		extentReport.endTest(eTest);
		extentReport.flush();
		Thread.sleep(1000);

		// to send extent report in email.
		/*
		 * try {
		 * SendMailSSLWithAttachment.sendReportByEmail(prop.getProperty("fromEmail"),
		 * prop.getProperty("fromEmailPassword"), prop.getProperty("toEmail")); }
		 * 
		 * catch(Exception e) {
		 * 
		 * log.error(" Emailing of report failed :  "+e); }
		 */

		logger.info("*********** All test classes run. Extent report generated and put in : " + extentReportPath
				+ " .Quitting browser**********");

	}

	// extent report related
	public void getResult(ITestResult result) {

		if (result.getStatus() == ITestResult.SUCCESS) {

			// eTest.log(LogStatus.PASS, result.getName() + " test is pass");

		} else if (result.getStatus() == ITestResult.SKIP) {

			eTest.log(LogStatus.SKIP, result.getName() + " Test is skipped and reason is : " + result.getThrowable());

		} else if (result.getStatus() == ITestResult.FAILURE) {

			// eTest.log(LogStatus.FAIL, result.getName() + " Test is failed and reason is :
			// " + result.getThrowable());
		}

		else if (result.getStatus() == ITestResult.STARTED) {

			eTest.log(LogStatus.INFO, result.getName() + " - Test is started");
		}

	}

	public static WebDriver launchBrowserNormally() throws Exception {

		boolean bPageLoaded = false;

		try {

			String browserName = prop.getProperty("browser");
			String appURL = prop.getProperty("appURL");

			if (browserName.equalsIgnoreCase("chrome")) {
				// This chromedriver is enabled to catch browser f12 console JavaScript erros as
				// well. See method collectBrowserJSerrorMessages() in GenericUtilities as well.
				// Download setting
				HashMap<String, Object> hChromePrefsMap = new HashMap<String, Object>();
				hChromePrefsMap.put("profile.default_content_settings.popups", 0);
				hChromePrefsMap.put("download.default_directory", g_strDownloadPath);

				ChromeOptions objChromeOptions = new ChromeOptions();
				objChromeOptions.setExperimentalOption("prefs", hChromePrefsMap);
				// To disable message " chrome is being cntrolled by Automated software..."
				objChromeOptions.addArguments("disable-infobars");
				objChromeOptions.addArguments("disable-infobars");
				objChromeOptions.addArguments("--no-sandbox");
				objChromeOptions.addArguments("--allow-insecure-localhost");
				// below code will run chrome in headless mode.
				// options.addArguments("--headless");
				objChromeOptions.addArguments("--disable-gpu");
				// Below code will start chrome in maximized mode
				// options.addArguments("--start-fullscreen");
				// Below code will remove message "Chrome is being controlled by... Software"
				DesiredCapabilities objDesiredCapabilities = DesiredCapabilities.chrome();
				objDesiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				objDesiredCapabilities.setCapability(ChromeOptions.CAPABILITY, objChromeOptions);
				objDesiredCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
						UnexpectedAlertBehaviour.ACCEPT);

				LoggingPreferences loggingPreferences = new LoggingPreferences();
				loggingPreferences.enable(LogType.BROWSER, Level.ALL);
				objDesiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, loggingPreferences);

				System.setProperty("webdriver.chrome.driver", chromeDriverPath);
				driver = new ChromeDriver(objDesiredCapabilities);

				// log.info(" chromedriver is enabled to collect all console JavaScript errors
				// also .");

			} else if (browserName.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", gerkoDriverPath);
				driver = new FirefoxDriver();
			}

			driver.manage().window().maximize();
			// Set good resolution for chrome to run smoothly in headless mode.
			// driver.manage().window().setSize(new Dimension(1600, 1200));
			driver.manage().deleteAllCookies();

			bPageLoaded = waitForPageToLoad();

			// Sending Ctrl+0 to ensure browser is set to 100% zoom level
			Actions action = new Actions(driver);
			action.keyDown(Keys.CONTROL).sendKeys(String.valueOf('\u0030')).perform();
			action.keyUp(Keys.CONTROL).perform();

			if (bPageLoaded) {
				logger.info("Successfully launched the browser");
			} else {
				logger.error("Browser did not launched within max wait time :: " + g_nMaxWaitTime + " Secs.");
			}

			// logger.info("***********Window maximized and browser cookies deleted******");

			// Eventfiring WebDriver setup. Comment below line if not required.
			// driver = new EventFiringWebDriver(driver).register(new WebEventListener());

			driver.manage().timeouts().pageLoadTimeout(Integer.parseInt((String) prop.get("PAGE_LOAD_TIMEOUT")),
					TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(Integer.parseInt((String) prop.get("IMPLICIT_WAIT")),
					TimeUnit.SECONDS);

			logger.info("********* Launching " + browserName + " browser*****************");

			driver.get(appURL);// launch the cover4PM broswer
			logger.info("********* Opening URL-- " + appURL + " *****************");

			return driver;
		} catch (Exception e) {

			logger.error(" Failed to initialize driver and browser setup :" + e.getMessage());
			return null;
		}

	}

	public static WebDriver launchBrowserForVpn() throws Exception {

		boolean bPageLoaded = false;

		try {

			String browserName = prop.getProperty("browser");

			if (browserName.equalsIgnoreCase("chrome")) {
				HashMap<String, Object> hChromePrefsMap = new HashMap<String, Object>();
				hChromePrefsMap.put("profile.default_content_settings.popups", 0);
				hChromePrefsMap.put("download.default_directory", g_strDownloadPath);

				ChromeOptions objChromeOptions = new ChromeOptions();
				objChromeOptions.setExperimentalOption("prefs", hChromePrefsMap);
				objChromeOptions.addArguments("disable-infobars");
				objChromeOptions.addArguments("--no-sandbox");
				objChromeOptions.addArguments("--allow-insecure-localhost");
				objChromeOptions.addArguments("--disable-gpu");
				DesiredCapabilities objDesiredCapabilities = DesiredCapabilities.chrome();
				objDesiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				objDesiredCapabilities.setCapability(ChromeOptions.CAPABILITY, objChromeOptions);
				objDesiredCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
						UnexpectedAlertBehaviour.ACCEPT);

				LoggingPreferences loggingPreferences = new LoggingPreferences();
				loggingPreferences.enable(LogType.BROWSER, Level.ALL);
				objDesiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, loggingPreferences);

				System.setProperty("webdriver.chrome.driver", chromeDriverPath);
				driver = new ChromeDriver(objDesiredCapabilities);

			} else if (browserName.equalsIgnoreCase("FF")) {
				System.setProperty("webdriver.gecko.driver", gerkoDriverPath);
				driver = new FirefoxDriver();
			}

			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();

			bPageLoaded = waitForPageToLoad();

			// Sending Ctrl+0 to ensure browser is set to 100% zoom level
			Actions action = new Actions(driver);
			action.keyDown(Keys.CONTROL).sendKeys(String.valueOf('\u0030')).perform();
			action.keyUp(Keys.CONTROL).perform();

			if (bPageLoaded) {
				logger.info("Successfully launched the browser");
			} else {
				logger.error("Browser did not launched within max wait time :: " + g_nMaxWaitTime + " Secs.");
			}

			driver.manage().timeouts().pageLoadTimeout(Integer.parseInt((String) prop.get("PAGE_LOAD_TIMEOUT")),
					TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(Integer.parseInt((String) prop.get("IMPLICIT_WAIT")),
					TimeUnit.SECONDS);

			logger.info("********* Launching " + browserName + " browser*****************");

			return driver;
		} catch (Exception e) {

			logger.error(" Failed to initialize driver and browser setup :" + e.getMessage());
			return null;
		}

	}

	public static boolean waitForPageToLoad(int... nMaxWaiTimeInSec) {
		boolean bResult = false;
		String strJSScript = "return document.readyState";
		String strReadtStausToWait = "complete";
		String strActualStatus = "";
		int nTimer = 0;
		int nMaxWaitTime = -1;

		if (nMaxWaiTimeInSec.length > 0)
			nMaxWaitTime = nMaxWaiTimeInSec[0];
		else
			nMaxWaitTime = g_nMaxWaitTime;

		logger.info("Waiting for page to load... Max Wait Time is :: " + nMaxWaitTime + " Secs.");

		try {
			if (driver == null) {
				logger.error("Driver object is NULL :: Failed to Wait");
				return bResult;
			}

			do {

				JavascriptExecutor jsExec = (JavascriptExecutor) driver;
				strActualStatus = jsExec.executeScript(strJSScript).toString();
				Thread.sleep(1000);
				nTimer++;
				logger.info("Current Page Status :: " + strActualStatus + " :: Waited For " + nTimer + " Seconds");
				if (strActualStatus.trim().equalsIgnoreCase(strReadtStausToWait)) {
					bResult = true;
					break;
				}

			} while (nTimer <= nMaxWaitTime);

			return bResult;

		} catch (Exception ex) {
			logger.error(" Page load failed :" + ex.getLocalizedMessage());
			ex.printStackTrace();
			return bResult;
		}
	}
}
