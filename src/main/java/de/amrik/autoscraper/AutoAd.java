package de.amrik.autoscraper;

class AutoAd{

  public String url = "https://www.autotrader.co.uk/car-details/";
  public String make = "NOMAKE";
  public String price = "£0";
  public String tags = "NOTAGS";

  public Boolean isAd = false;

  @Override
  public String toString(){
   return "Url "+url+"\nPrice "+price+"\nMake "+make; 
  }

}
