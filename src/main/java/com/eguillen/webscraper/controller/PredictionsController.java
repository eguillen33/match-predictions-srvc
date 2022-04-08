package com.eguillen.webscraper.controller;

import java.time.Duration;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

import com.eguillen.webscraper.model.Predictions;
import com.eguillen.webscraper.repo.PredictionRepository;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@EnableMongoRepositories(basePackageClasses = PredictionRepository.class)
@Configuration
@EnableCaching
public class PredictionsController {

  private static final Logger LOG = LoggerFactory.getLogger(PredictionsController.class);
  private static final String URL =
      "https://daily-betting-tips.p.rapidapi.com/daily-betting-tip-api/items/daily_betting_tips?q=10.03.2022";
  private OkHttpClient client = new OkHttpClient();

  @Bean
  CommandLineRunner commandLineRunner(PredictionRepository repo) {
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
        Predictions predictions = gson.fromJson(response.body().string(), Predictions.class);
        predictions.setPredictions(predictions.getPredictions().stream()
            .filter(p -> (p.getLeagueName().contains("NBA"))).collect(Collectors.toList()));
        logPrettyJSON(gson, predictions);
        persistData(repo, predictions);
      }
    };
  }

  @Bean
  public RedisCacheConfiguration cacheConfiguration() {
    return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(5))
        .disableCachingNullValues().serializeValuesWith(
            SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
  }

  private void persistData(PredictionRepository repo, Predictions predictions) {
    LOG.info("Saving prediction data...");
    repo.saveAll(predictions.getPredictions());
    LOG.info("Finished saving prediction data...");
  }

  private static void logPrettyJSON(Gson gson, Predictions entity) {
    String prettyJson = gson.toJson(entity);
    LOG.info(prettyJson);
  }
}
