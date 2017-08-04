package util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Util {
	
	private Properties properties;
	
	public Util(Properties properties){
		this.properties=properties;
	}
	
	public String getLoginUrl(){
		StringBuffer str = new StringBuffer();
		String url = properties.getValueOfProperty("url");
		String basicauthEnabled = properties.getValueOfProperty("basicauth.enabled");
		String basicauthUsername = properties.getValueOfProperty("basicauth.username");
		String basicauthPw = properties.getValueOfProperty("basicauth.password");
		
		str.append("http://");
		if(Boolean.TRUE.toString().equalsIgnoreCase(basicauthEnabled)) str.append(basicauthUsername);
		if(Boolean.TRUE.toString().equalsIgnoreCase(basicauthEnabled)) str.append(":");
		if(Boolean.TRUE.toString().equalsIgnoreCase(basicauthEnabled)) str.append(basicauthPw);
		if(Boolean.TRUE.toString().equalsIgnoreCase(basicauthEnabled)) str.append("@");
		str.append(url);
		
		return str.toString();
	}
	
	public void makeScreenshot(WebDriver driver)throws IOException{
		File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile,new File("C:/work/screenshots/Test_"+DateFormatter.getCurrentDateForFileName()+".png"));
	}
	
}
