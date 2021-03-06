package pl.coderslab.charity.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
            BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void setRoleAdmin(User user) {
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        Set<Role> userRoles = user.getRoles();
        userRoles.add(adminRole);
        user.setRoles(userRoles);
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void removeRoleAdmin(User user) {
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        if (user.getRoles().contains(adminRole)) {
            Set<Role> userRoles = user.getRoles();
            userRoles.remove(adminRole);
            user.setRoles(userRoles);
            userRepository.save(user);
        }
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void toggleEnable(User user) {
        if(user.isEnabled()){
            user.setEnabled(false);
        }else{
            user.setEnabled(true);
        }
        userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void createUser(User user) {
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<Role>(Arrays.asList(roleRepository.findByName("ROLE_USER"))));
        userRepository.save(user);        
    }

    @Override
    public void setRoleUser(User user) {
        user.setRoles(new HashSet<Role>(Arrays.asList(roleRepository.findByName("ROLE_USER"))));
        userRepository.save(user);
    }
}
