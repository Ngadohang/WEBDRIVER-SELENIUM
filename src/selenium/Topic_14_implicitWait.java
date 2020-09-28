package selenium;

import java.util.List;
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


public class Topic_14_implicitWait {
	WebDriver driver;
	WebElement element;
	WebDriverWait explicitWait;
	@BeforeClass
	public void beforeClass() {
		driver =new FirefoxDriver();
		//manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		explicitWait= new WebDriverWait(driver,10);
		driver.manage().window().maximize();
	}
	
	public void TC_findELement(){
		driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		driver.findElement(By.cssSelector("#start>button")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("#finish>h4")).isDisplayed());
		
	}
	@Test
	public void TC_findELements(){
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		List<WebElement> Buttons =driver.findElements(By.cssSelector("#start>button"));
		int sizeButon=Buttons.size();
		if(sizeButon>1) {
			for (WebElement Button : Buttons) {
				Button.click();
			}
			System.out.println("Tìm thấy"+sizeButon+"element");
		}else if(sizeButon==1) {
			for (WebElement Button : Buttons) {
				Button.click();
			}	
			System.out.println("Tìm thấy 1 element");
		}else {System.out.println("Không tìm thấy element");}
		
		List<WebElement> H4s =driver.findElements(By.cssSelector("#finish>h4"));
		int sizeH4=H4s.size();
		if(sizeH4>1) {
			for (WebElement H4 : H4s) {
				Assert.assertTrue(H4.isDisplayed());
			}
			System.out.println("Tìm thấy"+sizeH4+"element");
		}else if(sizeH4==1) {
			for (WebElement H4 : H4s) {
				Assert.assertTrue(H4.isDisplayed());
			}	
			System.out.println("Tìm thấy 1 element");
		}else {System.out.println("Không tìm thấy element");}
		
		
		
	}

	

	@AfterClass
	public void afterClass() {
		
	}
}
