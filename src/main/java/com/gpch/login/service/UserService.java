package com.gpch.login.service;

import com.gpch.login.model.Film;
import com.gpch.login.model.Role;
import com.gpch.login.model.User;
import com.gpch.login.repository.FilmRepository;
import com.gpch.login.repository.RoleRepository;
import com.gpch.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User saveUser(User user) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(user.getPassword().getBytes(), 0, user.getPassword().length());
            String hashedPass = new BigInteger(1, messageDigest.digest()).toString(16);
            if (hashedPass.length() < 32) {
                hashedPass = "0" + hashedPass;
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setActive(true);
            Role userRole = roleRepository.findByRole("USER");
            user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        } catch (java.security.NoSuchAlgorithmException e) {
            //nothing
        }
        return userRepository.save(user);
    }

    /*public List<Film> findAll(){
        return filmRepository.findAll();
    }*/
}