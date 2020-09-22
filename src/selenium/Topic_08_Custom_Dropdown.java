package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor jsExecutor;

	
	
	@BeforeClass
	public void beforeClass() {
		driver=new FirefoxDriver();
		waitExplicit= new WebDriverWait(driver,30) ;
		jsExecutor =(JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
	public void TC_01_JQuery() {
		
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemDropdown(By.xpath("//span[@id='number-button']"), By.xpath("//ul[@id='number-menu']//div"),"10" );
		sleepInSecond(2);
		System.out.println(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText());
		//Verify giá trị đã chọn
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "10");
	}
	
	
	public void TC_02_Argular() {
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		//không thể verity khi text chứa trong thẻ bại ẩn 
		selectItemDropdown(By.xpath("//ejs-dropdownlist[@id='games']//span[@class='e-input-group-icon e-ddl-icon e-search-icon']"), By.xpath("//ul[@id='games_options']//li"),"Cricket" );
		//Cách gettext thông thường không thể verify 
		//Assert.assertEquals(driver.findElement(By.xpath("//select[@id='games_hidden']/option")).getText(), "Cricket");
		//sử dụng select
		//sử dụng js dùng css locator
		sleepInSecond(2);
		Assert.assertEquals(getHiddenText("select[id='games_hidden']>option"), "Cricket");
		System.out.println(getHiddenText("select[id='games_hidden']>option"));
		
		
	}
	
	public void TC_03_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemDropdown(By.xpath("//i[@class='dropdown icon']"),By.xpath("//div[@class='visible menu transition']/div//span"),"Jenny Hess");
		//verify
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Jenny Hess");
		System.out.println(driver.findElement(By.xpath("//div[@class='divider text']")).getText());
	}
	
	
	public void TC_4_Valu() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemDropdown(By.xpath("//div[@class='btn-group']/li"),By.xpath("//div[@class='btn-group']/ul/li/a"),"First Option");
		//verify
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(),"First Option");
		System.out.println(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText());
	}
	
	public void TC_5_Edit() {
		 //selectEditItemDropdown(By parent, By child, String expectedItem)
	}
	
	@Test
	public void TC_6_Multil() {
		String [] multilSelect= {"",};
//		selectMultilItemDropdown(By parent,By child, String[] expectedItem,By selectXpath);
//		areItemSelected(By selectedXpath,By actualXpath, String [] expectedItem);
	}
	
	//Viet ham truyen vao locator dropdown,truyen vao tat ca cac item cua dropdown tren DOM, gia tri mong muon 
	public void selectItemDropdown(By parent, By child, String expectedItem) {
		//Click locator dropdown
		driver.findElement(parent).click();
		
		//Cho dropdown load tất cả item tren DOM
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(child));
		
		//Lay het tat ca item tren DOM nay dua vao List
		List<WebElement> childItem= driver.findElements(child);
		
		//Dùng vong lăp for duyet tat ca cac phan tu trong list
		for (WebElement actualItem : childItem) {
			//Moi lan duyet qua kiem ta childItem co bang voi actualItem
			if(actualItem.getText().trim().equals(expectedItem)) {
				//scroll den vi tri item do
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);",actualItem);
				//làm scroll smooth hơn
				sleepInSecond(1);
				//click chon item
				actualItem.click();
				//dung vong lap
				break;
			}
			
		}
		
	}
	
	public void selectEditItemDropdown(By parent, By child, String expectedItem) {
		//Click locator dropdown
		driver.findElement(parent).clear();
		
		//edit input tim kiem
		driver.findElement(parent).sendKeys("expectedItem");
		
		//Cho dropdown load tất cả item tren DOM
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(child));
		
		//Lay het tat ca item tren DOM nay dua vao List
		List<WebElement> childItem= driver.findElements(child);
		
		//Dùng vong lăp for duyet tat ca cac phan tu trong list
		for (WebElement actualItem : childItem) {
			//Moi lan duyet qua kiem ta childItem co bang voi actualItem
			if(actualItem.getText().trim().equals(expectedItem)) {
				//scroll den vi tri item do
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);",actualItem);
				//làm scroll smooth hơn
				sleepInSecond(1);
				//click chon item
				actualItem.click();
				//dung vong lap
				break;
			}
			
		}
		
	}

	public void selectMultilItemDropdown(By parent,By child, String[] expectedItem) {
		//click vào parent
		driver.findElement(parent).click();
		//cho load tat ca cac item
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(child));
		//cho tat ca item vao list webelement
		List<WebElement> allItem=driver.findElements(child);
		//Duyet qua tat ca cac item element co trong mang
		for (WebElement actualItem : allItem) {
			//duyet qua cac item trong chuoi
			for (String childItem : expectedItem) {
				//Nếu getText item=gia tri chuoi
				if(actualItem.getText()==childItem) {
					//scroll đen vi tri 
					//scroll den vi tri item do
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);",actualItem);
					//click item, nêu item ẩn thì dung javascript
					actualItem.click();
					//jsExecutor.executeScript("arguments[0].click();",actualItem);
					//Kiem tra so luong item da select= so luong item trong expectedItem thì cho dung vong lap
					
					List<WebElement> selectedItem= driver.findElements(child);
					System.out.println("Selected Item"+ selectedItem.size());
					if(selectedItem.size()==expectedItem.length) {
						break;
					}
				}
			}
		}
		
	}
	
	
	//Kiểm tra item da duoc chon
	public boolean areItemSelected(By selectedXpath,By actualXpath, String [] expectedItem) {
		List<WebElement> selectedAllItem =driver.findElements(selectedXpath);
		int allItems= selectedAllItem.size();
		String expectedText =driver.findElement(actualXpath).getText();
		System.out.println(expectedText);
		
		if(allItems<3 && allItems>0) {
			for (String selectedText : expectedItem) {
				if(expectedText.contains(selectedText)) {
					break;
				}
			}
			return true;
			
		}else {
			return expectedText.equals(allItems+"of 12 selected");
			 
		}
		
	}
	

	
	public void sleepInSecond(long Second) {
		try {
			Thread.sleep(Second*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getHiddenText(String cssLocator) {
	 
		return (String)jsExecutor.executeScript("return document.querySelector(\""+cssLocator+"\").textContent");	 
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}	