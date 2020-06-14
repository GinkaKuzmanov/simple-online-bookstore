package org.books.simpleonlinebookstore.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credentials {

    @NonNull
    @NotEmpty
    private String username;

    @NonNull
    @NotEmpty
    @Length(min = 4, max = 30)
    private String password;
}