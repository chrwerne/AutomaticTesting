package base;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import junitTest.LoginLogoutTest;
import util.Properties;
import util.Util;

public abstract class TestBase {

	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private Properties properties = new Properties(LoginLogoutTest.class.getSimpleName());
	
	private Properties globalProperties = new Properties(TestBase.class.getSimpleName());
	private Util util;
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", globalProperties.getValueOfProperty("webdriver.chrome.driver.path"));
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		util = new Util(properties);
		
		openEmsMaximised();
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
	
	
	public void openEmsMaximised(){
		getDriver().get(getUtil().getLoginUrl());
		getDriver().manage().window().maximize();
	}
	
	public WebDriver getDriver(){
		return this.driver;
	}
	
	public Util getUtil(){
		return this.util;
	}
	
	
	public Properties getProperties(){
		return this.properties;
	}
		
}
