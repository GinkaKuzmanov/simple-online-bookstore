package org.books.simpleonlinebookstore.web;

import lombok.extern.slf4j.Slf4j;
import org.books.simpleonlinebookstore.exceptions.InvalidEntityException;
import org.books.simpleonlinebookstore.models.User;
import org.books.simpleonlinebookstore.services.baseservices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Collection<User>> getAllUsers() {
        Collection<User> users = this.userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public User getUsers(@PathVariable("id") final Long id) {
        return this.userService.getUserById(id);

    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User created = this.userService.createUser(user);
        URI location = MvcUriComponentsBuilder.fromMethodName(UserController.class, "createUser", User.class)
                .pathSegment("{id}").buildAndExpand(created.getId()).toUri();
        log.info("User created: {}", location);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public User deleteUser(@PathVariable Long id) {
        return this.userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        if (!user.getId().equals(id)) throw new InvalidEntityException(
                String.format("User ID=%s from path is different from Entity ID=%s", id, user.getId()));
        User updated = this.userService.updateUser(user);
        log.info("User updated: {}", updated);
        return ResponseEntity.ok(updated);
    }


}
