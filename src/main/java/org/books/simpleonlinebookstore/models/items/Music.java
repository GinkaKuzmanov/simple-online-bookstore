package org.books.simpleonlinebookstore.models.items;

import lombok.*;
import org.books.simpleonlinebookstore.models.base.Item;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "music")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Music extends Item {

    @NonNull
    @NotEmpty
    private String artist;

    @NonNull
    @NotEmpty
    private String producer;

}
