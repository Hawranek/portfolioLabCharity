package pl.coderslab.charity.entity;

import lombok.Builder;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NumberFormat
    @NotNull(message = "Ilość przekazywanych worków nie może być mniejsza niż 1")
    @Min(1)
    private Integer quantity;
    @ManyToMany
    @NotEmpty(message = "Musisz wybrać przynajmniej jedną kategorię przekazywanych darów")
    private List<Category> categories;
    @ManyToOne
    @NotNull(message = "Wybierz instytucję, której chcesz przekazać dary")
    private Institution institution;
    @NotBlank(message = "Nie podałeś ulicy")
    private String street;
    @NotBlank(message = "Nie podałeś miasta")
    private String city;
    @NotBlank(message = "Nie podałeś kodu pocztowego")
    private String zipCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Wybierz datę odebrania darów od Ciebie")
    @Future
    private LocalDate pickUpDate;
    @DateTimeFormat(pattern = "HH:mm")
    @NotNull(message = "Wybierz godzinę odebrania darów od Ciebie")
    private LocalTime pickUpTime;
    private String pickUpComment;

    //tutaj można zamiast wpisywania w klasie message dla każdej walidacji dodać plik z listą message'ów

    @Override
    public String toString() {
        return "Donation{" +
                "quantity=" + quantity +
                ", categories=" + categories +
                ", institution=" + institution +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", pickUpDate=" + pickUpDate +
                ", pickUpTime=" + pickUpTime +
                ", pickUpComment='" + pickUpComment + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public String getPickUpComment() {
        return pickUpComment;
    }

    public void setPickUpComment(String pickUpComment) {
        this.pickUpComment = pickUpComment;
    }
}
