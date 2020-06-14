package org.books.simpleonlinebookstore.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.Email;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    private String mobilePhone;

    @Email
    private String secondEmail;

}
