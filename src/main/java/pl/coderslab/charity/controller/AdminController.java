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

    @RequestMapping                 //Landing page for admin
    public String index() {
        return "admin/admin-index";
    }

    @GetMapping("/users")                                       //user list page
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/admin-users";
    }

    @GetMapping("/upgrade/{id}")                                //promoting user to admin
    public String upgradeToAdmin(@PathVariable("id") Long userId) {
        User userById = userService.findById(userId);
        if (userById != null) {
            userService.setRoleAdmin(userById);
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/degrade/{id}")                                //degrading user from admin
    public String degradeAdmin(@PathVariable("id") Long userId) {
        User userById = userService.findById(userId);
        if (userById != null) {
            userService.removeRoleAdmin(userById);
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/tenable/{userid}")                            //changing status from enabled to blocked and backwards
    public String enableUser(@PathVariable("userid") Long userId) {
        User userById = userService.findById(userId);
        if (userById != null) {
            userService.toggleEnable(userById);
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{userid}")                             //deleting user
    public String deleteUser(@PathVariable("userid") Long userId) {
        User userById = userService.findById(userId);
        if (userById != null) {
            userService.deleteUser(userById);
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/user/form")                                     //adding user form
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/admin-userform";
    }

    @PostMapping("/user/form")                                      //saving user data from form
    public String addUser(@RequestParam("userid") Long userId, @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/admin-userform";                       //checking if the form is filled properly
        }
        User userToSave=new User();                             //creating an User object to save it in DB
        if (userId != null) {                                   //checking if User parameter is a new user or edited user
            userToSave = userService.findById(userId);          //filling object to save with form user data
            userToSave.setEmail(user.getEmail());
            userToSave.setPassword(user.getPassword());
            userToSave.setFirstName(user.getFirstName());
            userToSave.setLastName(user.getLastName());
            userToSave.setRoles(user.getRoles());
            userToSave.setEnabled(user.isEnabled());
        } else {
            userToSave = user;                                  //if User parameter is a new user, filling user to save with form data
        }
        userService.saveUser(userToSave);                       //saving user in DB
        return "redirect:/admin/users";
    }

    @GetMapping("edituser/{userid}")                            //editing user form with filled data
    public String editForm(@PathVariable("userid") Long userId, Model model) {
        model.addAttribute("user", userService.findById(userId));
        return "admin/admin-userform";
    }
}
