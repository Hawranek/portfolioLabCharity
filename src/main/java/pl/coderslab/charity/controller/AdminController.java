package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.CurrentUser;
import pl.coderslab.charity.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final InstitutionRepository institutionRepository;
    private final UserService userService;

    public AdminController(InstitutionRepository institutionRepository, UserService userService) {
        this.institutionRepository = institutionRepository;
        this.userService = userService;
    }

    @RequestMapping
    public String index(){
        return "admin/admin-index";
    }

    @GetMapping("/users")
    public String userList(Model model){
        model.addAttribute("users", userService.findAll());
        return "admin/admin-users";
    }

    @GetMapping("/upgrade/{id}")
    public String upgradeToAdmin(@PathVariable("id") Long userId){
        User userById = userService.findById(userId);
        if(userById!=null){
            userService.upgradeToAdmin(userById);
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/degrade/{id}")
    public String degradeAdmin(@PathVariable("id") Long userId){
        User userById = userService.findById(userId);
        if(userById!=null){
            userService.degradeAdmin(userById);
        }
        //znaleźć użytkownika po id - dorobić implementację metody w serwisie
        //użyć metody degradeAdmin z userService
        return "redirect:/admin/users";
    }
}
