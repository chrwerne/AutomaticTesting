package junitTest;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.apache.commons.io.FileUtils;

import util.DateFormatter;
import util.Properties;
import util.ScreenShotOnFailure;
import util.Util;
import util.WebDriverManager;

public class CreateMaxCallTest {

	private Logger logger = LoggerFactory.getLogger(CreateMaxCallTest.class);

//	private WebDriver driver;

	WebDriver driver = WebDriverManager.getDriverInstance();
	
	@Rule
    public ScreenShotOnFailure failure = new ScreenShotOnFailure(driver);
	
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private Properties properties = new Properties(CreateMaxCallTest.class.getSimpleName());
	private Util util;

	@Before
	public void setUp() throws Exception {
		
		WebDriverManager.startDriver();
		WebDriver driver = WebDriverManager.getDriverInstance();
		
		//driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		util = new Util(properties);
		
		driver.get(util.getLoginUrl());
		driver.manage().window().maximize();
		driver.findElement(By.id("mainForm:userName")).clear();
		driver.findElement(By.id("mainForm:userName")).sendKeys("JTS1");
		driver.findElement(By.id("mainForm:pass")).clear();
		driver.findElement(By.id("mainForm:pass")).sendKeys("Start123");
		driver.findElement(By.id("mainForm:submitButton")).click();
	}

	@Test
	public void test() throws Exception {
		
		WebDriver driver = WebDriverManager.getDriverInstance();
		
		driver.findElement(By.xpath("//h3[text() = 'eMS Management']")).click();
		driver.findElement(By.xpath("//button/span[2 and text()='New Call']")).click();
		driver.findElement(By.id("mainForm:name")).clear();
		driver.findElement(By.id("mainForm:name")).sendKeys("Max Call 5");
//		driver.findElement(By.id("mainForm:name")).sendKeys("Max Call, automatically generated at <"+ DateFormatter.getCurrentDate()+">");
		driver.findElement(By.id("mainForm:call_startDate_input")).sendKeys("01.01.2016 00:00");
		driver.findElement(By.id("mainForm:call_endDate_input")).sendKeys("31.12.2020 00:00");
		
		//Application form and procedure settings
		driver.findElement(By.id("mainForm:call_generalblockexemptionforsme")).click();
		driver.findElement(By.id("mainForm:call_usepurchaseofland")).click();
		
		//Partner settings
		driver.findElement(By.id("mainForm:call_contactdetails")).click();
		driver.findElement(By.id("mainForm:call_partnershipdescription")).click();
		driver.findElement(By.id("mainForm:call_partnerAdvancedPayment")).click();
		driver.findElement(By.id("mainForm:call_associatedpartner")).click();
		driver.findElement(By.id("mainForm:call_subpartners")).click();
		driver.findElement(By.id("mainForm:call_partnerProfit")).click();
				
		//Project Description settings
		driver.findElement(By.id("mainForm:call_cooperationcriteria")).click();
		driver.findElement(By.id("mainForm:call_macroregionalstrategy")).click();
		driver.findElement(By.id("mainForm:call_durability")).click();
		driver.findElement(By.id("mainForm:call_risksection")).click();
		driver.findElement(By.id("mainForm:call_enhancedcooperationcreteria")).click();
		
		//Workpackage settings
		driver.findElement(By.id("mainForm:call_wppreaparation")).click();
		driver.findElement(By.id("mainForm:call_wpimplementation")).click();
		driver.findElement(By.id("mainForm:call_wpmgmtbudget")).click();
		driver.findElement(By.id("mainForm:call_wpmgmtdescriptiondeliverable")).click();
		driver.findElement(By.id("mainForm:call_wpcommactivitybudget")).click();
		driver.findElement(By.id("mainForm:call_wpcommdescriptiondeliverable")).click();
		driver.findElement(By.id("mainForm:call_wpinvestbudget")).click();
		driver.findElement(By.id("mainForm:call_wpinvestactivitydescription")).click();
		driver.findElement(By.id("mainForm:call_wpinvestdeliverymonth")).click();
		driver.findElement(By.id("mainForm:call_wpimplactivitydescription")).click();
		driver.findElement(By.id("mainForm:call_wpimpldeliverabledescription")).click();
		driver.findElement(By.id("mainForm:call_wpinvestment")).click();
		driver.findElement(By.id("mainForm:call_wpcommunication")).click();
		driver.findElement(By.id("mainForm:call_wpmgmtdescriptionactivity")).click();
		driver.findElement(By.id("mainForm:call_wpmgmtdelmonth")).click();
		driver.findElement(By.id("mainForm:call_wpcommdescriptionactivity")).click();
		driver.findElement(By.id("mainForm:call_wpcommdeliverymonth")).click();
		driver.findElement(By.id("mainForm:call_wpinvestreuse")).click();
		driver.findElement(By.id("mainForm:call_wpinvestdeliverabledescription")).click();
		driver.findElement(By.id("mainForm:call_wpimplreuse")).click();
		driver.findElement(By.id("mainForm:call_wpimplactivitybudget")).click();
		driver.findElement(By.id("mainForm:call_wpimpldeliverymonth")).click();
		
		//Budget Settings
		driver.findElement(By.id("mainForm:call_cofinancingsource")).click();
		driver.findElement(By.id("mainForm:call_summary_fundsums")).click();
		driver.findElement(By.id("mainForm:call_individualperiods")).click();
		driver.findElement(By.id("mainForm:call_enableassimilatedpartner")).click();
		driver.findElement(By.id("mainForm:call_ssUnitCost")).click();
		driver.findElement(By.id("mainForm:call_budgetflatrates")).click();
		driver.findElement(By.id("mainForm:call_inkindenabled")).click();
		driver.findElement(By.id("mainForm:call_summary_fundrows")).click();
		driver.findElement(By.id("mainForm:call_allowprivate")).click();
		driver.findElement(By.id("mainForm:call_lumpSum")).click();
		
		driver.findElement(By.xpath("//div[text()='Max Flatrate Staff']/span/input")).clear();
		driver.findElement(By.xpath("//div[text()='Max Flatrate Staff']/span/input")).sendKeys("20");
		driver.findElement(By.xpath("//div[text()='Max Flatrate Office']/span/input")).clear();
		driver.findElement(By.xpath("//div[text()='Max Flatrate Office']/span/input")).sendKeys("15");
		
		//Macroregional strategies
		List<WebElement> macroregionalStrategies = driver.findElements(By.xpath("//tbody[@id='mainForm:macroTable_data']//div[@class='ui-chkbox ui-widget']/div[@class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']"));
		for(WebElement w: macroregionalStrategies){
			w.click();
		}
		
		//Priority axes
		List<WebElement> priorityAxes = driver.findElements(By.xpath("//tbody[@id='mainForm:priorityTable_data']//div[@class='ui-chkbox ui-widget']/div[@class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']"));
		priorityAxes.get(0).click();
		
		//Specific objectives
		List<WebElement> specificObjectives = driver.findElements(By.xpath("//tbody[@id='mainForm:specificTable_data']//div[@class='ui-chkbox ui-widget']/div[@class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']"));
		specificObjectives.get(0).click();
				
		//Checklist
		driver.findElement(By.xpath("//h3[text()='Eligibility']/following-sibling::div//div[@class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']")).click();
		
//		util.makeScreenshot(driver);
		Thread.sleep(5000);
		
		//Save and publish
		driver.findElement(By.id("mainForm:save")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("mainForm:publishCall")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("mainForm:confirm-yes")).click();
		
		Thread.sleep(4000);
//		driver.findElement(By.xpath("//button/span[2 and text()='Logout']")).click();
		Thread.sleep(4000);
		
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		
		WebDriver driver = WebDriverManager.getDriverInstance();
		driver.findElement(By.xpath("//button/span[2 and text()='Logout']")).click();
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
	

	private boolean isElementPresent(By by) {
		try {
			
			WebDriver driver = WebDriverManager.getDriverInstance();
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			WebDriver driver = WebDriverManager.getDriverInstance();
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			WebDriver driver = WebDriverManager.getDriverInstance();
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}

}
