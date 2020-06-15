package org.books.simpleonlinebookstore.dao;

import org.books.simpleonlinebookstore.models.items.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
    Optional<Music> findById(Long id);
}
