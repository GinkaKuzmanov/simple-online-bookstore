package org.books.simpleonlinebookstore.web.rest;

import org.books.simpleonlinebookstore.models.BillResponse;
import org.books.simpleonlinebookstore.models.items.Book;
import org.books.simpleonlinebookstore.models.items.Music;
import org.books.simpleonlinebookstore.services.baseservices.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/catalog")
public class CartController {

    private final CartService cartService;


    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/books")
    @PostAuthorize("isAuthenticated()")
    public ResponseEntity<BillResponse> buyBooks(@AuthenticationPrincipal UserDetails userDetails, @RequestBody List<Book> book) {
        String username = userDetails.getUsername();
        BillResponse billResponse = this.cartService.buyBookForUser(username, book);

        return new ResponseEntity<>(billResponse, HttpStatus.OK);
    }

    @PostMapping("/music")
    @PostAuthorize("isAuthenticated()")
    public ResponseEntity<BillResponse> buyMusic(@AuthenticationPrincipal UserDetails userDetails, @RequestBody List<Music> music) {
        String username = userDetails.getUsername();
        BillResponse billResponse = this.cartService.buyMusicForUser(username, music);
        return new ResponseEntity<>(billResponse, HttpStatus.OK);
    }

}

