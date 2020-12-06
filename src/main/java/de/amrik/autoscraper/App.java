package de.amrik.autoscraper;

import java.util.List;

/**
 * Test app to see how the api should be utlized in a proper scenario.
 * */
class App{

  public static void main(String[] args){
    Scraper scraper = new Scraper();

    List<AutoAd> cars = scraper.scrape();

    for(AutoAd car : cars){
     System.out.println(car); 
    }
    System.out.println(cars.size());
  }

}
