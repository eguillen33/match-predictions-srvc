package com.eguillen.webscraper.resource;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eguillen.webscraper.model.Prediction;

@RestController
@RequestMapping("/predictions")
public class PredictionsResource {

  @GetMapping("/all")
  List<Prediction> getAll() {
    return new ArrayList<>();
  }
}
