package com.gpch.login.service;

import com.gpch.login.model.Film;
import com.gpch.login.repository.FilmRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

public class FilmServiceTest {

    @Mock
    private FilmRepository mockFilmRepository;

    private FilmService FilmServiceUnderTest;
    private Film Film;

    @Before
    public void setUp() {
        initMocks(this);
        FilmServiceUnderTest = new FilmService(mockFilmRepository);
        Film = Film.builder()
                .id(1)
                .filmName("Зелёный Слоник")
                .filmGenre("Артхаус")
                .filmDescription("Описание 3")
                .build();

    }
}