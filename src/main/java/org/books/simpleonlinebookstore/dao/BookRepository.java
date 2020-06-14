package org.books.simpleonlinebookstore.dao;

import org.books.simpleonlinebookstore.models.items.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
