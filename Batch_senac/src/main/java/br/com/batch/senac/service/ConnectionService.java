package br.com.batch.senac.service;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ConnectionService {

	private final WebClient webClient = WebClient.builder().baseUrl("https://senac-sp.bennercloud.com.br").build();

	public String getToken() {
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("grant_type", "password");
		formData.add("username", "Benner.EBS");
		formData.add("password", "SUA_SENHA");
		formData.add("client_id", "Swagger");
		formData.add("client_secret", "");

		return webClient.post().uri("/app_services/auth.oauth2.svc/token").bodyValue(formData).retrieve()
				.bodyToMono(String.class).block();
	}

}
