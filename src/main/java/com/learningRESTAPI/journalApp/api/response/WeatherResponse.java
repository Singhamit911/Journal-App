package com.learningRESTAPI.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse {
    private Main main;

    @Getter
    @Setter
    public static class Main {
        @JsonProperty("temp")
        private double temp;

        @JsonProperty("feels_like")
        private double feelsLike;
    }

    @Getter
    @Setter
    public static class Weather {
        @JsonProperty("id")
        private int id;

        @JsonProperty("main")
        private String main;

        @JsonProperty("description")
        private String description;
    }

}
