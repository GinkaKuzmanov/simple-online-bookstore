package org.books.simpleonlinebookstore.services;

import org.books.simpleonlinebookstore.dao.ItemRepository;
import org.books.simpleonlinebookstore.models.base.Item;
import org.books.simpleonlinebookstore.services.baseservices.ItemService;
import org.books.simpleonlinebookstore.services.commercial.Priceable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;


    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Collection<Item> getItems() {
        return this.itemRepository.findAll();
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
