package de.amrik.autoscraper;

class AutoAd{

  public String url = "https://www.autotrader.co.uk";
  public int price = 0;
  public String make = "Default";
  public String model = "Car";
  public int mileage = 0;
  public int year = 2030;
  public String gearBox = "Automatic";
  public String colour = "Pink";
  public String engineSize = "0L";

  public Boolean isAd = false;

  @Override
  public String toString(){
   return "Url"+url+"/nPrice"+price+"/nMake"+make+"/nModel"+model; 
  }

}
