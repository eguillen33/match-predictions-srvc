package com.eguillen.webscraper.model;

import java.util.ArrayList;
import java.util.List;

public class Predictions {

  private List<Prediction> data = new ArrayList<>();
  
  public void setPredictions(List<Prediction> data) {
    this.data = data;
  }
  
  public List<Prediction> getPredictions() {
    return data;
  }
}
