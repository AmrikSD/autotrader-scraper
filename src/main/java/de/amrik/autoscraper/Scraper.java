package de.amrik.autoscraper;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.ArrayList;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * The main API interface.
 * From this you should be able to get everything you want.
 */
class Scraper {

  //Unknowns
  String postcode = "SW1A2AA";
  String make = "";
  String model = "";
  int maxDistance = 150;

  int minPrice = 1000;
  int maxPrice = 3000;

  int minYear = 0;
  int maxYear = 0;

  double minEngineSize = 0;
  double maxEngineSize = 2.0;

  String gearbox = "Automatic";

  int pagesToSearch = 100;

  private static WebDriver driver;

  public Scraper(){
  
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
    url += AutoData.MAKE + this.make;
    url += AutoData.MODEL + this.model;
    url += AutoData.MIN_ENGINE + this.minEngineSize;
    url += AutoData.MAX_ENGINE + this.maxEngineSize;
    url += AutoData.PRICE_FROM + this.minPrice;
    url += AutoData.PRICE_TO + this.maxPrice;

    System.out.println(url);

    // Start Selenium
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("user-agent=Literally fuck you autotrader...");
    options.addArguments("--headless");
    driver = new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(120, TimeUnit.MICROSECONDS);
    
    // Add the cookie so that autotrader can stop being little bitches..
    driver.get(AutoData.URL);
    driver.manage().addCookie(AutoData.cookie);


    // Get total number of pages.
    driver.get(url);   
    WebElement pageCountElement = driver.findElement(AutoData.PAGES_CLASS); 
    String pagesStr = pageCountElement.getText().split(" ")[3];
    int pages = Integer.parseInt(pagesStr);
    pages = Math.min(Math.min(pagesToSearch,pages),AutoData.MAX_PAGES);

    // Close Selenium
    if(driver != null){
      driver.close();
    }

    // Call some browsers to get the actual Car objects....
    // TODO: MAKE IT IT GO BRRRR (MULTITHREADING)
   

    int noOfBrowsers = (int) Math.ceil(pages*1.0 / AutoData.MAX_PAGES_PER_DRIVER*1.0);

    Browser[] browsers = new Browser[noOfBrowsers];
    Thread[] threadList = new Thread[noOfBrowsers];
    ArrayList<AutoAd>[] cars = new ArrayList[noOfBrowsers];

    ArrayList<AutoAd> returnList = new ArrayList<AutoAd>();

    for(int i = 0; i<noOfBrowsers; i++){
      int start = (i * AutoData.MAX_PAGES_PER_DRIVER);
      int end =  Math.min(pages ,((i+1) * AutoData.MAX_PAGES_PER_DRIVER) -1);
      cars[i] = new ArrayList<AutoAd>();
      browsers[i] = new Browser(url, start, end, cars[i]);
      threadList[i] = new Thread(browsers[i]);
    }


    for(Thread t : threadList){
      t.start();
    }

    for(Thread t : threadList){
      try{
        t.join();
      }catch(Exception e){
        System.out.println(e);
      }
    }

    for(Browser b : browsers){
      returnList.addAll(b.getAds());
    }

    return returnList;
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
  
  public void setMinEngineSize(double engSize){
    this.minEngineSize = engSize;
  }

  public void setMaxEngineSize(double engSize){
    this.maxEngineSize = engSize; 
  }

  public void setMake(String make){
    this.make = make;
  }

  public void setModel(String model){
    this.model = model; 
  }

  public void setPages(int pages){
    this.pagesToSearch = Math.min(100,pages);
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
  
  public double getMaxEngineSize(){
    return maxEngineSize;
  }

  public double getMinEngineSize(){
    return minEngineSize;
  }

  public String getMake(){
    return make;
  }
  public String getModel(){
    return model;
  }

  /**
   * return the number of pages the user wants to search, this can be more than what will actually be searched, if there are less pages on atotrader than the user wants..
   * */
  public int getPagesToSearch(){
    return pagesToSearch;
  }
}
