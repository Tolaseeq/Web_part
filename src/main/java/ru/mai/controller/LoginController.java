package ru.mai.controller;

import ru.mai.model.Film;
import ru.mai.model.User;
import ru.mai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping(value={"/", "/login"})
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping(value="/registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUserName(user.getUserName());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

    @GetMapping(value="/user/cabinet")
    public String login(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        List<Film> films = user.getWatchedFilms();
        model.addAttribute("user", user);
        model.addAttribute("films", films);
        return "user_cabinet";
    }

    @GetMapping(value="/user/cabinet/change_data")
    public ModelAndView changeUserData(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("change_data");
        return modelAndView;
    }

    @PostMapping(value = "/user/cabinet/change_data")
    public ModelAndView updateUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User userExists = userService.findUserByUserName(auth.getName());
        if (userExists == null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "User not found!!!");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("redirect:/user/cabinet/change_data");
        } else {
            userExists.setName(user.getName());
            userExists.setLastName(user.getLastName());
            userExists.setEmail(user.getEmail());
            userExists.setPassword(user.getPassword());
            userService.saveUser(userExists);
            modelAndView.addObject("successMessage", "User data has been changed successfully");
            modelAndView.setViewName("redirect:/user/cabinet");
        }
        return modelAndView;
    }
}
