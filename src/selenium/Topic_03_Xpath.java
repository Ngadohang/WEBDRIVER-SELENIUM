package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class Topic_03_Xpath {
		WebDriver driver;
			
	@BeforeClass
    public void BeforeClass() {
		driver =new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
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
		String passwordErrorMessage1= driver.findElement(By.id("advice-validate-password-pass")).getText();
		Assert.assertEquals(passwordErrorMessage1, "Please enter 6 or more characters without leading or trailing spaces.");
		Assert.assertTrue(passwordErrorMessage1.contains("Please enter 6 or more characters without leading or trailing spaces."));
		
	}
	@Test
	public void TC_03_LogwithInvalidEmail() throws InterruptedException {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("email")).sendKeys("ngado29895@gmail");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();
		String emailErrorMessage2 = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(emailErrorMessage2, "Please enter a valid email address. For example johndoe@domain.com.");
		
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
		//Cách lấy tuyệt đối,text ngắn,không có ký tự xuống dòng,khoảng trắng đầu dòng/cuối dòng
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText(),"MY DASHBOARD");
		//Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']/h1[text()='MY DASHBOARD']")).isDisplayed());
		
		//Cách lấy tương đối,text ngắn, có ký tự xuống dòng,có khoảng trắng đầu dòng/cuối dòng
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']/h1[contains(text(),'My Dashboard')]")).isDisplayed());
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='hello']/strong")).getText(),"Hello, Automation Testing!");
		
		String contactInformationText=driver.findElement(By.xpath("//div[@class='col-1']//div[@class='box-content']/p")).getText();
		Assert.assertTrue(contactInformationText.contains("Automation Testing"));
		Assert.assertTrue(contactInformationText.contains("automation_13@gmail.com"));
		
		//c2
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//div[@class='box-content']/p[contains(.,'Automation Testing')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//div[@class='box-content']/p[contains(.,'automation_13@gmail.com')]")).isDisplayed());
	
		//logout
		driver.findElement(By.xpath("//span[@class='label' and contains(.,'Account')]")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
	
	}
	
	@Test
	public void TC_06_Register_To_System() throws InterruptedException {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account' and @class='button']//span[text()='Create an Account']")).click();
		
		String firstname="Do";
		String lastname="Nga";
		String email = firstname+ randomnumber() + lastname+ "@gmail.com";
		
		driver.findElement(By.xpath("//*[@id='firstname' and @name='firstname']")).sendKeys(firstname);
		driver.findElement(By.xpath("//*[@id='lastname' and @name='lastname']")).sendKeys(lastname);
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.xpath("//*[@id='password' and @name='password']")).sendKeys("123456");
		driver.findElement(By.xpath("//*[@id='confirmation' and @name='confirmation']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Thank you for')]")).isDisplayed());
		
		String contactInformationText=driver.findElement(By.xpath("//div[@class='col-1']//div[@class='box-content']/p")).getText();
		Assert.assertTrue(contactInformationText.contains(firstname+" "+lastname));
		Assert.assertTrue(contactInformationText.contains(email));
		
		//logout
		driver.findElement(By.xpath("//span[@class='label' and contains(.,'Account')]")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		}	
	
	public int randomnumber() {
		Random rand = new Random();
	    return rand.nextInt(999999);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
}
