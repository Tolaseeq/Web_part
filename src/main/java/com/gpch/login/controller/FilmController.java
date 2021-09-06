package com.gpch.login.controller;

import com.gpch.login.model.Film;
import com.gpch.login.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FilmController {
    @Autowired
    private FilmService filmService;

    @GetMapping(value="/user/films")
    public String login(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Film> listFilm = filmService.findAll();
        model.addAttribute("listFilm", listFilm);
        return "film_list_test";
    }
}
