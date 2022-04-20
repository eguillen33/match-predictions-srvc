package com.eguillen.webscraper.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;
import com.eguillen.webscraper.model.Prediction;
import com.eguillen.webscraper.model.Predictions;
import com.eguillen.webscraper.repo.PredictionRepository;
import com.eguillen.webscraper.service.PredictionService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@EnableMongoRepositories(basePackageClasses = PredictionRepository.class)
@EnableCaching
@Service
public class PredictionServiceImpl implements PredictionService {
  private static final Logger LOG = LoggerFactory.getLogger(PredictionServiceImpl.class);
  private static final String BASE_URL =
      "https://daily-betting-tips.p.rapidapi.com/daily-betting-tip-api/items/daily_betting_tips";
  private static final String DATE_PARAM = "?q=";
  private OkHttpClient client = new OkHttpClient();

  @Autowired
  PredictionRepository repo;

  @Override
  public List<Prediction> getPredictions() {
    return repo.findAll();
  }

  @Override
  public void fetchPredictions(String date) throws IOException {
    Request request = new Request.Builder().url(buildURL(date)).get()
        .addHeader("content-type", "application/json").addHeader("connection", "keep-alive")
        .addHeader("x-rapidapi-host", "daily-betting-tips.p.rapidapi.com")
        .addHeader("x-rapidapi-key", "6MFB3oTLJVmshMRUybuYAf2kGntKp1EpupFjsnJdaj22gd9qib").build();

    try (Response response = client.newCall(request).execute()) {
      Gson gson = new GsonBuilder().setPrettyPrinting()
          .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
      Predictions predictions = gson.fromJson(response.body().string(), Predictions.class);
      predictions.setPredictions(predictions.getPredictions().stream()
          .filter(p -> (p.getLeagueName().contains("NBA"))).collect(Collectors.toList()));
      LOG.info("Found prediction data...");
      logPrettyJSON(gson, predictions);
      repo.saveAll(predictions.getPredictions());
    }
  }

  private String buildURL(String date) {
    return new StringBuilder(BASE_URL).append(DATE_PARAM).append(date).toString();
  }

  private void logPrettyJSON(Gson gson, Predictions entity) {
    String prettyJson = gson.toJson(entity);
    LOG.info(prettyJson);
  }

}
