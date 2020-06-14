package org.books.simpleonlinebookstore.dao;

import org.books.simpleonlinebookstore.models.base.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

}
