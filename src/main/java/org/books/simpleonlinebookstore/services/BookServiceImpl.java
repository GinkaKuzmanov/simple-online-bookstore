package org.books.simpleonlinebookstore.services;

import org.books.simpleonlinebookstore.dao.BookRepository;
import org.books.simpleonlinebookstore.exceptions.EntityNotFoundException;
import org.books.simpleonlinebookstore.models.items.Book;
import org.books.simpleonlinebookstore.services.baseservices.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;


    @Autowired
    public BookServiceImpl( BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getBooks() {

        return this.bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return this.bookRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Book with ID=%s not found.", id)));
    }

    @Override
    public Book createBook(Book book) {
        return null;
    }

    @Override
    public Book updateBook(Book book) {
        return null;
    }

    @Override
    public Book deleteBook(Long id) {
        return null;
    }

    @Override
    public long getBookCount() {
        return 0;
    }


}
