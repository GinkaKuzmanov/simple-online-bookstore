package org.books.simpleonlinebookstore.services.baseservices;

import org.books.simpleonlinebookstore.models.BillResponse;
import org.books.simpleonlinebookstore.models.items.Book;
import org.books.simpleonlinebookstore.models.items.Music;

import java.util.List;

public interface CartService {

    BillResponse buyBookForUser(String username, List<Book> book);

    BillResponse buyMusicForUser(String username, List<Music> music);

}
