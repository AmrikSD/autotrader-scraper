package de.amrik.autoscraper;

import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;

import de.amrik.autoscraper.AutoData;
import de.amrik.autoscraper.AutoAd;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;

/**
 * The main API interface.
 * From this you should be able to get everything you want.
 */
class Scraper {

  //Unknowns
  String postcode;
  int maxDistance;

  int minPrice;
  int maxPrice;

  int minYear;
  int maxYear;

  String gearbox;


  private static WebDriver driver;


  public Scraper(){
    //TODO: Pick some sane defaults.
    this.postcode = "SW1A2AA"; //TODO: check if just ignoring postcode, works, does website just guess for us?
    this.maxDistance = 200;
    this.minPrice = 1000;
    this.maxPrice = 10000;
    this.gearbox = "Automatic";
  }

  /**
   * Scraped data from autotrader.co.uk based on the parameters passed into the Scraper object
   * */
  public ArrayList <AutoAd> scrape(){

    // Build the Query
    String url = AutoData.URL;
    url += AutoData.SORT_BY + "relevance"; //Sort needs to go first.
    url += AutoData.POSTCODE + this.postcode;
    url += AutoData.RADIUS + this.maxDistance;
    url += AutoData.TRANSMISSION + this.gearbox;
    url += AutoData.PRICE_FROM + this.minPrice;
    url += AutoData.PRICE_TO + this.maxPrice;

    //TODO remove
    System.out.println(url);
    
    // Start Selenium
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    //TODO options.addArguments("--headless");
    driver = new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); 
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

    // Go to AutoTader. See how many pages there are - hardcap of 100. (AutoTrader does this)
    driver.get(url);
    WebElement pageCountElement = driver.findElement(By.className(AutoData.PAGES_CLASS)); 
    String pagesStr = pageCountElement.getText().split(" ")[3];
    int pages = Integer.parseInt(pagesStr);
    pages = Math.min(pages,100);

    System.out.println(pages);

    // Close Selenium
    if(driver != null){
      driver.close();
    }


    return new ArrayList <AutoAd>();
  }


  public void setPostcode(String postcode){

    this.postcode = postcode;
  }

  public void setMaxDistance(int maxDistance){
    this.maxDistance = maxDistance;
  }

  public void setMinPrice(int minPrice){
   this.minPrice = minPrice;
  }

  public void setMaxPrice(int maxPrice){
   this.maxPrice = maxPrice;
  }

  public void setMinYear(int minYear){
    this.minYear = minYear;
  }

  public void setMaxYear(int maxYear){
    this.maxYear = maxYear;
  }

  public void setGearbox(String gearbox){
    this.gearbox = gearbox;
  }

  public String getPostcode(){
    return postcode;
  }

  public int getMaxDistance(){
   return maxDistance;
  }

  public int getMinPrice(){
    return minPrice;
  }

  public int getMaxPrice(){
    return maxPrice;
  }

  public int getMinYear(){
    return minYear;
  }

  public int getMaxYear(){
    return maxYear;
  }

  public String getGearbox(){
    return gearbox;
  }

}
