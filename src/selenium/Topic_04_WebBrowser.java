package selenium;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_WebBrowser {
	WebDriver driver;
	
	@BeforeClass
	public void BeforeTest() {
		driver= new FirefoxDriver();
		 
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void TC_01_Url() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/login/");  
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/create/");	
	}
	
	@Test
	public void TC_02_Title() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Assert.assertEquals(driver.getTitle(),"Customer Login");
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		Assert.assertEquals(driver.getTitle(),"Create New Customer Account" );
		
		
	}
	@Test
	public void TC_03_Navigate() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		
		driver.navigate().back();

		Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/login/");
		Assert.assertEquals(driver.getTitle(),"Customer Login");
		
		driver.navigate().forward();
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/create/");
		Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
	}
	@Test
	public void TC_04_Page_Source() {
		String Pagesource;
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Pagesource =driver.getPageSource();
		Assert.assertFalse(Pagesource.contains("Login or Create an Account"));
				
	}
	
	@AfterClass
	public void AfterTest() {
		driver.quit();
	}
}
