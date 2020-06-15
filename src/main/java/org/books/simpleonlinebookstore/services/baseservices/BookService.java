package org.books.simpleonlinebookstore.services.baseservices;

import org.books.simpleonlinebookstore.models.items.Book;

import java.util.Collection;

public interface BookService {

    Collection<Book> getBooks();

    Book getBookById(Long id);

    Book createBook(Book book);

    Book updateBook(Book book);

    Book deleteBook(Long id);

    long getBookCount();
}
