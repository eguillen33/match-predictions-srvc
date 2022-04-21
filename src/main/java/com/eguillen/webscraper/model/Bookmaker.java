package com.eguillen.webscraper.model;

import java.util.List;

public class Bookmaker {

  private String key;
  private String title;
  private String lastUpdate;
  private List<Market> markets;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(String lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

  public List<Market> getMarkets() {
    return markets;
  }

  public void setMarkets(List<Market> markets) {
    this.markets = markets;
  }
}
