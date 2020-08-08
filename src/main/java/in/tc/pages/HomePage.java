package in.tc.pages;

import in.tc.base.Page;

public class HomePage extends Page{


	public LoginPage goToLoginLink(){
	
		click("myaccountlink_XPATH");
		click("loginlink_XPATH");
		
		return new LoginPage();
	}
	
	public void goToBag(){
		
	}
	
	public void goToSearch(){
		
	}
	
}
