package in.tc.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import in.tc.utilities.Utilities;

import in.tc.pages.HomePage;
import in.tc.pages.LoginPage;

public class LoginTest extends BaseTest{

	@Test(dataProviderClass = Utilities.class, dataProvider = "dp")
	public void loginTest(Hashtable<String,String> data) {
		
		HomePage page = new HomePage();
		LoginPage lp = page.goToLoginLink();
		lp.doLogin(data.get("username"), data.get("password"));
	}
}
