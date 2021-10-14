package pl.coderslab.charity.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(InstitutionRepository institutionRepository, UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("roles")
    List<Role> roles() {
        return userService.getRoles();
    }

    @RequestMapping
    public String index() {
        return "admin/admin-index";
    }

    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/admin-users";
    }

    @GetMapping("/upgrade/{id}")
    public String upgradeToAdmin(@PathVariable("id") Long userId) {
        User userById = userService.findById(userId);
        if (userById != null) {
            userService.setRoleAdmin(userById);
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/degrade/{id}")
    public String degradeAdmin(@PathVariable("id") Long userId) {
        User userById = userService.findById(userId);
        if (userById != null) {
            userService.removeRoleAdmin(userById);
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/tenable/{userid}")
    public String enableUser(@PathVariable("userid") Long userId) {
        User userById = userService.findById(userId);
        if (userById != null) {
            userService.toggleEnable(userById);
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{userid}")
    public String deleteUser(@PathVariable("userid") Long userId) {
        User userById = userService.findById(userId);
        if (userById != null) {
            userService.deleteUser(userById);
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/adduser")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/admin-adduser";
    }

    @PostMapping("/adduser")
    public String addUser(@RequestParam("userid") Long userId, @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/admin-adduser";
        }
        User userToSave=new User();
        if (userId != null) {
            userToSave = userService.findById(userId);
            userToSave.setEmail(user.getEmail());
            userToSave.setPassword(user.getPassword());
            userToSave.setFirstName(user.getFirstName());
            userToSave.setLastName(user.getLastName());
            userToSave.setRoles(user.getRoles());
            userToSave.setEnabled(user.isEnabled());
        } else {
            userToSave = user;
        }
        userService.saveUser(userToSave);
        return "redirect:/admin/users";
    }

    @GetMapping("edituser/{userid}")
    public String editForm(@PathVariable("userid") Long userId, Model model) {
        model.addAttribute("user", userService.findById(userId));
        return "admin/admin-adduser";
    }
}
