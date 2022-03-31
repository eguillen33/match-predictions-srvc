package com.eguillen.webscraper.repo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.eguillen.webscraper.model.Prediction;

public interface PredictionRepository extends MongoRepository<Prediction, String>{

  @Override
  @Query(value="{leagueName:'?0'}", fields="{'homeTeam' : 1, 'awayTeam' : 1}")
  List<Prediction> findAll();
  
  @Override
  public long count();
}
