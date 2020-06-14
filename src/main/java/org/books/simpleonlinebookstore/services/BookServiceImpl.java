package org.books.simpleonlinebookstore.services;

import org.books.simpleonlinebookstore.dao.BookRepository;
import org.books.simpleonlinebookstore.models.base.Item;
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
    public Item getBookById(Long id) {
        return null;
    }

    @Override
    public Item createBook(Item item) {
        return null;
    }

    @Override
    public Item updateBook(Item item) {
        return null;
    }

    @Override
    public Item deleteBook(Long id) {
        return null;
    }

    @Override
    public long getBookCount() {
        return 0;
    }


}
