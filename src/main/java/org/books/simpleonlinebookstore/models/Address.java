package org.books.simpleonlinebookstore.models;

import lombok.*;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @NotNull
    @NonNull
    private String street;

    @NotNull
    @NonNull
    private String city;

    @NotNull
    @NonNull
    private String country;

}
