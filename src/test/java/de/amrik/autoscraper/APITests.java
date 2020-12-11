package de.amrik.autoscraper;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Assert;
import org.junit.Test;


public class APITests{

  private static Scraper scraper;
  private static ArrayList<AutoAd> cars;

  @BeforeClass
  public static void setUp(){
    scraper = new Scraper();
    cars = new ArrayList<AutoAd>();
  }

  @Test
  public void getSomeMiniCars(){
    System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());

    scraper.setMake("Mini");
    scraper.setPages(4);
    cars.addAll(scraper.scrape());
    for(AutoAd car : cars){
      System.out.println(car.make +" : "+car.price);
    }
    for(AutoAd car : cars){
      Assert.assertTrue(car.make.contains("MINI"));
    }
  }


}
