package org.books.simpleonlinebookstore.models.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.books.simpleonlinebookstore.models.User;
import org.books.simpleonlinebookstore.services.commercial.Priceable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@NoArgsConstructor
@Data
public abstract class Item extends BaseEntity implements Priceable {

    @NonNull
    @NotEmpty
    protected String title;

    protected LocalDateTime datePublished = LocalDateTime.now();

    @NonNull
    @Column(nullable = false)
    protected Double price;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = User.class, cascade
            = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    protected Set<User> buyers = new HashSet<>();

    @Override
    public Double calculatePrice() {
        //with Bulgarian Value-added tax
        return price * 1.20;
    }

}
