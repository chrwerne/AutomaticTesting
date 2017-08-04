package junitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import base.TestBase;

public class LoginLogoutTest extends TestBase {

	private Logger logger = LoggerFactory.getLogger(LoginLogoutTest.class);

	@Test
	public void test2() throws Exception {
		logger.info("Starting LoginLogoutTest...");
		
		getDriver().findElement(By.id("mainForm:userName")).clear();
		getDriver().findElement(By.id("mainForm:userName")).sendKeys(getProperties().getValueOfProperty("username"));
		getDriver().findElement(By.id("mainForm:pass")).clear();
		getDriver().findElement(By.id("mainForm:pass")).sendKeys(getProperties().getValueOfProperty("password"));
		getDriver().findElement(By.id("mainForm:submitButton")).click();

		Thread.sleep(1000l);
		WebElement w = getDriver().findElement(By.xpath("//span[@class='logo-project-title'][2]"));
		assertEquals("Dashboard", w.getText());

		getDriver().findElement(By.xpath("//button/span[2 and text()='Logout']")).click();

		Thread.sleep(1000l);
		assertNotNull(getDriver().findElement(By.id("mainForm:userName")));
		
		logger.info("Finishing LoginLogoutTest.");
	}
}
