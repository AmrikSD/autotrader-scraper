package de.amrik.autoscraper;

import de.amrik.autoscraper.AutoData;
import de.amrik.autoscraper.AutoAd;
import java.util.ArrayList;

/**
 * The main API interface.
 * From this you should be able to get everything you want.
 */
class Scraper {

  private static AutoData ad = new AutoData();

  //Unknowns

  String postcode;
  int maxDistance;
  
  int minPrice;
  int maxPrice;
  
  int minYear;
  int maxYear;

  String gearbox;

  public Scraper(){

  }

  /**
   * Scraped data from autotrader.co.uk based on the parameters passed into the Scraper object
   * */
  public ArrayList <AutoAd> scrape(){
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
