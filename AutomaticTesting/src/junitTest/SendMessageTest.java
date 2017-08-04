package junitTest;

import static org.junit.Assert.fail;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.selenium.webdriven.commands.KeyEvent;

import util.Properties;
import util.Util;

public class SendMessageTest {
	
	private Logger logger = LoggerFactory.getLogger(SendMessageTest.class);
	
	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private Properties properties = new Properties(SendMessageTest.class.getSimpleName());
	private Util util;
	
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
//		baseUrl = "ems.interact-eu.net/ems";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		util = new Util(properties);
	}
	
	@Test
	public void test2() throws Exception {
		driver.get(util.getLoginUrl());
		driver.manage().window().maximize();
		driver.findElement(By.id("mainForm:userName")).clear();
		driver.findElement(By.id("mainForm:userName")).sendKeys("schaffm01");
		driver.findElement(By.id("mainForm:pass")).clear();
		driver.findElement(By.id("mainForm:pass")).sendKeys("Start123");
		driver.findElement(By.id("mainForm:submitButton")).click();
		driver.findElement(By.xpath("//button/span[2 and text()='Mailbox']")).click();
		driver.findElement(By.xpath("//button/span[text()='New Message']")).click();
		
		driver.findElement(By.id("mainForm:email_receiver_input")).sendKeys("schaffm01");
		driver.findElement(By.xpath("//div[@id='mainForm:email_receiver_panel']/ul/li[@data-item-value='schaffm01']")).click();
		
		driver.findElement(By.id("mainForm:email_subject")).sendKeys("This is an automated test message");
		
		driver.findElement(By.id("mainForm:projectList_label")).click();
		driver.findElement(By.xpath("//div[@id='mainForm:projectList_panel']/div/ul/li[@data-label='514 Reduce time for everyone']")).click();
		
		driver.findElement(By.id("mainForm:phasesList_label")).click();
		driver.findElement(By.xpath("//div[@id='mainForm:phasesList_panel']/div/ul/li[@data-label='1 Application']")).click();
	
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']")));
		driver.findElement(By.xpath("/html/body")).click();

		WebElement editable = driver.switchTo().activeElement(); 

		editable.clear();
		editable.sendKeys("Your text here"); 

		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//button/span[text()='Send']")).click();
		
		/*
		
		driver.findElement(By.xpath("//a[text()='Attachment']")).click();
		driver.findElement(By.xpath("//span[@class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-icon-left ui-fileupload-choose']")).click();

	    //put path to your image in a clipboard
	    StringSelection ss = new StringSelection("C:\\Users\\ipvsch\\Pictures\\52_Penguins.jpg");
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

	    //imitate mouse events like ENTER, CTRL+C, CTRL+V
	    Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
	    */
		
		util.makeScreenshot(driver);
		
		Thread.sleep(5000);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
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
