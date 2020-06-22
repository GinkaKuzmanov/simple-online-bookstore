package org.books.simpleonlinebookstore.services;

import org.books.simpleonlinebookstore.dao.UserRepository;
import org.books.simpleonlinebookstore.exceptions.InvalidEntityException;
import org.books.simpleonlinebookstore.models.User;
import org.books.simpleonlinebookstore.services.baseservices.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @BeforeEach
    private void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_RepositoryReturnsAllElements() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        Mockito.when(userRepository.findAll()).thenReturn(users);
        Collection<User> users1 = userService.getUsers();
        assertEquals(1, users1.size());
    }

    @Test
    public void test_createUser_ExistingUserThrowsException() {
        User user = new User();
        user.setEmail("panpan@abv.bg");
        Mockito.when(userRepository.findByEmail(user.getUsername()))
                .thenReturn(java.util.Optional.of(user));

        assertThrows(InvalidEntityException.class, () -> userService.createUser(user));
    }

    @Test
    public void test_getUserById() {
        User user = new User();
        user.setId(35L);
        Mockito.when(userRepository.findById(35L)).thenReturn(java.util.Optional.of(user));
        assertEquals(user, userService.getUserById(35L));
    }

    @Test
    public void test_getUserByEmail() {
        User user = new User();
        user.setEmail("gina@abv.bg");
        Mockito.when(userRepository.findByEmail("gina@abv.bg")).thenReturn(java.util.Optional.of(user));
        assertEquals(user, userService.getUserByEmail("gina@abv.bg"));
    }


}