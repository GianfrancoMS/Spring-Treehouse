package com.gianfranco.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.gianfranco.Application;
import com.gianfranco.domain.Favorite;
import com.gianfranco.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@DatabaseSetup("classpath:favorites.xml")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
public class FavoriteDaoTest {
    @Autowired
    private FavoriteDao favoriteDao;

    @Before
    public void setUp() {
        User user = new User();
        user.setId(1L);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null));
    }

    @Test
    public void findAllShowReturnTwo() throws Exception {
        assertThat(favoriteDao.findAll(), hasSize(2));
    }

    @Test
    public void findAllShowPersistEntity() throws Exception {
        String placeId = "treehoouseXDXD";
        Favorite favorite = new Favorite.FavoriteBuilder().withPlaceId(placeId).build();
        favoriteDao.saveForCurrentUser(favorite);
        assertThat(favoriteDao.findByPlaceId(placeId), notNullValue(Favorite.class));
    }
}
