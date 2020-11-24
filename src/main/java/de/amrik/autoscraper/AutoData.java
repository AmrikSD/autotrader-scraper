package de.amrik.autoscraper;

class AutoData {

  public static final String URL = "https://www.autotrader.co.uk/car-search";
  public static final int MAX_PAGES = 100; // https://www.autotrader.co.uk/.....&page=101 is not valid ever

  public static final String PAGES_CLASS = "paginationMini__count";
  public static final String RESULTS_LIST_CLASS = "search-page__results";
  public static final String ADVERT_CLASS = "search-page__result";

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

}
