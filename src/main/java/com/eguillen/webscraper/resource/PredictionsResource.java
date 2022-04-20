package com.eguillen.webscraper.resource;

import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.eguillen.webscraper.model.Prediction;
import com.eguillen.webscraper.service.PredictionService;

@RestController
@RequestMapping("/predictions")
public class PredictionsResource {

  private static final Logger LOG = LoggerFactory.getLogger(PredictionsResource.class);

  @Autowired
  PredictionService predictionService;

  @GetMapping("/all")
  List<Prediction> getAll(@RequestParam("date") String date) {
    if (date == null || date.isBlank()) {
      LOG.error("Missing date parameter");
      return null;
    }
    LOG.info("Loading predictions...");
    List<Prediction> predictions = predictionService.getPredictions();

    if (predictions == null || predictions.isEmpty()) {
      LOG.info(String.format("No predictions exists for date=%s. Fetching data...", date));
//      try {
//        predictionService.fetchPredictions(date);
//      } catch (IOException e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//      }
    }

    return predictions;
  }
}
