package XeroautomationSeptBatch_2019;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class XeroAutomation {
	
	static WebDriver driver;
	static ExtentReports reports;
	static ExtentTest logger;
	
	
	
	@BeforeTest
	public static void extentReportInit() throws IOException{
	String fileName = new SimpleDateFormat("'SampleDemo_'yyyyMMddHHmm'.html'").format(new Date(0));
	String path = "C:\\ExtentReport\\"+fileName;
	reports = new ExtentReports(path);
	
	
	}
	
	
	public static void launchBrowser(String browserName) throws InterruptedException, IOException{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\Config\\config.properties");
		prop.load(fis);
		logger = reports.startTest("Launching Browser");
		String browser = browserName;
		if(browser.equals("Mozilla")){
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\manju\\Downloads\\TekArch\\Webdrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			
		}else if(browser.equals("Chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\manju\\Downloads\\TekArch\\Webdrivers\\chromedriver (2).exe");
			//System.setProperty(ChromeDriver.SystemProperty.BROWSER_LOGFILE, "null");
			driver = new ChromeDriver();
		}else if(browser.equals("IE")){
			driver = new InternetExplorerDriver();
		}else if(browser.equals("Edge")){
			driver = new EdgeDriver();
		}
		logger.log(LogStatus.PASS,"Launched the browser");
		driver.manage().window().maximize();
		logger.log(LogStatus.PASS,"Browser maximized");		
		driver.get(prop.getProperty("xeroURL2"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);
		
		logger.log(LogStatus.PASS,"Launched xero URL");		
		
	}
	//@Test
	public static void TestID02_B() throws IOException, InterruptedException{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\Config\\config.properties");
		prop.load(fis);
		String[][] inputData = getDataInput("C:\\Users\\manju\\Documents\\TekArchAssignments\\TestData\\Xero","TestCaseID02_B.xls","Sheet1");
		launchBrowser("Mozilla");
		SoftAssert softAssert = new SoftAssert();
		driver.findElement(By.xpath(prop.getProperty("freetrialbtn_xpath"))).click();
		logger.log(LogStatus.INFO,"Clicked on free trial button");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);		
		driver.findElement(By.xpath(prop.getProperty("getstartedbtn_xpath"))).click();
		logger.log(LogStatus.INFO,"Clicked on Get started button");
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		String firstName = driver.findElement(By.xpath(prop.getProperty("firstnameerrmsg_xpath"))).getText();
		System.out.println(firstName);
		softAssert.assertTrue(firstName.contains(inputData[1][1]));
		logger.log(LogStatus.PASS,"Correct error message displayed for missing firstname");
		
		String lastName = driver.findElement(By.xpath(prop.getProperty("lastnameerrmsg_xpath"))).getText();
		System.out.println(lastName);
		softAssert.assertTrue(lastName.contains(inputData[2][1]));
		logger.log(LogStatus.PASS,"Correct error message displayed for missing lastname");
		
		String emailAddress = driver.findElement(By.xpath(prop.getProperty("emailaddresserrmsg_xpath"))).getText();
		softAssert.assertTrue(emailAddress.contains(inputData[3][1]));
		logger.log(LogStatus.PASS,"Correct error message displayed for missing email address");
		System.out.println(emailAddress);
		
		String phoneNumber = driver.findElement(By.xpath(prop.getProperty("phoneerrmsg_xpath"))).getText();
		softAssert.assertTrue(phoneNumber.contains(inputData[4][1]));
		logger.log(LogStatus.PASS,"Correct error message displayed for missing phone number");
		System.out.println(phoneNumber);
		System.out.println(inputData[6][1]);
		
		driver.findElement(By.xpath(prop.getProperty("emailaddresstxtfield_xpath"))).sendKeys(inputData[6][1]);
		driver.findElement(By.xpath(prop.getProperty("getstartedbtn_xpath"))).click();
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		
		String emailAddress1 = driver.findElement(By.xpath(prop.getProperty("emailaddresserrmsg_xpath"))).getText();
		softAssert.assertTrue(emailAddress1.contains(inputData[5][1]));
		logger.log(LogStatus.PASS,"Correct error message displayed for missing email address");
		System.out.println(emailAddress1);
		
		softAssert.assertAll();
		
		
	}
	
	//@Test
	public static void TestID02_C() throws IOException, InterruptedException{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\Config\\config.properties");
		prop.load(fis);
		String[][] inputData = getDataInput("C:\\Users\\manju\\Documents\\TekArchAssignments\\TestData\\Xero","TestCaseID02_C.xls","Sheet1");
		launchBrowser("Mozilla");
		SoftAssert softAssert = new SoftAssert();
		driver.findElement(By.xpath(prop.getProperty("freetrialbtn_xpath"))).click();
		logger.log(LogStatus.INFO,"Clicked on free trial button");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	
		//Thread.sleep(30000);
		
		driver.findElement(By.xpath(prop.getProperty("termslink_xpath"))).click();
		driver.findElement(By.xpath(prop.getProperty("privacylink_xpath"))).click();
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		 ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		    driver.switchTo().window(tabs2.get(2));
		    Thread.sleep(3000);
		    String termstext =  driver.findElement(By.xpath(prop.getProperty("termsandprivacyverificationtext_xpath"))).getText();
		    System.out.println("The terms string is : "+termstext);
		    softAssert.assertTrue(termstext.contains(inputData[1][1]));
			logger.log(LogStatus.PASS,"Terms of use page is displayed");
			//driver.close();
		    
		   
		    driver.switchTo().window(tabs2.get(1));	
		
		//driver.findElement(By.xpath(prop.getProperty("privacylink_xpath"))).click();
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//driver.switchTo().window(tabs2.get(2));
		String privacytext = driver.findElement(By.xpath(prop.getProperty("termsandprivacyverificationtext_xpath"))).getText();
		 System.out.println("The privacy string is : "+privacytext);
		softAssert.assertTrue(privacytext.contains(inputData[2][1]));
		logger.log(LogStatus.PASS,"Privacy notice page is displayed");
		softAssert.assertAll();		
	}
	
	//@Test
	public static void TestID02_D() throws IOException, InterruptedException{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\Config\\config.properties");
		prop.load(fis);
		String[][] inputData = getDataInput("C:\\Users\\manju\\Documents\\TekArchAssignments\\TestData\\Xero","TestCaseID02_D.xls","Sheet1");
		launchBrowser("Mozilla");
		SoftAssert softAssert = new SoftAssert();
		driver.findElement(By.xpath(prop.getProperty("freetrialbtn_xpath"))).click();
		logger.log(LogStatus.INFO,"Clicked on free trial button");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	
		
		driver.findElement(By.xpath(prop.getProperty("offerdetailslink_xpath"))).click();		
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		String offerDetailsText = driver.findElement(By.xpath(prop.getProperty("offerdetailslinktext_xpath"))).getText();
		
		 System.out.println("The offer detail string is : "+offerDetailsText);
			softAssert.assertTrue(offerDetailsText.contains(inputData[1][1]));
			logger.log(LogStatus.PASS,"Offer detailsS page is displayed");
			softAssert.assertAll();	
	}
	
	
//	@Test
	public static void TestID02_E() throws IOException, InterruptedException{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\Config\\config.properties");
		prop.load(fis);
		String[][] inputData = getDataInput("C:\\Users\\manju\\Documents\\TekArchAssignments\\TestData\\Xero","TestCaseID02_E.xls","Sheet1");
		launchBrowser("Mozilla");
		SoftAssert softAssert = new SoftAssert();
		driver.findElement(By.xpath(prop.getProperty("freetrialbtn_xpath"))).click();
		logger.log(LogStatus.INFO,"Clicked on free trial button");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	
	
		driver.findElement(By.xpath(prop.getProperty("bookkeeperlink_xpath"))).click();
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		String bookAndAccountText = driver.findElement(By.xpath(prop.getProperty("bookkeeperlinktext_xpath"))).getText();
		System.out.println("The book and account text displayed is: "+bookAndAccountText);
		softAssert.assertTrue(bookAndAccountText.contains(inputData[1][1]));
		logger.log(LogStatus.PASS,"Book and account details page is displayed");
		softAssert.assertAll();	
		
	}
	
	//@Test
	public static void TestID03_A() throws IOException, InterruptedException{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\Config\\config.properties");
		prop.load(fis);
		String[][] inputData = getDataInput("C:\\Users\\manju\\Documents\\TekArchAssignments\\TestData\\Xero","TestCaseID03_A.xls","Sheet1");
		launchBrowser("Mozilla");
		SoftAssert softAssert = new SoftAssert();
		driver.get(prop.getProperty("xeroURL2"));
		
		driver.findElement(By.xpath(prop.getProperty("loginlink_xpath"))).click();
		driver.findElement(By.xpath(prop.getProperty("username_xpath"))).sendKeys(inputData[1][0]);
		driver.findElement(By.xpath(prop.getProperty("password_xpath"))).sendKeys(inputData[1][1]);
		driver.findElement(By.xpath(prop.getProperty("loginbtn_xpath"))).click();
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("dashboardlink_xpath"))).click();
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		String dashText = driver.findElement(By.xpath(prop.getProperty("dashboardverificationtext_xpath"))).getText();
		System.out.println("The dashbard text displayed is: "+dashText);
		System.out.println("The dashbard text in excel sheet is: "+inputData[1][2]);
		softAssert.assertTrue(dashText.contains(inputData[1][2]));
		logger.log(LogStatus.PASS,"Dashboard page is displayed");
		
		driver.findElement(By.xpath(prop.getProperty("accountslink_xpath"))).click();
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("accountstransaction_xpath"))).click();
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		String transactionText = driver.findElement(By.xpath(prop.getProperty("accountstextverification_xpath"))).getText();
		System.out.println("The accounts text displayed is: "+transactionText);
		System.out.println("The accounts text in excel sheet is: "+inputData[1][3]);
		
		driver.findElement(By.xpath(prop.getProperty("accountslink_xpath"))).click();
		driver.findElement(By.xpath(prop.getProperty("reportslink_xpath"))).click();
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		String reportText = driver.findElement(By.xpath(prop.getProperty("reportsverificationtext_xpath"))).getText();
		System.out.println("The reports text displayed is: "+reportText);
		System.out.println("The reports text in excel sheet is: "+inputData[1][4]);		
		softAssert.assertTrue(reportText.contains(inputData[1][4]));
		logger.log(LogStatus.PASS, "Reports page is displayed.");
		
		
		driver.findElement(By.xpath(prop.getProperty("contactslink_xpath"))).click();
		driver.findElement(By.xpath(prop.getProperty("allcontactslink_xpath"))).click();
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		String contactText = driver.findElement(By.xpath(prop.getProperty("contactstextverification_xpath"))).getText();
		System.out.println("The contact text displayed is: "+contactText);
		System.out.println("The contact text in excel sheet is: "+inputData[1][5]);	
		softAssert.assertTrue(contactText.contains(inputData[1][5]));
		logger.log(LogStatus.PASS, "Contacts page is displayed.");
		
		driver.findElement(By.xpath(prop.getProperty("testlink_xpath"))).click();
		driver.findElement(By.xpath(prop.getProperty("settingslink_xpath"))).click();
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);		
		String settingsText = driver.findElement(By.xpath(prop.getProperty("settingsverificationlink_xpath"))).getText();
		System.out.println("The settings text displayed is: "+settingsText);
		System.out.println("The settings text in excel sheet is: "+inputData[1][6]);	
		softAssert.assertTrue(settingsText.contains(inputData[1][6]));
		logger.log(LogStatus.PASS, "Settings page is displayed.");
			
		
		driver.findElement(By.xpath(prop.getProperty("newlink_xpath"))).click();
		driver.findElement(By.xpath(prop.getProperty("invoicelink_xpath"))).click();
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);		
		String newText = driver.findElement(By.xpath(prop.getProperty("invoiceverificationtext_xpath"))).getText();
		System.out.println("The invoice text displayed is: "+newText);
		System.out.println("The invoice text in excel sheet is: "+inputData[1][7]);	
		softAssert.assertTrue(newText.contains(inputData[1][7]));
		logger.log(LogStatus.PASS, "New page is displayed.");
		
		
		driver.findElement(By.xpath(prop.getProperty("testlink_xpath"))).click();
		driver.findElement(By.xpath(prop.getProperty("filelink_xpath"))).click();
		
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);		
		String fileText = driver.findElement(By.xpath(prop.getProperty("filetextverification_xpath"))).getText();
		System.out.println("The file text displayed is: "+fileText);
		System.out.println("The file text in excel sheet is: "+inputData[1][8]);	
		softAssert.assertTrue(fileText.contains(inputData[1][8]));
		logger.log(LogStatus.PASS, "File page is displayed.");
		
		//driver.switchTo().frame(prop.getProperty("notificationiframe1_id"));
		driver.findElement(By.xpath(prop.getProperty("notificationicon_xpath"))).click();
		driver.switchTo().frame(prop.getProperty("notificationiconframe2_id"));
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Thread.sleep(3000);
		
		String notificationText = driver.findElement(By.xpath(prop.getProperty("notificationtext_xpath"))).getText();
		driver.switchTo().parentFrame();
		System.out.println("The Notification text displayed is: "+notificationText);
		System.out.println("The Notification text in excel sheet is: "+inputData[1][9]);	
		softAssert.assertTrue(notificationText.contains(inputData[1][9]));
		logger.log(LogStatus.PASS, "Notification page is displayed.");
		
		driver.findElement(By.xpath(prop.getProperty("searchicon_xpath"))).click();;
		driver.switchTo().frame(prop.getProperty("notificationiframe1_id"));
		
		String searchText = driver.findElement(By.xpath(prop.getProperty("searchfield_xpath"))).getAttribute("Value");
		driver.switchTo().parentFrame();
		System.out.println("The Search text displayed is: "+searchText);
		System.out.println("The Search text in excel sheet is: "+inputData[1][10]);	
		softAssert.assertTrue(searchText.contains(inputData[1][10]));
		logger.log(LogStatus.PASS, "Search Text is displayed.");
		
		
		driver.findElement(By.xpath(prop.getProperty("helpicon_xpath"))).click();
		String helpText = driver.findElement(By.xpath(prop.getProperty("helptextfield_xpath"))).getAttribute("placeholder");
		System.out.println("The Help text displayed is: "+helpText);
		System.out.println("The Help text in excel sheet is: "+inputData[1][11]);	
		softAssert.assertTrue(helpText.contains(inputData[1][11]));
		logger.log(LogStatus.PASS, "Help Text is displayed.");		
		softAssert.assertAll();			
	}
	
	//@Test
	public static void TestID04_A() throws IOException, InterruptedException{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\Config\\config.properties");
		prop.load(fis);
		String[][] inputData = getDataInput("C:\\Users\\manju\\Documents\\TekArchAssignments\\TestData\\Xero","TestCaseID03_A.xls","Sheet1");
		launchBrowser("Mozilla");
		SoftAssert softAssert = new SoftAssert();
		driver.get(prop.getProperty("xeroURL2"));
		
		driver.findElement(By.xpath(prop.getProperty("loginlink_xpath"))).click();
		driver.findElement(By.xpath(prop.getProperty("username_xpath"))).sendKeys(inputData[1][0]);
		driver.findElement(By.xpath(prop.getProperty("password_xpath"))).sendKeys(inputData[1][1]);
		driver.findElement(By.xpath(prop.getProperty("loginbtn_xpath"))).click();
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		String dashText = driver.findElement(By.xpath(prop.getProperty("dashboardverificationtext_xpath"))).getText();
		System.out.println("The dashbard text displayed is: "+dashText);
		System.out.println("The dashbard text in excel sheet is: "+inputData[1][2]);
		softAssert.assertTrue(dashText.contains(inputData[1][2]));
		logger.log(LogStatus.PASS,"Dashboard page is displayed");
		
		driver.findElement(By.xpath(prop.getProperty("usernamelink_xpath"))).click();
		driver.findElement(By.xpath(prop.getProperty("logoutlink_xpath"))).click();
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		logger.log(LogStatus.PASS,"Logged out successfully");
		softAssert.assertAll();			
	}
	
	//Testcase for uploading photo.
	//@Test
	public static void TestID06_A() throws IOException, InterruptedException{
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\Config\\config.properties");
			prop.load(fis);
			String[][] inputData = getDataInput("C:\\Users\\manju\\Documents\\TekArchAssignments\\TestData\\Xero","TestCaseID06_A.xls","Sheet1");
			launchBrowser("Mozilla");
			SoftAssert softAssert = new SoftAssert();
			driver.get(prop.getProperty("xeroURL2"));
			
			driver.findElement(By.xpath(prop.getProperty("loginlink_xpath"))).click();
			driver.findElement(By.xpath(prop.getProperty("username_xpath"))).sendKeys(inputData[1][0]);
			driver.findElement(By.xpath(prop.getProperty("password_xpath"))).sendKeys(inputData[1][1]);
			driver.findElement(By.xpath(prop.getProperty("loginbtn_xpath"))).click();
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			String dashText = driver.findElement(By.xpath(prop.getProperty("dashboardverificationtext_xpath"))).getText();
			System.out.println("The dashbard text displayed is: "+dashText);
			System.out.println("The dashbard text in excel sheet is: "+inputData[1][2]);
			softAssert.assertTrue(dashText.contains(inputData[1][2]));
			logger.log(LogStatus.PASS,"Dashboard page is displayed");
			
			driver.findElement(By.xpath(prop.getProperty("usernamelink_xpath"))).click();
			driver.findElement(By.xpath(prop.getProperty("editprofilelink_xpath"))).click();
			Thread.sleep(6000);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			logger.log(LogStatus.PASS,"Profile page displayed");
			driver.findElement(By.xpath(prop.getProperty("uploadimagebtn_xpath"))).click();
			//((JavascriptExecutor) driver).executeScript("document.getElementById('ID').style.display='block';");
			/*WebElement e = driver.findElement(By.xpath(prop.getProperty("browsebtn1_xpath")));
			e.sendKeys(inputData[1][2]);*/
			driver.findElement(By.cssSelector("#filefield-1202-button-btnWrap")).sendKeys(inputData[1][2]);
			Thread.sleep(9000);
			Thread.sleep(9000);
			driver.findElement(By.xpath(prop.getProperty("uploadbtn_xpath"))).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath(prop.getProperty("savebtn_xpath"))).click();
			driver.findElement(By.xpath(prop.getProperty("goback_xpath"))).click();
			driver.findElement(By.xpath(prop.getProperty("usernamelink_xpath"))).click();
			driver.findElement(By.xpath(prop.getProperty("logoutlink_xpath"))).click();
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			logger.log(LogStatus.PASS,"Logged out successfully");
			softAssert.assertAll();			
			
		}
	
	@Test
	public static void TestID08_A() throws IOException, InterruptedException{
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\Config\\config.properties");
			prop.load(fis);
			String[][] inputData = getDataInput("C:\\Users\\manju\\Documents\\TekArchAssignments\\TestData\\Xero","TestCaseID08_A.xls","Sheet1");
			launchBrowser("Mozilla");
			SoftAssert softAssert = new SoftAssert();
			driver.get(prop.getProperty("xeroURL2"));
			
			driver.findElement(By.xpath(prop.getProperty("loginlink_xpath"))).click();
			driver.findElement(By.xpath(prop.getProperty("username_xpath"))).sendKeys(inputData[1][0]);
			driver.findElement(By.xpath(prop.getProperty("password_xpath"))).sendKeys(inputData[1][1]);
			driver.findElement(By.xpath(prop.getProperty("loginbtn_xpath"))).click();
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			String dashText = driver.findElement(By.xpath(prop.getProperty("dashboardverificationtext_xpath"))).getText();
			System.out.println("The dashbard text displayed is: "+dashText);
			System.out.println("The dashbard text in excel sheet is: "+inputData[1][2]);
			softAssert.assertTrue(dashText.contains(inputData[1][2]));
			logger.log(LogStatus.PASS,"Dashboard page is displayed");
			
			driver.findElement(By.xpath(prop.getProperty("testlink_xpath"))).click();
			driver.findElement(By.xpath(prop.getProperty("myxerolink_xpath"))).click();
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			Thread.sleep(3000);
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			
			driver.findElement(By.xpath(prop.getProperty("addorganization_xpath"))).click();
			Thread.sleep(3000);
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath(prop.getProperty("organizationname_xpath"))).sendKeys(inputData[1][3]);
			WebElement dropDown = driver.findElement(By.xpath(prop.getProperty("timezonedropdown_xpath")));
			dropDown.click();
			driver.findElement(By.xpath(prop.getProperty("pacifictime_xpath"))).click();
			Thread.sleep(9000);
			WebElement element = driver.findElement(By.xpath(prop.getProperty("organizationdo_xpath")));
			String input="Accounting Services";
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].value='Accounting Services';", element);
			//((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
			//((JavascriptExecutor)driver).executeScript("arguments[0].value='"+inputData[1][5]+"';", element);
			//element.sendKeys(inputData[1][4]);
			//new WebDriverWait(driver, 90).until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("organizationdo_xpath")))).sendKeys(inputData[1][4]);;
			//driver.findElement(By.xpath(prop.getProperty("organizationdo_xpath"))).sendKeys(inputData[1][4]);
			//driver.findElement(By.xpath(prop.getProperty("organizationdo_xpath")))
			//driver.findElement(By.xpath("//*[@id='7ab5c99a-feec-4c3f-9139-885603484c88-control']")).
			
			//JavascriptExecutor js = (JavascriptExecutor) driver;
			//js.executeScript("document.getElementById('7ab5c99a-feec-4c3f-9139-885603484c88-control').setAttribute('value', 'Accounting Services')");
			//driver.findElement(By.id("7ab5c99a-feec-4c3f-9139-885603484c88-control")).setAttribute("value","Accounting");
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty("starttrial_xpath"))).click();
			Thread.sleep(6000);
			softAssert.assertAll();	
			
	}
	
	
	
	public static String[][] getDataInput(String path,String fileName,String sheetName) throws IOException{
		//String dt_Path =path;
		String dt_Path = path+"\\"+fileName;
		//String dt_Path ="C:\\Users\\manju\\Documents\\TekArchAssignments\\LoginErrorTC1.xls"; 
		File xlFile = new File(dt_Path);
		
		FileInputStream xlDoc = new FileInputStream(xlFile);
		HSSFWorkbook wb = new HSSFWorkbook(xlDoc);
		
		
		HSSFSheet sheet = wb.getSheet(sheetName);
		
		int rcount= sheet.getLastRowNum();
		int ccount = sheet.getRow(0).getLastCellNum();
		String data[][] = new String[rcount+1][ccount];
		for(int i=0; i<rcount;i++){
			for(int j=0; j<ccount;j++){
				System.out.print(sheet.getRow(i).getCell(j).getStringCellValue()+" | ");
				data[i][j]=sheet.getRow(i).getCell(j).getStringCellValue();
			}
			System.out.println();
			
		}
		
		return data;
		
	}
	
	
	
	
	
	
	@AfterMethod
	public static void closeBrowser(){
		driver.quit();
	}
	
	@AfterTest
	public static void tearDownDriver() throws InterruptedException{
		Thread.sleep(3000);
		//logger.log(LogStatus.INFO,"Close the Browser");
		//driver.quit();
		//logger.log(LogStatus.PASS,"Closed the Browser");
	reports.endTest(logger);
	reports.flush();
		
	}
	public static void clickButton(WebElement obj, String ObjName){
		if(obj.isDisplayed()){
			obj.click();
			System.out.priintln("Pass"+ObjName+"is clicked");
			else{
				System.out.println("Fail"+ObjName+"does not exist");
			}
			
		}
	}
	
	


}
