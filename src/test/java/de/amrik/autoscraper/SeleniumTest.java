package de.amrik.autoscraper;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDrver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Unit test for just selenium.
 *
 */
public class SeleniumTest {

  private WebElement element;
  private FirefoxDriver driver;

  @BeforeClass
	public static void openBrowser(){
    WebDriverManager.firefoxdriver().setup();
    FirefoxOptions options = new FirefoxOptions();
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--headless");
    driver = new FirefoxDriver(options);
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
