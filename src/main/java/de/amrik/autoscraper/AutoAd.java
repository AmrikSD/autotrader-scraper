package de.amrik.autoscraper;

class AutoAd{

  public String url;
  public int price;
  public int make;
  public int model;

  @Override
  public String toString(){
   return "Url"+url+"/nPrice"+price+"/nMake"+make+"/nModel"+model; 
  }

}
