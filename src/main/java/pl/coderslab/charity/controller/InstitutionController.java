package pl.coderslab.charity.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.charity.entity.Institution;
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

    @GetMapping("/delete/{id}")
    public String removeInstitution(@PathVariable("id") Long id){
        Institution institutionById = iService.findById(id);
        if(institutionById!=null){
            iService.remove(institutionById);
        }
        return "redirect:/admin/institution/list";
    }

    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("institution", new Institution());
        return "admin/admin-addinstitution";
    }

    @PostMapping("/add")
    public String create(@Valid Institution institution, BindingResult result){
        if(result.hasErrors()){
            return "admin/admin-addinstitution";
        }
        iService.create(institution);
        return "redirect:/admin/institution/list";
    }
}
