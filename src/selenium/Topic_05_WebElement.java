package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_05_WebElement {

	WebDriver driver;
	
	
	By emailTextboxBy=By.id("mail");
	By ageUnderRadioBy=By.id("under_18");
	By educationTextAreaBy=By.id("edu");
	By user5TextBy= By.xpath("//h5[text()='Name: User5']");
	By javaCheckbox=By.id("java");
	//Disabled
	By passwordTextboxBy= By.id("password");
	By bioTextAreaBy=By.id("bio");
	
	@BeforeClass
	public void TC_BeforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://automationfc.github.io/basic-form/");
	}
	@Test
	public void TC_01_Displayed() throws Exception {
		if(IsDisplayedElement(emailTextboxBy)) {
			SendKeyElement(emailTextboxBy,"ngado");
			}
		
		if(IsEnableElement(ageUnderRadioBy)) {
			ClickElement(ageUnderRadioBy);
		}
		
		if(IsDisplayedElement(educationTextAreaBy)) {
			SendKeyElement(educationTextAreaBy,"ngado");
		}
	 Assert.assertFalse(IsDisplayedElement(user5TextBy));
	 	
	 }
	
	@Test
	public void TC_02_Enable() throws Exception {
		driver.navigate().refresh();
		//mong muốn các element sau có thể thao tác
		Assert.assertTrue(IsEnableElement(emailTextboxBy));
		Assert.assertTrue(IsEnableElement(ageUnderRadioBy));
		Assert.assertTrue(IsEnableElement(educationTextAreaBy));
		//mong muốn các element sau không thao tác được
		Assert.assertFalse(IsEnableElement(bioTextAreaBy));
	}
	@Test
	public void TC_03_Selected()  {
		driver.navigate().refresh();
		if(IsDisplayedElement(ageUnderRadioBy)) {
			ClickElement(ageUnderRadioBy);
		}
		if(IsDisplayedElement(javaCheckbox)) {
			ClickElement(javaCheckbox);
		}
		//kiểm tra 2 element được chọn thành công
		Assert.assertTrue(IsSelectElement(ageUnderRadioBy));
		Assert.assertTrue(IsSelectElement(javaCheckbox));
		//Bỏ chọn
		ClickElement(javaCheckbox);
		//kiểm tra element được bỏ chọn thành công
		Assert.assertFalse(IsSelectElement(javaCheckbox));
		Assert.assertTrue(IsSelectElement(ageUnderRadioBy));
	}
	
	public boolean IsDisplayedElement(By by) {
		WebElement element= driver.findElement(by);
		if(element.isDisplayed()) {
			System.out.print("Element is displayed with:"+by);
			return true;
		}else {
			System.out.print("Element is not displayed with:"+by);
			return false;
		}
	}
	public boolean IsEnableElement(By by) {
		WebElement element= driver.findElement(by);
		if(element.isEnabled()) {
			System.out.print("Element is enable with:"+by);
			return true;
		}else {
			System.out.print("Element is not enable with:"+by);
			return false;
		}
	}
	public void SendKeyElement(By by,String value ) {
		WebElement element=driver.findElement(by);
		element.clear();
		element.sendKeys(value);
	}
	
	public void ClickElement(By by) {
		WebElement element=driver.findElement(by);
		element.click();
	}
	
	public boolean IsSelectElement(By by) {
		WebElement element=driver.findElement(by);
		 if(element.isSelected()) {
			System.out.print("Element is selected with:"+by);
			return true;
		}else {
			System.out.print("Element is not selected with:"+by);
			return false;
		}		
	}

	@AfterClass
	public void TC_03() {
		driver.quit();
	}



}



