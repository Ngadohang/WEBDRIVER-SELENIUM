package TestNG;

import org.junit.Assert;
import org.testng.annotations.Test;

public class Topic_07_Dependency {
	@Test()
	public void TC_Register() {
		System.out.println("A");
		Assert.assertTrue(false);
	}
	
	@Test(dependsOnMethods="TC_Register")
	public void TC_Login() {
		System.out.println("B");
	
	}
}