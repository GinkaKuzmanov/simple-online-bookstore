package org.books.simpleonlinebookstore.services;

import org.books.simpleonlinebookstore.models.BillResponse;
import org.books.simpleonlinebookstore.models.User;
import org.books.simpleonlinebookstore.models.base.Item;
import org.books.simpleonlinebookstore.models.items.Book;
import org.books.simpleonlinebookstore.models.items.Music;
import org.books.simpleonlinebookstore.services.baseservices.BookService;
import org.books.simpleonlinebookstore.services.baseservices.CartService;
import org.books.simpleonlinebookstore.services.baseservices.MusicService;
import org.books.simpleonlinebookstore.services.baseservices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final UserService userService;
    private final BookService bookService;
    private final MusicService musicService;


    @Autowired
    public CartServiceImpl(UserService userService, BookService bookService, MusicService musicService) {
        this.userService = userService;
        this.bookService = bookService;
        this.musicService = musicService;
    }

    @Override
    public BillResponse buyBookForUser(String username, List<Book> book) {
        User user = this.userService.getUserByEmail(username);
        List<Book> existingBooks = book.stream()
                .map(b -> this.bookService.getBookById(b.getId()))
                .collect(Collectors.toList());
        user.getBooks().addAll(existingBooks);

        Double price = existingBooks.stream()
                .mapToDouble(Item::calculatePrice)
                .sum();
        return new BillResponse(user.getUsername(), price);
    }

    @Override
    public BillResponse buyMusicForUser(String username, List<Music> music) {
        User user = this.userService.getUserByEmail(username);
        List<Music> existingMusic = music.stream()
                .map(m -> this.musicService.getMusicById(m.getId()))
                .collect(Collectors.toList());

        user.getMusic().addAll(existingMusic);

        Double price = existingMusic.stream()
                .mapToDouble(Item::calculatePrice)
                .sum();

        return new BillResponse(user.getUsername(), price);
    }
}
