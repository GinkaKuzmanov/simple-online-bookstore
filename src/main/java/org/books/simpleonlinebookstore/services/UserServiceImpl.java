package org.books.simpleonlinebookstore.services;

import org.books.simpleonlinebookstore.models.User;
import org.books.simpleonlinebookstore.services.baseservices.UserService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public Collection<User> getUsers() {
        return null;
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
}
