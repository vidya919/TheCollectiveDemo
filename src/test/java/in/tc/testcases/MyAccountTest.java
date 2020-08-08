package in.tc.testcases;

import org.testng.annotations.Test;

import in.tc.pages.MyAccountPage;
import in.tc.pages.TcAppPage;


public class MyAccountTest{

	@Test()
	public void myAccountTest(){
		
		TcAppPage tp = new TcAppPage();
		MyAccountPage map = tp.gotoMyAccout();
		map.Home();
	}
	
}


