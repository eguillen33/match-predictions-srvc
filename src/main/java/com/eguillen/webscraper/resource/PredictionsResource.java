package com.eguillen.webscraper.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eguillen.webscraper.model.Prediction;
import com.eguillen.webscraper.service.PredictionService;

@RestController
@RequestMapping("/predictions")
public class PredictionsResource {
	
  private static final Logger LOGGER = LoggerFactory.getLogger(PredictionsResource.class);
	
  @Autowired
  PredictionService perdictionService;

  @GetMapping("/all")
  List<Prediction> getAll() {
    LOGGER.debug("Fetching all predictions...");
    return perdictionService.getPredictions();
  }
}
