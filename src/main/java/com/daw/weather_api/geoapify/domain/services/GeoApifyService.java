package com.daw.weather_api.geoapify.domain.services;

import com.daw.weather_api.geoapify.domain.response.GeoApifyResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.Normalizer;

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

    private String normalizeCity(String city) {
        String[] parts = city.split(",");
        String cityName = parts[0].trim();

        String normalized = Normalizer.normalize(cityName, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{M}", "");
    }

    public GeoApifyResponse geocoding(String city) {
        String sanitizedCity = normalizeCity(city);

        String url = UriComponentsBuilder
                .fromHttpUrl("https://api.geoapify.com/v1/geocode/search")
                .queryParam("text", sanitizedCity)
                .queryParam("type", "city")
                .queryParam("filter", "countrycode:br")
                .queryParam("limit", 1)
                .queryParam("apiKey", apiKey)
                .toUriString();

        try {
            String json = restTemplate.getForObject(url, String.class);
            JsonNode root = objectMapper.readTree(json);
            JsonNode features = root.path("features");

            if (!features.isArray() || features.isEmpty()) {
                return null;
            }

            JsonNode properties = features.get(0).path("properties");
            double lat = properties.path("lat").asDouble();
            double lon = properties.path("lon").asDouble();

            GeoApifyResponse response = new GeoApifyResponse();
            response.setLat(lat);
            response.setLon(lon);

            return response;

        } catch (Exception e) {
            return null;
        }
    }
}
