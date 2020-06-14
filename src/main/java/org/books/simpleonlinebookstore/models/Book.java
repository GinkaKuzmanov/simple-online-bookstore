package org.books.simpleonlinebookstore.models;

import lombok.*;
import org.books.simpleonlinebookstore.models.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "books")
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class Book extends BaseEntity {

    @NonNull
    private String title;

    @NonNull
    private String authors;

    @NonNull
    private String publisher;

    @Column(scale = 2)
    private double price;

    @NonNull
    private String isbn;

    @NonNull
    private String genre;

    private Date publishedDate = new Date();

}
