package org.books.simpleonlinebookstore.models.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.books.simpleonlinebookstore.models.User;
import org.books.simpleonlinebookstore.services.commercial.Priceable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@NoArgsConstructor
@Data
public class Item extends BaseEntity implements Priceable {

    @NonNull
    @NotEmpty
    private String title;

    private final Date datePublished = new Date();

    @NotEmpty
    @NonNull
    @Column(columnDefinition = "money", scale = 2)
    private Double price;

    @ManyToOne(targetEntity = User.class,cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
    private User user;

    @Override
    public Double calculatePrice() {
        return price;
    }

}
