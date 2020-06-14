package org.books.simpleonlinebookstore.services.baseservices;

import org.books.simpleonlinebookstore.models.User;
import org.books.simpleonlinebookstore.services.commercial.Priceable;

public interface CartService extends Priceable {

    //catalog books
    void buyBookForUser(User user);

    //catalog music
    void buyMusicForUser(User user);

}
