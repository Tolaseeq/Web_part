package com.gpch.login.repository;

import com.gpch.login.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    Film findByFilmName(String filmName);
    Film findByFilmGenre(String filmGenre);
}