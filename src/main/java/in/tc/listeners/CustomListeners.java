package in.tc.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;
import in.tc.utilities.Utilities;
import in.tc.base.Page;

public class CustomListeners extends Page implements ITestListener{

	public void onTestStart(ITestResult result) {
		
		test = rep.startTest(result.getName().toUpperCase());
		
		}

	public void onTestSuccess(ITestResult result) {
		
		test.log(LogStatus.PASS,result.getName().toUpperCase()+"Pass");
		rep.endTest(test);
		rep.flush();
		
	}

	public void onTestFailure(ITestResult result) {
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			Utilities.CaptureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(LogStatus.FAIL,result.getName().toUpperCase()+"Failed with exception:"+result.getThrowable());
		test.log(LogStatus.FAIL,test.addScreenCapture(Utilities.screenshotName));
		Reporter.log("Capturing screenshot");
		Reporter.log("<a target=\"_blank\" href="+Utilities.screenshotName+">screenshot link</a>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+Utilities.screenshotName+"><img src="+Utilities.screenshotName+" height=200 weight=200></img></a>");
		rep.endTest(test);
		rep.flush();
	}

	public void onTestSkipped(ITestResult result) {
		
		test.log(LogStatus.SKIP, result.getName().toUpperCase()+"Skipped the test as runmode os NO");
		rep.endTest(test);
		rep.flush();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		
		
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
