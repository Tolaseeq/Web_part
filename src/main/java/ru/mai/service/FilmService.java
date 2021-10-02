package ru.mai.service;

import ru.mai.model.Film;
import ru.mai.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FilmService {

    private FilmRepository filmRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Transactional
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    public Optional<Film> findById(Integer id) {
        return filmRepository.findById(id);
    }
}