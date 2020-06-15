package org.books.simpleonlinebookstore.services;

import org.books.simpleonlinebookstore.dao.UserRepository;
import org.books.simpleonlinebookstore.exceptions.EntityNotFoundException;
import org.books.simpleonlinebookstore.models.User;
import org.books.simpleonlinebookstore.services.baseservices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public Collection<User> getUsers() {
        return this.userRepository.findAll()
                .stream().peek(user -> {
                    String pass = user.getPassword();
                    user.setPassword(this.encoder.encode(pass));
                }).collect(Collectors.toList());
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User deleteUser(Long id) {
        return null;
    }

    @Override
    public long getUsersCount() {
        return 0;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByEmail(username).orElseThrow(() ->
                new EntityNotFoundException(String.format("User '%s' not found.", username)));
    }
}
