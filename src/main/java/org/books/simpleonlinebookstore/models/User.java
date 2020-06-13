package org.books.simpleonlinebookstore.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.books.simpleonlinebookstore.models.base.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @NonNull
    @Length(min = 2, max = 50)
    private String firstName;

    @NonNull
    @Length(min = 2, max = 50)
    private String lastName;

    @NonNull
    @Email
    @Column(unique = true, nullable = false)
    @NotNull
    @EqualsAndHashCode.Include
    private String email;

    @NonNull
    @Length(min = 4, max = 80)
    @Column(nullable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Embedded
    @JsonIgnore
    private Address address;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "mobilePhone", column = @Column(name = "mobile_phone")),
            @AttributeOverride(name = "secondEmail", column = @Column(name = "second_email"))
    })
    @JsonIgnore
    private Contact contact;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created = LocalDateTime.now();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modified = LocalDateTime.now();

    private boolean active = true;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> authorities = new HashSet<>();

}
