package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.DonationService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/donation")
public class DonationController {

    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;
    private final DonationService dService;

    public DonationController(CategoryRepository categoryRepository, InstitutionRepository institutionRepository,
            DonationService dService) {
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
        this.dService = dService;
    }

    @ModelAttribute("institutions")
    List<Institution> institutions() {
        return institutionRepository.findAll();
    }

    @ModelAttribute("categories")
    List<Category> categories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/form")
    public String add(Model model){
        model.addAttribute("donation",new Donation());
        return "donation/donation-form";
    }

    @PostMapping("/form")
    public String save(@Valid Donation donation, BindingResult result){
        if (result.hasErrors()){
            return "donation/donation-form";
        }
        dService.save(donation);
        return "redirect:/";
    }

}
