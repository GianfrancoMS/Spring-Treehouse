package com.gianfranco.service.resttemplate;

import com.gianfranco.service.dto.geocoding.PlacesResult;

public interface PlacesService {
    PlacesResult findByPlaceId(String placeId);
}
