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

public class Topic_06_Textbox_Textarea {
	WebDriver driver;
	//khai bao bien 
	String loginPageUrl, userID, password;
	String customerName, date, month, year, dateOfBirthInput, dateOfBirthOutput, address, city, state, pin, phone,
			email, gender, customerID;
	
	//Khai bao locator
	By customerNameTextbox = By.name("name");
	By dobTextbox = By.name("dob");
	By addrTextArea = By.name("addr");
	By cityTextbox = By.name("city");
	By stateTextbox = By.name("state");
	By pinTextbox = By.name("pinno");
	By phoneTextbox = By.name("telephoneno");
	By emailTextbox = By.name("emailid");
	By passwordTextbox = By.name("password");
	
	@BeforeClass
	// Arrange (Pre-Condition)
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4");
		//new customer
		customerName = "Do Hang Nga";
		date = "29";
		month = "08";
		year = "1995";
		dateOfBirthInput = month+"/"+date+"/"+year;
		dateOfBirthOutput = year+"-"+month+"-"+year;
		address = "11 pho moi\nTrinh Nguyen\nChau Khe\nTu Son\nBac Ninh";
		city = "Bac Ninh";
		state = "Tu Son";
		pin = "150000";
		phone = "0335922093";
		email = "tony"+ getRandomNumber()+"@gmail.com";
	//edit customer
		

	}

	@Test
	public void TC_01_Register() {
		loginPageUrl = driver.getCurrentUrl();
		
		driver.findElement(By.xpath("//a[text()='here']")).click();

		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys("ngado29895@gmail.com");

		driver.findElement(By.name("btnLogin")).click();

		// Local: dung trong pham vi testcase
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']//following-sibling::td")).getText();

	}

	@Test
	public void TC_02__Login() {
		// Step: get/senKey/click (Action);
		// 2
		driver.navigate().back();
		driver.navigate().back();
		// 3
		driver.get(loginPageUrl);
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);

		driver.findElement(By.name("btnLogin")).click();
		// Verify: True/False/
		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(),
				"Welcome To Manager's Page of Guru99 Bank");

	}

	@Test
	public void TC_03_New_Customer()throws Exception{
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(customerNameTextbox).sendKeys(customerName);
		driver.findElement(dobTextbox).sendKeys(dateOfBirthInput);
		driver.findElement(By.xpath("//input[@value='m']")).click();
		driver.findElement(addrTextArea).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(phoneTextbox).sendKeys(phone);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(password);
		
		Thread.sleep(20000);
		driver.findElement(By.name("sub")).click();
		
		//Verify dòng xác nhận
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(),"Customer Registered Successfully!!!");
		//Verify đã nhập text
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),dateOfBirthOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Moblie No.']/following-sibling::td")).getText(),phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),email);
		customerID=driver.findElement(By.xpath("")).getText();
		
	}
	
	@Test
	public void TC_04_Edit_Customer() {
		
		
	}
	public int getRandomNumber() {
		Random rand= new Random();
		return rand.nextInt(999999);
	}
	@AfterClass
	public void afterClass() {

	}

}
