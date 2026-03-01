package br.com.batch.senac.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
public class WebClientConfig {

	@Value("${benner.api.base-url}")
	private String baseUrl;

	@Bean
	public WebClient webClient(WebClient.Builder builder) {
		return builder.baseUrl(baseUrl).build();
	}

	@Bean
	public ExecutorService executorService() {
		return Executors.newFixedThreadPool(30);
	}

	@Bean
	public Caffeine<Object, Object> caffeineConfig() {
		return Caffeine.newBuilder().maximumSize(10_000).expireAfterWrite(20, TimeUnit.MINUTES);
	}
}