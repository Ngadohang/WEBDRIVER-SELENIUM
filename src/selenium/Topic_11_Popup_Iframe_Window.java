package selenium;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Popup_Iframe_Window {
	WebDriver driver;
	
	boolean status;
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver =new FirefoxDriver();
		//driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
	public void TC_1_PopupFix() {
		driver.get("https://zingpoll.com/");
		
		//Kich dang nhap
		driver.findElement(By.id("Loginform")).click();
		//verify poup hiên thi
		status=driver.findElement(By.id("Login")).isDisplayed();
		sleepInSecond(4);
		Assert.assertTrue(status);
		//close popup
		
		driver.findElement(By.cssSelector("#Login .close")).click();;
		sleepInSecond(4);
		//verify popup 
		status=driver.findElement(By.id("Login")).isDisplayed();
		Assert.assertFalse(status);
		//log in
		driver.findElement(By.id("Loginform")).click();
		driver.findElement(By.id("loginEmail")).sendKeys("automationfc.vn@gmail.com");
		driver.findElement(By.id("loginPassword")).sendKeys("automationfc");
		driver.findElement(By.id("button-login")).click();
		sleepInSecond(3);
		//verify
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='wide']/div[contains(.,'Automation Testing')]")).isDisplayed());		
	}
	
	public void TC_2_PopupUnFix() {
		driver.get("https://blog.testproject.io/");
		sleepInSecond(13);
		//step1
		if(driver.findElement(By.xpath("//div[@id='mailch-bg']/div[@class='mailch-wrap rocket-lazyload']")).isDisplayed()) {
			Assert.assertTrue(driver.findElement(By.xpath("//a[contains(.,' Sign Up Now ')]")).isDisplayed());
			driver.findElement(By.id("close-mailch")).click();
			Assert.assertFalse(driver.findElement(By.xpath("//div[@id='mailch-bg']/div[@class='mailch-wrap rocket-lazyload']")).isDisplayed());
			
		}
		//step2
		//step3
	}
	
	
	public void TC_3_Frame_iframe() {
		driver.get("https://kyna.vn/");
		//switchto toi iframe
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']//iframe")));
		//verify 
		String iframeText=driver.findElement(By.xpath("//a[@title='Kyna.vn']")).getText();
		Assert.assertEquals(iframeText, "Kyna.vn");
		//Switch ve default content
		driver.switchTo().defaultContent();
		//Switch toi iframe khac
		driver.switchTo().frame("cs_chat_iframe");
		Assert.assertTrue(driver.findElement(By.cssSelector(".chat-area")).isDisplayed());
		//Senkey wechat
		sleepInSecond(7);
		driver.findElement(By.cssSelector(".textarea")).sendKeys("hi");
		driver.findElement(By.cssSelector(".textarea")).sendKeys(Keys.ENTER);
		sleepInSecond(4);
		Assert.assertTrue(driver.findElement(By.id("sign-in-menu")).isDisplayed());
		//Switch ve default content
		driver.switchTo().defaultContent();
		
		driver.findElement(By.id("live-search-bar")).sendKeys("Java");
		driver.findElement(By.id("live-search-bar")).sendKeys(Keys.ENTER);
		sleepInSecond(4);
		Assert.assertEquals(driver.findElement(By.cssSelector(".menu-heading>h1")).getText(),"'Java'");
	}
	
	@Test
	public void TC_4_TabSwitchTab() {
		//Lấy parentID
		//String parentWindow=driver.getWindowHandle();
		//Lấy tất cả ID tab/window
		//Set<String> allWindows=driver.getWindowHandles();
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		switchToWindowsByTitle("Google","https://www.google.com.vn/");
		switchToWindowsByTitle("SELENIUM WEBDRIVER FORM DEMO","https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		switchToWindowsByTitle("Facebook - Đăng nhập hoặc đăng ký","https://www.facebook.com/");
		switchToWindowsByTitle("SELENIUM WEBDRIVER FORM DEMO","https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		switchToWindowsByTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn","https://tiki.vn/");
		switchToWindowsByTitle("SELENIUM WEBDRIVER FORM DEMO","https://automationfc.github.io/basic-form/index.html");
		closeWindowsWithoutParent("SELENIUM WEBDRIVER FORM DEMO");
	}
	
	@Test
	public void TC_5() {
		driver.get("https://kyna.vn/");
		sleepInSecond(5);
		//Dong popup
		
		//Dong iframe
		driver.switchTo().frame("");
		//
		
	}
	
	public void TC_6() {
		
	}
	
	public void switchToWindowsById(String parentID) {
		Set<String> allWindows= driver.getWindowHandles();
		for (String windowID : allWindows) {
			if(!windowID.equals(parentID)) {
				driver.switchTo().window(windowID);
				break;
			}
		}
	}
	
	public boolean switchToWindowsByTitle(String titleTarget, String urlTarget) {
		Set<String> allWindows= driver.getWindowHandles();
		for (String windowID : allWindows) {
			driver.switchTo().window(windowID);
			String titleCurrent= driver.getTitle();
			if(titleCurrent.equals(titleTarget)) {
				break;
			}
		}
		if(driver.getCurrentUrl().equals(urlTarget)) {
			return true;
		}else return false;
	}
	
	public boolean closeWindowsWithoutParent(String titleParent) {
		Set<String> allWindows= driver.getWindowHandles();
		for (String windowID : allWindows) {
			driver.switchTo().window(windowID);
			String titleCurrent =driver.getTitle();
			if(!titleCurrent.equals(titleParent)) {
				driver.close();
			}else {
				driver.switchTo().window(windowID);
			}
		}
		if(allWindows.size()==1) {
			return true;
		}else return false;
	}
	
	public void clickELementHidden(By clickXpath) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(clickXpath));
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
