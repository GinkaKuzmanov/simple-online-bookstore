package org.books.simpleonlinebookstore.services.baseservices;

import org.books.simpleonlinebookstore.models.base.Item;
import org.books.simpleonlinebookstore.models.items.Book;

import java.util.Collection;

public interface BookService {

    Collection<Book> getBooks();

    Item getBookById(Long id);

    Item createBook(Item item);

    Item updateBook(Item item);

    Item deleteBook(Long id);

    long getBookCount();
}
