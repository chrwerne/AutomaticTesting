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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;

import com.thoughtworks.selenium.webdriven.commands.KeyEvent;

import util.Properties;
import util.ScreenShotOnFailure;
import util.Util;
import util.WebDriverManager;

public class CreateEligibilityChecklist {
	
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
		
		WebDriverManager.startDriver();
		WebDriver driver = WebDriverManager.getDriverInstance();
		
//		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		util = new Util(properties);
		
		driver.get(util.getLoginUrl());
		driver.manage().window().maximize();
		driver.findElement(By.id("mainForm:userName")).clear();
		driver.findElement(By.id("mainForm:userName")).sendKeys("schaffmjs01");
		driver.findElement(By.id("mainForm:pass")).clear();
		driver.findElement(By.id("mainForm:pass")).sendKeys("Start123");
		driver.findElement(By.id("mainForm:submitButton")).click();
	}

	@Test
	public void test() throws Exception {

		WebDriver driver = WebDriverManager.getDriverInstance();
		
		driver.findElement(By.xpath("//h3[text() = 'eMS Management']")).click();
		driver.findElement(By.xpath("//button/span[2 and text()='Manage Checklists']")).click();
//		Thread.sleep(2000);
		
		
//		String temp = "\"//button/span[2 and text()='Create checklist']\"";
//		waitFunction(temp);
//		waitFunction();
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button/span[2 and text()='Create checklist']")));
		
		driver.findElement(By.xpath("//button/span[2 and text()='Create checklist']")).click();

		
		
//		WebDriverWait wait2 = new WebDriverWait(driver, 5);
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button/span[2 and text()='Save changes']")));
		Thread.sleep(2000);
		
		driver.findElement(By.id("mainForm:checklist_name")).sendKeys("Automated Test Checklist");
		driver.findElement(By.xpath("//div/span[@class='ui-icon ui-icon-triangle-1-s ui-c']")).click();
		driver.findElement(By.xpath("//li[text()='Eligibility Check']")).click();
		driver.findElement(By.xpath("//button/span[2 and text()='Save changes']")).click();
		
		driver.findElement(By.xpath("//button/span[2 and text()='Show questions']")).click();

		//Textinput, no calculation
		driver.findElement(By.xpath("//button/span[2 and text()='Create question']")).click();
		driver.findElement(By.id("mainForm:questionTypeId")).click();
		driver.findElement(By.xpath("//li[@data-label='Textinput, no calculation']")).click();
		driver.findElement(By.id("mainForm:mg_Question_questiontext")).sendKeys("Textinput, no calculation");
		driver.findElement(By.id("mainForm:mg_Question_Section")).sendKeys("1");
		driver.findElement(By.id("mainForm:mg_Question_Weight")).sendKeys("1");
		driver.findElement(By.id("mainForm:verificationId")).click();
		driver.findElement(By.xpath("//li[@data-label='4-eyes']")).click();
		
		//driver.findElement(By.xpath("//div[@class='ui-chkbox-box ui-widget ui-corner-all ui-state-default ui-state-active']")).click();
		//driver.findElement(By.id("mainForm:mg_Question_Blocker_input")).click(); 
		
		fillEditor("Textinput, no calculation");	
		driver.findElement(By.id("mainForm:save")).click();
		
		//Yes - No
		driver.findElement(By.xpath("//button/span[2 and text()='Create question']")).click();
		driver.findElement(By.id("mainForm:questionTypeId")).click();
		driver.findElement(By.xpath("//li[text()='Yes -  No']")).click();
		fillStandardQuestion("Yes - No");
		driver.findElement(By.id("mainForm:save")).click();
	
		//Star rating
		driver.findElement(By.xpath("//button/span[2 and text()='Create question']")).click();
		driver.findElement(By.id("mainForm:questionTypeId")).click();
		driver.findElement(By.xpath("//li[text()='Star rating']")).click();
		fillStandardQuestion("Star rating");
		driver.findElement(By.id("mainForm:save")).click();
		
		//Slider
		driver.findElement(By.xpath("//button/span[2 and text()='Create question']")).click();
		driver.findElement(By.id("mainForm:questionTypeId")).click();
		driver.findElement(By.xpath("//li[text()='Slider']")).click();
		fillStandardQuestion("Slider");
		driver.findElement(By.id("mainForm:save")).click();
		
		//Label - no calculation
		driver.findElement(By.xpath("//button/span[2 and text()='Create question']")).click();
		driver.findElement(By.id("mainForm:questionTypeId")).click();
		driver.findElement(By.xpath("//li[text()='Label - no calculation']")).click();
		fillStandardQuestion("Label - no calculation");
		driver.findElement(By.id("mainForm:save")).click();
		
		//Yes -  No -  Not  Applicable
		driver.findElement(By.xpath("//button/span[2 and text()='Create question']")).click();
		driver.findElement(By.id("mainForm:questionTypeId")).click();
		driver.findElement(By.xpath("//li[text()='Yes -  No -  Not  Applicable']")).click();
		fillStandardQuestion("Yes -  No -  Not  Applicable");
		driver.findElement(By.id("mainForm:save")).click();
		
		//Number - 0,5 Steps
		driver.findElement(By.xpath("//button/span[2 and text()='Create question']")).click();
		driver.findElement(By.id("mainForm:questionTypeId")).click();
		driver.findElement(By.xpath("//li[text()='Number - 0,5  Steps']")).click();
		fillStandardQuestion("Number - 0,5 Steps");
		driver.findElement(By.id("mainForm:save")).click();
		
		driver.findElement(By.xpath("//button/span[2 and text()='Preview']")).click();
		
		//Add user role
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button/span[2 and text()='Manage Roles']")));
		
//		Thread.sleep(4000);
		driver.findElement(By.xpath("//button/span[2 and text()='Manage Roles']")).click();
		driver.findElement(By.xpath("//li[text()='JS']")).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='CHECKLIST_AUTOMATED_TEST_CHECKLIST_19']")));
		
//		Thread.sleep(4000);
		List<WebElement> priviledges = driver.findElements(By.xpath("//td[text()='CHECKLIST_AUTOMATED_TEST_CHECKLIST_19']"));
		priviledges.get(0).click();
		
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mainForm:savePrivilegesButton")));
		
//		Thread.sleep(4000);
		driver.findElement(By.id("mainForm:savePrivilegesButton")).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button/span[2 and text()='Logout']")));
//		Thread.sleep(4000);
		driver.findElement(By.xpath("//button/span[2 and text()='Logout']")).click();
		Thread.sleep(4000);
		
		driver.quit();
		
	}
	
	private void fillEditor(String question) {
		WebDriver driver = WebDriverManager.getDriverInstance();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']")));
		driver.findElement(By.xpath("/html/body")).click();
		WebElement editableYes = driver.switchTo().activeElement(); 
		editableYes.clear();
		editableYes.sendKeys(question); 
		driver.switchTo().defaultContent();
	}
	
	private void waitFunction(String id) {
		WebDriver driver = WebDriverManager.getDriverInstance();
		WebDriverWait wait = new WebDriverWait(driver, 5); // wait for max of 5 seconds
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(id)));
	}
	
	private void fillStandardQuestion(String questionType){
		WebDriver driver = WebDriverManager.getDriverInstance();
		driver.findElement(By.id("mainForm:mg_Question_questiontext")).sendKeys(questionType);
		driver.findElement(By.id("mainForm:mg_Question_Section")).sendKeys("1");
		driver.findElement(By.id("mainForm:mg_Question_Weight")).sendKeys("1");
		driver.findElement(By.id("mainForm:mg_Question_Min")).sendKeys("1");
		driver.findElement(By.id("mainForm:mg_Question_Max")).sendKeys("10");
		driver.findElement(By.id("mainForm:mg_Question_Steps")).sendKeys("10");
		driver.findElement(By.id("mainForm:verificationId")).click();
		driver.findElement(By.xpath("//li[@data-label='2 eyes']")).click();
		//driver.findElement(By.xpath("//div/span[@class='ui-chkbox-icon ui-c']")).click();
		fillEditor(questionType);		
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		WebDriver driver = WebDriverManager.getDriverInstance();
		WebDriverManager.stopDriver();
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
