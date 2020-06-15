package org.books.simpleonlinebookstore.dao;

import org.books.simpleonlinebookstore.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByAuthority(String authority);
}
