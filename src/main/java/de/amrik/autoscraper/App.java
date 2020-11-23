package de.amrik.autoscraper;

import de.amrik.autoscraper.Scraper;
import de.amrik.autoscraper.AutoAd;


/**
 * Test app to see how the api should be utlized in a proper scenario.
 * */
class App{

  public static void main(String[] args){
    Scraper scraper = new Scraper();

    scraper.scrape();
  }

}
