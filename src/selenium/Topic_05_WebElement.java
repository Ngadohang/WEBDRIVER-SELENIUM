package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_WebElement {

	WebDriver driver;
	
	
	By emailTextboxBy=By.id("mail");
	By ageUnderRadioBy=By.id("under_18");
	By educationTextAreaBy=By.id("edu");
	
	
	@BeforeClass
	public void TC_01() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		

		driver.get("");
	
	}
	@Test
	public void TC_02() {
		if(driver.findElement(emailTextboxBy).isDisplayed()) {
			System.out.println("Element is displayed with: "+ emailTextboxBy);
		}else {
			System.out.println("Element is not displayed with"+ emailTextboxBy);
		}
		
		if(driver.findElement(ageUnderRadioBy).isDisplayed()) {
			System.out.println("Element is displayed with:"+ ageUnderRadioBy);
		}else {
			System.out.println("Element is not displayed with:"+ ageUnderRadioBy);
		}
		
		if(driver.findElement(educationTextAreaBy).isDisplayed()) {
			System.out.println("Element is displayed with:"+ educationTextAreaBy);
		}else {
			System.out.println("Element is not displayed with"+ educationTextAreaBy );
		}
		
		
		
		
		
		
	}

	@AfterClass
	public void TC_03() {
		
	}



}



