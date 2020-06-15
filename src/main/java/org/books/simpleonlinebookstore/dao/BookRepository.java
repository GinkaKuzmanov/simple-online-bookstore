package org.books.simpleonlinebookstore.dao;

import lombok.NonNull;
import org.books.simpleonlinebookstore.models.items.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findById(Long id);
    Optional<Book> findByIsbn(@NonNull @NotEmpty String isbn);
}
