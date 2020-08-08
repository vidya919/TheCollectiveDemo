package in.tc.pages;

import in.tc.base.Page;

public class TcAppPage extends Page{

	
	public MyAccountPage gotoMyAccout(){
	
		click("myaccountlink_XPATH");
		click("myaccount_XPATH");
		
		return new MyAccountPage();
	}
	
	public void gotoWishlist(){
		
	}
}
