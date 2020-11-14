package de.amrik.autoscraper;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver; 

/**
 * Unit test for just selenium.
 * 
 */
public class SeleniumTest {
  private static FirefoxDriver driver;
  private WebElement element;

  @BeforeClass
	public static void openBrowser(){
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

  @Test
  public void getGoogle(){
     System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
     driver.get("http://192.168.0.15");
     element = driver.findElementByXPath("/html/body/div/header/nav/a");
     assertEquals("OldMan Bot" , element.getText());
  }

  @AfterClass
  public static void closeBrowser(){
    driver.quit();
  }

}

