package in.tc.testcases;

import org.testng.annotations.Test;

import in.tc.base.Page;
import in.tc.men.MenPage;


public class MenMenuTest{

	@Test()
	public void menMenuTest(){
		Page.menu.gotoMen();
		MenPage mp = new MenPage();
		mp.clickMenMenu();
	}
	
}
