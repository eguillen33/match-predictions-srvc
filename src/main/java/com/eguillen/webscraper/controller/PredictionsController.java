package com.eguillen.webscraper.controller;

import java.time.Duration;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@Configuration
public class PredictionsController {
  private static final Logger LOG = LoggerFactory.getLogger(PredictionsController.class);
  private static final Instant start = Instant.now();

  @Bean
  CommandLineRunner commandLineRunner() {
    return args -> {
      final Instant end = Instant.now();
      final Duration timeElapsed = Duration.between(start, end);
      LOG.info(String.format("Match predictions application started in %d milliseconds", timeElapsed.toMillis()));
    };
  }

  @Bean
  public RedisCacheConfiguration cacheConfiguration() {
    return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(5))
        .disableCachingNullValues().serializeValuesWith(
            SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
  }

}
