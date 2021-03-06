package org.books.simpleonlinebookstore.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.books.simpleonlinebookstore.exceptions.InvalidEntityException;
import org.books.simpleonlinebookstore.models.User;
import org.books.simpleonlinebookstore.models.items.Book;
import org.books.simpleonlinebookstore.models.items.Music;
import org.books.simpleonlinebookstore.services.baseservices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User created = this.userService.createUser(user);
        URI location = MvcUriComponentsBuilder.fromMethodName(UserController.class, "createUser", User.class)
                .pathSegment("{id}").buildAndExpand(created.getId()).toUri();
        log.info("User created: {}", location);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_ADMIN')")
    public User deleteUser(@PathVariable Long id) {
        return this.userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        if (!user.getId().equals(id)) throw new InvalidEntityException(
                String.format("User ID=%s from path is different from Entity ID=%s", id, user.getId()));
        User updated = this.userService.updateUser(user);
        log.info("User updated: {}", updated);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{userId}/books")
    public ResponseEntity<Collection<Book>> getUserBooks(@PathVariable Long userId) {
        User user = this.userService.getUserById(userId);
        Collection<Book> userBooks = user.getBooks();
        return ResponseEntity.ok(userBooks);
    }

    @GetMapping("/{userId}/music")
    public ResponseEntity<Collection<Music>> getUserMusic(@PathVariable Long userId) {
        User user = this.userService.getUserById(userId);
        Collection<Music> userMusic = user.getMusic();
        return ResponseEntity.ok(userMusic);
    }
}
