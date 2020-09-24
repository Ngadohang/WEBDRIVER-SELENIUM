package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_10_UserInterface {
	WebDriver driver;
	Actions action;
	WebElement element;
	@BeforeClass
	public void beforeClass() {
		driver =new FirefoxDriver();
		action=new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_1_leftClick() {
		//click()
	}
	@Test
	public void TC_2_doubleClick() {
		//doubleClick()
	}
	@Test
	public void TC_3_pressHover() {
		//clickAndHold()
	}
	@Test
	public void TC_4_hover() {
		//contextClick()
	}
	@Test
	public void TC_5_rightClick() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		element=driver.findElement(By.xpath("//span[text()='right click me']"));
		//click righ button
		action.contextClick(element).perform();;
		//hover mouse
		element=driver.findElement(By.xpath("//span[text()='Quit']/parent::li"));
		action.moveToElement(element).perform();
		//verify hover 'Quit' has: "context-menu-icon-quit context-menu-hover context-menu-visible"
		String elementAttribute=element.getAttribute("class");
		Assert.assertTrue(elementAttribute.contains("context-menu-visible"));
		Assert.assertTrue(elementAttribute.contains("context-menu-hover"));
		// using isDisplay()
		Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-quit.context-menu-hover.context-menu-visible")).isDisplayed());
		
	}
	
	public void TC_6_Key() {
		//keyUp(),keyDown(),senKeys()
	}
	
	@AfterClass
	public void afterClass() {
		
	}
}
