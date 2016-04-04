package test.java;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import test.pageObjects.BaseClass;
import test.pageObjects.ProductListing_Page;
import test.utility.Constant;
import test.utility.ExcelUtils;
import test.utility.Log;
import test.utility.Utils;
import test.appModules.CheckOut_Action;
import test.appModules.Confirmation_Action;
import test.appModules.PaymentDetails_Action;
import test.appModules.ProductSelect_Action;
import test.appModules.SignIn_Action;
import test.appModules.Verification_Action;

public class Framework_002 {
	public WebDriver driver;
	private String sTestCaseName;
	private int iTestCaseRow;
	
  @BeforeMethod
  public void beforeMethod() throws Exception {
	  	DOMConfigurator.configure("log4j.xml");
	  	sTestCaseName = this.toString();
	  	sTestCaseName = Utils.getTestCaseName(this.toString());
		Log.startTestCase(sTestCaseName);
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");
		iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName,Constant.Col_TestCaseName);
		driver = Utils.OpenBrowser(iTestCaseRow);
		new BaseClass(driver);  
        }
  
  @Test
  public void test02() throws Exception {
	  try{
		SignIn_Action.Execute(iTestCaseRow);
		ProductSelect_Action.productType(iTestCaseRow);
/*		ProductSelect_Action.productNumber(iTestCaseRow);
		ProductListing_Page.PopUpAddToCart.btn_GoToCart().click();
		CheckOut_Action.Execute();
		PaymentDetails_Action.execute(iTestCaseRow);
		Confirmation_Action.Execute();
		Verification_Action.Execute(); */
		ExcelUtils.setCellData("Pass", iTestCaseRow, Constant.Col_Result);
	  }catch (Exception e)
	  {
		  ExcelUtils.setCellData("Fail", iTestCaseRow, Constant.Col_Result);
		  Utils.takeScreenshot(driver, sTestCaseName);
		  Log.error(e.getMessage());
		  throw (e);
	  }
  }
		
  @AfterMethod
  public void afterMethod() {
	    Log.endTestCase(sTestCaseName);
	    driver.close();
  		}
}