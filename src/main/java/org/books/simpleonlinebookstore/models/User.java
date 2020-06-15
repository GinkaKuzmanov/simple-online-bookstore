package org.books.simpleonlinebookstore.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.books.simpleonlinebookstore.models.base.BaseEntity;
import org.books.simpleonlinebookstore.models.items.Book;
import org.books.simpleonlinebookstore.models.items.Music;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
        ({"authorities", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "active", "enabled"})
public class User extends BaseEntity implements UserDetails {

    @NonNull
    @Length(min = 2, max = 50)
    private String firstName;

    @NonNull
    @Length(min = 2, max = 50)
    private String lastName;

    @NonNull
    @Email
    @Column(unique = true, nullable = false)
    @NotEmpty
    @EqualsAndHashCode.Include
    private String email;

    @NonNull
    @Length(min = 4, max = 80)
    @Column(nullable = false)
    @NotEmpty
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
    private Collection<Role> authorities = new HashSet<>();

    @ManyToMany(mappedBy = "buyers")
    @ToString.Exclude
    @JsonIgnore
    private Set<Book> books = new HashSet<>();

    @ManyToMany(mappedBy = "buyers")
    @ToString.Exclude
    @JsonIgnore
    private Set<Music> music = new HashSet<>();


    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.active;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
