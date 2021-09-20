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
import org.slf4j.Logger;

@Controller
public class FilmController {

    public static final Logger logger = LoggerFactory.getLogger(FilmController.class);

    @Autowired
    private FilmService filmService;
    @Autowired
    private UserService userService;

    @GetMapping(value="/user/films")
    public String login(Model model) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        List<Film> listFilm = filmService.findAll();
        model.addAttribute("listFilm", listFilm);
        model.addAttribute("user", user);
        return "film_list_test";
    }

    @GetMapping("/user/add_to_watchlist/{id}")
    public String addToWatchlist(@PathVariable("id") Integer id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        var film = filmService.findById(id);
        if (film.isPresent()) {
            userService.addWatchedFilm(user, film.get());
        }
        return "redirect:/user/films";
    }
}
