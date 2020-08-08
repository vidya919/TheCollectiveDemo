package in.tc.pages;

import in.tc.base.Page;

public class LoginPage extends Page{

	
	public void verifyTextLoginPage(){
		
	}
	
	public TcAppPage doLogin(String username , String password){
	
		type("email_XPATH",username);
		type("password_XPATH",password);
		click("loginbtn_XPATH");
		
		return new TcAppPage();
	}
}
