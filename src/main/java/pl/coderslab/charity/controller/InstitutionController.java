package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.charity.service.InstitutionService;

@Controller
@RequestMapping("/admin/institution")
public class InstitutionController {
    
    private final InstitutionService iService;
    
    public InstitutionController(InstitutionService iService) {
        this.iService = iService;
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("institutions", iService.findAll());
        return "admin/admin-institutionlist";
    }
}
