package com.gpch.login.controller;

import com.gpch.login.model.Film;
import com.gpch.login.model.User;
import com.gpch.login.service.FilmService;
import com.gpch.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FilmController {
    @Autowired
    private FilmService filmService;
    private UserService userService;

    @GetMapping(value="/user/films")
    public String login(Model model) {
        List<Film> listFilm = filmService.findAll();
        model.addAttribute("listFilm", listFilm);
        return "film_list_test";
    }

    @RequestMapping("/user/add_to_watchlist/{id}")
    public String addToWatchlist(@RequestParam(value = "id") Long id, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        Film film = filmService.findById(id);
        userService.addWatchedFilm(user, film);
        model.addAttribute("user", user);
        model.addAttribute("film", film);
        return "film_list_test";
    }
}
