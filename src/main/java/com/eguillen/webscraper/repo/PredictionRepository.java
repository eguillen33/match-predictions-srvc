package com.eguillen.webscraper.repo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.eguillen.webscraper.model.Prediction;

public interface PredictionRepository extends MongoRepository<Prediction, String> {

  @Override
  //@Query(value="{}", fields="{'homeTeam' : 1, 'awayTeam' : 1}")
  List<Prediction> findAll();

}
