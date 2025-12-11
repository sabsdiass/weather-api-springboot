package com.daw.weather_api.geoapify.domain.services;

import com.daw.weather_api.geoapify.domain.response.GeoApifyResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GeoApifyService {

    @Value("${geoapify.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public GeoApifyService(RestTemplateBuilder builder, ObjectMapper objectMapper) {
        this.restTemplate = builder.build();
        this.objectMapper = objectMapper;
    }

    private String extractCityName(String city) {
        if (city == null) {
            return "";
        }
        String[] parts = city.split(",");
        return parts[0].trim(); // "São Paulo, SP" -> "São Paulo"
    }

    public GeoApifyResponse geocoding(String city) {
        String cityName = extractCityName(city);

        String url = UriComponentsBuilder
                .fromHttpUrl("https://api.geoapify.com/v1/geocode/search")
                .queryParam("text", cityName)          // ex: "São Paulo"
                .queryParam("type", "city")            // só cidades
                .queryParam("filter", "countrycode:br")
                .queryParam("lang", "pt")
                .queryParam("limit", 1)
                .queryParam("apiKey", apiKey)
                .toUriString();

        try {
            String json = restTemplate.getForObject(url, String.class);
            JsonNode root = objectMapper.readTree(json);
            JsonNode features = root.path("features");

            if (!features.isArray() || features.isEmpty()) {
                return null; // não encontrou nada
            }

            JsonNode properties = features.get(0).path("properties");
            double lat = properties.path("lat").asDouble();
            double lon = properties.path("lon").asDouble();

            GeoApifyResponse response = new GeoApifyResponse();
            response.setLat(lat);
            response.setLon(lon);
            return response;

        } catch (Exception e) {
            return null; // em caso de erro na API, devolve null e deixamos o app decidir
        }
    }
}
