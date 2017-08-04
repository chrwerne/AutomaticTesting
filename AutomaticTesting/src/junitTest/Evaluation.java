package junitTest;

import static org.junit.Assert.fail;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;

import com.thoughtworks.selenium.webdriven.commands.KeyEvent;

import util.DateFormatter;
import util.Properties;
import util.ScreenShotOnFailure;
import util.Util;
import util.WebDriverManager;

public class Evaluation {
	
	private Logger logger = LoggerFactory.getLogger(SendMessageTest.class);
	
//	private WebDriver driver;
	
	WebDriver driver = WebDriverManager.getDriverInstance();
	
	@Rule
    public ScreenShotOnFailure failure = new ScreenShotOnFailure(driver);
	
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private Properties properties = new Properties(CreateEligibilityChecklist.class.getSimpleName());
	private Util util;
	
	@Before
	public void setUp() throws Exception {
//		driver = new FirefoxDriver();
		
		WebDriverManager.startDriver();
		WebDriver driver = WebDriverManager.getDriverInstance();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		util = new Util(properties);
		
		driver.get(util.getLoginUrl());
		driver.manage().window().maximize();
		login("schaffmjs01");
	}
	
	@Test
	public void test() throws Exception {
		
		WebDriver driver = WebDriverManager.getDriverInstance();
		
		//Eligibility Check
		driver.findElement(By.xpath("//button/span[text()='View']")).click();
		driver.findElement(By.xpath("//button/span[text()='Check Eligibility']")).click();
		driver.findElement(By.id("mainForm:checkAllBoxTop")).click();
		driver.findElement(By.id("mainForm:evaluateProjectAll")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("mainForm:checkAllBoxTop")).click();
		driver.findElement(By.xpath("//div[@class='ems-save-project-button']")).click();
		driver.findElement(By.id("mainForm:eligibilityFinishWithList")).click();
		
		//Eligibility Decision
		driver.findElement(By.xpath("//h3[text() = 'Assessment']")).click();
		driver.findElement(By.xpath("//button/span[text()='Eligibility Decision']")).click();
		driver.findElement(By.xpath("//label[text()='---']")).click();
		driver.findElement(By.xpath("//li[text()='Eligible']")).click();
		driver.findElement(By.id("mainForm:save")).click();
		
		//Quality Assessment
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button/span[text()='Quality Assessment']")).click();
		driver.findElement(By.xpath("//button/span[text()='View']")).click();
		driver.findElement(By.xpath("//button/span[text()='Assess Application']")).click();
		driver.findElement(By.id("mainForm:evaluateProjectAll")).click();
		driver.findElement(By.id("mainForm:evaluateFinish")).click();
		driver.findElement(By.id("mainForm:confirm-yes")).click();
		
		//Recommendation
		driver.findElement(By.xpath("//button/span[text()='Recommendation']")).click();
		driver.findElement(By.xpath("//label[text()='---']")).click();
		driver.findElement(By.xpath("//li[text()='Recommended']")).click();
		driver.findElement(By.id("mainForm:save")).click();
		
		//Funding Decision (MC)
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button/span[text()='Funding Decision (MC)']")).click();
		driver.findElement(By.xpath("//input[@class='ui-inputfield ui-widget ui-state-default ui-corner-all hasDatepicker']")).sendKeys("07.09.2016");
		driver.findElement(By.xpath("//label[text()='---']")).click();
		driver.findElement(By.xpath("//li[text()='Approved']")).click();
		driver.findElement(By.id("mainForm:save")).click();
		
		//Handing Over to LP
		driver.findElement(By.xpath("//button/span[2 and text()='Logout']")).click();
		login("schaffm01");
		
		Thread.sleep(4000);
		driver.findElement(By.xpath("//td[text()='Test Project']")).click();
		driver.findElement(By.xpath("//button/span[text()='Lead Partner']")).click();
		driver.findElement(By.id("mainForm:prosPartnerEntry")).sendKeys("schaffm01");
		driver.findElement(By.xpath("//button/span[text()='Declare As Lead Partner']")).click();
		driver.findElement(By.id("mainForm:confirm-yes")).click();

		driver.findElement(By.xpath("//button/span[2 and text()='Logout']")).click();
		login("schaffmjs01");
		
		driver.findElement(By.xpath("//h3[text() = 'Handover And Contracting']")).click();
		driver.findElement(By.xpath("//button/span[text()='Handing Over To LP']")).click();
		driver.findElement(By.xpath("//label[text()='---']")).click();
		driver.findElement(By.xpath("//li[text()='Approved']")).click();
		driver.findElement(By.id("mainForm:save")).click();
		
		//Contracting
		Thread.sleep(4000);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button/span[text()='Contracting']")));
		driver.findElement(By.xpath("//button/span[text()='Contracting']")).click();
		driver.findElement(By.xpath("//button/span[text()='View']")).click();
		driver.findElement(By.xpath("//h3[text() = 'Application And Contract']")).click();
		driver.findElement(By.xpath("//button/span[text()='Subsidy Contract']")).click();
		driver.findElement(By.xpath("//button/span[text()='Save']")).click();
		driver.findElement(By.xpath("//button/span[text()='Approve Contract']")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("mainForm:confirm-yes")).click();
		
		driver.findElement(By.xpath("//button/span[text()='Contracted']")).click();
		driver.findElement(By.xpath("//td[text()='Test Project']")).click();
		
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		WebDriver driver = WebDriverManager.getDriverInstance();
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
	
	public void login(String user) {
		WebDriver driver = WebDriverManager.getDriverInstance();
		driver.findElement(By.id("mainForm:userName")).clear();
		driver.findElement(By.id("mainForm:userName")).sendKeys(user);
		driver.findElement(By.id("mainForm:pass")).clear();
		driver.findElement(By.id("mainForm:pass")).sendKeys("Start123");
		driver.findElement(By.id("mainForm:submitButton")).click();
	}

}
