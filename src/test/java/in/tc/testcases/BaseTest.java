package in.tc.testcases;

import org.testng.annotations.AfterSuite;

import in.tc.base.Page;

public class BaseTest {

	@AfterSuite
	public void tearDown(){
		
		Page.quit();
		
	}
}
