package ru.mai.controller;

import ru.mai.model.Film;
import ru.mai.model.User;
import ru.mai.service.FilmService;
import ru.mai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
public class FilmController {

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
        return "film_list";
    }

    @GetMapping(value="/user/films/sort")
    public String sort(Model model) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        List<Film> listFilm = filmService.findAll();
        Collections.sort(listFilm, Film.COMPARE_BY_RATE);
        model.addAttribute("listFilm", listFilm);
        model.addAttribute("user", user);
        return "film_list";
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
