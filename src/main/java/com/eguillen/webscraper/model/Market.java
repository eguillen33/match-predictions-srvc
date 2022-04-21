package com.eguillen.webscraper.model;

import java.util.List;

public class Market {
  private String key;
  private List<Outcome> outcomes;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public List<Outcome> getOutcomes() {
    return outcomes;
  }

  public void setOutcomes(List<Outcome> outcomes) {
    this.outcomes = outcomes;
  }
}
