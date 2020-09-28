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


public class Topic_14_statusELement {
	WebDriver driver;
	WebElement element;
	//WebDriverWait explicitWait;
	@BeforeClass
	public void beforeClass() {
		driver =new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		//explicitWait= new WebDriverWait(driver,10);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_1_statusElement(){
		
	}

	@AfterClass
	public void afterClass() {
		
	}
}
