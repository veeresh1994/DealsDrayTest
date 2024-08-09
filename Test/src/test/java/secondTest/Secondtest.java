package secondTest;

import java.io.File;
import java.time.Duration;
import java.util.logging.FileHandler;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Secondtest {

	public static final String URL="https://demo.dealsdray.com/";
	static WebDriver driver;
	
	public static void chromesetup() throws Throwable
	{
		driver = new ChromeDriver();
	}
	public static void firefoxsetup()
	{
		driver = new FirefoxDriver();
		
	}	
	
	public static void test() throws Throwable
	{
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		driver.findElement(By.cssSelector("#mui-1")).sendKeys("prexo.mis@dealsdray.com");
		driver.findElement(By.cssSelector("#mui-2")).sendKeys("prexo.mis@dealsdray.com");
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		driver.findElement(By.cssSelector(".MuiButtonBase-root.has-submenu.compactNavItem.css-46up3a")).click();	
		driver.findElement(By.xpath("(//a[@class='false']/button[@name='child'])[1]")).click();
		driver.findElement(By.xpath("//button[text()='Add Bulk Orders']")).click();
		driver.findElement(By.xpath("//input[@class='MuiOutlinedInput-input MuiInputBase-input MuiInputBase-inputSizeSmall css-1imb3v5']")).sendKeys("C:\\Users\\Dell\\Downloads\\demo-data.xlsx");
		driver.findElement(By.xpath("//button[text()='Import']")).click();
		driver.findElement(By.xpath("//button[text()='Validate Data']")).click();
		Thread.sleep(10);
		/*driver.switchTo().alert().accept();
		JavascriptExecutor je=(JavascriptExecutor)driver;
		je.executeScript("window.scrollBy(0,500)");*/
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		String path=".//Screenshot//"+"Otput.png";
		File dst=new File(path);
		FileUtils.copyFile(src, dst);
		
	}
	public static void main(String[]args) throws Throwable
	{
		ScreenRecorderUtil.startRecord("main");
		chromesetup();
		test();
		teardown();
		firefoxsetup();
		test();
		teardown();
		ScreenRecorderUtil.stopRecord();
	}
	
	public static void teardown() throws Throwable
	{
		Thread.sleep(5);
		driver.quit();
	}
	
}
