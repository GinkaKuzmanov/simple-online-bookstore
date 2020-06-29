package org.books.simpleonlinebookstore.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.books.simpleonlinebookstore.exceptions.InvalidEntityException;
import org.books.simpleonlinebookstore.models.items.Book;
import org.books.simpleonlinebookstore.services.baseservices.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/api/books")
@Slf4j
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping
    public ResponseEntity<Collection<Book>> getBooks() {
        Collection<Book> books = this.bookService.getBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable final Long id) {
        Book book = this.bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book added = this.bookService.createBook(book);
        URI location = MvcUriComponentsBuilder.fromMethodName(BookController.class, "addBook", Book.class)
                .pathSegment("{id}").buildAndExpand(added.getId()).toUri();
        log.info("Book added:{}", location);
        return new ResponseEntity<>(added, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public Book deleteBook(@PathVariable final Long id) {
        return this.bookService.deleteBook(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable final Long id, @RequestBody Book book) {
        if (!book.getId().equals(id)) throw new InvalidEntityException(
                String.format("Book ID=%s from path is different from Entity ID=%s", id, book.getId()));
        Book updated = this.bookService.updateBook(book);
        log.info("Book updated: {}", updated);
        return ResponseEntity.ok(updated);
    }


}
