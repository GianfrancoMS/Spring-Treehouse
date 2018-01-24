package com.gianfranco.service;

import com.gianfranco.service.dto.geocoding.GeocodingResult;

public interface GeocodingService {
    GeocodingResult findBySearchTerm(String q);
}