package com.gianfranco.service.dto.geocoding;

import com.gianfranco.service.dto.Dto;

public class PlacesResponse extends Dto {
    private PlacesResult result;
    private String status;

    public PlacesResult getResult() {
        return result;
    }

    public void setResult(PlacesResult result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
