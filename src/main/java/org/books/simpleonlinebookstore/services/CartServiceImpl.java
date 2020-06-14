package org.books.simpleonlinebookstore.services;

import org.books.simpleonlinebookstore.dao.ItemRepository;
import org.books.simpleonlinebookstore.models.base.Item;
import org.books.simpleonlinebookstore.services.baseservices.CartService;
import org.books.simpleonlinebookstore.services.baseservices.UserService;
import org.books.simpleonlinebookstore.services.commercial.Priceable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CartServiceImpl implements CartService {

    private final ItemRepository itemRepository;
    private final UserService userService;

    @Autowired
    public CartServiceImpl(ItemRepository itemRepository, UserService userService) {
        this.itemRepository = itemRepository;
        this.userService = userService;
    }

    @Override
    public Double calculatePrice() {
        Double totalPrice = 0d;
        for (Priceable item : itemRepository.findAll()) {
            totalPrice += item.calculatePrice();
        }

        return totalPrice;
    }

    @Override
    public Collection<Item> getItems() {
        return null;
    }

    @Override
    public Item getItemById(Long id) {
        return null;
    }

    @Override
    public Item createItem(Item item) {
        return null;
    }

    @Override
    public Item updateItem(Item item) {
        return null;
    }

    @Override
    public Item deleteItem(Long id) {
        return null;
    }

    @Override
    public long getPostsCount() {
        return 0;
    }
}
