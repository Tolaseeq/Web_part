package com.gpch.login.service;

import com.gpch.login.model.Film;
import com.gpch.login.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class FilmService {

    private FilmRepository filmRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public Film findByFilmGenre(String genre)
    {
        return filmRepository.findByFilmGenre(genre);
    }

    public Film findByFilmName(String filmName)
    {
        return filmRepository.findByFilmName(filmName);
    }

    public List<Film> findAll()
    {
        return filmRepository.findAll();
    }
}