package pl.coderslab.charity.service;

import java.util.List;

import pl.coderslab.charity.entity.Institution;

public interface InstitutionService {
    List<Institution> findAll();
    void save(Institution institution);
    void remove(Institution institution);
    Institution findById(Long id);
}
