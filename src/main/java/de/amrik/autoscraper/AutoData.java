package de.amrik.autoscraper;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.By; 

class AutoData {

  public static final String URL = "https://www.autotrader.co.uk/car-search";
  public static final int MAX_PAGES = 100; // AutoTrader limits their own search to 100 pages
  public static final int MAX_PAGES_PER_DRIVER = 20;
  public static final Cookie cookie = new Cookie("consentUUID","a4af61aa-34bd-439d-ab43-5f668016bb29");

  public static final String SORT_BY = "?sort=";
  public static final String POSTCODE = "&postcode=";
  public static final String RADIUS = "&radius=";
  public static final String TRANSMISSION = "&transmission=";
  public static final String PRICE_FROM = "&price-from=";
  public static final String PRICE_TO = "&price-to=";
  public static final String MAX_MILES = "&maximum-mileage=";
  public static final String MIN_ENGINE = "&minimum-badge-engine-size=";
  public static final String MAX_ENGINE = "&maximum-badge-engine-size=";
  public static final String INCLUDE_DELIVERY_OPTION = "&include-delivery-option=";
  public static final String COLOUR = "&colour=";
  public static final String ONE_SEARCH_AD = "&onesearchad=";
  public static final String ADVERTISING_LOCATION = "&advertising-location=";
  

  public static final By COOKIES_ACCEPT_XPATH = By.xpath("/html/body/div/div[3]/div[3]/div[2]/button[2]");

  public static final By PAGES_CLASS = By.className("paginationMini__count");
  public static final By RESULTS_LIST_CLASS = By.className("search-page__results");

  public static final By ADVERT_CLASS = By.className("search-page__result");
  public static final By ADVERT_XPATH = By.xpath("/html/body/main/section/div/ul/li/article/div");
  
  public static final By CAR_TITLE_CLASS = By.className("product-card-details__title");
  public static final By CAR_PRICE_CLASS = By.className("product-card-pricing__price");
  public static final By CAR_TAGS_CLASS = By.className("listing-key-specs");


}
