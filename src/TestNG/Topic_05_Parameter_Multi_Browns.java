package TestNG;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class Topic_05_Parameter_Multi_Browns {
	WebDriver driver;
	WebElement element;
	WebDriverWait explicitWait;
	
	String source_folder=System.getProperty("user.dir");
	By email= By.id("email");
	By password= By.id("pass");
	
	
	@Parameters("browserName")
	@BeforeClass
	public void beforeClass(@Optional("chrome") String browser) {
		if(browser.equals("firefox")) {
			System.setProperty("webdriver.geckodriver.driver", source_folder + "\\driver\\geckodriver.exe");
			driver =new FirefoxDriver();
		}else if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",source_folder+"\\driver\\chromedriver.exe");			
			driver =new ChromeDriver();
			
		}else if (browser.equals("egde")){
			System.setProperty("webdriver.edge.driver", source_folder+ "\\driver\\msedgedriver.exe");
	    	driver=new EdgeDriver();
		}else {
			throw new RuntimeException("Please Input your browser name");
		}
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
	public Object[][] DataTest(Method method){
		Object[][] result= null;
		if(method.getName().contains("Login")) {
			result= new Object [][] {
					{"automation_13@gmail.com","123123"}
			};
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

