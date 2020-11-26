package de.amrik.autoscraper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;


public class Browser {

  private volatile ArrayList<AutoAd> cars = new ArrayList<AutoAd>();
  
  private String url;
  private int startPage;
  private int endPage;

  private static WebDriver driver;

  /**
   * @param url the base URL that the browser should query - controlled by setters at @see de.amrik.autoscraper.scraper. 
   * @param startPage the page to start this browser from, useful when using more than one browser in parallel.
   * @param endPage the page to end scraping from, this is useful whne using more than one browser in parallel.
   *
   * */
  public void setParams(String url, int startPage, int endPage){
    this.url = url;
    this.startPage = Math.max(1, startPage);
    this.endPage = Math.min(AutoData.MAX_PAGES, endPage); 
  }
  
  /**
   * @param url the base URL that the browser should query - controlled by setters at @see de.amrik.autoscraper.scraper. 
   * @param endPage the page to end scraping from, this is useful whne using more than one browser in parallel.
   *
   * */
  public void setParams(String url, int endPage){
    this.url = url;
    this.endPage = Math.min(AutoData.MAX_PAGES, endPage); 
  }
  
  /**
   * @param url the base URL that the browser should query - controlled by setters at @see de.amrik.autoscraper.scraper. 
   *
   * */
  public void setParams(String url){
    this.url = url;
    this.startPage = Math.max(1, startPage);
    this.endPage = Math.min(AutoData.MAX_PAGES, endPage); 
  }
   
  public ArrayList<AutoAd> call(){
    
    setupBrowser();

    for(int i = startPage; i<=endPage;i++){
      driver.get(url+"&page="+i);
      
      for(WebElement ad: driver.findElements(AutoData.ADVERT_CLASS)){
        AutoAd currCar = new AutoAd();
        currCar.make = ad.findElement(AutoData.CAR_TITLE_CLASS).getText();
        currCar.price = ad.findElement(AutoData.CAR_PRICE_CLASS).getText();
        currCar.url = currCar.url + ad.getAttribute("id");
        currCar.tags = ad.findElement(AutoData.CAR_TAGS_CLASS).getText();


        cars.add(currCar);
      }
    
    }

    closeBrowser();

    return cars;

  }


  private void setupBrowser(){
    WebDriverManager.chromedriver().setup();
    
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("user-agent=fuck you autotrader LOL WORMS");
    options.addArguments("--headless");

    driver = new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    driver.get(AutoData.URL);
    driver.manage().addCookie(AutoData.cookie);
    driver.get(AutoData.URL);
  }

  private void closeBrowser(){
   if(driver != null){
     driver.close();
   } 
  }

}
