package de.amrik.autoscraper;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Browser implements Runnable {

  private WebDriver driver;
  private ChromeOptions options = new ChromeOptions();

  private String url;
  private int start;
  private int end;
  ArrayList<AutoAd> ads;

  public Browser(String url, int start, int end, ArrayList<AutoAd> ads) {

    this.url = url;
    this.start = start;
    this.end = end;
    this.ads = ads;
  }

  public void run() {
    startBrowser();

    for (int i = start; i <= end; i++) {
      driver.get(url + "&page=" + i);

      for (WebElement ad : driver.findElements(AutoData.ADVERT_CLASS)) {
        AutoAd currCar = new AutoAd();
        currCar.make = ad.findElement(AutoData.CAR_TITLE_CLASS).getText();
        currCar.price = ad.findElement(AutoData.CAR_PRICE_CLASS).getText();
        currCar.url = currCar.url + ad.getAttribute("id");
        currCar.tags = ad.findElement(AutoData.CAR_TAGS_CLASS).getText();

        ads.add(currCar);
      }
    }

    closeBrowser();
  }

  public void startBrowser() {
    WebDriverManager.chromedriver().setup();
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("user-agent=Literally fuck you autotrader...");
    // options.addArguments("--headless");
    driver = new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(120, TimeUnit.MICROSECONDS);
    driver.get(AutoData.URL);
    driver.manage().addCookie(AutoData.cookie);
  }

  public void closeBrowser() {
    if (driver != null) {
      driver.close();
    }
  }

  public ArrayList<AutoAd> getAds() {
    return ads;
  }
}