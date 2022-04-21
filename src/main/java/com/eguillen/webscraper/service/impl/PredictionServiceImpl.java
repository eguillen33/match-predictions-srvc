package com.eguillen.webscraper.service.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;
import com.eguillen.webscraper.model.Prediction;
import com.eguillen.webscraper.repo.PredictionRepository;
import com.eguillen.webscraper.service.PredictionService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@EnableMongoRepositories(basePackageClasses = PredictionRepository.class)
@EnableCaching
@Service
public class PredictionServiceImpl implements PredictionService {
  private static final Logger LOG = LoggerFactory.getLogger(PredictionServiceImpl.class);
  private static final String URL =
      "https://api.the-odds-api.com/v4/sports/basketball_nba/odds/?apiKey=e6bcd5f37bb6d16c32aaef9fe9023a0c&regions=us&markets=h2h,spreads,totals";
  private OkHttpClient client = new OkHttpClient();

  @Autowired
  PredictionRepository repo;

  @Override
  public List<Prediction> getPredictions(String date) {
    LOG.info(String.format("Getting predictions for date: %s", date));
    List<Prediction> predictions = new ArrayList<>();

    if (date != null) {
      // Check if we have data stored in redis cache or DB
      predictions = getFromCacheOrDB(date);
    }

    // If both cache and DB miss, fetch from external resource and save in DB and cache
    if ((predictions == null || predictions.isEmpty()) && date == null) {
      try {
        fetchPredictions();
        predictions = getFromCacheOrDB(date);
      } catch (IOException e) {
        throw new RuntimeException("Error fetching predictions", e);
      }
    }

    return predictions;
  }

  private List<Prediction> getFromCacheOrDB(String date) {
    // Try fetch from Redis cache
    List<Prediction> predictions = new ArrayList<>();

    // If cache miss, try fetch from DB
    if (predictions == null || predictions.isEmpty()) {
      LOG.info(String.format("Cache miss. Fetching data from DB..."));
      predictions = repo.findAll();
    }

    return predictions;
  }

  @Override
  public void fetchPredictions() throws IOException {
    Request request = new Request.Builder().url(URL).get().build();

    try (Response response = client.newCall(request).execute()) {
      String body = response.body().string();
      LOG.info("Raw response body:\n" + body);

      Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat(DateFormat.SHORT)
          .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

      List<Prediction> predictions =
          gson.fromJson(body, new TypeToken<ArrayList<Prediction>>() {}.getType());

      LOG.info("Found prediction data...");
      logPrettyJSON(gson, predictions);
      repo.saveAll(predictions);
    }
  }

  private void logPrettyJSON(Gson gson, List<Prediction> predictions) {
    String prettyJson = gson.toJson(predictions);
    LOG.info(prettyJson);
  }

}
