package pl.coderslab.charity.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String list(Model model) {
        model.addAttribute("institutions", iService.findAll());
        return "admin/admin-institutionlist";
    }

    @GetMapping("/delete/{id}")
    public String removeInstitution(@PathVariable("id") Long id) {
        Institution institutionById = iService.findById(id);
        if (institutionById != null) {
            iService.remove(institutionById);
        }
        return "redirect:/admin/institution/list";
    }

    @GetMapping("/form")
    public String addForm(Model model) {
        model.addAttribute("institution", new Institution());
        return "admin/admin-institutionform";
    }

    @PostMapping("/form")
    public String create(@RequestParam("institutionid") Long instId, @Valid Institution institution,
            BindingResult result) {
        if (result.hasErrors()) {                                               //checking for form errors
            return "admin/admin-institutionform";
        }
        Institution institutionToSave = new Institution();                      //creating Institution object for saving
        if (instId != null) {                                                   //checking if parameter institutionId is present
            institutionToSave = iService.findById(instId);                      //if it is, filling created object with form data
            institutionToSave.setName(institution.getName());
            institutionToSave.setDescription(institution.getDescription());
        } else {
            institutionToSave = institution;                                    //if it's not, putting form data into created object
        }
        iService.save(institutionToSave);                                       //saving data in DB
        return "redirect:/admin/institution/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("institution", iService.findById(id));
        return "admin/admin-institutionform";
    }
}
