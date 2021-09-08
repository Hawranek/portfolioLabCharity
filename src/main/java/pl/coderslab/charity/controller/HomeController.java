package pl.coderslab.charity.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;


@Controller
public class HomeController {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final CategoryRepository categoryRepository;

    @ModelAttribute("institutions")
    public List<Institution> institutions() {
        //limitowanie ilości wyników, dzieląc je na strony, i wyświetlając konkretną z nich
        return institutionRepository.findAll(PageRequest.of(0, 4)).getContent();
    }

    //instead of this way of adding modelattribute, we can
    //add this list in homeAction as
    // model.addAttribute("institutions",institutionRepository.findAll());
    @ModelAttribute("donations")
    public long donations() {
        return donationRepository.count();
    }

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository, CategoryRepository categoryRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/")
    public String homeAction(Model model) {
        Integer allByQuantity = donationRepository.sumOfQuantities().orElse(0);
        model.addAttribute("quantity", allByQuantity);
        return "index";
    }
}
