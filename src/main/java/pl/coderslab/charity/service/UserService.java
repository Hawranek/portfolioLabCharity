package pl.coderslab.charity.service;

import java.util.List;

import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;

public interface UserService {
    User findByEmail(String email);
    void saveUser(User user);
    void upgradeToAdmin(User user);
    void degradeAdmin(User user);
    List<User> findAll();
    User findById(Long id);
    void toggleEnable(User user);
    void deleteUser(User user);
    List<Role> getRoles();
}
