package de.amrik.autoscraper;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Browser implements Runnable {
  
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
   
  /**
   * Runs the browser to scrape from the url in setParams, populates a list of car adverts that can be retrieved using getCars()
   * */
  @Override
  public void run(){
    
    setupBrowser();

    for(int i = startPage; i<= endPage; i++){
      System.out.println(i);
    }


    closeBrowser();
  }


  private void setupBrowser(){
    WebDriverManager.chromedriver().setup();
    
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--headless");

    driver = new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    driver.get(AutoData.URL);
    driver.manage().addCookie(AutoData.cookie);
  }

  private void closeBrowser(){
   if(driver != null){
     driver.close();
   } 
  }

  /**
   * @return a list of @see de.amrik.autoscraper.AutoAd
   * */
  public ArrayList<AutoAd> getCars(){
    return cars;
  }

}
