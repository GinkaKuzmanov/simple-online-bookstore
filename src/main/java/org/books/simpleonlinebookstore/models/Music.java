package org.books.simpleonlinebookstore.models;

import lombok.*;
import org.books.simpleonlinebookstore.models.base.BaseEntity;
import org.books.simpleonlinebookstore.services.commercial.Priceable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Date;


@Entity
@Table(name = "music")
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Music extends BaseEntity implements Priceable {

    @NonNull
    @NotEmpty
    private String title;
    @NonNull
    @NotEmpty
    private String artist;
    @NonNull
    @NotEmpty
    private String producer;

    private Date datePublished = new Date();
    @NonNull
    @NotEmpty
    private Double price;

    @Override
    public Double calculatePrice() {
        return this.price;
    }
}
