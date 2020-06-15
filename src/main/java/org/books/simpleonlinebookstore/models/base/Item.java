package org.books.simpleonlinebookstore.models.base;

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
public class Item extends BaseEntity implements Priceable {

    @NonNull
    @NotEmpty
    protected String title;

    protected LocalDateTime datePublished = LocalDateTime.now();

    @NotEmpty
    @NonNull
    @Column(columnDefinition = "money", scale = 2)
    protected Double price;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinTable(name = "items_users",
            joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    protected Set<User> buyers = new HashSet<>();

    @Override
    public Double calculatePrice() {
        return price;
    }

}
