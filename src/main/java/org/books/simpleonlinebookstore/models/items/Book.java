package org.books.simpleonlinebookstore.models.items;

import lombok.*;
import org.books.simpleonlinebookstore.models.base.BaseEntity;
import org.books.simpleonlinebookstore.models.base.Item;
import org.books.simpleonlinebookstore.services.commercial.Priceable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "books")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Book extends Item {

    @NonNull
    @NotEmpty
    private String authors;

    @NonNull
    @NotEmpty
    private String publisher;

    @NonNull
    @NotEmpty
    private String isbn;

    @NonNull
    private String genre;

}
