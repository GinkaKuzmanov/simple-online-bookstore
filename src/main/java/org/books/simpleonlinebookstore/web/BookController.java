package org.books.simpleonlinebookstore.web;

import org.books.simpleonlinebookstore.models.items.Book;
import org.books.simpleonlinebookstore.services.baseservices.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/items")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/all")
    public Collection<Book> getBooks(){
        return this.bookService.getBooks();
    }
}
