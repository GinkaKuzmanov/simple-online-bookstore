package org.books.simpleonlinebookstore.services.baseservices;

import org.books.simpleonlinebookstore.models.base.Item;
import org.books.simpleonlinebookstore.services.commercial.Priceable;

import java.util.Collection;

public interface CartService extends Priceable {

    Collection<Item> getItems();

    Item getItemById(Long id);

    Item createItem(Item item);

    Item updateItem(Item item);

    Item deleteItem(Long id);

    long getPostsCount();
}
