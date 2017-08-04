package util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotOnFailure implements MethodRule {

    private WebDriver driver;

    public ScreenShotOnFailure(WebDriver driver){
    	this.driver = driver;
    }

    public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object o) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    statement.evaluate();
                } catch (Throwable t) {
                	System.out.println("Screenshot triggered.");
                    captureScreenShot(frameworkMethod.getName());
                    // rethrow to allow the failure to be reported by JUnit
                    throw t;
                }
            }

            public void captureScreenShot(String fileName) throws Exception {
            	
            	driver = WebDriverManager.getDriverInstance();
            	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            	FileUtils.copyFile(scrFile, new File("C:/work/screenshots/Test_"+DateFormatter.getCurrentDateForFileName()+".png"));
                System.out.println("Screenshot taken.");
        		
            }
        };
    }
}