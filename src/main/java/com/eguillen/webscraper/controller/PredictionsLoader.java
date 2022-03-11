package com.eguillen.webscraper.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.eguillen.webscraper.model.Predictions;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Configuration
public class PredictionsLoader {

  private static final Logger LOG = LoggerFactory.getLogger(PredictionsLoader.class);
  private static final String URL =
      "https://daily-betting-tips.p.rapidapi.com/daily-betting-tip-api/items/daily_betting_tips?q=10.03.2022";
  private OkHttpClient client = new OkHttpClient();

  @Bean
  CommandLineRunner initPredictions() {
    return args -> {
      LOG.info("Preloading predictions...");
      Request request = new Request.Builder().url(URL).get()
          .addHeader("content-type", "application/json").addHeader("connection", "keep-alive")
          .addHeader("x-rapidapi-host", "daily-betting-tips.p.rapidapi.com")
          .addHeader("x-rapidapi-key", "6MFB3oTLJVmshMRUybuYAf2kGntKp1EpupFjsnJdaj22gd9qib")
          .build();

      try (Response response = client.newCall(request).execute()) {
        Gson gson = new GsonBuilder().setPrettyPrinting()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        Predictions entity = gson.fromJson(response.body().string(), Predictions.class);
        String prettyJson = gson.toJson(entity);
        LOG.info(prettyJson);
        System.out.println(prettyJson);
      }
    };
  }
}
