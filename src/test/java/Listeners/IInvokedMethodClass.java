package Listeners;

import Utilities.Utility;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import java.io.IOException;
import static DriverFactory.DriverFactory.getDriver;



public class IInvokedMethodClass implements IInvokedMethodListener {

    public void afterInvocation(IInvokedMethod method, ITestResult testResult){

        try{
            if (testResult.getStatus() == ITestResult.FAILURE){
                Utility.takeScreenshot(getDriver(), testResult.getName());
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }


}
