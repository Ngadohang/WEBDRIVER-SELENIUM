package TestNG;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Priority_Skip_AlwaysRun {
	@BeforeClass
	public void beforeClass() {
		System.out.println("before");
		//Assert.assertTrue(false);
	}
	
	@Test(priority=1,enabled=false)
	public void Create_Accout() {
		System.out.println("Create_Accout");
	}
	@Test(priority=2,description="Edit Accout")
	public void Edit() {
		System.out.println("Edit");
	}
	@Test(priority=3)
	public void Search() {
		System.out.println("Search");
	}
	
	@Test(priority=3)
	public void View() {
		System.out.println("View");
	}
	@Test(priority=3)
	public void Delete() {
		System.out.println("Delete");
	}
	
	@AfterClass(alwaysRun=true)
	public void afterClass() {
		System.out.println("after");
	}
	
}
