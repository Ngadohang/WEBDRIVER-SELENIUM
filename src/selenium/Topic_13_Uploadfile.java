package selenium;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;


public class Topic_13_Uploadfile {
	WebDriver driver;
	WebElement element;
	String source_folder = System.getProperty("user.dir");
	String name1="image1.jpg";
	String name2="image2.jpg";
	String name3="image3.jpg";
	String name4="image4.jpg";
	
	String image_path_01= source_folder +"\\fileUpload\\" + name1;
	String image_path_02= source_folder +"\\fileUpload\\" + name2;
	String image_path_03= source_folder +"\\fileUpload\\" + name3;
	String image_path_04= source_folder +"\\fileUpload\\" + name4;
	String [] sizeimages= {"8.82 KB","11.91 KB","5.52 KB","13.95 KB"};
	String sizeAll= String.join(" ",sizeimages);
	String [] nameimages= {name1,name2,name3,name4};
	String nameAll= String.join(" ",nameimages);
	
	
	String chromeUpload_OneTime= source_folder +"\\autoIt\\chromeUploadOneTime.exe";
	String fireFoxUpload_OneTime= source_folder+"\\autoIt\\firefoxUploadOneTime.exe";
	String chromeUpload_Multiple= source_folder+"\\autoIt\\chromeUploadMultiple.exe";
	String fireFoxUpload_Multiple= source_folder+"\\autoIt\\firefoxUploadMultiple.exe";
		

	public void TC_1_GeckoDriver() {
		System.setProperty("webdriver.geckodriver.driver", source_folder + "\\driver\\geckodriver.exe");
		driver =new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		System.out.println(nameAll);
		System.out.println(sizeAll);
		
		WebElement uploadFile =driver.findElement(By.cssSelector("input[type='file']"));
		//1 lần upload nhiều file(browser lasted mới có chức năng này)
		uploadFile.sendKeys(image_path_01+"\n"+image_path_02+"\n"+image_path_03+"\n"+image_path_04);
		//1 lần upload 1 file
		//uploadFile.sendKeys(image_path_01)
		
		//Trước khi startupload
		//Verity mỗi ảnh up lên có đầy đủ cột ảnh/cột tên/cột dung lượng/cột button/muốn verify tất cả các sản phẩm
		List<WebElement> rowImages= driver.findElements(By.xpath("//tbody/tr"));
			for (WebElement rowImage : rowImages) {
				if( rowImages.size()==nameimages.length) {
					Assert.assertTrue(true);
					List<WebElement> colImages = driver.findElements(By.xpath("//tbody//tr/td"));
					for (WebElement colDetail : colImages) {
						//Kiểm tra cot ảnh có hien thị
						Assert.assertTrue(driver.findElement(By.cssSelector("td>span.preview>canvas")).isDisplayed());
						
						
						//Kiểm tra cột tên có hiển thị đúng tên
						Assert.assertTrue(driver.findElement(By.cssSelector("td>p[class='name']")).isDisplayed());
						String nameActual=driver.findElement(By.cssSelector("td>p[class='name']")).getText();
						System.out.println(nameActual);
						
						
						Assert.assertTrue(nameAll.contains(nameActual));
						//Kiểm tra cột dung lương có đúng dung lượng
						Assert.assertTrue(driver.findElement(By.cssSelector("td>p[class='size']")).isDisplayed());
						String sizeActual=driver.findElement(By.cssSelector("td>p[class='size']")).getText();
						
						System.out.println(sizeActual);
						
						Assert.assertTrue(sizeAll.contains(sizeActual));
						//Verify cột button có hiển thị 3button edit/start/cancel
						Assert.assertTrue(driver.findElement(By.cssSelector("td>button.edit")).isDisplayed());
						Assert.assertTrue(driver.findElement(By.cssSelector("td>button.start")).isDisplayed());
						Assert.assertTrue(driver.findElement(By.cssSelector("td>button.cancel")).isDisplayed());
						Assert.assertTrue(driver.findElement(By.cssSelector("td>button.edit")).isEnabled());
						Assert.assertTrue(driver.findElement(By.cssSelector("td>button.start")).isEnabled());
						Assert.assertTrue(driver.findElement(By.cssSelector("td>button.cancel")).isEnabled());
						
						//Thực hiện upload ảnh
						WebElement imageStartButtons=driver.findElement(By.cssSelector("td>button.start"));
						//Thực hiện upload ảnh
						imageStartButtons.click();
						sleepInSecond(10);
						//verify button edit/start/cancel ẩn
						sleepInSecond(5);
						Assert.assertFalse(isElementexistDom("td>button.edit"));
						Assert.assertFalse(isElementexistDom("td>button.start"));
						Assert.assertFalse(isElementexistDom("td>button.cancel"));
						
						//verify button delete display and enable
						Assert.assertTrue(driver.findElement(By.cssSelector("td>button.delete")).isDisplayed());
						Assert.assertTrue(driver.findElement(By.cssSelector("td>button.delete")).isEnabled());
					
						//verify link tại name
						Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+nameActual+"']")).isDisplayed());
						
						//verify link anh
						Assert.assertTrue(isImageShow(driver.findElement(By.cssSelector("span.preview img"))));
						
						boolean result= isElementexistDom("td>span.preview>canvas");
						if(result==false) {break;}
						
					}
				}
				
		
			}
	}
	

	public void TC_2_Chrome_Gofile(){
		System.setProperty("webdriver.chrome.driver", source_folder +"\\driver\\chromedriver.exe");
		driver =new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("https://gofile.io/uploadFiles");
		
		WebElement uploadFile =driver.findElement(By.cssSelector("input[type='file']"));
		//1 lần upload nhiều file(browser lasted mới có chức năng này)
		uploadFile.sendKeys(image_path_01+"\n"+image_path_02+"\n"+image_path_03+"\n"+image_path_04);
		//1 lần upload 1 file
		//uploadFile.sendKeys(image_path_01)
		sleepInSecond(2);
		driver.findElement(By.cssSelector("button#uploadFiles-btnUpload")).click();
		//Assert.assertTrue(driver.findElement(By.xpath("//td[contains(.,'Download link')]/following-sibling::td/a")).isDisplayed());
		driver.findElement(By.xpath("//td[contains(.,'Download link')]/following-sibling::td/a")).click();
		String parentID= driver.getWindowHandle();	
		switchToWindowsById(parentID);
		//verify icon download
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+name1+"']/following-sibling::td[@class]//i[contains(@class,'download')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+name2+"']/following-sibling::td[@class]//i[contains(@class,'download')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+name3+"']/following-sibling::td[@class]//i[contains(@class,'download')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+name4+"']/following-sibling::td[@class]//i[contains(@class,'download')]")).isDisplayed());

		//verify icon play
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+name1+"']/following-sibling::td[@class]//i[contains(@class,'play')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+name2+"']/following-sibling::td[@class]//i[contains(@class,'play')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+name3+"']/following-sibling::td[@class]//i[contains(@class,'play')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+name4+"']/following-sibling::td[@class]//i[contains(@class,'play')]")).isDisplayed());
		
		
	}
	
	
	public void TC_3_AutoIt_Chrome() throws IOException {
		System.setProperty("webdriver.chrome.driver", source_folder +"\\driver\\chromedriver.exe");
		driver =new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.cssSelector("span.fileinput-button")).click();
		sleepInSecond(5);
		Runtime.getRuntime().exec(new String[] {chromeUpload_Multiple,image_path_01});
	}
	
	public void TC_4_AutoIt_Firefox() throws IOException {
		System.setProperty("webdriver.geckodriver.driver", source_folder +"\\driver\\geckodriver.exe");
		driver =new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.findElement(By.cssSelector("span.fileinput-button")).click();
		sleepInSecond(6);
		Runtime.getRuntime().exec(new String[] {fireFoxUpload_Multiple,image_path_01,image_path_02});
		
	}
	
	@Test
	public void TC_5_Robot() throws AWTException {
		System.setProperty("webdriver.chrome.driver", source_folder +"\\driver\\chromedriver.exe");
		driver =new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("https://gofile.io/uploadFiles");
		
		driver.findElement(By.id("dropZoneBtnSelect")).click();
		
		StringSelection select =new StringSelection(image_path_01);
		
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);	
		
		Robot robot= new Robot();
		sleepInSecond(4);
		
		//nhan enter rui nha enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		//giu Ctrl +V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		//bỏ Ctrl +V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		//nhan enter rui nha enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
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
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isElementexistDom(String cssLocator) {
		JavascriptExecutor js;
		js = (JavascriptExecutor) driver;
		Object element= js.executeScript("document.querySelector(\""+cssLocator+"\")");	
		if(element!=null) {
			return true;
		}else return false;
	}
		
	public boolean isImageShow(WebElement element) {
		JavascriptExecutor js;
		js= (JavascriptExecutor) driver;
		return (boolean) js.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", element);
		
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
