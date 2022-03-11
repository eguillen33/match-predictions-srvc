package com.eguillen.webscraper.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eguillen.webscraper.model.Prediction;

@RestController
public class PredictionsController {

  @GetMapping("/predictions")
  List<Prediction> all() {
    return new ArrayList<>();
  }
}
