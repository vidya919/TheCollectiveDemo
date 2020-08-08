package in.tc.base;

import org.openqa.selenium.WebDriver;

import in.tc.men.MenPage;

public class TopMenu {

	WebDriver driver;

	public TopMenu(WebDriver driver) {

		this.driver = driver;
	}

	public MenPage gotoMen(){
		
		Page.mousehover("mousehovermen_XPATH");
		
		return new MenPage();
	}
	public void gotoWomen() {

	}
}
