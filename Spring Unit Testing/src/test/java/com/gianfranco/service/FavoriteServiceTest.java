package com.gianfranco.service;

import com.gianfranco.dao.FavoriteDao;
import com.gianfranco.domain.Favorite;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FavoriteServiceTest {
    @Mock
    private FavoriteDao favoriteDao;

    @InjectMocks
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllShouldReturnTwo() throws Exception {
        List<Favorite> favorites = Arrays.asList(
                new Favorite(),
                new Favorite()
        );

        when(favoriteDao.findAll()).thenReturn(favorites);

        assertEquals(2, favoriteService.findAll().size());

        verify(favoriteDao).findAll();
    }

    @Test
    public void findByIdShouldReturnOne() throws Exception {
        when(favoriteDao.findOne(1L)).thenReturn(new Favorite());
        assertThat(favoriteService.findById(1L), instanceOf(Favorite.class));
        verify(favoriteDao).findOne(1L);
    }

    @Test(expected = FavoriteNotFoundException.class)
    public void findByIdShouldThrowFavoriteNotFoundException() throws Exception {
        when(favoriteDao.findOne(1L)).thenReturn(null);
        favoriteService.findById(1L);
        verify(favoriteDao).findOne(1L);
    }
}
