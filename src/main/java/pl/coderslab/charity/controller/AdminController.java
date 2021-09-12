package pl.coderslab.charity.controller;

import com.google.gson.Gson;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.CurrentUser;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final InstitutionRepository institutionRepository;

    public AdminController(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @ModelAttribute("institutions")
    private List<Institution> institutions(){return institutionRepository.findAll();}

    @RequestMapping
    public String index(@AuthenticationPrincipal CurrentUser currentUser, Model model){
        model.addAttribute("currentUser",currentUser.getUser());
        List<Institution> all = institutionRepository.findAll();
        Gson gson= new Gson();
        String strmap=gson.toJson(all.get(1));
        model.addAttribute("institution",strmap);
        return "admin/admin-index";
    }
}
