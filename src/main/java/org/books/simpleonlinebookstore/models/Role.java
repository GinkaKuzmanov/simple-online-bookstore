package org.books.simpleonlinebookstore.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.books.simpleonlinebookstore.models.base.BaseEntity;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity implements GrantedAuthority {

    private String authority;

    public Role(String authority) {
        this.authority = authority;
    }

    @ManyToMany(mappedBy = "authorities",fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();


}
