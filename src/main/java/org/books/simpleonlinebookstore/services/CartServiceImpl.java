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
    public BillResponse buyBookForUser(String username, List<Book> books) {
        User user = this.userService.getUserByEmail(username);
        user.getBooks().addAll(books);

        this.userService.updateUser(user);

        for (Book book : books) {
            book.getBuyers().add(user);
            this.bookService.updateBook(book);
        }

        Double price = books.stream()
                .mapToDouble(Item::calculatePrice)
                .sum();

        return new BillResponse(user.getUsername(), price);
    }

    @Override
    public BillResponse buyMusicForUser(String username, List<Music> music) {
        User user = this.userService.getUserByEmail(username);
        user.getMusic().addAll(music);

        this.userService.updateUser(user);

        for (Music song : music) {
            song.getBuyers().add(user);
            this.musicService.updateMusic(song);
        }

        Double price = music.stream()
                .mapToDouble(Item::calculatePrice)
                .sum();

        return new BillResponse(user.getUsername(), price);
    }
}
