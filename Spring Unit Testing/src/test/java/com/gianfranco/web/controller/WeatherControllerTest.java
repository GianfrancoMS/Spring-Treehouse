package com.gianfranco.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class WeatherControllerTest {
    private MockMvc mockMvc;
    private WeatherController weatherController;

    @Before
    public void setUp() {
        weatherController = new WeatherController();
        mockMvc = MockMvcBuilders.standaloneSetup(weatherController).build();
    }

    @Test
    public void homeShouldRenderDetailView() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(view().name("weather/detail"));
    }

    @Test
    public void searchShouldRedirectWithPathParameter() throws Exception {
        mockMvc.perform(get("/search").param("q", "60657"))
                .andExpect(redirectedUrl("/search/60657"));
    }
}
