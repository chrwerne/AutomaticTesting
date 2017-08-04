package junitTest;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.Properties;
import util.Util;

public class LoginLogoutTest {

	private Logger logger = LoggerFactory.getLogger(LoginLogoutTest.class);

	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private Properties properties = new Properties(LoginLogoutTest.class.getSimpleName());
	private Util util;

	@Before
	public void setUp() throws Exception {
//		System.setProperty("webdriver.chrome.driver", "C:/work/software/tools/selenium/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
//		baseUrl = "ems.interact-eu.net/ems";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		util = new Util(properties);
	}

	@Test
	public void test2() throws Exception {
		driver.get(util.getLoginUrl());
		driver.manage().window().maximize();
		driver.findElement(By.id("mainForm:userName")).clear();
		driver.findElement(By.id("mainForm:userName")).sendKeys("chrwerne");
		driver.findElement(By.id("mainForm:pass")).clear();
		driver.findElement(By.id("mainForm:pass")).sendKeys("Start123");
		driver.findElement(By.id("mainForm:submitButton")).click();

		Thread.sleep(1000l);
		WebElement w = driver.findElement(By.xpath("//span[@class='logo-project-title'][2]"));
		assertEquals("Dashboard", w.getText());

		driver.findElement(By.xpath("//button/span[2 and text()='Logout']")).click();

		Thread.sleep(1000l);
		assertNotNull(driver.findElement(By.id("mainForm:userName")));
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
