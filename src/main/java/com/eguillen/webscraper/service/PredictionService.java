package com.eguillen.webscraper.service;

import java.io.IOException;
import java.util.List;

import com.eguillen.webscraper.model.Prediction;

public interface PredictionService {

  List<Prediction> getPredictions(String date);

  void fetchPredictions() throws IOException;
}
