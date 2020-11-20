package de.amrik.autoscraper;

import java.util.ArrayList;

import de.amrik.autoscraper.AutoData;
import de.amrik.autoscraper.AutoAd;


/**
 * The main API interface.
 * From this you should be able to get everything you want.
 */
class Scraper {

  String baseURL = "";

  //Unknowns

  String postcode;
  int maxDistance;
  
  int minPrice;
  int maxPrice;
  
  int minYear;
  int maxYear;

  String gearbox;

  public Scraper(){
    //TODO: Pick some sane defaults.
    this.postcode = "SW1A 2AA"; //TODO: check if no postcode, does website use geolocation to guess.
    this.maxDistance = 1250;
    this.minPrice = 1000;
    this.maxPrice = 10000;
    this.minYear = 2005;
    this.maxYear = 2020;
    this.gearbox = "Automatic";
  }

  /**
   * Scraped data from autotrader.co.uk based on the parameters passed into the Scraper object
   * */
  public ArrayList <AutoAd> scrape(){
   //https://www.autotrader.co.uk/car-search?sort=relevance&postcode=KT174EX&radius=200&include-delivery-option=on&price-to=3000&transmission=Automatic&page=98 

    // Build the Query
    String url = AutoData.URL + "";
    System.out.println(url);
 

    // Start the selenium instance
    
    return new ArrayList <AutoAd>();
    // Make the searches & build the list


    // Stop selenlum
    
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
