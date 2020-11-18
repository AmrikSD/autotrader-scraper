package de.amrik.autoscraper;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


/**
 * Unit test for just selenium.
 *
 */
public class SeleniumTest {

      private static WebDriver driver;
      private WebElement element;
      
      @BeforeClass
      public static void setUp()
      {
          WebDriverManager.chromedriver().setup();
          ChromeOptions options = new ChromeOptions();
          options.addArguments("--no-sandbox");
          options.addArguments("--disable-dev-shm-usage");
          options.addArguments("--headless");
          driver = new ChromeDriver(options);
          driver.navigate().to("https://www.google.com");
          driver.manage().window().maximize();
          driver.manage().timeouts().implicitlyWait(120, TimeUnit.MILLISECONDS);
      }

      @Test
      public void userLogin(){
       System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
       driver.get("http://192.168.0.15");
       element = driver.findElement(new By.ByXPath("/html/body/div/header/nav/a"));
       assertEquals("OldMan Bot" , element.getText());
      }

      @AfterClass
      public static void tearDown(){
          if (driver != null) {
              driver.quit();
          }
      }
  }
