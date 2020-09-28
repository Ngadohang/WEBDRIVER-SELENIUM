package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;




public class Topic_14_ExplicitWait {
	WebDriver driver;
	WebElement element;
	WebDriverWait explicitWait;
	String today= "Wednesday, September 09, 2020";
	
	@BeforeClass
	public void beforeClass() {
		driver =new FirefoxDriver();
		//manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		explicitWait= new WebDriverWait(driver,10);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_PresenceAlert(){
		driver.get("");
	}
	
	
	@Test
	public void TC_AjaxLoading(){
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ctl00_ContentPlaceholder1_Panel1")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='No Selected Dates to display.']")).isDisplayed());
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("\\[@title='\"+ today +\"']"))).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath("//div[@class='raDiv']"))));
		//explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@style,'position')]/div[@class='raDiv']")));
		
		

		
	}

	@AfterClass
	public void afterClass() {
		
	}
}
