package org.books.simpleonlinebookstore.services;

import org.books.simpleonlinebookstore.dao.RoleRepository;
import org.books.simpleonlinebookstore.dao.UserRepository;
import org.books.simpleonlinebookstore.exceptions.EntityNotFoundException;
import org.books.simpleonlinebookstore.exceptions.InvalidEntityException;
import org.books.simpleonlinebookstore.models.Role;
import org.books.simpleonlinebookstore.models.User;
import org.books.simpleonlinebookstore.services.baseservices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public Collection<User> getUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return this.userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("User with ID=%s not found.", id)));
    }

    @Override
    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() ->
                new EntityNotFoundException(String.format("User with email %s not found.", email)));
    }

    @Override
    public User createUser(User user) {
        this.userRepository.findByEmail(user.getEmail()).ifPresent(u -> {
            throw new InvalidEntityException(String.format("User with email '%s' already exists.", user.getEmail()));
        });
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        if (user.getAuthorities() == null || user.getAuthorities().size() == 0) {
            List<Role> roles = List.of(this.roleRepository.findByAuthority("ROLE_USER"));
            user.setAuthorities(roles);
        }

        user.setPassword(this.encoder.encode(user.getPassword()));
        user.setActive(true);
        return this.userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        user.setModified(LocalDateTime.now());
        return this.userRepository.saveAndFlush(user);
    }

    @Override
    public User deleteUser(Long id) {
        User old = this.getUserById(id);
        this.userRepository.deleteById(id);
        return old;
    }

    @Override
    public long getUsersCount() {
        return this.userRepository.count();
    }

    @Override
    public UserDetails getUserByUsername(String username) {
        return userRepository.findByEmail(username).orElseThrow(() ->
                new EntityNotFoundException(String.format("User '%s' not found.", username)));
    }
}
