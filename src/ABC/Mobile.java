package ABC;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Mobile {
	
	@BeforeClass
	public void beforeClass() {
		System.out.println("before");
	}
	
	@Test(groups="Mobile")
	public void TC_01() {
		System.out.println("Mobile");
	}
	@Test(groups="Web")
	public void TC_02() {
		System.out.println("Mobile");
	}
	@Test(groups="Mobile")
	public void TC_03() {
		System.out.println("Mobile");
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("after");
	}
}
