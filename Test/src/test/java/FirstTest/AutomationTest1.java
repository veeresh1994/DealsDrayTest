package FirstTest;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import secondTest.ScreenRecorderUtil;


public class AutomationTest1 {

	
    private static final Dimension[] RESOLUTIONS = {
        new Dimension(1920, 1080),
        new Dimension(1366, 768),
        new Dimension(1536, 864)
    };

    private static final String URL = "https://www.getcalley.com/page-sitemap.xml"; 

    public static void main(String[] args) throws Throwable {
    	ScreenRecorderUtil.startRecord("main");
        Thread.sleep(5);
        for (Dimension resolution : RESOLUTIONS) {
            takeScreenshotsInChrome(resolution);
            Thread.sleep(5);
            takeScreenshotsInFirefox(resolution);
        }
        ScreenRecorderUtil.stopRecord();
    }

    private static void takeScreenshotsInChrome(Dimension resolution) throws Throwable {
    	
         Thread.sleep(2);
        WebDriver driver = new ChromeDriver();
        try {
        	Thread.sleep(5);
            driver.manage().window().setSize(resolution);
            Thread.sleep(1);
            driver.get(URL);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
            takeScreenshot(driver, "Chrome", resolution);
        } finally {
        	Thread.sleep(5);
            driver.quit();
            Thread.sleep(5);
        }
    }

    private static void takeScreenshotsInFirefox(Dimension resolution) throws AWTException, Throwable {
    	 Thread.sleep(2);
        WebDriver driver = new FirefoxDriver();
        	 Thread.sleep(5);
            driver.manage().window().setSize(resolution);
            Thread.sleep(1);
            driver.get(URL);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
            takeScreenshot(driver, "Firefox", resolution);
        	 Thread.sleep(5);
            driver.quit();
            Thread.sleep(5);
    }

    private static void takeScreenshot(WebDriver driver, String browser, Dimension resolution) throws InterruptedException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Thread.sleep(5);
        String resolutionStr = resolution.getWidth() + "x" + resolution.getHeight();
        String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        String filepath=browser + ">" + resolutionStr + ">" + timestamp + ".png";
        File destination = new File(filepath);
        Thread.sleep(5);

        try {
            FileHandler.copy(screenshot, destination);
            System.out.println("Screenshot saved: " + destination.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }  
  
    	
        
}



