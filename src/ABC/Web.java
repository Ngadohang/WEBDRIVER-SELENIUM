package ABC;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Web {
	@BeforeClass
	public void beforeClass() {
		System.out.println("before");
	}
	
	@Test(groups="Web")
	public void TC_01() {
		System.out.println("Web");
	}
	@Test(groups="Web")
	public void TC_02() {
		System.out.println("Web");
	}
	@Test(groups="Mobile")
	public void TC_03() {
		System.out.println("Web");
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("after");
	}
}
