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

        Mockito.when(mockFilmRepository.findByFilmGenre(anyString()))
                .thenReturn(Film);
        Mockito.when(mockFilmRepository.findByFilmName(anyString()))
                .thenReturn(Film);
    }

    @Test
    public void testFindFilmByFilmName() {
        // Setup
        final String filmname = "Зелёный Слоник";

        // Run the test
        final Film result = FilmServiceUnderTest.findByFilmName(filmname);

        // Verify the results
        assertEquals(filmname, result.getFilmName());
    }

    @Test
    public void testFindFilmByFilmGenre() {
        // Setup
        final String filmgenre= "Артхаус";

        // Run the test
        final Film result = FilmServiceUnderTest.findByFilmGenre(filmgenre);

        // Verify the results
        assertEquals(filmgenre, result.getFilmGenre());
    }
}