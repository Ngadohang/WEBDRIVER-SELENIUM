package DataProviderTestngExcel;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class DataProviderFrameword {
	WebDriver driver;
	WebElement element;
	WebDriverWait explicitWait;
	String source_folder=System.getProperty("user.dir");
	By email= By.id("email");
	By password= By.id("pass");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.geckodriver.driver", source_folder + "\\driver\\geckodriver.exe");
		driver =new FirefoxDriver();
		//driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		explicitWait= new WebDriverWait(driver,10);
		driver.manage().window().maximize();
	}
	
	@Test(dataProvider="DataTest")
	public void TC_Login_Accout(String gmailData, String passwordData) {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(email).sendKeys(gmailData);
		driver.findElement(password).sendKeys(passwordData);
		
		driver.findElement(By.id("send2")).click();
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.xpath("//p[contains(.,'"+gmailData+"')]"))));
		
		driver.findElement(By.xpath("//a[contains(@class,'skip-account')]")).click();
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='header-account']//li[contains(@class,'last')]"))).click();
	
	}
	
	@Test(dataProvider="DataTest")
	public void TC_Register_Accout(String A, String B, String C) {
		System.out.println(A+B+C);
	}
	
	@DataProvider(name="DataTest")
	public Object[][] DataTest(Method method) throws Exception {
		Object[][] result= null;
		if(method.getName().contains("Login")) {
			result= ExcelUtils.getTableArray("", "Sheet1");
			}
		if(method.getName().contains("Register")) {
			result= new Object [][] {
				{"C","c","hó"}
			};
		}
		return result;
		
	}
		
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

