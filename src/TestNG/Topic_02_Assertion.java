package TestNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assertion {
	@Test
	public void TC_01() {
		int a=10;
		int b=15;
		
		
		Assert.assertTrue(a<b);
		
		Assert.assertFalse(a==b);
		
		Assert.assertEquals(a, 10);
		
	}
}
