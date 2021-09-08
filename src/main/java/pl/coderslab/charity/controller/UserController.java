package pl.coderslab.charity.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String form(Model model) {
        model.addAttribute("user", new User());
        return "user/register-form";
    }

    @PostMapping("/register")
    public String save(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user/register-form";
        }
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("user", new User());
        return "user/login-form";
    }
}
