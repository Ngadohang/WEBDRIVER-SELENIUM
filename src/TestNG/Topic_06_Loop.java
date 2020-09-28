package TestNG;

import org.testng.annotations.Test;

public class Topic_06_Loop {
	@Test(invocationCount=100)
	public void TC_01() {
		for(int i=1;i<=100;i++) {
		System.out.println("user"+i);
	}
	
	}
}