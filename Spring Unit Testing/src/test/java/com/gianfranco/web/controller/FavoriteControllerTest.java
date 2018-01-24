package com.gianfranco.web.controller;

import com.gianfranco.domain.Favorite;
import com.gianfranco.service.FavoriteNotFoundException;
import com.gianfranco.service.FavoriteService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.gianfranco.domain.Favorite.FavoriteBuilder;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class FavoriteControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private FavoriteController favoriteController;

    @Mock
    private FavoriteService favoriteService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(favoriteController).build();
    }

    @Test
    public void indexShouldIncludeFavoritesInTheModel() throws Exception {
        List<Favorite> favorites = Arrays.asList(
                new FavoriteBuilder(1L).withAddress("Chicago").withPlaceId("chicago1").build(),
                new FavoriteBuilder(2L).withAddress("Omaha").withPlaceId("omaha1").build()
        );

        when(favoriteService.findAll()).thenReturn(favorites);

        mockMvc.perform(get("/favorites"))
                .andExpect(status().isOk())
                .andExpect(view().name("favorite/index"))
                .andExpect(model().attribute("favorites", favorites));

        verify(favoriteService).findAll();
    }

    @Test
    public void addFavoriteShouldRedirectToNewFavorite() throws Exception {

        doAnswer(invocation -> {
            Favorite favorite = (Favorite) invocation.getArguments()[0];
            favorite.setId(1L);
            return null;
        }).when(favoriteService).save(any(Favorite.class));
        mockMvc.perform(post("/favorites")
                .param("formattedAdress", "chicago, il")
                .param("placeId", "windycity")
        ).andExpect(redirectedUrl("/favorites/1"));

        verify(favoriteService).save(any(Favorite.class));
    }

    @Test
    public void detailShouldErrorOrNotFound() throws Exception {
        when(favoriteService.findById(1L)).thenThrow(FavoriteNotFoundException.class);

        mockMvc.perform(get("/favorites/1"))
                .andExpect(view().name("error"))
                .andExpect(model().attribute("ex", org.hamcrest.Matchers.instanceOf(FavoriteNotFoundException.class)));
        verify(favoriteService).findById(1L);
    }

}