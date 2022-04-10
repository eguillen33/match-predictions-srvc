package com.eguillen.webscraper.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eguillen.webscraper.model.Prediction;
import com.eguillen.webscraper.repo.PredictionRepository;
import com.eguillen.webscraper.service.PredictionService;

@Service
public class PredictionServiceImpl implements PredictionService {

  @Autowired
  PredictionRepository repo;
	
  @Override
  public List<Prediction> getPredictions() {
    return repo.findAll();
  }

}
