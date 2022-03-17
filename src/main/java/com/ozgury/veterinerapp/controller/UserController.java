package com.ozgury.veterinerapp.controller;

import com.ozgury.veterinerapp.dto.Person;
import com.ozgury.veterinerapp.dto.user.User;
import com.ozgury.veterinerapp.repository.UserRepository;
import com.ozgury.veterinerapp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class UserController {

   

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping("/register")
    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Kayıt ol");
        return "register_form";
    }

    @PostMapping("/register/save")
    public String saveUser(User user, RedirectAttributes ra) {
        Optional<User> checkUser = userService.findByUserEmail(user.getEmail());
        if (checkUser.isPresent()) {
            ra.addFlashAttribute("message", "Mail kullanımda");
            return "redirect:/register";
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userService.save(user);
        ra.addFlashAttribute("message", "User kayıt oldu.");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
