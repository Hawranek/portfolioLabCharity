package pl.coderslab.charity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Donation;
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

    //instead of this way of adding modelattribute, we can
    //add this list in homeAction as
    // model.addAttribute("institutions",institutionRepository.findAll());
    @ModelAttribute("institutions")
    public List<Institution> institutions() {
        return institutionRepository.findAll();
    }

    //j.w.
    @ModelAttribute("donations")
    public List<Donation> donations() {
        return donationRepository.findAll();
    }

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository, CategoryRepository categoryRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping("/")
    public String homeAction(Model model) {
//        Donation donation=new Donation();
//        donation.setQuantity(5);
//        donation.setInstitution(institutionRepository.findById(1L).orElse(null));
//        donation.setCategories(Arrays.asList(categoryRepository.findById(1L).orElse(null)));
//        donation.setStreet("Jakaśtam");
//        donation.setCity("Warszawa");
//        donation.setZipCode("02-822");
//        donation.setPickUpDate(LocalDate.now());
//        donation.setPickUpTime(LocalTime.now());
//        donation.setPickUpComment("Bez komentarza");
//        donationRepository.save(donation);

        Integer allByQuantity = donationRepository.sumOfQuantities();
        model.addAttribute("quantity", allByQuantity);
        return "index";
    }
}
