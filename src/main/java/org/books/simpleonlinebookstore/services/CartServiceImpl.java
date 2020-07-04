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

import java.util.ArrayList;
import java.util.List;

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
        List<Book> booksStillNotBought = new ArrayList<>(books);

        User user = this.userService.getUserByEmail(username);

        booksStillNotBought.removeIf(book -> user.getBooks().contains(book));

        user.getBooks().addAll(booksStillNotBought);
        this.userService.updateUser(user);

        for (Book book : booksStillNotBought) {
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
        List<Music> musicNotYetBought = new ArrayList<>(music);

        User user = this.userService.getUserByEmail(username);
        musicNotYetBought.removeIf(m -> user.getMusic().contains(m));

        user.getMusic().addAll(musicNotYetBought);

        this.userService.updateUser(user);

        for (Music song : musicNotYetBought) {
            song.getBuyers().add(user);
            this.musicService.updateMusic(song);
        }

        Double price = music.stream()
                .mapToDouble(Item::calculatePrice)
                .sum();

        return new BillResponse(user.getUsername(), price);
    }
}
