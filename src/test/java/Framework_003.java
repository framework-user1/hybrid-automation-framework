package test.java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.utility.*;


public class Framework_003 {
	public WebDriver driver;
	
	@Test
	public void login() throws Exception 
	{
		System.out.println("Logging into website -> http://store.demoqa.com/");
	    driver.findElement(By.xpath("//div[@id='account']/a")).click();
		driver.findElement(By.id("log")).sendKeys("framework_demo_123");
	    driver.findElement(By.id("pwd")).sendKeys("sisdell!123*");
	    driver.findElement(By.id("login")).click();
	    Thread.sleep(3000);
	}
	
	@BeforeTest
	public void beforeTest() throws Exception {
		System.setProperty("webdriver.ie.driver", Constant.Driver_Path + "IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		System.out.println("Beginning of the test..");
		System.out.println("Opening the website -> http://store.demoqa.com/");
		driver.get("http://store.demoqa.com/");
		driver.manage().window().maximize();
	    Thread.sleep(2000);
	}
	
	@AfterTest
	public void afterTest() throws Exception {
		driver.findElement(By.linkText("Log out")).click();
		Thread.sleep(2000);
		System.out.println("End of the test..");
		driver.close();
	}
}