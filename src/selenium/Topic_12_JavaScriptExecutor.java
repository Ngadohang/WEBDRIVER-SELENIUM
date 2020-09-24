package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_12_JavaScriptExecutor {
	WebDriver driver;
	JavascriptExecutor js;
	String customerName, date, month, year, dateOfBirthInput, dateOfBirthOutput, address, city, state, pin, phone,
	email, gender, customerID, password;
	
	By customerNameTextbox = By.name("name");
	By dobTextbox = By.name("dob");
	By addrTextArea = By.name("addr");
	By cityTextbox = By.name("city");
	By stateTextbox = By.name("state");
	By pinTextbox = By.name("pinno");
	By phoneTextbox = By.name("telephoneno");
	By emailTextbox = By.name("emailid");
	By passwordTextbox = By.name("password");
	
	//String source_folder = System.getProperty("user.dir");
	
	
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.chrome.driver",".\\driver\\chromedriver.exe");
		System.setProperty("webdriver.geckodriver.driver",".\\driver\\geckodriver.exe");
		//Chặn nhật ký với cảnh báo 
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
		//driver=new ChromeDriver();
		driver =new FirefoxDriver();
		js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//Data login
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
		password="123456";
	}
	
	public void TC_1() {
		navigatePage("http://live.demoguru99.com/");
		getDomainPage("http://live.demoguru99.com");
		getUrlPage("http://live.demoguru99.com/");
		
		hightLightElement(By.cssSelector(".first>a.level0"));
		sleepInSecond(5);
		clickElementHidden(By.cssSelector(".first>a.level0"));

		hightLightElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button"));
		sleepInSecond(5);
		clickElementHidden(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button"));
		
		String expectedText=driver.findElement(By.cssSelector("li.success-msg span")).getText();
		verityTextInInnerText(expectedText);
		clickElementHidden(By.xpath("//a[text()='Customer Service']"));
		Assert.assertEquals(getTitlePage(),"Customer Service");
		//scrollToBottomPage();
		scrollToElementPage(By.id("newsletter"));
		verityTextInInnerText("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo.");
		navigatePage("http://demo.guru99.com/v4/");
		Assert.assertTrue(getDomainPage("demo.guru99.com"));
	}
	
	public void TC_2() {
		driver.get("http://demo.guru99.com/v4/");
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr284952");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("mebUtAq");
		
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		sleepInSecond(4);
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		driver.findElement(customerNameTextbox).sendKeys(customerName);
		driver.findElement(By.xpath("//input[@value='m']")).click();
		removeAttribute(dobTextbox,"type");
		sleepInSecond(5);
		driver.findElement(dobTextbox).sendKeys(dateOfBirthInput);
		driver.findElement(addrTextArea).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(phoneTextbox).sendKeys(phone);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(password);
		
		driver.findElement(By.name("sub")).click();
		sleepInSecond(5);
		//Verify dòng xác nhận
//		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(),"Customer Registered Successfully!!!");
//		//Verify đã nhập text
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),customerName);
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),gender);
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),dateOfBirthOutput);
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),address);
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),city);
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),state);
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),pin);
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Moblie No.']/following-sibling::td")).getText(),phone);
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),email);
		
	}
	@Test
	public void TC_3() {
		navigatePage("http://live.demoguru99.com/");
		clickElementHidden(By.cssSelector("#header-account a[title='My Account']"));
		clickElementHidden(By.cssSelector("a[title='Create an Account']"));
		
		senkeyToElement(By.id("firstname"), "Do");
		senkeyToElement(By.id("middlename"), "Hang");
		senkeyToElement(By.id("lastname"), "Nga");
		senkeyToElement(By.id("email_address"), "ngado"+getRandomNumber()+"@gmail.com");
		senkeyToElement(By.id("password"), "123456");
		senkeyToElement(By.id("confirmation"), "123456");
		
		clickElementHidden(By.cssSelector("button[title='Register']"));
		sleepInSecond(5);
		verityTextInInnerText("Thank you for registering with Main Website Store.");
		
		clickElementHidden(By.cssSelector("a[title='Log Out']"));
		
		sleepInSecond(10);
		
		Assert.assertTrue(getUrlPage("http://live.demoguru99.com/index.php/"));

		
	}
	
	public void navigatePage(String url) {
		js.executeScript("window.location='"+url+"'");
	}
	
	public boolean getUrlPage(String url) {
		String currentUrl= (String)js.executeScript("return document.URL");
		if(currentUrl.equals(url)) {
			return true;
		}else return false;
	}
	
	public boolean getDomainPage(String domain) {
		String currentDomain=(String)js.executeScript("return document.domain");
		if(currentDomain.equals(domain)) {
			return true;
		}else return false;
	
	}
	public void clickElementHidden(By elementXpath) {
		WebElement element= driver.findElement(elementXpath);
		js.executeScript("arguments[0].click()",element);
		
	}
	
	public boolean verityTextInInnerText(String expectedText) {
		String actualText= (String) js.executeScript("return document.documentElement.innerText.match('"+expectedText+"')[0]");
		return actualText.equals(expectedText);
	}

	public String getTitlePage() {
		return js.executeScript("return document.title").toString();
		
	}
	
	public void scrollToBottomPage() {
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	public void scrollToElementPage(By elementXpath) {
		WebElement element= driver.findElement(elementXpath);
		js.executeScript("arguments[0].scrollIntoView(true);",element);
	}
	
	public void removeAttribute(By elementXpath, String Attribute) {
		WebElement element=driver.findElement(elementXpath);
		js.executeScript("arguments[0].removeAttribute('"+Attribute+"');", element);
	}
	
	public void senkeyToElement(By elementXpath,String value) {
		WebElement element=driver.findElement(elementXpath);
		js.executeScript("arguments[0].setAttribute('value','"+value+"')", element);
	}
	
	public void hightLightElement(By elementXpaht) {
		WebElement element=driver.findElement(elementXpaht);
		String orginalStyle= element.getAttribute("style");
		js.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])",element,"style","border:5px soild red;border-style:dashed;");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		js.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])",element,"style",orginalStyle);

	}
	
	
	public int getRandomNumber() {
		Random rand= new Random();
		return rand.nextInt(999999);
	}
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
