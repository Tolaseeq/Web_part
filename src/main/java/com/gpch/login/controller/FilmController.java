package com.gpch.login.controller;

import com.gpch.login.model.Film;
import com.gpch.login.model.User;
import com.gpch.login.service.FilmService;
import com.gpch.login.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class FilmController {

    public static final Logger logger = LoggerFactory.getLogger(FilmController.class);

    @Autowired
    private FilmService filmService;
    private UserService userService;

    @GetMapping(value="/user/films")
    public String login(Model model) {
        List<Film> listFilm = filmService.findAll();
        model.addAttribute("listFilm", listFilm);
        return "film_list_test";
    }

    @GetMapping("/user/add_to_watchlist/{id}")
    public String addToWatchlist(@RequestParam(value = "id") Long id, Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        var film = filmService.findById(id);
        if (film.isPresent()) {
            userService.addWatchedFilm(user, film.get());
        }

        logger.debug("Film is " + film.get());
        model.addAttribute("user", user);
        model.addAttribute("film", film);
        return "film_list_test";
    }
}
