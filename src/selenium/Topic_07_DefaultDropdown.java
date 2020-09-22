package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_DefaultDropdown {
	WebDriver driver;
	Select select;
	@BeforeClass
	public void beforeClass() {
		driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_Single() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//tim dropdow Date birth month
		select =new Select(driver.findElement(By.id("job1")));
		//Kiem tra dropdowm là single dropdown hay multiple select
		Assert.assertFalse(select.isMultiple());
		//tim kiem gia tri dropdown bang selectByVisibleText
		select.selectByVisibleText("Manual Testing");
		//Tim kiem gia tri trong dropdown bang selectByValue
		//select.selectByValue("manual");
		//tim kiem gia tri trong dropdown băng selectByIndex
		//select.selectByIndex(5);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");
		//Kiem tra xem dropdown co tong bao nhieu item
		Assert.assertEquals(select.getOptions().size(), 10);
		
		
	}
	@Test
	public void TC_02_Multiple() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		select =new Select(driver.findElement(By.id("job2")));
		Assert.assertTrue(select.isMultiple());
		//chon nhieu cung 1 luc
		select.selectByVisibleText("Automation");
		select.selectByVisibleText("Manual");
		select.selectByVisibleText("Adhoc");
		select.selectByVisibleText("Mobile");
		
		Assert.assertEquals(select.getAllSelectedOptions().size(),4);
	}
	@Test
	public void TC_03() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		
		driver.findElement(By.id("gender-male")).click();
		
		//Date
		select=new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText("29");
		Assert.assertEquals(select.getOptions().size(),32);
		
		//Month
		select=new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText("August");
		Assert.assertEquals(select.getOptions().size(),13);
		
		//Year
		select=new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText("1995");
		Assert.assertEquals(select.getOptions().size(),112);
		
		driver.findElement(By.id("Email")).sendKeys(""+getRandomNumber()+ "@gmail.com");
		driver.findElement(By.id("FirstName")).sendKeys("Do Hang");
		driver.findElement(By.id("LastName")).sendKeys("Nga");
		driver.findElement(By.id("Company")).sendKeys("Tiki");
		
		driver.findElement(By.id("Password")).sendKeys("123456");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.className("result")).getText(),"Your registration completed");
		
		driver.findElement(By.name("register-continue")).click();
		
		select=new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(),"29");
		
		select=new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(),"August");
		
		select=new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(),"1995");
	}
	
	
	
	public int getRandomNumber() {
		Random rand=new Random();
		return rand.nextInt(99999);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
