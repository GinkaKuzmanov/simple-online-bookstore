package org.books.simpleonlinebookstore.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    @NonNull
    @NotEmpty
    private String jwt;

    @NonNull
    @NotEmpty
    private User user;
}

