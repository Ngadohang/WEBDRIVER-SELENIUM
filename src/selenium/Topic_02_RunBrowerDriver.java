package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_02_RunBrowerDriver {
        WebDriver driver;
        
    @Test
    public void TC_Run_on_Firefox() {
    	driver= new FirefoxDriver();
    	driver.get("http://www.facebook.com");
    	driver.quit();
    }
    @Test
    public void TC_Run_on_Chrome() {
    	System.setProperty("webdriver.chrome.driver",".\\driver\\chromedriver.exe");
    	driver=new ChromeDriver();
    	driver.get("http://www.facebook.com");
    	driver.quit(); 
    }
    
    @Test
    public void TC_Run_on_Edge_Chromium() {
    	System.setProperty("webdriver.edge.driver", ".\\driver\\msedgedriver.exe");
    	driver=new EdgeDriver();
    	driver.get("http://www.facebook.com");
    	driver.quit();
    }
   
    
}
