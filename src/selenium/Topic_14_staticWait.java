package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.annotations.VisibleForTesting;


public class Topic_14_staticWait {
	WebDriver driver;
	WebElement element;
	//WebDriverWait explicitWait;
	String source_folder= System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.geckodriver.driver", source_folder + "\\driver\\geckodriver.exe");
		driver =new FirefoxDriver();
		//driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		//explicitWait= new WebDriverWait(driver,10);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_10S(){
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.cssSelector("#start>button")).click();
		SleepInSecond(10);
		Assert.assertTrue(driver.findElement(By.cssSelector("#finish>h4")).isDisplayed());
		
	}
	@Test
	public void TC_4S(){
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.cssSelector("#start>button")).click();
		
		SleepInSecond(6);
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
		
	}
	@Test
	public void TC_1S(){
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.cssSelector("#start>button")).click();
		
		SleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
		
	}
	
	
	public void SleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
