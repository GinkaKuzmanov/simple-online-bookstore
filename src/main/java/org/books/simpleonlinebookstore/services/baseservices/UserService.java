package org.books.simpleonlinebookstore.services.baseservices;

import org.books.simpleonlinebookstore.models.User;

import java.util.Collection;

public interface UserService {

    Collection<User> getUsers();

    User getUserById(Long id);

    User getUserByEmail(String email);

    User createUser(User user);

    User updateUser(User user);

    User deleteUser(Long id);

    long getUsersCount();
}

