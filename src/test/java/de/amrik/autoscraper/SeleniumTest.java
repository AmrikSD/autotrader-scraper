package de.amrik.autoscraper;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
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
      public void getGoogle(){
       System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
       
       driver.get("https://www.google.com");
       WebElement searchTxt = driver.findElement(By.name("q"));
       WebElement submitBtn = driver.findElement(By.name("btnK"));

       searchTxt.sendKeys("test");
       submitBtn.click();

       System.out.println("Current URL is: " + driver.getCurrentUrl());
       Assert.assertTrue(driver.getTitle().contains("test"));
       System.out.println("Current Title is: " + driver.getTitle());
      }

      @AfterClass
      public static void tearDown(){
          if (driver != null) {
              driver.quit();
          }
      }
  }
