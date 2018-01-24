package com.gianfranco.service;

import com.gianfranco.service.dto.geocoding.Location;
import com.gianfranco.service.dto.weather.Weather;

public interface WeatherService {
    Weather findByLocation(Location location);
}