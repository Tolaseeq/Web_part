package com.gpch.login.controller;

import com.gpch.login.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FilmController {
    @Autowired
    private FilmService filmService;

    @GetMapping(value="/user/films")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("films_list");
        return modelAndView;
    }
}
