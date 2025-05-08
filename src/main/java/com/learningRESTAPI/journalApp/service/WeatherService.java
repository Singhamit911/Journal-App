package com.learningRESTAPI.journalApp.service;

import com.learningRESTAPI.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apikey;

    private static final String API = "https://api.openweathermap.org/data/2.5/weather?q=CITY&appid=API_KEY&units=metric";
    //https://api.openweathermap.org/data/2.5/weather?q=Delhi&appid=YOUR_API_KEY&units=imperial

    @Autowired
    private RestTemplate restTemplate;
    public WeatherResponse getWeather(String city){
        String finalApi = API.replace("CITY",city).replace("API_KEY",apikey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }
}
