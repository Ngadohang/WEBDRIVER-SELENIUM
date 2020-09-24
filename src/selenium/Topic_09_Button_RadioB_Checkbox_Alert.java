package selenium;

import java.io.IOException;
import java.sql.Driver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_RadioB_Checkbox_Alert {
	WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait explicitWait;
	String source_folder= System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		driver=new FirefoxDriver();
		js=(JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	

	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		
		WebElement loginButton= driver.findElement(By.cssSelector("button.fhs-btn-login"));
		Assert.assertFalse(loginButton.isEnabled());
		
		driver.findElement(By.cssSelector("#login_username")).sendKeys("ngado29895@gmail.com");
		driver.findElement(By.cssSelector("#login_password")).sendKeys("123456");
		Assert.assertTrue(isElementEnable(loginButton));
		loginButton.click();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.fhs-login-msg")).getText(),"Số điện thoại/Email hoặc Mật khẩu sai!");
		
		sleepInSecond(2);
		
	    driver.findElement(By.cssSelector("#login_username")).clear();
		driver.findElement(By.cssSelector("#login_password")).clear();
//		//Bat su kien 
				
//		Assert.assertFalse(isElementEnable(loginButton));
//		sleepInSecond(2);
//		//Xóa thuộc tính disable,click button, kiểm tra messager thông báo
//		removeAttribute(loginButton);
//		loginButton.click();
//		Assert.assertEquals(driver.findElement(By.cssSelector("div.checked-error div")).getText(),"Thông tin này không thể để trống");
//		Assert.assertEquals(driver.findElement(By.cssSelector("//lable[text()='Mật khẩu']//following-sibling::div[contains(.,'Thông tin này')]")).getText(),"Thông tin này không thể để trống");
	}
	
	public void TC_02_Default_CheckBox_Radio() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		isCheckBox(By.cssSelector(".k-checkbox-label"));
		
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		isRadioButton(By.cssSelector("input[type='radio']"));
	}
	
	public void TC_02_Customer_CheckBox_Radio() {
		driver.get("https://material.angular.io/components/radio/examples");
		isRaidoButtonCustomer(By.cssSelector("div.mat-radio-container input"));
		
		driver.get("");
	}
	
	public void TC_03_Alert() {
//		Alert alert= driver.switchTo().alert();
//		String alertText=alert.getText();
//		alert.accept();
//		alert.dismiss();
//		alert.sendKeys("abc");
//		
	}
	
	public void TC_04_AthenticationAlert() {
		handleAthenticationAlert("http://the-internet.herokuapp.com/basic_auth","admin","admin");
	}
	
	@Test
	public void TC_04_AuthenticationAlertAuto() throws IOException {
		String authenPath= source_folder + "\\autoIt\\authen_chrome.exe";
		driver.get("http://the-internet.herokuapp.com/basic_auth");
		String username="admin";
		String password="admin";
		sleepInSecond(5);
		if(driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] {authenPath,username,"password"});
		}else if(driver.toString().contains("chrome")||driver.toString().contains("egde")) {
			Runtime.getRuntime().exec(new String[] {authenPath,username,"password"});
		}
		sleepInSecond(5);
		
	}
	
	public void handleAthenticationAlert(String url,String username, String password) {
		String slipUrl [] =url.split("//");
		String newUrl= slipUrl[0]+ "/" +username+":"+password+"@"+slipUrl[1];
		driver.get(newUrl);
		sleepInSecond(2);
		Assert.assertEquals(driver.getCurrentUrl(), "http://the-internet.herokuapp.com/basic_auth");
	}
	
	public boolean isCheckBox(By checkAllXpath) {
		List<WebElement> allElement=driver.findElements(checkAllXpath);
		int allCheck= allElement.size();
		while(allCheck>0) {
			for (WebElement checkItem : allElement) {
				if(checkItem.isEnabled()) {
					if(!checkItem.isSelected()) {
						checkItem.click();
						sleepInSecond(3);
			
						allCheck--;
					}
				}else {
					Assert.assertTrue(checkItem.isEnabled());
				}
			}
		}
		for (WebElement checkItem : allElement) {
			if(checkItem.isSelected()) {
				checkItem.click();
				sleepInSecond(3);
			}
		}
		return true;
	}
	
	public boolean isCheckBoxCustomer(By elementHiddens) {
		List<WebElement> checkAll= driver.findElements(elementHiddens);
		for (WebElement checkItem : checkAll) {
			if(checkItem.isEnabled()) {
				if(!checkItem.isSelected()) {
					 selectedHiddenRadio_Checkbox(checkItem);
					 sleepInSecond(3);
					 Assert.assertTrue(checkItem.isSelected());
				}
			}else {
				Assert.assertTrue(checkItem.isEnabled());
			}
		}
		for (WebElement checkItem : checkAll) {
			if(checkItem.isEnabled()) {
				if(checkItem.isSelected()) {
					 selectedHiddenRadio_Checkbox(checkItem);
					 sleepInSecond(3);
					 Assert.assertFalse(checkItem.isSelected());
				}
			}else {
				Assert.assertTrue(checkItem.isEnabled());
			}
		}
		return true;
	}

	public boolean isElementSelected(By elementXpath) {
		WebElement element=driver.findElement(elementXpath);
		if(!element.isSelected()) {
			element.click();
			sleepInSecond(3);
			element.click();
			return true;
		}else {
			element.click();
			return false;
		}
	}
	
	//radio cho phép chọn 1 và item đã selected khi click sẽ không vẫn là selected
	public boolean isRadioButton (By radioAllXpath) {
		List<WebElement> allElement=driver.findElements(radioAllXpath);
		int allRadio=allElement.size();
		for (WebElement radioItem : allElement) {
				if(allRadio>0) {
					if(radioItem.isEnabled()) {
						if(!radioItem.isSelected()) {
							radioItem.click();
							sleepInSecond(2);
							radioItem.click();
						Assert.assertTrue(radioItem.isSelected());
						allRadio--;
						}
					}else {
						Assert.assertTrue(radioItem.isEnabled());
					}
				}else break;
		}		
			return true;
	}
	
	//hàm check 1 phan tu radio customer và verify 
	public boolean isRaidoButtonCustomer( By elementHiddens) {
		List<WebElement> allElementHidden = driver.findElements(elementHiddens);
		//thuc hien chuc nang click radio
			for (WebElement elementHidden : allElementHidden) {
				if(elementHidden.isEnabled()) {
					if(!elementHidden.isSelected()) {
						selectedHiddenRadio_Checkbox(elementHidden);
						sleepInSecond(2);
							Assert.assertTrue(elementHidden.isSelected());
					}else {
						selectedHiddenRadio_Checkbox(elementHidden);
						sleepInSecond(2);
						Assert.assertTrue(elementHidden.isSelected());
						
					}
				}else {
					Assert.assertTrue(elementHidden.isEnabled());
				}
			}
			
			//kiem tra so element duoc selected
			for (WebElement elementHidden : allElementHidden) {
				int i=0;
				if(elementHidden.isSelected()) {
					i ++;
					if(i==1) {
						return true;
					}else {return false;}
				}
			}
			return true;
	}
	
	//Ham cho customer checkbox/radio button
	public void selectedHiddenRadio_Checkbox(WebElement element) {
		js.executeScript("arguments[0].click()", element);
	}
	//hàm remove attribute
	public void removeAttribute(WebElement element) {
		js.executeScript("arguments[0].removeAttribute('disabled')",element);
	}
	//hàm check element disable/enable
	public boolean isElementEnable(WebElement element) {
		if(element.isEnabled()) {
			return true;
		}else return false;
	}
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

} 
