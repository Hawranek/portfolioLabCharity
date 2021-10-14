package pl.coderslab.charity.controller;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.CurrentUser;
import pl.coderslab.charity.service.UserService;

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
        userService.createUser(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "user/login-form";
    }

    // dodać akcję i widok do edycji profilu zalogowanego użytkownika
    @GetMapping("/editprofile")
    public String editForm(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        model.addAttribute("user", currentUser.getUser());
        return "user/edit-form";
    }

    @PostMapping("/editprofile")
    public String editSave(@RequestParam("userId") Long userId, @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user/edit-form";
        }
        User userToSave = new User();
        if (userId != null) {
            userToSave = userService.findById(userId);
            userToSave.setEmail(user.getEmail());
            userToSave.setPassword(user.getPassword());
            userToSave.setFirstName(user.getFirstName());
            userToSave.setLastName(user.getLastName());
            userService.setRoleUser(userToSave);
            userToSave.setEnabled(true);
        } else {
            userToSave = user;
        }
        userService.saveUser(userToSave);
        return "redirect:/";
    }
}
