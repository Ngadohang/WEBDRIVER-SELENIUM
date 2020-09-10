package Xpath;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UseXpath {
		WebDriver driver;
			
	@BeforeClass
    public void BeforeClass() {
		driver =new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void TC_01_LoginWithEmptyEmailandPassword() throws InterruptedException {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");
		driver.findElement(By.id("send2")).click();
		String emailErrorMessage = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		String passwordErrorMessage= driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(emailErrorMessage, "This is a required field.");
		Assert.assertEquals(passwordErrorMessage, "This is a required field.");
		
	}
	

	@Test
	public void TC_02_LoginwithInvalidPassword() throws InterruptedException {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("email")).sendKeys("ngado29895@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("45");
		driver.findElement(By.id("send2")).click();
		String emailErrorMessage1 = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		String passwordErrorMessage1= driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(emailErrorMessage1, "");
		Assert.assertEquals(passwordErrorMessage1, "Please enter 6 or more characters without leading or trailing spaces.");
		
	}
	@Test
	public void TC_03_LogwithInvalidEmail() throws InterruptedException {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("email")).sendKeys("ngado29895 @ gmail");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();
		String emailErrorMessage2 = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		String passwordErrorMessage2= driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(emailErrorMessage2, "Please enter a valid email address. For example johndoe@domain.com.");
		Assert.assertEquals(passwordErrorMessage2, "");
		
	}
	@Test
	public void TC_04_loginwithIncorrectEmail_Password() throws InterruptedException {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("email")).sendKeys("ngado29895@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();
		
		String sucessMessage= driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText();
		Assert.assertEquals(sucessMessage,"Invalid login or password.");
	}
	@Test
	public void TC_05_loginwithCorrectEmail_Password() throws InterruptedException {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("email")).sendKeys("automation_13@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123123");
		driver.findElement(By.id("send2")).click();
		
		String note = driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText();
		String note1 = driver.findElement(By.xpath("//p[@class='hello']/strong")).getText();
		String note2= driver.findElement(By.xpath("//div[@class='col-1']/div[@class='box']//p")).getText();
		Assert.assertEquals(note,"My Dashboard");
		Assert.assertEquals(note1,"Hello, Automation Testing!");
		Assert.assertEquals(note2,"Automation Testing automation_13@gmail.com");
	}
	@Test
	public void TC_06_loginwithCorrectEmail_Password() throws InterruptedException {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("email")).sendKeys("automation_13@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123123");
		driver.findElement(By.id("send2")).click();
		
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
