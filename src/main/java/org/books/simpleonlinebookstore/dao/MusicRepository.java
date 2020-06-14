package org.books.simpleonlinebookstore.dao;

import org.books.simpleonlinebookstore.models.items.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {

}
